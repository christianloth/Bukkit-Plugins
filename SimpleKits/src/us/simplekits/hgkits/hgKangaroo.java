package us.simplekits.hgkits;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.simplekits.kits.Main;

public class hgKangaroo implements CommandExecutor {

	Main main;

	public hgKangaroo(Main _SimpleKits) {
		_SimpleKits = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command c1v1, String lbl,
			String[] args) {
		M.doAll((Player) sender, Material.FIREWORK, 1, this.getClass()
				.getSimpleName(), sender, true);
		return false;
	}

}
