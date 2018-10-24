package us.simplekits.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.faris.kingkits.hooks.PvPKits;

public class Pitcher implements Listener {
	Main main;

	public Pitcher(Main inst) {
		this.main = inst;
	}

	@EventHandler
	public void snowball(EntityDamageByEntityEvent e) {
		if (((e.getDamager() instanceof Snowball))
				&& ((e.getEntity() instanceof Player))) {
			Snowball s = (Snowball) e.getDamager();
			if ((s.getShooter() instanceof Player)) {
				Player shooter = (Player) s.getShooter();
				if (PvPKits.getKit(shooter).equalsIgnoreCase("Pitcher")) {
					Damageable d = (Damageable) e.getEntity();
					double health = d.getHealth();
					d.setHealth(health -= 2.0D);
				}
			}
		}
	}

	@EventHandler
	public void death(PlayerDeathEvent e) {
		Player p = e.getEntity().getKiller();
		if (PvPKits.getKit(p).equalsIgnoreCase("Pitcher")) {
			p.getInventory().addItem(
					new ItemStack[] { new ItemStack(Material.SNOW_BALL, 16) });
			p.sendMessage(ChatColor.GREEN
					+ "You got a kill and were rewarded with 16 snowballs!");
		}
	}
}