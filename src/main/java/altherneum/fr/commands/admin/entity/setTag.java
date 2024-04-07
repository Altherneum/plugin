package altherneum.fr.commands.admin.entity;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import altherneum.fr.commands.api.isAdmin;
import altherneum.fr.entity.entityPersistentData;

import java.util.ArrayList;
import java.util.List;

public class setTag implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                if (sender instanceof Player player && args.length == 1) {
                    Entity entity = (player.getTargetEntity(20));
                    if (entity != null) {
                        entityPersistentData.setPersistentDataEntity(entity,
                                entityPersistentData.customKey.valueOf(args[0]));
                        if (!entityPersistentData.isCustomEntity(entity)) {
                            entityPersistentData.setPersistentDataEntity(entity, entityPersistentData.customKey.custom);
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
        ArrayList<String> argsToReturn = new ArrayList<>();
        for (entityPersistentData.customKey customKey : entityPersistentData.customKey.values()) {
            argsToReturn.add(customKey.name());
        }
        return argsToReturn;
    }
}