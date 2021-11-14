package me.quared.playersmp.gui;

import me.quared.playersmp.PlayerSMP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GuiObject implements Metadatable, Listener {
    
    private final ItemStack stack;
    private final Map<String, List<MetadataValue>> values = new HashMap<>();
    private Gui owningGui;
    private final Consumer<InventoryClickEvent> consumer;
    
    public GuiObject(Material material, int amount, Consumer<InventoryClickEvent> consumer) {
        stack = new ItemStack(material, amount);
        Bukkit.getPluginManager().registerEvents(this, PlayerSMP.getInstance());
        this.consumer = consumer;
    }

    @Deprecated
    public GuiObject(Material material, int amount, short dura, Consumer<InventoryClickEvent> consumer) {
        stack = new ItemStack(material, amount, dura);
        Bukkit.getPluginManager().registerEvents(this, PlayerSMP.getInstance());
        this.consumer = consumer;
    }
    
    public GuiObject(Material material, Consumer<InventoryClickEvent> consumer) {
        stack = new ItemStack(material);
        Bukkit.getPluginManager().registerEvents(this, PlayerSMP.getInstance());
        this.consumer = consumer;
    }

    @Deprecated
    public GuiObject(Material material, short dura, Consumer<InventoryClickEvent> consumer) {
        stack = new ItemStack(material);
        stack.setDurability(dura);
        Bukkit.getPluginManager().registerEvents(this, PlayerSMP.getInstance());
        this.consumer = consumer;
    }
    
    public GuiObject(ItemStack stack, Consumer<InventoryClickEvent> consumer) {
        this.stack = stack;
        Bukkit.getPluginManager().registerEvents(this, PlayerSMP.getInstance());
        this.consumer = consumer;
    }
    
    public void setOwningGui(Gui gui) {
        owningGui = gui;
    }
    
    public ItemStack getItem() {
        return stack;
    }
    
    @Override
    public void setMetadata(@NotNull String s, @NotNull MetadataValue metadataValue) {
        List<MetadataValue> values = new ArrayList<>();
        values.add(metadataValue);
        this.values.put(s, values);
    }
    
    public GuiObject addLore(String... s) {
        ItemMeta m = getItem().getItemMeta();
        List<String> lore = m.getLore() != null ? m.getLore() : new ArrayList<>();
        lore.addAll(Arrays.stream(s).filter(Predicate.not(String::isEmpty)).collect(Collectors.toList()));
        m.setLore(lore);
        getItem().setItemMeta(m);
        return this;
    }
    
    public GuiObject setName(String name) {
        ItemMeta meta = getItem().getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        getItem().setItemMeta(meta);
        return this;
    }
    
    public GuiObject addFlags(ItemFlag... flags) {
        ItemMeta meta = getItem().getItemMeta();
        meta.addItemFlags(flags);
        getItem().setItemMeta(meta);
        return this;
    }
    
    @Override
    public List<MetadataValue> getMetadata(String s) {
        return values.get(s);
    }
    
    @Override
    public boolean hasMetadata(String s) {
        return values.containsKey(s);
    }
    
    @Override
    public void removeMetadata(String s, Plugin plugin) {
        values.remove(s);
    }
    
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null && e.getClickedInventory().equals(owningGui.getInventory())) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            
            if (e.getCurrentItem().isSimilar(this.getItem())) {
                consumer.accept(e);
            }
        }
    }
    
}
