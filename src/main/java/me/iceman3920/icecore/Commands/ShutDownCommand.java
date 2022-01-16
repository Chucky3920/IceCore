package me.iceman3920.icecore.Commands;

import me.iceman3920.icecore.IceCore;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class ShutDownCommand implements CommandExecutor {
    IceCore plugin;
    public ShutDownCommand(IceCore plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("shutdown")) {
            if(args.length == 0) {
                //Bukkit.getServer().shutdown();
            }else {
                BossBar bar = BossBar.bossBar(Component.text("Awesome BossBar"), 1f, BossBar.Color.RED, BossBar.Overlay.PROGRESS);  //"Joe mama", BarColor.RED, BarStyle.SOLID);
                Player p = (Player) sender;
                p.showBossBar(bar);
                String number = args[0].substring((0), args[0].length()-1);
                int Timer;
                try{
                    Timer = Integer.parseInt(number);
                }catch (NumberFormatException ex) {
                    sender.sendMessage(ChatColor.RED + "Error! Message is not valid numbers!");
                    return false;
                }


                if(args[0].toLowerCase().endsWith("w")) {
                    Timer *= 604800;
                }else if(args[0].toLowerCase().endsWith("d")) {
                    Timer *= 86400;
                }else if(args[0].toLowerCase().endsWith("h")) {
                    Timer *= 3600;
                }else if(args[0].toLowerCase().endsWith("m")) {
                    Timer *= 3600;
                }else if(args[0].toLowerCase().endsWith("s")) {

                }else {
                    sender.sendMessage(ChatColor.RED + "Error! Message is not a valid prefix!");
                }


                int finalTimer = Timer;

                Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {

                    double time = finalTimer; //or any other number you want to start countdown from

                    @Override
                    public void run()
                    {

                        if (this.time == 0)
                        {
                            //Bukkit.getServer().shutdown();
                            sender.sendMessage("YEET");
                            p.hideBossBar(bar);


                        }

                        for (final Player player : Bukkit.getOnlinePlayers())
                        {
                            double barProgress = time / finalTimer;
                            bar.progress((float) barProgress);
                            player.sendMessage(this.time + " second(s) remains!");
                        }

                        this.time--;
                    }
                }, 0L, 20L);

            }



        }
        return false;
    }
}
