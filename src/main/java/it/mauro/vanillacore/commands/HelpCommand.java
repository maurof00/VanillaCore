package it.mauro.vanillacore.commands;

import it.mauro.vanillacore.Format;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;

        if(strings.length == 0) {
            player.sendMessage(Format.color("&7Usa &e/help case / tpa&7."));
        } else if (strings.length == 1 && strings[0].equalsIgnoreCase("case")) {
            player.sendMessage(Format.color("&7&m-----&8&l[&6Case - Aiuto&8&l]&7&m-----"));
            player.sendMessage(Format.color(""));
            player.sendMessage(Format.color(" &8* &7/sethome: &eImposta una casa!"));
            player.sendMessage(Format.color(" &8* &7/delhome: &eCancella una casa!"));
            player.sendMessage(Format.color(" &8* &7/homes: &eLista delle case che possiedi!"));
            player.sendMessage(Format.color(" &8* &7/home: &eVai a una casa che possiedi!"));
            player.sendMessage(Format.color(""));
        } else if (strings.length == 1 && strings[0].equalsIgnoreCase("tpa")) {
            player.sendMessage(Format.color("&7&m-----&8&l[&6Tpa - Aiuto&8&l]&7&m-----"));
            player.sendMessage(Format.color(""));
            player.sendMessage(Format.color(" &8* &7/tpa: &eManda tpa a un giocatore!"));
            player.sendMessage(Format.color(" &8* &7/tpaccept: &eAcetta il tpa di un giocatore!"));
            player.sendMessage(Format.color(" &8* &7/tpareject: &eRifiuta il tpa di un giocatore!"));
            player.sendMessage(Format.color(""));
        }else if (strings.length == 1 && strings[0].equalsIgnoreCase("tp")) {
            player.sendMessage(Format.color("&7&m-----&8&l[&6Tp - Aiuto&8&l]&7&m-----"));
            player.sendMessage(Format.color(""));
            player.sendMessage(Format.color(" &8* &7/tp <player>: &eTp da un giocatore!"));
            player.sendMessage(Format.color(" &8* &7/tp <player> <otherplayer>: &eTp un giocatore ad un altro giocatore!"));
            player.sendMessage(Format.color(" &8* &7/tp <x> <x> <y>: &eTp coordinate!"));
            player.sendMessage(Format.color(""));
        } else {
            player.sendMessage(Format.color("&7Usa &e/help case / tpa / tp&7."));
        }


        return false;
    }
}
