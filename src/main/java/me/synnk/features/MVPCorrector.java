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
        Config.mvpCorrector = !Config.mvpCorrector;
        PlayerUtils.showMessage("&8[&bMiningWare&8] &6MVP Corrector " + (Config.mvpCorrector?"&aEnabled":"&cDisabledEE") + "&r");
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        // empty
    }
}
