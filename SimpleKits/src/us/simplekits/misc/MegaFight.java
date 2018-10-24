package us.simplekits.misc;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MegaFight {
	
	public static ItemStack ds = new ItemStack(Material.DIAMOND_SWORD, 1);

	public static ItemStack bow = new ItemStack(Material.BOW, 1);

	public static ItemMeta bowMeta = bow.getItemMeta();
	{
		bowMeta.setDisplayName(ChatColor.GREEN + "OP!");
		bowMeta.setLore(Arrays.asList(new String[] { "OP Bow!" }));
		bow.setItemMeta(bowMeta);

		bow.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
		bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
	}

	public static ItemMeta dsMeta = ds.getItemMeta();
	{
		dsMeta.setDisplayName(ChatColor.GREEN + "OP!");
		dsMeta.setLore(Arrays.asList(new String[] { "OP Sword!" }));
		ds.setItemMeta(dsMeta);

		ds.addEnchantment(Enchantment.DAMAGE_ALL, 5);

	}
}
