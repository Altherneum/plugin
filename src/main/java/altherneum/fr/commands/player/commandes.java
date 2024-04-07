package altherneum.fr.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class commandes implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                for (String s : userCommands()) {
                    sender.sendMessage(s);
                }
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static ArrayList<String> userCommands() {
        ArrayList<String> cmdList = new ArrayList<>();
        Plugin plugin = Bukkit.getPluginManager().getPlugin("Plugin");
        List<Command> cmds = PluginCommandYamlParser.parse(plugin);
        String cmd;
        for (Command commands : cmds) {
            if (commands.getPermission() == null) {
                cmd = "§6" + commands.getName() + " §r: " + "[ ";
                for (String aliases : commands.getAliases()) {
                    cmd += "/§2" + aliases + "§r ";
                }
                cmd += "]";
                cmdList.add(cmd);
            }
        }
        return cmdList;
    }
}