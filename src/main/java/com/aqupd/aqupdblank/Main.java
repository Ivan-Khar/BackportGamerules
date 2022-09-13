package com.aqupd.aqupdblank;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main implements ModInitializer {

  public static Logger LOGGER = LoggerFactory.getLogger("Aquatic blank mod");

  @Override
  public void onInitialize() {
    LOGGER.info("helo");
  }
}
