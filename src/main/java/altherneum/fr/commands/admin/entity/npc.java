package altherneum.fr.commands.admin.entity;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import altherneum.fr.commands.api.isAdmin;

import java.util.ArrayList;
import java.util.List;

public class npc implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                if (args.length >= 4) {
                    String nom = "§6{§fNPC§6} §6";
                    for (int i = 3; i < args.length; i++) {
                        nom = nom.concat(args[i]);
                        nom = nom.concat(" ");
                    }
                    SpawnNPC(((Player) sender).getLocation(), nom, Boolean.valueOf(args[0]),
                            Villager.Profession.valueOf(args[1]), Villager.Type.valueOf(args[2]));
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
            for (Villager.Profession profession : Villager.Profession.values()) {
                argsToReturn.add(profession.toString());
            }
        }
        if (args.length == 3) {
            for (Villager.Type type : Villager.Type.values()) {
                argsToReturn.add(type.toString());
            }
        }
        if (args.length == 4) {
            argsToReturn.addAll(NPCName());
        }
        return argsToReturn;
    }

    public void SpawnNPC(Location location, String nom, boolean IA, Villager.Profession profession,
            Villager.Type type) {
        Villager npc = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
        if (!IA) {
            npc.setAI(false);
            npc.setSilent(true);
        }
        npc.setVillagerType(type);
        npc.setProfession(profession);
        npc.setCanPickupItems(false);
        npc.setCustomNameVisible(true);
        npc.setInvulnerable(true);
        npc.setCustomName(nom);
    }

    public static ArrayList<String> NPCName() {
        ArrayList<String> argsToReturn = new ArrayList<>();
        argsToReturn.add("Commerçant");
        argsToReturn.add("Commerçant Illégal");
        argsToReturn.add("Quêtes");
        argsToReturn.add("Guide");
        argsToReturn.add("Île");
        argsToReturn.add("???");
        argsToReturn.add("Rainhard");
        argsToReturn.add("Léon");
        argsToReturn.add("Mark");
        argsToReturn.add("Harold");
        return argsToReturn;
    }
}