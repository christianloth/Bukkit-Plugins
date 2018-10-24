package us.simplekits.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.faris.kingkits.hooks.PvPKits;

public class Jumper implements Listener {

	Main main;

	public Jumper(Main inst) {
		main = inst;
	}

	@EventHandler
	public void rightClick(PlayerInteractEvent evt) {
		Player p = evt.getPlayer();
		if (evt.getAction() == Action.RIGHT_CLICK_BLOCK
				|| evt.getAction() == Action.RIGHT_CLICK_AIR) {
			if (p.getInventory().getItemInHand().getType() == Material.FEATHER
					&& PvPKits.getKit(p).equalsIgnoreCase("Jumper")) {
				if (main.coolDown.contains(this.getClass().getSimpleName())) {
					p.sendMessage(ChatColor.RED
							+ "You are still in a cooldown of 30 sec!");
					return;
				}
				/*double x, y, z;
				double dX = pLoc.getX() - pLoc.getX() + 10;
				double dY = pLoc.getY() - pLoc.getY() + 10;
				double dZ = pLoc.getZ() - pLoc.getZ() + 10;
				double pitch = ((90 - p.getLocation().getPitch()) * Math.PI) / 180;
				double yaw = Math.atan2(dZ, dX); // Also ((p.getLocation().getYaw() + 90 + 180) * Math.PI) / 180
				x = Math.sin(pitch) * Math.cos(yaw);
				y = Math.sin(pitch) * Math.sin(yaw);
				z = Math.cos(pitch);*/
				Vector v = new Vector(0, 1.4735, 0);
				p.setVelocity(v);
				main.iniciarCoolDown(this.getClass().getSimpleName(), 0);
			}
		}
	}

}
