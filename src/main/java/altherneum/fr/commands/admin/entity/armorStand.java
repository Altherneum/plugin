package altherneum.fr.commands.admin.entity;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import altherneum.fr.commands.api.isAdmin;

public class armorStand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                String nom = "";
                for (String s : args) {
                    nom = nom.concat(s);
                    nom = nom.concat(" ");
                }
                altherneum.fr.entity.armorStand.Summon(((Player) sender).getLocation(), nom);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}