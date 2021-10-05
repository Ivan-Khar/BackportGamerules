package com.aqupd.aqupdblank.mixins;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.aqupd.aqupdblank.utils.AqLogger.*;

@Mixin(TitleScreen.class)
public class BlankMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        logInfo("Yay mixin example!!");
    }
}
