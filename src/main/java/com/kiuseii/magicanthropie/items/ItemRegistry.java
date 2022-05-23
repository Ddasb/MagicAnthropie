package com.kiuseii.magicanthropie.items;

import com.kiuseii.magicanthropie.MagicAnthropie;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, MagicAnthropie.MOD_ID);
    public static void register (IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
