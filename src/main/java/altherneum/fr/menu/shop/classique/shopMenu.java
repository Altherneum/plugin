package altherneum.fr.menu.shop.classique;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import altherneum.fr.menu.api.customInventory;
import altherneum.fr.menu.shop.grades.gradesItems;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.system.gold;
import altherneum.fr.system.ServerBootFile.serverType;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.IOException;
import java.util.List;

public class shopMenu {
    public static void OpenShop(Player player) throws IllegalArgumentException, IOException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SHOP, 27,
                itemsTranslation.shopTitle(lang) + textTranslation.MenuGoldFormat(lang, player));

        inventoryBuilder.getInventory().setItem(0, shopItems.Graine(lang));
        inventoryBuilder.getInventory().setItem(3, shopItems.Monstres(lang));
        inventoryBuilder.getInventory().setItem(5, shopItems.Dye(lang));
        inventoryBuilder.getInventory().setItem(8, shopItems.sellAll(lang));
        inventoryBuilder.getInventory().setItem(9, shopItems.Nourriture(lang));
        inventoryBuilder.getInventory().setItem(11, shopItems.Plantes(lang));
        inventoryBuilder.getInventory().setItem(15, shopItems.Minerais(lang));
        inventoryBuilder.getInventory().setItem(13, shopItems.Blocks(lang));
        if (ServerBootFile.serverTypeActual.equals(serverType.SkyBlock)) {
            inventoryBuilder.getInventory().setItem(17, gradesItems.grade(lang));
        }
        inventoryBuilder.getInventory().setItem(18, shopItems.Speciaux(lang));
        inventoryBuilder.getInventory().setItem(21, shopItems.Oeufs(lang));
        inventoryBuilder.getInventory().setItem(22, shopItems.Nether(lang));
        inventoryBuilder.getInventory().setItem(23, shopItems.Water(lang));
        inventoryBuilder.getInventory().setItem(26, shopItems.Custom(lang));

        player.openInventory(inventoryBuilder.getInventory());
    }

    public static void OpenShop(Player player, List<Material> materials, String nom)
            throws IllegalArgumentException, IOException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SHOP,
                shopUtils.GetInventorySize(materials), itemsTranslation.shopTitle(lang) + " §f~§6 " + nom + " §f~ "
                        + gold.GetGoldFormat(player) + textTranslation.gold(lang));
        for (Material material : materials) {
            inventoryBuilder.getInventory()
                    .addItem(shopItems.itemToSell(material, shopPrice.getPrix(material), 1, false, lang));
        }
        player.openInventory(inventoryBuilder.getInventory());
    }

    public static void OpenShopBuy(Player player, int amount, int prix, ItemStack itemStack)
            throws IllegalArgumentException, IOException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SHOP, 9,
                itemsTranslation.shopTitle(lang) + " §f~ " + gold.GetGoldFormat(player) + textTranslation.gold(lang));
        inventoryBuilder.getInventory().setItem(0, shopItems.itemSplitter(true, 1, prix, lang));
        inventoryBuilder.getInventory().setItem(1, shopItems.itemSplitter(true, 5, prix, lang));
        inventoryBuilder.getInventory().setItem(2, shopItems.itemSplitter(true, 16, prix, lang));
        inventoryBuilder.getInventory().setItem(4, itemStack);
        inventoryBuilder.getInventory().setItem(6, shopItems.itemSplitter(false, 1, prix, lang));
        inventoryBuilder.getInventory().setItem(7, shopItems.itemSplitter(false, 5, prix, lang));
        inventoryBuilder.getInventory().setItem(8, shopItems.itemSplitter(false, 16, prix, lang));
        player.openInventory(inventoryBuilder.getInventory());
    }
}