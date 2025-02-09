package ru.luckstudio.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import ru.luckstudio.FilterEnderChest;
import ru.luckstudio.HexUtils;

import java.util.List;

public class EventListener implements Listener {
    FilterEnderChest plugin;
    List<String> blockitems;

    public EventListener(FilterEnderChest plugin) {
        this.plugin = plugin;
        this.blockitems = plugin.getConfig().getStringList("items");
    }

    @EventHandler
    public void blockInventory(InventoryClickEvent event) {
        String blockMessage = HexUtils.color(plugin.getConfig().getString("messages.no-item"));
        if (event.getInventory().getType() == InventoryType.ENDER_CHEST) {
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null && blockitems != null && blockitems.contains(clickedItem.getType().name())) {
                event.setCancelled(true);
                event.getWhoClicked().sendMessage(blockMessage);
            }
        }
    }
}