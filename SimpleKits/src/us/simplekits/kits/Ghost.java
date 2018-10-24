package us.simplekits.kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;



public class Ghost implements Listener {
	Main main;

	public Ghost(Main instance) {
		this.main = instance;
	}

	@EventHandler
	void Interact(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		Player target = null;
		ItemStack item = player.getItemInHand();

		if (((event.getAction() == Action.RIGHT_CLICK_AIR)
				|| (event.getAction() == Action.RIGHT_CLICK_BLOCK)
				|| (event.getAction() == Action.LEFT_CLICK_AIR) || (event
				.getAction() == Action.LEFT_CLICK_BLOCK))
				&& (item.getType() == Material.FEATHER)) {

			if (main.coolDown.contains(player.getName() + "Ghost")) {				
				return;
			}

			for (Player p : Bukkit.getOnlinePlayers()) {
				if ((player.getWorld() == p.getWorld())
						&& (player != p)
						&& ((target == null) || (player.getLocation().distance(
								p.getLocation()) < target.getLocation()
								.distance(player.getLocation())))) {
					target = p;
				}
			}
			player.teleport(target);
			
			player.sendMessage(ChatColor.GOLD + "A cooldown has begun of "
					+ main.getConfig().getInt("Ghost.CoolDown") + " sec.");
			main.iniciarCoolDown(player.getName() + "Ghost", main.getConfig().getInt("Ghost.CoolDown"));
		}
		
	}
}