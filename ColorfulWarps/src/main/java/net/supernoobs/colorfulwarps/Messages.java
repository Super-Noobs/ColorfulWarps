package net.supernoobs.colorfulwarps;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import net.md_5.bungee.api.ChatColor;

public class Messages {
	private static YamlConfiguration conf;
	public Messages() {
		File getFile = new File(ColorfulWarps.plugin.getDataFolder()+File.separator+"messages.yml");
		if(!getFile.exists()) {
			ColorfulWarps.plugin.saveResource("messages.yml", false);
		}
		conf = YamlConfiguration.loadConfiguration(getFile);
	}
	
	public static String noArgumentsError() {
		return ChatColor.translateAlternateColorCodes('&', conf.getString("no-args"));
	}
	
}
