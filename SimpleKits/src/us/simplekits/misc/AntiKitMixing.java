package us.simplekits.misc;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;

import us.simplekits.kits.Main;

public class AntiKitMixing implements Listener {

	Main main;

	public AntiKitMixing(Main instance) {
		main = instance;
	}

	@EventHandler
	public void openRemove(InventoryOpenEvent e) {
		Inventory inv = e.getInventory();

		if ((inv.getHolder() instanceof Chest || inv.getHolder() instanceof DoubleChest)
				&& !e.getPlayer().isOp()) {
			if (inv.contains(Material.FLINT) || inv.contains(Material.SULPHUR)
					|| inv.contains(Material.BLAZE_POWDER)
					|| inv.contains(Material.NAME_TAG)
					|| inv.contains(Material.SUGAR)
					|| inv.contains(Material.REDSTONE)
					|| inv.contains(Material.COAL)
					|| inv.contains(Material.MAGMA_CREAM)
					|| inv.contains(Material.FIREWORK)
					|| inv.contains(Material.EGG)
					|| inv.contains(Material.SLIME_BALL)
					|| inv.contains(Material.STICK)
					|| inv.contains(Material.REDSTONE_TORCH_OFF)
					|| inv.contains(Material.REDSTONE_TORCH_ON)
					|| inv.contains(Material.LEASH)
					|| inv.contains(Material.ANVIL)
					|| inv.contains(Material.WATCH)
					|| inv.contains(Material.BOOK) || inv.contains(90)
					|| inv.contains(397)) {

				inv.remove(Material.FLINT);
				inv.remove(Material.NAME_TAG);
				inv.remove(Material.BLAZE_POWDER);
				inv.remove(Material.SUGAR);
				inv.remove(Material.REDSTONE);
				inv.remove(Material.COAL);
				inv.remove(Material.MAGMA_CREAM);
				inv.remove(Material.FIREWORK);
				inv.remove(Material.EGG);
				inv.remove(Material.SLIME_BALL);
				inv.remove(Material.STICK);
				inv.remove(Material.REDSTONE_TORCH_OFF);
				inv.remove(Material.REDSTONE_TORCH_ON);
				inv.remove(Material.LEASH);
				inv.remove(Material.ANVIL);
				inv.remove(Material.WATCH);
				inv.remove(Material.WATCH);
				inv.remove(90);
				inv.remove(397);

			}
		}
	}

	@EventHandler
	public void closeRemove(InventoryCloseEvent e) {
		Inventory inv = e.getInventory();

		if ((inv.getHolder() instanceof Chest || inv.getHolder() instanceof DoubleChest)
				&& !e.getPlayer().isOp()) {
			if (inv.contains(Material.FLINT) || inv.contains(Material.SULPHUR)
					|| inv.contains(Material.BLAZE_POWDER)
					|| inv.contains(Material.SUGAR)
					|| inv.contains(Material.NAME_TAG)
					|| inv.contains(Material.REDSTONE)
					|| inv.contains(Material.COAL)
					|| inv.contains(Material.MAGMA_CREAM)
					|| inv.contains(Material.FIREWORK)
					|| inv.contains(Material.EGG)
					|| inv.contains(Material.SLIME_BALL)
					|| inv.contains(Material.STICK)
					|| inv.contains(Material.REDSTONE_TORCH_OFF)
					|| inv.contains(Material.REDSTONE_TORCH_ON)
					|| inv.contains(Material.LEASH)
					|| inv.contains(Material.ANVIL)
					|| inv.contains(Material.WATCH)
					|| inv.contains(Material.WATCH) || inv.contains(90)
					|| inv.contains(397)) {

				inv.remove(Material.FLINT);
				inv.remove(Material.NAME_TAG);
				inv.remove(Material.BLAZE_POWDER);
				inv.remove(Material.SUGAR);
				inv.remove(Material.REDSTONE);
				inv.remove(Material.COAL);
				inv.remove(Material.MAGMA_CREAM);
				inv.remove(Material.FIREWORK);
				inv.remove(Material.EGG);
				inv.remove(Material.SLIME_BALL);
				inv.remove(Material.STICK);
				inv.remove(Material.REDSTONE_TORCH_OFF);
				inv.remove(Material.REDSTONE_TORCH_ON);
				inv.remove(Material.LEASH);
				inv.remove(Material.ANVIL);
				inv.remove(Material.BOOK);
				inv.remove(Material.WATCH);
				inv.remove(90);
				inv.remove(397);
			}
		}
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {

		Material itemType = e.getItemDrop().getItemStack().getType();
		if ((itemType == Material.FLINT || itemType == Material.SULPHUR
				|| itemType == Material.WATCH
				|| itemType == Material.NAME_TAG
				|| itemType == Material.BLAZE_POWDER
				|| itemType == Material.SUGAR || itemType == Material.REDSTONE
				|| itemType == Material.COAL
				|| itemType == Material.MAGMA_CREAM
				|| itemType == Material.FEATHER
				|| itemType == Material.FIREWORK || itemType == Material.EGG
				|| itemType == Material.SLIME_BALL
				|| itemType == Material.STICK || itemType == Material.BOOK
				|| itemType == Material.REDSTONE_TORCH_OFF
				|| itemType == Material.FISHING_ROD
				|| itemType == Material.REDSTONE_TORCH_ON || itemType == Material.LEASH)
				|| itemType.getId() == 397
				|| itemType == Material.ANVIL
				|| itemType.getId() == 90) {
			e.setCancelled(true);
		}
	}
}
