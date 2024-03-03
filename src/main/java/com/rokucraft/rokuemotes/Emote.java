package com.rokucraft.rokuemotes;

import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;

public record Emote(
        String key,
        String permission,
        Component name,
        String selfTemplate,
        String otherTemplate,
        Sound sound
) {
}
