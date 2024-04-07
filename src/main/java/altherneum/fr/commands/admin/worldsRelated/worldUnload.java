package altherneum.fr.commands.admin.worldsRelated;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import altherneum.fr.commands.api.isAdmin;
import altherneum.fr.world.api.worldManager;

import java.util.ArrayList;
import java.util.List;

public class worldUnload implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                if (args.length == 1) {
                    worldManager.UnloadWorld(args[0], true);
                } else if (args.length == 2) {
                    worldManager.UnloadWorld(args[0], Boolean.valueOf(args[1]));
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
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 1) {
            for (World world : Bukkit.getWorlds()) {
                list.add(world.getName());
            }
        }
        if (args.length == 2) {
            list.add(Boolean.TRUE.toString());
            list.add(Boolean.FALSE.toString());
        }
        return list;
    }
}