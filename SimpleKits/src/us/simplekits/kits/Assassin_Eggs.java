package us.simplekits.kits;

import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class Assassin_Eggs implements Listener {
	Main main;

	Assassin_Eggs(Main instance) {
		this.main = instance;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	void lanzarHuevoExplosivo(PlayerInteractEvent e) {
		if ((e.getAction() != Action.RIGHT_CLICK_BLOCK)
				&& (e.getAction() != Action.RIGHT_CLICK_AIR))
			return;
		Player j = e.getPlayer();

		ItemStack mano = j.getItemInHand();
		if (mano == null)
			return;
		if (mano.getType() != Material.EGG) {
			return;
		}
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Material mat = e.getClickedBlock().getType();
			if (materialBypass(mat))
				return;
			if (materialCancelarEvento(mat)) {
				e.setCancelled(true);
				j.updateInventory();
				return;
			}
		}

		e.setCancelled(true);

		ItemStack is = j.getItemInHand();
		j.getInventory().remove(is);
		is.setAmount(is.getAmount() - 1);
		j.getInventory().addItem(new ItemStack[] { is });
		j.updateInventory();

		Projectile pro = j.launchProjectile(Egg.class);
		pro.setVelocity(pro.getVelocity().multiply(
				pro.getVelocity().length() * 65.0D / 100.0D));
		this.main.explodingEggs.add(pro.getUniqueId());
	}

	@EventHandler
	void explotarEnSuperficie(ProjectileHitEvent e) {
		Projectile pro = e.getEntity();
		if (!(pro.getShooter() instanceof Player))
			return;
		if (!(pro instanceof Egg))
			return;
		UUID uuid = pro.getUniqueId();
		if (this.main.explodingEggs.contains(uuid)) {
			this.main.explodingEggs.remove(uuid);
			Location l = pro.getLocation();
			pro.remove();
			l.getWorld().createExplosion(l.getX(), l.getY(), l.getZ(),
					this.main.getConfig().getInt("Eggs.Explosion_Power"),
					false, false);
			efectosAdversos(l);
		}
	}

	@EventHandler
	void explotarEnEntidad(EntityDamageByEntityEvent e) {
		if (e.getCause() != EntityDamageEvent.DamageCause.PROJECTILE)
			return;
		if (!(e.getDamager() instanceof Egg))
			return;
		Egg huevo = (Egg) e.getDamager();
		if (!(huevo.getShooter() instanceof Player))
			return;
		if (!(huevo instanceof Egg))
			return;
		UUID uuid = huevo.getUniqueId();
		if (this.main.explodingEggs.contains(uuid)) {
			this.main.explodingEggs.remove(uuid);
			Location l = huevo.getLocation();
			huevo.remove();
			l.getWorld().createExplosion(l.getX(), l.getY(), l.getZ(),
					this.main.getConfig().getInt("Eggs.Explosion_Power"),
					false, false);
			efectosAdversos(l);
		}
	}

	void efectosAdversos(Location l) {
		int radius = this.main.getConfig().getInt("Eggs.Effect_Range");
		List<Entity> entidadesMundo = l.getWorld().getEntities();
		for (Entity en : entidadesMundo)
			if ((en.getLocation().distance(l) <= radius)
					&& ((en instanceof LivingEntity))) {
				((LivingEntity) en).addPotionEffect(new PotionEffect(
						PotionEffectType.CONFUSION, this.main.getConfig()
								.getInt("Eggs.Effect_Duration") * 20, 0));
				((LivingEntity) en).addPotionEffect(new PotionEffect(
						PotionEffectType.BLINDNESS, this.main.getConfig()
								.getInt("Eggs.Effect_Duration") * 20, 0));
			}
	}

	boolean materialBypass(Material mat) {
		if ((mat == Material.WOODEN_DOOR) || (mat == Material.IRON_DOOR)
				|| (mat == Material.TRAP_DOOR) || (mat == Material.FENCE_GATE)
				|| (mat == Material.LEVER) || (mat == Material.STONE_BUTTON)
				|| (mat == Material.DIODE) || (mat == Material.CHEST)
				|| (mat == Material.ENDER_CHEST) || (mat == Material.FURNACE)
				|| (mat == Material.DISPENSER) || (mat == Material.WORKBENCH)
				|| (mat == Material.BED) || (mat == Material.JUKEBOX)
				|| (mat == Material.ANVIL) || (mat == Material.BEACON)
				|| (mat == Material.NOTE_BLOCK)
				|| (mat == Material.BREWING_STAND)
				|| (mat == Material.ENCHANTMENT_TABLE)
				|| (mat == Material.FLOWER_POT) || (mat == Material.BOAT)
				|| (mat == Material.MINECART)) {
			return true;
		}
		return false;
	}

	boolean materialCancelarEvento(Material mat) {
		if ((mat == Material.SIGN) || (mat == Material.SIGN_POST)
				|| (mat == Material.WALL_SIGN)) {
			return true;
		}
		return false;
	}
}