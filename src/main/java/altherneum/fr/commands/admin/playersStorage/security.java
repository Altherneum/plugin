package altherneum.fr.commands.admin.playersStorage;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import altherneum.fr.commands.api.isAdmin;
import altherneum.fr.system.getDataStorage;

import java.util.ArrayList;
import java.util.List;

public class security implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                if (args.length == 4 || args.length == 3) {
                    if (getDataStorage.playerExistForBonus(sender, Bukkit.getOfflinePlayer(args[0]))) {
                        if (args[1].equalsIgnoreCase("add") && args.length == 4) {
                            altherneum.fr.system.security.addSecurity(Bukkit.getOfflinePlayer(args[0]),
                                    altherneum.fr.system.security.SecurityList.valueOf(args[2]), Integer.valueOf(args[3]));
                            return true;
                        } else if (args[1].equalsIgnoreCase("remove") && args.length == 3) {
                            altherneum.fr.system.security.removeSecurity(Bukkit.getOfflinePlayer(args[0]),
                                    altherneum.fr.system.security.SecurityList.valueOf(args[2]));
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
            for (altherneum.fr.system.security.SecurityList security : altherneum.fr.system.security.SecurityList.values()) {
                argsToReturn.add(security.name());
            }
        } else if (args.length == 4) {
            argsToReturn.add("1");
            argsToReturn.add("3");
            argsToReturn.add("7");
            argsToReturn.add("14");
            argsToReturn.add("30");
            argsToReturn.add("60");
            argsToReturn.add("999");
        }
        return argsToReturn;
    }
}