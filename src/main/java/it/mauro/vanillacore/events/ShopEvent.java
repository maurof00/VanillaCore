package it.mauro.vanillacore.events;

import it.mauro.vanillacore.Format;
import it.mauro.vanillacore.database.DatabaseManager;
import it.mauro.vanillacore.managers.ShopManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ShopEvent implements Listener {

    private final ShopManager shopManager;

    private DatabaseManager databaseManager;

    public ShopEvent(ShopManager shopManager, DatabaseManager databaseManager) {
        this.shopManager = shopManager;
        this.databaseManager = databaseManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getType().name().equalsIgnoreCase("Shop Menu")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
                return;
            }

            Player player = (Player) event.getWhoClicked();
            double balance = databaseManager.getBalance(player);

            switch (event.getCurrentItem().getType()) {
                case DIAMOND:
                    if (balance >= 120) {
                        databaseManager.subtractBalance(player, 120);
                        player.getInventory().addItem(new ItemStack(Material.DIAMOND));
                        player.sendMessage(Format.color("&7Hai comprato un &ediamante&7!"));
                    } else {
                        player.sendMessage(Format.error("Non hai abbastanza soldi!"));
                    }
                    break;

                case IRON_INGOT:
                    if (balance >= 50) {
                        databaseManager.subtractBalance(player, 50);
                        player.getInventory().addItem(new ItemStack(Material.IRON_INGOT));
                        player.sendMessage(Format.color("&7Hai comprato un &eferro&7!"));
                    } else {
                        player.sendMessage(Format.error("Non hai abbastanza soldi!"));
                    }
                    break;

                case NETHERITE_INGOT:
                    if (balance >= 500) {
                        databaseManager.subtractBalance(player, 500);
                        player.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT));
                        player.sendMessage(Format.color("&7Hai comprato un &enetherite&7!"));
                    } else {
                        player.sendMessage(Format.error("Non hai abbastanza soldi!"));
                    }
                    break;
            }
        }
    }
}
