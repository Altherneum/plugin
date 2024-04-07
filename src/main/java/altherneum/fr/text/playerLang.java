package altherneum.fr.text;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import altherneum.fr.system.getDataStorage;
import altherneum.fr.text.lang.languages;

import java.io.File;
import java.io.IOException;

public class playerLang {
    public static lang.languages getPlayerLang(OfflinePlayer offlinePlayer) throws IOException {
        return lang.languages.values()[getDataStorage.playerFileConfiguration(offlinePlayer).getInt("lang")];
    }

    public static void setPlayerLang(OfflinePlayer offlinePlayer, int i) throws IOException {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (i == 0) {
            fileConfiguration.set("lang", null);
        } else {
            fileConfiguration.set("lang", i);
        }
        fileConfiguration.save(file);
    }

    public static void SwapLang(OfflinePlayer offlinePlayer) throws IOException {
        if (getPlayerLang(offlinePlayer).equals(languages.fr)) {
            setPlayerLang(offlinePlayer, languages.en.ordinal());
        } else if (getPlayerLang(offlinePlayer).equals(languages.en)) {
            setPlayerLang(offlinePlayer, languages.fr.ordinal());
        }
    }
}