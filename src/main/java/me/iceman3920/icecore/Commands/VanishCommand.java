package me.iceman3920.icecore.Commands;

import me.iceman3920.icecore.IceCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VanishCommand implements CommandExecutor {



    IceCore iceCore;

    public VanishCommand(IceCore iceCore) {
        this.iceCore = iceCore;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("vanish")) {
            VanishCmd(sender, args, command);
        }
        return true;
    }

    public void VanishCmd(CommandSender sender, String[] args, Command command) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(iceCore.invisible_players.contains(player)) {
                iceCore.invisible_players.remove(player);
                player.sendMessage(ChatColor.AQUA + "You are now " + ChatColor.DARK_AQUA + "Visible");
                for(Player people : Bukkit.getOnlinePlayers()) {
                    if(!people.hasPermission("icecore.vanish.bypass")) {
                        people.showPlayer(iceCore, player);
                    }
                }
            }else {
                for(Player people : Bukkit.getOnlinePlayers()) {
                    if(!people.hasPermission("icecore.vanish.bypass")) {
                        people.hidePlayer(iceCore, player);
                    }
                }
                iceCore.invisible_players.add(player);
                player.sendMessage(ChatColor.AQUA + "You are now " + ChatColor.DARK_AQUA + "Invisible");
            }

        }
    }
}
