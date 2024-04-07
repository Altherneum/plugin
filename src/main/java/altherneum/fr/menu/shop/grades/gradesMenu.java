package altherneum.fr.menu.shop.grades;

import java.io.IOException;

import org.bukkit.entity.Player;

import altherneum.fr.menu.api.customInventory;
import altherneum.fr.system.tags;
import altherneum.fr.system.tags.TagsList;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

public class gradesMenu {

    public static void openGradeShop(Player player) throws IOException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SHOP, 9,
                itemsTranslation.shopTitle(lang) + textTranslation.MenuGoldFormat(lang, player));

        inventoryBuilder.getInventory().setItem(1, gradesItems.Aventurier(lang, tags.hasTags(player, TagsList.Aventurier)));

        inventoryBuilder.getInventory().setItem(2, gradesItems.Soldat(lang, tags.hasTags(player, TagsList.Soldat)));

        player.openInventory(inventoryBuilder.getInventory());
    }
}
