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
import altherneum.fr.text.shopTranslation;

public class pay implements TabCompleter, CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                lang.languages lang = playerLang.getPlayerLang((Player) sender);
                if (args.length == 2) {
                    if (getDataStorage.playerExistForBonus(sender, Bukkit.getOfflinePlayer(args[0]))) {
                        if (gold.GetHasEnoughGold((Player) sender, Integer.valueOf(args[1]))
                                && Integer.valueOf(args[1]) >= 1) {
                            gold.RemoveGold((Player) sender, Integer.valueOf(args[1]));
                            gold.AddGold(Bukkit.getOfflinePlayer(args[0]), Integer.valueOf(args[1]));
/* message pour chaque player avec pseudo et stockage yml */
                            return true;
                        } else {
                            sender.sendMessage(shopTranslation.PasAssezArgent(lang));
                            return true;
                        }
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
            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                argsToReturn.add(offlinePlayer.getName());
            }
        }

        if (args.length == 2) {
            argsToReturn.add("1");
            argsToReturn.add("50");
            argsToReturn.add("100");
            argsToReturn.add("250");
            argsToReturn.add("256");
            argsToReturn.add("500");
            argsToReturn.add("1000");
            argsToReturn.add("2500");
            argsToReturn.add("5000");
            argsToReturn.add("10000");
            argsToReturn.add("50000");
            argsToReturn.add("100000");
        }
        return argsToReturn;
    }
}
