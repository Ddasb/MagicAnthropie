package com.kiuseii.magicanthropie.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IMana extends INBTSerializable<CompoundTag> {
    int getMana();

    void setMana(int value);
}
