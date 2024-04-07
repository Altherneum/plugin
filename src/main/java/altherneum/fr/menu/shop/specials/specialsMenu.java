package altherneum.fr.menu.shop.specials;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import altherneum.fr.menu.api.customInventory;
import altherneum.fr.menu.api.persistentData;
import altherneum.fr.menu.shop.classique.shopItems;
import altherneum.fr.system.gold;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.IOException;

public class specialsMenu {
    public static void OpenShop(Player player) throws IllegalArgumentException, IOException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SHOP,
                InventoryType.HOPPER, itemsTranslation.shopTitle(lang) + " §f~§6 Bonus §f~ "
                        + gold.GetGoldFormat(player) + textTranslation.gold(lang));
        for (ItemStack itemStack : specialsItems.itemsList(lang)) {
            inventoryBuilder.getInventory().addItem(itemStack);
        }
        player.openInventory(inventoryBuilder.getInventory());
    }

    public static void OpenShopConfirm(Player player, ItemStack itemStack, int amount)
            throws IllegalArgumentException, IOException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SHOP, 9,
                itemsTranslation.shopTitle(lang) + " §f~§6 Bonus §f~ " + gold.GetGoldFormat(player)
                        + textTranslation.gold(lang));
        ItemStack itemStackNew = specialsItems.getItemFromItem(itemStack, lang, amount, true, false);
        int prix = specialsPrice.getPriceFromitem(itemStack);
        inventoryBuilder.getInventory().setItem(0, shopItems.itemSplitter(true, 1, prix, lang));
        inventoryBuilder.getInventory().setItem(1, shopItems.itemSplitter(true, 5, prix, lang));
        inventoryBuilder.getInventory().setItem(2, shopItems.itemSplitter(true, 16, prix, lang));
        inventoryBuilder.getInventory().setItem(6, shopItems.itemSplitter(false, 1, prix, lang));
        inventoryBuilder.getInventory().setItem(7, shopItems.itemSplitter(false, 5, prix, lang));
        inventoryBuilder.getInventory().setItem(8, shopItems.itemSplitter(false, 16, prix, lang));

        if (!persistentData.hasPersistentDataItemStack(itemStackNew, persistentData.customKey.specialsconfirm)) {
            persistentData.setPersistentDataItemStack(itemStackNew, persistentData.customKey.specialsconfirm);
        }
        itemStackNew.setAmount(amount);
        inventoryBuilder.getInventory().setItem(4, itemStackNew);
        player.openInventory(inventoryBuilder.getInventory());
    }
}