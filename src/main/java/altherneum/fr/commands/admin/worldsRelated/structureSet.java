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

public class structureSet implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                if (sender instanceof Player) {
                    if (args.length == 1) {
                        structure.setStructure(args[0], ((Player) sender).getWorld().getName());
                        return true;
                    } else if (args.length == 4) {
                        structure.setStructure(args[0], ((Player) sender).getWorld().getName(),
                                Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                        return true;
                    } else if (args.length == 5) {
                        structure.setStructure(args[0], ((Player) sender).getWorld().getName(),
                                Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),
                                Integer.parseInt(args[4]));
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
            argsToReturn.add("nom");
            argsToReturn.addAll(structure.Structures());
        }
        if (args.length >= 2 && args.length <= 4) {
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
        if (args.length == 5) {
            argsToReturn.add("0");
            argsToReturn.add("1");
            argsToReturn.add("2");
            argsToReturn.add("5");
            argsToReturn.add("10");
            argsToReturn.add("20");
        }
        return argsToReturn;
    }
}