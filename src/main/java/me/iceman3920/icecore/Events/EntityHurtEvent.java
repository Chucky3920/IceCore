package me.iceman3920.icecore.Events;

import me.iceman3920.icecore.IceCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;

public class EntityHurtEvent implements Listener {
    IceCore plugin;
    ArrayList<Player> god_players;
    public EntityHurtEvent(IceCore plugin) {
        this.plugin = plugin;
        god_players = plugin.god_players;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if(!(e.getEntity() instanceof Player)) return;

        Player player = (Player) e.getEntity();
        e.setCancelled(god_players.contains(player));


    }
}
