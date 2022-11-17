package com.aqupd.backportgamerules.mixins.mobexplosiondropdecay;

import com.aqupd.backportgamerules.configuration.Config;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin {
  //mobExplosionDropDecay
  //changing default Explosion type
  @ModifyArg(method = "explode", index = 5, at = @At(
      target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;",
      value = "INVOKE", ordinal = 0))
  private Explosion.DestructionType changeDestructionType(Explosion.DestructionType destructionType) {
    if((boolean) Config.INSTANCE.getSetting("mobExplosionDropDecay").getValue()) return destructionType; //DESTROY||NONE
    return Explosion.DestructionType.BREAK; //BREAK
  }
}
