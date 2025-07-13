package altherneum.fr.commands.api;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

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
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<String>();
        return list;
    }
}