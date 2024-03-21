package com.rokucraft.rokuemotes.command;

import com.rokucraft.rokuemotes.data.EmoteRepository;
import org.incendo.cloud.key.CloudKey;

import static org.incendo.cloud.key.CloudKey.cloudKey;

public class CommandKeys {
    public static final CloudKey<EmoteRepository> EMOTE_REPOSITORY =
            cloudKey("emote-repository", EmoteRepository.class);

    private CommandKeys() {}
}
