package com.kiuseii.magicanthropie.items;

import com.kiuseii.magicanthropie.MagicAnthropie;
import com.kiuseii.magicanthropie.items.ManaInfusedPowder;
import com.kiuseii.magicanthropie.items.ManaManipulationStaff;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, MagicAnthropie.MOD_ID);

    public static final RegistryObject<Item> MANA_DUST =
            ITEMS.register("mana_dust", () -> new Item(new Item.Properties().tab(MagicAnthropie.MAGICANTHROPIE_TAB)));

    public static final RegistryObject<Item> MANA_INFUSED_INGOT =
            ITEMS.register("mana_infused_ingot", () -> new Item(new Item.Properties().tab(MagicAnthropie.MAGICANTHROPIE_TAB)));

    public static final RegistryObject<Item> MANA_INFUSED_POWDER =
            ITEMS.register("mana_infused_powder", () -> new ManaInfusedPowder(new Item.Properties().tab(MagicAnthropie.MAGICANTHROPIE_TAB)));

    public static final RegistryObject<Item> MANA_MANIPULATION_STAFF =
            ITEMS.register("mana_manipulation_staff", () -> new ManaManipulationStaff(new Item.Properties().tab(MagicAnthropie.MAGICANTHROPIE_TAB)));

    public static void register (IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
