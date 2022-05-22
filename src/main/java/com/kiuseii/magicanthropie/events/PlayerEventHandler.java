package com.kiuseii.magicanthropie.events;

import com.kiuseii.magicanthropie.MagicAnthropie;
import com.kiuseii.magicanthropie.capabilities.CapabilityRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MagicAnthropie.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {
    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event) {
        if(!event.isWasDeath()) {
            return;
        }

        ServerPlayer oldPlayer = (ServerPlayer) event.getOriginal();
        ServerPlayer newPlayer = (ServerPlayer) event.getPlayer();

        if(!newPlayer.level.isClientSide) {
            oldPlayer.revive();

            CapabilityRegistry.getMana(oldPlayer).ifPresent(oldMana ->
                    CapabilityRegistry.getMana(newPlayer).ifPresent(newMana -> {
                        newMana.setMana(oldMana.getCurrentMana());

                        newMana.sync(newPlayer);
                    })
            );

            event.getOriginal().invalidateCaps();
        }
    }

    @SubscribeEvent
    public static void onPlayerChangedDimensionEvent(PlayerEvent.PlayerChangedDimensionEvent event) {
        ServerPlayer player = (ServerPlayer) event.getPlayer();
        if(!player.level.isClientSide) {
            CapabilityRegistry.getMana(player).ifPresent(mana -> mana.sync(player));
        }
    }

    @SubscribeEvent
    public static void onRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        ServerPlayer player = (ServerPlayer) event.getPlayer();
        if(!player.level.isClientSide) {
            CapabilityRegistry.getMana(player).ifPresent(mana -> mana.sync(player));
        }
    }

    @SubscribeEvent
    public static void onPlayerConnect(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer player = (ServerPlayer) event.getPlayer();
        if(!player.level.isClientSide) {
            CapabilityRegistry.getMana(player).ifPresent(mana -> mana.sync(player));
        }
    }
}
