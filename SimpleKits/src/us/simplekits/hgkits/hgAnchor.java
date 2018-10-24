package us.simplekits.hgkits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import us.simplekits.kits.Main;

public class hgAnchor implements Listener, CommandExecutor {

	Main main;

	public hgAnchor(Main i) {
		main = i;
	}

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2,
			String[] arg3) {
		M.doAll((Player) s, this.getClass().getSimpleName(), s, true);
		M.anchor.add(s.getName());
		return false;
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player
				&& e.getEntity() instanceof Damageable) {
			Player p = (Player) e.getEntity();
			if (M.anchor.contains(p.getName())) {
				e.setCancelled(true);
				((Damageable) e.getEntity()).damage(e.getDamage());
			}
		}
	}

	/*
	 * @EventHandler public void onDamage2(EntityDamageByEntityEvent e) { if
	 * (e.getDamager() instanceof Player && e.getEntity() instanceof Damageable
	 * && e.getEntity() != e.getDamager()) { if (M.anchor.contains(((Player)
	 * e.getDamager()).getName())) { e.setCancelled(true); ((Damageable)
	 * e.getDamager()).damage(e.getDamage()); } } }
	 */
}
