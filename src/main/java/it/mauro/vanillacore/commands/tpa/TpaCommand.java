package it.mauro.vanillacore.commands.tpa;

import it.mauro.vanillacore.Format;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TpaCommand implements CommandExecutor {

    private final Map<UUID, UUID> teleportRequests = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(Format.errorUsage("Usa: /tpa <player>"));
            return true;
        }

        String targetName = args[0];
        Player target = Bukkit.getPlayer(targetName);

        if (target == null || !target.isOnline()) {
            player.sendMessage(Format.error("Giocatore offline!"));
            return true;
        }

        // Check if there is an existing request from the player
        if (teleportRequests.containsKey(player.getUniqueId())) {
            player.sendMessage(Format.error("Hai già una richiesta in sospeso!"));
            return true;
        }

        // Send teleport request
        player.sendMessage(Format.color("&7Richiesta mandata a &e" + target.getName()));
        target.sendMessage(Format.color("&e"+player.getName() + " &7ti ha mandato una richiesta. " +
                "&eUsa /tpaccept o /tpreject."));

        // Store the teleport request
        teleportRequests.put(target.getUniqueId(), player.getUniqueId());

        return true;
    }

    public boolean hasRequest(UUID playerUUID) {
        return teleportRequests.containsKey(playerUUID);
    }

    public UUID getRequester(UUID targetUUID) {
        return teleportRequests.get(targetUUID);
    }

    public void removeRequest(UUID playerUUID) {
        teleportRequests.remove(playerUUID);
    }
}

