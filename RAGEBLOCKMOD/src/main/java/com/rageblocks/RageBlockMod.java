package com.rageblocks;

import net.fabricmc.api.ModInitializer;

public class RageBlockMod implements ModInitializer {
   public static final String MOD_ID = "rageblock";

   public void onInitialize() {
      ModScreenHandlers.registerScreenHandlers();
      ModBlocks.registerModBlocks();
      ModItems.registerModItems();
      ModItemGroups.registerItemGroups();
      ModBlockEntities.registerBlockEntities();
      ModSounds.registerSounds();
      RageBlockWorldGeneration.generateRageBlocks();
      System.out.println("Rage Block Mod Loaded!");
   }
}
