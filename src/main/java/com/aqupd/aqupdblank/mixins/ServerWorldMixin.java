package com.aqupd.aqupdblank.mixins;

import com.aqupd.aqupdblank.configuration.Config;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.minecraft.block.Blocks.*;
import static net.minecraft.block.SnowBlock.*;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
  //Cancelling vanilla setBlockState()
  @Redirect(method = "tickChunk", at = @At(
          target = "Lnet/minecraft/world/biome/Biome;canSetSnow(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z",
          value = "INVOKE",
          ordinal = 0))
  private boolean cancelCanSetSnow(Biome instance, WorldView world, BlockPos pos) { return false; }

  @Inject(method = "tickChunk", locals = LocalCapture.CAPTURE_FAILSOFT, at = @At(
          target = "Lnet/minecraft/world/biome/Biome;canSetSnow(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z",
          value = "INVOKE",
          ordinal = 0,
          shift = At.Shift.BEFORE))
  private void snowAccumulationHeight(WorldChunk chunk, int randomTickSpeed, CallbackInfo ci, ChunkPos chunkPos, boolean bl, int i, int j, Profiler profiler, BlockPos blockPos, BlockPos blockPos2, Biome biome) {
    ServerWorld serverWorld = ((ServerWorld)(Object)this);
    int maxSnowLayers = Config.INSTANCE.getSnowAccumulationHeight();
    if (biome.canSetSnow(serverWorld, blockPos) && maxSnowLayers > 0) {
      BlockState blockState = serverWorld.getBlockState(blockPos);
      if (blockState.isOf(SNOW)) {
        int l = blockState.get(LAYERS);
        if (l < Math.min(maxSnowLayers, 8)) {
          serverWorld.setBlockState(blockPos, blockState.with(LAYERS, l + 1));
        }
      } else {
        serverWorld.setBlockState(blockPos, SNOW.getDefaultState());
      }
    }
  }
}
