package me.quared.playersmp.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Gui {

    private final Inventory inventory;
    private final Map<Integer, GuiObject> objects;
    private final int size;
    
    public Gui(Map<Integer, GuiObject> objects, int size, String name) {
        inventory = Bukkit.createInventory(null, size, name);
        this.objects = objects;
        this.size = size;
        
        for (int key : objects.keySet()) {
            objects.get(key).setOwningGui(this);
            
            inventory.setItem(key, objects.get(key).getItem());
        }
    }
    
    public void setItem(int loc, GuiObject item) {
        objects.put(loc, item);
        item.setOwningGui(this);
        inventory.setItem(loc, item.getItem());
    }
    
    public Inventory getInventory() {
        return inventory;
    }
    
    public boolean slotTaken(int slot) {
        return inventory.getItem(slot) != null;
    }
    
    public List<Integer> getUnusedSlots() {
        List<Integer> ints = new ArrayList<>();
        objects.keySet().forEach(i -> {
            if (!slotTaken(i)) ints.add(i);
        });
        return ints;
    }
    
    public int getSize() {
        return size;
    }
    
    public Map<Integer, GuiObject> getObjects() {
        return objects;
    }
}
