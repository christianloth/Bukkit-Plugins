package us.simplekits.hgkits;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.simplekits.kits.Main;

public class hgEarly implements CommandExecutor {

	Main main;

	public hgEarly(Main instance) {
		main = instance;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl,
			String[] args) {
		M.doAll((Player) s, Material.MUSHROOM_SOUP, 1, this.getClass()
				.getSimpleName(), s, true);
		return false;
	}
}
