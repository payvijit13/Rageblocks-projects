package com.rageblocks;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemGroup;

public class ModItemGroups {

    public static final ItemGroup RAGEBLOCK_GROUP = Registry.register(
        Registries.ITEM_GROUP,
        Identifier.of("rageblock", "rageblock_group"),
        FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.RAGE_ORE))
            .displayName(Text.translatable("itemGroup.rageblock"))
            .entries((context, entries) -> {
                entries.add(ModItems.RAGE_BLOCK_ITEM);
                entries.add(ModItems.RAGE_ORE_ITEM);
                entries.add(ModItems.UNPURIFIED_RAGE);
                entries.add(ModItems.PURIFIER_ITEM);
                entries.add(ModItems.PURE_RAGE);
                entries.add(ModItems.RAGE_CASING_ITEM);
                //music disc used to be here:(((((
                entries.add(ModItems.RAGE_HELMET);
                entries.add(ModItems.RAGE_CHESTPLATE);
                entries.add(ModItems.RAGE_LEGGINGS);
                entries.add(ModItems.RAGE_BOOTS);
                
            })
            .build()
    );

    public static void registerItemGroups() {
        System.out.println("Registering RageBlock Item Group!");
    }
}