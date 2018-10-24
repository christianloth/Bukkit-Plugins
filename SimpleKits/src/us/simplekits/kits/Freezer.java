package us.simplekits.kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.faris.kingkits.hooks.PvPKits;

public class Freezer implements Listener {

	Main main;

	public Freezer(Main i) {
		main = i;
	}

	private List<String> iccy = new ArrayList<String>();

	@EventHandler
	public void snowball(EntityDamageByEntityEvent e) {
		if (((e.getDamager() instanceof Snowball))
				&& ((e.getEntity() instanceof Player))) {
			Snowball s = (Snowball) e.getDamager();
			if ((s.getShooter() instanceof Player)) {
				Player shooter = (Player) s.getShooter();
				final Player shot = (Player) e.getEntity();
				if (PvPKits.getKit(shooter.getName()).equalsIgnoreCase(
						"Freezer")) {
					iccy.add(shot.getName());
					main.getServer().getScheduler()
							.scheduleSyncDelayedTask(main, new Runnable() {
								public void run() {
									iccy.remove(shot.getName());
									shot.setVelocity(new Vector(0.0D, 1.0D,
											0.0D));
								}
							}, 20L);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if (iccy.contains(e.getPlayer().getName()))
			e.setCancelled(true);
	}

	@EventHandler
	public void death(PlayerDeathEvent e) {
		Player p = e.getEntity().getKiller();
		if (PvPKits.getKit(p).equalsIgnoreCase("Freezer")) {
			p.getInventory().addItem(new ItemStack(Material.SNOW_BALL, 16));
			p.sendMessage(ChatColor.GREEN
					+ "You got a kill and were rewarded with 16 snowballs!");
		}
	}
}
