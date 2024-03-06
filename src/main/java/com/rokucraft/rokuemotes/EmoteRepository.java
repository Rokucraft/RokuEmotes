package com.rokucraft.rokuemotes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmoteRepository {
    private final Map<String, Emote> emotes;

    public EmoteRepository(Map<String, Emote> emotes) {
        this.emotes = emotes;
    }

    private Optional<Emote> getById(String id) {
        return Optional.ofNullable(emotes.get(id));
    }

    private List<Emote> getAll() {
        return emotes.values().stream().toList();
    }
}
