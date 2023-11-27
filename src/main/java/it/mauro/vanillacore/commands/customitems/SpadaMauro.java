package it.mauro.vanillacore.commands.customitems;

import it.mauro.vanillacore.Format;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SpadaMauro implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(Format.error("Comando solo per giocatori!"));
        }

        Player player = (Player) commandSender;

        if(!player.hasPermission("maurosurvival.item.maurosword")) {
            player.sendMessage(Format.error("Non hai abbastanza permessi!"));
        } else {
            List<String> lore = new ArrayList<>();
            lore.add(Format.color("&e\uD83D\uDDE1 &8| &7ᴀғғɪʟᴀᴛᴇᴢᴢᴀ &e20"));
            ItemStack item = new ItemStack(Material.RED_DYE);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(Format.color("&csᴘᴀᴅᴀ ᴍᴀᴜʀᴏ"));
            itemMeta.setLore(lore);
            item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 20);
            item.setItemMeta(itemMeta);
            // da la spada al giocatore.
            player.getInventory().addItem(item);
        }





        return false;
    }
}
