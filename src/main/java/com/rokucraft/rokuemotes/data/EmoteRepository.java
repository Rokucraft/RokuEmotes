package com.rokucraft.rokuemotes.data;

import com.rokucraft.rokuemotes.config.ConfigHelper;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class EmoteRepository {
    private final ConfigHelper configHelper;

    @Inject
    public EmoteRepository(ConfigHelper configHelper) {
        this.configHelper = configHelper;
    }

    private Optional<Emote> getById(String id) {
        return Optional.ofNullable(configHelper.getConfig().emotes().get(id));
    }

    private List<Emote> getAll() {
        return configHelper.getConfig().emotes().values().stream().toList();
    }
}
