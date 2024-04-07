package altherneum.fr.system;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class gold {
    public static String FormatInt(int i) {
        if (i > 0) {
            double value = Double.valueOf(i);
            String suffix = " kmbt";
            String formattedNumber = "";
            NumberFormat formatter = new DecimalFormat("#,###.#");
            int power = (int) StrictMath.log10(value);
            value = value / (Math.pow(10, (power / 3) * 3));
            formattedNumber = formatter.format(value);
            formattedNumber = formattedNumber + suffix.charAt(power / 3);
            return formattedNumber.length() > 4 ? formattedNumber.replaceAll("\\.[0-9]+", "") : formattedNumber;
        }
        return "0";
    }

    public static void AddGold(OfflinePlayer offlinePlayer, int montant) throws IOException {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set("Gold", fileConfiguration.getInt("Gold") + montant);
        fileConfiguration.save(file);
        if (offlinePlayer.isOnline()) {
            lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
            offlinePlayer.getPlayer().sendMessage(textTranslation.addGold(lang, FormatInt(montant)));
            offlinePlayer.getPlayer().sendMessage(textTranslation.remainingGold(lang, GetGoldFormat(offlinePlayer)));
        }
    }

    public static int GetGold(OfflinePlayer offlinePlayer) {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        return fileConfiguration.getInt("Gold");
    }

    public static String GetGoldFormat(OfflinePlayer offlinePlayer) {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        double value = fileConfiguration.getInt("Gold");
        return FormatInt((int) value);
    }

    public static boolean GetHasEnoughGold(OfflinePlayer offlinePlayer, int montant) {
        return GetGold(offlinePlayer) >= montant;
    }

    public static void RemoveGold(OfflinePlayer offlinePlayer, int montant) throws IOException {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set("Gold", fileConfiguration.getInt("Gold") - montant);
        fileConfiguration.save(file);
        if (offlinePlayer.isOnline()) {
            lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
            offlinePlayer.getPlayer().sendMessage(textTranslation.removeGold(lang, FormatInt(montant)));
            offlinePlayer.getPlayer().sendMessage(textTranslation.remainingGold(lang, GetGoldFormat(offlinePlayer)));
        }
    }

    public static void SetGold(OfflinePlayer offlinePlayer, int montant) throws IOException {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set("Gold", montant);
        fileConfiguration.save(file);
        if (offlinePlayer.isOnline()) {
            lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
            offlinePlayer.getPlayer().sendMessage(textTranslation.remainingGold(lang, GetGoldFormat(offlinePlayer)));
        }
    }
}