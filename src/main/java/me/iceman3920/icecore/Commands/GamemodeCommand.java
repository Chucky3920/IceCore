package me.iceman3920.icecore.Commands;

import me.iceman3920.icecore.IceCore;
import me.iceman3920.icecore.Misc.ErrorMessages;
import me.iceman3920.icecore.Misc.Messages;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamemodeCommand implements TabExecutor {
    IceCore plugin;


    public GamemodeCommand(IceCore plugin) {
        this.plugin = plugin;

    }

    ErrorMessages error = new ErrorMessages();
    Messages messages = new Messages();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("gamemode")) {
            if(args.length == 0){ player.sendMessage(error.notEnoughArguments); return false; }


            if(args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")) {
                GamemodeChanger(player, GameMode.SURVIVAL, args, type.NORMAL);
            }else if(args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")) {
                GamemodeChanger(player, GameMode.CREATIVE, args, type.NORMAL);
            }else if(args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")) {
                GamemodeChanger(player, GameMode.ADVENTURE, args, type.NORMAL);
            }else if(args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("4") || args[0].equalsIgnoreCase("sp")) {
                GamemodeChanger(player, GameMode.SPECTATOR, args, type.NORMAL);
            }




        }else if(command.getName().equalsIgnoreCase("gms")) {
            GamemodeChanger(player, GameMode.SURVIVAL, args, type.SHORT);
        }else if(command.getName().equalsIgnoreCase("gmc")) {
            GamemodeChanger(player, GameMode.CREATIVE, args, type.SHORT);
        }else if(command.getName().equalsIgnoreCase("gma")) {
            GamemodeChanger(player, GameMode.ADVENTURE, args, type.SHORT);
        }else if(command.getName().equalsIgnoreCase("gmsp")) {
            GamemodeChanger(player, GameMode.SPECTATOR, args, type.SHORT);
        }


        return false;
    }
    enum type{SHORT, NORMAL}
    void GamemodeChanger(Player player, GameMode gm, String[] args, type t) {
        boolean isShort;
        Player target = null;
        isShort = t == type.SHORT;
        if(isShort && args.length >= 1) {
            target = Bukkit.getPlayerExact(args[0]);
        }else if(!isShort && args.length >= 2){
            target = Bukkit.getPlayerExact(args[1]);
        }



        if(target != null) {
            target.setGameMode(gm);
            target.sendMessage(messages.GamemodeChangedTarget(gm, player));
            player.sendMessage(messages.GamemodeChanged(gm, player));
        }else {
            player.setGameMode(gm);
            player.sendMessage(messages.GamemodeChanged(gm, player));
        }
    }







    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("gamemode") && args.length == 1) {

            List<String> gamemodes = new ArrayList<>() {{
                add("creative"); add("adventure"); add("survival"); add("spectator");
            }};
            Collections.sort(gamemodes);
            return gamemodes;
        }else {
            List<String> players = new ArrayList<>();
            for(Player p : Bukkit.getOnlinePlayers()) {
                players.add(p.getName());
            }
            Collections.sort(players);
            return players;
        }
    }
}
