package com.kiuseii.magicanthropie.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.INBTSerializable;

public interface IManaCap extends INBTSerializable<CompoundTag> {
    int getCurrentMana();

    int getMaxMana();

    int setMana(final int mana);

    int addMana(final int manaToAdd);

    int consumeMana(final int manaToRemove);

    public void sync(ServerPlayer player);
}
