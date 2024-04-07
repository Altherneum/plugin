package altherneum.fr.menu.settings;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;

public class settingsItems {
    public static ItemStack ItemStackResourcePack(lang.languages lang) throws IOException {
        ItemStack is = new ItemStack(Material.ITEM_FRAME);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.ResourcePackTitle(lang));
        meta.setLore(itemsTranslation.ResourcePackLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.resourcepack);
        return is;
    }

    public static ItemStack ItemStackLang(lang.languages lang) throws IOException {
        ItemStack is = new ItemStack(Material.BOOK);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.LangTitle(lang));
        meta.setLore(itemsTranslation.LangLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.langswap);
        return is;
    }

    public static ItemStack ItemStackCompassPose(lang.languages lang) throws IOException {
        ItemStack is = new ItemStack(Material.COMPASS);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.CompassPoseTitle(lang));
        meta.setLore(itemsTranslation.CompassPoseLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.compasspose);
        return is;
    }

    public static ItemStack ItemStackChat(lang.languages lang) throws IOException {
        ItemStack is = new ItemStack(Material.OAK_SIGN);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.ChatTitle(lang));
        meta.setLore(itemsTranslation.ChatLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.chatsetting);
        return is;
    }
}
