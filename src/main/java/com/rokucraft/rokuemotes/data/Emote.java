package com.rokucraft.rokuemotes.data;

import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.spongepowered.configurate.objectmapping.meta.NodeKey;

public record Emote(
        @NodeKey
        String key,
        Component name,
        String selfTemplate,
        String otherTemplate,
        Sound sound
) {
        public String permission() {
                return "emote." + key;
        }
}
