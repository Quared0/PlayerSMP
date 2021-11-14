package me.quared.playersmp.holograms;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public final class HologramManager {
    
    private final Plugin plugin;
    private static HologramManager hologramManager;
    
    private final List<Hologram> holograms;
    
    public HologramManager(Plugin plugin) {
        this.plugin = plugin;
        hologramManager = this;
        
        holograms = new ArrayList<>();
    }
    
    public void createHologram(Location location, String text) {
        holograms.add(new Hologram(text, location));
    }
    
    public List<Hologram> getHologramList() {
        return holograms;
    }
    
    public static HologramManager getHologramManager() {
        return hologramManager;
    }
    
}
