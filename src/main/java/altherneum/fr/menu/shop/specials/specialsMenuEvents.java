package altherneum.fr.menu.shop.specials;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.system.gold;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.shopTranslation;

import java.io.IOException;
import java.text.ParseException;

public class specialsMenuEvents implements Listener {
    @EventHandler
    public void PlayerClickItemsList(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.specialsmenu)) {
                if (e.getClick().isRightClick() || e.getClick().isLeftClick()) {
                    specialsMenu.OpenShopConfirm((Player) e.getWhoClicked(), e.getCurrentItem(), 1);
                }
            }
        }
    }

    @EventHandler
    public void PlayerClickItemsBuy(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.specialsconfirm)) {
                if (e.getClick().isLeftClick()) {
                    Player player = (Player) e.getWhoClicked();
                    int prix = specialsPrice.getPriceFromitem(e.getCurrentItem()) * e.getCurrentItem().getAmount();
                    if (gold.GetHasEnoughGold(player, prix)) {
                        if (e.getWhoClicked().getInventory().firstEmpty() == -1) {
                            e.getWhoClicked().sendMessage(shopTranslation
                                    .EmplacementVide(playerLang.getPlayerLang((Player) e.getWhoClicked())));
                        } else {
                            gold.RemoveGold(player, prix);
                            ItemStack itemStack = specialsItems.getItemFromItem(e.getCurrentItem(), lang.languages.en,
                                    e.getCurrentItem().getAmount(), false, true);
                            e.getWhoClicked().getInventory().addItem(itemStack);
                            // shopLogs.LogData(player, false, material, amount);
                            specialsMenu.OpenShopConfirm((Player) e.getWhoClicked(), e.getCurrentItem(),
                                    e.getCurrentItem().getAmount());
                        }
                    } else {
                        e.getWhoClicked().sendMessage(
                                shopTranslation.PasAssezArgent(playerLang.getPlayerLang((Player) e.getWhoClicked())));
                    }
                }
            }
        }
    }
}