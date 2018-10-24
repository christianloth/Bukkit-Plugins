package us.simplekits.events;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
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

	Main main = this;

	public List<String> Hg = new ArrayList<String>();
	public GameState gameState = null;

	public void onEnable() {
		System.out
				.println("SimpleEvents by Christian || Eexar has been enabled!");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new Tags(this), this);
		start();
	}

	public void onDisable() {
		System.out
				.println("SimpleEvents by Christian || Eexar has been disabled ):");

	}

	public int min(int time) {
		return 20 * 60 * time;
	}

	public void broadcast() {
		this.getServer().getScheduler()
				.scheduleSyncRepeatingTask(this, new Runnable() {

					@Override
					public void run() {
						Bukkit.broadcastMessage("§7-----------------------------------------------------");
						Bukkit.broadcastMessage("§7An §aHg §7tournament is starting in 2 minutes. Type §a/Hg §7to join!");
						Bukkit.broadcastMessage("§7-----------------------------------------------------");
						gameState = GameState.STARTING;

					}

				}, 0L, min(22));
	}

	public void start() {
		broadcast();
		Bukkit.getServer().getScheduler()
				.scheduleSyncRepeatingTask(this, new Runnable() {
					public void run() {
						if (Hg.size() < 6) {
							Bukkit.broadcastMessage(ChatColor.RED
									+ "The Hg tournament failed to start due to less then 6 players participating!");
							gameState = null;
							Hg.clear();
							return;
						}
						gameState = GameState.STARTED;
						Bukkit.broadcastMessage(ChatColor.GOLD
								+ "Hg has started!");
						for (Player online : Bukkit.getOnlinePlayers())
							if (Hg.contains(online.getName())) {
								online.getInventory().clear();
								online.getInventory().setArmorContents(null);
								online.setGameMode(GameMode.SURVIVAL);
								Hg.remove(online.getName());
								online.setHealth(0.0D);
								online.setHealth(20.0D);
								Hg.add(online.getName());
								online.setAllowFlight(false);
								online.setFlying(false);
								for (PotionEffect pots : online
										.getActivePotionEffects()) {
									online.removePotionEffect(pots.getType());
								}
								online.teleport(new Location(online.getWorld(),
										4140.5D, 67.0D, -3952.5D));
								online.addPotionEffect(new PotionEffect(
										PotionEffectType.INVISIBILITY, 100, 1));
								ItemStack wSword = new ItemStack(
										Material.WOOD_SWORD);
								ItemStack soup = new ItemStack(
										Material.MUSHROOM_SOUP);
								ItemStack bowl = new ItemStack(Material.BOWL,
										32);
								ItemStack red = new ItemStack(
										Material.RED_MUSHROOM, 32);
								ItemStack brown = new ItemStack(
										Material.BROWN_MUSHROOM, 32);
								wSword.addEnchantment(Enchantment.DURABILITY, 3);
								online.getInventory().addItem(
										new ItemStack[] { wSword });
								for (int i = 0; i < 32; i++) {
									online.getInventory().addItem(
											new ItemStack[] { soup });
								}
								online.getInventory().addItem(
										new ItemStack[] { bowl });
								online.getInventory().addItem(
										new ItemStack[] { red });
								online.getInventory().addItem(
										new ItemStack[] { brown });
							}
					}
				}, min(2), min(22));
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("Hg")) {
			if ((sender instanceof Player)) {
				Player p = (Player) sender;
				if (gameState != GameState.STARTING) {
					p.sendMessage(ChatColor.GOLD
							+ "Hg is not being hosted at this time!");
					return true;
				}
				if (gameState == GameState.STARTED) {
					p.sendMessage(ChatColor.GOLD + "Hg has already started!");
					return true;
				}
				if (Hg.contains(p.getName())) {
					p.sendMessage(ChatColor.GOLD + "You are already in the Hg!");
					return true;
				}
				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName()
						+ ChatColor.DARK_PURPLE + " has joined Hg!");
				Hg.add(p.getName());
			} else {
				sender.sendMessage("You must be a player to do this.");
			}
		}
		return false;
	}

	@EventHandler
	public void onLogout(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (Hg.contains(p.getName())) {
			Hg.remove(p.getName());
			p.setHealth(0.0D);
			for (Player online : Bukkit.getOnlinePlayers())
				online.sendMessage(ChatColor.DARK_PURPLE + p.getName()
						+ " has just logged out in Hg. Players left: "
						+ ChatColor.GOLD + Hg.size());
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		if ((event.getEntity() instanceof Player)) {
			Player player = event.getEntity();
			if (Hg.contains(player.getName())) {
				if (gameState == GameState.STARTED) {
					Bukkit.broadcastMessage(ChatColor.DARK_PURPLE
							+ player.getName()
							+ " has lost in Hg. Players left: "
							+ ChatColor.GOLD + Hg.size());
					Hg.remove(player.getName());
				}
			}
		}
	}

	@EventHandler
	public void onDeathTwo(PlayerDeathEvent event) {
		if ((event.getEntity() instanceof Player)) {
			Player player = event.getEntity();
			for (Player online : Bukkit.getOnlinePlayers())
				if ((Hg.contains(online.getName())) && (Hg.size() == 1)
						&& (gameState == GameState.STARTED)) {
					Bukkit.broadcastMessage(ChatColor.GREEN + online.getName()
							+ " has won the Hg and rewarded with $1000!");
					Bukkit.getServer().dispatchCommand(
							Bukkit.getConsoleSender(),
							"money give " + online.getName() + " 1000");
					online.teleport(new Location(player.getWorld(), 1243.0D,
							180.0D, 8032.0D));
					online.getInventory().clear();
					Hg.clear();
					gameState = null;
				}
		}
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		if ((Hg.contains(player.getName())) && (gameState == GameState.STARTED)
				&& (!player.isOp())) {
			event.setCancelled(true);
			player.sendMessage(ChatColor.RED + "You cannot use commands in Hg!");
		}
	}
}