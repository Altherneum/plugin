package altherneum.fr.menu.api;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
            .addString(modelName) // This string matches the "when" value in your resource pack
            .build());

        // 3. (Optional) Set a custom name if desired
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§6" + getTitle(modelName));
            item.setItemMeta(meta);
        }

        // 4. Give the item to the player
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
        ak47, shotgun
    }

    public static Material getMaterial(String modelName){
        switch (modelName) {
            case "ak47":
                return Material.GOLDEN_SWORD;
            case "shotgun":
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
            default:
                return "";
        }
    }

    public static String getLore(String modelName){
        switch (modelName) {
            case "ak47":
                return "AK-47";
            case "shotgun":
                return "ShotGun";
            default:
                return "";
        }
    }
}
