package us.simplekits.hgkits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;

import us.simplekits.kits.Main;

public class M implements Listener {
	
	static List<String> hgkit = new ArrayList<String>();

	static List<String> stomper = new ArrayList<String>();
	static List<String> rhino = new ArrayList<String>();
	static List<String> turtle = new ArrayList<String>();
	static List<String> switcher = new ArrayList<String>();
	static List<String> anchor = new ArrayList<String>();
	static List<String> mage = new ArrayList<String>();
	static List<String> fisherman = new ArrayList<String>();
	public static List<String> grappler = new ArrayList<String>();
	static List<String> phantom = new ArrayList<String>();

	@SuppressWarnings("rawtypes")
	List<ArrayList> all = new ArrayList<ArrayList>();

	@EventHandler
	public void rm(PlayerQuitEvent evt) {
		if (hgkit.contains(evt.getPlayer().getName())) {
			hgkit.remove(evt.getPlayer().getName());
		}
		if (stomper.contains(evt.getPlayer().getName())) {
			stomper.remove(evt.getPlayer().getName());
		}
		if (rhino.contains(evt.getPlayer().getName())) {
			rhino.remove(evt.getPlayer().getName());
		}
		if (turtle.contains(evt.getPlayer().getName())) {
			turtle.remove(evt.getPlayer().getName());
		}
		if (switcher.contains(evt.getPlayer().getName())) {
			switcher.remove(evt.getPlayer().getName());
		}
		if (anchor.contains(evt.getPlayer().getName())) {
			anchor.remove(evt.getPlayer().getName());
		}
		if (mage.contains(evt.getPlayer().getName())) {
			mage.remove(evt.getPlayer().getName());
		}
		if (fisherman.contains(evt.getPlayer().getName())) {
			fisherman.remove(evt.getPlayer().getName());
		}
		if (grappler.contains(evt.getPlayer().getName())) {
			grappler.remove(evt.getPlayer().getName());
		}
		if (phantom.contains(evt.getPlayer().getName())) {
			phantom.remove(evt.getPlayer().getName());
		}

	}

	@EventHandler
	public void rm2(PlayerDeathEvent evt) {
		if (hgkit.contains(evt.getEntity().getName())) {
			hgkit.remove(evt.getEntity().getName());
		}
		if (stomper.contains(evt.getEntity().getName())) {
			stomper.remove(evt.getEntity().getName());
		}
		if (rhino.contains(evt.getEntity().getName())) {
			rhino.remove(evt.getEntity().getName());
		}
		if (turtle.contains(evt.getEntity().getName())) {
			turtle.remove(evt.getEntity().getName());
		}
		if (switcher.contains(evt.getEntity().getName())) {
			switcher.remove(evt.getEntity().getName());
		}
		if (anchor.contains(evt.getEntity().getName())) {
			anchor.remove(evt.getEntity().getName());
		}
		if (mage.contains(evt.getEntity().getName())) {
			mage.remove(evt.getEntity().getName());
		}
		if (fisherman.contains(evt.getEntity().getName())) {
			fisherman.remove(evt.getEntity().getName());
		}
		if (grappler.contains(evt.getEntity().getName())) {
			grappler.remove(evt.getEntity().getName());
		}
		if (phantom.contains(evt.getEntity().getName())) {
			phantom.remove(evt.getEntity().getName());
		}
	}

	Main main;

	public M(Main _SimpleKits) {
		_SimpleKits = main;
	}

	public static void giveSoup(Player p, int amount) {
		for (int i = 0; i < amount; i++) {
			p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));

		}
	}

	public static void giveSSword(Player p) {
		ItemStack stoneSword = new ItemStack(Material.WOOD_SWORD);
		stoneSword.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		p.getInventory().addItem(stoneSword);

	}

	public static void giveMisc(Player p, Material item, int amount) {
		p.getInventory().addItem(new ItemStack(item, amount));
	}

	public static void giveArmor(Player p, Material helmet,
			Material chestplate, Material leggings, Material boots) {
		p.getInventory().setHelmet(new ItemStack(helmet));
		p.getInventory().setLeggings(new ItemStack(chestplate));
		p.getInventory().setChestplate(new ItemStack(leggings));
		p.getInventory().setBoots(new ItemStack(boots));
	}

	public static void clear(Player p) {
		p.getInventory().clear();
	}

	public static void msg(Player p, String kitName) {
		p.sendMessage(ChatColor.GOLD + "You have obtained kit "
				+ ChatColor.DARK_PURPLE + kitName + ChatColor.GOLD + ".");
	}

	public static void colorLeather(Player p, int color, boolean chestplate,
			boolean leggings) {

		if (chestplate) {
			ItemStack lChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			lChestplate.addEnchantment(Enchantment.DURABILITY, 3);
			LeatherArmorMeta lc = (LeatherArmorMeta) lChestplate.getItemMeta();
			lc.setColor(Color.fromRGB(color));
			lChestplate.setItemMeta(lc);
			p.getInventory().setChestplate(lChestplate);
		}
		if (leggings) {
			ItemStack lLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
			lLeggings.addEnchantment(Enchantment.DURABILITY, 3);
			LeatherArmorMeta ll = (LeatherArmorMeta) lLeggings.getItemMeta();
			ll.setColor(Color.fromRGB(color));
			lLeggings.setItemMeta(ll);
			p.getInventory().setLeggings(lLeggings);
		}
	}

	public static void doAll(Player p, Material material2Slot, int amount,
			String kitName, CommandSender sender, boolean soup) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command.");
			return;
		}
		if (p.hasPermission("simplekits.kits." + kitName)) {
			if (com.faris.kingkits.hooks.PvPKits.hasKit(p, false) && !p.isOp()) {
				p.sendMessage(ChatColor.GOLD + "You have already chosen a kit!");
				return;
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

			giveSSword(p);
			giveMisc(p, material2Slot, amount);
			if (soup) {
				giveSoup(p, 31);
			}
			giveMisc(p, Material.RED_MUSHROOM, 32);
			giveMisc(p, Material.BROWN_MUSHROOM, 32);
			giveMisc(p, Material.BOWL, 32);
			p.sendMessage(ChatColor.GOLD + "You have obtained kit "
					+ ChatColor.DARK_PURPLE + kitName + ChatColor.GOLD + ".");
			p.teleport(new Location(p.getWorld(), 3559.5D, 71.0D, -4277.5D,
					90.0F, 0.0F));

		} else {
			p.sendMessage(ChatColor.RED
					+ "You do not have permission to use this kit.");
		}
	}

	public static void doAll(Player p, String kitName, CommandSender sender,
			boolean soup) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command.");
			return;
		}
		if (p.hasPermission("simplekits.kits." + kitName)) {
			if (com.faris.kingkits.hooks.PvPKits.hasKit(p, false) && !p.isOp()) {
				p.sendMessage(ChatColor.GOLD + "You have already chosen a kit!");
				return;
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

			giveSSword(p);
			if (soup) {
				giveSoup(p, 31);
			}
			giveMisc(p, Material.RED_MUSHROOM, 32);
			giveMisc(p, Material.BROWN_MUSHROOM, 32);
			giveMisc(p, Material.BOWL, 32);
			p.sendMessage(ChatColor.GOLD + "You have obtained kit "
					+ ChatColor.DARK_PURPLE + kitName + ChatColor.GOLD + ".");
			p.teleport(new Location(p.getWorld(), 3559.5D, 71.0D, -4277.5D,
					90.0F, 0.0F));

		} else {
			p.sendMessage(ChatColor.RED
					+ "You do not have permission to use this kit.");
		}
	}
}