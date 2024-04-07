package altherneum.fr.system;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import altherneum.fr.main.main;
import altherneum.fr.menu.island.island;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.File;
import java.io.IOException;

public class getDataStorage {
    public static String rootFolder() {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        return new File(plugin.getDataFolder().getAbsolutePath()).getParentFile().getParentFile().getParentFile()
                .getAbsolutePath();
    }

    public static File dataFolder() {
        File dataFolder = new File(rootFolder(), "DataFolder");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        return dataFolder;
    }

    public static File playerDataFolder() {
        File dataFolder = new File(dataFolder(), "players");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        return dataFolder;
    }

    public static File serverTypeFile() {
        return new File(dataFolder(), "serverTypeFile.yml");
    }

    public static File serversStatsFile(String server) {
        return new File(dataFolder(), server + ".yml");
    }

    public static File structureFolder() {
        File structuresFolder = new File(dataFolder(), "structures");
        if (!structuresFolder.exists()) {
            structuresFolder.mkdir();
        }
        return structuresFolder;
    }

    public static File structureFile(String nom) {
        File structuresFolder = new File(structureFolder(), nom + ".yml");
        return structuresFolder;
    }

    public static File playerFolder(OfflinePlayer offlinePlayer) {
        File playerFolder = new File(playerDataFolder(), offlinePlayer.getUniqueId().toString());
        if (!playerFolder.exists()) {
            playerFolder.mkdir();
        }
        return playerFolder;
    }

    public static File playerFile(OfflinePlayer offlinePlayer) {
        return new File(playerFolder(offlinePlayer), "playerFile.yml");
    }

    public static Boolean playerExist(OfflinePlayer offlinePlayer) {
        return playerFile(offlinePlayer).exists();
    }

    public static Boolean playerExistForBonus(CommandSender sender, OfflinePlayer offlinePlayer) throws IOException {
        if (!playerExist(offlinePlayer)) {
            lang.languages language;
            if (sender instanceof Player) {
                language = playerLang.getPlayerLang((Player) sender);
            } else {
                language = lang.languages.fr;
            }
            sender.sendMessage(textTranslation.noInformationPage(language));
            return false;
        }
        return true;
    }

    public static FileConfiguration playerFileConfiguration(OfflinePlayer offlinePlayer) throws IOException {
        File file = playerFile(offlinePlayer);
        if (!file.exists()) {
            file.createNewFile();
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static File tagFile(OfflinePlayer offlinePlayer) {

        return new File(playerFolder(offlinePlayer), "tag.yml");
    }

    public static File bonusFile(OfflinePlayer offlinePlayer) {
        return new File(playerFolder(offlinePlayer), "bonus.yml");
    }

    public static File securityFile(OfflinePlayer offlinePlayer) {
        return new File(playerFolder(offlinePlayer), "security.yml");
    }

    public static File chatFile(OfflinePlayer offlinePlayer) {
        return new File(playerFolder(offlinePlayer), "chat.yml");
    }

    public static File cmdFile(OfflinePlayer offlinePlayer) {
        return new File(playerFolder(offlinePlayer), "commands.yml");
    }

    public static File islandBonus(OfflinePlayer offlinePlayer) {
        return new File(playerFolder(offlinePlayer), "islandBoost.yml");
    }

    public static File islandGlobalBonus(OfflinePlayer offlinePlayer) throws IOException {
        return new File(islandFolder(island.GetIslandNumber(offlinePlayer)), "islandBoost.yml");
    }

    public static File islandGlobalBonus(int ile) throws IOException {
        return new File(islandFolder(ile), "islandBoost.yml");
    }

    public static File shopLogs(OfflinePlayer offlinePlayer) {
        return new File(playerFolder(offlinePlayer), "shopLogs.yml");
    }

    public static File playerIslandData(OfflinePlayer offlinePlayer) {
        return new File(playerFolder(offlinePlayer), "islandData.yml");
    }

    public static File playerOPPrisonData(OfflinePlayer offlinePlayer) {
        return new File(playerFolder(offlinePlayer), "OPPrison.yml");
    }

    public static File islandDataFolder() {
        File dataFolder = new File(dataFolder(), "islands");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        return dataFolder;
    }

    public static File islandFolder(int island) {
        File islandFolder = new File(islandDataFolder(), String.valueOf(island));
        if (!islandFolder.exists()) {
            islandFolder.mkdir();
        }
        return islandFolder;
    }

    public static File islandFile(int island) {
        return new File(islandFolder(island), "islandFile.yml");
    }

    public static File islandAutoSell(int island) {
        return new File(islandFolder(island), "islandAutoSell.yml");
    }
}