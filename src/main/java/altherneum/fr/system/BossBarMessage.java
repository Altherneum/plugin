package altherneum.fr.system;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import altherneum.fr.main.main;

public class BossBarMessage implements Listener {
    private static final JavaPlugin plugin = main.getPlugin(main.class);

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    @EventHandler
    public void onPlayerJoinStartBar(PlayerJoinEvent e) {
        sendBossBar(e.getPlayer());
    }

    public static NamespacedKey getKeyAsNameSpaced(Player player) {
        return new NamespacedKey(getPlugin(), player.getUniqueId().toString());
    }

    public static void sendBossBar(Player player) {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        new BukkitRunnable() {

            int duration = 5*20;
            int value = 0;
            int remainDuration = duration + 1;

            BossBar bossBar;
            NamespacedKey key = getKeyAsNameSpaced(player);

            @Override
            public void run() {
                if (player.isOnline()) {

                    if (remainDuration == duration + 1) {

                        if (value == 0) {
                            bossBar = Bukkit.createBossBar(
                                    key,
                                    "§6Gold §f: §2§l" + gold.GetGoldFormat(player),
                                    BarColor.WHITE,
                                    BarStyle.SOLID);
                        }

                        else if (value == 1) {
                            bossBar = Bukkit.createBossBar(
                                    key,
                                    "",
                                    BarColor.WHITE,
                                    BarStyle.SOLID);
                        }
                        
                        else if (value == 2) {
                            bossBar = Bukkit.createBossBar(
                                    key,
                                    "§f§lJoueurs en ligne §r: §2" + Bukkit.getOnlinePlayers().size() + " §f/ §4"
                                            + Bukkit.getMaxPlayers(),
                                    BarColor.WHITE,
                                    BarStyle.SOLID);
                        }

                        else {
                            bossBar = Bukkit.createBossBar(
                                    key,
                                    "§4§l" + ServerBootFile.getServerType() + "§7.§fAltherneum§7.§ffr",
                                    BarColor.WHITE,
                                    BarStyle.SOLID);
                        }

                        bossBar.setProgress(1);
                        bossBar.setVisible(true);
                        bossBar.addPlayer(player);

                        remainDuration--;
                    } else {

                        double progress = (double) remainDuration / duration;
                        bossBar.setProgress(progress);

                        if (remainDuration == 0) {
                            bossBar.removeAll();
                            Bukkit.removeBossBar(key);

                            remainDuration = duration + 1;

                            value++;
                            if (value == 4) {
                                value = 0;
                            }
                        } else {
                            remainDuration--;

                            if (value == 0) {
                                bossBar.setTitle("§6Gold §f: §2§l" + gold.GetGoldFormat(player));
                            }
                        }
                    }
                } else {
                    bossBar.removeAll();
                    Bukkit.removeBossBar(key);
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 1);
    }
}
