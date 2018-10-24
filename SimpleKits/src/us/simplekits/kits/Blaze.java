package us.simplekits.kits;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

class Blaze implements Listener {
	Main main;

	public Blaze(Main instance) {
		main = instance;
	}

	// ============================================= Huevos Explosivos
	@EventHandler(priority = EventPriority.HIGHEST)
	void lanzarHuevoExplosivo(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK
				&& e.getAction() != Action.RIGHT_CLICK_AIR)
			return;
		Player j = e.getPlayer();

		ItemStack mano = j.getItemInHand();
		if (mano == null)
			return;
		if (mano.getType() != Material.BLAZE_POWDER)
			return;
		if (main.coolDown.contains(j.getName() + "Blaze"))
			return;

		Projectile pro = j.launchProjectile(Fireball.class);
		pro.setVelocity(pro.getVelocity().multiply(
				pro.getVelocity().length()
						* main.getConfig().getInt("Blaze.Projectile_Speed")
						/ 100));
		main.iniciarCoolDown(j.getName() + "Blaze", 1);
	}
}