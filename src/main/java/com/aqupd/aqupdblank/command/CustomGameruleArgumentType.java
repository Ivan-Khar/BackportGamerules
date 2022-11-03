package com.aqupd.aqupdblank.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class CustomGameruleArgumentType implements ArgumentType<String> {
  private static final Collection<String> EXAMPLES = Arrays.asList("snowLayerAccumulationHeight", "snowLayersKillGrass");
  private static final Collection<String> SCOREBOARDS = Arrays.asList("snowLayerAccumulationHeight", "snowLayersKillGrass");

  private CustomGameruleArgumentType() {
  }

  public static CustomGameruleArgumentType string() {
    return new CustomGameruleArgumentType();
  }

  public static String getScoreboard(final CommandContext<?> context, final String name) {
    return context.getArgument(name, String.class);
  }

  @Override
  public String parse(StringReader reader) throws CommandSyntaxException {
    return reader.getString();
  }

  @Override
  public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
    SCOREBOARDS.forEach(str -> { if (str.startsWith(builder.getRemainingLowerCase())) builder.suggest(str); });
    return builder.buildFuture();
  }

  @Override
  public Collection<String> getExamples() {
    return EXAMPLES;
  }
}
