package altherneum.fr.commands.api;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

import altherneum.fr.system.tags;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.IOException;

public class isAdmin {
    public static boolean isAdmin(OfflinePlayer offlinePlayer) throws IOException {
        return offlinePlayer.isOp() && tags.hasTags(offlinePlayer, tags.TagsList.Admin);
    }

    public static boolean isAdmin(CommandSender sender) throws IOException {
        if (sender instanceof Player && isAdmin((OfflinePlayer) sender)) {
            return true;
        } else if (sender instanceof ConsoleCommandSender || sender instanceof RemoteConsoleCommandSender) {
            return true;
        } else {
            if (sender instanceof Player) {
                lang.languages lang = playerLang.getPlayerLang((OfflinePlayer) sender);
                sender.sendMessage(textTranslation.commandAdmin(lang));
            }
        }
        return false;
    }
}