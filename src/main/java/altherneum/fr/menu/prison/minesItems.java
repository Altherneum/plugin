package altherneum.fr.menu.prison;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;

public class minesItems {
    public static ItemStack ItemStackMineSelector(lang.languages lang) {
        ItemStack is = new ItemStack(Material.STONE);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.MineSelectorTitle(lang));
        meta.setLore(itemsTranslation.MineSelectorLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.mineselector);
        return is;
    }

    public static ItemStack ItemStackMineStone(lang.languages lang, Player player) throws IOException {
        Boolean mineUnlocked = prisonData.getMinesUnlocked(player).contains("Mine.1");
        ItemStack is;
        if (mineUnlocked) {
            is = new ItemStack(Material.STONE);
        } else {
            is = new ItemStack(Material.BARRIER);
        }
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.MineStoneTitle(lang));
        meta.setLore(itemsTranslation.MineStoneLore(lang, mineUnlocked));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.minestone);
        return is;
    }

    public static ItemStack ItemStackMineIron(lang.languages lang, Player player) throws IOException {
        Boolean mineUnlocked = prisonData.getMinesUnlocked(player).contains("Mine.2");
        ItemStack is;
        if (mineUnlocked) {
            is = new ItemStack(Material.IRON_INGOT);
        } else {
            is = new ItemStack(Material.BARRIER);
        }
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.MineIronTitle(lang));
        meta.setLore(itemsTranslation.MineIronLore(lang, mineUnlocked));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.mineiron);
        return is;
    }

    public static ItemStack ItemStackMineGold(lang.languages lang, Player player) throws IOException {
        Boolean mineUnlocked = prisonData.getMinesUnlocked(player).contains("Mine.3");
        ItemStack is;
        if (mineUnlocked) {
            is = new ItemStack(Material.GOLD_INGOT);
        } else {
            is = new ItemStack(Material.BARRIER);
        }
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.MineGoldTitle(lang));
        meta.setLore(itemsTranslation.MineGoldLore(lang, mineUnlocked));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.minegold);
        return is;
    }

    public static ItemStack ItemStackMineDiamond(lang.languages lang, Player player) throws IOException {
        Boolean mineUnlocked = prisonData.getMinesUnlocked(player).contains("Mine.4");
        ItemStack is;
        if (mineUnlocked) {
            is = new ItemStack(Material.DIAMOND);
        } else {
            is = new ItemStack(Material.BARRIER);
        }
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.MineDiamondTitle(lang));
        meta.setLore(itemsTranslation.MineDiamondLore(lang, mineUnlocked));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.minediamond);
        return is;
    }

    public static ItemStack ItemStackMineNetherite(lang.languages lang, Player player) throws IOException {
        Boolean mineUnlocked = prisonData.getMinesUnlocked(player).contains("Mine.5");
        ItemStack is;
        if (mineUnlocked) {
            is = new ItemStack(Material.NETHERITE_INGOT);
        } else {
            is = new ItemStack(Material.BARRIER);
        }
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.MineNetheriteTitle(lang));
        meta.setLore(itemsTranslation.MineNetheriteLore(lang, mineUnlocked));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.minenetherite);
        return is;
    }
}
