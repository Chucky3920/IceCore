package me.iceman3920.icecore.Commands;

import me.iceman3920.icecore.IceCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VanishCommand implements CommandExecutor {
    IceCore iceCore;

    public VanishCommand(final IceCore iceCore) {
        this.iceCore = iceCore;
    }

    public boolean onCommand(@NotNull final CommandSender sender, @NotNull final Command command, @NotNull final String label, @NotNull final String[] args) {
        if (command.getName().equalsIgnoreCase("vanish")) {
            this.VanishCmd(sender, args, command);
        }
        return true;
    }

    public void VanishCmd(final CommandSender sender, final String[] args, final Command command) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (this.iceCore.invisible_players.contains(player)) {
                this.iceCore.invisible_players.remove(player);
                player.sendMessage(ChatColor.AQUA + "You are now " + ChatColor.DARK_AQUA + "Visible");
                for (final Player people : Bukkit.getOnlinePlayers()) {
                    if (!people.hasPermission("icecore.vanish.bypass")) {
                        people.showPlayer(this.iceCore, player);
                    }
                }
            }
            else {
                for (final Player people : Bukkit.getOnlinePlayers()) {
                    if (!people.hasPermission("icecore.vanish.bypass")) {
                        people.hidePlayer(this.iceCore, player);
                    }
                }
                this.iceCore.invisible_players.add(player);
                player.sendMessage(ChatColor.AQUA + "You are now " + ChatColor.DARK_AQUA + "Invisible");
            }
        }
    }
}
