package altherneum.fr.system;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class security {
    public enum SecurityList {
        Mute, Ban, Cheat, Swear, BanAnarchie, Warn
    }

    public static File file(OfflinePlayer offlinePlayer) {
        return getDataStorage.securityFile(offlinePlayer);
    }

    public static FileConfiguration fileConfiguration(OfflinePlayer offlinePlayer) throws IOException {
        File file = file(offlinePlayer);
        if (!file.exists()) {
            file.createNewFile();
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void addSecurity(OfflinePlayer offlinePlayer, SecurityList SecurityString, int Day)
            throws IOException {
        if (!hasSecurity(offlinePlayer, SecurityString)) {
            List<String> securitylist = GetSecurity(offlinePlayer);
            securitylist.add(SecurityString.name());
            FileConfiguration fileConfigurationPlayer = fileConfiguration(offlinePlayer);
            fileConfigurationPlayer.set("List", securitylist);
            fileConfigurationPlayer.set(SecurityString.name() + ".day", Day);
            fileConfigurationPlayer.set(SecurityString.name() + ".date", dateAPI.DateFormated(dateAPI.now()));
            fileConfigurationPlayer.save(file(offlinePlayer));
            if (offlinePlayer.isOnline()) {
                lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
                offlinePlayer.getPlayer().sendMessage(textTranslation.addSecurity(lang, SecurityString.name(), Day));
            }
            if (SecurityString.equals(SecurityList.Ban) || SecurityString.equals(SecurityList.Cheat)) {
                if (offlinePlayer.isOnline()) {
                    lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
                    offlinePlayer.getPlayer().kickPlayer(textTranslation.addBan(lang, Day));
                }
            }
            if (SecurityString.equals(SecurityList.Warn)) {
                if (offlinePlayer.isOnline()) {
                    lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
                    offlinePlayer.getPlayer().sendMessage(textTranslation.addWarn(lang));
                }
            }
            if (SecurityString.equals(SecurityList.BanAnarchie)) {
                if (offlinePlayer.isOnline()) {
                    lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
                    offlinePlayer.getPlayer().kickPlayer(textTranslation.addBan(lang, Day) + " -> Anarchie");
                }
            }
        } else {
            if (SecurityString.equals(SecurityList.Warn)) {
                addSecurity(offlinePlayer, SecurityList.Ban, 1);
                removeSecurity(offlinePlayer, SecurityList.Warn);
            }
        }
    }

    public static Date dateSecurity(OfflinePlayer offlinePlayer, SecurityList SecurityString)
            throws IOException, ParseException {
        return dateAPI.ReturnDate(file(offlinePlayer), SecurityString.name() + ".date");
    }

    public static int daySecurity(OfflinePlayer offlinePlayer, SecurityList SecurityString) throws IOException {
        return fileConfiguration(offlinePlayer).getInt(SecurityString.name() + ".day");
    }

    public static List<String> GetSecurity(OfflinePlayer offlinePlayer) throws IOException {
        return fileConfiguration(offlinePlayer).getStringList("List");
    }

    public static boolean hasSecurity(OfflinePlayer offlinePlayer, SecurityList SecurityString) throws IOException {
        return GetSecurity(offlinePlayer).contains(SecurityString.name());
    }

    public static boolean isObsoletSecurity(OfflinePlayer offlinePlayer, SecurityList securityList)
            throws IOException, ParseException {
        if (hasSecurity(offlinePlayer, securityList)) {
            Date dateUntilRemove = dateAPI.ReturnDateWithXDay(dateSecurity(offlinePlayer, securityList),
                    daySecurity(offlinePlayer, securityList));
            if (dateUntilRemove.after(dateAPI.now())) {
                return false;
            }
            removeSecurity(offlinePlayer, securityList);
        }
        return true;
    }

    public static void removeSecurity(OfflinePlayer offlinePlayer, SecurityList SecurityString) throws IOException {
        if (hasSecurity(offlinePlayer, SecurityString)) {
            List<String> SecurityList = GetSecurity(offlinePlayer);
            SecurityList.remove(SecurityString.name());
            FileConfiguration fileConfigurationPlayer = fileConfiguration(offlinePlayer);
            if (SecurityList.isEmpty()) {
                file(offlinePlayer).delete();
            } else {
                fileConfigurationPlayer.set("List", SecurityList);
                fileConfigurationPlayer.set(SecurityString.name(), null);
                fileConfigurationPlayer.set(SecurityString.name() + ".day", null);
                fileConfigurationPlayer.set(SecurityString.name() + ".date", null);
                fileConfigurationPlayer.save(file(offlinePlayer));
            }
            if (offlinePlayer.isOnline()) {
                lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
                offlinePlayer.getPlayer().sendMessage(textTranslation.removeSecurity(lang, SecurityString.name()));
            }
        }
    }
}