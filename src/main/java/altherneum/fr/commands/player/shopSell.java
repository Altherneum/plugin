package altherneum.fr.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import altherneum.fr.menu.shop.classique.shopSellAll;

public class shopSell implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                shopSellAll.sellAll((Player) sender);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }
}
