package net.supernoobs.colorfulwarps;

import java.util.Arrays;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Util {
	public static Inventory warpMenu(Entity player) {
		
		Inventory warpInv = Bukkit.createInventory(null, 27, "§aWarp Menu");
		for(Warp warp:ColorfulWarps.plugin.warpManager.getWarps().values()){
			ItemStack addStack;
			if(warp.hasPermission(player)) {
				addStack = warp.getItemStack();
				if(addStack == null) {
					addStack = warpLocked(warp);
				}
			} else {
				addStack = warpLocked(warp);
			}
			warpInv.addItem(addStack);
		}
		warpInv.addItem(getCloseInventory());
		return warpInv;
		
	}
	
	public static ItemStack getCloseInventory() {
		ItemStack stack = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName("§cClose warp menu");
		stack.setItemMeta(meta);
		return stack;
	}
	
	public static ItemStack warpLocked(Warp warp) {
		ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)14);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(ChatColor.RED+warp.getWarpName());
		meta.setLore(Arrays.asList(ChatColor.RED+"Warp locked"));
		stack.setItemMeta(meta);
		return stack;
	}
}
