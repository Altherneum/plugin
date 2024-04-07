package altherneum.fr.menu.island.bonus;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.menu.island.island;
import altherneum.fr.system.getDataStorage;

import java.io.IOException;
import java.util.ArrayList;

public class bonusPlayerFile {
    public static FileConfiguration playerFileConfiguration(OfflinePlayer offlinePlayer) {
        return YamlConfiguration.loadConfiguration(getDataStorage.islandBonus(offlinePlayer));
    }

    public static FileConfiguration islandFileConfiguration(OfflinePlayer offlinePlayer) throws IOException {
        return YamlConfiguration.loadConfiguration(getDataStorage.islandGlobalBonus(offlinePlayer));
    }

    public static FileConfiguration islandFileConfiguration(int ile) throws IOException {
        return YamlConfiguration.loadConfiguration(getDataStorage.islandGlobalBonus(ile));
    }

    public static int getLvl(OfflinePlayer offlinePlayer, persistentData.customKey key) {
        return playerFileConfiguration(offlinePlayer).getInt(key.toString());
    }

    public static int getLvlIsland(OfflinePlayer offlinePlayer, persistentData.customKey key) throws IOException {
        return islandFileConfiguration(offlinePlayer).getInt(key.toString());
    }

    public static int getLvlIsland(int ile, persistentData.customKey key) throws IOException {
        return islandFileConfiguration(ile).getInt(key.toString());
    }

    public static void setLvlIsland(OfflinePlayer offlinePlayer, persistentData.customKey key, int lvl)
            throws IOException {
        FileConfiguration fileConfiguration = islandFileConfiguration(offlinePlayer);
        fileConfiguration.set(key.toString(), lvl);
        fileConfiguration.save(getDataStorage.islandGlobalBonus(offlinePlayer));
        updateIslandBonus(key, offlinePlayer);
    }

    public static void addLvl(OfflinePlayer offlinePlayer, persistentData.customKey key, int lvl) throws IOException {
        FileConfiguration fileConfiguration = playerFileConfiguration(offlinePlayer);
        fileConfiguration.set(key.toString(), getLvl(offlinePlayer, key) + lvl);
        fileConfiguration.save(getDataStorage.islandBonus(offlinePlayer));
        updateIslandLvl(offlinePlayer, key);
    }

    public static void updateIslandLvl(OfflinePlayer offlinePlayer, persistentData.customKey key) throws IOException {
        int total = 0;
        for (OfflinePlayer offlinePlayer1 : island.GetIslandPlayer(offlinePlayer)) {
            total += getLvl(offlinePlayer1, key);
        }
        setLvlIsland(offlinePlayer, key, total);
    }

    public static void updateAllIslandLvl(OfflinePlayer offlinePlayer) throws IOException {
        int total = 0;
        for (persistentData.customKey key1 : KeyList()) {
            for (OfflinePlayer offlinePlayer1 : island.GetIslandPlayer(offlinePlayer)) {
                total += getLvl(offlinePlayer1, key1);
            }
            setLvlIsland(offlinePlayer, key1, total);
            total = 0;
        }
    }

    public static ArrayList<persistentData.customKey> KeyList() {
        ArrayList<persistentData.customKey> keys = new ArrayList<>();
        keys.add(persistentData.customKey.bonusores);
        keys.add(persistentData.customKey.bonusplayeramount);
        keys.add(persistentData.customKey.bonustickspeed);
        keys.add(persistentData.customKey.bonusworldborder);
        return keys;
    }

    public static void updateIslandBonus(persistentData.customKey key, OfflinePlayer offlinePlayer) throws IOException {
        for (World world : Bukkit.getWorlds()) {
            if (island.isPlayerIsland(world.getName(), offlinePlayer)) {
                updateIslandBonus(key, world, offlinePlayer);
            }
        }
    }

    public static void updateIslandBonus(persistentData.customKey key, World world, OfflinePlayer offlinePlayer)
            throws IOException {
        switch (key) {
            case bonusworldborder:
                world.getWorldBorder().setCenter(world.getSpawnLocation());
                world.getWorldBorder()
                        .setSize(50 + (getLvlIsland(offlinePlayer, persistentData.customKey.bonusworldborder) * 25));
                return;
            case bonustickspeed:
                world.setGameRule(GameRule.RANDOM_TICK_SPEED,
                        3 + (getLvlIsland(offlinePlayer, persistentData.customKey.bonustickspeed) / 10));
        }
    }
}