package altherneum.fr.menu.island;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import altherneum.fr.menu.api.persistentData;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

public class islandEvents implements Listener {
    @EventHandler
    public void PlayerClickCompass(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.ile)) {
                islandMenu.islandCompass((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickCreate(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.ilecreate)) {
                e.getWhoClicked().closeInventory();
                island.CreateIsland((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickIle(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, IllegalStateException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.iletp)) {
                e.getWhoClicked().closeInventory();
                island.TeleportToIsland((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickQuitter(InventoryClickEvent e) throws IllegalArgumentException, IOException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.ileleave)) {
                islandMenu.LeaveMenu((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickQuitterConfirmed(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, IllegalStateException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.ileleaveconfirm)) {
                e.getWhoClicked().closeInventory();
                island.LeaveIsland((OfflinePlayer) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickExclude(InventoryClickEvent e) throws IllegalArgumentException, IOException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.ilekick)) {
                islandMenu.ExcludeMenu((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickInvite(InventoryClickEvent e) throws IllegalArgumentException, IOException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.ileinvite)) {
                islandMenu.InviterMenu((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickLocate(InventoryClickEvent e) throws IllegalArgumentException, IOException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.ilelocate)) {
                e.getWhoClicked().closeInventory();
                island.SetIslandLocation((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickPending(InventoryClickEvent e) throws IllegalArgumentException, IOException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.ilepending)) {
                islandMenu.PendingMenu((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickOwner(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.ileowner)) {
                if (island.GetIsIslandOwner((Player) e.getWhoClicked())) {
                    islandMenu.OwnerMenu((Player) e.getWhoClicked());
                } else {
                    if (island.GetIsPlayerAFK(Bukkit
                            .getOfflinePlayer(UUID.fromString(island.GetIslandOwner((Player) e.getWhoClicked()))))) {
                        island.SetOwner((Player) e.getWhoClicked(), true, false);
                        islandMenu.islandCompass((Player) e.getWhoClicked());
                    }
                }
            }
        }
    }

    @EventHandler
    public void PlayerClickBonus(InventoryClickEvent e) throws IllegalArgumentException, IOException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.ileboost)) {
                e.getWhoClicked().closeInventory();
            }
        }
    }
    // separate ileOwnerGive & IleOwnerRevendiquer

    // Player head lore && name
    // merge Online / offline to methode
    // Online, UUID, Name, last login, first login, gold, ...

    // Create island still lag server
}