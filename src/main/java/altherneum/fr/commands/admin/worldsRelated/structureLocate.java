package altherneum.fr.commands.admin.worldsRelated;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import altherneum.fr.commands.api.isAdmin;
import altherneum.fr.main.main;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.world.api.structure;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class structureLocate implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                Plugin plugin = JavaPlugin.getPlugin(main.class);
                File file = getDataStorage.structureFile(args[0]);
                if (file.exists()) {
                    FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
                    int blockCount = fileConfiguration.getInt("blockCount");
                    int i = 1;
                    while (i <= blockCount) {
                        fileConfiguration.set(i + ".location.x",
                                fileConfiguration.getInt(i + ".location.x") + Integer.valueOf(args[1]));
                        fileConfiguration.set(i + ".location.y",
                                fileConfiguration.getInt(i + ".location.y") + Integer.valueOf(args[2]));
                        fileConfiguration.set(i + ".location.z",
                                fileConfiguration.getInt(i + ".location.z") + Integer.valueOf(args[3]));
                        i++;
                    }
                    fileConfiguration.save(file);
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
            argsToReturn.add("nom");
            argsToReturn.addAll(structure.Structures());
        } else if (args.length >= 2 && args.length <= 4) {
            argsToReturn.add("-50");
            argsToReturn.add("-100");
            argsToReturn.add("-250");
            argsToReturn.add("-500");
            argsToReturn.add("0");
            argsToReturn.add("50");
            argsToReturn.add("100");
            argsToReturn.add("250");
            argsToReturn.add("256");
            argsToReturn.add("500");
        }
        return argsToReturn;
    }
}