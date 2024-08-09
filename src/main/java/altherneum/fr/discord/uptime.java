package altherneum.fr.discord;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.sun.management.OperatingSystemMXBean;

import altherneum.fr.main.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class uptime {

    public static void StartUptime() {
        Random random = new Random();
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    sendUptime();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 150 + random.nextInt(150));
    }

    public static void sendUptime() throws UnknownHostException, IOException, InterruptedException, ExecutionException {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = allocatedMemory - freeMemory;
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();
        double cpuusage = operatingSystemMXBean.getSystemCpuLoad() * 100;
        File diskPartition = new File("/");
        long free = diskPartition.getFreeSpace();
        long total = diskPartition.getTotalSpace();

        boolean alternativePing = true;

        String adress1 = "www.google.fr";
        boolean isPinged = false;
        String adress2 = "www.discord.com";
        boolean isPinged2 = false;
        
        int port = 80;
        int timeout = 2000;

        long currentTime = System.currentTimeMillis();
        if (alternativePing) {
            isPinged = connectSocket(adress1, port, timeout);
        } else {
            isPinged = InetAddress.getByName(adress1).isReachable(timeout);
        }
        currentTime = System.currentTimeMillis() - currentTime;

        long currentTime2 = System.currentTimeMillis();
        if (alternativePing) {
            isPinged2 = InetAddress.getByName(adress2).isReachable(timeout);
        } else {
            isPinged2 = connectSocket(adress2, port, timeout);
        }
        currentTime2 = System.currentTimeMillis() - currentTime2;

        String message = "";

        long time = Instant.now().getEpochSecond();
        message += "<t:" + time + ":R>";

        message += "\n\n**__Joueurs__** : " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers();

        message += "\n\n**__RAM__** : " + Math.round(usedMemory / 1024 / 1024) + "/"
                + Math.round(maxMemory / 1024 / 1024) + " Mo";
        message += "\n**__CPU__** : " + Math.round(cpuusage) + "%";
        message += "\n**__TPS__** : " + Math.round(Bukkit.getTPS()[0]);
        message += "\n**__HDD__** : " + Math.round(free / 1024 / 1024 / 1024) + "/"
                + Math.round(total / 1024 / 1024 / 1024) + " Go";

        if (isPinged) {
            message += "\n\n**__Ping__** " + adress1 + " : " + currentTime + " Ms";
        } else {
            message += "\n\n**__Ping__** : __Erreur__";
        }

        if (isPinged2) {
            message += "\n**__Ping__** " + adress2 + " : " + currentTime2 + " Ms";
        } else {
            message += "\n**__Ping__** : __Erreur__";
        }

        sendDiscordUptime(message);
    }
    
    public static boolean connectSocket(String address, int port, int timeout) throws IOException {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(address, port), timeout);
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        } finally {
            socket.close();
        }
    }

    public static void sendDiscordUptime(String text) throws InterruptedException, ExecutionException {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setThumbnail(discordMain.api.getYourself().getAvatar());
        embedBuilder.setDescription(text);

        boolean found = false;
        for (Message messages : discordMain.api.getServerTextChannelById(token.uptimeChannelID()).get().getMessages(20)
                .get()) {
            if (messages.getUserAuthor().get().equals(discordMain.api.getYourself())) {
                found = true;
                messages.edit(embedBuilder);
                return;
            }
        }

        if (!found) {
            discordMain.api.getServerTextChannelById(token.uptimeChannelID()).get().sendMessage(embedBuilder);
        }
    }
}
