package altherneum.fr.menu.shop.grades;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.text.lang;
import altherneum.fr.text.shopTranslation;

public class gradesItems {
    public static ItemStack grade(lang.languages lang) {
        ItemStack is = new ItemStack(Material.CHAINMAIL_HELMET, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.gradeTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.addAll(shopTranslation.gradeLore(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.grade);
        return is;
    }

    public static ItemStack Aventurier(lang.languages lang, boolean hasGrade) {
        ItemStack is = new ItemStack(Material.LEATHER_HELMET, 1);
        if (hasGrade) {
            is.setType(Material.BARRIER);
        }
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.aventurierTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.addAll(shopTranslation.aventurierLore(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.aventurier);
        return is;
    }

    public static ItemStack Soldat(lang.languages lang, boolean hasGrade) {
        ItemStack is = new ItemStack(Material.CHAINMAIL_HELMET, 1);
        if (hasGrade) {
            is.setType(Material.BARRIER);
        }
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.soldatTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.addAll(shopTranslation.soldatLore(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.soldat);
        return is;
    }
}
