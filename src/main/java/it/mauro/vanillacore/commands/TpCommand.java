package it.mauro.vanillacore.commands;

import it.mauro.vanillacore.Format;
import it.mauro.vanillacore.MauroSurvival;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.Normalizer;

public class TpCommand implements CommandExecutor {

    private final MauroSurvival plugin;

    public TpCommand(MauroSurvival plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        if(player.hasPermission("maurosurvival.commands.tp")) {
            if (args.length == 1) {
                String targetPlayerName = args[0];
                Player targetPlayer = plugin.getServer().getPlayer(targetPlayerName);

                if (targetPlayer != null) {
                    player.teleport(targetPlayer.getLocation());
                    player.sendMessage(Format.color("&7Teletrasportato da &e%target%")
                            .replace("%target%", targetPlayerName));
                } else {
                    player.sendMessage(Format.error("Giocatore non trovato!"));
                }
            } else if (args.length == 2) {
                // /tp <player1> <player2>
                String sourcePlayerName = args[0];
                String targetPlayerName = args[1];

                Player sourcePlayer = plugin.getServer().getPlayer(sourcePlayerName);
                Player targetPlayer = plugin.getServer().getPlayer(targetPlayerName);

                if (sourcePlayer != null && targetPlayer != null) {
                    sourcePlayer.teleport(targetPlayer.getLocation());
                    sourcePlayer.sendMessage(Format.color("&7Teletrasportato da &e%target%")
                            .replace("%target%", targetPlayerName));
                    player.sendMessage(Format.color("&7Teletrasportato &e%p1%&7 da &e%p2%")
                            .replace("%p1%", sourcePlayerName)
                            .replace("%p2%", targetPlayerName));
                } else {
                    player.sendMessage(Format.error("Giocatori non trovati!"));
                }
            } else if (args.length == 3) {
                // /tp <x> <y> <z>
                try {
                    double x = Double.parseDouble(args[0]);
                    double y = Double.parseDouble(args[1]);
                    double z = Double.parseDouble(args[2]);

                    player.teleport(new Location(player.getWorld(), x, y, z));
                    player.sendMessage(Format.color("&7Teletrasportato a &e%x% &6%y% &e%z%")
                            .replaceAll("%x%", String.valueOf(x))
                            .replaceAll("%y%", String.valueOf(y))
                            .replaceAll("%z%", String.valueOf(z))
                    );
                } catch (NumberFormatException e) {
                    player.sendMessage(Format.color("&cCoordinate invalide!"));
                }
            } else {
                player.sendMessage(Format.errorUsage("Usa: /help tp"));
            }
        } else {
            player.sendMessage(Format.error("Non hai abbastanza permessi!"));
        }

        return true;
    }
}

