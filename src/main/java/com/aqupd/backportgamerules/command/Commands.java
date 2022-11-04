package com.aqupd.backportgamerules.command;

import com.aqupd.backportgamerules.configuration.Config;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Commands {

  private Commands() {}
  public static Commands INSTANCE = new Commands();
  Logger LOGGER = LogManager.getLogger(this.getClass());
  private Config cfg = Config.INSTANCE;

  public void register(CommandDispatcher<ServerCommandSource> dis, CommandRegistryAccess reg, CommandManager.RegistrationEnvironment env) {
    dis.register(CommandManager.literal("backportgamerules").requires(source -> source.hasPermissionLevel(2))
        .then(CommandManager.literal("list")
            .executes(this::getGameruleList))
        .then(CommandManager.literal("set")
            .then(CommandManager.argument("gamerule", CustomGameruleArgumentType.string())
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
    //cfg.setSetting("lavaSourceConversion", !bool);
    return 1;
  }

  private int modVersion(CommandContext<?> ctx) {
    return 1;
  }

  private int sendHelp(CommandContext<?> ctx) {
    return 1;
  }
}
