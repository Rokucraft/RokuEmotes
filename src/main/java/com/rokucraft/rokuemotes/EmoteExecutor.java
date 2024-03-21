package com.rokucraft.rokuemotes;

import com.rokucraft.rokuemotes.data.Emote;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import javax.inject.Inject;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

@NullMarked
public class EmoteExecutor {

    @Inject
    public EmoteExecutor() {
    }

    public void perform(Emote emote, Player self, @Nullable Player other) {
        boolean isSelf = other == null;
        Component message;
        if (isSelf) {
            message = miniMessage().deserialize(emote.selfTemplate());
        } else {
            message = miniMessage().deserialize(
                    emote.otherTemplate(),
                    Placeholder.component("self", self.displayName()),
                    Placeholder.component("other", other.displayName())
            );
        }

        Bukkit.broadcast(message);

        self.playSound(emote.sound());
        if (!isSelf) {
            other.playSound(emote.sound());
        }
    }

    public void perform(Emote emote, Player self) {
        perform(emote, self, null);
    }
}
