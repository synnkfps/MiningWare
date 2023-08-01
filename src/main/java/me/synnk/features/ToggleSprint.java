package me.synnk.features;

import me.synnk.MiningWare;
import me.synnk.config.Config;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.synnk.MiningWare.mc;

public class ToggleSprint extends Feature {
    public ToggleSprint() {
        super("togglesprint");
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (!Config.toggleSprint) return;

        if (!mc.thePlayer.isSprinting() && mc.thePlayer.ridingEntity==null && !mc.thePlayer.isCollidedHorizontally && !mc.thePlayer.isSneaking()) {
            mc.thePlayer.setSprinting(true);
        }
    }
}
