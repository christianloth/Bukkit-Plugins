package us.simplekits.misc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import us.simplekits.kits.Main;

public class AntiBadChat implements Listener {

	Main main;

	public AntiBadChat(Main instance) {
		this.main = instance;
	}

	@EventHandler
	public void swerve(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();

		if (e.getMessage().contains("zuduxmc")
				|| e.getMessage().contains("ZuduxMc")
				|| e.getMessage().contains("ZUDUXMC")
				|| e.getMessage().contains("ZuduxMC")) {
			p.sendMessage(ChatColor.RED
					+ "Nice try!!! All mods and above have been notified!");
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.hasPermission("simplekits.swervezudux.notify")) {
					player.sendMessage(ChatColor.RED
							+ p.getName().toUpperCase() + ChatColor.GRAY
							+ " IS TRYING TO ADVERTIZE ZUDUX'S STREAM!!");
				}
			}
			e.setCancelled(true);
		}
		if (e.getMessage().contains("jugglekits")
				|| e.getMessage().contains("JuggleKits")
				|| e.getMessage().contains("JUGGLEKITS")) {
			e.setCancelled(true);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
					"mail send Eexar " + p.getName() + ": " + e.getMessage());
		}
		if (e.getMessage().contains("pokekits")
				|| e.getMessage().contains("PokeKits")
				|| e.getMessage().contains("POKEKITS")
				|| e.getMessage().contains("HIVE")
				|| e.getMessage().contains("Hive")
				|| e.getMessage().contains("hive")
				|| e.getMessage().contains("Zudux")
				|| e.getMessage().contains("zudux")) {
			e.setCancelled(true);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
					"mail send Eexar " + p.getName() + ": " + e.getMessage());
		}
	}
}
