package us.simplekits.misc;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import us.simplekits.kits.Main;

import com.iCo6.system.Accounts;

public class Gambling implements Listener, CommandExecutor {

	Main main;

	public Gambling(Main inst) {
		main = inst;
	}

	public Accounts ac = new Accounts();
	public static boolean session;
	HashMap<Player, Integer> gblAmounts = new HashMap<Player, Integer>();

	public double balance(Player p) {
		return ac.get(p.getName()).getHoldings().getBalance();
	}

	public int convertToInt(String s) {
		return Integer.parseInt(s);
	}

	public int totalPot() {
		int total = 0;
		for (int values : gblAmounts.values()) {
			total = values;
		}
		return total;
	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl,
			String[] args) {
		if (!(s instanceof Player)) {
			s.sendMessage(ChatColor.RED + "You can't bet as the console!");
			return true;
		}
		Player p = (Player) s;
		if (lbl.equalsIgnoreCase("beton")
				&& p.hasPermission("simplekits.bet.on")) {
			session = true;
			s.sendMessage(ChatColor.GREEN + "Gambling is now allowed!");
			return true;
		}
		if (lbl.equalsIgnoreCase("betoff")
				&& p.hasPermission("simplekits.bet.off")) {
			session = false;
			s.sendMessage(ChatColor.RED + "Gambling is no longer allowed!");
			return true;
		}

		if (lbl.equalsIgnoreCase("bet")) {
			if (args.length < 1) {
				p.sendMessage(ChatColor.RED
						+ "/bet <amount (must be greater than $300)>");

				return true;
			}
			if (!isInt(args[0])) {
				p.sendMessage(ChatColor.RED + "Please input a valid number!");
				return true;
			}
			if (convertToInt(args[0]) >= 300
					&& balance(p) >= convertToInt(args[0])
					&& !gblAmounts.containsKey(p)) { // Add iConomy
				// balance
				// condition
				p.sendMessage(ChatColor.GREEN + "You have successfully bet $"
						+ convertToInt(args[0]));
				if (!gblAmounts.containsKey(p)) {
					gblAmounts.put(p, convertToInt(args[0]));
				}
			} else if (Integer.parseInt(args[0]) < 300) {
				p.sendMessage(ChatColor.RED
						+ "/bet <amount (must be greater than $300)>");
			} else if (balance(p) < convertToInt(args[0])) {
				p.sendMessage(ChatColor.RED
						+ "You don't have enough money to bet this amount!");
			}
		}

		return false;
	}

	@EventHandler
	public void death(PlayerDeathEvent evt) {
		if (gblAmounts.containsKey(evt.getEntity())) {
			gblAmounts.remove(evt.getEntity());
		}
	}

	@EventHandler
	public void logout(PlayerQuitEvent evt) {
		if (gblAmounts.containsKey(evt.getPlayer())) {
			gblAmounts.remove(evt.getPlayer());
		}
	}
}
