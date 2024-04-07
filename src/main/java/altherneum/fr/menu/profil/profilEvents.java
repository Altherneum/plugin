package altherneum.fr.menu.profil;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.menu.island.island;
import altherneum.fr.world.api.teleportation;

import java.io.IOException;
import java.text.ParseException;

public class profilEvents implements Listener {
    @EventHandler
    public void PlayerClickIle(InventoryClickEvent e) throws IllegalArgumentException, IOException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.ileplayerhead)) {
                SkullMeta itemMeta = (SkullMeta) e.getCurrentItem().getItemMeta();
                OfflinePlayer offlinePlayer = itemMeta.getOwningPlayer();
                profilMenu.menuProfil((Player) e.getWhoClicked(), offlinePlayer);
            }
        }
    }

    @EventHandler
    public void PlayerClickIleVisite(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.ileprofilviste)) {
                SkullMeta itemMeta = (SkullMeta) e.getCurrentItem().getItemMeta();
                OfflinePlayer offlinePlayer = itemMeta.getOwningPlayer();
                if (island.GetHasIsland(offlinePlayer)) {
                    String world = "i." + island.GetIslandNumber(offlinePlayer);
                    teleportation.Teleport((Player) e.getWhoClicked(), world, false);
                }
            }
        }
    }
}