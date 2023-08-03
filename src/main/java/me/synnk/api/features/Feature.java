package me.synnk.api.features;

import me.synnk.api.features.FeatureType;
import me.synnk.config.Config;
import me.synnk.utils.PlayerUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;

import java.util.Arrays;
import java.util.List;

public abstract class Feature {
    private String commandName;
    private final FeatureType type;
    private final String displayName;
    private List<String> aliases;

    public Feature(String displayName, String name, FeatureType type) {
        this(displayName, name, type, Arrays.asList(name));
    }

    public Feature(String displayName, String name, FeatureType type, List<String> aliases) {
        this.displayName = displayName;
        this.commandName = name;
        this.type = type;
        this.aliases = aliases;

        registerCommand();
    }

    private void registerCommand() {
        ClientCommandHandler.instance.registerCommand(new CommandFeature(this));
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public List<String> getAliases() {
        return this.aliases;
    }

    // Override this method in subclasses to define the feature behavior
    public void onTrigger() {
    }

    // Custom command class to execute the feature
    private static class CommandFeature extends CommandBase {
        private final Feature feature;

        public CommandFeature(Feature feature) {
            this.feature = feature;
        }

        @Override
        public List<String> getCommandAliases() {
            return feature.getAliases();
        }

        @Override
        public String getCommandName() {
            return feature.getCommandName();
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/" + feature.getCommandName();
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) {
            if (feature.type == FeatureType.TRIGGER) {
                feature.onTrigger();
                PlayerUtils.showMessage("&8[&bMiningWare&8] &6" + feature.getDisplayName() + "&aTriggered" + "&r");
            } else {
                Config.setSetting(feature.getCommandName(), !Config.getBoolean(feature.getCommandName()), feature.getDisplayName());
                PlayerUtils.showMessage("&8[&bMiningWare&8] &6" + feature.getDisplayName() + (Config.getBoolean(feature.getCommandName()) ? " &aEnabled" : " &cDisabled") + "&r");
            }

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
