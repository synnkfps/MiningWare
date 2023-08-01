package me.synnk.handlers;


import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.command.CommandHandler;
import net.minecraftforge.common.MinecraftForge;

public abstract class Module implements Minecraft {
    private final String name = getAnnotation().name();
    private final String description = getAnnotation().description();

    private int key = getAnnotation().key();

    private boolean enabled = false;
    private boolean opened = false;
    private boolean binding = false;
    private boolean drawn = true;

    private IModule getAnnotation() {
        if (getClass().isAnnotationPresent(IModule.class)) {
            return getClass().getAnnotation(IModule.class);
        }

        throw new IllegalStateException("annotation not found"); // thx johnshiozo
    }

    // oops
    public boolean nullCheck() {
        return mc.thePlayer == null || mc.theWorld == null;
    }

    public void enable() {
        enabled = true;

        MinecraftForge.EVENT_BUS.register(this);

        onEnable();

        handleNotifications(true);
    }

    public void disable() {
        enabled = false;

        MinecraftForge.EVENT_BUS.unregister(this);

        onDisable();

        handleNotifications(false);
    }

    private void handleNotifications(boolean enable) {
        if (nullCheck()) return;
        String message;

        if (enable) {
            message = ChatFormatting.AQUA + name + ChatFormatting.RESET + " is now " + ChatFormatting.GREEN + "ENABLED";
        } else {
            message = ChatFormatting.AQUA + name + ChatFormatting.RESET + " is now " + ChatFormatting.RED + "DISABLED";
        }
    }

    public void toggle() {
        try {
            if (enabled) {
                disable();
            } else {
                enable();
            }
        } catch (Exception ignored) {}
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            enable();
        } else {
            disable();
        }
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public void setBinding(boolean binding) {
        this.binding = binding;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getKey() {
        return key;
    }

    public boolean isOpened() {
        return opened;
    }

    public boolean isBinding() {
        return binding;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

    public String getArraylistInfo() {
        return "";
    }

    public void onEnable() {}

    public void onDisable() {}

    public void onUpdate() {}
}