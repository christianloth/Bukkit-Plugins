package us.simplekits.misc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.simplekits.kits.Main;

public class Donate implements CommandExecutor {

	Main main;

	public Donate(Main i) {
		main = i;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl,
			String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (lbl.equalsIgnoreCase("donate")) {
				p.sendMessage("§5*********§6Donate!§5*********");
				p.sendMessage("§5****§adonate.simplekits.us!§5****");
				p.sendMessage("§7Type §3/kitlist §7to see the kits that each rank gets!");
				p.sendMessage("§7Type §3/buy §7to buy any of these in-game!");
				p.sendMessage("§aYou also get in-game cash for each rank you buy!");
				p.sendMessage("/Special §7- For information about the rank §rSpecial§7!");
				p.sendMessage("§a/Donor §7- For information about the rank §aDonor§7!");
				p.sendMessage("§9/SuperDonor §7- For information about the rank §9SuperDonor§7!");
				p.sendMessage("§6/UltraDonor §7- For information about the rank §6UltraDonor§7!");
				p.sendMessage("§e/Police §7- For information about the rank §ePolice§7!");
				p.sendMessage("§5****§adonate.simplekits.us!§5****");
				return true;
			}
			if (lbl.equalsIgnoreCase("special")) {
				this.special(
						p,
						"15",
						"You get access to exclusive warps where the better players will be and the staff like t1v1 and bold name in chat with $10,000 Added to in-game balance. You also get one extra kit (rotates monthly).");

				return true;
			}
			if (lbl.equalsIgnoreCase("donor")) {
				this.send(
						"§aDonor",
						p,
						"5",
						"10",
						"20",
						"20,000",
						"40,000",
						"Green name in chat. You get most of the kits! Access to /enderchest. Access to /warp DonorShop. You get to join the server when it is full.");
				return true;
			}
			if (lbl.equalsIgnoreCase("superdonor")) {
				this.send(
						"§9SuperDonor",
						p,
						"10",
						"20",
						"40",
						"40,000",
						"60,000",
						"Blue name in chat. You get all abilities that Donor gets. You get to host events with /top and /broadcast. Get a custom PvP base (ask an Admin for it). ABUSE OF POWERS = DEMOTE");
				return true;
			}
			if (lbl.equalsIgnoreCase("ultradonor")) {
				this.send(
						"§6UltraDonor",
						p,
						"15",
						"30",
						"60",
						"60,000",
						"80,000",
						"Gold name in chat. All abilities that Super Donor and Donor have. You get to use /fly to host events! Able to test kits that are being created before released and receive every single kit including all hgkits. Able to /nick yourself a coustom nane of your very own! You also get an amazing Mega PvP base with 18 double chests, a full enchanter (up to 30 levels), and a potion brewer! ABUSE OF POWERS = DEMOTE.");
				return true;
			}
			if (lbl.equalsIgnoreCase("police")) {
				this.send(
						"§ePolice",
						p,
						"30",
						"60",
						"90",
						"50,000",
						"100,000",
						"150,000",
						"Yello name in chat. You get access to /kick to kick anyone who wants to FFA in the 1v1 arena. You also get every single thing that UltraDonor has plus, you can fly around spawn (without interacting with PvP)!! If you want to be like the staff, this is the rank for you. With this rank, you get to be like an admin's helper. You chance to become staff also dramastically increases by 60%! You can't go wrong with this AMAZING rank! ABUSE OF POWERS = DEMOTE <-- Do not abuse this rank!");
				return true;
			}
			if (lbl.equalsIgnoreCase("kitlist")) {
				p.sendMessage(ChatColor.GREEN
						+ "§oDonor Kits§r: Blaze, Cactus, Earthquake, Fireman, Grandpa, Snail, Thor, Viking, Viper, Witherman, Tornado, Freezer, Chef.");
				p.sendMessage(ChatColor.BLUE
						+ "§oSuperDonor Kits§r: Batman, Grappler, Gunman, Kangaroo, Tank, Timelord, Wizard, Bomber.");
				p.sendMessage(ChatColor.GOLD
						+ "§oUltraDonor Kits§r: EVERY SINGLE KIT AND HG KIT!");
				p.sendMessage(ChatColor.YELLOW
						+ "§oPolice Kits§r: EVERY SINGLE KIT AND HG KIT!");
			}
		}
		return false;
	}

	public void send(String rank, Player p, String price1m, String price2m,
			String priceLife, String money1m, String money2m, String moneylm,
			String details) {
		p.sendMessage("§7This rank is: " + rank);
		p.sendMessage("§7- §a$" + price1m + " §7for one month plus $" + money1m
				+ " added to your in-game cash!");
		p.sendMessage("§7- §a$" + price2m + " §7for two months plus $" + money2m
				+ " added to your in-game cash!");
		p.sendMessage("§7- §a$" + priceLife + " §7for life plus $" + moneylm
				+ " in-game cash!");
		p.sendMessage("§7- §3Get it and see more details at §adonate.simplekits.us");
		p.sendMessage("§7- §3You can also type §a/buy §3to get any of these in-game!");
		p.sendMessage(details);
	}

	public void send(String rank, Player p, String price1m, String price2m,
			String priceLife, String money2m, String moneylm, String details) {
		p.sendMessage("§7This rank is: " + rank);
		p.sendMessage("§7- §a$" + price1m + " §7for one month");
		p.sendMessage("§7- §a$" + price2m + " §7for two months plus $" + money2m
				+ " added to your in-game cash!");
		p.sendMessage("§7- §a$" + priceLife + " §7for life plus $" + moneylm
				+ " added to your in-game cash!");
		p.sendMessage("§7- §3Get it and see more details at §adonate.simplekits.us");
		p.sendMessage("§7- §3You can also type §a/buy §3to get any of these in-game!");
		p.sendMessage(details);
	}

	public void special(Player p, String priceLife, String details) {
		p.sendMessage("§7This rank is: §rSpecial");
		p.sendMessage("§7- §a$" + priceLife + " §7for life plus $10,000 in-game cash!");
		p.sendMessage("§7- §3Get it and see more details at §adonate.simplekits.us");
		p.sendMessage("§7- §3You can also type §a/buy §3to get any of these in-game!");
		p.sendMessage(details);
	}
}
