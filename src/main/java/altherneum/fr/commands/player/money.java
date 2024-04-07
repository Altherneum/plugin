package altherneum.fr.commands.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import altherneum.fr.system.getDataStorage;
import altherneum.fr.system.gold;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

public class money implements TabCompleter, CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                lang.languages lang = playerLang.getPlayerLang((Player) sender);
                if (args.length == 0) {
                    sender.sendMessage(textTranslation.remainingGold(lang, gold.GetGoldFormat((Player) sender)));
                    return true;
                } else if (args.length == 1) {
                    if (getDataStorage.playerExistForBonus(sender, Bukkit.getOfflinePlayer(args[0]))) {
                        sender.sendMessage(textTranslation.SayGold(lang, Bukkit.getOfflinePlayer(args[0]),  gold.GetGoldFormat(Bukkit.getOfflinePlayer(args[0]))));
                        return true;
                    }
                }
                return false;
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> argsToReturn = new ArrayList<String>();
        if (args.length == 1) {
            argsToReturn.add(sender.getName());
            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                argsToReturn.add(offlinePlayer.getName());
            }
        }
        return argsToReturn;
    }
}
