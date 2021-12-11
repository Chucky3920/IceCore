package me.iceman3920.icecore;

import me.iceman3920.icecore.Commands.UtilitiesCommands;
import me.iceman3920.icecore.Commands.VanishCommand;
import me.iceman3920.icecore.Events.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class IceCore extends JavaPlugin {

    public static final String PREFIX = "[&l&bIceCore&r] ";
    FileConfiguration config;
    List<String> motd;
    public ArrayList<Player> invisible_players;

    public IceCore() {
        this.config = this.getConfig();
        this.invisible_players = new ArrayList<Player>();
    }

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents((Listener)new JoinEvent(this), (Plugin)this);
        this.saveDefaultConfig();
        Objects.requireNonNull(this.getCommand("fly")).setExecutor(new UtilitiesCommands());
        Objects.requireNonNull(this.getCommand("vanish")).setExecutor(new VanishCommand(this));
    }


    public boolean onCommand(@NotNull final CommandSender sender, @NotNull final Command command, @NotNull final String label, @NotNull final String[] args) {
        return super.onCommand(sender, command, label, args);
    }

    public FileConfiguration getConfigFile() {
        return this.config;
    }
}

