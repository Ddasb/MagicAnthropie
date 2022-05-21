package com.kiuseii.magicanthropie.capabilities;

import com.kiuseii.magicanthropie.MagicAnthropie;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MagicAnthropie.MOD_ID)
public class ManaCapability {
    public static final Capability<IMana> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void register (RegisterCapabilitiesEvent event) {
        event.register(IMana.class);
    }

    private ManaCapability() {}
}
