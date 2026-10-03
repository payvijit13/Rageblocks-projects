package com.rageblocks;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

   public static BlockEntityType<PurifierBlockEntity> PURIFIER;

   public static void registerBlockEntities() {
      PURIFIER = Registry.register(
         Registries.BLOCK_ENTITY_TYPE,
         Identifier.of("rageblock", "purifier"),
         BlockEntityType.Builder.create(
            PurifierBlockEntity::new,
            new Block[]{ModBlocks.PURIFIER}
         ).build(null)
      );

      System.out.println("Registering Block Entities!");
   }
}