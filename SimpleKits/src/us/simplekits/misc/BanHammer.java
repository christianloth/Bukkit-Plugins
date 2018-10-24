package us.simplekits.misc;

import mkremins.fanciful.FancyMessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import us.simplekits.kits.Main;

public class BanHammer implements Listener, CommandExecutor {

	Main main;

	public BanHammer(Main i) {
		main = i;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onLogin(PlayerLoginEvent event) {
		Player p = event.getPlayer();
		if (p.isBanned()) {
			FancyMessage message = new FancyMessage("Hi");
			event.setKickMessage(message.itemTooltip(message.toString()).toString());
			//event.setKickMessage("§cYour account has been suspended from SimpleKits. Please buy an unban at §6donate.simplekits.us§r");

		}
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		if ((cmd.getName().equalsIgnoreCase("ban"))
				&& (sender.hasPermission("simplekits.ban"))) {
			String reason = "";
			for (int i = 1; i < args.length; i++)
				reason = reason + (i > 1 ? ' ' : "") + args[i];
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Please specify a player!");
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null) {
				OfflinePlayer target1 = Bukkit.getOfflinePlayer(args[0]);
				target1.setBanned(true);
				Bukkit.getServer().broadcastMessage(
						ChatColor.GRAY + target1.getName() + ChatColor.GRAY
								+ " has been banned!");
				return true;
			}

			target.kickPlayer(ChatColor.RED + sender.getName()
					+ " has banned you for " + reason
					+ ". Please buy an unban at " + ChatColor.GOLD
					+ "donate.simplekits.us§r");
			target.setBanned(true);
			Bukkit.getServer().broadcastMessage(
					ChatColor.GRAY + target.getName() + ChatColor.GRAY
							+ " has been banned!");
		}

		if ((cmd.getName().equalsIgnoreCase("kick"))
				&& (sender.hasPermission("simplekits.kick"))) {
			String reason = "";
			for (int i = 1; i < args.length; i++)
				reason = reason + (i > 1 ? ' ' : "") + args[i];
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Please specify a player!");
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage(ChatColor.RED + "Could not find player "
						+ args[0] + "!");
				return true;
			}
			target.kickPlayer(ChatColor.RED + sender.getName()
					+ " kicked you for " + reason + "§r");
			Bukkit.getServer().broadcastMessage(
					ChatColor.GRAY + target.getName() + ChatColor.GRAY
							+ " has been kicked!");
		}

		if ((cmd.getName().equalsIgnoreCase("unban") || cmd.getName()
				.equalsIgnoreCase("pardon"))
				&& (sender.hasPermission("simplekits.pardon"))) {
			String reason = "";
			for (int i = 1; i < args.length; i++)
				reason = reason + (i > 1 ? ' ' : "") + args[i];
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Please specify a player!");
				return true;
			}
			OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
			target.setBanned(false);
			Bukkit.getServer().broadcastMessage(
					ChatColor.GRAY + target.getName() + " has been pardoned.");
		}
		if ((cmd.getName().equalsIgnoreCase("banip"))) {

		}

		return false;
	}

}
