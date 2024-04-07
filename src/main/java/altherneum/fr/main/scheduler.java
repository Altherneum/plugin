package altherneum.fr.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import altherneum.fr.discord.messages;
import altherneum.fr.entity.armorStand;
import altherneum.fr.entity.spawnNPC;
import altherneum.fr.menu.island.islandUnloader;
import altherneum.fr.menu.shop.specials.bonus.autoSell;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.system.Tab;
import altherneum.fr.system.dateAPI;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.world.api.LastWorldsInit;
import altherneum.fr.world.api.structure;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;
import java.util.Random;

public class scheduler {
    public static void start() {
        try {
            delayedScheduler();
            islandUnloader.IslandScheduler();
            restart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ServersStats() throws IOException {
        Random random = new Random();
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    String server = ServerBootFile.getServerTypeFromYML().toString().toLowerCase(Locale.ROOT);
                    File file = getDataStorage.serversStatsFile(server);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

                    fileConfiguration.set("onlinePlayers", Bukkit.getServer().getOnlinePlayers().size());
                    fileConfiguration.set("maxPlayers", Bukkit.getServer().getMaxPlayers());
                    fileConfiguration.set("lastTick", dateAPI.DateFormated(dateAPI.now()));

                    fileConfiguration.save(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 20 + random.nextInt(80));
    }

    public static void delayedScheduler() {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    armorStand.start();
                    spawnNPC.start();
                    Tab.scheduler();
                    ServersStats();
                    BuildSpawn();
                    autoSell.autoSellStarter();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskLater(plugin, 5);
    }

    public static void BuildSpawn() throws IOException, ParseException {
        Boolean BuildSpawn = false;

        String server = ServerBootFile.getServerTypeFromYML().toString().toLowerCase(Locale.ROOT);
        File file = getDataStorage.serversStatsFile(server);

        if (!file.exists()) {
            file.createNewFile();
            BuildSpawn = true;
        }

        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (!fileConfiguration.getBoolean("spawnSet")) {
            fileConfiguration.set("spawnSet", true);
            fileConfiguration.save(file);
            BuildSpawn = true;
        }

        if (BuildSpawn) {
            structure.setStructure(structure.SpawnName(ServerBootFile.getServerTypeFromYML()), "Spawn", 1, 5);
            LastWorldsInit.LoadWorld();
        }

        LastWorldsInit.StartOther();
    }

    public static void restart() {
        try {
            sendRestartMessage(30 * 60 * 20);
            sendRestartMessage(15 * 60 * 20);
            sendRestartMessage(10 * 60 * 20);
            sendRestartMessage(5 * 60 * 20);
            sendRestartMessage(3 * 60 * 20);
            sendRestartMessage(2 * 60 * 20);
            sendRestartMessage(60 * 20);
            sendRestartMessage(30 * 20);
            sendRestartMessage(15 * 20);
            sendRestartMessage(10 * 20);
            sendRestartMessage(5 * 20);
            sendRestartMessage(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendRestartMessage(int time) {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        int timeH = (20 * 60 * 60 * 8);
        int timeUntil = timeH - time;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (time == 0) {
                    Bukkit.spigot().restart();
                } else {
                    messages.ServerRestart(time / 20);
                }
            }
        }.runTaskLater(plugin, timeUntil);
    }
}