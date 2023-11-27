package it.mauro.vanillacore.commands;

import it.mauro.vanillacore.Format;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(Format.error("Comando solo per giocatori!"));
        }

        Player player = (Player) commandSender;
        if(!player.hasPermission("maurosurvival.commands.heal")) {
            player.sendMessage(Format.error("Non hai abbastanza permessi!"));
        } else {
            player.setHealth(20);
            player.setFoodLevel(20);
            player.sendMessage(Format.color("&7Giocatore &eCurato&7 con successo!"));
        }



        return false;
    }
}
