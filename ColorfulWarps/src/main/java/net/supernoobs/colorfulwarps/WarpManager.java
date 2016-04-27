package net.supernoobs.colorfulwarps;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class WarpManager {
	private HashMap<String,Warp> warps;
	private File warpsFile;
	YamlConfiguration conf;
	public WarpManager(){
		warps = new HashMap<String,Warp>();
		loadWarps();
	}
	
	private void loadWarps() {
		warpsFile = new File(ColorfulWarps.plugin.getDataFolder()+File.separator+"warps.yml");
		if(!warpsFile.exists()) {
			ColorfulWarps.plugin.saveResource("warps.yml", false);
		}
		
		conf = YamlConfiguration.loadConfiguration(warpsFile);
		
		conf.getConfigurationSection("warps");
		for(String warpKey:conf.getConfigurationSection("warps").getKeys(false)) {
			Warp warpy = loadWarp(warpKey);
			warps.put(warpKey, warpy);
		}
	}
	
	private Warp loadWarp(String warpKey) {
		Warp nWarp = new Warp(warpKey);
		nWarp.setLocation((Location) conf.get("warps."+warpKey+".location"));
		nWarp.setItemStack(conf.getItemStack("warps."+warpKey+".gui-item"));
		return nWarp;
	}
	
	public void addWarp(Warp warp) {
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(warpsFile);
		conf.set("warps."+warp.getWarpName()+".location", warp.getLocation());
		conf.set("warps."+warp.getWarpName()+".gui-item", warp.getItemStack());
		warps.put(warp.getWarpName(), warp);
		try {
			conf.save(warpsFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delWarp(String warp) {
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(warpsFile);
		conf.set("warps."+warp,null);
		warps.remove(warp);
		try {
			conf.save(warpsFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Warp getWarp(String warpName) {
		if(!warps.containsKey(warpName)) {
			Warp warp = loadWarp(warpName);
			warps.put(warp.getWarpName(), warp);
			return warp;
		}
		return warps.get(warpName);
		
	}

	public HashMap<String,Warp> getWarps() {
		return warps;
	}

	public void setWarps(HashMap<String,Warp> warps) {
		this.warps = warps;
	}
}
