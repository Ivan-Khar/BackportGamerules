package com.aqupd.backportgamerules.command;

import com.aqupd.backportgamerules.configuration.Config;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.server.command.ServerCommandSource;

import java.util.concurrent.CompletableFuture;

public class GamerulesSuggestionProvider implements SuggestionProvider<ServerCommandSource> {
  @Override
  public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) throws CommandSyntaxException {
    for (String s: Config.INSTANCE.getGamerules().keySet()) {
      builder.suggest(s);
    }
    return builder.buildFuture();
  }
}
