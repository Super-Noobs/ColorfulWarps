package net.supernoobs.colorfulwarps;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class PlayerWarp implements ConfigurationSerializable {
	private UUID owningUUID;
	private int monthlyUpkeep;
	private Location warpLocation;
	
	public PlayerWarp(Map<String, Object> map) {
		owningUUID = UUID.fromString((String)map.get("owner-uuid"));
		monthlyUpkeep = (Integer)map.get("monthly-upkeep");
		warpLocation = (Location)map.get("warp-location");
	}
	
	@Override
	public Map<String, Object> serialize() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("owner-uuid", owningUUID);
		map.put("monthly-upkeep", monthlyUpkeep);
		map.put("warp-location", warpLocation);
		return map;
	}

}
