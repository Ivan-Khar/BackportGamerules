package com.aqupd.aqupdblank.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Biome.class)
public class BiomeMixin {
  @Redirect(method = "canSetSnow", at = @At(
      target = "Lnet/minecraft/block/BlockState;isAir()Z",
      value = "INVOKE"))
  private boolean changeIsAir(BlockState instance) {
    return instance.isAir() || instance.isOf(Blocks.SNOW);
  }
}
