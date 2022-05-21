package com.kiuseii.magicanthropie.items;

import com.kiuseii.magicanthropie.capabilities.CapabilityRegistry;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ManaManipulationStaff extends Item {
    public ManaManipulationStaff(Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        CapabilityRegistry.getMana(pPlayer).ifPresent(iMana -> {
            int currentMana = iMana.consumeMana(1000);

            pPlayer.sendMessage(new TextComponent("Mana : " + currentMana), pPlayer.getUUID());
            pPlayer.sendMessage(new TextComponent("Test"), pPlayer.getUUID());
        });

        pPlayer.sendMessage(new TextComponent("Test 2"), pPlayer.getUUID());

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
