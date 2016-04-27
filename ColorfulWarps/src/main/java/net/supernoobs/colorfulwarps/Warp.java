package net.supernoobs.colorfulwarps;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class Warp {
	private Location location;
	private String warpName;
	private ItemStack itemStack;
	
	public Warp(String name) {
		setWarpName(name);
	}
	
	public Warp(String name, Location location) {
		setWarpName(name);
		setLocation(location);
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getWarpName() {
		return warpName;
	}

	public void setWarpName(String warpName) {
		this.warpName = warpName;
	}

	public ItemStack getItemStack() {
		return itemStack;
	}

	public void setItemStack(ItemStack itemStack) {
		this.itemStack = itemStack;
	}
}
