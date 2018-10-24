package us.simplekits.misc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import us.simplekits.kits.Main;

public class CombatLog implements Listener {

	List<String> tag = new ArrayList<String>();
	List<Integer> time = new ArrayList<Integer>();

	Main main;

	public CombatLog(Main m) {
		main = m;
	}

	public void inTake(int time, Player p) {
		main.getServer().getScheduler()
				.scheduleSyncRepeatingTask(main, new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

					}

				}, 0, time);
	}

	@EventHandler
	public void PvP(EntityDamageByEntityEvent event) {
		final Player player = (Player) event.getEntity();
		Entity p = event.getEntity();
		Entity d = event.getDamager();
		if (((d instanceof Player)) && ((p instanceof Player))) {
			inTake(5, player);
			if (!tag.contains(player.getName())) {
				tag.add(player.getName());
				player.sendMessage("§5You have been tagged.");
				main.getServer().getScheduler()
						.scheduleSyncDelayedTask(main, new Runnable() {
							public void run() {
								if (tag.contains(player.getName())) {
									tag.remove(player.getName());
									player.sendMessage("§5You may now log off.");
								}
							}
						}, 100L);
			}
		}
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		if (this.tag.contains(p.getName())) {
			Bukkit.dispatchCommand(p, "kill " + p.getName());
			Bukkit.getServer().broadcastMessage(
					"§6" + p.getName() + " §7just combat logged!");
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		if (this.tag.contains(p.getName()))
			Bukkit.dispatchCommand(p, "kill " + p.getName());
	}

}
