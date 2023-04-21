// 
// Decompiled by Procyon v0.5.36
// 

package com.janochacon.gameoverplus.utils;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.server.ServerWorld;

import static net.minecraft.world.gen.Heightmap.Type.WORLD_SURFACE;

public class CleanTeleporter extends Teleporter
{
    private final ServerWorld worldServerInstance;
    
    public CleanTeleporter(final ServerWorld par1WorldServer) {
        super((ServerWorld) par1WorldServer);
        this.worldServerInstance = par1WorldServer;
    }
    
    public void func_180266_a(final Entity pEntity, final float rotationYaw) {
        final int i = MathHelper.floor(pEntity.getX());
        final int j = MathHelper.floor(pEntity.getY());
        final int k = MathHelper.floor(pEntity.getZ());
        this.worldServerInstance.getChunk(new BlockPos(i, j, k));
        final int height = this.worldServerInstance.getHeight(WORLD_SURFACE, i, k);
        pEntity.teleportTo((double)i, (double)height, (double)k);
    }
}
