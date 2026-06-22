package altherneum.fr.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import altherneum.fr.entity.clickNPC;
import altherneum.fr.system.ServerBootFile;

public class bed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Survie)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.RPG)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.Anarchie)) {
                    clickNPC.tpBed((Player) sender);
                    return true;
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}