package me.quared.playersmp;

import me.quared.playersmp.commands.SMPCommand;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class PlayerSMP extends JavaPlugin {

    private static PlayerSMP instance;

    private YamlConfiguration playerWorlds;
    private File playerWorldsFile;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        registerCommands();

        registerDataConfig();
    }

    private void registerDataConfig() {
        playerWorldsFile = new File(getDataFolder(), "player_worlds.yml");

        playerWorlds = new YamlConfiguration();
        try {
            playerWorlds.load(playerWorldsFile);
        } catch (IOException | InvalidConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("smp")).setExecutor(new SMPCommand());
        Objects.requireNonNull(getCommand("smp")).setTabCompleter(new SMPCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static PlayerSMP getInstance() {
        return instance;
    }

    public YamlConfiguration getPlayerWorlds() {
        return playerWorlds;
    }

    public void savePlayerWorlds() {
        try {
            playerWorlds.save(playerWorldsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
