package me.iceman3920.icecore.Commands;

import me.iceman3920.icecore.IceCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GodModeCommand implements CommandExecutor {
    IceCore plugin;
    ArrayList<Player> god_players;
    public GodModeCommand(IceCore plugin) {
        this.plugin = plugin;
        god_players = plugin.god_players;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("godmode")) {
            if(!(sender instanceof Player)) return false;
            Player player = (Player) sender;

            if(god_players.contains(player)) {
                god_players.remove(player);
                player.sendMessage(Color(IceCore.PREFIX + "&6GodMode &bturned &3Off"));
            }else {
                god_players.add(player);
                player.sendMessage(Color(IceCore.PREFIX + "&6GodMode &bturned &3On"));
            }
        }
        return false;
    }

    private String Color(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
