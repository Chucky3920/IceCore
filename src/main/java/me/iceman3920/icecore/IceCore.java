package me.iceman3920.icecore;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import me.iceman3920.icecore.Commands.*;
import me.iceman3920.icecore.Events.*;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class IceCore extends JavaPlugin {

    public static final String PREFIX = Color("&f[&b&lIce&6&lCore&r&f] ");
    public ArrayList<Player> god_players;
    public static ArrayList<Player> staff_chat_players_list;
    public ArrayList<Player> invisible_players;
    public ArrayList<Player> ore_tracker;
    FileConfiguration config;
    WebhookClient client;


    public IceCore() {
        this.config = this.getConfig();
        this.invisible_players = new ArrayList<Player>();
        staff_chat_players_list = new ArrayList<Player>();
        this.god_players = new ArrayList<Player>();
        this.ore_tracker = new ArrayList<Player>();
    }



    public void onEnable() {
        WebhookClientBuilder builder = new WebhookClientBuilder("https://discord.com/api/webhooks/926594417677176912/3ncgkr6HOmkc9eNmxDUJu8EVCz0A2pTbyKUnjbB8SYJ3a0MWmaZA-C0ju93JwA5ocpOG"); // or id, token
        builder.setThreadFactory((job) -> {
            Thread thread = new Thread(job);
            thread.setName("Hello");
            thread.setDaemon(true);
            return thread;
        });
        builder.setWait(true);
        client = builder.build();
        //client.send("Yeet");


        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new MessageSentEvent(this), this);
        getServer().getPluginManager().registerEvents(new CommandProcessEvent(this), this);
        getServer().getPluginManager().registerEvents(new EntityHurtEvent(this), this);
        getServer().getPluginManager().registerEvents(new BlockBrokenEvent(this), this);


        this.saveDefaultConfig();
        this.getCommand("fly").setExecutor(new UtilitiesCommands());
        this.getCommand("heal").setExecutor(new UtilitiesCommands());
        this.getCommand("feed").setExecutor(new UtilitiesCommands());
        this.getCommand("clearchat").setExecutor(new UtilitiesCommands());
        this.getCommand("oretracker").setExecutor(new OreTrackerCommand(this));

        this.getCommand("godmode").setExecutor(new GodModeCommand(this));
        this.getCommand("shutdown").setExecutor(new ShutDownCommand(this));


        this.getCommand("vanish").setExecutor(new VanishCommand(this));
        this.getCommand("staffchat").setExecutor(new StaffChat(this));


        this.getCommand("gamemode").setExecutor(new GamemodeCommand(this));
        this.getCommand("gmc").setExecutor(new GamemodeCommand(this));
        this.getCommand("gms").setExecutor(new GamemodeCommand(this));
        this.getCommand("gmsp").setExecutor(new GamemodeCommand(this));
        this.getCommand("gma").setExecutor(new GamemodeCommand(this));


    }

    public void onDisable() {

    }


    public WebhookClient getClient() {
        return this.client;
    }

    private static String Color(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}

