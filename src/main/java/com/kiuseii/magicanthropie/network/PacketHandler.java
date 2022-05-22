package com.kiuseii.magicanthropie.network;

import com.kiuseii.magicanthropie.MagicAnthropie;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.List;

public class PacketHandler {
    private static String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MagicAnthropie.MOD_ID, "main_channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static final void register() {
        int messageId = 0;
        INSTANCE.registerMessage(
                messageId++,
                ManaPacket.class,
                ManaPacket::encode,
                ManaPacket::decode,
                ManaPacket.Handler::handle);
    }

    public static void sendTo(Object msg, ServerPlayer player) {
        if (!(player instanceof FakePlayer)) {
            INSTANCE.sendTo(msg, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public static void sendToServer(Object msg) {
        INSTANCE.sendToServer(msg);
    }

    public static void sendToAllPlayers(Object msg, MinecraftServer server) {
        List<ServerPlayer> list = server.getPlayerList().getPlayers();
        for(ServerPlayer e : list) {
            sendTo(msg, e);
        }
    }
}
