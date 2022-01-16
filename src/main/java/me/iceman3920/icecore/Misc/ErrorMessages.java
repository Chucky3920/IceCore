package me.iceman3920.icecore.Misc;

import me.iceman3920.icecore.IceCore;
import org.bukkit.ChatColor;

public class ErrorMessages {

    public ErrorMessages() {

    }




    public String noPermissionCommand = Color(IceCore.PREFIX + "&cError! You do not have enough permissions to execute this command!");


    public String noValidPlayer = Color(IceCore.PREFIX + "&cError! Please specify a real player!");

    public String notEnoughArguments = Color(IceCore.PREFIX + "&cError! Not enough arguments!");

    //public String StaffChat



    private static String Color(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
