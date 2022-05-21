package com.kiuseii.magicanthropie.capabilities;

import com.kiuseii.magicanthropie.MagicAnthropie;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.fml.common.Mod;

public class ManaDensityImplementation implements IManaDensity {
    private static final String NBT_KEY_MANA = "manaDensity";

    private int manaDensity = 10000;

    @Override
    public int getManaDensity() {
        return this.manaDensity;
    }

    @Override
    public void setManaDensity(int value) {
        this.manaDensity = value;
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putInt(NBT_KEY_MANA, this.manaDensity);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.manaDensity = nbt.getInt(NBT_KEY_MANA);
    }
}
