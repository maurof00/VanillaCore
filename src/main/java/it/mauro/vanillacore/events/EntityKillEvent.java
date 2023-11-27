package it.mauro.vanillacore.events;

import it.mauro.vanillacore.Format;
import it.mauro.vanillacore.database.DatabaseManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityKillEvent implements Listener {

    private final DatabaseManager databaseManager;

    public EntityKillEvent(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            Player player = event.getEntity().getKiller();
            EntityType entityType = event.getEntityType();

            if (entityType == EntityType.ZOMBIE || entityType == EntityType.SKELETON || entityType == EntityType.CREEPER) {
                databaseManager.addBalance(player, 10.0);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Format.color("&7+ &e10€")));
            }
        }
    }

}
