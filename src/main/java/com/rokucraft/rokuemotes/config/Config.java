package com.rokucraft.rokuemotes.config;

import com.rokucraft.rokuemotes.Emote;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.HashMap;
import java.util.Map;

@ConfigSerializable
public record Config(
        Map<String, Emote> emotes
) {
    public static final Config DEFAULT = new Config(new HashMap<>());
}
