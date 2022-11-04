package com.aqupd.backportgamerules.mixins.mobexplosiondropdecay;

import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FireballEntity.class)
public class FireballEntityMixin {
  //mobExplosionDropDecay
  //changing default Explosion type to BREAK
  @ModifyArg(method = "onCollision", index = 6, at = @At(
      target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFZLnet/minecraft/world/explosion/Explosion$DestructionType;)Lnet/minecraft/world/explosion/Explosion;",
      value = "INVOKE", ordinal = 0))
  private Explosion.DestructionType changeDestructionType(Explosion.DestructionType destructionType) {
    if(true) return Explosion.DestructionType.BREAK;
    return destructionType;
  }
}
