package me.lebobus.bobuscore.kitpvp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.lebobus.bobuscore.kitpvp.listeners.KitsShopGUI;
import net.md_5.bungee.api.ChatColor;

public class Shop implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

   	    if (cmd.getName().equalsIgnoreCase("shop")) {
   		    if (args.length > 0) {
   			    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage : &b/shop"));
				return true;
		    } else {
		    	KitsShopGUI.show((Player)sender);
		    }
   	    }
		return false;
    }
			

	
}
