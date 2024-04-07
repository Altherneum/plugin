package altherneum.fr.menu.shop.specials;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.text.lang;
import altherneum.fr.text.shopTranslation;

import java.util.ArrayList;

public class specialsItems {
    public static ItemStack getItemFromItem(ItemStack itemStack, lang.languages lang, int amount, boolean sell,
            boolean finalItem) {
        for (NamespacedKey key : itemStack.getItemMeta().getPersistentDataContainer().getKeys()) {
            if (itemStack(persistentData.customKey.valueOf(key.getKey()), lang, amount, sell, finalItem)
                    .getType() != Material.AIR) {
                return itemStack(persistentData.customKey.valueOf(key.getKey()), lang, amount, sell, finalItem);
            }
        }
        return new ItemStack(Material.AIR);
    }

    public static ItemStack itemStack(persistentData.customKey key, lang.languages lang, int amount, boolean sell,
            boolean finalItem) {
        switch (key) {
            case specialsautosell:
                return AutoSell(lang, amount, sell, finalItem);
            case specialsvip:
                return VIP(lang, amount, sell, finalItem);
            case specialsjoinquit:
                return JoinQuit(lang, amount, sell, finalItem);
            case specialsaura:
                return Aura(lang, amount, sell, finalItem);
            case specialsfly:
                return Fly(lang, amount, sell, finalItem);
            default:
                return new ItemStack(Material.AIR);
        }
    }

    public static ArrayList<ItemStack> itemsList(lang.languages lang) {
        ArrayList<ItemStack> itemsList = new ArrayList<>();
        itemsList.add(VIP(lang, 1, false, false));
        itemsList.add(JoinQuit(lang, 1, false, false));
        itemsList.add(Aura(lang, 1, false, false));
        itemsList.add(Fly(lang, 1, false, false));
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            itemsList.add(AutoSell(lang, 1, false, false));
        }
        return itemsList;
    }

    public static ItemStack VIP(lang.languages lang, int amount, boolean sell, boolean finalItem) {
        ItemStack is = new ItemStack(Material.DIAMOND);
        is.setAmount(amount);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.VIPTitle(lang));
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.addAll(shopTranslation.VIPLore(lang));
        lore.add("");
        if (!finalItem) {
            lore.add(shopTranslation.BuyPriceBonus(lang, specialsPrice.Prix(persistentData.customKey.specialsvip)));
            if (sell) {
                lore.add("");
                lore.add(shopTranslation.TotalBuyPrice(lang, specialsPrice.Prix(persistentData.customKey.specialsvip),
                        amount));
            }
            lore.add("");
            lore.add(shopTranslation.LeftClick(lang));
            lore.add(shopTranslation.Escape(lang));
            lore.add(shopTranslation.OtherClick(lang));
            lore.add("");
        }
        meta.setLore(lore);
        is.setItemMeta(meta);
        if (!finalItem) {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.specialsmenu);
        }
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.specials);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.specialsvip);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack JoinQuit(lang.languages lang, int amount, boolean sell, boolean finalItem) {
        ItemStack is = new ItemStack(Material.PAPER);
        is.setAmount(amount);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.JoinQuitTitle(lang));
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.addAll(shopTranslation.JoinQuitLore(lang));
        lore.add("");
        if (!finalItem) {
            lore.add(
                    shopTranslation.BuyPriceBonus(lang, specialsPrice.Prix(persistentData.customKey.specialsjoinquit)));
            if (sell) {
                lore.add("");
                lore.add(shopTranslation.TotalBuyPrice(lang,
                        specialsPrice.Prix(persistentData.customKey.specialsjoinquit), amount));
            }
            lore.add("");
            lore.add(shopTranslation.LeftClick(lang));
            lore.add(shopTranslation.Escape(lang));
            lore.add(shopTranslation.OtherClick(lang));
            lore.add("");
        }
        meta.setLore(lore);
        is.setItemMeta(meta);
        if (!finalItem) {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.specialsmenu);
        }
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.specials);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.specialsjoinquit);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack Aura(lang.languages lang, int amount, boolean sell, boolean finalItem) {
        ItemStack is = new ItemStack(Material.FIREWORK_ROCKET);
        is.setAmount(amount);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.AuraTitle(lang));
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.addAll(shopTranslation.AuraLore(lang));
        lore.add("");
        if (!finalItem) {
            lore.add(shopTranslation.BuyPriceBonus(lang, specialsPrice.Prix(persistentData.customKey.specialsaura)));
            if (sell) {
                lore.add(shopTranslation.TotalBuyPrice(lang, specialsPrice.Prix(persistentData.customKey.specialsaura),
                        amount));
            }
            lore.add("");
            lore.add(shopTranslation.LeftClick(lang));
            lore.add(shopTranslation.Escape(lang));
            lore.add(shopTranslation.OtherClick(lang));
            lore.add("");
        }
        meta.setLore(lore);
        is.setItemMeta(meta);
        if (!finalItem) {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.specialsmenu);
        }
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.specials);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.specialsaura);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack Fly(lang.languages lang, int amount, boolean sell, boolean finalItem) {
        ItemStack is = new ItemStack(Material.FEATHER);
        is.setAmount(amount);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.FlyTitle(lang));
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.addAll(shopTranslation.FlyLore(lang));
        lore.add("");
        if (!finalItem) {
            lore.add(shopTranslation.BuyPriceBonus(lang, specialsPrice.Prix(persistentData.customKey.specialsfly)));
            if (sell) {
                lore.add("");
                lore.add(shopTranslation.TotalBuyPrice(lang, specialsPrice.Prix(persistentData.customKey.specialsfly),
                        amount));
            }
            lore.add("");
            lore.add(shopTranslation.LeftClick(lang));
            lore.add(shopTranslation.Escape(lang));
            lore.add(shopTranslation.OtherClick(lang));
            lore.add("");
        }
        meta.setLore(lore);
        is.setItemMeta(meta);
        if (!finalItem) {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.specialsmenu);
        }
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.specials);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.specialsfly);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack AutoSell(lang.languages lang, int amount, boolean sell, boolean finalItem) {
        ItemStack is = new ItemStack(Material.NAME_TAG);
        is.setAmount(amount);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.VenteAutoTitle(lang));
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.addAll(shopTranslation.VenteAutoLore(lang));
        lore.add("");
        if (!finalItem) {
            lore.add(
                    shopTranslation.BuyPriceBonus(lang, specialsPrice.Prix(persistentData.customKey.specialsautosell)));
            if (sell) {
                lore.add("");
                lore.add(shopTranslation.TotalBuyPrice(lang,
                        specialsPrice.Prix(persistentData.customKey.specialsautosell), amount));
            }
            lore.add("");
            lore.add(shopTranslation.LeftClick(lang));
            lore.add(shopTranslation.Escape(lang));
            lore.add(shopTranslation.OtherClick(lang));
            lore.add("");
        }
        meta.setLore(lore);
        is.setItemMeta(meta);
        if (!finalItem) {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.specialsmenu);
        }
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.specials);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.specialsautosell);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }
}