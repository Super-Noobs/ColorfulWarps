package net.supernoobs.colorfulwarps;

import java.util.List;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class PlayerWarpCategory implements ConfigurationSerializable {
	private String categoryName;
	private int slotCost;
	private int monthlyUpkeep;
	private List<PlayerWarp> warps;
	
	/**
	 * Creates a new object from the given map, used by the Bukkit serialization service.
	 * @param map
	 */
	public PlayerWarpCategory(Map<String, Object> map) {
		// TODO Deserialize thezies.
	}
	
	@Override
	public Map<String, Object> serialize() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Inventory getInventory(int page) {
		// TODO Create an inventory containing buttons to go to the warps.
		return null;
	}
	
	public boolean buyWarp(Player player, int slotId) {
		Economy economy = ColorfulWarps.economy;
		// Check that the economy is loaded properly
		if(economy == null) {
			return false;
		}
		
		if(economy.has(player, slotCost)) {
			EconomyResponse response = economy.withdrawPlayer(player, slotCost);
			if(response.transactionSuccess()) {
				warps.add(new PlayerWarp(player, slotCost));
			}
		}
		return false;
	}
}
