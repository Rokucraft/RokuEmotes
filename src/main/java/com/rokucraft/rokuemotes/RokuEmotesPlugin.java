package com.rokucraft.rokuemotes;

import com.rokucraft.rokuemotes.config.Config;
import com.rokucraft.rokuemotes.config.ConfigHelper;
import org.bukkit.plugin.java.JavaPlugin;

public class RokuEmotesPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Config config = new ConfigHelper(this).load();
        EmoteRepository repository = new EmoteRepository(config.emotes());
    }
}
