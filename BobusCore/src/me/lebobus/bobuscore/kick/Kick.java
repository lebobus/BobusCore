package me.lebobus.bobuscore.kick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick implements CommandExecutor {

     public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("kick")) {
			if (!sender.isOp()) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You are not allowed to &bkick&7 people."));
				return true;
			}
			
            if (args.length == 0 || args.length > 1 || args.length < 1) {
            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage : &b/kick &7[&bplayer&7]"));
                    return true;
            }
            
            
            OfflinePlayer target = Bukkit.getServer().getPlayer(args[0]);
            if (args.length == 1) {
            	
            	if (target == null) {
            		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b"+args[0]+"&7 is not online&7."));
            		return true;
            	}
            	
            	if (target.isOnline()) {
            		((Player) target).kickPlayer(ChatColor.translateAlternateColorCodes('&', "&7You have been &bkicked&7."));
            		Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&b"+sender.getName()+" &7has kicked&b "+target.getName()+"&7."));
            		return true;
            	}

            }
            
            
            
		}
	return true;
     }
}
