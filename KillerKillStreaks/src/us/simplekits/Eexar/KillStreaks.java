package us.simplekits.Eexar;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class KillStreaks implements Listener {
	public static ListStore list;
	public static String seperator = " ";

	Main main;

	public KillStreaks(Main instance) {
		main = instance;
	}

	private void cmd(String cmd, Player p) {
		main.getServer().dispatchCommand(main.getServer().getConsoleSender(),
				cmd.replaceAll("&", "§").replaceAll("<player>", p.getName()));
	}

	private boolean contains(String player) {
		ArrayList<String> values = list.getValues();

		for (String tmp : values) {
			String[] arr = tmp.split(seperator);

			if (this.main.getServer().getPlayer(arr[0]).getName() == this.main
					.getServer().getPlayer(player).getName()) {
				return true;
			}
		}

		return false;
	}

	private int getValue(String player) {
		ArrayList<String> values = list.getValues();

		for (String tmp : values) {
			String[] arr = tmp.split(seperator);
			if (Bukkit.getPlayer(arr[0]).getName() == Bukkit.getPlayer(player)
					.getName()) {
				return Integer.parseInt(arr[1]);
			}

		}

		return 0;
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerDie(PlayerDeathEvent evt) {
		if ((evt.getEntity().getKiller() instanceof Player)) {
			Player p = evt.getEntity().getKiller();
			int streak = 0;
			if (contains(evt.getEntity().getKiller().getName())) {
				streak = getValue(evt.getEntity().getKiller().getName());
				list.remove(evt.getEntity().getKiller().getName() + seperator
						+ streak);
			}
			streak++;
			list.add(evt.getEntity().getKiller().getName() + seperator + streak);
			if (streak == 3) {
				short pot = 8201;
				main.getServer()
						.dispatchCommand(
								main.getServer().getConsoleSender(),
								"bc &6<player>&9 has gotten a killstreak of &63&9 and was rewarded with a &6Strength Potion"
										.replaceAll("&", "§").replaceAll(
												"<player>", p.getName()));
				ItemStack strp1 = new ItemStack(Material.getMaterial(373), 1,
						pot);
				p.getInventory().addItem(new ItemStack[] { strp1 });
			}
			if (streak == 5) {
				cmd("bc &6<player>&9 has gotten a killstreak of &65&9 and was rewarded with a &6Protection Chestplate &9and &6Leggings",
						p);
				cmd("give <player> 307 1 0:1", p);
				cmd("give <player> 308 1 0:1", p);
			}
			if (streak == 10) {
				p.getInventory().getHelmet().setDurability((short) -1);
				p.getInventory().getChestplate().setDurability((short) -1);
				p.getInventory().getLeggings().setDurability((short) -1);
				p.getInventory().getBoots().setDurability((short) -1);
				cmd("bc &6<player>&9 has gotten a killstreak of &610&9 and was rewarded with a &6Free Repair &9and &6Soup",
						p);
				cmd("money give <player> 2000", p);
				cmd("give <player> 282 15", p);
			}
			if (streak == 15) {
				cmd("bc &6<player>&9 has gotten a killstreak of &615&9 and was rewarded with a &6Sharp III Sword",
						p);
				cmd("give <player> 276 1 16:3", p);
			}
			if (streak == 20) {
				cmd("bc &6<player>&9 has gotten a killstreak of &620&9 and was rewarded with &6$1000",
						p);
				cmd("money give <player> 1000", p);
			}
			if (streak == 25) {
				cmd("bc &6<player>&9 has gotten a killstreak of &625&9 and was rewarded with a &6Daimond Chestplate",
						p);
				cmd("give <player> 311 1", p);
			}
			if (streak == 30) {
				cmd("bc &6<player>&9 has gotten a killstreak of &630&9 and was rewarded with &6Diamond Leggings",
						p);
				cmd("give <player> 312 1", p);
			}
			if (streak == 40) {
				cmd("bc &6<player>&9 has gotten a killstreak of &640&9 and was rewarded with &6Diamond Boots&e and &6Helmet",
						p);
				cmd("give <player> 310 1", p);
				cmd("give <player> 313 1", p);
			}
			if (streak == 50) {
				cmd("bc &6<player>&9 has gotten a killstreak of &650&9! Thats pretty good! He got &6$5K",
						p);
				cmd("money give <player> 5000", p);
			}
			if (streak == 100) {
				cmd("bc &6<player>&9 has gotten a killstreak of &650&9! WOW! He got &6$10K",
						p);
				cmd("money give <player> 10000", p);
			}
		}
	}
}