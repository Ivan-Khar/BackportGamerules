package com.aqupd.backportgamerules;

import com.aqupd.backportgamerules.command.CustomGameruleArgumentType;
import com.aqupd.backportgamerules.configuration.Config;
import com.aqupd.backportgamerules.command.Commands;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main implements ModInitializer {

  public static Logger LOGGER = LoggerFactory.getLogger("Aquatic blank mod");

  @Override
  public void onInitialize() {
    Config.INSTANCE.load();
    CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> Commands.INSTANCE.register(dispatcher, registryAccess, environment));
    ArgumentTypeRegistry.registerArgumentType(new Identifier("backportgamerules", "gamerule"), CustomGameruleArgumentType.class, ConstantArgumentSerializer.of(CustomGameruleArgumentType::string));
  }

}
