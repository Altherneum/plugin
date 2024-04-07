package altherneum.fr.commands.admin.worldsRelated;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import altherneum.fr.commands.api.isAdmin;
import altherneum.fr.world.api.structure;

import java.util.ArrayList;
import java.util.List;

public class structureSave implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                if (sender instanceof Player) {
                    if (args.length == 7) {
                        structure.saveStructure(Integer.valueOf(args[0]), Integer.valueOf(args[3]),
                                Integer.valueOf(args[1]), Integer.valueOf(args[4]), Integer.valueOf(args[2]),
                                Integer.valueOf(args[5]), ((Player) sender).getWorld(), args[6]);
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
        if (args.length >= 1 && args.length <= 6) {
            argsToReturn.add("0");
            argsToReturn.add("50");
            argsToReturn.add("100");
            argsToReturn.add("250");
            argsToReturn.add("256");
            argsToReturn.add("500");
            argsToReturn.add("-50");
            argsToReturn.add("-100");
            argsToReturn.add("-250");
            argsToReturn.add("-500");
        }
        if (args.length == 7) {
            argsToReturn.add("nom");
            argsToReturn.addAll(structure.Structures());
        }
        return argsToReturn;
    }
}