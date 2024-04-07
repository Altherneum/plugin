package altherneum.fr.menu.compass;

import org.bukkit.entity.Player;

import altherneum.fr.menu.api.customInventory;
import altherneum.fr.menu.island.islandItems;
import altherneum.fr.menu.prison.minesItems;
import altherneum.fr.menu.prison.pickaxeItems;
import altherneum.fr.menu.serverSelector.serverSelectorItems;
import altherneum.fr.menu.shop.classique.shopItems;
import altherneum.fr.menu.shop.grades.gradesItems;
import altherneum.fr.player.anarchieLife;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.IOException;
import java.text.ParseException;

public class compassMenu {
    public static void menuCompass(Player player) throws IllegalArgumentException, IOException, ParseException {
        lang.languages lang = playerLang.getPlayerLang(player);

        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Survie)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.Anarchie)) {
            customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.MENU, 9,
                    textTranslation.Menu(lang) + textTranslation.MenuGoldFormat(lang, player));
            inventoryBuilder.getInventory().setItem(0, compassItems.ItemStackTeleporation(lang));
            if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Anarchie)) {
                inventoryBuilder.getInventory().setItem(4, anarchieLife.GetlifeItemStack(lang, player));
            }
            inventoryBuilder.getInventory().setItem(6, compassItems.ItemStackShop(lang));
            inventoryBuilder.getInventory().setItem(7, shopItems.Custom(lang));
            inventoryBuilder.getInventory().setItem(8, compassItems.ItemStackSettings(lang));
            player.openInventory(inventoryBuilder.getInventory());
        }

        else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Creatif)) {
            customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.MENU, 9,
                    textTranslation.Menu(lang) + textTranslation.MenuGoldFormat(lang, player));
            inventoryBuilder.getInventory().setItem(0, compassItems.ItemStackTeleporation(lang));
            inventoryBuilder.getInventory().setItem(8, compassItems.ItemStackSettings(lang));
            player.openInventory(inventoryBuilder.getInventory());
        }

        else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.OPPrison)) {
            customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.MENU, 9,
                    textTranslation.Menu(lang) + textTranslation.MenuGoldFormat(lang, player));
            inventoryBuilder.getInventory().setItem(0, compassItems.ItemStackTeleporation(lang));
            // inventoryBuilder.getInventory().setItem(4, compassItems.ItemStackShop(lang));
            inventoryBuilder.getInventory().setItem(4, shopItems.sellAll(lang));
            inventoryBuilder.getInventory().setItem(5, minesItems.ItemStackMineSelector(lang));
            inventoryBuilder.getInventory().setItem(6, pickaxeItems.ItemStackPickaxeSelector(lang));
            inventoryBuilder.getInventory().setItem(8, compassItems.ItemStackSettings(lang));
            player.openInventory(inventoryBuilder.getInventory());
        }

        else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Hub)) {
            customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.MENU, 54,
                    textTranslation.Menu(lang) + textTranslation.MenuGoldFormat(lang, player));
            inventoryBuilder.getInventory().setItem(19, serverSelectorItems.ItemStackSurvie(lang, player));
            inventoryBuilder.getInventory().setItem(20, serverSelectorItems.ItemStackCreatif(lang, player));
            inventoryBuilder.getInventory().setItem(21, serverSelectorItems.ItemStackRPG(lang, player));
            inventoryBuilder.getInventory().setItem(28, serverSelectorItems.ItemStackSkyBlock(lang, player));
            inventoryBuilder.getInventory().setItem(29, serverSelectorItems.ItemStackAnarchie(lang, player));
            inventoryBuilder.getInventory().setItem(30, serverSelectorItems.ItemStackOPPrison(lang, player));
            inventoryBuilder.getInventory().setItem(16, shopItems.Custom(lang));
            inventoryBuilder.getInventory().setItem(25, compassItems.ItemStackSettings(lang));
            inventoryBuilder.getInventory().setItem(34, islandItems.ItemStackPlayerHeadInfo(player, lang));
            player.openInventory(inventoryBuilder.getInventory());
        }

        else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.RPG)) {
            customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.MENU, 9,
                    textTranslation.Menu(lang) + textTranslation.MenuGoldFormat(lang, player));
            inventoryBuilder.getInventory().setItem(0, compassItems.ItemStackTeleporation(lang));
            inventoryBuilder.getInventory().setItem(4, compassItems.ItemStackQuest(lang));
            inventoryBuilder.getInventory().setItem(5, compassItems.ItemStackShopEnchant(lang));
            inventoryBuilder.getInventory().setItem(6, compassItems.ItemStackShop(lang));
            inventoryBuilder.getInventory().setItem(8, compassItems.ItemStackSettings(lang));
            player.openInventory(inventoryBuilder.getInventory());
        }

        else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.MENU, 9,
                    textTranslation.Menu(lang) + textTranslation.MenuGoldFormat(lang, player));
            inventoryBuilder.getInventory().setItem(0, compassItems.ItemStackTeleporation(lang));
            inventoryBuilder.getInventory().setItem(1, compassItems.ItemStackIle(lang));
            inventoryBuilder.getInventory().setItem(3, compassItems.ItemStackShop(lang));
            inventoryBuilder.getInventory().setItem(4, shopItems.Custom(lang));
            inventoryBuilder.getInventory().setItem(5, gradesItems.grade(lang));
            inventoryBuilder.getInventory().setItem(6, compassItems.ItemStackShopEnchant(lang));
            inventoryBuilder.getInventory().setItem(8, compassItems.ItemStackSettings(lang));
            player.openInventory(inventoryBuilder.getInventory());
        }
    }
}