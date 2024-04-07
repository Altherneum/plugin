package altherneum.fr.menu.shop.grades;

import java.io.IOException;
import java.text.ParseException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.menu.api.persistentData.customKey;
import altherneum.fr.system.gold;
import altherneum.fr.system.tags;
import altherneum.fr.system.tags.TagsList;

public class gradesMenuEvent implements Listener {
    @EventHandler
    public void PlayerClickGrade(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.grade)) {
                gradesMenu.openGradeShop((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickSubMenu(InventoryClickEvent e) throws IllegalArgumentException, IOException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.aventurier)) {
                if (!tags.hasTags((Player) e.getWhoClicked(), TagsList.Aventurier)) {
                    if (gold.GetHasEnoughGold((Player) e.getWhoClicked(), gradesPrices.prices(customKey.aventurier))) {
                        gold.RemoveGold((Player) e.getWhoClicked(), gradesPrices.prices(customKey.aventurier));
                        tags.addTags((Player) e.getWhoClicked(), TagsList.Aventurier);
                    }
                }
            }

            else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.soldat)) {
                if (!tags.hasTags((Player) e.getWhoClicked(), TagsList.Soldat)) {
                    if (gold.GetHasEnoughGold((Player) e.getWhoClicked(), gradesPrices.prices(customKey.soldat))) {
                        gold.RemoveGold((Player) e.getWhoClicked(), gradesPrices.prices(customKey.soldat));
                        tags.addTags((Player) e.getWhoClicked(), TagsList.Soldat);
                    }
                }
            }
        }
    }
}
