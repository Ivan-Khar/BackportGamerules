package com.aqupd.aqupdblank.mixins;

import net.minecraft.fluid.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterFluid.class)
public class WaterFluidMixin {
  @Inject(method = "isInfinite", cancellable = true, at = @At(value = "HEAD"))
  private void test(CallbackInfoReturnable<Boolean> cir) { cir.setReturnValue(false); }
}