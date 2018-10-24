package us.simplekits.hgkits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import us.simplekits.kits.Main;

public class hgRhino implements Listener, CommandExecutor {

	Main main;
	
	public hgRhino(Main i) {
		main = i;
	}

	List<String> cooldown = new ArrayList<String>();

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2,
			String[] arg3) {
		M.doAll((Player) s, Material.MUSHROOM_SOUP, 1, this.getClass()
				.getSimpleName(), s, true);
		M.rhino.add(s.getName());
		return false;
	}

	@EventHandler
	public void bla(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.isSneaking() && M.rhino.contains(p.getName())
				&& !cooldown.contains(p.getName())) {
			
		}
	}
}
