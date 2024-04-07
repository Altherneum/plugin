package altherneum.fr.menu.api;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

public class customInventoryClick implements Listener {
    @EventHandler
    public void playerClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null || e.getView().getTopInventory() == null) {
            return;
        }

        if (isCancelled(e.getClickedInventory())) {
            e.setCancelled(true);
        }

        if (e.isShiftClick() && isCancelled(e.getView().getTopInventory())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void playerDrag(InventoryDragEvent e) {
        for (int i : e.getRawSlots()) {
            if (e.getView().getInventory(i) != null && isCancelled(e.getView().getInventory(i))) {
                e.setCancelled(true);
                return;
            }
        }
    }

    public static boolean cancel(customInventory.CustomInventoryType customInventoryType) {
        /*
         * case LOBBY:
         * return false;
         */
        return true;
    }

    public static boolean isCustomMenu(Inventory inventory) {
        return inventory.getHolder() instanceof customInventory;
    }

    public static boolean isCancelled(Inventory inventory) {
        if (isCustomMenu(inventory)) {
            customInventory customInventory = (customInventory) inventory.getHolder();
            return cancel(customInventory.getInventoryType());
        }
        return false;
    }
}