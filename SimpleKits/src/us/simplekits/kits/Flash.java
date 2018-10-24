package us.simplekits.kits;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Flash implements Listener {

	Main main;

	public Flash(Main instance) {
		main = instance;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	void redstoneTorchClick(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		Block block = player.getTargetBlock(null, 50);
		double x = block.getLocation().getX();
		double y = block.getLocation().getY();
		double z = block.getLocation().getZ();
		ItemStack item = player.getItemInHand();

		if (event.getAction() == Action.RIGHT_CLICK_AIR
				|| event.getAction() == Action.RIGHT_CLICK_BLOCK
				|| event.getAction() == Action.LEFT_CLICK_AIR
				|| event.getAction() == Action.LEFT_CLICK_BLOCK) {

			if (item.getType() == Material.REDSTONE_TORCH_ON
					|| item.getType() == Material.REDSTONE_TORCH_OFF) {

				if (main.coolDown.contains(player.getName() + "Flash")) {
					event.getPlayer().sendMessage(
							ChatColor.RED + "You are still in your cooldown!");
					return;
				}

				Location targetLoc = new Location(player.getWorld(), x, y, z,
						player.getLocation().getYaw(), player.getLocation().getPitch());
				
				player.getWorld().playEffect(player.getLocation(),
						Effect.ENDER_SIGNAL, 1);
				player.teleport(targetLoc);
				
				player.getWorld().playEffect(player.getLocation(),
						Effect.ENDER_SIGNAL, 1);
				player.sendMessage(ChatColor.GOLD + "A cooldown has begun of "
						+ main.getConfig().getInt("Flash.CoolDown") + " sec.");
				
				main.iniciarCoolDown(player.getName() + "Flash", main
						.getConfig().getInt("Flash.CoolDown"));
						
			}
		}	

				/*
				  else { player.sendMessage(ChatColor.RED +
				  "You can not teleport inside of a block!");
				  }
				 */
			
	}

	@EventHandler
	void noPlaceRedstoneTorch(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();

		if (item.getType() == Material.REDSTONE_TORCH_ON
				|| item.getType() == Material.REDSTONE_TORCH_OFF) {
			if (!player.isOp())
				event.setCancelled(true);
		}
	}
}