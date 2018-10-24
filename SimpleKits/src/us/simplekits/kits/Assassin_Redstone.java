package us.simplekits.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


class Assassin_Redstone implements Listener {
	Main main;

	public Assassin_Redstone(Main instance) {
		main = instance;
	}

	// ============================================= Huevos Explosivos
	@EventHandler
	void lanzarHuevoExplosivo(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK
				&& e.getAction() != Action.RIGHT_CLICK_AIR)
			return;
		Player j = e.getPlayer();

		ItemStack mano = j.getItemInHand();
		if (mano == null)
			return;
		if (mano.getType() != Material.REDSTONE)
			return;
		if (main.coolDown.contains(j.getName() + "Assassin_Redstone"))
			return;

		j.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200,
				0));

		main.iniciarCoolDown(j.getName() + "Assassin_Redstone", main
				.getConfig().getInt("Assassin_Redstone.CoolDown"));
	}
}