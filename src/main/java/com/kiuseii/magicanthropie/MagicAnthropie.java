package com.kiuseii.magicanthropie;

import com.kiuseii.magicanthropie.blocks.BlockRegistry;
import com.kiuseii.magicanthropie.items.ItemRegistry;
import com.kiuseii.magicanthropie.network.PacketHandler;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MagicAnthropie.MOD_ID)
public class MagicAnthropie {
    public static final String MOD_ID = "magicanthropie";

    public static final CreativeModeTab MAGICANTHROPIE_TAB = new CreativeModeTab("magicanthropie_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.MANA_DUST.get());
        }
    };

    public MagicAnthropie() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemRegistry.register(eventBus);
        BlockRegistry.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup (final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.MANA_CELL.get(), RenderType.translucent());
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.register();
    }
}
