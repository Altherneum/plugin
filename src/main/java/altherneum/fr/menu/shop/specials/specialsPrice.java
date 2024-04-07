package altherneum.fr.menu.shop.specials;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import altherneum.fr.menu.api.persistentData;

public class specialsPrice {
    public static int Prix(persistentData.customKey key) {
        switch (key) {
            case specialsautosell:
                return 1000000;
            case specialsvip:
                return 250000;
            case specialsjoinquit:
            case specialsaura:
                return 100000;
            case specialsfly:
                return 500000;
            default:
                return Integer.MAX_VALUE;
        }
    }

    public static int getPriceFromitem(ItemStack itemStack) {
        for (NamespacedKey key : itemStack.getItemMeta().getPersistentDataContainer().getKeys()) {
            if (Prix(persistentData.customKey.valueOf(key.getKey())) != Integer.MAX_VALUE) {
                return Prix(persistentData.customKey.valueOf(key.getKey()));
            }
        }
        return Integer.MAX_VALUE;
    }
}