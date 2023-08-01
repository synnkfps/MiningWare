package me.synnk.features;

import me.synnk.handlers.IModule;
import me.synnk.handlers.Module;

@IModule(name = "Flashlight", description = "Idk")
public class Flashlight extends Module {
    public void onUpdate() {
        if (nullCheck()) return;

        try {
            if (keyCheck() && logicCheck()) {
                mc.thePlayer.setSprinting(true);
            }
        } catch (Exception ignored) {}
    }

    private boolean keyCheck() {
        return mc.gameSettings.keyBindForward.isKeyDown();
    }

    private boolean logicCheck() {
        return !mc.thePlayer.isCollidedHorizontally && !mc.thePlayer.isSneaking() && mc.thePlayer.getFoodStats().getFoodLevel() > 6f;
    }
}
