package com.aqupd.backportgamerules;

import com.aqupd.backportgamerules.configuration.Config;
import com.aqupd.backportgamerules.command.Commands;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class Main implements ModInitializer {

  @Override
  public void onInitialize() {
    Config.INSTANCE.load();
    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> Commands.INSTANCE.register(dispatcher, dedicated));
  }
}
