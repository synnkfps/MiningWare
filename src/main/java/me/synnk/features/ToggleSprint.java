package me.synnk.features;

import me.synnk.api.features.Feature;
import me.synnk.api.features.FeatureType;
import me.synnk.config.Config;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.synnk.MiningWare.mc;

public class ToggleSprint extends Feature {
    public ToggleSprint() {
        super("Toggle Sprint","togglesprint", FeatureType.TOGGLE);
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (!Config.getBoolean(this.getCommandName())) return;
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
    private boolean nullCheck() {
        return mc.thePlayer == null || mc.theWorld == null;
    }

    private boolean logicCheck() {
        return !mc.thePlayer.isCollidedHorizontally && !mc.thePlayer.isSneaking() && mc.thePlayer.getFoodStats().getFoodLevel() > 6f;
    }

    @Override
    public void onTrigger() {

    }
}
