package com.kiuseii.magicanthropie.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IManaDensity extends INBTSerializable<CompoundTag> {
    int getManaDensity();

    void setManaDensity(int value);
}
