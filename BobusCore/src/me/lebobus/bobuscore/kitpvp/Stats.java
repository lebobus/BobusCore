package me.lebobus.bobuscore.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.lebobus.bobuscore.Main;
import net.md_5.bungee.api.ChatColor;

public class Stats implements CommandExecutor {
	
	public Files stats;

     public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("stats")) {
			Player p = (Player) sender;
			
			stats = new Files(Main.inst.getDataFolder(), "stats.yml");
	        stats.loadFile();

	        Integer credits = stats.getInt("player."+p.getName()+".credits");
	        Integer kills = stats.getInt("player."+p.getName()+".kills");
	        Integer deaths = stats.getInt("player."+p.getName()+".deaths");
	        Integer kdr = (stats.getInt("player."+p.getName()+".kills")/stats.getInt("player."+p.getName()+".deaths"));
			
			if (args.length == 0 ) {	
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bStats &7:"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Kills : &b"+kills));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Deaths : &b"+deaths));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7KD/R : &b"+kdr));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Credits : &b"+credits));
				return true;
			}
			
			if (args.length > 1) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage : &b/stats &7[&bplayer&7]"));
				return false;
			}
			
			if (args.length == 1) {
				@SuppressWarnings("deprecation")
				OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
				if (!target.hasPlayedBefore()) {
            		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b"+args[0]+"&7 does not exist&7."));
            		return true;
            	}
				
				if (target != null) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b"+target.getPlayer().getName()+"'s stats &7:"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Kills : &b"+kills));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Deaths : &b"+deaths));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7KD/R : &b"+kdr));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Credits : &b"+credits));
					return true;
				}
				
			}
		}
		
		
		
	
		return false;
     }
	
     
     
     
     
     
}
