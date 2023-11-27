package it.mauro.vanillacore.commands.homes;

import it.mauro.vanillacore.MauroSurvival;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeTabCompleter implements TabCompleter {

    private final MauroSurvival plugin;

    public HomeTabCompleter(MauroSurvival plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            return Collections.emptyList();
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("home")) {
            if (args.length == 1) {
                File file = new File(plugin.getDataFolder(), player.getUniqueId() + ".yml");
                FileConfiguration config = YamlConfiguration.loadConfiguration(file);

                if (config.contains("homes")) {
                    ConfigurationSection homesSection = config.getConfigurationSection("homes");
                    if (homesSection != null) {
                        List<String> homeNames = new ArrayList<>(homesSection.getKeys(false));
                        if (!homeNames.isEmpty()) {
                            String partialHomeName = args[0].toLowerCase();
                            List<String> matchedHomes = new ArrayList<>();

                            for (String name : homeNames) {
                                if (name.toLowerCase().startsWith(partialHomeName)) {
                                    matchedHomes.add(name);
                                }
                            }

                            return matchedHomes;
                        }
                    }
                }
            }
        }

        return Collections.emptyList();
    }
}

