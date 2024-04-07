package altherneum.fr.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

public class help implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                lang.languages lang = playerLang.getPlayerLang(((Player) sender).getPlayer());
                sender.sendMessage(textTranslation.helpMSG(lang));
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }
}