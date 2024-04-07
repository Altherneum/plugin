package altherneum.fr.menu.api;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class customInventory implements InventoryHolder {
    private final CustomInventoryType inventoryType;

    private final Inventory inventory;

    public customInventory(CustomInventoryType CustomInventoryType, int size, String title) {
        this.inventory = Bukkit.createInventory(this, size, title);
        this.inventoryType = CustomInventoryType;
    }

    public customInventory(CustomInventoryType CustomInventoryType, InventoryType inventoryType, String title) {
        this.inventory = Bukkit.createInventory(this, inventoryType, title);
        this.inventoryType = CustomInventoryType;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public CustomInventoryType getInventoryType() {
        return this.inventoryType;
    }

    public enum CustomInventoryType {
        SKYBLOCK, MENU, VOYAGE, SHOP, PROFIL, SETTINGS
    }
}