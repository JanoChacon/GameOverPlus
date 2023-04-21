package com.janochacon.gameoverplus.listener;
import com.janochacon.gameoverplus.capability.Capabilities;
import com.janochacon.gameoverplus.utils.CleanTeleporter;
import com.janochacon.gameoverplus.config.ConfigMod;
import com.pixelmonmod.pixelmon.api.battles.BattleResults;
import com.pixelmonmod.pixelmon.api.events.HealerEvent;
import com.pixelmonmod.pixelmon.api.events.battles.BattleEndEvent;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class BattleLostEvent {

    public BattleLostEvent() {
    }

    @SubscribeEvent
    public void onHealerUse(HealerEvent.Pre e) {
        ServerPlayerEntity player = (ServerPlayerEntity)e.player;
        Capabilities.getPlayer(player).setRX(player.getX());
        Capabilities.getPlayer(player).setRY(player.getY());
        Capabilities.getPlayer(player).setRZ(player.getZ());
        Capabilities.getPlayer(player).setDimension(dimToSting(player.level.dimension()));
    }

    @SubscribeEvent
    public void onHealerUse(HealerEvent.Post e) {
        ServerPlayerEntity player = (ServerPlayerEntity)e.player;
        Capabilities.getPlayer(player).setRX(player.getX());
        Capabilities.getPlayer(player).setRY(player.getY());
        Capabilities.getPlayer(player).setRZ(player.getZ());
        Capabilities.getPlayer(player).setDimension(dimToSting(player.level.dimension()));
    }

    private String dimToSting(RegistryKey<World> dimension){
        String dimString = "overworld";

        if (dimension == World.OVERWORLD){
            dimString = "overworld";
        } else if (dimension == World.NETHER) {
            dimString = "nether";
        }else if (dimension == World.END) {
            dimString = "end";
        }
        return dimString;
    }

    private RegistryKey<World> dimToKey(String dimension){

        RegistryKey<World> dimKey = World.OVERWORLD;

        if (dimension.equals("overworld")){
            dimKey = World.OVERWORLD;
        } else if (dimension.equals("nether")) {
            dimKey = World.NETHER;
        }else if (dimension.equals("end")) {
            dimKey = World.END;
        }
        return dimKey;
    }

    @SubscribeEvent
    public void onBattleEnd(final BattleEndEvent e) {
        final boolean pvpBattle = e.getPlayers().size() > 1;

        for (final ServerPlayerEntity player : e.getPlayers()) {
            boolean lostBattle = true;
            BattleResults result = e.getResult(player).get();
            if (result.equals(BattleResults.DEFEAT)){
                if ((!pvpBattle)) {
                    this.playerWhitedOut(player);
                }
            }
        }
    }
    private void teleportPlayer(ServerPlayerEntity player) {

            if (player.level.dimension() != dimToKey(Capabilities.getPlayer(player).getDimension())){
                player.changeDimension(ServerLifecycleHooks.getCurrentServer().getLevel(dimToKey(Capabilities.getPlayer(player).getDimension())),
                        new CleanTeleporter((ServerWorld) player.level));
            }
                player.teleportTo(Capabilities.getPlayer(player).getRX(), Capabilities.getPlayer(player).getRY()+1, Capabilities.getPlayer(player).getRZ());
    }

    private void playerWhitedOut(final ServerPlayerEntity player) {
        final boolean broadcastMessage = ConfigMod.broadcastWhiteOut.get();
        final boolean healPokemon = ConfigMod.healPokemon.get();
        final boolean killPlayer = ConfigMod.killPlayer.get();
        final boolean respawnAtHealer = ConfigMod.respawnAtHealer.get();
        if (killPlayer) {
            player.kill();
        }
        if (!killPlayer) {
            if (respawnAtHealer && validateHealerPos(player)) {
                teleportPlayer(player);
            }
            else {
                if (player.getRespawnPosition() == null){
                    player.teleportTo(
                            ServerLifecycleHooks.getCurrentServer().overworld().getSharedSpawnPos().getX(),
                            ServerLifecycleHooks.getCurrentServer().overworld().getSharedSpawnPos().getY(),
                            ServerLifecycleHooks.getCurrentServer().overworld().getSharedSpawnPos().getZ()
                    );
                }
                else {
                    player.teleportTo(
                            player.getRespawnPosition().getX(),
                            player.getRespawnPosition().getY(),
                            player.getRespawnPosition().getZ()
                    );
                }

            }
        }
        if (healPokemon) {
            StorageProxy.getParty(player).heal();
        }
        if (broadcastMessage) {
            player.sendMessage(new StringTextComponent("Te han derrotado, F"), Util.NIL_UUID);
        }
    }

    private boolean validateHealerPos(ServerPlayerEntity player){
        if (Capabilities.getPlayer(player).getRX() == 0 && Capabilities.getPlayer(player).getRY() == 0 && Capabilities.getPlayer(player).getRZ() == 0){
            return false;
        } else {
            return true;
        }

    }

}
