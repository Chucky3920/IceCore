package me.iceman3920.icecore.Events;

import me.iceman3920.icecore.IceCore;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class CommandProcessEvent implements Listener {
    IceCore plugin;
    public CommandProcessEvent(IceCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerCommandProcess(PlayerCommandPreprocessEvent e) {
        List<String> list = plugin.getConfig().getStringList("disabled-commands");
        for(String s : list) {
            if(e.getMessage().equalsIgnoreCase("/" + s)) {
                if(!e.getPlayer().isOp()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(Color("&cYou are not allowed to execute this command!"));
                }

            }
        }
    }
    private String Color(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
