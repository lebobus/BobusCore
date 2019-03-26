package me.lebobus.bobuscore.ban;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.BanList;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ban implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		
		
		if (cmd.getName().equalsIgnoreCase("ban")) {
			if (!sender.isOp()) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You are not allowed to &bban&7 people."));
				return true;
			}
			
            if (args.length == 0) {
            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage : &b/ban &7[&bplayer&7] [&breason&7]"));
                    return true;
            }
            
            if (args.length > 2) {
            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage : &b/ban &7[&bplayer&7] [&breason&7]"));
                    return true;
            }
            
            if (args.length < 2) {
            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage : &b/ban &7[&bplayer&7] [&breason&7]"));
                    return true;
            }
            
            OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
            if (args.length == 2) {
            	
            	if (target.isBanned()) {
            		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b"+target.getName()+"&7 is already &bbanned&7."));
            		return true;
            	}
	
            	if (args[1] == null) {
            		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage : &b/ban &7[&bplayer&7] [&creason&7]"));
            		return true;
            	}
            	
            	if (target.isOnline()) {
            		((Player) target).kickPlayer(ChatColor.translateAlternateColorCodes('&', "&7You have been &bbanned &7for&b "+args[1]+"&7."));
            	}

            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), ChatColor.AQUA+args[1], null, sender.getName());
            Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&b"+sender.getName()+" &7has banned&b "+target.getName()+" &7for&b "+args[1]+"&7."));
            }
            return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("pardon")) {
			if (!sender.isOp()) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You are not allowed to &bunban&7 people."));
				return true;
			}
			
			if (args.length == 0) {
            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage : &b/pardon &7[&bplayer&7]"));
                    return true;
            }
            
            if (args.length > 1) {
            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage : &b/pardon &7[&bplayer&7]"));
                    return true;
            }
            
            if (args.length < 1) {
            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage : &b/pardon &7[&bplayer&7]"));
                    return true;
            }
            
            OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
            if (target.isBanned()) {
            	Bukkit.getBanList(BanList.Type.NAME).pardon(target.getName());
            	Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&b"+sender.getName()+" &7has unbanned&b "+target.getName()+"&7."));
            } else {
            	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b"+target.getName()+"&7 is not &bbanned&7."));
            	return true;
		}
	}
		
    return true;
		
	}
	
	
}
