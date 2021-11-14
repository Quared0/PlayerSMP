package me.quared.playersmp.holograms;

import me.quared.playersmp.PlayerSMP;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.metadata.FixedMetadataValue;

public class Hologram {
    
    private ArmorStand armorStand;
    
    public Hologram(String text, Location location) {
        armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', text));
        
        armorStand.setMetadata("hologram", new FixedMetadataValue(PlayerSMP.getInstance(), true));
        
        HologramManager.getHologramManager().getHologramList().add(this);
    }
    
    public Hologram(String text, Entity entity) {
        armorStand = (ArmorStand) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ARMOR_STAND);
        entity.addPassenger(armorStand);
        
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', text));
        
        armorStand.setMetadata("hologram", new FixedMetadataValue(PlayerSMP.getInstance(), true));
    
        HologramManager.getHologramManager().getHologramList().add(this);
    }
    
    public Location getLocation() {
        return armorStand.getLocation();
    }
    
    public void setText(String text) {
        armorStand.setCustomName(text);
    }
    
    public void remove() {
        armorStand.remove();
    }
    
}