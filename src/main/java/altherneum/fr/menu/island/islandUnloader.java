package altherneum.fr.menu.island;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import altherneum.fr.main.main;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;
import altherneum.fr.world.api.worldManager;

import java.io.IOException;
import java.text.ParseException;

public class islandUnloader {
    public static void IslandScheduler() {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        new BukkitRunnable() {
            @Override
            public void run() {
                int i = 0;
                int i2 = 0;
                for (World world : Bukkit.getWorlds()) {
                    if (world.getName().startsWith("i.")) {
                        i2++;
                        if (world.getPlayers().isEmpty()) {
                            i++;
                            try {
                                worldManager.UnloadWorld(world.getName(), true);
                            } catch (IllegalArgumentException | IllegalStateException | IOException
                                    | ParseException e) {
                            }
                        }
                    }
                }
                if (i > 0) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        try {
                            lang.languages lang = playerLang.getPlayerLang(player);
                            player.sendMessage(textTranslation.IslandUnloaded(lang, i, i2));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 10 * 60 * 20, 20 * 60 * 20);
    }
}