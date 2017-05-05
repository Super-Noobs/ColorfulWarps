package net.supernoobs.colorfulwarps;

import java.util.Collection;
import java.util.HashMap;

public class PlayerWarpsManager {
	private HashMap<String, PlayerWarpCategory> categories;
	
	/**
	 * Calls load on construction
	 */
	public PlayerWarpsManager() {
		load();
	}
	
	/***
	 * Saves the warp file to playerwarps.yml
	 */
	public void save() {
		// TODO Save warp categories
	}
	
	/***
	 * Loads playerwarps.yml
	 */
	public void load() {
		// TODO Load file
	}
	
	public PlayerWarpCategory getCategory(String categoryName) {
		return categories.get(categoryName);
	}
	
	public Collection<PlayerWarpCategory> getCategories() {
		return categories.values();
	}
	
	public void addCategory(PlayerWarpCategory category) {
		
	}
	
}
