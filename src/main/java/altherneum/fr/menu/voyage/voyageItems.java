package altherneum.fr.menu.voyage;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;

public class voyageItems {
    public static ItemStack ItemStackSpawn(lang.languages lang) {
        ItemStack is = new ItemStack(Material.COMPASS);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.spawnTitle());
        meta.setLore(itemsTranslation.spawnLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.spawn);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack ItemStackWorld(lang.languages lang) {
        ItemStack is = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.mainWorldTitle());
        meta.setLore(itemsTranslation.mainWorldLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.world);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack ItemStackBed(lang.languages lang) {
        ItemStack is = new ItemStack(Material.RED_BED);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.mainWorldTitle());
        meta.setLore(itemsTranslation.mainBedLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.bed);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }
}