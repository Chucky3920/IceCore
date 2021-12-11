package me.iceman3920.icecore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch (command.getName().toLowerCase()) {
            case "gmc":
            case "gms":
            case "gmsp":
            case "gma":
                GameModeShortcut(sender, args, command);
                break;
            case "gamemode":
                GamemodeHandler(sender, args, command);
                break;

        }
        return false;
    }

    public void GamemodeHandler(CommandSender sender, String[] args, Command command) {
        if (sender instanceof Player) {
            if (args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0")) {
                this.GamemodeChanger(sender, args, GameMode.SURVIVAL, GamemodeCommand.CommandType.COMMAND);
            }
            else if (args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")) {
                this.GamemodeChanger(sender, args, GameMode.CREATIVE, GamemodeCommand.CommandType.COMMAND);
            }
            else if (args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2")) {
                this.GamemodeChanger(sender, args, GameMode.ADVENTURE, GamemodeCommand.CommandType.COMMAND);
            }
            else if (args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3")) {
                this.GamemodeChanger(sender, args, GameMode.SPECTATOR, GamemodeCommand.CommandType.COMMAND);
            }
            else {
                sender.sendMessage(ChatColor.RED + "Error! Please specify a gamemode!");
            }
        }
    }

    public void GameModeShortcut(CommandSender sender, String[] args, Command command) {
        if (command.getName().equalsIgnoreCase("gmc")) {
            this.GamemodeChanger(sender, args, GameMode.CREATIVE, GamemodeCommand.CommandType.SHORTCUT);
        }
        else if (command.getName().equalsIgnoreCase("gms")) {
            this.GamemodeChanger(sender, args, GameMode.SURVIVAL, GamemodeCommand.CommandType.SHORTCUT);
        }
        else if (command.getName().equalsIgnoreCase("gma")) {
            this.GamemodeChanger(sender, args, GameMode.ADVENTURE, GamemodeCommand.CommandType.SHORTCUT);
        }
        else if (command.getName().equalsIgnoreCase("gmsp")) {
            this.GamemodeChanger(sender, args, GameMode.SPECTATOR, GamemodeCommand.CommandType.SHORTCUT);
        }
    }

    void GamemodeChanger(final CommandSender sender, final String[] args, final GameMode gameMode, final GamemodeCommand.CommandType commandType) {
        final Player player = (Player)sender;
        String gm;
        if (gameMode.equals((GameMode.CREATIVE))) {
            gm = "Creative";
        }
        else if (gameMode.equals((GameMode.SURVIVAL))) {
            gm = "Survival";
        }
        else if (gameMode.equals((GameMode.SPECTATOR))) {
            gm = "Spectator";
        }
        else if (gameMode.equals((GameMode.ADVENTURE))) {
            gm = "Adventure";
        }
        else {
            gm = "error";
        }
        if ((args.length == 0 || args.length == 1) && (commandType.equals((GamemodeCommand.CommandType.COMMAND)) || commandType.equals((GamemodeCommand.CommandType.SHORTCUT)))) {
            player.setGameMode(gameMode);
            player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 12.0f, 1.0f);
            player.sendMessage(ChatColor.AQUA + "Your gamemode has changed to " + ChatColor.DARK_AQUA + gm);
        }
        else if (commandType == GamemodeCommand.CommandType.COMMAND && player.hasPermission("icecore.gamemode.others")) {
            final Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + "Error! Please specify a real player!");
                return;
            }
            target.setGameMode(gameMode);
            target.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 12.0f, 1.0f);
            target.sendMessage(ChatColor.AQUA + "Your gamemode has changed to " + ChatColor.DARK_AQUA + gm);
            player.sendMessage(ChatColor.DARK_AQUA + target.getName() + ChatColor.AQUA +  " gamemode has changed to " + ChatColor.DARK_AQUA + gm);
        }
        else if (commandType == GamemodeCommand.CommandType.SHORTCUT && player.hasPermission("icecore.gamemode.others")) {
            final Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + "Error! Please specify a real player!");
                return;
            }
            target.setGameMode(gameMode);
            target.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 12.0f, 1.0f);
            target.sendMessage(ChatColor.AQUA + "Your gamemode has changed to " + ChatColor.DARK_AQUA + gm);
            player.sendMessage(ChatColor.DARK_AQUA + target.getName() + ChatColor.AQUA +
                    " gamemode has changed to " + ChatColor.DARK_AQUA + gm);
        }
    }



    enum CommandType {SHORTCUT, COMMAND}
}
