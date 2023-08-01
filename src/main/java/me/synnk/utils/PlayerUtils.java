package me.synnk.utils;

import net.minecraft.util.ChatComponentText;

import static me.synnk.MiningWare.mc;

public class PlayerUtils {
    public static void showMessage(String message) {
        message = message.replace("&", "§");

        mc.thePlayer.addChatMessage(new ChatComponentText(message));
    }
}
