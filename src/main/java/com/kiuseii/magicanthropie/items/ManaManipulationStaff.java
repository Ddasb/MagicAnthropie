package com.kiuseii.magicanthropie.items;

import com.kiuseii.magicanthropie.capabilities.CapabilityRegistry;
import com.kiuseii.magicanthropie.utils.IDisplayMana;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ManaManipulationStaff extends Item implements IDisplayMana {
    public ManaManipulationStaff(Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide()) {
            CapabilityRegistry.getMana(pPlayer).ifPresent(mana -> {
                int currentMana = mana.consumeMana(1000);

                mana.sync((ServerPlayer) pPlayer);

                pPlayer.sendMessage(new TextComponent("Mana : " + currentMana), pPlayer.getUUID());
            });
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
