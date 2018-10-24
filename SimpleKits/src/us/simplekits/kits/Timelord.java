package us.simplekits.kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;


public class Timelord implements Listener {

	Main main;

	public Timelord(Main instance) {
		main = instance;
	}

	private List<String> freeze = new ArrayList<String>();

	@EventHandler
	public void freeze(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();

		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& item.getType() == Material.WATCH) {

			if (main.coolDown.contains(p.getName()
					+ this.getClass().getSimpleName())) {
				p.sendMessage(ChatColor.RED
						+ "You are still in your 15 sec. cooldown");
				return;

			}

			main.iniciarCoolDown(p.getName() + this.getClass().getSimpleName(),
					15);
			
			for (final Player allPlayers : Bukkit.getOnlinePlayers()) {
				if (allPlayers.getLocation().distance(p.getLocation()) <= 5) {
					freeze.add(allPlayers.getName());
				}
				
				main.getServer().getScheduler()
				.scheduleSyncDelayedTask(main, new Runnable() {

					@Override
					public void run() {
						freeze.remove(allPlayers.getName());

					}
				}, 60L);
			}
			freeze.remove(p.getName());
			
			if (p.getNearbyEntities(5, 5, 5).size() == 1) {
				p.sendMessage(ChatColor.BLUE
						+ "You have frozen "
						+ ChatColor.LIGHT_PURPLE
						+ "1"
						+ ChatColor.BLUE + " player!");
			} else {
				p.sendMessage(ChatColor.BLUE
						+ "You have frozen "
						+ ChatColor.LIGHT_PURPLE
						+ (p.getNearbyEntities(5, 5, 5).size())
						+ ChatColor.BLUE + " players!");
			}
		}
	}

	@EventHandler
	public void playerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();

		if (freeze.contains(p.getName())) {
			Location loc = e.getFrom();
			loc.setPitch(e.getTo().getPitch());
			loc.setYaw(e.getTo().getYaw());
			p.teleport(loc);
		}
	}
	
	@EventHandler
	public void playerDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();

		if (freeze.contains(p.getName())) {
			freeze.remove(p.getName());
			main.iniciarCoolDown(p.getName() + this.getClass().getSimpleName(),
					0);
		}
	}
}
