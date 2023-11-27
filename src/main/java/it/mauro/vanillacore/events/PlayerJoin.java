package it.mauro.vanillacore.events;

import it.mauro.vanillacore.Format;
import it.mauro.vanillacore.MauroSurvival;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private final MauroSurvival pl;

    public PlayerJoin(MauroSurvival pl) {
        this.pl = pl;
    }

    @EventHandler
    public void OnJoin(PlayerJoinEvent e) {
        Player player = e .getPlayer();

        e.setJoinMessage(Format.color("&7%player% &eè appena entrato nella vanilla!").replace("%player%", player.getName()));
    }
}
