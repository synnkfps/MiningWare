package me.synnk.mixin;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@SideOnly(Side.CLIENT)
@Mixin(GuiMainMenu.class)
public class MixinGuiMainMenu {

    /**
     * @author e
     * @reason e
     */
    @Overwrite
    public void renderSkybox(int a, int b, float c) {}

}