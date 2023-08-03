package me.synnk.features;


import me.synnk.api.features.Feature;
import me.synnk.api.features.FeatureType;

public class Flashlight extends Feature {
    public Flashlight() {
        super("FlashLight","flashlight", FeatureType.TOGGLE);
    }

    @Override
    public void onTrigger() {

    }
}
