package com.aqupd.aqupdblank.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class Commands {

  private Commands() {}
  public static Commands INSTANCE = new Commands();

  public void register(CommandDispatcher<ServerCommandSource> dis, CommandRegistryAccess reg, CommandManager.RegistrationEnvironment env) {
    dis.register(CommandManager.literal("backportgamerules").requires(source -> source.hasPermissionLevel(2))
        .then(CommandManager.literal("list")
            .executes(this::getGameruleList))
        .then(CommandManager.literal("set")
            .then(CommandManager.argument("gamerule", StringArgumentType.word())
                .then(CommandManager.argument("value", StringArgumentType.word())
                    .executes(this::setGamerule))))
        .then(CommandManager.literal("version")
            .executes(this::modVersion))
        .then(CommandManager.literal("help")
            .executes(this::sendHelp))
        .executes(this::modVersion));
  }

  private int getGameruleList(CommandContext<?> ctx) {
    return 1;
  }

  private int setGamerule(CommandContext<?> ctx) {
    return 1;
  }

  private int modVersion(CommandContext<?> ctx) {
    return 1;
  }

  private int sendHelp(CommandContext<?> ctx) {
    return 1;
  }
}
