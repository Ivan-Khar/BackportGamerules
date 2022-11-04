package com.aqupd.backportgamerules.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Biome.class)
public class BiomeMixin {
  //snowAccumulationHeight
  //making it so snow can be placed in snow when raining
  @Redirect(method = "canSetSnow", at = @At(
      target = "Lnet/minecraft/block/BlockState;isAir()Z",
      value = "INVOKE"))
  private boolean changeIsAir(BlockState instance) {
    return instance.isAir() || instance.isOf(Blocks.SNOW);
  }
}
