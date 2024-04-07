package altherneum.fr.menu.api;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import altherneum.fr.main.main;

import java.io.IOException;

public class persistentDataClick implements Listener {
    // check for armor
    // when equiped, et si Shift click (fast equip)
    @EventHandler
    public void leave(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                player.updateInventory();
            }
        }.runTaskLater(JavaPlugin.getPlugin(main.class), 0);
    }

    @EventHandler
    public void pickItem(EntityPickupItemEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player) {
            Player player = (Player) event.getEntity();
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.updateInventory();
                }
            }.runTaskLater(JavaPlugin.getPlugin(main.class), 0);
        }

    }

    @EventHandler
    public void playerClickPersistentItem(InventoryClickEvent e) throws IllegalArgumentException, IOException {
        if (e.getCurrentItem() != null) {
            if (persistentData.isCustomItemStack(e.getCurrentItem())) {
                if (containCanceledKey(e.getCurrentItem().getItemMeta().getPersistentDataContainer())) {
                    e.setCancelled(true);
                    return;
                }
            }
        }

        if (e.getClick() == ClickType.NUMBER_KEY
                && e.getView().getBottomInventory().getItem(e.getHotbarButton()) != null) {
            if (persistentData.isCustomItemStack(e.getView().getBottomInventory().getItem(e.getHotbarButton()))) {
                if (containCanceledKey(e.getView().getBottomInventory().getItem(e.getHotbarButton()).getItemMeta()
                        .getPersistentDataContainer())) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void playerDropPersistentItem(PlayerDropItemEvent e) {
        if (e.getItemDrop() != null) {
            if (persistentData.isCustomItemStack(e.getItemDrop().getItemStack())) {
                if (containCanceledKey(e.getItemDrop().getItemStack().getItemMeta().getPersistentDataContainer())) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void playerSwapPersistentItemOffHand(PlayerSwapHandItemsEvent e) {
        if (e.getMainHandItem() != null && !e.getMainHandItem().getType().equals(Material.AIR)) {
            if (persistentData.isCustomItemStack(e.getMainHandItem())) {
                if (containCanceledKey(e.getMainHandItem().getItemMeta().getPersistentDataContainer())) {
                    e.setCancelled(true);
                    return;
                }
            }
        }

        if (e.getOffHandItem() != null && !e.getOffHandItem().getType().equals(Material.AIR)) {
            if (persistentData.isCustomItemStack(e.getOffHandItem())) {
                if (containCanceledKey(e.getOffHandItem().getItemMeta().getPersistentDataContainer())) {
                    e.setCancelled(true);
                }
            }
        }
    }

    public static boolean containCanceledKey(PersistentDataContainer persistentDataContainer) {
        for (NamespacedKey key : persistentDataContainer.getKeys()) {
            if (!cancel(persistentData.customKey.valueOf(key.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public static boolean cancel(persistentData.customKey customKey) {
        /*
         * case voyage:
         * return false;
         */
        return true;
    }

    @EventHandler
    public void rightClickBlockCanceled(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (persistentData.isCustomItemStack(e.getItem())) {
                if (containCanceledKey(e.getItem().getItemMeta().getPersistentDataContainer())) {
                    e.setUseItemInHand(Event.Result.DENY);
                    e.getPlayer().updateInventory();
                }
            }
        }
    }
}