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

public class UtilitiesCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        UtilitiesHandler(sender, command, label, args);
        return false;
    }

    public void UtilitiesHandler(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(command.getName().equalsIgnoreCase("fly")) {
            FlyCommand(sender, command, args);
        }else if(command.getName().equalsIgnoreCase("heal")) {
            HealFeedCmds(CommandType.HEAL, sender, args);
        }else if(command.getName().equalsIgnoreCase("feed")) {
            HealFeedCmds(CommandType.FEED, sender, args);
        }
    }
    ArrayList<Player> list_of_flying_players = new ArrayList<>();
    private void FlyCommand(CommandSender sender, Command command, String[] args) {

        Player player = (Player) sender;
        if(args.length == 0) {
            if (list_of_flying_players.contains(player)){
                list_of_flying_players.remove(player);
                player.sendMessage(ChatColor.AQUA + "Fly Mode set to False");
                player.setAllowFlight(false);
            }else{
                list_of_flying_players.add(player);
                player.sendMessage(ChatColor.AQUA + "Fly Mode set to True");
                player.setAllowFlight(true);
            }


        }else {
            if(!player.hasPermission("icecore.fly.others")) return;
            Player target = Bukkit.getPlayerExact(args[0]);
            if(target == null) { player.sendMessage(ChatColor.RED + "Error! Please specify a real player"); return; }

            if(list_of_flying_players.contains(target)) {
                list_of_flying_players.remove(target);
                target.sendMessage(ChatColor.AQUA + "Fly Mode set to false");
                player.sendMessage(ChatColor.DARK_AQUA + target.getName() + " fly mode is now set to False");
                target.setAllowFlight(false);


            }
            else {
                list_of_flying_players.add(target);
                target.sendMessage(ChatColor.AQUA + "Fly Mode set to True");
                player.sendMessage(ChatColor.DARK_AQUA + target.getName() + " fly mode is now set to True");
                target.setAllowFlight(true);

            }
        }
    }








    private void HealFeedCmds(CommandType commandType, CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if(commandType.equals(CommandType.FEED) && player.hasPermission("icecore.feed.others")) {

            if(args.length == 0) {
                player.setFoodLevel(20);
                player.setSaturation(20);
                player.sendMessage(ChatColor.AQUA + "You are now fed");
            }else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if(target == null) { player.sendMessage(ChatColor.RED + "Error! Please specify a real player"); return; }
                target.setFoodLevel(20);
                target.setSaturation(20);
                target.sendMessage(ChatColor.AQUA + "You are now fed");
                target.sendMessage(ChatColor.AQUA + "You just fed " + ChatColor.DARK_AQUA + target.getName());
            }
        }else {
            if(args.length == 0) {
                player.setFoodLevel(20);
                player.setSaturation(20);
                player.setHealth(20);
                player.sendMessage(ChatColor.AQUA + "You are now healed");
            }else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if(target == null) { player.sendMessage(ChatColor.RED + "Error! Please specify a real player"); return; }
                target.setFoodLevel(20);
                target.setSaturation(20);
                target.setHealth(20);
                target.sendMessage(ChatColor.AQUA + "You are now healed");
                target.sendMessage(ChatColor.AQUA + "You were just healed " + ChatColor.DARK_AQUA + target.getName());
            }
        }
    }



    enum CommandType {
        HEAL,
        FEED
    }
}

