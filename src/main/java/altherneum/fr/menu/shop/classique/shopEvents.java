package altherneum.fr.menu.shop.classique;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.menu.island.bonus.bonusMenu;
import altherneum.fr.menu.shop.specials.specialsMenu;
import altherneum.fr.system.gold;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.shopTranslation;

import java.io.IOException;
import java.text.ParseException;

public class shopEvents implements Listener {
    @EventHandler
    public void PlayerClickCompass(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.shop)) {
                shopMenu.OpenShop((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickSubMenu(InventoryClickEvent e) throws IllegalArgumentException, IOException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.shopblocks)) {
                shopMenu.OpenShop((Player) e.getWhoClicked(), shopItemsList.getBlocks(),
                        shopTranslation.BlocksTitle(playerLang.getPlayerLang((Player) e.getWhoClicked())));
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopgraines)) {
                shopMenu.OpenShop((Player) e.getWhoClicked(), shopItemsList.getSeeds(),
                        shopTranslation.GrainesTitle(playerLang.getPlayerLang((Player) e.getWhoClicked())));
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopminerais)) {
                shopMenu.OpenShop((Player) e.getWhoClicked(), shopItemsList.getOres(),
                        shopTranslation.MineraisTitle(playerLang.getPlayerLang((Player) e.getWhoClicked())));
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopmonstres)) {
                shopMenu.OpenShop((Player) e.getWhoClicked(), shopItemsList.getMonstres(),
                        shopTranslation.MonstresTitle(playerLang.getPlayerLang((Player) e.getWhoClicked())));
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopplantes)) {
                shopMenu.OpenShop((Player) e.getWhoClicked(), shopItemsList.getPlantes(),
                        shopTranslation.PlantesTitle(playerLang.getPlayerLang((Player) e.getWhoClicked())));
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopnourritures)) {
                shopMenu.OpenShop((Player) e.getWhoClicked(), shopItemsList.getFoods(),
                        shopTranslation.NourrituresTitle(playerLang.getPlayerLang((Player) e.getWhoClicked())));
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopspeciaux)) {
                shopMenu.OpenShop((Player) e.getWhoClicked(), shopItemsList.getSpecials(),
                        shopTranslation.SpeciauxTitle(playerLang.getPlayerLang((Player) e.getWhoClicked())));
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopoeufs)) {
                shopMenu.OpenShop((Player) e.getWhoClicked(), shopItemsList.getOeufs(),
                        shopTranslation.OeufsTitle(playerLang.getPlayerLang((Player) e.getWhoClicked())));
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopaquatiques)) {
                shopMenu.OpenShop((Player) e.getWhoClicked(), shopItemsList.getWater(),
                        shopTranslation.AquatiquesTitle(playerLang.getPlayerLang((Player) e.getWhoClicked())));
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopnether)) {
                shopMenu.OpenShop((Player) e.getWhoClicked(), shopItemsList.getNether(),
                        shopTranslation.NetherTitle(playerLang.getPlayerLang((Player) e.getWhoClicked())));
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopteintures)) {
                shopMenu.OpenShop((Player) e.getWhoClicked(), shopItemsList.getDye(),
                        shopTranslation.TeinturesTitle(playerLang.getPlayerLang((Player) e.getWhoClicked())));
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopcustom)) {
                specialsMenu.OpenShop((Player) e.getWhoClicked());
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopsellall)) {
                shopSellAll.sellAll((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickItemsList(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopitemtoselllist)) {
                if (e.getClick().isRightClick() || e.getClick().isLeftClick()) {
                    ItemStack itemStack = shopItems.itemToSell(e.getCurrentItem().getType(),
                            shopPrice.getPrix(e.getCurrentItem().getType()), 1, true,
                            playerLang.getPlayerLang((OfflinePlayer) e.getWhoClicked()));
                    shopMenu.OpenShopBuy((Player) e.getWhoClicked(), 1, shopPrice.getPrix(e.getCurrentItem().getType()),
                            itemStack);
                    // shopMenu.OpenShopBuy((Player) e.getWhoClicked(),
                    // e.getCurrentItem().getType(), 1);
                }
            }
        }
    }

    @EventHandler
    public void PlayerOtherClickToMenu(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopitemtoselllist)
                    || persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                            persistentData.customKey.shopitemtosell)
                    || persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                            persistentData.customKey.specialsmenu)) {
                if (!e.getClick().isRightClick() && !e.getClick().isLeftClick()) {
                    shopMenu.OpenShop((Player) e.getWhoClicked());
                }
            }
        }
    }

    @EventHandler
    public void PlayerClickItemsBuy(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopitemtosell)) {
                if (e.getClick().isLeftClick()) {
                    Player player = (Player) e.getWhoClicked();
                    Material material = e.getCurrentItem().getType();
                    int prix = shopPrice.getPrix(material);
                    int amount = e.getCurrentItem().getAmount();
                    if (gold.GetHasEnoughGold(player, prix * amount)) {
                        if (e.getWhoClicked().getInventory().firstEmpty() == -1) {
                            e.getWhoClicked().sendMessage(shopTranslation
                                    .EmplacementVide(playerLang.getPlayerLang((Player) e.getWhoClicked())));
                        } else {
                            gold.RemoveGold(player, prix * amount);
                            e.getWhoClicked().getInventory().addItem(new ItemStack(material, amount));
                            shopLogs.LogData(player, false, material, amount);
                            ItemStack itemStack = shopItems.itemToSell(e.getCurrentItem().getType(),
                                    shopPrice.getPrix(e.getCurrentItem().getType()), amount, true,
                                    playerLang.getPlayerLang(player));
                            shopMenu.OpenShopBuy(player, amount, shopPrice.getPrix(e.getCurrentItem().getType()),
                                    itemStack);
                            // shopMenu.OpenShopBuy(player, material, amount);
                        }
                    } else {
                        e.getWhoClicked().sendMessage(
                                shopTranslation.PasAssezArgent(playerLang.getPlayerLang((Player) e.getWhoClicked())));
                    }
                }
            }
        }
    }

    @EventHandler
    public void PlayerClickItemsSell(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.shopitemtosell)) {
                if (e.getClick().isRightClick()) {
                    Player player = (Player) e.getWhoClicked();
                    Material material = e.getCurrentItem().getType();
                    int prix = shopPrice.getPrix(material);
                    int amount = e.getCurrentItem().getAmount();
                    ItemStack itemStack = new ItemStack(material, amount);
                    if (e.getWhoClicked().getInventory().containsAtLeast(new ItemStack(material), amount)) {
                        e.getWhoClicked().getInventory().removeItem(itemStack);
                        gold.AddGold(player, (prix * amount) / 10);
                        shopLogs.LogData(player, true, material, amount);
                        ItemStack itemStack2 = shopItems.itemToSell(e.getCurrentItem().getType(),
                                shopPrice.getPrix(e.getCurrentItem().getType()), amount, true,
                                playerLang.getPlayerLang(player));
                        shopMenu.OpenShopBuy(player, amount, shopPrice.getPrix(e.getCurrentItem().getType()),
                                itemStack2);
                        // shopMenu.OpenShopBuy(player, material, amount);
                    } else {
                        e.getWhoClicked().sendMessage(shopTranslation
                                .PasAssezObjetVendre(playerLang.getPlayerLang((Player) e.getWhoClicked())));
                    }
                }
            }
        }
    }

    @EventHandler
    public void PlayerClickSplitter(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.shopsplitter)) {
                int amount = e.getCurrentItem().getAmount();
                ItemStack itemStack = e.getClickedInventory().getItem(4);
                int actuelAmount = itemStack.getAmount();
                int finalAmount = 0;
                if (e.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)) {
                    if ((actuelAmount + amount) <= itemStack.getMaxStackSize()) {
                        if (actuelAmount + amount <= 64) {
                            finalAmount = actuelAmount + amount;
                        } else {
                            finalAmount = 64;
                        }
                    } else {
                        finalAmount = itemStack.getMaxStackSize();
                    }
                } else if (e.getCurrentItem().getType().equals(Material.REDSTONE_BLOCK)) {
                    if (actuelAmount - amount >= 1) {
                        finalAmount = actuelAmount - amount;
                    } else {
                        finalAmount = 1;
                    }
                }

                if (persistentData.hasPersistentDataItemStack(itemStack, persistentData.customKey.specialsconfirm)) {
                    specialsMenu.OpenShopConfirm((Player) e.getWhoClicked(), e.getClickedInventory().getItem(4),
                            finalAmount);
                } else if (persistentData.hasPersistentDataItemStack(itemStack,
                        persistentData.customKey.bonusconfirm)) {
                    bonusMenu.OpenShopConfirm((Player) e.getWhoClicked(), e.getClickedInventory().getItem(4),
                            finalAmount);
                } else {
                    ItemStack itemStack2 = shopItems.itemToSell(itemStack.getType(),
                            shopPrice.getPrix(itemStack.getType()), finalAmount, true,
                            playerLang.getPlayerLang((Player) e.getWhoClicked()));
                    shopMenu.OpenShopBuy((Player) e.getWhoClicked(), amount, shopPrice.getPrix(itemStack.getType()),
                            itemStack2);
                }
            }
        }
    }
}