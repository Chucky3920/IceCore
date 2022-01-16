package me.iceman3920.icecore.Commands;

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
        if (command.getName().equalsIgnoreCase("fly")) {
            this.FlyCommand(sender, command, args);
        }
        else if (command.getName().equalsIgnoreCase("heal")) {
            this.HealFeedCmds(UtilitiesCommands.CommandType.HEAL, sender, args);
        }
        else if (command.getName().equalsIgnoreCase("feed")) {
            this.HealFeedCmds(UtilitiesCommands.CommandType.FEED, sender, args);
        }
        else if (command.getName().equalsIgnoreCase("clearchat")) {
            this.ClearChat(sender, args);
        }
        return false;
    }

    ArrayList<Player> list_of_flying_players;

    public UtilitiesCommands() {
        this.list_of_flying_players = new ArrayList<Player>();
    }


    private void ClearChat(CommandSender sender, String[] args) {
        if(args.length == 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                for (int i = 0; i < 150; i++)
                {

                    player.sendMessage(" ");
                }
            }
        }else {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Error! Please specify a real player!");
                return;
            }
            for (int i = 0; i < 150; i++)
            {

                target.sendMessage(" ");
            }
        }
    }


    private void FlyCommand(CommandSender sender,  Command command,  String[] args) {
         Player player = (Player)sender;
        if (args.length == 0) {
            if (this.list_of_flying_players.contains(player)) {
                this.list_of_flying_players.remove(player);
                player.sendMessage(ChatColor.DARK_AQUA + "You can no longer fly");
                player.setAllowFlight(false);
            }
            else {
                this.list_of_flying_players.add(player);
                player.sendMessage(ChatColor.DARK_AQUA + "You can now fly");
                player.setAllowFlight(true);
            }
        }
        else {
            if (!player.hasPermission("icecore.fly.others")) {
                return;
            }
             Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + "Error! Please specify a real player!");
                return;
            }
            if (this.list_of_flying_players.contains(target)) {
                this.list_of_flying_players.remove(target);
                target.sendMessage(ChatColor.AQUA + "You can now fly");
                player.sendMessage(ChatColor.DARK_AQUA + target.getName() + ChatColor.AQUA + " can now fly");
                target.setAllowFlight(false);
            }
            else {
                this.list_of_flying_players.add(target);
                target.sendMessage(ChatColor.AQUA + "You can now fly");
                player.sendMessage(ChatColor.DARK_AQUA + target.getName() + ChatColor.AQUA + " can now fly");
                target.setAllowFlight(true);
            }
        }
    }

    private void HealFeedCmds(UtilitiesCommands.CommandType commandType,  CommandSender sender,  String[] args) {
         Player player = (Player)sender;
        if (commandType.equals(UtilitiesCommands.CommandType.FEED) && player.hasPermission("icecore.feed.others")) {
            if (args.length == 0) {
                player.setFoodLevel(20);
                player.setSaturation(20.0f);
                player.sendMessage(ChatColor.AQUA + "You have now been fed");
            }
            else {
                 Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Error! Please specify a real player!");
                    return;
                }
                target.setFoodLevel(20);
                target.setSaturation(20.0f);
                target.sendMessage(ChatColor.AQUA + "You have now been fed");
                player.sendMessage(ChatColor.DARK_AQUA + target.getName() + " Has now been fed");
            }
        }
        else if (args.length == 0) {
            player.setFoodLevel(20);
            player.setSaturation(20.0f);
            player.setHealth(20.0);
            player.sendMessage(ChatColor.AQUA + "You have been healed");
        }
        else {
             Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + "Error! Please specify a real player!");
                return;
            }
            target.setFoodLevel(20);
            target.setSaturation(20.0f);
            target.setHealth(20.0);
            target.sendMessage(ChatColor.AQUA + "You have been healed");
            player.sendMessage(ChatColor.DARK_AQUA + target.getName() + " Has been healed");
        }
    }



    enum CommandType {HEAL,FEED}
}

