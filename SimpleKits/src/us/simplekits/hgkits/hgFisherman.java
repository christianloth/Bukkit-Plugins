package us.simplekits.hgkits;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import us.simplekits.kits.Main;

import com.faris.kingkits.hooks.PvPKits;

public class hgFisherman implements CommandExecutor, Listener {
	Main main;

	public hgFisherman(Main i) {
		this.main = i;
	}

	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] args) {
		M.doAll((Player) sender, Material.FISHING_ROD, 1, getClass()
				.getSimpleName(), sender, true);
		M.fisherman.add(sender.getName());
		return false;
	}

	@EventHandler
	public void playerFish(PlayerFishEvent e) {
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		if ((e.getCaught() instanceof Player)) {
			Player c = (Player) e.getCaught();
			if (((M.fisherman.contains(p.getName())) || (PvPKits.getKit(p) == "Fisherman"))
					&& (p.getItemInHand().getType() == Material.FISHING_ROD)) {
				c.sendMessage(ChatColor.RED
						+ "You have been caught by a fisherman!");
				c.teleport(loc);
			}
		}
	}
}