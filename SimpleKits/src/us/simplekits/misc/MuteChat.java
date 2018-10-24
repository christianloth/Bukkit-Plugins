package us.simplekits.misc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import us.simplekits.kits.Main;

public class MuteChat implements Listener, CommandExecutor {

	Main main;

	public MuteChat(Main main2) {
		main2 = main;
	}

	public static boolean muteChat;

	@EventHandler
	public void muteChat(AsyncPlayerChatEvent evt) {
		Player p = evt.getPlayer();
		if (muteChat) {
			p.sendMessage(ChatColor.RED
					+ "The chat has been temporarily muted!");
			evt.setCancelled(true);
			return;
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Must be a player!");
			return true;
		}
		if (!sender.hasPermission("simplekits.mutechat")) {
			sender.sendMessage(ChatColor.RED + "No permission.");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "/mutechat <on|off>");
			return true;
		}
		if (args[0].equalsIgnoreCase("on")) {
			muteChat = true;
			Bukkit.broadcastMessage(ChatColor.GOLD
					+ "The global chat has been muted by " + ChatColor.DARK_PURPLE
					+ sender.getName() + ChatColor.GOLD + "!");
			return true;
		}
		if (args[0].equalsIgnoreCase("off")) {
			muteChat = false;
			Bukkit.broadcastMessage(ChatColor.GOLD + "The chat has been unmuted!");
			return true;
		}
		return false;
	}
}
