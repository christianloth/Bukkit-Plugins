package us.simplekits.hgkits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import us.simplekits.kits.Main;

public class hgPhantom implements Listener, CommandExecutor {

	Main main;

	public hgPhantom(Main i) {
		main = i;
	}

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2,
			String[] arg3) {
		M.doAll((Player) s, Material.WOOD_HOE, 1, this.getClass()
				.getSimpleName(), s, true);
		M.phantom.add(s.getName());
		return false;
	}

	@EventHandler
	void PlayerInteract(final PlayerInteractEvent event) {

		if (event.getAction() == Action.RIGHT_CLICK_AIR
				&& event.getPlayer().getInventory().getItemInHand().getType() == Material.WOOD_HOE
				|| event.getAction() == Action.RIGHT_CLICK_BLOCK
				&& event.getPlayer().getInventory().getItemInHand().getType() == Material.WOOD_HOE) {
			if (!M.phantom.contains(event.getPlayer().getName())) {
				return;
			}

			if (main.coolDown.contains(event.getPlayer().getName()
					+ this.getClass().getSimpleName())) {
				event.getPlayer().sendMessage(
						ChatColor.RED + "You are still in your cooldown!");
				return;
			}

			event.getPlayer().sendMessage(
					ChatColor.GOLD + "You are now flying!!" + ChatColor.GOLD
							+ " For " + " 10 sec.");

			Bukkit.broadcastMessage(ChatColor.GRAY
					+ event.getPlayer().getName()
					+ " is fly enabled with hgPhantom. He is not fly-hacking.");
			event.getPlayer().setAllowFlight(true);
			event.getPlayer().setFlying(true);

			main.getServer().getScheduler()
					.scheduleSyncDelayedTask(main, new Runnable() {
						@Override
						public void run() {
							event.getPlayer()
									.sendMessage(
											ChatColor.DARK_PURPLE
													+ "You are no longer flying! Please wait "
													+ ChatColor.GOLD
													+ main.getConfig().getInt(
															"Flint.CoolDown")
													+ ChatColor.WHITE
													+ ChatColor.DARK_PURPLE
													+ " sec. to fly again");

							event.getPlayer().setFlying(false);
							event.getPlayer().setAllowFlight(false);
						}
					}, 200);

			main.iniciarCoolDown(event.getPlayer().getName()
					+ this.getClass().getSimpleName(), 40);

		}
	}

}
