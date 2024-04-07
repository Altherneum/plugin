package altherneum.fr.menu.shop.specials;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.menu.island.island;
import altherneum.fr.menu.shop.specials.bonus.autoSell;
import altherneum.fr.menu.shop.specials.bonus.bonus;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.system.ServerBootFile.serverType;

import java.io.IOException;

public class specialsUseEvents implements Listener {
    @EventHandler
    public void onPlayerUseBonus(PlayerInteractEvent e) throws IOException {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getItem() != null) {
                if (persistentData.hasPersistentDataItemStack(e.getItem(), persistentData.customKey.specials)) {

                    e.setCancelled(true);

                    if (persistentData.hasPersistentDataItemStack(e.getItem(), persistentData.customKey.specialsvip)) {
                        removeOneBonus(e.getItem(), e.getPlayer());
                        bonus.addBonus(e.getPlayer(), bonus.BonusList.VIP, 3);
                    }

                    else if (persistentData.hasPersistentDataItemStack(e.getItem(),
                            persistentData.customKey.specialsjoinquit)) {
                        removeOneBonus(e.getItem(), e.getPlayer());
                        bonus.addBonus(e.getPlayer(), bonus.BonusList.JoinQuitMessage, 3);
                    }

                    else if (persistentData.hasPersistentDataItemStack(e.getItem(),
                            persistentData.customKey.specialsaura)) {
                        removeOneBonus(e.getItem(), e.getPlayer());
                        bonus.addBonus(e.getPlayer(), bonus.BonusList.AuraTP, 3);
                    }

                    else if (persistentData.hasPersistentDataItemStack(e.getItem(),
                            persistentData.customKey.specialsfly)) {
                        removeOneBonus(e.getItem(), e.getPlayer());
                        bonus.addBonus(e.getPlayer(), bonus.BonusList.Fly, 3);
                    }

                    else if (persistentData.hasPersistentDataItemStack(e.getItem(),
                            persistentData.customKey.specialsautosell)
                            && e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                        if (ServerBootFile.getServerType().equals(serverType.SkyBlock)
                                && island.IsInHerIsland(e.getPlayer())) {
                            removeOneBonus(e.getItem(), e.getPlayer());
                            autoSell.addChest(e.getClickedBlock(), island.GetIslandNumber(e.getPlayer()));
                        }
                    }
                }
            }
        }
    }

    public static void removeOneBonus(ItemStack itemStack, Player player) {
        ItemStack itemStack1 = new ItemStack(itemStack);
        itemStack1.setAmount(1);
        player.getInventory().removeItem(itemStack1);
    }
}