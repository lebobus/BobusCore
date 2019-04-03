package me.lebobus.bobuscore.kitpvp;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.lebobus.bobuscore.Main;

public class KitsShopGUI implements Listener {
	
	private static Inventory inv;
	 
	public Files stats;
	
    public KitsShopGUI() {
    	inv = Bukkit.getServer().createInventory(null, 9, ChatColor.AQUA + "Buy new kits!");
        
    	    ItemStack kitpvp = new ItemStack(Material.IRON_CHESTPLATE);
    	    ItemStack kitarcher = new ItemStack(Material.BOW);
    	    ItemStack kitfireman = new ItemStack(Material.BLAZE_POWDER);
    	    
            ItemMeta kitpvpmetameta = kitpvp.getItemMeta();
            ItemMeta kitarchermeta = kitarcher.getItemMeta();
            ItemMeta kitfiremanmeta = kitfireman.getItemMeta();
            
            kitpvpmetameta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aKit PvP"));
            kitarchermeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aKit Archer"));
            kitfiremanmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cKit Fireman"));
            
            kitpvpmetameta.setLore(Arrays.asList(ChatColor.GREEN + "10,000 credits"));
            kitarchermeta.setLore(Arrays.asList(ChatColor.GREEN + "10,000 credits"));
            kitfiremanmeta.setLore(Arrays.asList(ChatColor.GREEN + "10,000 credits"));
            
            kitpvp.setItemMeta(kitpvpmetameta);
            kitarcher.setItemMeta(kitarchermeta);
            kitfireman.setItemMeta(kitfiremanmeta);
            
            inv.setItem(0, kitpvp);
            inv.setItem(1, kitarcher);
            inv.setItem(2, kitfireman);
            
            return;
    }
    
    
    public static void show(Player p) {
        p.openInventory(inv);
    }
    
    
    @EventHandler
    public void test(BlockBreakEvent e) {
    	Scoreboard.takeCredits(e.getPlayer(), 10);
    }
    
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
    	
    	Player p = (Player) e.getWhoClicked(); 
    	
    	ItemStack t = e.getCurrentItem(); 
    	
    	stats = new Files(Main.inst.getDataFolder(), "stats.yml");
        stats.loadFile();
        
        Integer pcredits = stats.getInt("player."+p.getName()+".credits");
        Integer creditsmissingkitpvp = 10000-pcredits;
        String s = NumberFormat.getIntegerInstance(Locale.US).format(creditsmissingkitpvp);
        
        ArrayList<String> list = (ArrayList<String>) stats.getStringList("player."+p.getName()+".kits");
    	
            if (!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
            if (t == null || t.getType() == Material.AIR) return;
            if (t.getItemMeta() == null) return;
            if (t.getItemMeta().getDisplayName().contains("Kit PvP")) {
            	p.sendMessage("kitpvp");
            	if(stats.getString("player."+p.getName()+".kits").contains("pvp")) {
            	   e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You already possess the &bPvP &7kit."));
            	   e.setCancelled(true);
            	   return;
            	}
            	if (pcredits >= 10000) {
            		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You've bought the &bPvP &7kit for &b10&7,&b000 credits&7."));
            		list.add("pvp");
            		stats.set("player."+p.getName()+".kits", list);
            		stats.saveFile();
            		Scoreboard.takeCredits((Player)e.getWhoClicked(), 10000);
            	} else {
            		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You don't have enough &bcredits&7. You're missing &c"+s+" credits&7."));
            	}
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
            }
            
            
            if (t == null || t.getType() == Material.AIR) return;
            if (t.getItemMeta() == null) return;
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Kit Fireman")) {
            	p.sendMessage("kitfireman");
            	if(stats.getString("player."+p.getName()+".kits").contains("fireman")) {
            	   p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You already possess the &bFireman &7kit."));
            	   e.setCancelled(true);
            	   return;
            	}
            	if (pcredits >= 10000) {
            		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You've bought the &bFireman &7kit for &b10&7,&b000 credits&7."));
            		list.add("fireman");
            		stats.set("player."+p.getName()+".kits", list);
            		
            		// TAKE CREDITS //
                    ScoreHelper helper = ScoreHelper.createScore(p.getPlayer());
                    
                    Integer creditss = stats.getInt("player."+p.getName()+".credits");
                    Integer kills = stats.getInt("player."+p.getName()+".kills");
                    Integer deaths = stats.getInt("player."+p.getName()+".deaths");
                    Integer bestks = stats.getInt("player."+p.getPlayer().getName()+".bestkillstreak");
                    Integer totalcredits = creditss-10000;

                    stats.set("player."+p.getName()+".credits", totalcredits);
                    stats.set("player."+p.getName()+".kills", kills);
                    stats.set("player."+p.getName()+".deaths", deaths);
                    stats.set("player."+p.getName()+".killstreak", Killstreak.killstreak.get(p.getUniqueId()));
                    stats.set("player."+p.getName()+".bestkillstreak", bestks);
                    
                    helper.setTitle("&b"+p.getName());
                    helper.setSlot(5, "&8» &7Kills &a" + kills);
                    helper.setSlot(4, "&8» &7Deaths &a" + deaths);
                    helper.setSlot(3, "&8» &7Killstreak &a" + Killstreak.killstreak.get(p.getUniqueId()));
                    helper.setSlot(2, "&8» &7Best killstreak &a" + bestks);
                    helper.setSlot(1, "&8» &7Credits &a" + totalcredits);
                    //////////////////
                    
            		stats.saveFile();
            	} else {
            		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You don't have enough &bcredits&7. You're missing &c"+s+" credits&7."));
            	}
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
            }
            
            
    }
    
    

}
