package me.synnk.features;

import me.synnk.api.features.Feature;
import me.synnk.api.features.FeatureType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static java.util.Arrays.asList;

public class MVPCorrector extends Feature {
    public MVPCorrector() {
        super("mvpcorrector", FeatureType.TOGGLE, asList("mvpc", "mvpgrammar", "mvpg"));
    }

    @Override
    public void onTrigger() {

    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        // empty
    }
}
