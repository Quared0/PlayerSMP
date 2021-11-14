package me.quared.playersmp.items;

import me.quared.playersmp.PlayerSMP;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CustomItem implements Listener {
    
    private final ItemStack itemStack;
    private final Consumer<PlayerInteractEvent> consumer;
    
    public CustomItem(ItemStack itemStack, Consumer<PlayerInteractEvent> consumer) {
        this.itemStack = itemStack;
        this.consumer = consumer;
        Bukkit.getPluginManager().registerEvents(this, PlayerSMP.getInstance());
    }
    
    public CustomItem(Material mat, Consumer<PlayerInteractEvent> consumer) {
        this.itemStack = new ItemStack(mat);
        this.consumer = consumer;
        Bukkit.getPluginManager().registerEvents(this, PlayerSMP.getInstance());
    }
    
    public ItemStack getItemStack() {
        return itemStack;
    }
    
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().isSimilar(itemStack) ||
                e.getPlayer().getInventory().getItemInOffHand().isSimilar(itemStack)) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                e.setCancelled(true);
                
                consumer.accept(e);
            }
        }
    }
    
    public CustomItem addLore(String... s) {
        ItemMeta m = getItemStack().getItemMeta();
        List<String> lore = m.getLore() != null ? m.getLore() : new ArrayList<>();
        
        lore.addAll(Arrays.asList(s));
        m.setLore(lore);
        getItemStack().setItemMeta(m);
        return this;
    }
    
    public CustomItem setName(String name) {
        ItemMeta meta = getItemStack().getItemMeta();
        meta.setDisplayName(name);
        getItemStack().setItemMeta(meta);
        return this;
    }
    
    public CustomItem addLore(ItemFlag... flags) {
        ItemMeta meta = getItemStack().getItemMeta();
        meta.addItemFlags(flags);
        getItemStack().setItemMeta(meta);
        return this;
    }
    
}
