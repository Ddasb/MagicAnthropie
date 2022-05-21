package com.kiuseii.magicanthropie.capabilities;

import com.kiuseii.magicanthropie.MagicAnthropie;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.parser.Entity;

@Mod.EventBusSubscriber(modid = MagicAnthropie.MOD_ID)
public class ManaDensityAttacher {
    private static class ManaDensityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        public static final ResourceLocation IDENTIFIER = new ResourceLocation(MagicAnthropie.MOD_ID, "manaDensityCapability");

        private final IManaDensity backend = new ManaDensityImplementation();
        private final LazyOptional<IManaDensity> optionalData = LazyOptional.of(() -> backend);

        @NotNull
        @Override
        public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return ManaDensityCapability.INSTANCE.orEmpty(cap, this.optionalData);
        }

        void invalidate() {
            this.optionalData.invalidate();
        }


        @Override
        public CompoundTag serializeNBT() {
            return this.backend.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.backend.deserializeNBT(nbt);
        }
    }

    @SubscribeEvent
    public static void attach(final AttachCapabilitiesEvent<Entity> event) {
        final ManaDensityProvider provider = new ManaDensityProvider();

        event.addCapability(ManaDensityProvider.IDENTIFIER, provider);
    }
}
