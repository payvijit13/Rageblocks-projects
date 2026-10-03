package com.rageblocks;

import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block RAGE_BLOCK = registerBlock(
            "rage_block",
            new Block(AbstractBlock.Settings.create()
                    .copy(Blocks.STONE)
                    .strength(5.0F, 6.0F)
                    .requiresTool())
    );

    public static final Block RAGE_ORE = registerBlock(
            "rage_ore",
            new Block(AbstractBlock.Settings.create()
                    .copy(Blocks.IRON_ORE))
    );

    public static final Block PURIFIER = registerBlock(
            "purifier",
            new PurifierBlock(AbstractBlock.Settings.create()
                    .copy(Blocks.IRON_ORE))
    );
    
    public static final Block RAGE_CASING = registerBlock(
            "rage_casing",
            new RageCasingBlock(AbstractBlock.Settings.create()
                    .copy(Blocks.DARK_OAK_PLANKS))
    );

    private static Block registerBlock(String name, Block block) {
        return Registry.register(
                Registries.BLOCK,
                Identifier.of("rageblock", name),
                block
        );
    }

    public static void registerModBlocks() {
        System.out.println("YO I THINK ITS WORKING YEEEE");
    }
}