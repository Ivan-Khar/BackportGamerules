package com.aqupd.aqupdblank.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class Gamerules {

  public Gamerules() {}
  public static Gamerules INSTANCE = new Gamerules();

  public static final GameRules.Key<GameRules.IntRule> SNOW_ACCUMULATION_HEIGHT =
      GameRuleRegistry.register("snowAccumulationHeight", GameRules.Category.MISC, GameRuleFactory.createIntRule(1));

  public void init() {}
}
