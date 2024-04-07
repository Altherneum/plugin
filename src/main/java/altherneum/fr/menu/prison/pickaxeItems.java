package altherneum.fr.menu.prison;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;

public class pickaxeItems {
    public static ItemStack ItemStackPickaxeSelector(lang.languages lang) {
        ItemStack is = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.PickaxeSelectorTitle(lang));
        meta.setLore(itemsTranslation.PickaxeSelectorLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.pickaxeselector);
        return is;
    }

    public static ItemStack ItemStackPickaxeCantUpgrade(lang.languages lang) throws IOException {
        ItemStack is = new ItemStack(Material.BARRIER);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.PickaxeCantUpgradeTitle(lang));
        meta.setLore(itemsTranslation.PickaxeCantUpgradeLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        return is;
    }

    public static ItemStack ItemStackPickaxeUpgrade(lang.languages lang, Player player) throws IOException {
        ItemStack is = new ItemStack(pickaxe.Nextpickaxe(prisonData.getPickaxe(player)));
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.PickaxeUpgradeTitle(lang));
        meta.setLore(itemsTranslation.PickaxeUpgradeLore(lang, player));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.pickaxeupgrade);
        return is;
    }

    public static ItemStack ItemStackPickaxeEnchantLooting(lang.languages lang, Player player) throws IOException {
        ItemStack is = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = is.getItemMeta();
        Enchantment enchant = Enchantment.LOOT_BONUS_BLOCKS;
        meta.setDisplayName(itemsTranslation.PickaxeEnchantTitle(lang, enchant));
        meta.setLore(itemsTranslation.PickaxeEnchantLore(lang, player, enchant));
        is.setItemMeta(meta);

        is.addUnsafeEnchantment(enchant, 1);

        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.pickaxeenchant);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.pickaxeenchantlooting);
        return is;
    }

    public static ItemStack ItemStackPickaxeEnchantDigSpeed(lang.languages lang, Player player) throws IOException {
        ItemStack is = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = is.getItemMeta();
        Enchantment enchant = Enchantment.DIG_SPEED;
        meta.setDisplayName(itemsTranslation.PickaxeEnchantTitle(lang, enchant));
        meta.setLore(itemsTranslation.PickaxeEnchantLore(lang, player, enchant));
        is.setItemMeta(meta);

        is.addUnsafeEnchantment(enchant, 1);

        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.pickaxeenchant);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.pickaxeenchantspeed);
        return is;
    }
}
