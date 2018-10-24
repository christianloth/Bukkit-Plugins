package us.simplekits.kits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Batman implements Listener {

	Main main;

	public Batman(Main instance) {
		main = instance;
	}

	@EventHandler
	void PlayerInteract(final PlayerInteractEvent event) {
		int blockId = event.getPlayer().getItemInHand().getType().getId();

		if (event.getAction() == Action.RIGHT_CLICK_AIR && blockId == 318
				|| event.getAction() == Action.RIGHT_CLICK_BLOCK
				&& blockId == 318) {

			if (main.coolDown.contains(event.getPlayer().getName() + "Batman")) {
				event.getPlayer().sendMessage(
						ChatColor.RED + "You are still in your cooldown!");
				return;
			}

			event.getPlayer().sendMessage(
					ChatColor.BLACK + "You are now flying!!" + ChatColor.GOLD
							+ " For "
							+ main.getConfig().getInt("Flint.Flight_Time")
							+ " sec.");
			
			Bukkit.broadcastMessage(ChatColor.GRAY + event.getPlayer().getName()
					+ " is fly enabled with kit batman. He is not fly-hacking.");
			event.getPlayer().setAllowFlight(true);
			event.getPlayer().setFlying(true);

			main.getServer().getScheduler()
					.scheduleSyncDelayedTask(main, new Runnable() {
						@Override
						public void run() {
							event.getPlayer().sendMessage( ChatColor.DARK_PURPLE +
									"You are no longer flying! Please wait "
											+ ChatColor.GOLD
											+ main.getConfig().getInt(
													"Flint.CoolDown")
											+ ChatColor.WHITE
											+ ChatColor.DARK_PURPLE + " sec. to fly again");

							event.getPlayer().setFlying(false);
							event.getPlayer().setAllowFlight(false);
						}
					}, main.getConfig().getInt("Flint.Flight_Time") * 20);

			main.iniciarCoolDown(event.getPlayer().getName() + "Batman", main
					.getConfig().getInt("Flint.CoolDown")
					+ main.getConfig().getInt("Flint.Flight_Time"));

		}
	}
}