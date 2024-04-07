package altherneum.fr.player;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import altherneum.fr.system.tags;

import java.io.IOException;

public class anvilUnlimited implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void anvilMaxStack(PrepareAnvilEvent e) throws IOException {
        if ((e.getView().getPlayer() instanceof Player p)
                && tags.hasTags((OfflinePlayer) e.getView().getPlayer(), tags.TagsList.Admin)) {
            final ItemStack leftItem = e.getInventory().getItem(0);
            final ItemStack rightItem = e.getInventory().getItem(1);
            if (leftItem != null && rightItem != null) {
                e.getInventory().setMaximumRepairCost(Integer.MAX_VALUE);
                if (e.getInventory().getRepairCost() >= 39) {
                    e.getInventory().setRepairCost(39);
                    e.getView().setProperty(InventoryView.Property.REPAIR_COST, e.getInventory().getRepairCost());
                    p.updateInventory();
                }
                ItemStack itemStackResult = getMergeResult(leftItem, rightItem);

                ItemMeta itemMeta = itemStackResult.getItemMeta();
                itemMeta.setDisplayName(e.getInventory().getRenameText());
                itemStackResult.setItemMeta(itemMeta);

                if (e.getInventory().getRepairCost() == 0) {
                    e.getInventory().setRepairCost(39);
                    e.getView().setProperty(InventoryView.Property.REPAIR_COST, e.getInventory().getRepairCost());
                }

                e.setResult(itemStackResult);
            }
        }
    }

    private ItemStack getMergeResult(ItemStack leftItem, ItemStack rightItem) {
        ItemStack ItemStackResult = new ItemStack(Material.AIR);
        if (leftItem.getType() == Material.ENCHANTED_BOOK && rightItem.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta e1 = (EnchantmentStorageMeta) leftItem.getItemMeta();
            EnchantmentStorageMeta e2 = (EnchantmentStorageMeta) rightItem.getItemMeta();
            ItemStackResult.setType(Material.ENCHANTED_BOOK);
            EnchantmentStorageMeta e3 = (EnchantmentStorageMeta) ItemStackResult.getItemMeta();
            e1.getStoredEnchants().forEach((enchant, level) -> {
                if (e2.getStoredEnchantLevel(enchant) < e1.getStoredEnchantLevel(enchant)) {
                    e3.addStoredEnchant(enchant, level, true);
                    ItemStackResult.setItemMeta(e3);
                } else if (e1.getStoredEnchantLevel(enchant) == e2.getStoredEnchantLevel(enchant)
                        && enchant.getMaxLevel() != 1) {
                    e3.addStoredEnchant(enchant, level + 1, true);
                    ItemStackResult.setItemMeta(e3);
                }
            });
            e2.getStoredEnchants().forEach((enchant, level) -> {
                if (e1.getStoredEnchantLevel(enchant) < e2.getStoredEnchantLevel(enchant)) {
                    e3.addStoredEnchant(enchant, level, true);
                    ItemStackResult.setItemMeta(e3);
                } else if (e1.getStoredEnchantLevel(enchant) == e2.getStoredEnchantLevel(enchant)
                        && enchant.getMaxLevel() != 1) {
                    e3.addStoredEnchant(enchant, level + 1, true);
                    ItemStackResult.setItemMeta(e3);
                }
            });
        } else if (leftItem.getType() != Material.ENCHANTED_BOOK && rightItem.getType() == Material.ENCHANTED_BOOK) {
            ItemStackResult.setType(leftItem.getType());
            EnchantmentStorageMeta e1 = (EnchantmentStorageMeta) rightItem.getItemMeta();
            e1.getStoredEnchants().forEach((enchant, level) -> {
                if (leftItem.getEnchantmentLevel(enchant) < e1.getStoredEnchantLevel(enchant)) {
                    ItemStackResult.addUnsafeEnchantment(enchant, level);
                } else if (e1.getStoredEnchantLevel(enchant) == leftItem.getEnchantmentLevel(enchant)
                        && enchant.getMaxLevel() != 1) {
                    ItemStackResult.addUnsafeEnchantment(enchant, level + 1);
                }
            });
            leftItem.getEnchantments().forEach((enchant, level) -> {
                if (e1.getStoredEnchantLevel(enchant) < leftItem.getEnchantmentLevel(enchant)) {
                    ItemStackResult.addUnsafeEnchantment(enchant, level);
                } else if (e1.getStoredEnchantLevel(enchant) == leftItem.getEnchantmentLevel(enchant)
                        && enchant.getMaxLevel() != 1) {
                    ItemStackResult.addUnsafeEnchantment(enchant, level + 1);
                }
            });
        } else if (leftItem.getType().equals(rightItem.getType())) {
            ItemStackResult.setType(leftItem.getType());
            leftItem.getEnchantments().forEach((enchant, level) -> {
                if (rightItem.getEnchantmentLevel(enchant) < leftItem.getEnchantmentLevel(enchant)) {
                    ItemStackResult.addUnsafeEnchantment(enchant, level);
                } else if (rightItem.getEnchantmentLevel(enchant) == leftItem.getEnchantmentLevel(enchant)
                        && enchant.getMaxLevel() != 1) {
                    ItemStackResult.addUnsafeEnchantment(enchant, level + 1);
                }
            });
            rightItem.getEnchantments().forEach((enchant, level) -> {
                if (leftItem.getEnchantmentLevel(enchant) < rightItem.getEnchantmentLevel(enchant)) {
                    ItemStackResult.addUnsafeEnchantment(enchant, level);
                } else if (rightItem.getEnchantmentLevel(enchant) == leftItem.getEnchantmentLevel(enchant)
                        && enchant.getMaxLevel() != 1) {
                    ItemStackResult.addUnsafeEnchantment(enchant, level + 1);
                }
            });
        }
        return ItemStackResult;
    }
}