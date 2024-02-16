package xyz.ketok.wilderness.fabric.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.ketok.wilderness.other.WdHooks;

@Mixin(BoneMealItem.class)
public class MixinBonemealItem {
    @Inject(method = "growCrop(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    private static void growCrop(ItemStack stack, Level level, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
        if (WdHooks.onBonemeal(level, pos)) {
            if (level instanceof ServerLevel) {
                stack.shrink(1);
            }

            info.setReturnValue(true);
            info.cancel();
        }
    }
}
