package com.aqupd.aqupdblank.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AqLogger {
    private static final Logger LOGGER = LogManager.getLogger();
    private static String logprefix = "[AqUpd's blank mod] ";

    public static void logError(String error) {
        LOGGER.error(logprefix + error);
    }

    public static void logWarn(String warn) {
        LOGGER.warn(logprefix + warn);
    }

    public static void logInfo(String info) {
        LOGGER.info(logprefix + info);
    }

}