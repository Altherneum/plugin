package altherneum.fr.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import altherneum.fr.menu.profil.profilMenu;
import altherneum.fr.system.getDataStorage;

import java.util.ArrayList;
import java.util.List;

public class profil implements TabCompleter, CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                if (getDataStorage.playerExistForBonus(sender, Bukkit.getOfflinePlayer(args[0]))) {
                    profilMenu.menuProfil(((Player) sender).getPlayer(), Bukkit.getOfflinePlayer(args[0]));
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> argsToReturn = new ArrayList<String>();
        if (args.length == 1) {
            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                argsToReturn.add(offlinePlayer.getName());
            }
        }
        return argsToReturn;
    }
}