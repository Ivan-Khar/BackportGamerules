package com.aqupd.backportgamerules.configuration;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;

@SuppressWarnings({"ResultOfMethodCallIgnored", "UnusedReturnValue", "SpellCheckingInspection"})
public class Config {

  private Config() {}
  public static final Config INSTANCE = new Config();

  Logger LOGGER = LogManager.getLogger(this.getClass());
  private final File confFile = new File("./config/AqMods/GameruleBackport.json");
  Gson gson = new GsonBuilder().setPrettyPrinting().create();

  private Field<Integer> SNOW_ACCUMULATION_HEIGHT = new Field<>(1);
  private Field<Boolean> SNOW_LAYERS_KILL_GRASS = new Field<>(true);
  private Field<Boolean> BLOCK_EXPLOSION_DROP_DECAY = new Field<>(true);
  private Field<Boolean> MOB_EXPLOSION_DROP_DECAY = new Field<>(true);
  private Field<Boolean> TNT_EXPLOSION_DROP_DECAY = new Field<>(false);
  private Field<Boolean> WATER_SOURCE_CONVERSION = new Field<>(true);
  private Field<Boolean> LAVA_SOURCE_CONVERSION = new Field<>(false);
  private Field<Boolean> GLOBAL_SOUND_EVENTS = new Field<>(true);

  private final HashMap<String, Field> gamerules = new HashMap<>() {{
    put("snowAccumulationHeight", SNOW_ACCUMULATION_HEIGHT);
    put("snowLayersKillGrass", SNOW_LAYERS_KILL_GRASS);
    put("blockExplosionDropDecay", BLOCK_EXPLOSION_DROP_DECAY);
    put("mobExplosionDropDecay", MOB_EXPLOSION_DROP_DECAY);
    put("tntExplosionDropDecay", TNT_EXPLOSION_DROP_DECAY);
    put("waterSourceConversion", WATER_SOURCE_CONVERSION);
    put("lavaSourceConversion", LAVA_SOURCE_CONVERSION);
    put("globalSoundEvents", GLOBAL_SOUND_EVENTS);
  }};

  public Field<?> getSetting(String gamerule) {
    if(gamerules.containsKey(gamerule)) return gamerules.get(gamerule);
    return null;
  }
  public boolean setSetting(String gamerule, Object value) {
    if(gamerules.containsKey(gamerule)) {
      gamerules.get(gamerule).setValue(value);
      save();
      return true;
    }
    return false;
  }
  public HashMap<String, Field> getGamerules() {
    return gamerules;
  }

  public void load() {
    if (!confFile.exists() || confFile.length() == 0) save();
    try {
      JsonObject jo = gson.fromJson(new FileReader(confFile), JsonObject.class);
      gamerules.keySet().forEach(key -> {
        JsonElement jE = jo.get(key);
        if (jE != null) {
          JsonPrimitive value = jE.getAsJsonPrimitive();
          Field field = gamerules.get(key);
          if(value.isBoolean()) field.setValue(jE.getAsBoolean());
          if(value.isString()) field.setValue(jE.getAsString());
          if(value.isNumber()) field.setValue(jE.getAsInt());
        }
      });
      save();
    } catch (FileNotFoundException ex) {
      LOGGER.trace("Couldn't load configuration file", ex);
    }
  }

  public void save() {
    try {
      if (!confFile.exists()) { confFile.getParentFile().mkdirs(); confFile.createNewFile(); }
      JsonObject jo = new JsonObject();
      gamerules.keySet().forEach(key -> {
        Object value = gamerules.get(key).getValue();
        if(value instanceof Boolean) jo.add(key, new JsonPrimitive((boolean) value));
        else if(value instanceof String) jo.add(key, new JsonPrimitive((String) value));
        else if(value instanceof Number) jo.add(key, new JsonPrimitive((Number) value));
        else jo.add(key, null);
      });

      PrintWriter printwriter = new PrintWriter(new FileWriter(confFile));
      printwriter.print(gson.toJson(jo));
      printwriter.close();
    } catch (IOException ex) {
      LOGGER.trace("Couldn't save configuration file", ex);
    }
  }
}
