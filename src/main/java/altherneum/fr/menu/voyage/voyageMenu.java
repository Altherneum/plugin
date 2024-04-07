package altherneum.fr.menu.voyage;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import altherneum.fr.menu.api.customInventory;
import altherneum.fr.menu.island.island;
import altherneum.fr.menu.island.islandItems;
import altherneum.fr.menu.serverSelector.serverSelectorItems;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.IOException;
import java.text.ParseException;

public class voyageMenu {
    public static void menuVoyage(Player player) throws IllegalArgumentException, IOException, ParseException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.VOYAGE,
                InventoryType.HOPPER, textTranslation.voyage(lang) + textTranslation.MenuGoldFormat(lang, player));

        if (!ServerBootFile.getServerType().equals(ServerBootFile.serverType.Hub)) {
            inventoryBuilder.getInventory().setItem(0, serverSelectorItems.ItemStackHub(lang, player));
        }

        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Survie)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.RPG)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.Anarchie)) {
            inventoryBuilder.getInventory().setItem(2, voyageItems.ItemStackSpawn(lang));
            inventoryBuilder.getInventory().setItem(4, voyageItems.ItemStackWorld(lang));
        }

        else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Creatif)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.OPPrison)) {
            inventoryBuilder.getInventory().setItem(2, voyageItems.ItemStackSpawn(lang));
        }

        else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            inventoryBuilder.getInventory().setItem(2, voyageItems.ItemStackSpawn(lang));
            if (island.GetHasIsland(player)) {
                inventoryBuilder.getInventory().setItem(4, islandItems.ItemStackTPIle(lang));
            } else {
                inventoryBuilder.getInventory().setItem(4, islandItems.ItemStackCreateIle(lang));
            }
        }
        player.openInventory(inventoryBuilder.getInventory());
    }
}