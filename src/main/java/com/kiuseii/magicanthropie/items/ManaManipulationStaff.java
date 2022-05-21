package com.kiuseii.magicanthropie.items;

import com.kiuseii.magicanthropie.capabilities.IMana;
import com.kiuseii.magicanthropie.capabilities.ManaCapability;
import com.kiuseii.magicanthropie.capabilities.ManaImplementation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;

public class ManaManipulationStaff extends Item {
    public ManaManipulationStaff(Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getLevel().isClientSide()){
            Player player = pContext.getPlayer();
            LazyOptional<IMana> optional = player.getCapability(ManaCapability.INSTANCE);
            IMana manaCap = optional.orElseThrow(() -> new NullPointerException("ha"));

            int mana = manaCap.getMana();

            manaCap.setMana(mana - 1000);

            String message = String.format("This " + manaCap.getMana());

            player.sendMessage(new TextComponent(message), player.getUUID());
        }
        return super.useOn(pContext);
    }
}
