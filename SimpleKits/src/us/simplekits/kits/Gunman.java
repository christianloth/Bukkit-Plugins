package us.simplekits.kits;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;



public class Gunman implements Listener {

	Main main;

	public Gunman(Main instance) {
		main = instance;
	}

	@EventHandler
	void gunman(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK
				&& event.getAction() != Action.RIGHT_CLICK_AIR
				&& event.getAction() != Action.LEFT_CLICK_BLOCK
				&& event.getAction() != Action.LEFT_CLICK_AIR)
			return;
		Player player = event.getPlayer();

		ItemStack is = player.getItemInHand();
		if (is == null)
			return;
		
		if (is.getType() != Material.SULPHUR)
			return;
		
		if (main.coolDown.contains(player.getName() + "Gunman"))
			return;

		Projectile pro = player.launchProjectile(Arrow.class);
		pro.setVelocity(pro.getVelocity().multiply(
				pro.getVelocity().length()
						* main.getConfig().getInt("Gunpowder.Projectile_Speed")
						/ 100));
		
		player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 1);
		
		main.CoolDownDouble(player.getName() + "Gunman", main.getConfig().getDouble("Gunpowder.CoolDown"));
	}
	
}