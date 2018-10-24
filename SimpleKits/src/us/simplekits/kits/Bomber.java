package us.simplekits.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.faris.kingkits.hooks.PvPKits;

public class Bomber implements Listener {

	Main main;

	public Bomber(Main inst) {
		main = inst;
	}

	@EventHandler
	public void rightClickTNT(PlayerInteractEvent evt) {
		Player p = evt.getPlayer();
		if (evt.getAction() == Action.RIGHT_CLICK_BLOCK
				|| evt.getAction() == Action.RIGHT_CLICK_AIR) {
			if (p.getInventory().getItemInHand().getType() == Material.TNT
					&& PvPKits.getKit(p).equalsIgnoreCase("Bomber")) {
				if (main.coolDown.contains(this.getClass().getSimpleName())) {
					p.sendMessage(ChatColor.RED + "You are still in a cooldown of 30 sec!");
					return;
				}
				main.iniciarCoolDown(this.getClass().getSimpleName(), 30);
				p.getWorld().createExplosion(p.getLocation().getX(),
						p.getLocation().getY(), p.getLocation().getZ(), 5,
						false, false);
				for (Entity e : p.getNearbyEntities(5, 5, 5)) {
					if (e instanceof Damageable && !((Player) e == (p))) {
						double pHealth = ((Damageable) e).getHealth();
						((Damageable) e).setHealth(pHealth -= 10);
					}
				}
			}
		}
	}
}
