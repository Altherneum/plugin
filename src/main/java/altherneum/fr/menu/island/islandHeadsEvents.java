package altherneum.fr.menu.island;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.system.getDataStorage;

import java.io.IOException;
import java.text.ParseException;

public class islandHeadsEvents implements Listener {
    @EventHandler
    public void PlayerClickExclude(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.excludeplayerhead)) {
                SkullMeta itemMeta = (SkullMeta) e.getCurrentItem().getItemMeta();
                OfflinePlayer offlinePlayer = itemMeta.getOwningPlayer();
                if (island.GetIsIslandOwner((Player) e.getWhoClicked())
                        && island.IsInSameIsland((Player) e.getWhoClicked(), offlinePlayer)) {
                    island.KickFromIsland(offlinePlayer, (Player) e.getWhoClicked());
                    if (island.GetPlayerList(island.GetIslandNumber((Player) e.getWhoClicked())).size() >= 2) {
                        islandMenu.ExcludeMenu((Player) e.getWhoClicked());
                        return;
                    }
                }
                islandMenu.islandCompass((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickInvite(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.inviteplayerhead)) {
                SkullMeta itemMeta = (SkullMeta) e.getCurrentItem().getItemMeta();
                OfflinePlayer offlinePlayer = itemMeta.getOwningPlayer();
                if (island.GetIsIslandOwner((Player) e.getWhoClicked())
                        && !island.IsInSameIsland((Player) e.getWhoClicked(), offlinePlayer)) {
                    // Check size player max et bonus
                    island.SetPendingIsland(offlinePlayer, (Player) e.getWhoClicked());
                    if (!island.GetOnlinePlayerWithoutIslandWithCheckedInvite((Player) e.getWhoClicked()).isEmpty()) {
                        islandMenu.InviterMenu((Player) e.getWhoClicked());
                        return;
                    }
                }
                islandMenu.islandCompass((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickOwner(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.ownerplayerhead)) {
                SkullMeta itemMeta = (SkullMeta) e.getCurrentItem().getItemMeta();
                OfflinePlayer offlinePlayer = itemMeta.getOwningPlayer();
                if (island.GetIsIslandOwner((Player) e.getWhoClicked())
                        && island.IsInSameIsland((Player) e.getWhoClicked(), offlinePlayer)) {
                    island.SetOwner(offlinePlayer, true, false);
                }
                islandMenu.islandCompass((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickPending(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.pendingplayerhead)) {
                SkullMeta itemMeta = (SkullMeta) e.getCurrentItem().getItemMeta();
                OfflinePlayer offlinePlayer = itemMeta.getOwningPlayer();
                if (e.getClick().isLeftClick()) {
                    if (!island.GetHasIsland((Player) e.getWhoClicked()) && island.GetIsIslandOwner(offlinePlayer)) {
                        getDataStorage.playerIslandData((Player) e.getWhoClicked()).delete();
                        island.AddNewPlayerToIsland((Player) e.getWhoClicked(), offlinePlayer);
                        e.getWhoClicked().closeInventory();
                        island.TeleportToIsland((Player) e.getWhoClicked());
                        return;
                    }
                    islandMenu.PendingMenu((Player) e.getWhoClicked());
                } else if (e.getClick().isRightClick()) {
                    island.SetPendingIslandRemove((Player) e.getWhoClicked(), island.GetIslandNumber(offlinePlayer));
                    islandMenu.PendingMenu((Player) e.getWhoClicked());
                }
            }
        }
    }
}