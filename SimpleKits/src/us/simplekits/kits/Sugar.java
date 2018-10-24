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



class Sugar implements Listener{
Main main;
Sugar(Main instance){main = instance;}
	
//============================================= Huevos Explosivos
	@EventHandler
	void lanzarHuevoExplosivo(PlayerInteractEvent e){
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR)return;
		Player j = e.getPlayer();
		
		ItemStack mano = j.getItemInHand();
		if(mano == null)return;
		if(mano.getType() != Material.SUGAR)return;
		if(main.coolDown.contains(j.getName() + "Sugar"))return;
		
		j.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, main.getConfig().getInt("Sugar.Speed_Duration") * 20, 3));

		main.iniciarCoolDown(j.getName() + "Sugar", main.getConfig().getInt("Sugar.CoolDown"));
	}
}