package com.kiuseii.magicanthropie.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

public class ManaCap implements IManaCap {
    private final LivingEntity livingEntity;

    private int mana = 10000;

    private int maxMana = 10000;

    public ManaCap(@Nullable final LivingEntity entity) {
        this.livingEntity = entity;
    }

    @Override
    public int getCurrentMana() {
        return mana;
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public int setMana(int mana) {
        if(mana > getMaxMana()){
            this.mana = getMaxMana();
        }else if(mana < 0){
            this.mana = 0;
        }else {
            this.mana = mana;
        }
        return this.getCurrentMana();
    }

    @Override
    public int addMana(int manaToAdd) {
        this.setMana(this.getCurrentMana() + manaToAdd);
        return this.getCurrentMana();
    }

    @Override
    public int consumeMana(int manaToRemove) {
        this.setMana(this.getCurrentMana() - manaToRemove);
        return this.getCurrentMana();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("current", getCurrentMana());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        setMana(tag.getInt("current"));
    }

}
