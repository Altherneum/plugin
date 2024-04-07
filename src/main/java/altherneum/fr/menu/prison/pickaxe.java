package altherneum.fr.menu.prison;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;

public class pickaxe {
    public static ItemStack getPickaxe(OfflinePlayer offlinePlayer) throws IOException {
        lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
        ItemStack is = new ItemStack(prisonData.getPickaxe(offlinePlayer));
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.PickaxeSelectorTitle(lang));
        meta.setLore(itemsTranslation.YourPickaxeLore(lang));

        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        is.setItemMeta(meta);

        ArrayList<String> keysString = prisonData.getEnchant(offlinePlayer);
        for (String key : keysString) {
            Enchantment enchantment = Enchantment.getByKey(NamespacedKey.fromString(key));
            int lvl = prisonData.getEnchantLvl(offlinePlayer, enchantment);
            is.addUnsafeEnchantment(enchantment, lvl);
        }

        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.mypickaxe);
        return is;
    }

    public static Material Nextpickaxe(Material material) {
        switch (material) {
            case WOODEN_PICKAXE:
                return Material.STONE_PICKAXE;
            case STONE_PICKAXE:
                return Material.IRON_PICKAXE;
            case IRON_PICKAXE:
                return Material.GOLDEN_PICKAXE;
            case GOLDEN_PICKAXE:
                return Material.DIAMOND_PICKAXE;
            case DIAMOND_PICKAXE:
                return Material.NETHERITE_PICKAXE;
            default:
                return Material.AIR;
        }
    }

    public static int NextpickaxePrice(Material material) {
        switch (material) {
            case WOODEN_PICKAXE:
                return 250;
            case STONE_PICKAXE:
                return 1_000;
            case IRON_PICKAXE:
                return 5_000;
            case GOLDEN_PICKAXE:
                return 25_000;
            case DIAMOND_PICKAXE:
                return 100_000;
            case NETHERITE_PICKAXE:
                return Integer.MAX_VALUE;
            default:
                return Integer.MAX_VALUE;
        }
    }

    public static int enchantPrice(altherneum.fr.menu.api.persistentData.customKey key, int lvl) {
        switch (key) {
            case pickaxeenchantlooting:
                return 1_000 * (lvl + 1);
            case pickaxeenchantspeed:
                return 1_000 * (lvl + 1);
            default:
                return Integer.MAX_VALUE;
        }
    }

    public static altherneum.fr.menu.api.persistentData.customKey enchantKey(Enchantment enchantment) {
        if (enchantment.equals(Enchantment.LOOT_BONUS_BLOCKS)) {
            return altherneum.fr.menu.api.persistentData.customKey.pickaxeenchantlooting;
        }
        
        if (enchantment.equals(Enchantment.DIG_SPEED)) {
            return altherneum.fr.menu.api.persistentData.customKey.pickaxeenchantspeed;
        }
        return null;
    }

    public static void UpdatePickaxe(Player player) throws IllegalArgumentException, IOException {
        for (ItemStack is : player.getInventory().getContents()) {
            if (persistentData.hasPersistentDataItemStack(is, altherneum.fr.menu.api.persistentData.customKey.mypickaxe)) {
                player.getInventory().remove(is);
                break;
            }
        }

        player.getInventory().addItem(getPickaxe(player));
    }
}
