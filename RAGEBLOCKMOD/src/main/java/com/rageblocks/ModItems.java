package com.rageblocks;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.item.ArmorItem;

public class ModItems {

    public static final Item RAGE_BLOCK_ITEM = registerItem(
            "rage_block",
            new BlockItem(ModBlocks.RAGE_BLOCK, new Item.Settings())
    );

    public static final Item RAGE_ORE_ITEM = registerItem(
            "rage_ore",
            new BlockItem(ModBlocks.RAGE_ORE, new Item.Settings())
    );

    public static final Item UNPURIFIED_RAGE = registerItem(
            "unpurified_rage",
            new Item(new Item.Settings())
    );

    public static final Item PURIFIER_ITEM = registerItem(
            "purifier",
            new BlockItem(ModBlocks.PURIFIER, new Item.Settings())
    );

    public static final Item PURE_RAGE = registerItem(
            "pure_rage",
            new Item(new Item.Settings())
    );

    public static final Item RAGE_CASING_ITEM = registerItem(
            "rage_casing",
            new BlockItem(ModBlocks.RAGE_CASING, new Item.Settings())
    );
//where the music disc used to live:(
    public static final Item RAGE_HELMET = registerItem(
        "rage_helmet",
        new ArmorItem(RageArmorMaterial.RAGE, ArmorItem.Type.HELMET, new Item.Settings())
);

public static final Item RAGE_CHESTPLATE = registerItem(
        "rage_chestplate",
        new ArmorItem(RageArmorMaterial.RAGE, ArmorItem.Type.CHESTPLATE, new Item.Settings())
);

public static final Item RAGE_LEGGINGS = registerItem(
        "rage_leggings",
        new ArmorItem(RageArmorMaterial.RAGE, ArmorItem.Type.LEGGINGS, new Item.Settings())
);

public static final Item RAGE_BOOTS = registerItem(
        "rage_boots",
        new ArmorItem(RageArmorMaterial.RAGE, ArmorItem.Type.BOOTS, new Item.Settings())
);

    private static Item registerItem(String name, Item item) {
        return Registry.register(
                Registries.ITEM,
                new Identifier("rageblock", name),
                item
        );
    }

    public static void registerModItems() {
        System.out.println("it works YAY!");
    }
}