package net.supernoobs.colorfulwarps.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import net.supernoobs.colorfulwarps.Warp;

public class TeleportToWarp implements Runnable {
	private Warp warp;
	private String playerName;
	public TeleportToWarp(Warp warp, String player) {
		this.warp = warp;
		this.playerName = player;
	}
	@Override
	public void run() {
		Player player = Bukkit.getServer().getPlayer(playerName);
		if(!warp.hasPermission(player)) {
			player.sendMessage("§cYou lack permission for this warp");
			return;
		}
		if(warp.getLocation() == null) {
			player.sendMessage("§cWarp not found!");
			return;
		}
		player.sendMessage("§aNow taking you to "+warp.getWarpName());
		player.teleport(warp.getLocation(), TeleportCause.PLUGIN);
		
	}
	
}
