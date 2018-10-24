package lms.Eexar;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener {

	public List<String> Lms = new ArrayList<String>();
	public GameState gameState = null;

	public void onEnable() {
		System.out.println("Lms by Christian || Eexar has been enabled!");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
	}

	public void onDisable() {
		System.out.println("Lms by Christian || Eexar has been disabled ):");

	}

	public int min(int time) {
		return 20 * 60 * time;
	}

	public void broadcast() {
		this.getServer().getScheduler()
				.scheduleSyncDelayedTask(this, new Runnable() {

					@Override
					public void run() {
						Bukkit.broadcastMessage("§7-----------------------------------------------------");
						Bukkit.broadcastMessage("§6Lms §7is starting in 1 minute. Type §a/Lms join §7to join!");
						Bukkit.broadcastMessage("§7-----------------------------------------------------");
						gameState = GameState.STARTING;

					}

				}, 0);
	}

	public void start() {
		broadcast();
		Bukkit.getServer().getScheduler()
				.scheduleSyncDelayedTask(this, new Runnable() {
					public void run() {
						if (Lms.size() < 6) {
							Bukkit.broadcastMessage(ChatColor.RED
									+ "The Lms tournament failed to start due to less then 6 players participating!");
							gameState = null;
							Lms.clear();
							return;
						}
						gameState = GameState.STARTED;
						Bukkit.broadcastMessage(ChatColor.GOLD
								+ "Lms has started!");
						for (Player online : Bukkit.getOnlinePlayers())
							if (Lms.contains(online.getName())) {
								online.getInventory().clear();
								online.getInventory().setArmorContents(null);
								online.setGameMode(GameMode.SURVIVAL);
								online.setAllowFlight(false);
								online.setFlying(false);
								for (PotionEffect pots : online
										.getActivePotionEffects()) {
									online.removePotionEffect(pots.getType());
								}
								online.teleport(new Location(online.getWorld(),
										2041.5D, 94.0D, -1109.5D));
								online.addPotionEffect(new PotionEffect(
										PotionEffectType.INVISIBILITY, 100, 1));
								ItemStack dSword = new ItemStack(
										Material.DIAMOND_SWORD);
								ItemStack soup = new ItemStack(
										Material.MUSHROOM_SOUP);
								online.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
								online.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
								online.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
								online.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
								online.getInventory().addItem(
										new ItemStack[] { dSword });
								for (int i = 0; i < 32; i++) {
									online.getInventory().addItem(
											new ItemStack[] { soup });
								}
							}
					}
				}, min(1));
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("Lms")) {
			if ((sender instanceof Player)) {
				Player p = (Player) sender;
				if (args.length == 0) {
					p.sendMessage("§7****§5Lms§7****");
					p.sendMessage("§5/Lms host §7--> For §9SuperDonors §7and above!");
					p.sendMessage("§5/Lms join §7--> Join Lms!");
					return true;
				}
				if (args[0].equalsIgnoreCase("host") && gameState == null
						&& p.hasPermission("simplekits.lms.host")) {
					start();
					gameState = GameState.STARTING;
				}
				if (args[0].equalsIgnoreCase("join")) {
					if (gameState != GameState.STARTING) {
						p.sendMessage(ChatColor.GOLD
								+ "Lms is not being hosted at this time!");
						return true;
					}
					if (gameState == GameState.STARTED) {
						p.sendMessage(ChatColor.GOLD
								+ "Lms has already started!");
						return true;
					}
					if (Lms.contains(p.getName())) {
						p.sendMessage(ChatColor.GOLD
								+ "You are already in the Lms!");
						return true;
					}
					Lms.add(p.getName());
					p.sendMessage(ChatColor.GOLD + "You have joined Lms!");
				}
				if (!args[0].equalsIgnoreCase("host")
						&& !args[0].equalsIgnoreCase("join")) {
					p.sendMessage("§7****§5Lms§7****");
					p.sendMessage("§5/Lms host §7--> For §9SuperDonors §7and above!");
					p.sendMessage("§5/Lms join §7--> Join Lms!");
					return true;
				}
			} else {
				sender.sendMessage("You must be a player to do this.");
			}
		}
		return false;
	}

	@EventHandler
	public void onLogout(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (Lms.contains(p.getName())) {
			Lms.remove(p.getName());
			p.setHealth(0.0D);
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		if ((event.getEntity() instanceof Player)) {
			Player player = event.getEntity();
			if (Lms.contains(player.getName())) {
				if (gameState == GameState.STARTED) {
					Lms.remove(player.getName());
					Bukkit.broadcastMessage(ChatColor.GRAY + "" + Lms.size() + " players left in Lms.");
				}
			}
		}
	}

	@EventHandler
	public void onDeathTwo(PlayerDeathEvent event) {
		if ((event.getEntity() instanceof Player)) {
			for (Player online : Bukkit.getOnlinePlayers())
				if ((Lms.contains(online.getName())) && (Lms.size() == 1)
						&& (gameState == GameState.STARTED)) {
					Bukkit.broadcastMessage(ChatColor.GREEN + online.getName()
							+ " has won the Lms and rewarded with $1000!");
					Bukkit.getServer().dispatchCommand(
							Bukkit.getConsoleSender(),
							"money give " + online.getName() + " 1000");
					online.teleport(online.getWorld().getSpawnLocation());
					online.getInventory().clear();
					Lms.clear();
					gameState = null;
				}
		}
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		if ((Lms.contains(player.getName()))
				&& (gameState == GameState.STARTED) && (!player.isOp())) {
			event.setCancelled(true);
			player.sendMessage(ChatColor.RED
					+ "You cannot use commands in Lms!");
		}
	}

	public enum GameState {

		STARTING, STARTED;

	}
}
