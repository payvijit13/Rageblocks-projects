package com.rageblocks;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    public static final ScreenHandlerType<PurifierScreenHandler> PURIFIER_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(
                    Identifier.of("rageblock", "purifier"),
                    PurifierScreenHandler::new
            );

    public static void registerScreenHandlers() {
        System.out.println("Bro it's registering the screen!");
    }
}