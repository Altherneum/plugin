package altherneum.fr.menu.island;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import altherneum.fr.menu.api.customInventory;
import altherneum.fr.menu.compass.compassItems;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

public class islandMenu {
    public static void islandCompass(Player player) throws IllegalArgumentException, IOException, ParseException {
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            lang.languages lang = playerLang.getPlayerLang(player);
            customInventory inventoryBuilder;
            if (island.GetHasIsland(player)) {
                inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SKYBLOCK, 18,
                        itemsTranslation.IleMenuTitle(lang));
                inventoryBuilder.getInventory().setItem(1, islandItems.ItemStackTPIle(lang));
                inventoryBuilder.getInventory().setItem(7, islandItems.ItemStackLeaveIle(lang));
                inventoryBuilder.getInventory().setItem(2, islandItems.ItemStackKickIle(lang));
                inventoryBuilder.getInventory().setItem(4, islandItems.ItemStackOwnerIle(lang));
                inventoryBuilder.getInventory().setItem(3, islandItems.ItemStackInviteIle(lang));
                inventoryBuilder.getInventory().setItem(5, islandItems.ItemStackLocateIle(lang));
                inventoryBuilder.getInventory().setItem(17, islandItems.ItemStackIleBonus(lang));

                addPlayerHead(inventoryBuilder, player, lang);
            } else {
                inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SKYBLOCK,
                        InventoryType.HOPPER, itemsTranslation.IleMenuTitle(lang));
                inventoryBuilder.getInventory().setItem(1, islandItems.ItemStackCreateIle(lang));
                if (!island.GetPendingIsland(player).isEmpty()) {
                    inventoryBuilder.getInventory().setItem(3, islandItems.ItemStackPendingIle(lang));
                }
            }
            player.openInventory(inventoryBuilder.getInventory());
        }
    }

    private static void addPlayerHead(customInventory inventoryBuilder, Player player, lang.languages lang)
            throws IOException {
        int indexPlus = 8;
        int indexHead = 1;
        for (ItemStack head : islandItems.GetPlayerHeadIsland(island.GetIslandPlayer(player), lang)) {
            inventoryBuilder.getInventory().setItem(indexHead + indexPlus, head);
            indexHead++;
        }
    }

    public static void LeaveMenu(Player player) throws IOException {
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            lang.languages lang = playerLang.getPlayerLang(player);
            customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SKYBLOCK,
                    InventoryType.HOPPER, itemsTranslation.IleLeaveTitle(lang));
            inventoryBuilder.getInventory().setItem(1, compassItems.ItemStackIle(lang));
            inventoryBuilder.getInventory().setItem(3, islandItems.ItemStackLeaveConfirmIle(lang));
            player.openInventory(inventoryBuilder.getInventory());
        }
    }

    public static void ExcludeMenu(Player player) throws IOException {
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            lang.languages lang = playerLang.getPlayerLang(player);
            customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SKYBLOCK, 18,
                    itemsTranslation.IleKickTitle(lang));
            inventoryBuilder.getInventory().addItem(compassItems.ItemStackIle(lang));
            inventoryBuilder.getInventory().addItem(islandItems.ItemStackKickIle(lang));
            int indexPlus = 2;
            int indexHead = 1;
            for (ItemStack itemStack : islandItems
                    .GetPlayerHeadExclude(island.GetIslandPlayerWithoutClickingPlayer(player), lang)) {
                inventoryBuilder.getInventory().setItem(indexHead + indexPlus, itemStack);
                indexHead++;
            }
            player.openInventory(inventoryBuilder.getInventory());
        }
    }

    public static void OwnerMenu(Player player) throws IOException {
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            lang.languages lang = playerLang.getPlayerLang(player);
            customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SKYBLOCK, 18,
                    itemsTranslation.IleOwnerTitle(lang));
            inventoryBuilder.getInventory().addItem(compassItems.ItemStackIle(lang));
            inventoryBuilder.getInventory().addItem(islandItems.ItemStackOwnerIle(lang));
            int indexPlus = 2;
            int indexHead = 1;
            for (ItemStack itemStack : islandItems
                    .GetPlayerHeadOwner(island.GetIslandPlayerWithoutClickingPlayer(player), lang)) {
                inventoryBuilder.getInventory().setItem(indexHead + indexPlus, itemStack);
                indexHead++;
            }
            player.openInventory(inventoryBuilder.getInventory());
        }
    }

    public static void InviterMenu(Player player) throws IOException {
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            lang.languages lang = playerLang.getPlayerLang(player);
            customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SKYBLOCK, 54,
                    itemsTranslation.IleInviteTitle(lang));
            inventoryBuilder.getInventory().addItem(compassItems.ItemStackIle(lang));
            inventoryBuilder.getInventory().addItem(islandItems.ItemStackInviteIle(lang));
            int indexPlus = 2;
            int indexHead = 1;
            for (ItemStack itemStack : islandItems
                    .GetPlayerHeadInvite(island.GetOnlinePlayerWithoutIslandWithCheckedInvite(player), lang)) {
                if (inventoryBuilder.getInventory().firstEmpty() != 1) {
                    inventoryBuilder.getInventory().setItem(indexHead + indexPlus, itemStack);
                    indexHead++;
                }
            }
            player.openInventory(inventoryBuilder.getInventory());
        }
    }

    public static void PendingMenu(Player player) throws IOException {
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            if (!island.GetPendingIsland(player).isEmpty()) {
                lang.languages lang = playerLang.getPlayerLang(player);
                customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SKYBLOCK, 54,
                        itemsTranslation.IlePendingTitle(lang));
                inventoryBuilder.getInventory().addItem(compassItems.ItemStackIle(lang));
                inventoryBuilder.getInventory().addItem(islandItems.ItemStackPendingIle(lang));
                int indexPlus = 2;
                int indexHead = 1;
                for (int i : island.GetPendingIsland(player)) {
                    if (island.GetIslandOwner(i).equals(island.GetPendingOwnerForIsland(player, i))) {
                        inventoryBuilder.getInventory().setItem(indexHead + indexPlus, islandItems.GetPlayerHeadPending(
                                Bukkit.getOfflinePlayer(UUID.fromString(island.GetIslandOwner(i))), lang));
                        indexHead++;
                    } else {
                        island.SetPendingIslandRemove(player, i);
                    }
                }
                if (indexHead >= 2) {
                    player.openInventory(inventoryBuilder.getInventory());
                }
            }
        }
    }
}