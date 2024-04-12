package com.rokucraft.rokuemotes.data;

import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.spongepowered.configurate.objectmapping.meta.NodeKey;

import static net.kyori.adventure.text.Component.text;

@NullMarked
public record Emote(
        @NodeKey String key,
        @Nullable Component name,
        String selfTemplate,
        String otherTemplate,
        Sound sound
) {
    public String permission() {
        return "emote." + key;
    }

    public Component name() {
        return name != null ? name : text(key);
    }
}
