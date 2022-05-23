package com.kiuseii.magicanthropie.gui;

import com.kiuseii.magicanthropie.MagicAnthropie;
import com.kiuseii.magicanthropie.capabilities.CapabilityRegistry;
import com.kiuseii.magicanthropie.capabilities.IManaCap;
import com.kiuseii.magicanthropie.utils.IDisplayMana;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class GuiManaHUD extends GuiComponent {
    private static final Minecraft minecraft = Minecraft.getInstance();

    public boolean shouldDisplayBar() {
        ItemStack mainHand = minecraft.player.getMainHandItem();
        ItemStack offHand = minecraft.player.getOffhandItem();

        return (mainHand.getItem() instanceof IDisplayMana && ((IDisplayMana) mainHand.getItem()).shouldDisplay(mainHand))
                || (offHand.getItem() instanceof IDisplayMana && ((IDisplayMana) offHand.getItem()).shouldDisplay(offHand));
    }

    public void drawHUD(PoseStack ms, float pt) {
        if (!shouldDisplayBar()) {
            return;
        }

        IManaCap mana = CapabilityRegistry.getMana(minecraft.player).orElse(null);

        if(mana == null) {
            return;
        }


        int offsetLeft = 4;
        int manaLength = 106;
        int height = minecraft.getWindow().getGuiScaledHeight() - 23;

        RenderSystem.setShaderTexture(0, new ResourceLocation(MagicAnthropie.MOD_ID, "textures/gui/mana_gui_container.png"));
        blit(ms,offsetLeft, height, 0, 0, 108, 18, 256, 256);

        RenderSystem.setShaderTexture(0,new ResourceLocation(MagicAnthropie.MOD_ID, "textures/gui/mana_gui_bar.png"));
        blit(ms,offsetLeft, height, 0, 0, (int) (manaLength * (double) mana.getCurrentMana() / mana.getMaxMana()), 18, 256, 256);
    }
}
