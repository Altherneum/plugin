package altherneum.fr.commands.admin.worldsRelated;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import altherneum.fr.commands.api.isAdmin;
import altherneum.fr.world.api.worldManager;

import java.util.ArrayList;
import java.util.List;

public class setSpawn implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                if (args.length == 0 && sender instanceof Player player) {
                    setWorldSpawn(player.getWorld().getName());
                    return true;
                } else if (args.length >= 1) {
                    if (Bukkit.getWorld(args[0]) == null) {
                        worldManager.Generate(args[0], false, World.Environment.NORMAL, WorldType.NORMAL, true);
                    }

                    if (args.length == 1) {
                        setWorldSpawn(args[0]);
                        return true;
                    } else if (args.length == 4) {
                        setWorldSpawn(args[0], Integer.valueOf(args[1]), Integer.valueOf(args[2]),
                                Integer.valueOf(args[3]));
                        return true;
                    } else if (args.length == 6) {
                        setWorldSpawn(args[0], Integer.valueOf(args[1]), Integer.valueOf(args[2]),
                                Integer.valueOf(args[3]), Integer.valueOf(args[4]), Integer.valueOf(args[5]));
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
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 1) {
            for (World world : Bukkit.getWorlds()) {
                list.add(world.getName());
            }
        }
        if (args.length >= 2 && args.length <= 5) {
            listPose(list, sender);
        }
        return list;
    }

    public List<String> listPose(List<String> list, CommandSender sender) {
        if (sender instanceof Player player) {
            list.add(String.valueOf((int) player.getLocation().getX()));
            list.add(String.valueOf((int) player.getLocation().getY()));
            list.add(String.valueOf((int) player.getLocation().getZ()));
            list.add(String.valueOf((int) player.getLocation().getYaw()));
            list.add(String.valueOf((int) player.getLocation().getPitch()));
        }
        list.add("-100");
        list.add("0");
        list.add("64");
        list.add("100");
        list.add("256");
        return list;
    }

    public static void setWorldSpawn(String worldName) {
        World world = Bukkit.getWorld(worldName);
        Location spawnLocation = new Location(world, 0, 64, 0, 0, 0);
        world.setSpawnLocation(spawnLocation);
    }

    public static void setWorldSpawn(String worldName, int x, int y, int z) {
        World world = Bukkit.getWorld(worldName);
        Location spawnLocation = new Location(world, x, y, z, 0, 0);
        world.setSpawnLocation(spawnLocation);
    }

    public static void setWorldSpawn(String worldName, int x, int y, int z, int yaw, int pitch) {
        World world = Bukkit.getWorld(worldName);
        Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
        world.setSpawnLocation(spawnLocation);
    }
}