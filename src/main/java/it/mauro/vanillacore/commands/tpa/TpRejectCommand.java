package it.mauro.vanillacore.commands.tpa;

import it.mauro.vanillacore.Format;
import it.mauro.vanillacore.commands.tpa.TpaCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpRejectCommand implements CommandExecutor {

    private final TpaCommand tpaCommand;

    public TpRejectCommand(TpaCommand tpaCommand) {
        this.tpaCommand = tpaCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (!tpaCommand.hasRequest(player.getUniqueId())) {
            player.sendMessage(Format.color("&eNessuna richiesta in sospeso!"));
            return true;
        }

        UUID requesterUUID = tpaCommand.getRequester(player.getUniqueId());
        Player requester = Bukkit.getPlayer(requesterUUID);

        if (requester == null || !requester.isOnline()) {
            player.sendMessage(Format.error("Il giocatore a cui hai mandato la richiesta è ora offline!."));
            tpaCommand.removeRequest(player.getUniqueId());
            return true;
        }

        // Reject teleport request
        player.sendMessage(Format.color("&cRichiesta rifiutata."));
        requester.sendMessage(Format.color("&e"+player.getName() + " &7ha rifiutato la tua richiesta!"));

        // Remove the teleport request
        tpaCommand.removeRequest(player.getUniqueId());

        return true;
    }
}

