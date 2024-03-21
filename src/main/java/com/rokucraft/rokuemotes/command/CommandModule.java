package com.rokucraft.rokuemotes.command;

import com.rokucraft.rokuemotes.RokuEmotesPlugin;
import com.rokucraft.rokuemotes.command.caption.DefaultCaptionsProvider;
import com.rokucraft.rokuemotes.data.EmoteRepository;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.bean.CommandBean;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.paper.PaperCommandManager;

import javax.inject.Singleton;

import static org.incendo.cloud.execution.ExecutionCoordinator.simpleCoordinator;

@Module
public abstract class CommandModule {
    @Provides
    @Singleton
    static CommandManager<CommandSender> provideCommandManager(
            RokuEmotesPlugin plugin,
            EmoteRepository emoteRepository
    ) {
        PaperCommandManager<CommandSender> manager = PaperCommandManager.createNative(plugin, simpleCoordinator());
        manager.registerBrigadier();
        manager.registerCommandPreProcessor(c -> {
            CommandContext<CommandSender> context = c.commandContext();
            context.store(CommandKeys.EMOTE_REPOSITORY, emoteRepository);
        });
        manager.captionRegistry().registerProvider(new DefaultCaptionsProvider<>());
        return manager;
    }

    @Binds
    @IntoSet
    abstract CommandBean<CommandSender> provideEmoteCommand(EmoteCommand command);
}
