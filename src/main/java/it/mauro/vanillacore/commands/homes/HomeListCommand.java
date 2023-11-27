package it.mauro.vanillacore.commands.homes;

import it.mauro.vanillacore.MauroSurvival;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeListCommand implements CommandExecutor {

    private final MauroSurvival plugin;

    public HomeListCommand(MauroSurvival plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;
        plugin.listPlayerHomes(player);

        return true;
    }
}

