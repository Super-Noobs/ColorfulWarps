package net.supernoobs.colorfulwarps.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import net.supernoobs.colorfulwarps.ColorfulWarps;
import net.supernoobs.colorfulwarps.Warp;
import net.supernoobs.colorfulwarps.runnables.TeleportToWarp;

public class InventoryListener implements Listener {
	@EventHandler
	public void inventoryEvent(InventoryClickEvent ev) {
		if(ev.getInventory().getName().equals("§aWarp Menu")) {
			ItemStack stack = ev.getCurrentItem();
			if(stack != null) {
				if(stack.getType().equals(Material.REDSTONE_BLOCK)) {
					ev.getWhoClicked().closeInventory();
				} else if(!stack.getType().equals(Material.AIR)) {
					for(Warp warp:ColorfulWarps.plugin.warpManager.getWarps().values()) {
						if(stack.isSimilar(warp.getItemStack())) {
							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ColorfulWarps.plugin, 
									new TeleportToWarp(warp, ev.getWhoClicked().getName()));
						}
					}
				}
			}
			
			ev.setCancelled(true);
		}
	}
}
