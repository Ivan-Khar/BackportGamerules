package com.aqupd.backportgamerules.mixins;

import net.minecraft.fluid.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LavaFluid.class)
public class LavaFluidMixin {
  @Inject(method = "isInfinite", cancellable = true, at = @At(value = "HEAD"))
  private void test(CallbackInfoReturnable<Boolean> cir) { cir.setReturnValue(true); }
}