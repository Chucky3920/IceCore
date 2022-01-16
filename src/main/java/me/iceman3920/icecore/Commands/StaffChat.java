package me.iceman3920.icecore.Commands;

import me.iceman3920.icecore.IceCore;
import me.iceman3920.icecore.Misc.ErrorMessages;
import me.iceman3920.icecore.Misc.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StaffChat implements TabExecutor {

    IceCore plugin;
    public StaffChat(IceCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("staffchat")) {
            if(!(sender instanceof Player)) {
                Bukkit.getLogger().info("Error!");
                return false;
            }

            Player player = (Player) sender;

            if(!player.hasPermission("icecore.staffchat")) { player.sendMessage(new ErrorMessages().noPermissionCommand); return false;}

            if(args.length < 1) {
                player.sendMessage(new ErrorMessages().notEnoughArguments);
                return false;
            }

            if(args[0].equalsIgnoreCase("toggle")) {
                ArrayList<Player> list = plugin.staff_chat_players_list;
                if(list.contains(player)) {
                    list.remove(player);
                    player.sendMessage(IceCore.PREFIX + Color("&bYou now chat in &3Global &bchat"));
                }else {
                    list.add(player);
                    player.sendMessage(IceCore.PREFIX + Color("&bYou now chat in &3Staff &bchat"));
                }


            }else {

                String message = new Messages().StaffChatFormat(player);

                for(String s : args) {
                    message = message + s + " ";
                }

                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(p.hasPermission("icecore.staffchat")) {
                        p.sendMessage(Color(message));
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 12.0f, 1.0f);
                    }
                }
            }
        }
        
        return false;
    }

    private String Color(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("staffchat") && args.length == 1) {

            List<String> tabCompleter = new ArrayList<String>() {{
                add("toggle");
            }};
            Collections.sort(tabCompleter);
            return tabCompleter;
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
