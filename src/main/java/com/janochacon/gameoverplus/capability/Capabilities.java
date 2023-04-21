package com.janochacon.gameoverplus.capability;

import com.janochacon.gameoverplus.GameOverPlus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Capabilities {
    @CapabilityInject(IPlayerPosCapability.class)
    public static final Capability<IPlayerPosCapability> PLAYER_CAPABILITIES = null;

    public static IPlayerPosCapability getPlayer(PlayerEntity player) {
        LazyOptional<IPlayerPosCapability> playerData = player.getCapability(Capabilities.PLAYER_CAPABILITIES, null);
        return playerData.orElse(null);
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(IPlayerPosCapability.class, new PlayerPosCapabilityStorage(), PlayerPosCapabilityFactory::new);
    }

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity)
            event.addCapability(new ResourceLocation(GameOverPlus.MOD_ID, "capabilities"), new PlayerPosCapabilityProvider());
    }
}