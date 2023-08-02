package me.synnk.api.features;

import me.synnk.config.Config;
import me.synnk.utils.PlayerUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;

import java.util.Arrays;
import java.util.List;

public abstract class Feature {
    private String name = "";
    private final FeatureType type;
    private List<String> aliases = Arrays.asList(name);

    public Feature(String name, FeatureType type) {
        this.name = name;
        this.type = type;

        registerCommand();
    }

    public Feature(String name, FeatureType type, List<String> aliases) {
        this.name = name;
        this.aliases = aliases;
        this.type = type;

        registerCommand();
    }

    private void registerCommand() {
        ClientCommandHandler.instance.registerCommand(new CommandFeature(this));
    }

    public String getName() {
        return this.name;
    }

    public void onTrigger() {
    }

    // Custom command class to execute the feature
    private static class CommandFeature extends CommandBase {
        private final String featureName;
        private final List<String> featureAliases;

        private final String commandName;
        private final FeatureType type;

        public CommandFeature(Feature feature) {
            this.featureName = feature.name;
            this.featureAliases = feature.aliases;
            this.commandName = feature.name;
            this.type = feature.type;
            if (this.type == FeatureType.TRIGGER) {
                feature.onTrigger();
            }
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