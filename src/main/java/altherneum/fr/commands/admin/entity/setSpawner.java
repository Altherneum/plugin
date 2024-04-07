package altherneum.fr.commands.admin.entity;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import altherneum.fr.commands.api.isAdmin;

import java.util.ArrayList;
import java.util.List;

public class setSpawner implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                if (args.length == 7) {
                    Block block = ((Player) sender).getTargetBlockExact(10);
                    block.setType(Material.SPAWNER);
                    CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();
                    creatureSpawner.setSpawnedType(EntityType.valueOf(args[0]));
                    creatureSpawner.setSpawnCount(Integer.valueOf(args[1]));
                    creatureSpawner.setMaxNearbyEntities(Integer.valueOf(args[2]));
                    creatureSpawner.setRequiredPlayerRange(Integer.valueOf(args[3]));
                    creatureSpawner.setSpawnRange(Integer.valueOf(args[4]));
                    creatureSpawner.setMinSpawnDelay(Integer.valueOf(args[5]) * 20);
                    creatureSpawner.setMaxSpawnDelay(Integer.valueOf(args[6]) * 20);
                    block.setBlockData(creatureSpawner.getBlockData());
                    creatureSpawner.update(true);
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
        List<String> argsToReturn = new ArrayList<String>();
        if (args.length == 1) {
            for (EntityType entityType : EntityType.values()) {
                argsToReturn.add(entityType.name());
            }
        }
        if (args.length == 2 || args.length == 3) {
            argsToReturn.add("1");
            argsToReturn.add("3");
            argsToReturn.add("5");
            argsToReturn.add("10");
        }
        if (args.length == 4 || args.length == 5) {
            argsToReturn.add("5");
            argsToReturn.add("10");
            argsToReturn.add("15");
            argsToReturn.add("20");
            argsToReturn.add("30");
            argsToReturn.add("50");
            argsToReturn.add("75");
            argsToReturn.add("100");
        }
        if (args.length == 6 || args.length == 7) {
            argsToReturn.add("10");
            argsToReturn.add("40");
        }
        return argsToReturn;
    }
}