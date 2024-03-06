package com.rokucraft.rokuemotes.config;

import com.rokucraft.rokuemotes.RokuEmotesPlugin;
import com.rokucraft.rokuemotes.config.serializers.SoundSerializer;
import net.kyori.adventure.sound.Sound;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

public class ConfigHelper {
    private final RokuEmotesPlugin plugin;
    private final YamlConfigurationLoader loader;

    public ConfigHelper(RokuEmotesPlugin plugin) {
        this.plugin = plugin;
        loader = YamlConfigurationLoader.builder()
                .path(plugin.getDataFolder().toPath().resolve("config.yml"))
                .defaultOptions(opts -> opts.serializers(b -> b.register(Sound.class, SoundSerializer.INSTANCE)))
                .build();
    }

    public Config load() {
        try {
            return loader.load().get(Config.class, Config.DEFAULT);
        } catch (ConfigurateException e) {
            plugin.getSLF4JLogger().error("Couldn't load configuration!", e);
            return Config.DEFAULT;
        }
    }
}
