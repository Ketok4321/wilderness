package xyz.ketok.wilderness.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.ketok.wilderness.other.WdTags;
import xyz.ketok.wilderness.registry.WdSoundEvents;

@Mixin(AxeItem.class)
public class MixinAxeItem {
    @Redirect(
            method = "useOn(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V")
    )
    private void mixin(Level level, @Nullable Player player, BlockPos pos, SoundEvent sound, SoundSource source, float volume, float pitch) {
        if (level.getBlockState(pos).is(WdTags.Blocks.OVERGROWN_LOGS)) {
            level.playSound(player, pos, WdSoundEvents.UNMOSS.get(), source, volume, pitch);
        }
        else {
            level.playSound(player, pos, sound, source, volume, pitch);
        }
    }
}
