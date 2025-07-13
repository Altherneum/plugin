package altherneum.fr.enchantements;

/*
import java.util.Set;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.bukkit.NamespacedKey;

import io.papermc.paper.enchantments.EnchantmentRarity;
import io.papermc.paper.registry.set.RegistryKeySet;
import net.kyori.adventure.text.Component;

public class instantmine /*extends Enchantment implements Listener*/ {

    /*  
    @EventHandler
    public void onEnchantUse(BlockBreakEvent e) {
        ItemStack it = e.getPlayer().getInventory().getItemInMainHand();
        if (it.getEnchantments().containsKey(this)) {
            e.setDropItems(false);
            for (ItemStack is : e.getBlock().getDrops(it, e.getPlayer())) {
                e.getPlayer().getInventory().addItem(is);
            }
    
            e.getPlayer().giveExp(e.getExpToDrop());
            e.setExpToDrop(0);
        }
    }
    
    public instantmine(String namespace) {
        super(NamespacedKey.minecraft(namespace));
    }
    
    
    //  public instantmine(NamespacedKey key) {
    //  super(key);
    //  }
     

    @Override
    public NamespacedKey getKey() {
        return super.getKey();
    }

    @Override
    public String getName() {
        return "instantmine";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return true;
    }

    @Override
    public String translationKey() {
        throw new UnsupportedOperationException("Unimplemented method 'translationKey'");
    }

    @Override
    public Component displayName(int arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'displayName'");
    }

    @Override
    public Set<EquipmentSlot> getActiveSlots() {
        throw new UnsupportedOperationException("Unimplemented method 'getActiveSlots'");
    }

    @Override
    public float getDamageIncrease(int arg0, EntityCategory arg1) {
        return 0;
    }

    @Override
    public EnchantmentRarity getRarity() {
        return EnchantmentRarity.VERY_RARE;
    }

    @Override
    public boolean isDiscoverable() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public int getMaxModifiedCost(int i) {
        return 0;
    }

    @Override
    public int getMinModifiedCost(int i) {
        return 0;
    }

    @Override
    public String getTranslationKey() {
        throw new UnsupportedOperationException("Unimplemented method 'getTranslationKey'");
    }

    @Override
    public Component description() {
        throw new UnsupportedOperationException("Unimplemented method 'description'");
    }

    @Override
    public Set<EquipmentSlotGroup> getActiveSlotGroups() {
        throw new UnsupportedOperationException("Unimplemented method 'getActiveSlotGroups'");
    }

    @Override
    public int getAnvilCost() {
        throw new UnsupportedOperationException("Unimplemented method 'getAnvilCost'");
    }

    @Override
    public float getDamageIncrease(int arg0, EntityType arg1) {
        throw new UnsupportedOperationException("Unimplemented method 'getDamageIncrease'");
    }

    @Override
    public RegistryKeySet<Enchantment> getExclusiveWith() {
        throw new UnsupportedOperationException("Unimplemented method 'getExclusiveWith'");
    }

    @Override
    public  RegistryKeySet<ItemType> getPrimaryItems() {
        throw new UnsupportedOperationException("Unimplemented method 'getPrimaryItems'");
    }

    @Override
    public RegistryKeySet<ItemType> getSupportedItems() {
        throw new UnsupportedOperationException("Unimplemented method 'getSupportedItems'");
    }

    @Override
    public int getWeight() {
        throw new UnsupportedOperationException("Unimplemented method 'getWeight'");
    }
    */
}
