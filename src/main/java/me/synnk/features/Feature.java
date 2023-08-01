package me.synnk.features;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.synnk.config.Config;
import me.synnk.utils.PlayerUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Feature {
    private String name = "";
    private List<String> aliases = Arrays.asList(name);

    public Feature(String name) {
        this.name = name;

        registerCommand();
    }

    public Feature(String name, List<String> aliases) {
        this.name = name;
        this.aliases = aliases;

        registerCommand();
    }

    private void registerCommand() {
        ClientCommandHandler.instance.registerCommand(new CommandFeature(this));
    }

    public String getName() {
        return this.name;
    }

    public abstract void onTrigger();

    // Custom command class to execute the feature
    private static class CommandFeature extends CommandBase {
        private String featureName;
        private List<String> featureAliases;

        private String commandName;

        public CommandFeature(Feature feature) {
            this.featureName = feature.name;
            this.featureAliases = feature.aliases;
            this.commandName = feature.name;
        }

        @Override
        public List<String> getCommandAliases() {
            return this.featureAliases;
        }

        @Override
        public String getCommandName() {
            return commandName;
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/" + commandName;
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) {
            Config.setSetting(this.featureName, !Config.getBoolean(this.featureName));
            PlayerUtils.showMessage("&8[&bMiningWare&8] &6"+ featureName + (Config.getBoolean(featureName)?" &aEnabled":" &cDisabled") + "&r");
        }

        @Override
        public boolean canCommandSenderUseCommand(ICommandSender var1) {
            return true;
        }

        @Override
        public int getRequiredPermissionLevel() {
            return 0;
        }
    }
}