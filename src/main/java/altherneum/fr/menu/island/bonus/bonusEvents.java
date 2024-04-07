package altherneum.fr.menu.island.bonus;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.menu.island.islandMenu;
import altherneum.fr.system.gold;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.shopTranslation;

import java.io.IOException;
import java.text.ParseException;

public class bonusEvents implements Listener {
    @EventHandler
    public void PlayerClickCompass(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.ileboost)) {
                bonusMenu.OpenShop((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickItemsList(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.bonusmenu)) {
                if (e.getClick().isLeftClick()) {
                    bonusMenu.OpenShopConfirm((Player) e.getWhoClicked(), e.getCurrentItem(), 1);
                } else {
                    islandMenu.islandCompass((Player) e.getWhoClicked());
                }
            }
        }
    }

    @EventHandler
    public void PlayerClickItemsBuy(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.bonusconfirm)) {
                if (e.getClick().isLeftClick()) {
                    Player player = (Player) e.getWhoClicked();
                    int amount = e.getCurrentItem().getAmount();
                    int prix = bonusPrice.Price(bonusItems.getKeyFromItem(e.getCurrentItem())) * amount;
                    if (gold.GetHasEnoughGold(player, prix)) {
                        gold.RemoveGold(player, prix);
                        bonusPlayerFile.addLvl(player, bonusItems.getKeyFromItem(e.getCurrentItem()), amount);
                        bonusMenu.OpenShopConfirm((Player) e.getWhoClicked(), e.getCurrentItem(), amount);
                    } else {
                        e.getWhoClicked().sendMessage(
                                shopTranslation.PasAssezArgent(playerLang.getPlayerLang((Player) e.getWhoClicked())));
                    }
                } else {
                    bonusMenu.OpenShop((Player) e.getWhoClicked());
                }
            }
        }
    }
}