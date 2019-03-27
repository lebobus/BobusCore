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
import me.lebobus.bobuscore.kitpvp.KitsListener;
import me.lebobus.bobuscore.kitpvp.Scoreboard;
import me.lebobus.bobuscore.kitpvp.Signs;
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
  
  public void onEnable()
  {
    registerEvents(this, new Listener[] { this });
    registerEvents(this, new Listener[] { new FfaListener(), new KtsListener(), new Menu(null), new MenuInv(), new Signs(), new KitsListener(), new Scoreboard() });
    getCommand("ffa").setExecutor(new Ffa());
    getCommand("ban").setExecutor(new Ban());
    getCommand("pardon").setExecutor(new Ban());
    getCommand("kts").setExecutor(new Kts());
    getCommand("kick").setExecutor(new Kick());
    getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord"); 
    //RandomFireWorks.getManager().addColors();
    //RandomFireWorks.getManager().addTypes();
        
    
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
    
    inst = this;
    
    plugin = this;
  }
  
  public void onDisable()
  {
	  
	  config = new Files(getDataFolder(), "config.yml");
	  stats = new Files(getDataFolder(), "stats.yml");
	  
	  this.config.loadFile();
	  this.config.saveFile();
	  
	  this.stats.loadFile();
	  this.stats.saveFile();

    plugin = null;
  }
  
  public static void registerEvents(Plugin plugin, Listener... listeners)
  {
    Listener[] arrayOfListener;
    int j = (arrayOfListener = listeners).length;
    for (int i = 0; i < j; i++)
    {
      Listener listener = arrayOfListener[i];
      Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
    }
  }
  
  public static Plugin getPlugin()
  {
    return plugin;
  }
}