package us.simplekits.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class Wizard implements Listener {

	Main main;

	public Wizard(Main instance) {
		main = instance;
	}

	@EventHandler
	void wizard(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();

		if (event.getAction() == Action.RIGHT_CLICK_AIR
				|| event.getAction() == Action.RIGHT_CLICK_BLOCK) {

			if (item.getType() == Material.BOOK)
				player.openEnchanting(null, true);

		}
	}

	@EventHandler
	void playerDeath(PlayerDeathEvent event) {
		Player killer = event.getEntity().getKiller();

		if (!(killer instanceof Player)) {
			return;
		}
		if (killer.getInventory().contains(Material.BOOK)) {
			main.getServer().dispatchCommand(
					main.getServer().getConsoleSender(),
					"xp 1l " + killer.getName());
			killer.sendMessage(ChatColor.GOLD
					+ "You have been rewarded with 1 level of Xp!");
		}
	}
}
