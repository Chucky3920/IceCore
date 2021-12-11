package me.iceman3920.icecore;

import me.iceman3920.icecore.Commands.CommandHandler;
import me.iceman3920.icecore.Commands.UtilitiesCommands;
import me.iceman3920.icecore.Commands.VanishCommand;
import me.iceman3920.icecore.Events.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class IceCore extends JavaPlugin {
    public static final String PREFIX = "[&l&bIceCore&r] ";
    FileConfiguration config = getConfig();
    List<String> motd;

    public ArrayList<Player> invisible_players = new ArrayList<>();

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[IceCore] " + ChatColor.GOLD +"Starting up");


        //Events
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);

        //Config place
        saveDefaultConfig();

        Objects.requireNonNull(getCommand("fly")).setExecutor(new UtilitiesCommands());
        Objects.requireNonNull(getCommand("vanish")).setExecutor(new VanishCommand(this));





        Bukkit.getLogger().info("[IceCore] " + ChatColor.GREEN +"Has started");
    }



    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[IceCore] " + ChatColor.RED + "Shutting down");

        Bukkit.getLogger().info("[IceCore] " + ChatColor.RED + "Has shut down");
    }






    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        new CommandHandler().commandSent(sender, command, label, args);
        return super.onCommand(sender, command, label, args);
    }

    public FileConfiguration getConfigFile() {
        return config;
    }
}


