package altherneum.fr.menu.shop.classique;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import altherneum.fr.system.getDataStorage;

import java.io.File;
import java.io.IOException;

public class shopLogs {
    public static void LogData(OfflinePlayer offlinePlayer, boolean Sell, Material material, int amount)
            throws IOException {
        File file = getDataStorage.shopLogs(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        String path = "";
        if (Sell) {
            path += "Sell.";
            fileConfiguration.set("Total.Sell",
                    fileConfiguration.getInt("Total.Sell") + (shopPrice.getPrix(material) * amount / 10));
        } else {
            path += "Buy.";
            fileConfiguration.set("Total.Buy",
                    fileConfiguration.getInt("Total.Buy") + (shopPrice.getPrix(material) * amount));
        }
        path += material.toString();
        fileConfiguration.set(path, fileConfiguration.getInt(path) + amount);
        fileConfiguration.save(file);
    }

    public static int GetTotal(boolean Sell, OfflinePlayer offlinePlayer) throws IOException {
        File file = getDataStorage.shopLogs(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (Sell) {
            return fileConfiguration.getInt("Total.Sell") + fileConfiguration.getInt("Total.Auto");
        } else {
            return fileConfiguration.getInt("Total.Buy") + fileConfiguration.getInt("Total.Special");
        }
    }

    public static void LogTotalAutoSell(OfflinePlayer offlinePlayer, int amountGold) throws IOException {
        File file = getDataStorage.shopLogs(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set("Total.Auto", fileConfiguration.getInt("Total.Auto") + amountGold);
        fileConfiguration.save(file);
    }

    public static void LogTotalSpecial(OfflinePlayer offlinePlayer, int amountGold) throws IOException {
        File file = getDataStorage.shopLogs(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set("Total.Special", fileConfiguration.getInt("Total.Special") + amountGold);
        fileConfiguration.save(file);
    }
}