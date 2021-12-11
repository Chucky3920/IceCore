package me.iceman3920.icecore.Events;

import me.iceman3920.icecore.IceCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    IceCore plugin;

    public JoinEvent(final IceCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerJoin(final PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        for (int i = 0; i < this.plugin.invisible_players.size(); ++i) {
            if (!player.hasPermission("icecore.vanish.bypass")) {
                player.hidePlayer(this.plugin, this.plugin.invisible_players.get(i));
            }
        }
    }
}
