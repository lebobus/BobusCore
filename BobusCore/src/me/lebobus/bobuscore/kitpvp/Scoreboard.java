package me.lebobus.bobuscore.kitpvp;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.lebobus.bobuscore.Main;

public class Scoreboard implements Listener {

	public Files stats;
	
	/*
	@EventHandler
    public void test(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Integer ks = Killstreak.killstreak.get(p.getUniqueId());
		Integer newks = ks + 1;
		Killstreak.killstreak.put(p.getUniqueId(), newks);
		if (Killstreak.killstreak.containsKey(p.getUniqueId())) {
            //Killstreak.killstreak.remove(p.getUniqueId());
            stats = new Files(Main.inst.getDataFolder(), "stats.yml");
            this.stats.loadFile();
            Integer bestks = stats.getInt("player."+p.getName()+".bestkillstreak");
            
            p.sendMessage(newks +"a"+ bestks+"b");
            if (newks > bestks) {
            	stats.set("player."+p.getName()+".bestkillstreak", newks);
            	p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You've set a new &bkillstreak &7record of &b"+newks));
            	stats.saveFile();
            }
		}
		e.getPlayer().sendMessage("debug");
	}
	*/
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
        Killstreak.killstreak.put(player.getUniqueId(), 0);
        Integer bestks = stats.getInt("player."+event.getPlayer().getName()+".bestkillstreak");

        helper.setTitle("&b"+player.getName());
        helper.setSlot(5, "&8» &7Kills &a" + kills);
        helper.setSlot(4, "&8» &7Deaths &a" + deaths);
        helper.setSlot(3, "&8» &7Killstreak &a" + Killstreak.killstreak.get(event.getPlayer().getUniqueId()));
        helper.setSlot(2, "&8» &7Best killstreak &a" + bestks);
        helper.setSlot(1, "&8» &7Credits &a" + credits);

        stats.saveFile();
    }
   
	
	public static void addKill(Player player) {
		Main.inst.stats = new Files(Main.inst.getDataFolder(), "stats.yml");
		Main.inst.stats.loadFile();

        ScoreHelper helper = ScoreHelper.createScore(player);
        
        Integer credits = Main.inst.stats.getInt("player."+player.getName()+".credits");
        Integer kills = Main.inst.stats.getInt("player."+player.getName()+".kills");
        Integer deaths = Main.inst.stats.getInt("player."+player.getName()+".deaths");
        Integer bestks = Main.inst.stats.getInt("player."+player.getPlayer().getName()+".bestkillstreak");
        Integer newkills = kills+1;

        Main.inst.stats.set("player."+player.getName()+".credits", credits);
        Main.inst.stats.set("player."+player.getName()+".kills", newkills);
        Main.inst.stats.set("player."+player.getName()+".deaths", deaths);
        Main.inst.stats.set("player."+player.getName()+".killstreak", Killstreak.killstreak.get(player.getUniqueId()));
        Main.inst.stats.set("player."+player.getName()+".bestkillstreak", bestks);

        helper.setTitle("&b"+player.getName());
        helper.setSlot(5, "&8» &7Kills &a" + newkills);
        helper.setSlot(4, "&8» &7Deaths &a" + deaths);
        helper.setSlot(3, "&8» &7Killstreak &a" + Killstreak.killstreak.get(player.getUniqueId()));
        helper.setSlot(2, "&8» &7Best killstreak &a" + bestks);
        helper.setSlot(1, "&8» &7Credits &a" + credits);

        Main.inst.stats.saveFile();
	}
	
	public static void addDeath(Player player) {
		Main.inst.stats = new Files(Main.inst.getDataFolder(), "stats.yml");
		Main.inst.stats.loadFile();

        ScoreHelper helper = ScoreHelper.createScore(player);
        
        Integer credits = Main.inst.stats.getInt("player."+player.getName()+".credits");
        Integer kills = Main.inst.stats.getInt("player."+player.getName()+".kills");
        Integer deaths = Main.inst.stats.getInt("player."+player.getName()+".deaths");
        Integer bestks = Main.inst.stats.getInt("player."+player.getPlayer().getName()+".bestkillstreak");
        Integer newdeaths = deaths+1;

        Main.inst.stats.set("player."+player.getName()+".credits", credits);
        Main.inst.stats.set("player."+player.getName()+".kills", kills);
        Main.inst.stats.set("player."+player.getName()+".deaths", newdeaths);
        Main.inst.stats.set("player."+player.getName()+".killstreak", Killstreak.killstreak.get(player.getUniqueId()));
        Main.inst.stats.set("player."+player.getName()+".bestkillstreak", bestks);

        helper.setTitle("&b"+player.getName());
        helper.setSlot(5, "&8» &7Kills &a" + kills);
        helper.setSlot(4, "&8» &7Deaths &a" + newdeaths);
        helper.setSlot(3, "&8» &7Killstreak &a" + Killstreak.killstreak.get(player.getUniqueId()));
        helper.setSlot(2, "&8» &7Best killstreak &a" + bestks);
        helper.setSlot(1, "&8» &7Credits &a" + credits);

        Main.inst.stats.saveFile();
	}
	
	public static void addCredits(Player player, Integer credits) {
		Main.inst.stats = new Files(Main.inst.getDataFolder(), "stats.yml");
        Main.inst.stats.loadFile();

        ScoreHelper helper = ScoreHelper.createScore(player);
        
        Integer creditss = Main.inst.stats.getInt("player."+player.getName()+".credits");
        Integer kills = Main.inst.stats.getInt("player."+player.getName()+".kills");
        Integer deaths = Main.inst.stats.getInt("player."+player.getName()+".deaths");
        Integer bestks = Main.inst.stats.getInt("player."+player.getPlayer().getName()+".bestkillstreak");
        Integer totalcredits = creditss+credits;

        Main.inst.stats.set("player."+player.getName()+".credits", totalcredits);
        Main.inst.stats.set("player."+player.getName()+".kills", kills);
        Main.inst.stats.set("player."+player.getName()+".deaths", deaths);
        Main.inst.stats.set("player."+player.getName()+".killstreak", Killstreak.killstreak.get(player.getUniqueId()));
        Main.inst.stats.set("player."+player.getName()+".bestkillstreak", bestks);
        
        helper.setTitle("&b"+player.getName());
        helper.setSlot(5, "&8» &7Kills &a" + kills);
        helper.setSlot(4, "&8» &7Deaths &a" + deaths);
        helper.setSlot(3, "&8» &7Killstreak &a" + Killstreak.killstreak.get(player.getUniqueId()));
        helper.setSlot(2, "&8» &7Best killstreak &a" + bestks);
        helper.setSlot(1, "&8» &7Credits &a" + totalcredits);

        Main.inst.stats.saveFile();
	}
	
	public static void takeCredits(Player player, Integer credits) {
		Main.inst.stats = new Files(Main.inst.getDataFolder(), "stats.yml");
        Main.inst.stats.loadFile();

        ScoreHelper helper = ScoreHelper.createScore(player);
        
        Integer creditss = Main.inst.stats.getInt("player."+player.getName()+".credits");
        Integer kills = Main.inst.stats.getInt("player."+player.getName()+".kills");
        Integer deaths = Main.inst.stats.getInt("player."+player.getName()+".deaths");
        Integer bestks = Main.inst.stats.getInt("player."+player.getPlayer().getName()+".bestkillstreak");
        Integer totalcredits = creditss-credits;

        Main.inst.stats.set("player."+player.getName()+".credits", totalcredits);
        Main.inst.stats.set("player."+player.getName()+".kills", kills);
        Main.inst.stats.set("player."+player.getName()+".deaths", deaths);
        Main.inst.stats.set("player."+player.getName()+".killstreak", Killstreak.killstreak.get(player.getUniqueId()));
        Main.inst.stats.set("player."+player.getName()+".bestkillstreak", bestks);
        
        helper.setTitle("&b"+player.getName());
        helper.setSlot(5, "&8» &7Kills &a" + kills);
        helper.setSlot(4, "&8» &7Deaths &a" + deaths);
        helper.setSlot(3, "&8» &7Killstreak &a" + Killstreak.killstreak.get(player.getUniqueId()));
        helper.setSlot(2, "&8» &7Best killstreak &a" + bestks);
        helper.setSlot(1, "&8» &7Credits &a" + totalcredits);

        Main.inst.stats.saveFile();
	}
	
	public static void addKillstreak(Player player) {
		Main.inst.stats = new Files(Main.inst.getDataFolder(), "stats.yml");
		Main.inst.stats.loadFile();

        ScoreHelper helper = ScoreHelper.createScore(player);
        
        Integer credits = Main.inst.stats.getInt("player."+player.getName()+".credits");
        Integer kills = Main.inst.stats.getInt("player."+player.getName()+".kills");
        Integer deaths = Main.inst.stats.getInt("player."+player.getName()+".deaths");
        Integer bestks = Main.inst.stats.getInt("player."+player.getPlayer().getName()+".bestkillstreak");
        Integer ks = Killstreak.killstreak.get(player.getUniqueId());
        Integer newks = ks + 1;
        
        
        Killstreak.killstreak.put(player.getUniqueId(), newks);
        Main.inst.stats.set("player."+player.getName()+".credits", credits);
        Main.inst.stats.set("player."+player.getName()+".kills", kills);
        Main.inst.stats.set("player."+player.getName()+".deaths", deaths);
        Main.inst.stats.set("player."+player.getName()+".killstreak", Killstreak.killstreak.get(player.getUniqueId()));
        Main.inst.stats.set("player."+player.getName()+".bestkillstreak", bestks);

        helper.setTitle("&b"+player.getName());
        helper.setSlot(5, "&8» &7Kills &a" + kills);
        helper.setSlot(4, "&8» &7Deaths &a" + deaths);
        helper.setSlot(3, "&8» &7Killstreak &a" + newks);
        helper.setSlot(2, "&8» &7Best killstreak &a" + bestks);
        helper.setSlot(1, "&8» &7Credits &a" + credits);

        Main.inst.stats.saveFile();
	}
	
	public static void setBestkillstreak(Player player, Integer bestks) {
		Main.inst.stats = new Files(Main.inst.getDataFolder(), "stats.yml");
		Main.inst.stats.loadFile();

        ScoreHelper helper = ScoreHelper.createScore(player);
        
        Integer credits = Main.inst.stats.getInt("player."+player.getName()+".credits");
        Integer kills = Main.inst.stats.getInt("player."+player.getName()+".kills");
        Integer deaths = Main.inst.stats.getInt("player."+player.getName()+".deaths");
        Integer ks = Killstreak.killstreak.get(player.getUniqueId());


        Main.inst.stats.set("player."+player.getName()+".credits", credits);
        Main.inst.stats.set("player."+player.getName()+".kills", kills);
        Main.inst.stats.set("player."+player.getName()+".deaths", deaths);
        Main.inst.stats.set("player."+player.getName()+".killstreak", Killstreak.killstreak.get(player.getUniqueId()));
        Main.inst.stats.set("player."+player.getName()+".bestkillstreak", bestks);

        helper.setTitle("&b"+player.getName());
        helper.setSlot(5, "&8» &7Kills &a" + kills);
        helper.setSlot(4, "&8» &7Deaths &a" + deaths);
        helper.setSlot(3, "&8» &7Killstreak &a" + ks);
        helper.setSlot(2, "&8» &7Best killstreak &a" + bestks);
        helper.setSlot(1, "&8» &7Credits &a" + credits);

        Main.inst.stats.saveFile();
	}
	
	public static void refreshScoreboard(Player player) {
		Main.inst.stats = new Files(Main.inst.getDataFolder(), "stats.yml");
        Main.inst.stats.loadFile();

        ScoreHelper helper = ScoreHelper.createScore(player);
        
        Integer credits = Main.inst.stats.getInt("player."+player.getName()+".credits");
        Integer kills = Main.inst.stats.getInt("player."+player.getName()+".kills");
        Integer deaths = Main.inst.stats.getInt("player."+player.getName()+".deaths");
        Integer bestks = Main.inst.stats.getInt("player."+player.getPlayer().getName()+".bestkillstreak");
        Integer ks = Killstreak.killstreak.get(player.getUniqueId());

        Main.inst.stats.set("player."+player.getName()+".credits", credits);
        Main.inst.stats.set("player."+player.getName()+".kills", kills);
        Main.inst.stats.set("player."+player.getName()+".deaths", deaths);
        //stats.set("player."+player.getName()+".killstreak", Killstreak.killstreak.get(player.getUniqueId()));
        Main.inst.stats.set("player."+player.getName()+".bestkillstreak", bestks);

        helper.setTitle("&b"+player.getName());
        helper.setSlot(5, "&8» &7Kills &a" + kills);
        helper.setSlot(4, "&8» &7Deaths &a" + deaths);
        helper.setSlot(3, "&8» &7Killstreak &a" + ks);
        helper.setSlot(2, "&8» &7Best killstreak &a" + bestks);
        helper.setSlot(1, "&8» &7Credits &a" + credits);

        Main.inst.stats.saveFile();
	}
	
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(ScoreHelper.hasScore(player)) {
            ScoreHelper.removeScore(player);
        }
    }

	
	
	
}
