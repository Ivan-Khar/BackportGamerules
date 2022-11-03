package com.aqupd.aqupdblank.mixins;

import com.aqupd.aqupdblank.configuration.Config;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SpreadableBlock.class)
public class SpreadableBlockMixin {
  @Inject(method = "canSurvive", cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT, at = @At(
      target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", value = "INVOKE", ordinal = 0))
  private static void checkIfSnowLayersAboveGrass(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir, BlockPos blockPos, BlockState blockState) {
    boolean snowLayersKillGrass = Config.INSTANCE.isSnowLayersKillGrass();
    if(!snowLayersKillGrass && blockState.isOf(Blocks.SNOW)) {
      cir.setReturnValue(true);
    }
  }
}