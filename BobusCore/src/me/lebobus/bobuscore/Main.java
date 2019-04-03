package me.lebobus.bobuscore;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.lebobus.bobuscore.ban.Ban;
import me.lebobus.bobuscore.ffa.Ffa;
import me.lebobus.bobuscore.ffa.FfaListener;
import me.lebobus.bobuscore.kick.Kick;
import me.lebobus.bobuscore.kitpvp.Files;
import me.lebobus.bobuscore.kitpvp.Killstreak;
import me.lebobus.bobuscore.kitpvp.KitsListener;
import me.lebobus.bobuscore.kitpvp.KitsShopGUI;
import me.lebobus.bobuscore.kitpvp.ScoreHelper;
import me.lebobus.bobuscore.kitpvp.Scoreboard;
import me.lebobus.bobuscore.kitpvp.Signs;
import me.lebobus.bobuscore.kitpvp.Stats;
import me.lebobus.bobuscore.kts.Kts;
import me.lebobus.bobuscore.kts.KtsListener;
import me.lebobus.bobuscore.lobby.Menu;
import me.lebobus.bobuscore.lobby.MenuInv;

@SuppressWarnings("unused")
public class Main
  extends JavaPlugin
  implements Listener
{
	
	public static Main inst;
	 
	public Files config;
	public Files stats;
	
  private static Plugin plugin;
  
  public void onEnable() {
	  
    registerEvents(this, new Listener[] { this });
    registerEvents(this, new Listener[] { new FfaListener(), new KtsListener(), new Menu(), new MenuInv(), new Signs(), new KitsListener(), new Scoreboard(), new Killstreak(), new KitsShopGUI()  });
    getCommand("ffa").setExecutor(new Ffa());
    getCommand("ban").setExecutor(new Ban());
    getCommand("pardon").setExecutor(new Ban());
    getCommand("kts").setExecutor(new Kts());
    getCommand("kick").setExecutor(new Kick());
    getCommand("stats").setExecutor(new Stats());
    getCommand("addcredits").setExecutor(new Stats());
    getCommand("takecredits").setExecutor(new Stats());
    getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord"); 
        
    
    config = new Files(getDataFolder(), "config.yml");
    stats = new Files(getDataFolder(), "stats.yml");
    
    
    if(!config.fileExists()){
    	config.createFile();
    	config.loadFile();
    	config.saveFile();
    	}
    	 
    if(!stats.fileExists()){
    	stats.createFile();
    	stats.loadFile();
    	stats.saveFile();
    	}
    
    this.stats.loadFile();
    this.config.loadFile();
    
    
    
    for (Player player : Bukkit.getOnlinePlayers()) {
    	Killstreak.killstreak.put(player.getUniqueId(), 0);
    	
    	this.stats = new Files(this.getDataFolder(), "stats.yml");
    	this.stats.loadFile();

        ScoreHelper helper = ScoreHelper.createScore(player);
        
        Integer credits = this.stats.getInt("player."+player.getName()+".credits");
        Integer kills = this.stats.getInt("player."+player.getName()+".kills");
        Integer deaths = this.stats.getInt("player."+player.getName()+".deaths");
        Integer bestks = this.stats.getInt("player."+player.getPlayer().getName()+".bestkillstreak");
        Integer ks = Killstreak.killstreak.get(player.getUniqueId());

        this.stats.set("player."+player.getName()+".credits", credits);
        this.stats.set("player."+player.getName()+".kills", kills);
        this.stats.set("player."+player.getName()+".deaths", deaths);
        this.stats.set("player."+player.getName()+".killstreak", Killstreak.killstreak.get(player.getUniqueId()));
        this.stats.set("player."+player.getName()+".bestkillstreak", bestks);

        helper.setTitle("&b"+player.getName());
        helper.setSlot(5, "&8» &7Kills &a" + kills);
        helper.setSlot(4, "&8» &7Deaths &a" + deaths);
        helper.setSlot(3, "&8» &7Killstreak &a" + ks);
        helper.setSlot(2, "&8» &7Best killstreak &a" + bestks);
        helper.setSlot(1, "&8» &7Credits &a" + credits);

        this.stats.saveFile();
    }
    
    
    inst = this;
    
    plugin = this;
  }
  
  public void onDisable() {
	  
	  config = new Files(getDataFolder(), "config.yml");
	  stats = new Files(getDataFolder(), "stats.yml");
	  
	  this.config.loadFile();
	  this.config.saveFile();
	  
	  this.stats.loadFile();
	  this.stats.saveFile();

    plugin = null;
  }
  
  public static void registerEvents(Plugin plugin, Listener... listeners) {
	  
    Listener[] arrayOfListener;
    int j = (arrayOfListener = listeners).length;
    for (int i = 0; i < j; i++)
    {
      Listener listener = arrayOfListener[i];
      Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
    }
  }
  
  public static Plugin getPlugin() {
    return plugin;
  }
  
}