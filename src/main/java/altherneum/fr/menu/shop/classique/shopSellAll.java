package altherneum.fr.menu.shop.classique;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import altherneum.fr.system.gold;

public class shopSellAll {
    public static void sellAll(Player player) throws IOException {
        // check server type != creatif
        
        Inventory inventory = player.getInventory();

        int total = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item != null && !item.getType().equals(Material.AIR)) {
                if (shopPrice.getPrix(item.getType()) != Integer.MAX_VALUE) {
                    total += (shopPrice.getPrix(item.getType()) * item.getAmount());
                    inventory.remove(item);
                }
            }
        }

        if (total > 0) {
            gold.AddGold(player, total / 10);
        }
    }
}
