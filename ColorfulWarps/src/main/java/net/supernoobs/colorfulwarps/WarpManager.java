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
		if(conf.isConfigurationSection("warps")) {
			for(String warpKey:conf.getConfigurationSection("warps").getKeys(false)) {
				Warp warpy = (Warp) conf.get("warps."+warpKey);
				warps.put(warpKey, warpy);
			}
		}
	}
	
	public void addWarp(Warp warp) {
		conf.set("warps."+warp.getWarpName(), warp);
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
	
	public boolean warpExists(String warpName) {
		return warps.containsKey(warpName);
	}
	
	public Warp getWarp(String warpName) {
		return warps.get(warpName);
		
	}

	public HashMap<String,Warp> getWarps() {
		return warps;
	}

	public void setWarps(HashMap<String,Warp> warps) {
		this.warps = warps;
	}
}
