package com.aqupd.backportgamerules.mixins.fluidsourceconversion;

import com.aqupd.backportgamerules.configuration.Config;
import net.minecraft.fluid.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin {
  //lavaSourceConversion
  //making lava infinite source
  @Inject(method = "isInfinite", cancellable = true, at = @At(value = "HEAD"))
  private void test(CallbackInfoReturnable<Boolean> cir) {
    cir.setReturnValue((boolean) Config.INSTANCE.getSetting("lavaSourceConversion").getValue());
  }
}