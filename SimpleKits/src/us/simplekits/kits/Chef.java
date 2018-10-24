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

import com.faris.kingkits.hooks.PvPKits;

public class Chef implements Listener {

	Main main;

	public Chef(Main inst) {
		main = inst;
	}

	@EventHandler
	void DamageEvent(EntityDamageByEntityEvent event) {
		LivingEntity livingEntity = (LivingEntity) event.getEntity();
		Entity attacker = event.getDamager();
		if (!PvPKits.getKit((Player) attacker).equalsIgnoreCase("Chef")) {
			return;
		}
		if (event.getCause() == DamageCause.ENTITY_ATTACK) {
			if (!(attacker instanceof Player)) {
				return;
			}
			Player a = (Player) attacker;

			ItemStack is = a.getItemInHand();
			if (is == null)
				return;
			if (is.getType() != Material.CAKE)
				return;
			livingEntity.setFireTicks(1000);
		}
	}
}
