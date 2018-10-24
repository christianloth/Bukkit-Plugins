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
import org.bukkit.util.Vector;

import com.faris.kingkits.hooks.PvPKits;

public class Tornado implements Listener {

	Main main;

	public Tornado(Main inst) {
		main = inst;
	}

	@EventHandler
	public void rightClick(PlayerInteractEvent evt) {
		Player p = evt.getPlayer();
		if (evt.getAction() == Action.RIGHT_CLICK_BLOCK
				|| evt.getAction() == Action.RIGHT_CLICK_AIR) {
			if (p.getInventory().getItemInHand().getType() == Material.APPLE
					&& PvPKits.getKit(p).equalsIgnoreCase("Tornado")) {
				if (main.coolDown.contains(this.getClass().getSimpleName())) {
					p.sendMessage(ChatColor.RED
							+ "You are still in a cooldown of 60 sec!");
					return;
				}
				for (Entity e : p.getNearbyEntities(5, 5, 5)) {
					if (e instanceof Damageable) {
						e.setVelocity(new Vector(p.getLocation().getDirection()
								.getX()
								* -1.5, 1.3, p.getLocation().getDirection()
								.getZ()
								* -1.5));
					}
				}
				main.iniciarCoolDown(this.getClass().getSimpleName(), 60);
				return;
			}
		}
	}
}
