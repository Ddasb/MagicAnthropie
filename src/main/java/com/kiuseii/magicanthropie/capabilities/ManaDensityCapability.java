package com.kiuseii.magicanthropie.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ManaDensityCapability {
    public static final Capability<IManaDensity> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void register (RegisterCapabilitiesEvent event) {
        event.register(IManaDensity.class);
    }

    private ManaDensityCapability() {}
}
