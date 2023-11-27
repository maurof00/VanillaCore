package it.mauro.vanillacore.commands;

import it.mauro.vanillacore.Format;
import it.mauro.vanillacore.MauroSurvival;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PluginCommands implements CommandExecutor {

    private final MauroSurvival pl;

    public PluginCommands(MauroSurvival pl) {
        this.pl = pl;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender.hasPermission("maurosurvival.commands.reload")) {
            commandSender.sendMessage(Format.color(pl.getConfig().getString("messaggi.plugin_config_reload")));
            pl.getConfig().options().copyDefaults(false);
            pl.reloadConfig();
            pl.saveConfig();
        } else {
            commandSender.sendMessage(Format.error(pl.getConfig().getString("errori.plugin_no_permission")));
        }



        return false;
    }
}
