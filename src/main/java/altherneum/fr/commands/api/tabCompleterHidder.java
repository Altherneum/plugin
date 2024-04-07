package altherneum.fr.commands.api;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.plugin.Plugin;

import altherneum.fr.system.ServerBootFile;
import altherneum.fr.system.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class tabCompleterHidder implements Listener {
    @EventHandler
    public void onTabComplete(PlayerCommandSendEvent e) throws IOException {
        if (!isAdmin.isAdmin((OfflinePlayer) e.getPlayer())) {
            if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Creatif) && tags.hasTags(e.getPlayer(), tags.TagsList.Builder)) {
            } 
            else {
                e.getCommands().clear();
                for (String command : commands()) {
                    e.getCommands().add(command);
                }
            }
        }
    }

    public static ArrayList<String> commands() {
        ArrayList<String> cmdList = new ArrayList<>();
        Plugin plugin = Bukkit.getPluginManager().getPlugin("plugin");
        List<Command> cmds = PluginCommandYamlParser.parse(plugin);
        for (Command commands : cmds) {
            cmdList.add(commands.getName());
            for (String aliases : commands.getAliases()) {
                if (commands.getPermission() == null)/* Marche pas */ {
                    cmdList.add(aliases);
                }
            }
        }
        return cmdList;
    }
}