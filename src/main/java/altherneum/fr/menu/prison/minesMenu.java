package altherneum.fr.menu.prison;

import java.io.IOException;
import java.text.ParseException;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import altherneum.fr.menu.api.customInventory;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

public class minesMenu {
    public static void minesMenu(Player player) throws IllegalArgumentException, IOException, ParseException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.MENU,
                InventoryType.HOPPER,
                textTranslation.Mines(lang) + textTranslation.MenuGoldFormat(lang, player));
        
        inventoryBuilder.getInventory().addItem(minesItems.ItemStackMineStone(lang, player));
        inventoryBuilder.getInventory().addItem(minesItems.ItemStackMineIron(lang, player));
        inventoryBuilder.getInventory().addItem(minesItems.ItemStackMineGold(lang, player));
        inventoryBuilder.getInventory().addItem(minesItems.ItemStackMineDiamond(lang, player));
        inventoryBuilder.getInventory().addItem(minesItems.ItemStackMineNetherite(lang, player));
        
        player.openInventory(inventoryBuilder.getInventory());
    }
}
