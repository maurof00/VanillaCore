package it.mauro.vanillacore.managers;

import it.mauro.vanillacore.Format;
import it.mauro.vanillacore.MauroSurvival;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShopManager implements Listener {
    private final MauroSurvival plugin;
    private final Map<UUID, Inventory> playerShopMenus;

    public ShopManager(MauroSurvival plugin) {
        this.plugin = plugin;
        this.playerShopMenus = new HashMap<>();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void openShopMenu(Player player) {
        if (playerShopMenus.containsKey(player.getUniqueId())) {
            player.sendMessage(Format.color("&eHai già un shop aperto!"));
            return;
        }

        Inventory shopMenu = Bukkit.createInventory(new ShopMenuHolder(), 9, "Shop Menu");

        shopMenu.setItem(2, new ItemStack(Material.DIAMOND));
        shopMenu.setItem(4, new ItemStack(Material.IRON_INGOT));
        shopMenu.setItem(6, new ItemStack(Material.NETHERITE_INGOT));

        player.openInventory(shopMenu);
        playerShopMenus.put(player.getUniqueId(), shopMenu);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof ShopMenuHolder) {
            Player player = (Player) event.getPlayer();
            playerShopMenus.remove(player.getUniqueId());
        }
    }

    private static class ShopMenuHolder implements org.bukkit.inventory.InventoryHolder {
        @Override
        public Inventory getInventory() {
            return null;
        }
    }
}
