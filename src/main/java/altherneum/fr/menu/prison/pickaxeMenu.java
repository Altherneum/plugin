package altherneum.fr.menu.prison;

import java.io.IOException;
import java.text.ParseException;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import altherneum.fr.menu.api.customInventory;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

public class pickaxeMenu {
    public static void pickaxeMenu(Player player) throws IllegalArgumentException, IOException, ParseException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.MENU,
                InventoryType.HOPPER,
                itemsTranslation.PickaxeSelectorTitle(lang) + textTranslation.MenuGoldFormat(lang, player));

        if (!pickaxe.Nextpickaxe(prisonData.getPickaxe(player)).equals(Material.AIR)) {
            inventoryBuilder.getInventory().setItem(0, pickaxeItems.ItemStackPickaxeUpgrade(lang, player));
        } else {
            inventoryBuilder.getInventory().setItem(0, pickaxeItems.ItemStackPickaxeCantUpgrade(lang));
        }

        inventoryBuilder.getInventory().setItem(2, pickaxeItems.ItemStackPickaxeEnchantDigSpeed(lang, player));
        inventoryBuilder.getInventory().setItem(3, pickaxeItems.ItemStackPickaxeEnchantLooting(lang, player));
        player.openInventory(inventoryBuilder.getInventory());
    }
}
