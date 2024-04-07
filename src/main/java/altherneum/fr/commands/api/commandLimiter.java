package altherneum.fr.commands.api;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import altherneum.fr.system.dateAPI;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.system.tags;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class commandLimiter implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCmd(PlayerCommandPreprocessEvent e) throws IOException {
        LogData(e.getPlayer(), e.getMessage());
        if (!isAdmin.isAdmin((OfflinePlayer) e.getPlayer())) {
            boolean cancel = true;

            for (String string : commandList()) {
                if (e.getMessage().split(" ")[0].equalsIgnoreCase(string)) {
                    cancel = false;
                }
            }

            if (e.getMessage().startsWith("//") || e.getMessage().split(" ")[0].equalsIgnoreCase("/mask")) {
                if (tags.hasTags(e.getPlayer(), tags.TagsList.Builder)) {
                    cancel = false;
                }
            }

            if (cancel) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(textTranslation.limitedCmd(playerLang.getPlayerLang(e.getPlayer())));
                e.getPlayer().sendMessage(textTranslation.useHelp(playerLang.getPlayerLang(e.getPlayer())));
            }
        }
    }

    public static ArrayList<String> commandList() {
        ArrayList<String> cmdList = new ArrayList<>();
        Plugin plugin = Bukkit.getPluginManager().getPlugin("plugin");
        List<Command> cmds = PluginCommandYamlParser.parse(plugin);
        for (Command commands : cmds) {
            cmdList.add("/" + commands.getName());
            for (String aliases : commands.getAliases()) {
                cmdList.add("/" + aliases);
            }
        }
        return cmdList;
    }

    public static File cmdFile(OfflinePlayer offlinePlayer) throws IOException {
        File file = getDataStorage.cmdFile(offlinePlayer);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static FileConfiguration playerFileConfiguration(OfflinePlayer offlinePlayer) throws IOException {
        return YamlConfiguration.loadConfiguration(cmdFile(offlinePlayer));
    }

    @SuppressWarnings("deprecation")
    public static void LogData(Player player, String string) throws IOException {
        FileConfiguration fileConfiguration = playerFileConfiguration(player);
        List<String> Messages = fileConfiguration.getStringList(
                dateAPI.now().getYear() + 1900 + "." + (dateAPI.now().getMonth() + 1) + "." + dateAPI.now().getDate());
        Messages.add(dateAPI.now().getHours() + ":" + dateAPI.now().getMinutes() + ":" + dateAPI.now().getSeconds()
                + " : " + string);
        fileConfiguration.set(
                dateAPI.now().getYear() + 1900 + "." + (dateAPI.now().getMonth() + 1) + "." + dateAPI.now().getDate(),
                Messages);
        fileConfiguration.save(cmdFile(player));
    }
}