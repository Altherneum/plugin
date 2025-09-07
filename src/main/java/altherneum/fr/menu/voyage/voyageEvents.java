package altherneum.fr.menu.voyage;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import altherneum.fr.entity.clickNPC;
import altherneum.fr.menu.api.persistentData;
import altherneum.fr.world.api.teleportation;

import java.io.IOException;
import java.text.ParseException;

public class voyageEvents implements Listener {
    @EventHandler
    public void PlayerClickVoyage(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.voyage)) {
                voyageMenu.menuVoyage((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickSpawn(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.spawn)) {
                teleportation.Teleport((Player) e.getWhoClicked(), "Spawn", false);
            }
        }
    }

    @EventHandler
    public void PlayerClickWorld(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.world)) {
                clickNPC.tpWorld((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickBed(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.bed)) {
                clickNPC.tpBed((Player) e.getWhoClicked());
            }
        }
    }
}