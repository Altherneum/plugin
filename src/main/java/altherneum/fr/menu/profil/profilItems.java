package altherneum.fr.menu.profil;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;

import java.io.IOException;

public class profilItems {
    public static ItemStack ItemStackInfo(lang.languages lang, OfflinePlayer offlinePlayer) throws IOException {
        ItemStack is = new ItemStack(Material.BOOK);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.InfoTitle(lang));
        meta.setLore(itemsTranslation.InfoLore(lang, offlinePlayer));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.info);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack ItemStackTags(lang.languages lang, OfflinePlayer offlinePlayer) throws IOException {
        ItemStack is = new ItemStack(Material.OAK_SIGN);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.TagsTitle(lang));
        meta.setLore(itemsTranslation.TagsLore(lang, offlinePlayer));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.tags);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack ItemStackPlayerData(lang.languages lang, OfflinePlayer offlinePlayer) throws IOException {
        ItemStack is = new ItemStack(Material.ARMOR_STAND);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.PlayerDataTitle(lang));
        meta.setLore(itemsTranslation.PlayerDataLore(lang, offlinePlayer));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.playerdata);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }
}