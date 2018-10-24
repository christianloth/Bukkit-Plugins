package us.simplekits.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;


public class Kangaroo implements Listener {
	Main main;

	public Kangaroo(Main instance) {
		this.main = instance;
	}
	
	

	@EventHandler(priority = EventPriority.HIGHEST)
	void kangroo(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if ((event.getAction() == Action.RIGHT_CLICK_BLOCK)
				|| (event.getAction() == Action.RIGHT_CLICK_AIR)
				|| (event.getAction() == Action.LEFT_CLICK_BLOCK)
				|| (event.getAction() == Action.LEFT_CLICK_AIR)) {
			if (this.main.coolDown.contains(player.getName() + "Kangaroo")) {
				return;
			}
			if (player.getItemInHand().getType() == Material.FIREWORK) {
				if (!this.main.noFallList.contains(player.getName())) {
					this.main.noFallList.add(player.getName());
				}
				this.main.getServer().getScheduler()
						.scheduleSyncDelayedTask(this.main, new Runnable() {
							public void run() {
								Kangaroo.this.main.noFallList.remove(player
										.getName());
							}
						}, 40L);

				player.setVelocity(new Vector(player.getLocation()
						.getDirection().getX()
						* this.main.getConfig().getDouble(
								"Firework.Horizontal_Impulse"), player
						.getLocation().getDirection().getY()
						* this.main.getConfig().getDouble(
								"Firework.Vertical_Impulse"), player
						.getLocation().getDirection().getZ()
						* this.main.getConfig().getDouble(
								"Firework.Horizontal_Impulse")));

				this.main.CoolDownDouble(player.getName() + "Kangaroo", 1.5D);
			}
		}
	}

	@EventHandler
	void groundBypass(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if ((player.getItemInHand().getType() == Material.FIREWORK)
				&& ((event.getAction() == Action.RIGHT_CLICK_BLOCK)
						|| (event.getAction() == Action.RIGHT_CLICK_AIR)
						|| (event.getAction() == Action.LEFT_CLICK_BLOCK) || (event
						.getAction() == Action.LEFT_CLICK_AIR)))
			event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	void noKangrooFallDamage(EntityDamageEvent event) {
		if (((event.getEntity() instanceof Player))
				&& (event.getCause().equals(EntityDamageEvent.DamageCause.FALL))) {
			if (this.main.noFallList.contains(((Player) event.getEntity())
					.getName())) {
				event.setCancelled(true);
				this.main.noFallList.remove(((Player) event.getEntity())
						.getName());
			}
		}
	}
}