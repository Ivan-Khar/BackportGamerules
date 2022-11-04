package com.aqupd.backportgamerules.mixins.mobExplosionDropDecay;

import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {
  //mobExplosionDropDecay
  //changing default Explosion type to BREAK
  @ModifyArg(method = "explode", index = 5, at = @At(
      target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;",
      value = "INVOKE", ordinal = 0))
  private Explosion.DestructionType changeDestructionType(Explosion.DestructionType destructionType) {
    if(true) return Explosion.DestructionType.BREAK;
    return destructionType;
  }
}
