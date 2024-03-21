package com.rokucraft.rokuemotes.command.caption;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.caption.CaptionProvider;
import org.incendo.cloud.caption.DelegatingCaptionProvider;

public class DefaultCaptionsProvider<C> extends DelegatingCaptionProvider<C> {

    public static final String ARGUMENT_PARSE_FAILURE_EMOTE = "'<input>' is not a valid emote";

    private static final CaptionProvider<?> PROVIDER = CaptionProvider.constantProvider()
            .putCaption(CaptionKeys.ARGUMENT_PARSE_FAILURE_EMOTE, ARGUMENT_PARSE_FAILURE_EMOTE)
            .build();
    @Override
    public @NonNull CaptionProvider<C> delegate() {
        return (CaptionProvider<C>) PROVIDER;
    }
}
