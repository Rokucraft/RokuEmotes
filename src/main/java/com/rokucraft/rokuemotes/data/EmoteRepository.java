package com.rokucraft.rokuemotes.data;

import com.rokucraft.rokuemotes.config.ConfigHelper;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import javax.inject.Inject;
import java.util.List;

@NullMarked
public class EmoteRepository {
    private final ConfigHelper configHelper;

    @Inject
    public EmoteRepository(ConfigHelper configHelper) {
        this.configHelper = configHelper;
    }

    private @Nullable Emote getById(String id) {
        return configHelper.getConfig().emotes().get(id);
    }

    private List<Emote> getAll() {
        return configHelper.getConfig().emotes().values().stream().toList();
    }
}
