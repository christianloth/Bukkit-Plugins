package us.simplekits.hgkits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import us.simplekits.kits.Main;

public class hgEndermage implements Listener, CommandExecutor {
	
	Main main;
	
	public hgEndermage(Main i) {
		main = i;
	}

	public static boolean isIn(Location check, Location min, Location max) {
		boolean x = false;
		boolean y = false;
		boolean z = false;

		for (int i = min.getBlockX(); i <= max.getBlockX(); i++) {
			if (check.getBlockX() == i) {
				x = true;
			}

		}

		for (int i = min.getBlockY(); i <= max.getBlockY(); i++) {
			if (check.getBlockY() == i) {
				y = true;
			}

		}

		for (int i = min.getBlockZ(); i <= max.getBlockZ(); i++) {
			if (check.getBlockZ() == i) {
				z = true;
			}

		}

		return (x) && (y) && (z);
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerEndermage(BlockPlaceEvent e) {
		Player p = e.getPlayer();

		if (p.hasPermission("viperboy.kits.endermage")
				&& M.mage.contains(p.getName())) {
			if (e.getBlockPlaced().getType() == Material.PORTAL) {
				Location temp = e.getBlockPlaced().getLocation();

				int minX = temp.getBlockX() - 1;
				int minY = 0;
				int minZ = temp.getBlockZ() - 1;

				int maxX = temp.getBlockX() + 1;
				int maxY = 256;
				int maxZ = temp.getBlockZ() + 1;

				Location min = new Location(temp.getWorld(), minX, minY, minZ);
				Location max = new Location(temp.getWorld(), maxX, maxY, maxZ);

				List<Player> toTp = new ArrayList<Player>();
				toTp.add(p);

				for (Player t : Bukkit.getOnlinePlayers()) {
					if (!t.getName().equalsIgnoreCase(p.getName())) {
						if (isIn(t.getLocation(), min, max)) {
							toTp.add(t);
						}
					}
				}

				if (toTp.size() >= 2) {
					e.setCancelled(true);

					for (Player v : toTp) {
						v.teleport(temp);
					}

				}

				e.setCancelled(true);
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		M.doAll((Player) arg0, Material.PORTAL, 1, this.getClass()
				.getSimpleName(), arg0, true);
		M.mage.add(arg0.getName());
		return false;
	}
}
