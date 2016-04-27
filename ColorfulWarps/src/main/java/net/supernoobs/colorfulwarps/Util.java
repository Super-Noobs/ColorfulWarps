package net.supernoobs.colorfulwarps;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Util {
	public static Inventory warpMenu() {
		
		Inventory warpInv = Bukkit.createInventory(null, 27, "§aWarp Menu");
		for(Warp warp:ColorfulWarps.plugin.warpManager.getWarps().values()){
			warpInv.addItem(embedWarpInfo(warp));
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
	
	public static ItemStack embedWarpInfo(Warp warp) {
		ItemStack stack = warp.getItemStack();
		if(stack != null) {
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName("§a"+warp.getWarpName());
			meta.setLore(Arrays.asList("§2Click to warp"));
			stack.setItemMeta(meta);
			return stack;
		}
		return new ItemStack(Material.AIR, 1);
	}
}
