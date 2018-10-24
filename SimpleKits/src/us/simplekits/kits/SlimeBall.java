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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



public class SlimeBall implements Listener {
	Main main;
	SlimeBall(Main instance){main = instance; }
	
	@EventHandler
	void DamageEvent(EntityDamageByEntityEvent event) {
		LivingEntity livingEntity = (LivingEntity) event.getEntity();
		Entity attacker = event.getDamager();

		if (event.getCause() == DamageCause.ENTITY_ATTACK) {
			if (!(attacker instanceof Player))
				return;
			Player a = (Player) attacker;

			ItemStack is = a.getItemInHand();
			if (is == null)
				return;
			if (is.getType() != Material.COAL)
				return;
			event.setCancelled(true);

			livingEntity.addPotionEffect(new PotionEffect(
					PotionEffectType.WITHER, main.getConfig().getInt(
							"SlimeBall.Effect_Duration") * 20, 0));

		}
	}
}
