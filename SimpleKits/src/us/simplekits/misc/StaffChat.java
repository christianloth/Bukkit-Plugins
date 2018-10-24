package us.simplekits.misc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.simplekits.kits.Main;

public class StaffChat implements CommandExecutor {

	Main main;

	public StaffChat(Main i) {
		main = i;
	}
	
	public boolean onCommand(CommandSender s, Command arg1, String arg2,
			String[] args) {
		if (!s.hasPermission("simplekits.staffchat.notify")) {
			return true;
		}
		if (args.length >= 1) {
			StringBuilder messageToAnnounce = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				messageToAnnounce.append(args[i]);
				messageToAnnounce.append(' ');
			}
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.hasPermission("simplekits.staffchat.notify")) {
					p.sendMessage("§7[§1Staff Chat§7] §9" + s.getName()
							+ "§7: " + messageToAnnounce.toString());
				}
			}
		} else {
			s.sendMessage(ChatColor.RED + "/staffchat <message>");
		}
		return false;
	}

}
