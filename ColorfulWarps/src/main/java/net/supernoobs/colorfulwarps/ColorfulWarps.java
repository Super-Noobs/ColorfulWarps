package net.supernoobs.colorfulwarps;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
					SetWarp(sender, args[1]);
					return true;
				}
				if(args[0].equalsIgnoreCase("delete")) {
					warpManager.delWarp(args[1]);
					sender.sendMessage("§cAttempted to remove "+args[1]);
					return true;
				}
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new TeleportToWarp(warpManager.getWarp(args[0]),sender.getName()));
				return true;
			}
		}
		return false;
	}
	
	private void WarpMenu(Player sender){
		sender.openInventory(Util.warpMenu());
	}
	
	private void SetWarp(CommandSender sender, String warpName) {
		Warp warp = new Warp(warpName);
		warp.setLocation(((Player) sender).getLocation());
		warp.setItemStack(new ItemStack(Material.BREAD));
		warpManager.addWarp(warp);
		sender.sendMessage("§aSuccessfully set warp to "+warp.getWarpName());
	}
}
