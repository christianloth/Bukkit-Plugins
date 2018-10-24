package us.simplekits.kits;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;


class Earthquake implements Listener {
	Main main;

	public Earthquake(Main instance) {
		main = instance;
	}

	// ============================================= Empuje
	@EventHandler
	void damage(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof LivingEntity))
			return;
		LivingEntity víctima = (LivingEntity) e.getEntity();
		Entity atacante = null;

		// Ataques cuerpo a cuerpo
		if (e.getCause() == DamageCause.ENTITY_ATTACK) {
			atacante = e.getDamager();
			if (!(atacante instanceof Player))
				return;
			Player a = (Player) atacante;

			ItemStack is = a.getItemInHand();
			if (is == null)
				return;
			if (is.getType() != Material.MAGMA_CREAM)
				return;
			e.setCancelled(true);

			Vector unidadVector = víctima.getLocation().toVector()
					.subtract(a.getLocation().toVector()).normalize();
			unidadVector.setY(main.getConfig().getDouble(
					"Magma_Cream.Vertical_Impulse"));
			víctima.setVelocity(unidadVector.multiply(main.getConfig()
					.getDouble("Magma_Cream.Horizontal_Impulse")));

		}
	}
}