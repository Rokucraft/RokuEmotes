package com.rokucraft.rokuemotes.di;

import com.rokucraft.rokuemotes.RokuEmotesPlugin;
import com.rokucraft.rokuemotes.command.CommandModule;
import dagger.BindsInstance;
import dagger.Component;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.bean.CommandBean;

import javax.inject.Singleton;
import java.util.Set;

@Component(modules = {RokuEmotesModule.class, CommandModule.class})
@Singleton
public interface RokuEmotesComponent {

    CommandManager<CommandSender> commandManager();

    Set<CommandBean<CommandSender>> commands();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder plugin(RokuEmotesPlugin plugin);
        RokuEmotesComponent build();
    }
}
