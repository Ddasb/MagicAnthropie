package com.kiuseii.magicanthropie.setup;

import com.kiuseii.magicanthropie.MagicAnthropie;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, MagicAnthropie.MOD_ID);

    public static final RegistryObject<Item> MANA_DUST =
            ITEMS.register("mana_dust", () -> new Item(new Item.Properties().tab(MagicAnthropie.MAGICANTHROPIE_TAB)));

    public static final RegistryObject<Item> MANA_INFUSED_INGOT =
            ITEMS.register("mana_infused_ingot", () -> new Item(new Item.Properties().tab(MagicAnthropie.MAGICANTHROPIE_TAB)));

    public static void register (IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
