package altherneum.fr.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import altherneum.fr.world.api.teleportation;

public class spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                teleportation.Teleport(((Player) sender).getPlayer(), "Spawn", false);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}