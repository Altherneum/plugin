package altherneum.fr.menu.shop.specials.bonus;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import altherneum.fr.system.dateAPI;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class bonus {
    public enum BonusList {
        JoinQuitMessage, AuraTP, VIP, VIPPlus, PKAura, Repair, Replant, Fly, Compact, Weather, Tree, Hat, EnderChest
    }

    public static File file(OfflinePlayer offlinePlayer) {
        return getDataStorage.bonusFile(offlinePlayer);
    }

    public static FileConfiguration fileConfiguration(OfflinePlayer offlinePlayer) throws IOException {
        File file = file(offlinePlayer);
        if (!file.exists()) {
            file.createNewFile();
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void addBonus(OfflinePlayer offlinePlayer, BonusList BonusString, int Day) throws IOException {
        FileConfiguration fileConfigurationPlayer = fileConfiguration(offlinePlayer);
        if (!hasBonus(offlinePlayer, BonusString)) {
            List<String> BonusList = GetBonus(offlinePlayer);
            BonusList.add(BonusString.name());
            fileConfigurationPlayer.set("List", BonusList);
            fileConfigurationPlayer.set(BonusString.name() + ".day", Day);
            fileConfigurationPlayer.set(BonusString.name() + ".date", dateAPI.DateFormated(dateAPI.now()));
        } else {
            int ActualDay = fileConfigurationPlayer.getInt(BonusString.name() + ".day");
            fileConfigurationPlayer.set(BonusString.name() + ".day", (Day + ActualDay));
        }
        fileConfigurationPlayer.save(file(offlinePlayer));

        if (offlinePlayer.isOnline()) {
            lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
            offlinePlayer.getPlayer().sendMessage(textTranslation.addBonus(lang, BonusString.name(), Day));
        }
    }

    public static Date dateBonus(OfflinePlayer offlinePlayer, BonusList BonusString) throws ParseException {
        return dateAPI.ReturnDate(file(offlinePlayer), BonusString.name() + ".date");
    }

    public static int dayBonus(OfflinePlayer offlinePlayer, BonusList BonusString) throws IOException {
        return fileConfiguration(offlinePlayer).getInt(BonusString.name() + ".day");
    }

    public static List<String> GetBonus(OfflinePlayer offlinePlayer) throws IOException {
        return fileConfiguration(offlinePlayer).getStringList("List");
    }

    public static boolean hasBonus(OfflinePlayer offlinePlayer, BonusList BonusString) throws IOException {
        return GetBonus(offlinePlayer).contains(BonusString.name());
    }

    public static boolean isObsoletBonus(OfflinePlayer offlinePlayer, BonusList bonusList)
            throws IOException, ParseException {
        if (hasBonus(offlinePlayer, bonusList)) {
            Date dateUntilRemove = dateAPI.ReturnDateWithXDay(dateBonus(offlinePlayer, bonusList),
                    dayBonus(offlinePlayer, bonusList));
            if (dateUntilRemove.after(dateAPI.now())) {
                return false;
            }
            removeBonus(offlinePlayer, bonusList);
        }
        return true;
    }

    public static void removeBonus(OfflinePlayer offlinePlayer, BonusList BonusString) throws IOException {
        if (hasBonus(offlinePlayer, BonusString)) {
            List<String> BonusList = GetBonus(offlinePlayer);
            BonusList.remove(BonusString.name());
            FileConfiguration fileConfigurationPlayer = fileConfiguration(offlinePlayer);
            if (BonusList.isEmpty()) {
                file(offlinePlayer).delete();
            } else {
                fileConfigurationPlayer.set("List", BonusList);
                fileConfigurationPlayer.set(BonusString.name(), null);
                fileConfigurationPlayer.set(BonusString.name() + ".day", null);
                fileConfigurationPlayer.set(BonusString.name() + ".date", null);
                fileConfigurationPlayer.save(file(offlinePlayer));
            }
            if (offlinePlayer.isOnline()) {
                lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
                offlinePlayer.getPlayer().sendMessage(textTranslation.removeBonus(lang, BonusString.name()));
            }
        }
    }
}