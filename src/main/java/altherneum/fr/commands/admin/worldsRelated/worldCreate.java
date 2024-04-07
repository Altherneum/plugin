package altherneum.fr.commands.admin.worldsRelated;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import altherneum.fr.commands.api.isAdmin;
import altherneum.fr.world.api.teleportation;
import altherneum.fr.world.api.worldManager;

import java.util.ArrayList;
import java.util.List;

public class worldCreate implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                if (args.length == 1) {
                    World world = worldManager.Generate(args[0], false, World.Environment.NORMAL, WorldType.NORMAL,
                            true);
                    if (sender instanceof Player) {
                        teleportation.Teleport((Player) sender, world.getName(), false);
                    }
                    return true;
                } else if (args.length == 5) {
                    World world = worldManager.Generate(args[0], Boolean.valueOf(args[1]),
                            World.Environment.valueOf(args[2]), WorldType.valueOf(args[3]), Boolean.valueOf(args[4]));
                    if (sender instanceof Player) {
                        teleportation.Teleport((Player) sender, world.getName(), false);
                    }
                    return true;
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
        if (args.length == 3) {
            for (World.Environment worldEnvironment : World.Environment.values()) {
                list.add(worldEnvironment.toString());
            }
        }
        if (args.length == 4) {
            for (WorldType worldType : WorldType.values()) {
                list.add(worldType.toString());
            }
        }
        if (args.length == 5) {
            list.add(Boolean.TRUE.toString());
            list.add(Boolean.FALSE.toString());
        }
        return list;
    }
}