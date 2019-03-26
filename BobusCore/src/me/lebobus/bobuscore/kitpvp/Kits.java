package me.lebobus.bobuscore.kitpvp;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public enum Kits {

        
    PvP, Fireman, ; //Add more here
     
    public static HashMap<UUID, Kits> currentKit = new HashMap<UUID, Kits>();
     
    public static void setKit(Player p, Kits kit){ //save the player's kit inside the HashMap
    currentKit.put(p.getUniqueId(), kit);
    
    if(getKit(p).equals(PvP)) {
    	giveKitPvP(p);
    	p.sendMessage(ChatColor.AQUA + Kits.getKitName(p));
    }
    
    if(getKit(p).equals(Fireman)) {
    	p.sendMessage("fireman");
    }
    
    }
     
    public static Kits getKit(Player p){ //Return the enum (kit) from the player
    return currentKit.get(p.getUniqueId());
    }
     
    public static boolean hasKit(Player p){ //Check if the player has a kit
    if(currentKit.containsKey(p.getUniqueId())) return true;
    return false;
    }
     
    public static void clearPlayer(Player p){ //On leave + reload, for memory leaks
    currentKit.remove(p.getUniqueId());
    }
     
    public static String getKitName(Player p){ //Not sure if actually works lol.
    return getKit(p).toString();
    }
    
	public static void giveKitPvP(Player p) {
		p.getInventory().setArmorContents(null);
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemStack[] armor = new ItemStack[4];
		armor[0] = new ItemStack(Material.IRON_BOOTS);
		armor[1] = new ItemStack(Material.IRON_LEGGINGS);
		armor[2] = new ItemStack(Material.IRON_CHESTPLATE);
		armor[3] = new ItemStack(Material.IRON_HELMET);
		p.getInventory().setItem(0, sword);
		p.getInventory().setArmorContents(armor);
        p.updateInventory();
	}
	
	
	public void onDeath(PlayerDeathEvent e) {
		Player p = ((OfflinePlayer) e).getPlayer();
		
		if(hasKit(p) == true) {
			clearPlayer(p);
		}
	}
	
	
	
	
	
}
