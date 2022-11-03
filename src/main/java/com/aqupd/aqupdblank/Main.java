package com.aqupd.aqupdblank;

import com.aqupd.aqupdblank.init.Gamerules;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main implements ModInitializer {

  public static Logger LOGGER = LoggerFactory.getLogger("Aquatic blank mod");

  @Override
  public void onInitialize() {
    Gamerules.INSTANCE.init();
    LOGGER.info("helo");
  }

}
