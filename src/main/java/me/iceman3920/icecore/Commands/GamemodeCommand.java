package me.iceman3920.icecore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand {

    public void GamemodeHandler(CommandSender sender, String[] args, Command command) {
        if(sender instanceof Player) {
            if(args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0")) {
                GamemodeChanger(sender, args, GameMode.SURVIVAL, CommandType.COMMAND);
            }else if(args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")) {
                GamemodeChanger(sender, args, GameMode.CREATIVE, CommandType.COMMAND);
            }else if(args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2")) {
                GamemodeChanger(sender, args, GameMode.ADVENTURE, CommandType.COMMAND);
            }else if(args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3")) {
                GamemodeChanger(sender, args, GameMode.SPECTATOR, CommandType.COMMAND);
            } else {
                sender.sendMessage(ChatColor.RED + "Error! Please specify a gamemode!");
            }
        }else {
            sender.sendMessage(ChatColor.RED + "Error! You are not a player!");
        }
    }

    public void GameModeShortcut(CommandSender sender, String[] args, Command command) {
        if(command.getName().equalsIgnoreCase("gmc")) {
            GamemodeChanger(sender, args, GameMode.CREATIVE, CommandType.SHORTCUT);
        }else if (command.getName().equalsIgnoreCase("gms")) {
            GamemodeChanger(sender, args, GameMode.SURVIVAL, CommandType.SHORTCUT);
        }else if (command.getName().equalsIgnoreCase("gma")) {
            GamemodeChanger(sender, args, GameMode.ADVENTURE, CommandType.SHORTCUT);
        }else if (command.getName().equalsIgnoreCase("gmsp")) {
            GamemodeChanger(sender, args, GameMode.SPECTATOR, CommandType.SHORTCUT);
        }
    }


    void GamemodeChanger(CommandSender sender, String[] args, GameMode gameMode, CommandType commandType) {
        Player player = (Player) sender;
        String gm;
        if(gameMode.equals(GameMode.CREATIVE)) gm = "Creative";
        else if(gameMode.equals(GameMode.SURVIVAL)) gm = "Survival";
        else if(gameMode.equals(GameMode.SPECTATOR)) gm = "Spectator";
        else if(gameMode.equals(GameMode.ADVENTURE)) gm = "Adventure";
        else gm = "error";



        if((args.length == 0 || args.length == 1) && (commandType.equals(CommandType.COMMAND) ||commandType.equals(CommandType.SHORTCUT))) {
            player.setGameMode(gameMode);
            player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 12f, 1f);
            player.sendMessage(ChatColor.AQUA + "Your gamemode is now " + ChatColor.DARK_AQUA + gm);

        }else if(commandType == CommandType.COMMAND && player.hasPermission("icecore.gamemode.others")) {
            Player target = Bukkit.getPlayerExact(args[1]);
            if(target == null) { player.sendMessage(ChatColor.RED + "Error! Please specify a real player"); return; }

            target.setGameMode(gameMode);
            target.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 12f, 1f);
            target.sendMessage(ChatColor.AQUA + "Your gamemode is now " + ChatColor.DARK_AQUA + gm);
            player.sendMessage(ChatColor.DARK_AQUA + target.getName() + ChatColor.AQUA + " gamemode is now " + ChatColor.DARK_AQUA + gm);

        }else if(commandType == CommandType.SHORTCUT && player.hasPermission("icecore.gamemode.others")){
            Player target = Bukkit.getPlayerExact(args[0]);
            if(target == null) { player.sendMessage(ChatColor.RED + "Error! Please specify a real player"); return; }

            target.setGameMode(gameMode);
            target.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 12f, 1f);
            target.sendMessage(ChatColor.AQUA + "Your gamemode is now " + ChatColor.DARK_AQUA + gm);
            player.sendMessage(ChatColor.DARK_AQUA + target.getName() + ChatColor.AQUA + " gamemode is now " + ChatColor.DARK_AQUA + gm);
        }


    }

    private enum CommandType {
        COMMAND,
        SHORTCUT
    }


}
