package net.supernoobs.colorfulwarps;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.supernoobs.colorfulwarps.listeners.InventoryListener;
import net.supernoobs.colorfulwarps.runnables.TeleportToWarp;

public class ColorfulWarps extends JavaPlugin {
	public static ColorfulWarps plugin;
	
	public WarpManager warpManager;
	
	@Override
	public void onEnable() {
		plugin = this;
		ConfigurationSerialization.registerClass(Warp.class);
		warpManager = new WarpManager();
		
		getServer().getPluginManager().registerEvents(new InventoryListener(), this);
		new Messages();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			if (cmd.getName().equalsIgnoreCase("setwarp")) {
				if(args.length < 1) {
					sender.sendMessage(Messages.noArgumentsError());
					return true;
				}
				SetWarp(sender,args[0]);
				return true;
			}
			if (cmd.getName().equalsIgnoreCase("delwarp")) {
				warpManager.delWarp(args[0]);
				sender.sendMessage("§cAttempted to remove "+args[0]);
				return true;
			}
			if (cmd.getName().equalsIgnoreCase("warp")) {
				if(args.length == 0) {
					WarpMenu((Player)sender);
					return true;
				}
				if(args[0].equalsIgnoreCase("set")) {
					if(args.length > 2) {
						if(args[1].toLowerCase().equals("item")) {
							Warp warp = warpManager.getWarp(args[2]);
							warp.setItemStack(((Player) sender).getInventory().getItemInMainHand());
							warpManager.addWarp(warp);
							return true;
						}
					}
					SetWarp(sender, args[1]);
					return true;
				}
				if(args[0].equalsIgnoreCase("delete")) {
					warpManager.delWarp(args[1]);
					sender.sendMessage("§cAttempted to remove "+args[1]);
					return true;
				} 
				if(args[0].equalsIgnoreCase("lock")) {
					if(!warpManager.warpExists(args[1])) {
						sender.sendMessage("§cWarp not found");
						return true;
					}
					Warp warp = warpManager.getWarp(args[1]);
					warp.setPermissionRequired(!warp.isPermissionRequired());
					warpManager.addWarp(warp);
					sender.sendMessage("§aWarp lock: "+warp.isPermissionRequired());
					return true;
				}
				if(warpManager.warpExists(args[0])) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new TeleportToWarp(warpManager.getWarp(args[0]),sender.getName()));
				} else {
					sender.sendMessage("§cWarp not found!");
					return true;
				}
				return true;
			}
		}
		return false;
	}
	
	private void WarpMenu(Player sender){
		sender.openInventory(Util.warpMenu(sender));
	}
	
	private void SetWarp(CommandSender sender, String warpName) {
		Warp warp = new Warp(warpName);
		warp.setLocation(((Player) sender).getLocation());
		warp.setItemStack(warp.generateDefaultItemStack());
		warpManager.addWarp(warp);
		sender.sendMessage("§aSuccessfully set warp to "+warp.getWarpName());
	}
}
