package com.kiuseii.magicanthropie.utils;

import net.minecraft.world.item.ItemStack;

public interface IDisplayMana {
    default boolean shouldDisplay(ItemStack stack){
        return true;
    }
}
