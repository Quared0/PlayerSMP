package me.quared.playersmp.util;

import me.quared.playersmp.PlayerSMP;
import org.bukkit.ChatColor;

public final class ConfigUtil {

    public static String getConfigString(String path) {
        return colorize(PlayerSMP.getInstance().getConfig().getString(path));
    }

    public static String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
