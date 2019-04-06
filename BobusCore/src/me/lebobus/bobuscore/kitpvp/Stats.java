package me.lebobus.bobuscore.kitpvp;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.lebobus.bobuscore.Main;
import me.lebobus.bobuscore.kitpvp.listeners.Killstreak;
import me.lebobus.bobuscore.utils.Files;
import me.lebobus.bobuscore.utils.IntegerCheck;
import me.lebobus.bobuscore.utils.Scoreboard;
import net.md_5.bungee.api.ChatColor;

public class Stats implements CommandExecutor {
	
	public Files stats;

     public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
    	 if (cmd.getName().equalsIgnoreCase("addcredits")) {
    		 if (args.length == 0 || args.length == 1 || args.length > 2) {
    			 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage : &b/addcredits &7[&bplayer&7] [&bcredits&7]"));
 				 return true;
 			}
 			

 			if (args.length == 2) {
 				@SuppressWarnings("deprecation")
 				OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
				if (stats.getString("player."+target.getName()) == null) {
            		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b"+args[0]+"&7 does not exist&7."));
            		return true;
                }
 				
 				if (!IntegerCheck.isInt(args[1])) {
 					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b"+args[1]+"&7 is not an integer&7."));
             		return true;
 				}
 				
 				if (target != null) {
 					Integer credits = Integer.parseInt(args[1]);
 					Scoreboard.addCredits(target.getPlayer(), credits);
 					String creditsformatted = NumberFormat.getIntegerInstance(Locale.US).format(credits);
 					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have added &b"+creditsformatted+" credits &7to&b "+target.getName()+"&7's balance."));
 					target.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b"+sender.getName()+" &7has added &b"+creditsformatted+" credits &7to your balance."));
 					return true;
 				}
 				
 			}
    	 }
    	 
    	 
    	 if (cmd.getName().equalsIgnoreCase("takecredits")) {
    		 if (args.length == 0 || args.length == 1 || args.length > 2) {
    			 sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage : &b/takecredits &7[&bplayer&7] [&bcredits&7]"));
 				 return true;
 			}
 			

 			if (args.length == 2) {
 				@SuppressWarnings("deprecation")
 				OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
				if (stats.getString("player."+target.getName()) == null) {
            		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b"+args[0]+"&7 does not exist&7."));
            		return true;
                }
 				
 				if (!IntegerCheck.isInt(args[1])) {
 					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b"+args[1]+"&7 is not an integer&7."));
             		return true;
 				}
 				
 				if (target != null) {
 					Integer credits = Integer.parseInt(args[1]);
 					Scoreboard.takeCredits(target.getPlayer(), credits);
 					String creditsformatted = NumberFormat.getIntegerInstance(Locale.US).format(credits);
 					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have taken &c"+creditsformatted+" credits &7from&b "+target.getName()+"&7's balance."));
 					target.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b"+sender.getName()+" &7has taken &c"+creditsformatted+" credits &7from your balance."));
 					return true;
 				}
 				
 			}
    	 }
    	 
    	 
    	 
    	
		if (cmd.getName().equalsIgnoreCase("stats")) {
			Player p = (Player) sender;
			
			stats = new Files(Main.inst.getDataFolder(), "stats.yml");
	        stats.loadFile();

	        DecimalFormat df = new DecimalFormat("#.00");
	        
	        Integer credits = stats.getInt("player."+p.getName()+".credits");
			String creditsformatted = NumberFormat.getIntegerInstance(Locale.US).format(credits);
	        
	        Integer kills = stats.getInt("player."+p.getName()+".kills");
	        Integer deaths = stats.getInt("player."+p.getName()+".deaths");
	        Integer ks = Killstreak.killstreak.get(p.getUniqueId());
	        Integer bestks = stats.getInt("player."+p.getName()+".bestkillstreak");
	        
	        double kdr = ((double)stats.getInt("player."+p.getName()+".kills")/(double)stats.getInt("player."+p.getName()+".deaths"));
	        String kdr2 = df.format(kdr);
			
			if (args.length == 0 ) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-----------------------------------------------------"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Stats :"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Kills : &b"+kills));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Deaths : &b"+deaths));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7KD/R : &b"+kdr2));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Killstreak : &b"+ks));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Best killstreak : &b"+bestks));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Credits : &b"+creditsformatted));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-----------------------------------------------------"));
				return true;
			}
			
			if (args.length > 1) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage : &b/stats &7[&bplayer&7]"));
				return false;
			}
			
			if (args.length == 1) {
				@SuppressWarnings("deprecation")
				OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
				if (stats.getString("player."+target.getName()) == null) {
            		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b"+args[0]+"&7 does not exist&7."));
            		return true;
                }
				
					Integer targetcredits = stats.getInt("player."+target.getName()+".credits");
					String tcreditsformatted = NumberFormat.getIntegerInstance(Locale.US).format(targetcredits);
					
			        Integer targetkills = stats.getInt("player."+target.getName()+".kills");
			        Integer targetdeaths = stats.getInt("player."+target.getName()+".deaths");
			        Integer targetks = Killstreak.killstreak.get(target.getUniqueId());
			        Integer targetbestks = stats.getInt("player."+target.getName()+".bestkillstreak");
			        
			        double targetkdr = ((double)stats.getInt("player."+target.getName()+".kills")/(double)stats.getInt("player."+target.getName()+".deaths"));
			        
			        String targetkdr2 = df.format(targetkdr);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-----------------------------------------------------"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b"+target.getName()+"&7's stats :"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Kills : &b"+targetkills));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Deaths : &b"+targetdeaths));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7KD/R : &b"+targetkdr2));
					if (target.isOnline()) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Killstreak : &b"+targetks));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Killstreak : &b0"));
					}
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Best killstreak : &b"+targetbestks));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Credits : &b"+tcreditsformatted));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-----------------------------------------------------"));
					return true;
				
				
			}
		}
		
		
		
	
		return false;
     }
	
     
     
}
