package com.rageblocks;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;

public class RageBlockWorldGeneration {

   public static void generateRageBlocks() {
      BiomeModifications.addFeature(
         BiomeSelectors.foundInOverworld(),
         GenerationStep.Feature.UNDERGROUND_ORES,
         RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of("rageblock", "rage_ore")
         )
      );
   }
}