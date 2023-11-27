package it.mauro.vanillacore.commands.tpa;

import it.mauro.vanillacore.Format;
import it.mauro.vanillacore.commands.tpa.TpaCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpAcceptCommand implements CommandExecutor {

    private final TpaCommand tpaCommand;

    public TpAcceptCommand(TpaCommand tpaCommand) {
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
            player.sendMessage(Format.error("Nessuna richiesta in sospeso!"));
            return true;
        }

        UUID requesterUUID = tpaCommand.getRequester(player.getUniqueId());
        Player requester = Bukkit.getPlayer(requesterUUID);

        if (requester == null || !requester.isOnline()) {
            player.sendMessage(Format.error("Il giocatore a cui hai mandato la richiesta è ora offline!."));
            tpaCommand.removeRequest(player.getUniqueId());
            return true;
        }

        // Accept teleport request
        requester.teleport(player.getLocation());
        player.sendMessage(Format.color("&2Richiesta accettata."));
        requester.sendMessage(Format.color("&e"+player.getName() + " &7ha accettato la tua richiesta!"));

        tpaCommand.removeRequest(player.getUniqueId());

        return true;
    }
}

