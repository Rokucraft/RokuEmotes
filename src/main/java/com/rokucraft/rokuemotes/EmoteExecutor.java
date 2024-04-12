package com.rokucraft.rokuemotes;

import com.rokucraft.rokuemotes.data.Emote;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import javax.inject.Inject;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

@NullMarked
public class EmoteExecutor {

    @Inject
    public EmoteExecutor() {
    }

    public void perform(Emote emote, Player self, @Nullable Player other) {
        sendMessages(emote, self, other);
        playSounds(emote, self, other);
    }

    public void perform(Emote emote, Player self) {
        perform(emote, self, null);
    }

    private void sendMessages(Emote emote, Player self, @Nullable Player other) {
        Component message;
        if (other == null) {
            message = miniMessage().deserialize(emote.selfTemplate());
        } else {
            message = miniMessage().deserialize(
                    emote.otherTemplate(),
                    Placeholder.component("self", self.displayName()),
                    Placeholder.component("other", other.displayName())
            );
        }

        Bukkit.broadcast(message);
    }

    private void playSounds(Emote emote, Player self, @Nullable Player other) {
        self.playSound(emote.sound());
        if (other != null) {
            other.playSound(emote.sound());
        }
    }
}
