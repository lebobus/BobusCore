package me.lebobus.bobuscore.kitpvp;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.lebobus.bobuscore.Main;

public class Scoreboard implements Listener {

	public Files stats;
	
	
	@EventHandler
    public void test(BlockBreakEvent e) {
		addKill(e.getPlayer());
		addDeath(e.getPlayer());
		addCredits(e.getPlayer());
	}
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        stats = new Files(Main.inst.getDataFolder(), "stats.yml");
        stats.loadFile();

        ScoreHelper helper = ScoreHelper.createScore(player);
        
        
        Integer credits = stats.getInt("player."+player.getName()+".credits");
        Integer kills = stats.getInt("player."+player.getName()+".kills");
        Integer deaths = stats.getInt("player."+player.getName()+".deaths");

        stats.set("player."+player.getName()+".credits", credits);
        stats.set("player."+player.getName()+".kills", kills);
        stats.set("player."+player.getName()+".deaths", deaths);

        helper.setTitle("&b"+player.getName());
        helper.setSlot(3, "&8» &7Kills &a" + kills);
        helper.setSlot(2, "&8» &7Deaths &a" + deaths);
        helper.setSlot(1, "&8» &7Credits &a" + credits);

        stats.saveFile();
    }
   
	
	public void addKill(Player player) {
        stats = new Files(Main.inst.getDataFolder(), "stats.yml");
        stats.loadFile();

        ScoreHelper helper = ScoreHelper.createScore(player);
        
        Integer credits = stats.getInt("player."+player.getName()+".credits");
        Integer kills = stats.getInt("player."+player.getName()+".kills");
        Integer deaths = stats.getInt("player."+player.getName()+".deaths");

        kills++;

        stats.set("player."+player.getName()+".credits", credits);
        stats.set("player."+player.getName()+".kills", kills);
        stats.set("player."+player.getName()+".deaths", deaths);

        helper.setTitle("&b"+player.getName());
        helper.setSlot(3, "&8» &7Kills &a" + kills);
        helper.setSlot(2, "&8» &7Deaths &a" + deaths);
        helper.setSlot(1, "&8» &7Credits &a" + credits);

        stats.saveFile();
	}
	
	public void addDeath(Player player) {
        stats = new Files(Main.inst.getDataFolder(), "stats.yml");
        stats.loadFile();

        ScoreHelper helper = ScoreHelper.createScore(player);
        
        Integer credits = stats.getInt("player."+player.getName()+".credits");
        Integer kills = stats.getInt("player."+player.getName()+".kills");
        Integer deaths = stats.getInt("player."+player.getName()+".deaths");

        deaths++;

        stats.set("player."+player.getName()+".credits", credits);
        stats.set("player."+player.getName()+".kills", kills);
        stats.set("player."+player.getName()+".deaths", deaths);

        helper.setTitle("&b"+player.getName());
        helper.setSlot(3, "&8» &7Kills &a" + kills);
        helper.setSlot(2, "&8» &7Deaths &a" + deaths);
        helper.setSlot(1, "&8» &7Credits &a" + credits);

        stats.saveFile();
	}
	
	public void addCredits(Player player) {
        stats = new Files(Main.inst.getDataFolder(), "stats.yml");
        stats.loadFile();

        ScoreHelper helper = ScoreHelper.createScore(player);
        
        Integer credits = stats.getInt("player."+player.getName()+".credits");
        Integer kills = stats.getInt("player."+player.getName()+".kills");
        Integer deaths = stats.getInt("player."+player.getName()+".deaths");

        credits++;

        stats.set("player."+player.getName()+".credits", credits);
        stats.set("player."+player.getName()+".kills", kills);
        stats.set("player."+player.getName()+".deaths", deaths);

        helper.setTitle("&b"+player.getName());
        helper.setSlot(3, "&8» &7Kills &a" + kills);
        helper.setSlot(2, "&8» &7Deaths &a" + deaths);
        helper.setSlot(1, "&8» &7Credits &a" + credits);

        stats.saveFile();
	}
	
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(ScoreHelper.hasScore(player)) {
            ScoreHelper.removeScore(player);
        }
    }

	
	
	
}
