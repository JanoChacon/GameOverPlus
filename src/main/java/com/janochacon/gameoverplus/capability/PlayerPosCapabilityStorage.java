package com.janochacon.gameoverplus.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class PlayerPosCapabilityStorage implements Capability.IStorage<IPlayerPosCapability>{

    @Override
    public INBT writeNBT(Capability<IPlayerPosCapability> capability, IPlayerPosCapability instance,
                         Direction side) {
        CompoundNBT nbt = new CompoundNBT();

        nbt.putString("dimension", instance.getDimension());
        nbt.putDouble("posX", instance.getRX());
        nbt.putDouble("posY", instance.getRY());
        nbt.putDouble("posZ", instance.getRZ());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IPlayerPosCapability> capability, IPlayerPosCapability instance,
                        Direction side, INBT nbt) {
        CompoundNBT nbt2 = (CompoundNBT) nbt;
        instance.setDimension(nbt2.getString("dimension"));
        instance.setRX(nbt2.getDouble("posX"));
        instance.setRY(nbt2.getDouble("posY"));
        instance.setRZ(nbt2.getDouble("posZ"));
    }

}