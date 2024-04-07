package altherneum.fr.chat;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import altherneum.fr.system.dateAPI;
import altherneum.fr.system.getDataStorage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class chatLogger {
    public static File chatFile(OfflinePlayer offlinePlayer) throws IOException {
        File file = getDataStorage.chatFile(offlinePlayer);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static FileConfiguration playerFileConfiguration(OfflinePlayer offlinePlayer) throws IOException {
        return YamlConfiguration.loadConfiguration(chatFile(offlinePlayer));
    }

    @SuppressWarnings("deprecation")
    public static void LogData(Player player, String string) throws IOException {
        FileConfiguration fileConfiguration = playerFileConfiguration(player);
        List<String> Messages = fileConfiguration.getStringList(
                dateAPI.now().getYear() + 1900 + "." + (dateAPI.now().getMonth() + 1) + "." + dateAPI.now().getDate());
        Messages.add(dateAPI.now().getHours() + ":" + dateAPI.now().getMinutes() + ":" + dateAPI.now().getSeconds()
                + " : " + string);
        fileConfiguration.set(
                dateAPI.now().getYear() + 1900 + "." + (dateAPI.now().getMonth() + 1) + "." + dateAPI.now().getDate(),
                Messages);
        fileConfiguration.save(chatFile(player));
    }
}