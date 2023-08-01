package me.synnk.features;

import me.synnk.config.Config;
import me.synnk.utils.PlayerUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static java.util.Arrays.asList;

public class MVPCorrector extends Feature {
    public MVPCorrector() {
        super("mvpcorrector", asList("mvpc", "mvpgrammar", "mvpg"));
    }

    @Override
    public void onTrigger() {

    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        // empty
    }
}
