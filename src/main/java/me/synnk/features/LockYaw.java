package me.synnk.features;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Collections;

import static me.synnk.MiningWare.mc;

public class LockYaw extends Feature {

    public LockYaw() {
        super("lockyaw", Collections.singletonList("lockyaw"));
    }

    @Override
    public void onTrigger() {
        if (mc.thePlayer != null) {
            EntityPlayer player = mc.thePlayer;
            float yaw = player.rotationYaw;
            float newYaw = Math.round(yaw / 90.0F) * 90.0F;

            // Normalize the yaw to be within the range -180 to 180 degrees
            while (newYaw <= -180.0F) {
                newYaw += 360.0F;
            }
            while (newYaw > 180.0F) {
                newYaw -= 360.0F;
            }

            player.rotationYaw = newYaw;
            // Send a chat message to notify the player
            // mc.thePlayer.addChatMessage(new ChatComponentText("Your yaw has been locked to " + newYaw));
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        // empty
    }
}
