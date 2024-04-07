package altherneum.fr.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import altherneum.fr.commands.api.isAdmin;
import altherneum.fr.system.getDataStorage;

import java.util.ArrayList;
import java.util.List;

public class removePlayerAttribute implements TabCompleter, CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                if (args.length == 1 && getDataStorage.playerExistForBonus(sender, Bukkit.getOfflinePlayer(args[0]))) {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
                    if (offlinePlayer.isOnline()) {
                        Player player = offlinePlayer.getPlayer();
                        for (Attribute a : Attribute.values()) {
                            if (player.getAttribute(a) != null) {
                                for (AttributeModifier am : player.getAttribute(a).getModifiers()) {
                                    player.getAttribute(a).removeModifier(am);
                                }
                            }
                        }
                        return true;
                    }
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> argsToReturn = new ArrayList<String>();
        if (args.length == 1) {
            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                argsToReturn.add(offlinePlayer.getName());
            }
        }
        return argsToReturn;
    }
}