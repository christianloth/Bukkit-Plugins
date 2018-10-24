package us.simplekits.hgkits;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

import us.simplekits.kits.Main;

public class hgGrappler implements Listener, CommandExecutor {

	Main main;

	public hgGrappler(Main i) {
		main = i;
	}

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerFish(PlayerFishEvent event) {
		final Player player = event.getPlayer();

		if (!M.grappler.contains(player.getName())) {
			return;
		}
		if (event.getState().equals(PlayerFishEvent.State.IN_GROUND)) {

			Location fm = player.getLocation();
			Location to = event.getHook().getLocation();

			fm.setY(fm.getY() + 0.5D);
			player.teleport(fm);

			double g = -0.08D;
			double d = to.distance(fm);
			double t = d;
			double v_x = (1.0D + 0.07000000000000001D * t)
					* (to.getX() - fm.getX()) / t;
			double v_y = (1.0D + 0.03D * t) * (to.getY() - fm.getY()) / t
					- 0.5D * g * t;
			double v_z = (1.0D + 0.07000000000000001D * t)
					* (to.getZ() - fm.getZ()) / t;

			Vector v = player.getVelocity();
			v.setX(v_x);
			v.setY(v_y);
			v.setZ(v_z);
			player.setVelocity(v);

		} else if (event.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY)) {

			Location fm = player.getLocation();
			Location to = event.getHook().getLocation();

			fm.setY(fm.getY() + 0.5D);
			player.teleport(fm);

			double g = -0.08D;
			double d = to.distance(fm);
			double t = d;
			double v_x = (1.0D + 0.07000000000000001D * t)
					* (to.getX() - fm.getX()) / t;
			double v_y = (1.0D + 0.03D * t) * (to.getY() - fm.getY()) / t
					- 0.5D * g * t;
			double v_z = (1.0D + 0.07000000000000001D * t)
					* (to.getZ() - fm.getZ()) / t;

			Vector v = player.getVelocity();
			v.setX(v_x);
			v.setY(v_y);
			v.setZ(v_z);
			player.setVelocity(v);

		}
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		M.doAll((Player) arg0, Material.FISHING_ROD, 1, this.getClass()
				.getSimpleName(), arg0, true);
		M.grappler.add(arg0.getName());
		return false;
	}

}
