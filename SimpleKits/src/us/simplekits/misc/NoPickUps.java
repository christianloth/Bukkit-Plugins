package us.simplekits.misc;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import us.simplekits.kits.Main;

public class NoPickUps implements Listener {

	Main main;

	public NoPickUps(Main instance) {
		main = instance;
	}

	@EventHandler
	void BowlPickUp(PlayerPickupItemEvent event) {
		ItemStack item = event.getItem().getItemStack();
		Material itemType = item.getType();

		if ((itemType == Material.BOWL || itemType == Material.FLINT
				|| itemType == Material.SULPHUR
				|| itemType == Material.BLAZE_POWDER
				|| itemType == Material.SUGAR || itemType == Material.REDSTONE
				|| itemType == Material.COAL
				|| itemType == Material.MAGMA_CREAM
				|| itemType == Material.FEATHER
				|| itemType == Material.FIREWORK || itemType == Material.EGG
				|| itemType == Material.SLIME_BALL
				|| itemType == Material.STICK
				|| itemType == Material.REDSTONE_TORCH_OFF
				|| itemType == Material.FISHING_ROD
				|| itemType == Material.WATCH
				|| itemType == Material.BOOK
				|| itemType == Material.REDSTONE_TORCH_ON || itemType == Material.LEASH)
				|| itemType.getId() == 397 || itemType == Material.ANVIL || itemType.getId() == 90) {
			event.setCancelled(true);
		}
	}
}
