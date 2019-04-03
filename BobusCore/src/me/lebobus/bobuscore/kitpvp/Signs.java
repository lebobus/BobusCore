package me.lebobus.bobuscore.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.md_5.bungee.api.ChatColor;

public class Signs implements Listener {

	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
		String alreadyChosen = ChatColor.GRAY + "You've already chosen the " + ChatColor.AQUA + Kits.getKit(e.getPlayer()) + ChatColor.GRAY + " kit.";
            if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
            if (e.getClickedBlock().getState() instanceof Sign) {
                    Sign s = (Sign) e.getClickedBlock().getState();
                    if (s.getLine(1).contains("Server") && s.getLine(2).equalsIgnoreCase("Lobby")) {
                    	//Menu.sendToServer(e.getPlayer(), "lobby");
                    	return;
                    }
                    
                    if (s.getLine(1).contains("Arena") && s.getLine(2).equalsIgnoreCase("Main")) {
                    		e.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 208.5D, 14.0D, 195.5D, 0.0F, 0.0F));
                    		return;
                    }
                    	
                    if (s.getLine(1).contains("Kit") && s.getLine(2).equalsIgnoreCase("PvP")) {
                    	int y = e.getPlayer().getLocation().getBlockY();
                    	if (Kits.hasKit(e.getPlayer()) == true && y >= 14 && !(Kits.getKit(e.getPlayer()).equals(Kits.PvP))) {
                        	Kits.setKit(e.getPlayer(), Kits.PvP);
                        	return;
                    	}
                    	
                    	if (Kits.hasKit(e.getPlayer()) == true && Kits.getKit(e.getPlayer()).equals(Kits.PvP) && y >= 14) {
                        	e.getPlayer().sendMessage(alreadyChosen);
                    		return;
                    	}
                    	
                    	Kits.setKit(e.getPlayer(), Kits.PvP);
                    }
                    
                    if (s.getLine(1).contains("Shop") && s.getLine(2).equalsIgnoreCase("Kits")) {
                    	KitsShopGUI.show(e.getPlayer());
                    }
                    
                    if (s.getLine(1).contains("Kit") && s.getLine(2).equalsIgnoreCase("Fireman")) {
                    	int y = e.getPlayer().getLocation().getBlockY();
                    	if (Kits.hasKit(e.getPlayer()) == true && y >= 14 && !(Kits.getKit(e.getPlayer()).equals(Kits.Fireman))) {
                        	Kits.setKit(e.getPlayer(), Kits.Fireman);
                        	return;
                    	}
                    	
                    	if (Kits.hasKit(e.getPlayer()) == true && Kits.getKit(e.getPlayer()).equals(Kits.Fireman) && y >= 14) {
                        	e.getPlayer().sendMessage(alreadyChosen);
                    		return;
                    	}
                    	
                    	Kits.setKit(e.getPlayer(), Kits.Fireman);
                    }
                    	
                    
                    
                    
                    
            }
    }
	
	
	
	
	
	
	
	
	
	
	
	
}
