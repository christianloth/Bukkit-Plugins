package us.simplekits.hgkits;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import us.simplekits.kits.Main;

public class hgArcher implements CommandExecutor {

	Main main;

	public hgArcher(Main i) {
		main = i;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {
		Player p = (Player) sender;
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command.");
			return true;
		}
		if (p.hasPermission("simplekits.kits."
				+ this.getClass().getSimpleName())) {
			if (com.faris.kingkits.hooks.PvPKits.hasKit(p, false) && !p.isOp()) {
				p.sendMessage(ChatColor.GOLD + "You have already chosen a kit!");
				return true;
			}
			p.getInventory().clear();
			for (PotionEffect effect : p.getActivePotionEffects()) {
				p.removePotionEffect(effect.getType());
			}
			p.getInventory().setHelmet(null);
			p.getInventory().setChestplate(null);
			p.getInventory().setLeggings(null);
			p.getInventory().setBoots(null);

			p.setGameMode(GameMode.SURVIVAL);

			M.giveSSword(p);
			ItemStack bow = new ItemStack(Material.BOW);
			bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
			bow.addEnchantment(Enchantment.DURABILITY, 3);
			p.getInventory().addItem(bow);
			M.giveSoup(p, 30);
			M.giveMisc(p, Material.ARROW, 1);
			M.giveMisc(p, Material.RED_MUSHROOM, 32);
			M.giveMisc(p, Material.BROWN_MUSHROOM, 32);
			M.giveMisc(p, Material.BOWL, 32);
			p.sendMessage(ChatColor.GOLD + "You have obtained kit "
					+ ChatColor.DARK_PURPLE + this.getClass().getSimpleName() + ChatColor.GOLD + ".");
			p.teleport(new Location(p.getWorld(), 3559.5D, 71.0D, -4277.5D,
					90.0F, 0.0F));

		} else {
			p.sendMessage(ChatColor.RED
					+ "You do not have permission to use this kit.");
		}

		return false;
	}

}
