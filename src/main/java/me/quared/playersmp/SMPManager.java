package me.quared.playersmp;

import org.bukkit.entity.Player;

public class SMPManager {

    private static SMPManager smpManager;
    private final PlayerSMP plugin;

    public SMPManager(PlayerSMP plugin) {
        smpManager = this;

        this.plugin = plugin;
    }

    public void createSMP(Player p) {

    }

    public static SMPManager getSMPManager() {
        return smpManager;
    }

}
