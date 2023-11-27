package it.mauro.vanillacore;

import net.md_5.bungee.api.ChatColor;

public class Format {

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static String error(String s) {
        return ChatColor.translateAlternateColorCodes('&', "&c[&4&l!&c] "+s);
    }
    public static String errorUsage(String s) {
        return ChatColor.translateAlternateColorCodes('&', "&e[&6&l!&e] "+s);
    }
}
