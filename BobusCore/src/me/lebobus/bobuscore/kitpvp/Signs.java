package me.lebobus.bobuscore.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.lebobus.bobuscore.lobby.Menu;
import net.md_5.bungee.api.ChatColor;

public class Signs implements Listener {

	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
            if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
            if (e.getClickedBlock().getState() instanceof Sign) {
                    Sign s = (Sign) e.getClickedBlock().getState();
                    if (s.getLine(1).contains("Server") && s.getLine(2).equalsIgnoreCase("Lobby")) {
                    	Menu.sendToServer(e.getPlayer(), "lobby");
                    	return;
                    }
                    
                    if (s.getLine(1).contains("Arena") && s.getLine(2).equalsIgnoreCase("Main")) {
                    	int y = e.getPlayer().getLocation().getBlockY();
                    	if (Kits.hasKit(e.getPlayer()) == true && y >= 14) {
                    		e.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 208.5D, 14.0D, 195.5D, 0.0F, 0.0F));
                        	Kits.setKit(e.getPlayer(), Kits.PvP);
                    		return;
                    	}
                    	
                    	if (Kits.hasKit(e.getPlayer()) == true && y < 14) {
                        	e.getPlayer().sendMessage(ChatColor.GRAY + "You already have chosen " + ChatColor.AQUA + Kits.getKitName(e.getPlayer()));
                    		return;
                    	}
                    
                    	
                    	e.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 208.5D, 14.0D, 195.5D, 0.0F, 0.0F));
                    	Kits.setKit(e.getPlayer(), Kits.PvP);
                    	return;
                    }
                    
                    
                    
                    
            }
    }
	
	
	
	
	
	
	
	
	
	
	
	
}
