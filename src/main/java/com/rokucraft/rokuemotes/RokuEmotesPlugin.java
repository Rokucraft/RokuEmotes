package com.rokucraft.rokuemotes;

import com.rokucraft.rokuemotes.di.DaggerRokuEmotesComponent;
import com.rokucraft.rokuemotes.di.RokuEmotesComponent;
import org.bukkit.plugin.java.JavaPlugin;

public class RokuEmotesPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        RokuEmotesComponent component = DaggerRokuEmotesComponent.builder()
                .plugin(this)
                .build();
    }
}
