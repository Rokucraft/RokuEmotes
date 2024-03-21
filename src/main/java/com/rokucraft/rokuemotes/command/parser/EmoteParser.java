package com.rokucraft.rokuemotes.command.parser;

import com.rokucraft.rokuemotes.command.CommandKeys;
import com.rokucraft.rokuemotes.command.caption.CaptionKeys;
import com.rokucraft.rokuemotes.data.Emote;
import com.rokucraft.rokuemotes.data.EmoteRepository;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.bukkit.BukkitCommandContextKeys;
import org.incendo.cloud.caption.CaptionVariable;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.context.CommandInput;
import org.incendo.cloud.exception.parsing.ParserException;
import org.incendo.cloud.parser.ArgumentParseResult;
import org.incendo.cloud.parser.ArgumentParser;
import org.incendo.cloud.parser.ParserDescriptor;
import org.incendo.cloud.suggestion.BlockingSuggestionProvider;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class EmoteParser<C> implements ArgumentParser<C, Emote>, BlockingSuggestionProvider.Strings<C> {

    public static <C>ParserDescriptor<C, Emote> emoteParser() {
        return ParserDescriptor.of(new EmoteParser<>(), Emote.class);
    }

    @Override
    public ArgumentParseResult<Emote> parse(CommandContext<C> commandContext, CommandInput commandInput) {
        String input = commandInput.readString();
        EmoteRepository emoteRepository = commandContext.get(CommandKeys.EMOTE_REPOSITORY);
        Emote emote = emoteRepository.getById(input);
        if (emote == null) {
            return ArgumentParseResult.failure(new EmoteParseException(input, commandContext));
        }
        return ArgumentParseResult.success(emote);
    }

    @Override
    public Iterable<String> stringSuggestions(CommandContext<C> commandContext, CommandInput commandInput) {
        final CommandSender sender = commandContext.get(BukkitCommandContextKeys.BUKKIT_COMMAND_SENDER);
        EmoteRepository emoteRepository = commandContext.get(CommandKeys.EMOTE_REPOSITORY);
        return emoteRepository.getAll()
                .stream()
                .filter(e -> sender.hasPermission(e.permission()))
                .map(Emote::key)
                .toList();
    }

    public static final class EmoteParseException extends ParserException {

        public EmoteParseException(
                String input,
                CommandContext<?> context
        ) {
            super(
                    EmoteParser.class,
                    context,
                    CaptionKeys.ARGUMENT_PARSE_FAILURE_EMOTE,
                    CaptionVariable.of("input", input)
            );
        }
    }
}
