package com.rokucraft.rokuemotes.config;

import com.rokucraft.rokuemotes.config.serializers.SoundSerializer;
import com.rokucraft.rokuemotes.di.DataPath;
import net.kyori.adventure.sound.Sound;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigHelper {
    private final YamlConfigurationLoader loader;
    private Config config;

    @Inject
    public ConfigHelper(@DataPath Path dataPath) {
        Path configPath = dataPath.resolve("config.yml");
        if (Files.notExists(configPath)) {
            try {
                Files.createDirectories(configPath.getParent());
                Files.createFile(configPath);
            } catch (IOException e) {
                throw new RuntimeException("Couldn't create items.yml", e);
            }
        }
        loader = YamlConfigurationLoader.builder()
                .path(configPath)
                .defaultOptions(opts -> opts.serializers(b -> b.register(Sound.class, SoundSerializer.INSTANCE)))
                .build();
    }

    public Config getConfig() {
        if (this.config == null) {
            try {
                this.config = loader.load().get(Config.class, Config.DEFAULT);
            } catch (ConfigurateException e) {
                throw new RuntimeException("Could not load config!");
            }
        }
        return this.config;
    }
}
