package us.simplekits.misc;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import us.simplekits.kits.Main;


public class BcMsg implements CommandExecutor {
	
	Main main;
	
	public BcMsg(Main instance) {
		main = instance; 
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender.isOp()) {
			String msg = "";
			for (int i = 0; i < args.length; i++)
				msg = msg + (i > 0 ? " " : "") + args[i];
			Bukkit.broadcastMessage(msg.replaceAll("&", "§"));
		}
		return false;
	}

}
