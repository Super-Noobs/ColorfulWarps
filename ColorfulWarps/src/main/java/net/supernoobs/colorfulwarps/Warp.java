package net.supernoobs.colorfulwarps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Warp implements ConfigurationSerializable {
	private Location location;
	private String warpName;
	private ItemStack itemStack;
	private boolean permissionRequired;
	
	private Warp() {
		
	}
	
	public Warp(String name) {
		setWarpName(name);
		permissionRequired = false;
	}
	
	public Warp(String name, Location location) {
		setWarpName(name);
		setLocation(location);
		permissionRequired = false;
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
		boolean needsReformat = false;
		if(itemStack.hasItemMeta()) {
			ItemMeta meta = itemStack.getItemMeta();
			if(!meta.hasDisplayName()) {
				needsReformat = true;
			}
		} else {
			needsReformat = true;
		}
		if(needsReformat) {
			ItemMeta meta = itemStack.getItemMeta();
			meta.setDisplayName("§a"+warpName);
			meta.setLore(Arrays.asList("§2Click to warp"));
		}
		this.itemStack = itemStack;
	}

	public boolean isPermissionRequired() {
		return permissionRequired;
	}

	public void setPermissionRequired(boolean requiresPermission) {
		this.permissionRequired = requiresPermission;
	}
	
	public boolean hasPermission(Entity entity) {
		if(isPermissionRequired()) {
			return entity.hasPermission("colorfulwarps.warp."+warpName);
		} else {
			return true;
		}
	}
	
	public ItemStack generateDefaultItemStack(){
		ItemStack stack = new ItemStack(Material.BREAD);
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName("§a"+warpName);
		meta.setLore(Arrays.asList("§2Click to warp"));
		stack.setItemMeta(meta);
		return stack;
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", getWarpName());
		map.put("gui-item", itemStack);
		map.put("location", location);
		map.put("locked", permissionRequired);
		return map;
	}
	
	public static Warp deserialize(Map<String, Object> map) {
		Warp warp = new Warp();
		warp.warpName = (String) map.get("name");
		warp.itemStack = (ItemStack) map.get("gui-item");
		warp.location = (Location) map.get("location");
		warp.permissionRequired = (boolean) map.get("locked");
		return warp;
	}
}
