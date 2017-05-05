package net.supernoobs.colorfulwarps;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerWarp implements ConfigurationSerializable {
	private UUID owner;
	private int monthlyUpkeep;
	private Location warpLocation;
	
	public PlayerWarp(Map<String, Object> map) {
		owner = UUID.fromString((String)map.get("owner-uuid"));
		monthlyUpkeep = (Integer)map.get("monthly-upkeep");
		warpLocation = (Location)map.get("warp-location");
	}
	
	@Override
	public Map<String, Object> serialize() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("owner-uuid", owner);
		map.put("monthly-upkeep", monthlyUpkeep);
		map.put("warp-location", warpLocation);
		return map;
	}
	
	/**
	 * Creates a new warp owned by the given player
	 * @param player
	 */
	public PlayerWarp(Player player, int upkeep) {
		owner = player.getUniqueId();
		warpLocation = player.getLocation();
		monthlyUpkeep = upkeep;
	}
	
	/**
	 * Creates an item stack representing the given player's warp
	 * @return
	 */
	public ItemStack getMenuButton() {
		// TODO create item stack that can be used for the given button
		return null;
	}

}
