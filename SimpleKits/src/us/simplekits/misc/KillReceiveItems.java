package us.simplekits.misc;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import us.simplekits.kits.Main;

public class KillReceiveItems implements Listener {

	Main main;

	public KillReceiveItems(Main instance) {
		main = instance;
	}
	
	@EventHandler
	public void leatherKill(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Player killer = player.getKiller();

		if (player.getKiller() instanceof Player
				&& killer.getInventory().contains(Material.LEATHER)) {

			PlayerInventory playerInv = killer.getInventory();
			
			for (int i = 0; i < 8; i++) {
			playerInv.addItem(new ItemStack(Material.MUSHROOM_SOUP, 1));
			}
			
			killer.sendMessage(ChatColor.DARK_PURPLE
					+ "You have been awarded with 8 soup!");
		}

		if (player.getKiller() instanceof Player
				&& killer.getInventory().contains(372)) {
			killer.sendMessage(ChatColor.DARK_PURPLE
					+ "You have been given Strength II and Nausea for 10 sec!");
			killer.addPotionEffect(new PotionEffect(
					PotionEffectType.INCREASE_DAMAGE, 200, 1));
			killer.addPotionEffect(new PotionEffect(
					PotionEffectType.CONFUSION, 200, 0));
		}
	}
}