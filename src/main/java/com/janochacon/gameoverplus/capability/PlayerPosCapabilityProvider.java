package com.janochacon.gameoverplus.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerPosCapabilityProvider implements ICapabilitySerializable<CompoundNBT> {

    IPlayerPosCapability instance = Capabilities.PLAYER_CAPABILITIES.getDefaultInstance();

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return Capabilities.PLAYER_CAPABILITIES.orEmpty(cap, LazyOptional.of(() -> instance));
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) Capabilities.PLAYER_CAPABILITIES.getStorage().writeNBT(Capabilities.PLAYER_CAPABILITIES, instance, null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        Capabilities.PLAYER_CAPABILITIES.getStorage().readNBT(Capabilities.PLAYER_CAPABILITIES, instance, null, nbt);
    }

}
