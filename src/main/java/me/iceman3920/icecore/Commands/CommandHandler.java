package me.iceman3920.icecore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandHandler {


    public boolean commandSent(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        switch (command.getName().toLowerCase()) {
            //GAMEMODES
            case "gamemode", "gm" -> new GamemodeCommand().GamemodeHandler(sender, args, command);
            case "gmc", "gms", "gmsp", "gma" -> new GamemodeCommand().GameModeShortcut(sender, args, command);

            //UTILITIES
            case "fly", "heal", "feed" -> new UtilitiesCommands().UtilitiesHandler(sender, command, label, args);
        }
        return false;
    }
}
