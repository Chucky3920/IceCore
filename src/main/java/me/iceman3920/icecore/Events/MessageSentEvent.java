package me.iceman3920.icecore.Events;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.iceman3920.icecore.IceCore;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class MessageSentEvent implements Listener  {
    IceCore plugin;
    public MessageSentEvent(IceCore plugin) {
        this.plugin = plugin;
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void MessageSent(AsyncChatEvent e) {
        Player player = e.getPlayer();
        ArrayList<Player> list = IceCore.staff_chat_players_list;
        String message = PlainTextComponentSerializer.plainText().serialize(e.originalMessage());
        if(list.contains(player)) {
            e.setCancelled(true);
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(!player.hasPermission("icecore.staffchat")) return;

                String msg = "&f[&6StaffChat&f] &3" + player.getName() + "&f:&b ";
                p.sendMessage(Color(msg + message));

            }
        }else {
            e.setCancelled(false);
        }

    }
    private String Color(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }



}
