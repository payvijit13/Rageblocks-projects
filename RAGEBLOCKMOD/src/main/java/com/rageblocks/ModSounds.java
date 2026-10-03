package com.rageblocks;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent PURIFIER_COMPLETE =
            registerSound("purifier_complete");

    // RIP RageBlocks theme 2026 - 2026 💀

    private static SoundEvent registerSound(String name) {
        Identifier id = new Identifier("rageblock", name);

        return Registry.register(
                Registries.SOUND_EVENT,
                id,
                SoundEvent.of(id)
        );
    }

    public static void registerSounds() {
        System.out.println("Registering RAGEBLOCKS sounds!");
    }
}