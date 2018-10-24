package us.simplekits.hgkits;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import us.simplekits.kits.Main;

public class hgTurtle implements Listener, CommandExecutor {

	Main main;

	public hgTurtle(Main i) {
		main = i;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl,
			String[] args) {
		M.doAll((Player) sender, Material.MUSHROOM_SOUP, 1, this.getClass()
				.getSimpleName(), sender, true);
		if (!M.turtle.contains(sender.getName())) {
			M.turtle.add(sender.getName());
		}
		return false;
	}

	@EventHandler
	public void dmg(EntityDamageEvent evt) {
		if (evt.getEntity() instanceof Player) {
			Player p = (Player) evt.getEntity();
			if (p.isSneaking() && evt.getDamage() >= 1.0
					&& M.turtle.contains(p.getName())) {
				evt.setDamage(1.0);
			}
		}
	}

	@EventHandler
	public void dmg2(EntityDamageByEntityEvent evt) {
		if (evt.getDamager() instanceof Player) {
			Player p = (Player) evt.getDamager();
			if (p.isSneaking() && M.turtle.contains(p.getName())) {
				evt.setCancelled(true);
			}
		}
	}
}
