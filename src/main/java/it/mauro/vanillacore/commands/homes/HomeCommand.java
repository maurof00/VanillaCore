package it.mauro.vanillacore.commands.homes;

// ... (imports)

import it.mauro.vanillacore.Format;
import it.mauro.vanillacore.MauroSurvival;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.text.Normalizer;

public class HomeCommand implements CommandExecutor {

    private final MauroSurvival plugin;

    public HomeCommand(MauroSurvival plugin) {
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
            player.sendMessage(Format.errorUsage(plugin.getConfig().getString("usage.home_set_usage")));
            return true;
        }

        String homeName = args[0];

        File file = new File(plugin.getDataFolder(), player.getUniqueId() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        if (config.contains("homes." + homeName)) {
            Location homeLocation = (Location) config.get("homes." + homeName);
            player.teleport(homeLocation);
            player.sendMessage(Format.color(plugin.getConfig().getString("messaggi.home_teleport")
                    .replaceAll("%home%", homeName)));
        } else {
            player.sendMessage(Format.error(plugin.getConfig().getString("errori.home_not_found")));
        }

        return true;
    }
}


