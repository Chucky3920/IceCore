package me.iceman3920.icecore.Events;

import me.clip.placeholderapi.PlaceholderAPI;
import me.iceman3920.icecore.IceCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class JoinEvent implements Listener {
    IceCore plugin;

    public JoinEvent(IceCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        for (int i = 0; i < this.plugin.invisible_players.size(); ++i) {
            if (!player.hasPermission("icecore.vanish.bypass")) {
                player.hidePlayer(this.plugin, this.plugin.invisible_players.get(i));
            }
        }



        ArrayList<String> msg2 = (ArrayList<String>) plugin.getConfig().getStringList("motd");
        String[] arr = msg2.toArray(new String[msg2.size()]);
        for (int i = 0; i < msg2.size(); i++)
        {
            arr[i] = PlaceholderAPI.setPlaceholders(e.getPlayer(), arr[i]);
            player.sendMessage(arr[i]);
        }










    }
}
