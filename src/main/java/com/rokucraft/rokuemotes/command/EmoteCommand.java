package com.rokucraft.rokuemotes.command;

import com.rokucraft.rokuemotes.EmoteExecutor;
import com.rokucraft.rokuemotes.data.Emote;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.Command;
import org.incendo.cloud.bean.CommandBean;
import org.incendo.cloud.bean.CommandProperties;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.key.CloudKey;

import javax.inject.Inject;

import static com.rokucraft.rokuemotes.command.parser.EmoteParser.emoteParser;
import static net.kyori.adventure.text.Component.text;
import static org.incendo.cloud.bukkit.parser.PlayerParser.playerParser;

public class EmoteCommand extends CommandBean<CommandSender> {

    private final EmoteExecutor emoteExecutor;

    @Inject
    public EmoteCommand(EmoteExecutor emoteExecutor) {
        this.emoteExecutor = emoteExecutor;
    }

    private static final CloudKey<Emote> emoteKey = CloudKey.cloudKey("emote", Emote.class);
    private static final CloudKey<Player> targetKey = CloudKey.cloudKey("target", Player.class);

    @Override
    protected @NonNull CommandProperties properties() {
        return CommandProperties.of("emote", "em");
    }

    @Override
    protected Command.Builder<? extends CommandSender> configure(Command.Builder<CommandSender> builder) {
        return builder
                .senderType(Player.class)
                .required(emoteKey, emoteParser())
                .optional(targetKey, playerParser())
                .handler(this::handle);
    }

    private void handle(@NonNull CommandContext<Player> ctx) {
        Player sender = ctx.sender();
        Emote emote = ctx.get(emoteKey);
        if (!sender.hasPermission(emote.permission())) {
            sender.sendMessage(text("You don't have permission to use this emote.", NamedTextColor.RED));
            return;
        }
        emoteExecutor.perform(emote, ctx.getOrDefault(targetKey, null));
    }
}
