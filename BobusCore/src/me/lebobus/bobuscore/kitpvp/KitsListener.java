package me.lebobus.bobuscore.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.lebobus.bobuscore.Main;

public class KitsListener implements Listener {
	
	public Files config;
    public Files stats;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = (Player) e.getPlayer();
		
	    stats = new Files(Main.inst.getDataFolder(), "stats.yml");
		stats.loadFile();
		
	  if(!p.hasPlayedBefore()) {
		stats.set("player"+"."+p.getName()+"."+"kills", 0);
	    stats.set("player"+"."+p.getName()+"."+"deaths", 0);
	    stats.set("player"+"."+p.getName()+"."+"kdr", 0);
	    stats.set("player"+"."+p.getName()+"."+"killstreak", 0);
	    stats.set("player"+"."+p.getName()+"."+"bestkillstreak", 0);
	    stats.set("player"+"."+p.getName()+"."+"credits", 0);
	    stats.set("player"+"."+p.getName()+"."+"kits", "");
        stats.saveFile();
	  } 
   }
	
	
    @EventHandler
    public void playerDropItem(PlayerDropItemEvent e) {
    if (e.getItemDrop().getItemStack().getType() == Material.BOWL) {
    	e.getItemDrop().remove();
        }
    }
    
    
    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
    	e.setCancelled(true);
    }
	
    
    @EventHandler
    public void autoRespawn(PlayerDeathEvent e) {
    	Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable()
        {
          public void run()
          {
        	  e.getEntity().spigot().respawn();
          }
        }, 5L);
    	
    }
    

	@SuppressWarnings("deprecation")
	@EventHandler
	public void oanPlayerInteract(PlayerInteractEvent event) {
	   if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	       if (event.getPlayer().getItemInHand().getType() == Material.MUSHROOM_SOUP) {
		       if (event.getHand().equals(EquipmentSlot.HAND)) {
		    	   if (event.getPlayer().getHealth() == 20) return;
	              event.getPlayer().setHealth(event.getPlayer().getHealth()+3);
	              event.getPlayer().setItemInHand(new ItemStack(Material.BOWL));
	           }
	       }   
	   }
    }
	
}
