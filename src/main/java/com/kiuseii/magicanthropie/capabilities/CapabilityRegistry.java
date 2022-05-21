package com.kiuseii.magicanthropie.capabilities;

import com.kiuseii.magicanthropie.MagicAnthropie;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CapabilityRegistry {
    public static final Capability<IManaCap> MANA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    public static LazyOptional<IManaCap> getMana(final LivingEntity entity){
        return entity.getCapability(MANA_CAPABILITY);
    }

    @Mod.EventBusSubscriber(modid = MagicAnthropie.MOD_ID)
    public static class EventHandler {
        @SubscribeEvent
        public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) {
            if(event.getObject() instanceof Player) {
                ManaCapAttacher.attach(event);
            }
        }

        @SubscribeEvent
        public static void registerCapabilities(final RegisterCapabilitiesEvent event) {
            event.register(IManaCap.class);
        }

        @SubscribeEvent
        public static void playerClone(PlayerEvent.Clone event) {
            Player oldPlayer = event.getOriginal();
            oldPlayer.revive();

            getMana(oldPlayer).ifPresent(oldMaxMana -> getMana(event.getPlayer()).ifPresent(newMaxMana -> {
                newMaxMana.setMana(oldMaxMana.getCurrentMana());
            }));

            event.getOriginal().invalidateCaps();
        }
    }
}
