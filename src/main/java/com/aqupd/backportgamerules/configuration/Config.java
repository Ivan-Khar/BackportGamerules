package com.aqupd.backportgamerules.configuration;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class Config {

  private Config() {}
  public static final Config INSTANCE = new Config();

  Logger LOGGER = LogManager.getLogger(this.getClass());
  private final File confFile = new File("./config/AqMods/GameruleBackports.json");
  Gson gson = new GsonBuilder().setPrettyPrinting().create();

  private int SNOW_ACCUMULATION_HEIGHT = 1;
  private boolean SNOW_LAYERS_KILL_GRASS = true;

  public int getSnowAccumulationHeight() { return SNOW_ACCUMULATION_HEIGHT; }
  public boolean isSnowLayersKillGrass() { return SNOW_LAYERS_KILL_GRASS; }

  public void setSnowAccumulationHeight(int height) {
    SNOW_ACCUMULATION_HEIGHT = Math.min(Math.max(height, 0), 8);
    save();
  }

  public void setSnowLayersKillGrass(boolean kill) {
    SNOW_LAYERS_KILL_GRASS = kill;
    save();
  }

  public void load() {
    if (!confFile.exists() || confFile.length() == 0) save();
    try {
      JsonObject jo = gson.fromJson(new FileReader(confFile), JsonObject.class);
      JsonElement jE;

      if ((jE = jo.get("snow_accumulation_height")) != null) SNOW_ACCUMULATION_HEIGHT = jE.getAsInt();
      if ((jE = jo.get("snow_layers_kill_grass")) != null) SNOW_LAYERS_KILL_GRASS = jE.getAsBoolean();
      save();
    } catch (FileNotFoundException ex) {
      LOGGER.trace("Couldn't load configuration file", ex);
    }
  }

  public void save() {
    try {
      if (!confFile.exists()) { confFile.getParentFile().mkdirs(); confFile.createNewFile(); }
      if (confFile.exists()) {
        JsonObject jo = new JsonObject();
        jo.add("snow_accumulation_height", new JsonPrimitive(SNOW_ACCUMULATION_HEIGHT));
        jo.add("snow_layers_kill_grass", new JsonPrimitive(SNOW_LAYERS_KILL_GRASS));

        PrintWriter printwriter = new PrintWriter(new FileWriter(confFile));
        printwriter.print(gson.toJson(jo));
        printwriter.close();
      }
    } catch (IOException ex) {
      LOGGER.trace("Couldn't save configuration file", ex);
    }
  }
}
