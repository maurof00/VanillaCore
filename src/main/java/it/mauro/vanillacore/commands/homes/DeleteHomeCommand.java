package it.mauro.vanillacore.commands.homes;


import it.mauro.vanillacore.Format;
import it.mauro.vanillacore.MauroSurvival;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteHomeCommand implements CommandExecutor {

    private final MauroSurvival plugin;

    public DeleteHomeCommand(MauroSurvival plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(Format.errorUsage(plugin.getConfig().getString("usage.home_delete_usage")));
            return true;
        }

        String homeName = args[0];
        plugin.deletePlayerHome(player, homeName);

        return true;
    }
}

