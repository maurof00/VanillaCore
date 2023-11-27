package it.mauro.vanillacore.commands.economy;

import it.mauro.vanillacore.Format;
import it.mauro.vanillacore.database.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

    private final DatabaseManager databaseManager;

    public BalanceCommand(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(Format.error("Comando solo per giocatori!"));
        }
        Player p = (Player) commandSender;

        double balance = databaseManager.getBalance(p);
        if(strings.length == 0) {
            p.sendMessage(Format.color("&7Conto: &e%soldi%")
                    .replace("%soldi%", String.valueOf(balance)));
        } else if (strings.length == 1) {
            String player2 = strings[0];
            Player target = Bukkit.getPlayer(player2);
            double balancet = databaseManager.getBalance(target);
            if(target == null) {
                p.sendMessage(Format.error("Giocatore offline!"));
            }
            p.sendMessage(Format.color("&7Conto di %target%: &e%soldi%")
                    .replace("%soldi%", String.valueOf(balance))
                    .replace("%target%", player2));
        }


        return false;
    }
}
