package me.synnk.handlers;

import me.synnk.config.Config;
import me.synnk.event.events.PacketSentEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.commons.lang3.StringUtils;

public class ChatEventHandler {

    private boolean acceptSuffix = false;
    private boolean modifyingMessage = false;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (!Config.mvpCorrector) return;
        if (event.phase == TickEvent.Phase.END && acceptSuffix) {
            acceptSuffix = false;
        }
    }

    @SubscribeEvent
    public void onSendPacket(PacketSentEvent event) {
        if (!Config.mvpCorrector) return;

        Packet<?> packet = event.packet;

        if (packet instanceof C01PacketChatMessage) {
            C01PacketChatMessage chatPacket = (C01PacketChatMessage) packet;
            String message = chatPacket.getMessage();

            //System.out.println("Message intercepted: " + message);
            //System.out.println("Modifying the message.");
            // Check if the message is currently being modified
            if (modifyingMessage) {
                // If yes, skip intercepting and modifying
                return;
            }

            // Modify the message
            message = modifyMessage(message);
            //System.out.println("New modified message: " +message);
            // Send the modified message
            if (!message.equals(chatPacket.getMessage())) {
                event.setCanceled(true);
                acceptSuffix = true;

                // Set the flag to true to avoid re-intercepting the modified message
                modifyingMessage = true;

                // Send the modified message
                //System.out.println("Set event.setCanceled, acceptSuffix and modifyingMessage to true");
                //System.out.println("Sending message to the chat: " + message);
                Minecraft.getMinecraft().thePlayer.sendChatMessage(message);

                // Reset the flag after sending the modified message
                modifyingMessage = false;
                //System.out.println("Set modifyingMessage to false");
            }
        }
    }
    public static String modifyMessage(String msg) {
        String message = msg;

        if (message.startsWith("/") || message.startsWith("\\") || message.startsWith("!") || message.startsWith(":") ||
                message.startsWith(";") || message.startsWith(".") || message.startsWith(",") || message.startsWith("@") ||
                message.startsWith("&") || message.startsWith("*") || message.startsWith("$") || message.startsWith("#") ||
                message.startsWith("(") || message.startsWith(")")) {
            return message;
        }

        message = StringUtils.capitalize(message);
        String correctedMessage = message;

        // Make sure "i" is in uppercase (as in "I")
        correctedMessage = correct(correctedMessage, "i", "I");

        // Add a period at the end of the message if it doesn't have one
        if (!correctedMessage.endsWith(".") && !correctedMessage.endsWith("?") && !correctedMessage.endsWith("!") && !correctedMessage.endsWith("-")) {
            correctedMessage += ".";
        }

        // Add space between the abbreviation and the period
        correctedMessage = correct(correctedMessage, "you are", "you're");
        correctedMessage = correct(correctedMessage, "youre", "you're");
        correctedMessage = correct(correctedMessage, "u're", "you're");
        correctedMessage = correct(correctedMessage, "ur", "your");
        correctedMessage = correct(correctedMessage, "i am", "I'm");
        correctedMessage = correct(correctedMessage, "we are", "we're");
        correctedMessage = correct(correctedMessage, "they are", "they're");
        correctedMessage = correct(correctedMessage, "cannot", "can't");
        correctedMessage = correct(correctedMessage, "do not", "don't");
        correctedMessage = correct(correctedMessage, "does not", "doesn't");
        correctedMessage = correct(correctedMessage, "will not", "won't");
        correctedMessage = correct(correctedMessage, "should not", "shouldn't");
        correctedMessage = correct(correctedMessage, "has not", "hasn't");
        correctedMessage = correct(correctedMessage, "have not", "haven't");
        correctedMessage = correct(correctedMessage, "is not", "isn't");
        correctedMessage = correct(correctedMessage, "are not", "aren't");
        correctedMessage = correct(correctedMessage, "would not", "wouldn't");
        correctedMessage = correct(correctedMessage, "could not", "couldn't");
        correctedMessage = correct(correctedMessage, "should have", "should've");
        correctedMessage = correct(correctedMessage, "could have", "could've");
        correctedMessage = correct(correctedMessage, "would have", "would've");
        correctedMessage = correct(correctedMessage, "must not", "mustn't");
        correctedMessage = correct(correctedMessage, "might not", "mightn't");
        correctedMessage = correct(correctedMessage, "need not", "needn't");
        correctedMessage = correct(correctedMessage, "had not", "hadn't");
        correctedMessage = correct(correctedMessage, "was not", "wasn't");
        correctedMessage = correct(correctedMessage, "were not", "weren't");
        correctedMessage = correct(correctedMessage, "dare not", "daren't");
        correctedMessage = correct(correctedMessage, "is not", "ain't");
        correctedMessage = correct(correctedMessage, "dont", "don't");
        correctedMessage = correct(correctedMessage, "ive", "I've");
        correctedMessage = correct(correctedMessage, "youve", "you've");
        correctedMessage = correct(correctedMessage, "id", "I'd");
        correctedMessage = correct(correctedMessage, "youd", "you'd");
        correctedMessage = correct(correctedMessage, "mustve", "must've");
        correctedMessage = correct(correctedMessage, "its", "it's");
        correctedMessage = correct(correctedMessage, "tho", "though");

        correctedMessage = StringUtils.capitalize(correctedMessage);

        return correctedMessage;
    }

    public static String correct(String original, String toFind, String replacement) {
        return original.replaceAll("\\b" + toFind + "\\b", replacement);
    }

}