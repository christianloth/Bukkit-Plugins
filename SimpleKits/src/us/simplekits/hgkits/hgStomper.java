package us.simplekits.hgkits;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import us.simplekits.kits.Main;

public class hgStomper implements CommandExecutor, Listener {

	Main main;

	public hgStomper(Main i) {
		main = i;
	}

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2,
			String[] args) {
		if (s.hasPermission("kingkits.kits.Stomper")) {
			M.doAll((Player) s, Material.MUSHROOM_SOUP, 1, this.getClass()
					.getSimpleName(), s, true);
			if (!M.stomper.contains(s.getName())) {
				M.stomper.add(s.getName());
			}
		} else {
			M.doAll((Player) s, Material.MUSHROOM_SOUP, 1, this.getClass()
					.getSimpleName(), s, true);
			if (!M.stomper.contains(s.getName())) {
				M.stomper.add(s.getName());
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void playerDamage(EntityDamageEvent evt) {
		if ((evt.getEntity() instanceof Player)) {
			Player p = (Player) evt.getEntity();
			if ((M.stomper.contains(p.getName()))
					&& (evt.getCause()
							.equals(EntityDamageEvent.DamageCause.FALL))) {
				evt.setDamage((int) p.getFallDistance() / 12);
				if (p.getFallDistance() > 4.5F) {
					List<Entity> nearby = p.getNearbyEntities(3.5D, 3.5D, 3.5D);
					for (Entity e : nearby)
						if (((Player) e).isSneaking()) {
							((Player) e).damage((int) p.getFallDistance() / 10);
							if (p.getFallDistance() / 10 < 0.5) {
								Bukkit.broadcastMessage("Test");
							}
						} else {
							((Player) e).damage((int) p.getFallDistance() / 2);
						}
				}
			}
		}
	}
}
