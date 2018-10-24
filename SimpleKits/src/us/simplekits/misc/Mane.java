package us.simplekits.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import us.simplekits.kits.Main;


public class Mane implements Listener, CommandExecutor {

	Main main;

	public Mane(Main instance) {
		main = instance;
	}

	public static List<Player> soupCD = new ArrayList<Player>();
	public static List<Player> sponge = new ArrayList<Player>();

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (((label.equalsIgnoreCase("Soup")) || (label
				.equalsIgnoreCase("Refill"))) && ((sender instanceof Player))) {
			final Player p = (Player) sender;
			if (!soupCD.contains(p)) {
				Inventory inv = Bukkit.createInventory(p, 36,
						ChatColor.DARK_RED + "" + ChatColor.BOLD + "Soups");
				ItemStack sup = new ItemStack(Material.MUSHROOM_SOUP);
				ItemMeta supMeta = sup.getItemMeta();
				supMeta.setDisplayName(ChatColor.RED + "Soup");
				supMeta.setLore(Arrays.asList(new String[] {
						"Right click to heal 3.5 hearts!",
						"Use these wisely in combat!" }));
				sup.setItemMeta(supMeta);
				for (int n = 0; n <= 37; n++) {
					inv.addItem(new ItemStack[] { sup });
				}
				p.openInventory(inv);
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0F, 1.0F);
				p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 1);
				soupCD.add(p);
				p.sendMessage(ChatColor.RED
						+ "You have summoned a bountiful amount of soup to appear. You"
						+ " must wait a while before summoning more soup.");
				Bukkit.getServer().getScheduler()
						.scheduleSyncDelayedTask(main, new Runnable() {
							public void run() {
								try {
									Mane.soupCD.remove(p);
								} catch (Exception localException) {
								}
							}
						}, 600L);
			} else {
				p.sendMessage(ChatColor.RED
						+ "You are too tired to summon more soup!");
			}

		}

		return false;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onItemDrop(final PlayerDropItemEvent e) {
		if (!e.isCancelled()) {
			final Item d = e.getItemDrop();
			Bukkit.getServer().getScheduler()
					.scheduleSyncDelayedTask(main, new Runnable() {
						public void run() {
							try {
								e.getItemDrop().remove();
								d.getLocation()
										.getWorld()
										.playEffect(d.getLocation(),
												Effect.SMOKE, 1);
								d.getLocation()
										.getWorld()
										.playSound(d.getLocation(),
												Sound.CHICKEN_EGG_POP, 1.0F,
												1.0F);
							} catch (Exception localException) {
							}
						}
					}, 200L);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerDeathEvent e) {
		final Player p = e.getEntity();

		e.setDroppedExp(0);
		
		PlayerInventory pinv = p.getInventory();
		
		pinv.setHelmet(null);
		pinv.setChestplate(null);
		pinv.setLeggings(null);
		pinv.setBoots(null);
		
		for (PotionEffect effect1 : p
				.getActivePotionEffects()) {
			p.removePotionEffect(effect1.getType());
		}
		
		Object effect;
		
		for (Iterator<PotionEffect> localIterator2 = p
				.getActivePotionEffects().iterator(); localIterator2
				.hasNext();) {
			effect = (PotionEffect) localIterator2.next();
			p.removePotionEffect(((PotionEffect) effect)
					.getType());
		}

		/*
		 * Bukkit.getServer().getScheduler() .scheduleSyncDelayedTask(main, new
		 * Runnable() { public void run() { try { Packet205ClientCommand packet
		 * = new Packet205ClientCommand(); packet.a = 1; ((CraftPlayer)
		 * p).getHandle().playerConnection .a(packet); } catch (Exception
		 * localException) { } } }, 20L);
		 */

		final Location pLoc = p.getLocation();
		Bukkit.getServer().getScheduler()
				.scheduleSyncDelayedTask(main, new Runnable() {
					@SuppressWarnings({ "rawtypes", "deprecation" })
					public void run() {
						Object effect;
						try {
							p.setHealth(20.0D);
							p.setFireTicks(0);
							p.setVelocity(new Vector(0.0D, 0.0D, 0.0D));
							
							
							
							p.updateInventory();
						} catch (Exception localException) {
						}
						try {
							for (effect = p.getWorld().getEntities().iterator(); ((Iterator) effect)
									.hasNext();) {
								Entity e = (Entity) ((Iterator) effect).next();
								if ((pLoc.distanceSquared(e.getLocation()) <= 16.0D)
										&& ((e instanceof Item))) {
									e.remove();
									e.getLocation()
											.getWorld()
											.playEffect(e.getLocation(),
													Effect.SMOKE, 1);
									e.getLocation()
											.getWorld()
											.playSound(e.getLocation(),
													Sound.CHICKEN_EGG_POP,
													1.0F, 1.0F);
								}
							}
						} catch (Exception localException1) {
						}
					}
				}, 150L);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerHurted(EntityDamageEvent e) {
		if ((e.getEntity() instanceof Player)) {
			Player p = (Player) e.getEntity();
			if ((e.getCause().equals(EntityDamageEvent.DamageCause.FALL))
					&& (sponge.contains(p))) {
				sponge.remove(p);
				e.setCancelled(true);
			}
		}
	}
}
