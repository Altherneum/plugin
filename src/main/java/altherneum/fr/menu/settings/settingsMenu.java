package altherneum.fr.menu.settings;

import java.io.IOException;
import java.text.ParseException;

import org.bukkit.entity.Player;

import altherneum.fr.menu.api.customInventory;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

public class settingsMenu {
    public static void SettingsMenu(Player player) throws IllegalArgumentException, IOException, ParseException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SETTINGS, 54,
                itemsTranslation.SettingsTitle(lang) + textTranslation.MenuGoldFormat(lang, player));

        inventoryBuilder.getInventory().addItem(settingsItems.ItemStackResourcePack(lang));
        inventoryBuilder.getInventory().addItem(settingsItems.ItemStackLang(lang));
        inventoryBuilder.getInventory().addItem(settingsItems.ItemStackCompassPose(lang));
        inventoryBuilder.getInventory().addItem(settingsItems.ItemStackChat(lang));

        player.openInventory(inventoryBuilder.getInventory());
    }
}
