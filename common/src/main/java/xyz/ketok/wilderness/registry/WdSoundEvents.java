package xyz.ketok.wilderness.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import xyz.ketok.wilderness.Wilderness;

public class WdSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Wilderness.MOD_ID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> UNMOSS = sound("item.axe.unmoss");

    private static RegistrySupplier<SoundEvent> sound(String id) {
        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Wilderness.MOD_ID, id)));
    }
}
