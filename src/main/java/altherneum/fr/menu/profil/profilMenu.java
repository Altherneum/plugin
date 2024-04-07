package altherneum.fr.menu.profil;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import altherneum.fr.menu.api.customInventory;
import altherneum.fr.menu.island.islandItems;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.system.ServerBootFile.serverType;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.IOException;

public class profilMenu {
    public static void menuProfil(Player player, OfflinePlayer offlinePlayer)
            throws IllegalArgumentException, IOException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.PROFIL,
                InventoryType.HOPPER, offlinePlayer.getName() + textTranslation.MenuGoldFormat(lang, offlinePlayer));
        inventoryBuilder.getInventory().setItem(0, profilItems.ItemStackInfo(lang, offlinePlayer));
        inventoryBuilder.getInventory().setItem(1, profilItems.ItemStackTags(lang, offlinePlayer));
        inventoryBuilder.getInventory().setItem(2, profilItems.ItemStackPlayerData(lang, offlinePlayer));
        if (ServerBootFile.getServerTypeFromYML().equals(serverType.SkyBlock)) {
            inventoryBuilder.getInventory().setItem(3, islandItems.ItemStackPlayerHeadInfoVisite(offlinePlayer, lang));
        }
        player.openInventory(inventoryBuilder.getInventory());
    }
}