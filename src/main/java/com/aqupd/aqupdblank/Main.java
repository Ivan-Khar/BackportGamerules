package com.aqupd.aqupdblank;

import net.fabricmc.api.ModInitializer;

import static com.aqupd.aqupdblank.utils.AqLogger.*;

public class Main implements ModInitializer {
    @Override
    public void onInitialize() {
        logInfo("Doors on Stairs initialized");
		logWarn("just warning lol");
		logError("YOOO ERROR!");
    }
}
