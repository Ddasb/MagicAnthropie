package com.kiuseii.magicanthropie.blocks;

import com.kiuseii.magicanthropie.MagicAnthropie;
import com.kiuseii.magicanthropie.items.ItemRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MagicAnthropie.MOD_ID);

    public static final RegistryObject<Block> CRYSTALIZED_MANA_ORE = registryBlock("crystalized_mana_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(5)
                    .requiresCorrectToolForDrops()), MagicAnthropie.MAGICANTHROPIE_TAB);

    public static final RegistryObject<Block> DEEPSLATE_CRYSTALIZED_MANA_ORE = registryBlock("deepslate_crystalized_mana_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(5)
                    .requiresCorrectToolForDrops()), MagicAnthropie.MAGICANTHROPIE_TAB);

    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
