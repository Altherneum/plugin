package altherneum.fr.menu.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.CustomModelData;

public class customModel {
    public static void test(Player player){
        giveItem(player, customKey.ak47.toString(), getMaterial(customKey.ak47.toString()));
        hasItemInHand(player,customKey.ak47.toString());
    }

    public static void giveItem(Player player, String modelName, Material material) {
        // 1. Create the base item (e.g., an Iron Sword)
        ItemStack item = new ItemStack(material);

        // 2. Modify the Data Components
        item.setData(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelData.customModelData()
            .addString(realModelName(modelName)) // This string matches the "when" value in your resource pack
            .build());

        // 3. (Optional) Set a custom name if desired
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§6" + getTitle(modelName));
            
            Plugin plugin = Bukkit.getPluginManager().getPlugin("plugin");
            NamespacedKey maxKey = new NamespacedKey(plugin, "max_durability");
            NamespacedKey currentKey = new NamespacedKey(plugin, "current_durability");
            meta.getPersistentDataContainer().set(maxKey, PersistentDataType.INTEGER, maxDurability(modelName));
            meta.getPersistentDataContainer().set(currentKey, PersistentDataType.INTEGER, maxDurability(modelName));

            item.setItemMeta(meta);
        }

        item.setLore(getLore(modelName));

        persistentData.setPersistentDataItemStack(item, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(item, persistentData.customKey.weapon);
        //item.setDurability(maxDurability(modelName));

        player.getInventory().addItem(item);

        hasItemInHand(player, modelName);
    }

    public static void hasItemInHand(Player player, String modelName){
        // Usage Example:
        if (hasCustomModelString(player.getInventory().getItemInMainHand(), modelName)) {
            player.sendMessage("You are holding the custom sword!");
        }
        else{
            player.sendMessage("Error, need debug wrong item in hand");
        }
    }

    public static boolean hasCustomModelString(ItemStack item, String targetString) {
        // 1. Check if the component exists first
        if (!item.hasData(DataComponentTypes.CUSTOM_MODEL_DATA)) {
            return false;
        }

        // 2. Retrieve the CustomModelData component
        CustomModelData cmd = item.getData(DataComponentTypes.CUSTOM_MODEL_DATA);


        if (cmd != null) {
            // Proceed to get strings
            List<String> modelDataStrings = cmd.strings();
            if (!modelDataStrings.isEmpty()) {
                String firstString = modelDataStrings.get(0);
                // Example: Check if the first string matches a specific value
                if (targetString.equals(firstString)) {
                    return true;
                }
            }   
        }
        return false;
    }

    public enum customKey {
        ak47, shotgun, revolver
    }

    public static Material getMaterial(String modelName){
        switch (modelName) {
            case "ak47":
                return Material.GOLDEN_SWORD;
            case "shotgun":
                return Material.GOLDEN_SWORD;
            case "revolver":
                return Material.GOLDEN_SWORD;
            default:
                return Material.AIR;
        }
    }

    public static String getTitle(String modelName){
        switch (modelName) {
            case "ak47":
                return "AK-47";
            case "shotgun":
                return "ShotGun";
            case "revolver":
                return "Revolver";
            default:
                return "";
        }
    }

    public static ArrayList<String> getLore(String modelName) {
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add("");
        switch (modelName) {
            case "ak47":
                Lore.add("§fune §7AK-47");
            case "shotgun":
                Lore.add("§fun §7fusil à pompe");
            case "revolver":
                Lore.add("§fun §7revolver");
            default:
                Lore.add("");
        }
        Lore.add("");
        return Lore;
    }

    public static int maxDurability(String modelName){
        switch (modelName) {
            case "ak47":
                return 30;
            case "shotgun":
                return 6;
            case "revolver":
                return 6;
            default:
                return 0;
        }
    }

    public static short coolDown(String modelName){ //tick
        switch (modelName) {
            case "ak47":
                return 10;
            case "shotgun":
                return 30;
            case "revolver":
                return 30;
            default:
                return 0;
        }
    }

        public static short reloadCoolDown(String modelName){ //tick
        switch (modelName) {
            case "ak47":
                return 45;
            case "shotgun":
                return 60;
            case "revolver":
                return 60;
            default:
                return 0;
        }
    }

    public static String realModelName(String modelName){
        switch (modelName) {
            case "ak47":
                return "ak47";
            case "shotgun":
                return "shotgun";
            case "revolver":
                return "revolver";
            default:
                return "";
        }
    }
}
