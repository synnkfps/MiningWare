package me.synnk.config;

import me.synnk.MiningWare;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Config {
    private static final HashMap<String, Object> settings = new HashMap<>();
    public static final List<String> settingNames = new ArrayList<>();

    public static void addSetting(String settingName, Object settingValue) {
        settings.put(settingName, settingValue);
        settingNames.add(settingName);
    }


    public static Object getSetting(String settingName) {
        return settings.getOrDefault(settingName, null);
    }

    public static Integer getLength() {
        int tmp = 0;

        for (String i: settingNames) {
            tmp++;
        }

        return tmp;
    }

    public static boolean getBoolean(String settingName) {
        return (boolean) settings.getOrDefault(settingName, false);
    }

    public static void setSetting(String settingName, Object newValue) {
        if (!(newValue == getSetting(settingName))) {
            settings.put(settingName, newValue);
        }
    }
}
