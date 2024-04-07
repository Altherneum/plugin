package altherneum.fr.chat;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import altherneum.fr.system.getDataStorage;
import altherneum.fr.system.security;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import javax.security.auth.login.LoginException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class playerChat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e)
            throws IOException, ParseException, LoginException, InterruptedException {
        TextComponent message = new TextComponent();
        message.setText("§r §7>>§r " + e.getMessage());
        chatLogger.LogData(e.getPlayer(), e.getMessage());
        if (security.isObsoletSecurity(e.getPlayer(), security.SecurityList.Mute)
                && security.isObsoletSecurity(e.getPlayer(), security.SecurityList.Swear)) {
            for (Player receiver : Bukkit.getOnlinePlayers()) {
                if (!getDataStorage.playerFileConfiguration(receiver).getBoolean("chatSetting")) {
                    receiver.getPlayer().spigot().sendMessage(playerTextHover.getHover(e.getPlayer(), receiver),
                            message);
                }
            }
            Bukkit.getServer().getLogger()
                    .info(e.getPlayer().getUniqueId() + " : " + e.getPlayer().getName() + " : " + e.getMessage());
        } else {
            lang.languages lang = playerLang.getPlayerLang(e.getPlayer());
            e.getPlayer().sendMessage(textTranslation.mute(lang));
            // send Bonus & date until remove
        }
        e.setCancelled(true);
    }

    public static void toggleChatSetting(OfflinePlayer offlinePlayer) throws IOException {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (getDataStorage.playerFileConfiguration(offlinePlayer).getBoolean("chatSetting")) {
            fileConfiguration.set("chatSetting", null);
        } else {
            fileConfiguration.set("chatSetting", true);
        }
        fileConfiguration.save(file);
    }
}