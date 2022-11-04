package com.aqupd.backportgamerules.command;

import com.aqupd.backportgamerules.configuration.Config;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Commands {

  private Commands() {}
  public static Commands INSTANCE = new Commands();

  private Config cfg = Config.INSTANCE;

  public void register(CommandDispatcher<ServerCommandSource> dis, CommandRegistryAccess reg, CommandManager.RegistrationEnvironment env) {
    LiteralCommandNode<ServerCommandSource> BGCommand = dis.register(CommandManager.literal("backportgamerule").requires(source -> source.hasPermissionLevel(2))
        .then(CommandManager.literal("list")
            .executes(ctx -> getGameruleList(ctx.getSource())))
        .then(CommandManager.literal("set")
            .then(CommandManager.argument("gamerule", StringArgumentType.word())
                .suggests(new GamerulesSuggestionProvider())
                .then(CommandManager.argument("value", StringArgumentType.word())
                    .executes(ctx -> setGamerule(ctx.getSource(), StringArgumentType.getString(ctx, "gamerule"), StringArgumentType.getString(ctx, "value"))))))
        .then(CommandManager.literal("version")
            .executes(ctx -> modVersion(ctx.getSource())))
        .executes(ctx -> modVersion(ctx.getSource())));

    dis.register(CommandManager.literal("bpgamerule").redirect(BGCommand));
  }

  private int getGameruleList(ServerCommandSource source) {
    StringBuilder builder = new StringBuilder();
    Config.INSTANCE.getGamerules().forEach((a, b) -> builder.append(String.format("%s: %s \n", a, b.getValue())));
    source.sendFeedback(Text.literal("Gamerule list: \n").formatted(Formatting.BOLD, Formatting.GRAY), false);
    source.sendFeedback(Text.of(builder.toString()), false);
    return 1;
  }

  private int setGamerule(ServerCommandSource source, String gamerule, String value) {
    if(cfg.getSetting(gamerule) == null) {
      source.sendFeedback(Text.literal("Unknown rule: " + gamerule).formatted(Formatting.RED), false);
      return -1;
    }

    if (cfg.getSetting(gamerule).getValue() instanceof String) { cfg.setSetting(gamerule, value); }
    if (cfg.getSetting(gamerule).getValue() instanceof Boolean) { cfg.setSetting(gamerule, Boolean.valueOf(value)); }
    try {
      if (cfg.getSetting(gamerule).getValue() instanceof Number) { cfg.setSetting(gamerule, Integer.valueOf(value)); }
    } catch (NumberFormatException ex) {
      source.sendFeedback(Text.literal("Value " + value + " is not an integer").formatted(Formatting.RED), false);
      return -1;
    }

    source.sendFeedback(Text.translatable("commands.gamerule.set", gamerule, cfg.getSetting(gamerule).getValue()), false);
    return 1;
  }

  private int modVersion(ServerCommandSource source) {
    source.sendFeedback(Text.literal("""
        
        [Backport Gamerules 1.0]
        Backporting 1.20 gamerules to the older versions.
        """).formatted(Formatting.GOLD), false);
    return 1;
  }
}
