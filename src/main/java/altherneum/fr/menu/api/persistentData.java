package altherneum.fr.menu.api;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import altherneum.fr.main.main;

public class persistentData {
    private static final JavaPlugin plugin = main.getPlugin(main.class);

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static NamespacedKey getKeyAsNameSpaced(persistentData.customKey customKey) {
        return new NamespacedKey(getPlugin(), customKey.toString());
    }

    public static void setPersistentDataItemStack(ItemStack itemStack, persistentData.customKey customKey,
            PersistentDataType persistentDataType) {
        if (itemStack == null || itemStack.getType().equals(Material.AIR) || itemStack.getItemMeta() == null) {
            return;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        persistentDataContainer.set(getKeyAsNameSpaced(customKey), persistentDataType, customKey.toString());
        itemStack.setItemMeta(itemMeta);
    }

    public static void setPersistentDataItemStack(ItemStack itemStack, persistentData.customKey customKey) {
        setPersistentDataItemStack(itemStack, customKey, PersistentDataType.STRING);
        ItemMeta meta = itemStack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);
    }

    public static void removePersistentDataItemStack(ItemStack itemStack, persistentData.customKey customKey) {
        if (itemStack == null || itemStack.getType().equals(Material.AIR) || itemStack.getItemMeta() == null
                || !hasPersistentDataItemStack(itemStack, customKey)) {
            return;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        persistentDataContainer.remove(getKeyAsNameSpaced(customKey));
        itemStack.setItemMeta(itemMeta);
    }

    public static Boolean hasPersistentDataItemStack(ItemStack itemStack, customKey customKey,
            PersistentDataType persistentDataType) {
        if (itemStack == null || itemStack.getType().equals(Material.AIR) || itemStack.getItemMeta() == null) {
            return false;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        if (persistentDataContainer.has(getKeyAsNameSpaced(customKey), persistentDataType)) {
            return persistentDataContainer.get(getKeyAsNameSpaced(customKey), persistentDataType)
                    .equals(customKey.toString());
        }
        return false;
    }

    public static Boolean hasPersistentDataItemStack(ItemStack itemStack, persistentData.customKey customKey) {
        return hasPersistentDataItemStack(itemStack, customKey, PersistentDataType.STRING);
    }

    public static Boolean isCustomItemStack(ItemStack itemStack) {
        return hasPersistentDataItemStack(itemStack, customKey.custom);
    }

    public enum customKey {
        custom, ile, ileboost, voyage, shop, enchant, quest, compass, locked, /*
                                                                              */
        ilecreate, iletp, ilekick, ileinvite, ilelocate, ileowner, ilepending, ileplayerhead, inviteplayerhead,
        excludeplayerhead, ileleave, ileleaveconfirm, ownerplayerhead, pendingplayerhead, ileprofilviste, /*
                                                                                                          */
        shopblocks, shopgraines, shopminerais, shopmonstres, shopplantes, shopnourritures, shopspeciaux, shopoeufs,
        shopaquatiques, shopnether, shopcustom, shopteintures, /*
                                                               */
        shopitemtosell, shopsplitter, shopitemtoselllist, shopsellall, /*
                                                                       */
        specialsautosell, specialsfly, specialsaura, specialsjoinquit, specialsvip, specials, specialsmenu,
        specialsconfirm, /*
                         */
        bonusores, bonusworldborder, bonustickspeed, bonusplayeramount, bonusmenu, spawn, world, bed, info, tags, playerdata,
        bonusconfirm, /*
                      */
        hub, serverselector, survie, skyblock, rpg, anarchie, creatif,
        opprison, /*
                  */
        minestone, mineiron, minegold, minediamond, minenetherite, mineselector, pickaxeselector, pickaxeupgrade,
        pickaxeenchant, pickaxeenchantlooting, pickaxeenchantspeed, mypickaxe, /*
                                                                               */
        settings, resourcepack, langswap, compasspose, chatsetting,

        grade, aventurier, soldat,

        anarchielife
    }
}