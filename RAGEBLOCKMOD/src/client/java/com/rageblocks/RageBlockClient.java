package com.rageblocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class RageBlockClient implements ClientModInitializer {

   @Override
   public void onInitializeClient() {
      HandledScreens.register(
         ModScreenHandlers.PURIFIER_SCREEN_HANDLER,
         PurifierScreen::new
      );
   }
}