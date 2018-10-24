package us.simplekits.kits;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import mkremins.fanciful.FancyMessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import us.simplekits.hgkits.M;
import us.simplekits.hgkits.hgAnchor;
import us.simplekits.hgkits.hgArcher;
import us.simplekits.hgkits.hgEarly;
import us.simplekits.hgkits.hgEndermage;
//import us.simplekits.hgkits.hgFisherman;
import us.simplekits.hgkits.hgGrappler;
import us.simplekits.hgkits.hgKangaroo;
import us.simplekits.hgkits.hgPhantom;
import us.simplekits.hgkits.hgRhino;
import us.simplekits.hgkits.hgStomper;
import us.simplekits.hgkits.hgSwitcher;
import us.simplekits.hgkits.hgTurtle;
import us.simplekits.killstreaks.KillStreaks;
import us.simplekits.killstreaks.ListStore;
import us.simplekits.misc.AntiBadChat;
import us.simplekits.misc.AntiKitMixing;
import us.simplekits.misc.BanHammer;
import us.simplekits.misc.BcMsg;
import us.simplekits.misc.CombatLog;
import us.simplekits.misc.Donate;
import us.simplekits.misc.Gambling;
import us.simplekits.misc.KillReceiveItems;
import us.simplekits.misc.Mane;
import us.simplekits.misc.Messages;
import us.simplekits.misc.MuteChat;
import us.simplekits.misc.NoPickUps;
import us.simplekits.misc.StaffChat;

public class Main extends JavaPlugin implements Listener {

	public static final Logger logger = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdfFile;

	// ============================================= Instancias
	Main main;
	// Command1v1 co = new Command1v1(main);

	// ============================================= Variables
	// public Economy pluginEconómico = null;

	// ============================================= Cabeceras
	// String label = (ChatColor.DARK_RED + "[" + ChatColor.GOLD +
	// " BottledEXP " + ChatColor.DARK_RED + "]" + ChatColor.WHITE + " - " +
	// ChatColor.GREEN);

	// ============================================= HashMaps y Listas
	// HashMap<Item, String> sacrificadoresYSacrificio = new HashMap<Item,
	// String>();

	public List<UUID> explodingEggs = new ArrayList<UUID>();
	public List<UUID> Bolas_De_Nieve = new ArrayList<UUID>();
	public List<String> Jugadores_Congelados = new ArrayList<String>();
	public List<String> coolDown = new ArrayList<String>();
	public List<String> noFallList = new ArrayList<String>();

	public static boolean chatMute;

	// ============================================= DISABLE / ENABLE
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		Main.logger.info("<<[ " + pdfFile.getName()
				+ " ]>> By Christian || Eexar Disabled! ):");
		KillStreaks.list.save();
		main = null;
	}

	/*
	 * File kitFile = new File(main.getDataFolder(), "Kit_File.yml");
	 * 
	 * 
	 * public void kitFileWrite(String log) {
	 * 
	 * if (!kitFile.exists()) { try {
	 * 
	 * kitFile.createNewFile(); } catch (IOException e1) {
	 * 
	 * } }
	 * 
	 * try { PrintWriter pw = new PrintWriter(kitFile);
	 * 
	 * pw.println(log);
	 * 
	 * pw.flush(); pw.close();
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	@Override
	public void onEnable() {
		main = this;
		pdfFile = this.getDescription();
		config(true);

		MuteChat.muteChat = false;
		Gambling.session = false;

		String pluginFolder = getDataFolder().getAbsolutePath();
		new File(pluginFolder).mkdirs();
		KillStreaks.list = new ListStore(new File(pluginFolder + File.separator
				+ "killstreaks.txt"));
		KillStreaks.list.load();

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		//pm.registerEvents(new Assassin_Eggs(this), this);
		pm.registerEvents(new Blaze(this), this);
		pm.registerEvents(new Assassin_Redstone(this), this);
		pm.registerEvents(new Sugar(this), this);
		pm.registerEvents(new Earthquake(this), this);
		pm.registerEvents(new Witherman(this), this);
		pm.registerEvents(new Batman(this), this);
		pm.registerEvents(new Gunman(this), this);
		pm.registerEvents(new NoPickUps(this), this);
		pm.registerEvents(new Kangaroo(this), this);
		pm.registerEvents(new KillReceiveItems(this), this);
		pm.registerEvents(new Flash(this), this);
		pm.registerEvents(new Ghost(this), this);
		pm.registerEvents(new Wizard(this), this);
		pm.registerEvents(new Grappler(this), this);
		pm.registerEvents(new AntiKitMixing(this), this);
		pm.registerEvents(new Timelord(this), this);
		pm.registerEvents(new AntiBadChat(this), this);
		pm.registerEvents(new Mane(this), this);
		pm.registerEvents(new KillStreaks(this), this);
		pm.registerEvents(new M(this), this);
		pm.registerEvents(new MuteChat(this), this);
		pm.registerEvents(new Messages(this), this);
		pm.registerEvents(new CombatLog(this), this);
		pm.registerEvents(new BanHammer(this), this);
		pm.registerEvents(new Gambling(this), this);
		//pm.registerEvents(new Pitcher(this), this);
		pm.registerEvents(new Bomber(this), this);
		pm.registerEvents(new Tornado(this), this);
		pm.registerEvents(new Freezer(this), this);
		pm.registerEvents(new Jumper(this), this);
		pm.registerEvents(new Chef(this), this);

		pm.registerEvents(new hgSwitcher(this), this);
		//pm.registerEvents(new hgFisherman(this), this);
		pm.registerEvents(new hgStomper(this), this);
		pm.registerEvents(new hgRhino(this), this);
		pm.registerEvents(new hgTurtle(this), this);
		pm.registerEvents(new hgAnchor(this), this);
		pm.registerEvents(new hgEndermage(this), this);
		pm.registerEvents(new hgGrappler(this), this);
		pm.registerEvents(new hgPhantom(this), this);

		getCommand("soup").setExecutor(new Mane(this));
		getCommand("refill").setExecutor(new Mane(this));
		getCommand("bcmsg").setExecutor(new BcMsg(this));
		getCommand("mutechat").setExecutor(new MuteChat(this));
		getCommand("mc").setExecutor(new MuteChat(this));
		getCommand("donate").setExecutor(new Donate(this));
		getCommand("special").setExecutor(new Donate(this));
		getCommand("donor").setExecutor(new Donate(this));
		getCommand("superdonor").setExecutor(new Donate(this));
		getCommand("ultradonor").setExecutor(new Donate(this));
		getCommand("police").setExecutor(new Donate(this));
		getCommand("kitlist").setExecutor(new Donate(this));
		getCommand("ban").setExecutor(new BanHammer(this));
		getCommand("unban").setExecutor(new BanHammer(this));
		getCommand("pardon").setExecutor(new BanHammer(this));
		getCommand("kick").setExecutor(new BanHammer(this));
		getCommand("bet").setExecutor(new Gambling(this));
		getCommand("beton").setExecutor(new Gambling(this));
		getCommand("betoff").setExecutor(new Gambling(this));
		getCommand("sc").setExecutor(new StaffChat(this));
		getCommand("staffchat").setExecutor(new StaffChat(this));

		getCommand("hgearly").setExecutor(new hgEarly(this));
		getCommand("hgkangaroo").setExecutor(new hgKangaroo(this));
		getCommand("hgswitcher").setExecutor(new hgSwitcher(this));
		//getCommand("hgfisherman").setExecutor(new hgFisherman(this));
		getCommand("hgstomper").setExecutor(new hgStomper(this));
		getCommand("hgturtle").setExecutor(new hgTurtle(this));
		getCommand("hgrhino").setExecutor(new hgRhino(this));
		getCommand("hgarcher").setExecutor(new hgArcher(this));
		getCommand("hganchor").setExecutor(new hgAnchor(this));
		getCommand("hgendermage").setExecutor(new hgEndermage(this));
		getCommand("hggrappler").setExecutor(new hgGrappler(this));
		getCommand("hgphantom").setExecutor(new hgPhantom(this));

		Main.logger.info("<<[" + pdfFile.getName() + "]>> "
				+ "By Eexar Successfully Loaded!");

		Bukkit.broadcastMessage("§a§lThe server has restarted. Please re-select your kits.");

		Bukkit.getServer().getScheduler()
				.scheduleSyncRepeatingTask(this, new Runnable() {
					public void run() {
						for (Player p : Bukkit.getOnlinePlayers())
							if (p.getLocation().getBlock()
									.getRelative(BlockFace.DOWN).getType()
									.equals(Material.SPONGE)) {
								if (!Mane.sponge.contains(p)) {
									Mane.sponge.add(p);
								}
								p.getWorld().playEffect(p.getLocation(),
										Effect.STEP_SOUND, 19);
								for (int n = 0; n <= 8; n++)
									if (p.getLocation().getBlock()
											.getRelative(BlockFace.DOWN, n)
											.getType().equals(Material.SPONGE)) {
										Vector vec = new Vector(0, n, 0);
										p.setVelocity(vec);
									}
							}
					}
				}, 1L, 5L);

		/*
		 * Bukkit.getServer().getScheduler() .scheduleSyncRepeatingTask(this,
		 * new Runnable() {
		 * 
		 * @SuppressWarnings("deprecation") public void run() { for (Player all
		 * : Bukkit.getOnlinePlayers()) if ((Mane.sponge.contains(all)) &&
		 * (all.isOnGround())) Mane.sponge.remove(all); } }, 1L, 200L);
		 */
	}

	// ============================================= Cargar Configuración
	public final void config(boolean inicialize) {
		if (inicialize)
			saveDefaultConfig();

		if (!inicialize)
			reloadConfig();
	}

	@EventHandler
	public void join(PlayerJoinEvent evt) {
		new FancyMessage(
				"Check out donate.simplekits.us or click here to donate for amazing perks!")
				.color(ChatColor.RED).then(" §nhere §r").color(ChatColor.GOLD)
				.then("bla").color(ChatColor.GOLD)
				.link("http://donate.simplekits.us/").send(evt.getPlayer());

	}

	@EventHandler
	public void playerFoodLevelChange(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	// ================= CoolDown General
	public void iniciarCoolDown(final String clave, int cooldown) { // clave =
																	// nombre
		// jugador + x
		coolDown.add(clave);
		main.getServer().getScheduler()
				.scheduleSyncDelayedTask(main, new Runnable() {
					public void run() {
						if (coolDown.contains(clave))
							coolDown.remove(clave);
					}
				}, cooldown * 20);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			final String commandLabel, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Must be a player!");
			return true;
		}

		final Player player = (Player) sender;

		/*
		 * player.sendMessage(ChatColor.GOLD + "You will be teleported to the "
		 * + ChatColor.LIGHT_PURPLE + "HG " + ChatColor.GOLD +
		 * "Arena in 5 sec.");
		 * 
		 * this.getServer().getScheduler() .scheduleSyncDelayedTask(this, new
		 * Runnable() {
		 * 
		 * @Override public void run() { player.teleport(new
		 * Location(player.getWorld(), 3559.5D, 71.0D, -4277.5, 90, 0));
		 * 
		 * player.getInventory().clear(); if (!lms.contains(player.getName())) {
		 * lms.add(player.getName()); }
		 * 
		 * }
		 * 
		 * }, 80L);
		 */

		if (cmd.getName().equalsIgnoreCase("earlyhg")
				|| cmd.getName().equalsIgnoreCase("stomper")
				|| cmd.getName().equalsIgnoreCase("warrior")
				|| cmd.getName().equalsIgnoreCase("endermage")
				|| cmd.getName().equalsIgnoreCase("switcher")
				|| cmd.getName().equalsIgnoreCase("thor")
				|| cmd.getName().equalsIgnoreCase("monk")
				|| cmd.getName().equalsIgnoreCase("kangaroo")
				|| cmd.getName().equalsIgnoreCase("flash")
				|| cmd.getName().equalsIgnoreCase("grandpa")
				|| cmd.getName().equalsIgnoreCase("grappler")
				|| cmd.getName().equalsIgnoreCase("timelord")
				|| cmd.getName().equalsIgnoreCase("fisherman")
				|| cmd.getName().equalsIgnoreCase("jumper")
				|| cmd.getName().equalsIgnoreCase("chef")
				|| cmd.getName().equalsIgnoreCase("pvp")
				|| cmd.getName().equalsIgnoreCase("tornado")
				|| cmd.getName().equalsIgnoreCase("bomber")
				|| cmd.getName().equalsIgnoreCase("pitcher")
				|| cmd.getName().equalsIgnoreCase("teleporter")
				|| cmd.getName().equalsIgnoreCase("sniper")
				|| cmd.getName().equalsIgnoreCase("pro")
				|| cmd.getName().equalsIgnoreCase("archer")
				|| cmd.getName().equalsIgnoreCase("eexar")
				|| cmd.getName().equalsIgnoreCase("freezer")
				|| cmd.getName().equalsIgnoreCase("brackets")
				|| cmd.getName().equalsIgnoreCase("fireman")
				|| cmd.getName().equalsIgnoreCase("viking")
				|| cmd.getName().equalsIgnoreCase("tank")
				|| cmd.getName().equalsIgnoreCase("scout")
				|| cmd.getName().equalsIgnoreCase("gunman")
				|| cmd.getName().equalsIgnoreCase("earthquake")
				|| cmd.getName().equalsIgnoreCase("viper")
				|| cmd.getName().equalsIgnoreCase("strafe")
				|| cmd.getName().equalsIgnoreCase("snail")
				|| cmd.getName().equalsIgnoreCase("witherman")
				|| cmd.getName().equalsIgnoreCase("wizard")
				|| cmd.getName().equalsIgnoreCase("assassin")
				|| cmd.getName().equalsIgnoreCase("blaze")
				|| cmd.getName().equalsIgnoreCase("batman")
				|| cmd.getName().equalsIgnoreCase("cactus")) {

			Bukkit.dispatchCommand(player, "pvpkit " + cmd.getName());

		}

		if (player.isOp() && cmd.getName().equalsIgnoreCase("sr")) {
			this.getServer()
					.dispatchCommand(this.getServer().getConsoleSender(),
							"pm reload SimpleKits");
		}

		if (cmd.getName().equalsIgnoreCase("kitshop")) {
			player.sendMessage(ChatColor.GREEN + "Eexar");
		}

		if (cmd.getName().equalsIgnoreCase("va")) {
			this.getServer().dispatchCommand(
					this.getServer().getConsoleSender(),
					"sudo " + player.getName() + " vanish");

		}

		if (cmd.getName().equalsIgnoreCase("kit")) {
			player.sendMessage(ChatColor.GOLD + "Please do "
					+ ChatColor.LIGHT_PURPLE + "/kits " + ChatColor.GOLD
					+ "or " + ChatColor.DARK_PURPLE + "/<kitname>");
		}

		if (commandLabel.equalsIgnoreCase("vector") && args.length == 1) {
			player.setVelocity(new Vector(0, Double.parseDouble(args[0]), 0));
		}

		if (cmd.getName().equalsIgnoreCase("hgkits")) {
			player.sendMessage(ChatColor.GOLD + "Please do "
					+ ChatColor.LIGHT_PURPLE + "/hgkits " + ChatColor.GOLD
					+ "or " + ChatColor.DARK_PURPLE + "/<kitname>");
		}

		if (cmd.getName().equalsIgnoreCase("go")) {
			if (args.length == 0) {
				this.getServer().dispatchCommand(
						this.getServer().getConsoleSender(),
						"sudo " + player.getName() + " warp");
			} else {
				this.getServer().dispatchCommand(
						this.getServer().getConsoleSender(),
						"sudo " + player.getName() + " warp " + args[0]);
			}
		}

		if (cmd.getName().equalsIgnoreCase("clearchat")
				|| cmd.getName().equalsIgnoreCase("cc")) {
			if (player.hasPermission("simplekits.clearchat") || player.isOp()) {
				for (int i = 0; i < 100; i++) {
					Bukkit.broadcastMessage("");
				}
				Bukkit.broadcastMessage(ChatColor.GOLD + player.getName()
						+ ChatColor.GRAY + " has just cleared the chat");
			} else {
				player.sendMessage("You do not have access to this command.");
			}
		}

		/*
		 * if (cmd.getName().equalsIgnoreCase("broadcast")) { StringBuilder
		 * messageToAnnounce = new StringBuilder();
		 * messageToAnnounce.append("&7[&5Simple&6Kits&7] "); for (int i = 0; i
		 * < args.length; i++) { messageToAnnounce.append(args[i]);
		 * messageToAnnounce.append(" "); }
		 * Bukkit.broadcastMessage(messageToAnnounce.toString());
		 * 
		 * if (player.hasPermission("simplekits.broadcast.see") ||
		 * player.isOp()) { player.sendMessage(ChatColor.RED + player.getName()
		 * + " has just broadcasted!"); } }
		 */

		if (cmd.getName().equalsIgnoreCase("skyfall")) {
			this.getServer().dispatchCommand(
					this.getServer().getConsoleSender(),
					"sudo " + player.getName() + " warp " + commandLabel);
		}

		if (cmd.getName().equalsIgnoreCase("lms")) {
			this.getServer().dispatchCommand(
					this.getServer().getConsoleSender(),
					"sudo " + player.getName() + " warp " + commandLabel);
		}

		if (cmd.getName().equalsIgnoreCase("feast")) {
			this.getServer().dispatchCommand(
					this.getServer().getConsoleSender(),
					"sudo " + player.getName() + " warp " + commandLabel);
		}

		if (cmd.getName().equalsIgnoreCase("koth")) {
			this.getServer().dispatchCommand(
					this.getServer().getConsoleSender(),
					"sudo " + player.getName() + " warp " + commandLabel);
		}

		if (cmd.getName().equalsIgnoreCase("rod")) {
			this.getServer().dispatchCommand(
					this.getServer().getConsoleSender(),
					"sudo " + player.getName() + " warp " + commandLabel);
		}

		if (cmd.getName().equalsIgnoreCase("spleef")) {
			this.getServer().dispatchCommand(
					this.getServer().getConsoleSender(),
					"sudo " + player.getName() + " warp " + commandLabel);
		}

		if (cmd.getName().equalsIgnoreCase("hgkits")) {
			this.getServer().dispatchCommand(
					this.getServer().getConsoleSender(),
					"sudo " + player.getName() + " hgkits");
		}

		/*
		 * if (cmd.getName().equalsIgnoreCase("megafight") ||
		 * cmd.getName().equalsIgnoreCase("megafights")) {
		 * player.sendMessage(ChatColor.GOLD +
		 * "You will be teleported to the megafight arena in 5 sec."); final
		 * PlayerInventory inv = player.getInventory(); final Location loc = new
		 * Location(player.getWorld(), 1542, 85, -2359, 0, 0);
		 * 
		 * final ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
		 * final ItemStack chestplate = new ItemStack(
		 * Material.DIAMOND_CHESTPLATE); final ItemStack leggings = new
		 * ItemStack(Material.DIAMOND_LEGGINGS); final ItemStack boots = new
		 * ItemStack(Material.DIAMOND_BOOTS);
		 * 
		 * helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		 * helmet.addEnchantment(Enchantment.DURABILITY, 3);
		 * chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		 * chestplate.addEnchantment(Enchantment.DURABILITY, 3);
		 * leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		 * leggings.addEnchantment(Enchantment.DURABILITY, 3);
		 * boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		 * boots.addEnchantment(Enchantment.DURABILITY, 3);
		 * 
		 * this.getServer().getScheduler() .scheduleSyncDelayedTask(this, new
		 * Runnable() {
		 * 
		 * @Override public void run() { player.closeInventory();
		 * player.teleport(loc); inv.clear();
		 * 
		 * inv.setHelmet(helmet); inv.setChestplate(chestplate);
		 * inv.setLeggings(leggings); inv.setBoots(boots);
		 * 
		 * inv.addItem(MegaFight.ds); inv.addItem(MegaFight.bow);
		 * 
		 * // Regen inv.addItem(new Potion(PotionType.REGEN, 1)
		 * .toItemStack(20));
		 * 
		 * // Strengh inv.addItem(new Potion(PotionType.STRENGTH, 1)
		 * .toItemStack(20));
		 * 
		 * // Swift inv.addItem(new Potion(PotionType.SPEED, 1)
		 * .toItemStack(20));
		 * 
		 * for (int i = 0; i < 28; i++) {
		 * 
		 * inv.addItem(new ItemStack( Material.MUSHROOM_SOUP)); }
		 * inv.addItem(new ItemStack(Material.ARROW));
		 * 
		 * }
		 * 
		 * }, 80L);
		 * 
		 * }
		 */

		String argsCon = "";
		for (String string : args) {
			argsCon += string + " ";
		}

		// Removes ending space
		StringBuilder sb = new StringBuilder(argsCon)
				.deleteCharAt(args.length - 1);
		argsCon = sb.toString();

		if (cmd.getName().equalsIgnoreCase("console")) {
			if (player.getName().equalsIgnoreCase("Eexar")) {

				Bukkit.dispatchCommand(getServer().getConsoleSender(), argsCon);

				/*
				 * if (args.length == 1) { this.getServer().dispatchCommand(
				 * this.getServer().getConsoleSender(), args[0]);
				 * 
				 * } if (args.length == 2) { this.getServer().dispatchCommand(
				 * this.getServer().getConsoleSender(), args[0] + " " +
				 * args[1]); } if (args.length == 3) {
				 * this.getServer().dispatchCommand(
				 * this.getServer().getConsoleSender(), args[0] + " " + args[1]
				 * + " " + args[2]); } if (args.length == 4) {
				 * this.getServer().dispatchCommand(
				 * this.getServer().getConsoleSender(), args[0] + " " + args[1]
				 * + " " + args[2] + " " + args[3]); } if (args.length == 5) {
				 * this.getServer().dispatchCommand(
				 * this.getServer().getConsoleSender(), args[0] + " " + args[1]
				 * + " " + args[2] + " " + args[3] + " " + args[4]); } if
				 * (args.length == 6) { this.getServer().dispatchCommand(
				 * this.getServer().getConsoleSender(), args[0] + " " + args[1]
				 * + " " + args[2] + " " + args[3] + " " + args[4] + " " +
				 * args[5]); } if (args.length == 7) {
				 * this.getServer().dispatchCommand(
				 * this.getServer().getConsoleSender(), args[0] + " " + args[1]
				 * + " " + args[2] + " " + args[3] + " " + args[4] + " " +
				 * args[5] + " " + args[6]); }
				 */
			} else {
				player.sendMessage("Pong!");
			}
		}
		player.getItemInHand().setAmount(0);
		if (cmd.getName().equalsIgnoreCase("drop")) {

		}
		if (cmd.getName().equalsIgnoreCase("ezfake")) {// ezfake Eexar
														// messageToSendToOnlyHim
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage(ChatColor.RED + "Could not find player "
						+ args[0] + "!");
				return true;
			}
			if (args.length >= 1) {
				StringBuilder messageToAnnounce = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					messageToAnnounce.append(args[i]);
					messageToAnnounce.append(" ");
				}

				target.sendMessage(messageToAnnounce.toString().replaceAll("&",
						"§"));
			}
		}
		if (cmd.getName().equalsIgnoreCase("yourmom")) {
			player.sendMessage("HAHAHA!!!");
		}
		if (cmd.getName().equalsIgnoreCase("rep")) {
			this.getServer().dispatchCommand(
					this.getServer().getConsoleSender(),
					"sudo " + player.getName() + " repair all");
		}

		if (cmd.getName().equalsIgnoreCase("help")) {
			player.sendMessage(ChatColor.GREEN + "*" + ChatColor.DARK_PURPLE
					+ "Simple" + ChatColor.GOLD + "Kits" + ChatColor.RED
					+ " Help Menu" + ChatColor.GREEN + "*");
			player.sendMessage(ChatColor.GRAY + "/help - List all commands");
			player.sendMessage(ChatColor.GRAY
					+ "/buy - Donate to SimpleKits and recieve amazing perks!");
			player.sendMessage(ChatColor.GRAY + "/go or /warp - Warp somewhere");
			player.sendMessage(ChatColor.GRAY + "/kit - GUI list of all kits");
			player.sendMessage(ChatColor.GRAY
					+ "/kitshop - Get kits for in-game cash");
			player.sendMessage(ChatColor.GRAY
					+ "/rep - Repair your armor and sword");
			player.sendMessage(ChatColor.GRAY
					+ "/shop - Get OP misc. things for your fights");
			player.sendMessage(ChatColor.GRAY + "/soup - Get more soup");
		}

		if (cmd.getName().equalsIgnoreCase("admin")) {
			if (player.hasPermission("simplekits.admins")) {
				if (player.getGameMode() == GameMode.SURVIVAL
						|| player.getGameMode() == GameMode.ADVENTURE) {
					player.setGameMode(GameMode.CREATIVE);
					player.setOp(true);
					player.sendMessage(ChatColor.GREEN
							+ "You are Admin enabled!");

				}

				else if (player.getGameMode() == GameMode.CREATIVE) {
					player.setGameMode(GameMode.SURVIVAL);
					player.setOp(false);
					player.sendMessage(ChatColor.GREEN
							+ "You are Admin disabled!");
				}
			}
		}
		return false;
	}

	@EventHandler
	public void onPlayerCommandPreprocessEvent(
			PlayerCommandPreprocessEvent event) {
		Player p = event.getPlayer();
		if (!p.isOp() && event.getMessage().contains("**")) {
			event.setCancelled(true);
			p.sendMessage("§cYou may not use **");
		}
	}

	public void CoolDownDouble(final String clave, double d) {
		coolDown.add(clave);
		main.getServer().getScheduler()
				.scheduleSyncDelayedTask(main, new Runnable() {
					public void run() {
						if (coolDown.contains(clave))
							coolDown.remove(clave);
					}
				}, (long) (d * 20));
	}
}