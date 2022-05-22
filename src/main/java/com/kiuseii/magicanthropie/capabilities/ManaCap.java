package com.kiuseii.magicanthropie.capabilities;

import com.kiuseii.magicanthropie.network.ManaPacket;
import com.kiuseii.magicanthropie.network.PacketHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.apache.logging.log4j.core.jmx.Server;

import javax.annotation.Nullable;
import java.util.Collections;

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

    @Override
    public void sync(ServerPlayer player) {
        if (player instanceof ServerPlayer) {
            PacketHandler.sendTo(new ManaPacket(serializeNBT()), player);
        }
    }
}
