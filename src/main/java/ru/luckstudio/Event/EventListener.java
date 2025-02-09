package ru.luckstudio.Event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
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

        if(event.getWhoClicked().hasPermission("filench.admin")) {
            return;
        }

        String blockMessage = HexUtils.color(plugin.getConfig().getString("messages.no-item"));
        if (event.getInventory().getType() == InventoryType.ENDER_CHEST) {
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null && blockitems != null && blockitems.contains(clickedItem.getType().name())) {
                event.setCancelled(true);
                event.getWhoClicked().sendMessage(blockMessage);
            }
        }
    }

    @EventHandler
    public void blockDragInventory(InventoryDragEvent event) {

        if(event.getWhoClicked().hasPermission("filench.admin")) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void InventoryOpen(InventoryOpenEvent event) {
        if (event.getInventory().getType() == InventoryType.ENDER_CHEST) {
            Player player = (Player) event.getPlayer();
            ItemStack[] enderChest = event.getInventory().getContents();

            for (String itemName : blockitems) {
                Material material = Material.getMaterial(itemName);

                if (material != null) {
                    for (int i = 0; i < enderChest.length; i++) {
                        ItemStack item = enderChest[i];

                        if (item != null && item.getType() == material) {
                            player.getInventory().addItem(item);
                            enderChest[i] = null;

                        }
                    }
                }
            }

            event.getInventory().setContents(enderChest);
        }
    }
}