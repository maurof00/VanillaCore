package it.mauro.vanillacore.commands;

import it.mauro.vanillacore.Format;
import it.mauro.vanillacore.events.PlayerJoin;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;

        if(player.hasPermission("maurovanilla.commands.gamemode")) {
            if(player.getGameMode() == GameMode.CREATIVE) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(Format.color("&7Gamemode impostata a &eSopravvivenza"));
            } else {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(Format.color("&7Gamemode impostata a &eCreative"));
            }
        }



        return false;
    }
}
