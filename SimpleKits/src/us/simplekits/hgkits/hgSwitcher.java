package us.simplekits.hgkits;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import us.simplekits.kits.Main;

public class hgSwitcher implements CommandExecutor, Listener {

	Main main;

	public hgSwitcher(Main i) {
		main = i;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] args) {
		M.doAll((Player) sender, Material.SNOW_BALL, 16, this.getClass()
				.getSimpleName(), sender, true);
		if (!M.stomper.contains(sender.getName())) {
			M.switcher.add(sender.getName());
		}
		return false;
	}

	@EventHandler
	public void snowball(EntityDamageByEntityEvent e) {
		if (((e.getDamager() instanceof Snowball))
				&& ((e.getEntity() instanceof Player))) {
			Snowball s = (Snowball) e.getDamager();
			if ((s.getShooter() instanceof Player)) {
				Player shooter = (Player) s.getShooter();
				Location shooterLoc = shooter.getLocation();
				if (M.switcher.contains(shooter.getName())) {
					shooter.teleport(e.getEntity().getLocation());
					e.getEntity().teleport(shooterLoc);

				}
			}
		}
	}
}
