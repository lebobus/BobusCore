package me.lebobus.bobuscore.kitpvp;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.lebobus.bobuscore.Main;

public class KitsListener implements Listener {
	
	public Files config;
    public Files stats;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = (Player) e.getPlayer();
		
	    stats = new Files(Main.inst.getDataFolder(), "stats.yml");
		stats.loadFile();
		
	  if(!p.hasPlayedBefore()) {
		stats.set("player"+"."+p.getName()+"."+"kills", 0);
	    stats.set("player"+"."+p.getName()+"."+"deaths", 0);
	    stats.set("player"+"."+p.getName()+"."+"kdr", 0);
	    stats.set("player"+"."+p.getName()+"."+"killstreak", 0);
	    stats.set("player"+"."+p.getName()+"."+"bestkillstreak", 0);
	    stats.set("player"+"."+p.getName()+"."+"credits", 0);
        stats.saveFile();
	  } 
   }
	
	
}
