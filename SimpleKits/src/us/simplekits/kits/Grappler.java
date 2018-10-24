package us.simplekits.kits;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;


public class Grappler implements Listener {

	Main main;

	public Grappler(Main instance) {
		main = instance;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerFish(PlayerFishEvent event) {
		Player player = event.getPlayer();
		if (!player.getInventory().contains(Material.NAME_TAG)) {
			return;
		}
		if (this.main.coolDown.contains(player.getName() + "Grappler")) {
			player.sendMessage(ChatColor.RED
					+ "You are still in your cooldown!");
			event.setCancelled(true);
			return;
		}

		if (event.getState().equals(PlayerFishEvent.State.IN_GROUND)) {
			Location from = player.getLocation();
			Location to = event.getHook().getLocation();

			from.setY(from.getY() + 0.5D);
			player.teleport(from);

			double g = -0.08D;
			double d = to.distance(from);
			double t = d;
			double velocity_x = (1.0D + 0.07000000000000001D * t)
					* (to.getX() - from.getX()) / t;
			double velocity_y = (1.0D + 0.03D * t) * (to.getY() - from.getY())
					/ t - 0.5D * g * t;
			double velocity_z = (1.0D + 0.07000000000000001D * t)
					* (to.getZ() - from.getZ()) / t;

			Vector playerVelocity = player.getVelocity();

			playerVelocity.setX(velocity_x);
			playerVelocity.setY(velocity_y);
			playerVelocity.setZ(velocity_z);

			player.setVelocity(playerVelocity);

			this.main.iniciarCoolDown(player.getName() + "Grappler", 1);
		} else if (event.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY)) {
			Location fm = player.getLocation();
			Location to = event.getHook().getLocation();

			fm.setY(fm.getY() + 0.5D);
			player.teleport(fm);

			double g = -0.08D;
			double d = to.distance(fm);
			double t = d;
			double velocity_x = (32.0D + 2147483647.0D * t)
					* (to.getX() - fm.getX()) / t;
			double velocity_y = (32.0D + 0.03D * t) * (to.getY() - fm.getY())
					/ t - 0.5D * g * t;
			double velocity_z = (32.0D + 2147483647.0D * t)
					* (to.getZ() - fm.getZ()) / t;

			Vector playerVelocity = player.getVelocity();

			playerVelocity.setX(velocity_x);
			playerVelocity.setY(velocity_y);
			playerVelocity.setZ(velocity_z);

			player.setVelocity(playerVelocity);

			this.main.iniciarCoolDown(player.getName() + "Grappler", this.main
					.getConfig().getInt("Grappler.CoolDown"));
		}
	}
}

/*
 * @EventHandler void leadFish(PlayerInteractEvent event) { Player player =
 * (Player) event.getPlayer(); // Entity hook =
 * player.getWorld().spawnEntity(player.getEyeLocation(),
 * EntityType.FISHING_HOOK);
 * 
 * if (player.getItemInHand().getType().getId() == 420) { if (event.getAction()
 * == Action.RIGHT_CLICK_AIR) {
 * 
 * //What happens when the lead is thrown.
 * 
 * /* hook.setVelocity(new Vector(player.getLocation() .getDirection().getX() 2,
 * player .getLocation().getDirection().getY() 2, player
 * .getLocation().getDirection().getZ() 2));
 */

// }
// }
// }
