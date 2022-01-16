package me.iceman3920.icecore.Events;

import me.iceman3920.icecore.IceCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;

public class BlockBrokenEvent implements Listener {
    IceCore plugin;
    ArrayList<Player> ore_tracker_players;
    public BlockBrokenEvent(IceCore plugin) {
        this.plugin = plugin;
        ore_tracker_players = plugin.ore_tracker;
    }
    String[] OreTrackerBlocks = {"IRON_ORE", "DEEPSLATE_IRON_ORE", "COAL_ORE", "DEEPSLATE_COAL_ORE",
            "COPPER_ORE", "DEEPSLATE_COPPER_ORE", "GOLD_ORE", "DEEPSLATE_GOLD_ORE", "DIAMOND_ORE", "DEEPSLATE_DIAMOND_ORE",
            "REDSTONE_ORE", "DEEPSLATE_REDSTONE_ORE", "EMERALD_ORE", "DEEPSLATE_EMERALD_ORE", "LAPIS_ORE", "DEEPSLATE_LAPIS_ORE", "ANCIENT_DEBRIS", "NETHER_GOLD_ORE"};
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        for (String s : OreTrackerBlocks) {
            if(e.getBlock().getBlockData().getMaterial().name().equalsIgnoreCase(s)) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if(ore_tracker_players.contains(p)) {
                        p.sendMessage(Color(IceCore.PREFIX + "&3" + e.getPlayer().getName() + " &bHas mined a&3 " + e.getBlock().getBlockData().getMaterial().name()));
                    }
                }
            }
        }

    }

    private String Color(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
