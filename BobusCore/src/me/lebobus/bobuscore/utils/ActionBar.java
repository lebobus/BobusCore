package me.lebobus.bobuscore.utils;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;

public class ActionBar
{
  private PacketPlayOutChat packet;
  
  public ActionBar(String text)
  {
    PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}"));
    this.packet = packet;
  }
  
  public void sendToPlayer(Player p)
  {
    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(this.packet);
  }
  
  public void sendToAll()
  {
    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
      ((CraftPlayer)p).getHandle().playerConnection.sendPacket(this.packet);
    }
  }
}
