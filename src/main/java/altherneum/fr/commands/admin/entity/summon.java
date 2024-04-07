package altherneum.fr.commands.admin.entity;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import altherneum.fr.commands.api.isAdmin;

import java.util.ArrayList;
import java.util.List;

public class summon implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                if (args.length >= 3) {
                    String nom = "§6{§fNPC§6} §6";
                    for (int i = 2; i < args.length; i++) {
                        nom = nom.concat(args[i]);
                        nom = nom.concat(" ");
                    }
                    SpawnNPC(((Player) sender).getLocation(), nom, Boolean.valueOf(args[0]),
                            EntityType.valueOf(args[1]));
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
            argsToReturn.add(Boolean.TRUE.toString());
            argsToReturn.add(Boolean.FALSE.toString());
        }
        if (args.length == 2) {
            for (EntityType entityType : EntityType.values()) {
                argsToReturn.add(entityType.toString());
            }
        }
        if (args.length == 3) {
            argsToReturn.addAll(npc.NPCName());
        }
        return argsToReturn;
    }

    public void SpawnNPC(Location location, String nom, boolean IA, EntityType entityType) {
        LivingEntity livingEntity = (LivingEntity) location.getWorld().spawnEntity(location, entityType);
        if (!IA) {
            livingEntity.setAI(false);
            livingEntity.setSilent(true);
        }
        livingEntity.setCanPickupItems(false);
        livingEntity.setCustomNameVisible(true);
        livingEntity.setInvulnerable(true);
        livingEntity.setCustomName(nom);
    }
}