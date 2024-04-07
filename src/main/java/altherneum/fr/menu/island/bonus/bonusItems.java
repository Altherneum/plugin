package altherneum.fr.menu.island.bonus;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.text.lang;
import altherneum.fr.text.shopTranslation;

import java.io.IOException;
import java.util.ArrayList;

public class bonusItems {
    public static ArrayList<ItemStack> itemsList(lang.languages lang, OfflinePlayer offlinePlayer) throws IOException {
        ArrayList<ItemStack> itemsList = new ArrayList<>();
        itemsList.add(worldBoarder(lang, false,
                bonusPlayerFile.getLvl(offlinePlayer, persistentData.customKey.bonusworldborder),
                bonusPlayerFile.getLvlIsland(offlinePlayer, persistentData.customKey.bonusworldborder), 1));
        itemsList.add(Ores(lang, false, bonusPlayerFile.getLvl(offlinePlayer, persistentData.customKey.bonusores),
                bonusPlayerFile.getLvlIsland(offlinePlayer, persistentData.customKey.bonusores), 1));
        itemsList.add(PlayerAmount(lang, false,
                bonusPlayerFile.getLvl(offlinePlayer, persistentData.customKey.bonusplayeramount),
                bonusPlayerFile.getLvlIsland(offlinePlayer, persistentData.customKey.bonusplayeramount), 1));
        itemsList.add(
                TickSpeed(lang, false, bonusPlayerFile.getLvl(offlinePlayer, persistentData.customKey.bonustickspeed),
                        bonusPlayerFile.getLvlIsland(offlinePlayer, persistentData.customKey.bonustickspeed), 1));
        return itemsList;
    }

    public static persistentData.customKey getKeyFromItem(ItemStack itemStack) throws IOException {
        if (persistentData.hasPersistentDataItemStack(itemStack, persistentData.customKey.bonusworldborder)) {
            return persistentData.customKey.bonusworldborder;
        } else if (persistentData.hasPersistentDataItemStack(itemStack, persistentData.customKey.bonusores)) {
            return persistentData.customKey.bonusores;
        } else if (persistentData.hasPersistentDataItemStack(itemStack, persistentData.customKey.bonusplayeramount)) {
            return persistentData.customKey.bonusplayeramount;
        } else if (persistentData.hasPersistentDataItemStack(itemStack, persistentData.customKey.bonustickspeed)) {
            return persistentData.customKey.bonustickspeed;
        }
        return null;
    }

    public static ItemStack getItemFromKey(persistentData.customKey key, lang.languages lang,
            OfflinePlayer offlinePlayer, int amount) throws IOException {
        if (key.equals(persistentData.customKey.bonusworldborder)) {
            return worldBoarder(lang, true,
                    bonusPlayerFile.getLvl(offlinePlayer, persistentData.customKey.bonusworldborder),
                    bonusPlayerFile.getLvlIsland(offlinePlayer, persistentData.customKey.bonusworldborder), amount);
        } else if (key.equals(persistentData.customKey.bonusores)) {
            return Ores(lang, true, bonusPlayerFile.getLvl(offlinePlayer, persistentData.customKey.bonusores),
                    bonusPlayerFile.getLvlIsland(offlinePlayer, persistentData.customKey.bonusores), amount);
        } else if (key.equals(persistentData.customKey.bonusplayeramount)) {
            return PlayerAmount(lang, true,
                    bonusPlayerFile.getLvl(offlinePlayer, persistentData.customKey.bonusplayeramount),
                    bonusPlayerFile.getLvlIsland(offlinePlayer, persistentData.customKey.bonusplayeramount), amount);
        } else if (key.equals(persistentData.customKey.bonustickspeed)) {
            return TickSpeed(lang, true, bonusPlayerFile.getLvl(offlinePlayer, persistentData.customKey.bonustickspeed),
                    bonusPlayerFile.getLvlIsland(offlinePlayer, persistentData.customKey.bonustickspeed), amount);
        }
        return new ItemStack(Material.AIR);
    }

    public static ItemStack worldBoarder(lang.languages lang, boolean confirm, int lvl, int islandlvl, int amount) {
        ItemStack is = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.WorldBoarderTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.addAll(shopTranslation.WorldBoarderLore(lang));
        Lore.add(" ");
        Lore.addAll(shopTranslation.WorldBoarderLVL(lang, lvl, islandlvl));
        Lore.add(" ");
        Lore.add(shopTranslation.BuyPriceBonus(lang,
                bonusPrice.Price(persistentData.customKey.bonusworldborder) * amount));
        Lore.add(" ");
        Lore.add(shopTranslation.LeftClick(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(shopTranslation.OtherClick(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.bonusworldborder);
        if (confirm) {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.bonusconfirm);
        } else {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.bonusmenu);
        }
        return is;
    }

    public static ItemStack Ores(lang.languages lang, boolean confirm, int lvl, int islandlvl, int amount) {
        ItemStack is = new ItemStack(Material.IRON_ORE, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.OresTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.addAll(shopTranslation.OresLore(lang));
        Lore.add(" ");
        Lore.addAll(shopTranslation.OresLoreLVL(lang, lvl, islandlvl));
        Lore.add(" ");
        Lore.add(shopTranslation.BuyPriceBonus(lang, bonusPrice.Price(persistentData.customKey.bonusores) * amount));
        Lore.add(" ");
        Lore.add(shopTranslation.LeftClick(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(shopTranslation.OtherClick(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.bonusores);
        if (confirm) {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.bonusconfirm);
        } else {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.bonusmenu);
        }
        return is;
    }

    public static ItemStack PlayerAmount(lang.languages lang, boolean confirm, int lvl, int islandlvl, int amount) {
        ItemStack is = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.PlayerAmountTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.addAll(shopTranslation.PlayerAmountLore(lang));
        Lore.add(" ");
        Lore.addAll(shopTranslation.PlayerAmountLoreLVL(lang, lvl, islandlvl));
        Lore.add(" ");
        Lore.add(shopTranslation.BuyPriceBonus(lang,
                bonusPrice.Price(persistentData.customKey.bonusplayeramount) * amount));
        Lore.add(" ");
        Lore.add(shopTranslation.LeftClick(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(shopTranslation.OtherClick(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.bonusplayeramount);
        if (confirm) {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.bonusconfirm);
        } else {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.bonusmenu);
        }
        return is;
    }

    public static ItemStack TickSpeed(lang.languages lang, boolean confirm, int lvl, int islandlvl, int amount) {
        ItemStack is = new ItemStack(Material.CLOCK, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.TickSpeedTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.addAll(shopTranslation.TickSpeedLore(lang));
        Lore.add(" ");
        Lore.addAll(shopTranslation.TickSpeedLoreLVL(lang, lvl, islandlvl));
        Lore.add(" ");
        Lore.add(shopTranslation.BuyPriceBonus(lang,
                bonusPrice.Price(persistentData.customKey.bonustickspeed) * amount));
        Lore.add(" ");
        Lore.add(shopTranslation.LeftClick(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(shopTranslation.OtherClick(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.bonustickspeed);
        if (confirm) {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.bonusconfirm);
        } else {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.bonusmenu);
        }
        return is;
    }
}