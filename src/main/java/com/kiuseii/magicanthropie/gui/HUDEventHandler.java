package com.kiuseii.magicanthropie.gui;

import com.kiuseii.magicanthropie.MagicAnthropie;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MagicAnthropie.MOD_ID)
public class HUDEventHandler {
    private static final GuiManaHUD manaHUD = new GuiManaHUD();

    @SubscribeEvent
    public static void renderManaHUD(final RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }

        manaHUD.drawHUD(event.getMatrixStack(), event.getPartialTicks());
    }
}
