package com.kiuseii.magicanthropie.network;

import com.kiuseii.magicanthropie.capabilities.CapabilityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaPacket {
    private final CompoundTag tag;

    public ManaPacket(CompoundTag tag) {
        this.tag = tag;
    }

    public static void encode(ManaPacket msg, FriendlyByteBuf buf) {
        buf.writeNbt(msg.tag);
    }

    public static ManaPacket decode(FriendlyByteBuf buf) {
        return new ManaPacket(buf.readNbt());
    }

    public static class Handler{
        public static void handle(final ManaPacket msg, Supplier<NetworkEvent.Context> ctx) {
            Minecraft minecraft = Minecraft.getInstance();

            ctx.get().enqueueWork(() -> {

                CapabilityRegistry.getMana(minecraft.player).ifPresent(cap -> cap.deserializeNBT(msg.tag));

            });

            ctx.get().setPacketHandled(true);
        }
    }
}
