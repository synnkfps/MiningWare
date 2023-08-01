package me.synnk.features;

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
    private final String name;
    private List<String> aliases;

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
        ClientCommandHandler.instance.registerCommand(new CommandFeature(this.name, this.aliases));
    }

    public String getName() {
        return this.name;
    }

    public void onTrigger() {}


    // Custom command class to execute the feature
    private class CommandFeature extends CommandBase {

        private final String name;
        private final List<String> aliases;

        public CommandFeature(String name, List<String> aliases) {
            this.name = name;
            this.aliases = aliases;
        }

        @Override
        public List<String> getCommandAliases() {
            return aliases;
        }

        @Override
        public String getCommandName() {
            return name;
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/" + name;
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) {
            onTrigger();
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

