package us.simplekits.Eexar;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public static final Logger logger = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdfFile;

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pdfFile = getDescription();
		String pluginFolder = getDataFolder().getAbsolutePath();
		new File(pluginFolder).mkdirs();
		pm.registerEvents(new KillStreaks(this), this);
		KillStreaks.list = new ListStore(new File(pluginFolder + File.separator
				+ "killstreaks.txt"));
		KillStreaks.list.load();

		logger.info("<<["
				+ pdfFile.getName()
				+ "]>> "
				+ "By Christian || Eexar fron simplekits.us Successfully Loaded!");

	}

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		logger.info("<<[ " + pdfFile.getName()
				+ " ]>> By Christian || Eexar from simplekits.us Disabled! ):");
		KillStreaks.list.save();
	}
}
