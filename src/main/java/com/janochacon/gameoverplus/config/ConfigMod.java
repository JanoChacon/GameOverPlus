// 
// Decompiled by Procyon v0.5.36
// 

package com.janochacon.gameoverplus.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ConfigMod {
    
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> broadcastWhiteOut;
    public static final ForgeConfigSpec.ConfigValue<Boolean> healPokemon;
    public static final ForgeConfigSpec.ConfigValue<Boolean> whiteOutFromPVP;
    public static final ForgeConfigSpec.ConfigValue<Boolean> killPlayer;
    public static final ForgeConfigSpec.ConfigValue<Boolean> respawnAtHealer;
    
    static {
        BUILDER.push("==================================[ Pixelmon WhiteOut ]==================================");
        broadcastWhiteOut = BUILDER.comment("Broadcast to all players when a player loses a battle. If false, only player will receive the message.")
                .define("Boolean", true);

        healPokemon = BUILDER.comment("Heal the players Pokemon after being returned to their spawn point.")
                .define("Boolean", true);

        whiteOutFromPVP = BUILDER.comment("Should players white out from losing a pvp battle.")
                .define("Boolean", false);

        killPlayer = BUILDER.comment("Kill player when whiting out.")
                .define("Boolean", false);

        respawnAtHealer = BUILDER.comment("Spawn at last used Healer. If player hasn't used a healer, player will spawn at their Spawn Point instead, Only used if killPlayer is false.")
                .define("Boolean", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
