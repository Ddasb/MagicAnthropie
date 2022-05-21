package com.kiuseii.magicanthropie.capabilities;

import net.minecraft.nbt.CompoundTag;

public class ManaImplementation implements IMana {
    private static final String NBT_KEY_MANA = "mana";
    private static final int maxMana = 10000;

    private int mana = 10000;

    @Override
    public int getMana() {
        return this.mana;
    }

    @Override
    public void setMana(int value) {
        if (value > maxMana) {
            this.mana = 10000;
        } else if (value < 0) {
            this.mana = 0;
        } else {
            this.mana = value;
        }
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putInt(NBT_KEY_MANA, this.mana);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.mana = nbt.getInt(NBT_KEY_MANA);
    }
}
