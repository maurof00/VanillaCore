package it.mauro.vanillacore;

import it.mauro.vanillacore.commands.GamemodeCommands;
import it.mauro.vanillacore.commands.HealCommand;
import it.mauro.vanillacore.commands.HelpCommand;
import it.mauro.vanillacore.commands.TpCommand;
import it.mauro.vanillacore.commands.customitems.SpadaMauro;
import it.mauro.vanillacore.commands.economy.BalanceCommand;
import it.mauro.vanillacore.commands.economy.ShopCommand;
import it.mauro.vanillacore.commands.homes.*;
import it.mauro.vanillacore.commands.tpa.TpAcceptCommand;
import it.mauro.vanillacore.commands.tpa.TpRejectCommand;
import it.mauro.vanillacore.commands.tpa.TpaCommand;
import it.mauro.vanillacore.database.DatabaseManager;
import it.mauro.vanillacore.events.EntityKillEvent;
import it.mauro.vanillacore.events.PlayerJoin;
import it.mauro.vanillacore.events.ShopEvent;
import it.mauro.vanillacore.managers.ShopManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class MauroSurvival extends JavaPlugin {

    private final TpaCommand tpaCommand = new TpaCommand();

    private DatabaseManager databaseManager;
    private ShopManager shopManager;

    @Override
    public void onEnable() {
        shopManager = new ShopManager(this);
        databaseManager = new DatabaseManager(this);
        saveDefaultConfig();
        commands();
        events();
        getLogger().info("Plugin abilitato!");
    }

    void commands() {
        getCommand("shop").setExecutor(new ShopCommand(shopManager));
        getCommand("conto").setExecutor(new BalanceCommand(databaseManager));
        getCommand("home").setTabCompleter(new HomeTabCompleter(this));
        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("home").setExecutor(new HomeCommand(this));
        getCommand("delhome").setExecutor(new DeleteHomeCommand(this));
        getCommand("tpa").setExecutor(tpaCommand);
        getCommand("tpaccept").setExecutor(new TpAcceptCommand(tpaCommand));
        getCommand("tpareject").setExecutor(new TpRejectCommand(tpaCommand));
        getCommand("homes").setExecutor(new HomeListCommand(this));
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("tp").setExecutor(new TpCommand(this));
        getCommand("gmc").setExecutor(new GamemodeCommands());
        getCommand("spadamauro").setExecutor(new SpadaMauro());
        getCommand("cura").setExecutor(new HealCommand());
    }

    void events() {
        getServer().getPluginManager().registerEvents(new ShopEvent(shopManager, databaseManager), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new EntityKillEvent(databaseManager), this);
    }


    @Override
    public void onDisable() {
        getLogger().info("Plugin disabilitato!");
    }

    public void savePlayerHome(Player player, String homeName, Location location) {
        File file = new File(getDataFolder(), player.getUniqueId() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        ConfigurationSection homesSection = config.getConfigurationSection("homes");
        if (homesSection == null) {
            homesSection = config.createSection("homes");
        }

        homesSection.set(homeName, location);

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deletePlayerHome(Player player, String homeName) {
        File file = new File(getDataFolder(), player.getUniqueId() + ".yml");
        if (!file.exists()) {
            player.sendMessage(Format.error(getConfig().getString("errori.home_empty_list")));
            return;
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (config.contains("homes." + homeName)) {
            config.set("homes." + homeName, null);

            try {
                config.save(file);
                player.sendMessage(Format.color(getConfig().getString("messaggi.home_delete")
                        .replaceAll("%home%", homeName)));
            } catch (IOException e) {
                e.printStackTrace();
                player.sendMessage(Format.error(getConfig().getString("errori.home_delete_error")));
            }
        } else {
            player.sendMessage(Format.error(getConfig().getString("errori.home_not_found")));
        }
    }
    public void listPlayerHomes(Player player) {
        File file = new File(getDataFolder(), player.getUniqueId() + ".yml");
        if (!file.exists()) {
            player.sendMessage(Format.error(getConfig().getString("errori.home_empty_list")));
            return;
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (config.contains("homes")) {
            ConfigurationSection homesSection = config.getConfigurationSection("homes");
            player.sendMessage("");
            player.sendMessage(Format.color("&7&m-----&8&l[&eCase - Lista&8&l]&7&m-----"));
            player.sendMessage("");
            for (String homeName : homesSection.getKeys(false)) {
                player.sendMessage(Format.color("&8 * &e%home%")
                        .replaceAll("%home%", homeName));
                player.sendMessage("");
            }
        } else {
            player.sendMessage(Format.error(getConfig().getString("errori.home_empty_list")));
        }
    }
}
