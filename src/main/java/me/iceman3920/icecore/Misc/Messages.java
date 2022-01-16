package me.iceman3920.icecore.Misc;

import me.iceman3920.icecore.IceCore;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Messages {

    public String GamemodeChanged(GameMode gm, Player player) {
        return Color(IceCore.PREFIX + "&bYour gamemode has been changed to&3 " + gm);
    }

    public String GamemodeChangedTarget(GameMode gm, Player target) {
        return Color(IceCore.PREFIX + "&3" + target.getName() + " &bgamemode has been changed to&3 " + gm);
    }

    public String StaffChatFormat(Player player) {
        return "&f[&6StaffChat&f] &3" + player.getName() + "&f:&b ";
    }



    private static String Color(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
