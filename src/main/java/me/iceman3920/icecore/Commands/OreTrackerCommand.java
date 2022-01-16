package me.iceman3920.icecore.Commands;

import me.iceman3920.icecore.IceCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OreTrackerCommand implements CommandExecutor {
    IceCore plugin;
    ArrayList<Player> ore_tracker_players;
    public OreTrackerCommand(IceCore plugin) {
        this.plugin = plugin;
        ore_tracker_players = plugin.ore_tracker;

    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("oretracker")) {
            if(!(sender instanceof Player)) return false;
            Player player = (Player) sender;
            if(ore_tracker_players.contains(player)) {
                ore_tracker_players.remove(player);
                player.sendMessage(Color( IceCore.PREFIX + "&bYou are no longer tracking ores!"));
            }else {
                ore_tracker_players.add(player);
                player.sendMessage(Color( IceCore.PREFIX + "&6You are now tracking ores!"));
            }
        }
        return false;
    }
    private String Color(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
