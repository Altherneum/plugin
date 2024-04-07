package altherneum.fr.commands.admin.playersStorage;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import altherneum.fr.commands.api.isAdmin;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.system.tags;

import java.util.ArrayList;
import java.util.List;

public class tag implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                if (args.length == 3) {
                    if (getDataStorage.playerExistForBonus(sender, Bukkit.getOfflinePlayer(args[0]))) {
                        if (args[1].equalsIgnoreCase("add")) {
                            tags.addTags(Bukkit.getOfflinePlayer(args[0]), tags.TagsList.valueOf(args[2]));
                            return true;
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            tags.removeTags(Bukkit.getOfflinePlayer(args[0]), tags.TagsList.valueOf(args[2]));
                            return true;
                        }
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
        } else if (args.length == 2) {
            argsToReturn.add("Add");
            argsToReturn.add("Remove");
        } else if (args.length == 3) {
            for (altherneum.fr.system.tags.TagsList tags : tags.TagsList.values()) {
                argsToReturn.add(tags.name());
            }
        }
        return argsToReturn;
    }
}