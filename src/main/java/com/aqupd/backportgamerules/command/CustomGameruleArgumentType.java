package com.aqupd.backportgamerules.command;

import com.aqupd.backportgamerules.configuration.Config;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class CustomGameruleArgumentType implements ArgumentType<String> {
  private static final Collection<String> EXAMPLES = Arrays.asList("snowLayerAccumulationHeight", "snowLayersKillGrass");

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
    //Config.INSTANCE.getGamerules().keySet().forEach();
    builder.suggest("asadfasdf");
    return builder.buildFuture();
  }

  @Override
  public Collection<String> getExamples() {
    return EXAMPLES;
  }
}
