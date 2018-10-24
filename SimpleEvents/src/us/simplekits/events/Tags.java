package us.simplekits.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;

public class Tags implements Listener {
	
	Main main;
	
	public Tags(Main inst) {
		main = inst;
	}
	
	@EventHandler
	public void onNameTag(AsyncPlayerReceiveNameTagEvent evt) {
		Player p = evt.getNamedPlayer();
		String pName = p.getName();
		
		if (p.hasPermission("simplekits.tags.Owner")) {
			evt.setTag("§4" + pName);
			return;
		}
		if (p.hasPermission("simplekits.tags.Admin")) {
			evt.setTag("§c" + pName);
			return;
		}
		if (p.hasPermission("simplekits.tags. ModPlus")) {
			evt.setTag("§5§o" + pName);
			return;
		}
		if (p.hasPermission("simplekits.tags.Moderator")) {
			evt.setTag("§5" + pName);
			return;
		}
		if (p.hasPermission("simplekits.tags.TrialMod")) {
			evt.setTag("§d" + pName);
			return;
		}
		if (p.hasPermission("simplekits.tags.Police")) {
			evt.setTag("§e" + pName);
			return;
		}
		if (p.hasPermission("simplekits.tags.UltraDonor")) {
			evt.setTag("§6" + pName);
			return;
		}
		if (p.hasPermission("simplekits.tags.SuperDonor")) {
			evt.setTag("§9" + pName);
			return;
		}
		if (p.hasPermission("simplekits.tags.Donor")) {
			evt.setTag("§a" + pName);
			return;
		}
		if (p.hasPermission("simplekits.tags.default")) {
			evt.setTag("§7" + pName);
			return;
		}
	}
}
