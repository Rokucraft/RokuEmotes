package com.rokucraft.rokuemotes.di;

import com.rokucraft.rokuemotes.RokuEmotesPlugin;
import dagger.Module;
import dagger.Provides;

import java.nio.file.Path;

@Module
public abstract class RokuEmotesModule {
    @Provides
    @DataPath
    static Path provideDataPath(RokuEmotesPlugin plugin) {
        return plugin.getDataFolder().toPath();
    }
}
