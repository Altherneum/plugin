package altherneum.fr.commands.api;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import org.bukkit.inventory.ItemStack;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.CustomModelData;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class test implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                /*
                
                Player player = (Player) sender;
                Plugin plugin = JavaPlugin.getPlugin(main.class);
                instantmine instantmine = new instantmine("instantmine");
                player.getItemInHand().addUnsafeEnchantment(instantmine, 0);
                player.sendMessage("OK");
                for (Enchantment enchant : player.getItemInHand().getEnchantments().keySet()) {
                    player.sendMessage("enchant : " + enchant.getName());
                }
                
                */

                Player player = (Player) sender;

                // 1. Create the base item (e.g., an Iron Sword)
                ItemStack item = new ItemStack(Material.GOLDEN_SWORD);

                // 2. Modify the Data Components
                item.setData(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelData.customModelData()
                    .addString("ak47") // This string matches the "when" value in your resource pack
                    .build());

                // 3. (Optional) Set a custom name if desired
                ItemMeta meta = item.getItemMeta();
                if (meta != null) {
                    meta.setDisplayName("§6Custom texture test");
                    item.setItemMeta(meta);
                }

                // 4. Give the item to the player
                player.getInventory().addItem(item);

                // Usage Example:
                if (hasCustomModelString(player.getInventory().getItemInMainHand(), "ak47")) {
                    player.sendMessage("You are holding the custom sword!");
                }
                else{
                    player.sendMessage("Error, need debug wrong item in hand");
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasCustomModelString(ItemStack item, String targetString) {
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<String>();
        return list;
    }
}