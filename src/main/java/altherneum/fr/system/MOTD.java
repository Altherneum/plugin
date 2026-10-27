package altherneum.fr.system;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import altherneum.fr.text.textTranslation;

public class MOTD implements Listener {
    @EventHandler
    public void onServerListPing(ServerListPingEvent e) {
        e.setMotd(MOTD());
        e.setMaxPlayers(Bukkit.getMaxPlayers());
        Bukkit.getServer().setMaxPlayers(Bukkit.getMaxPlayers());
    }

    public static String MOTD() {
        String BootText = "§6[ §2§l" + textTranslation.ServerName() + textTranslation.ServerTLD() + " §6] §6";
        String BottomText = " §r!\n§r" + textTranslation.ServerName() + textTranslation.ServerTLD() + "/§2discord";
        return BootText + ServerBootFile.getServerType().toString() + BottomText;
    }
}