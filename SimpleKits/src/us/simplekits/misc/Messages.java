package us.simplekits.misc;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import us.simplekits.kits.Main;

public class Messages implements Listener {

	Main main;

	public Messages(Main inst) {
		main = inst;
	}

	@EventHandler
	public void join(PlayerJoinEvent evt) {
		evt.setJoinMessage(ChatColor.YELLOW + evt.getPlayer().getName()
				+ ChatColor.GRAY + " joined!");
	}
	
	@EventHandler
	public void leave(PlayerQuitEvent evt) {
		evt.setQuitMessage(ChatColor.YELLOW + evt.getPlayer().getName()
				+ ChatColor.GRAY + " left");
	}
	
	@EventHandler
	public void leave(PlayerDeathEvent evt) {
		evt.setDeathMessage(null);
	}

}
