package altherneum.fr.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import altherneum.fr.menu.island.islandMenu;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class island implements CommandExecutor, TabCompleter {
    public ArrayList<String> TotalCmds() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.addAll(Inviter());
        cmds.addAll(Locate());
        cmds.addAll(Leave());
        cmds.addAll(Pending());
        cmds.addAll(Kick());
        cmds.addAll(Owner());
        return cmds;
    }

    public ArrayList<String> Inviter() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("invite");
        cmds.add("add");
        cmds.add("inviter");
        return cmds;
    }

    public ArrayList<String> Locate() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("locate");
        cmds.add("spawnpoint");
        cmds.add("location");
        return cmds;
    }

    public ArrayList<String> Leave() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("leave");
        cmds.add("quitter");
        return cmds;
    }

    public ArrayList<String> Pending() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("join");
        cmds.add("pending");
        cmds.add("rejoindre");
        return cmds;
    }

    public ArrayList<String> Kick() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("kick");
        cmds.add("exclure");
        cmds.add("exclude");
        return cmds;
    }

    public ArrayList<String> Owner() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("owner");
        cmds.add("chef");
        cmds.add("leader");
        return cmds;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                if (args.length == 0) {
                    if (altherneum.fr.menu.island.island.GetHasIsland((OfflinePlayer) sender)) {
                        altherneum.fr.menu.island.island.TeleportToIsland((Player) sender);
                    } else {
                        altherneum.fr.menu.island.island.CreateIsland((Player) sender);
                    }
                    return true;
                } else if (args.length == 1) {

                    if (Kick().contains(args[0].toLowerCase())) {
                        islandMenu.ExcludeMenu((Player) sender);
                        return true;
                    } else if (Inviter().contains(args[0].toLowerCase())) {
                        islandMenu.InviterMenu((Player) sender);
                        return true;
                    } else if (Locate().contains(args[0].toLowerCase())) {
                        altherneum.fr.menu.island.island.SetIslandLocation((Player) sender);
                        return true;
                    } else if (Owner().contains(args[0].toLowerCase())) {
                        islandMenu.OwnerMenu((Player) sender);
                        return true;
                    } else if (Leave().contains(args[0].toLowerCase())) {
                        islandMenu.LeaveMenu((Player) sender);
                        return true;
                    } else if (Pending().contains(args[0].toLowerCase())) {
                        islandMenu.PendingMenu((Player) sender);
                        return true;
                    }
                } else if (args.length == 2) {
                    if (Inviter().contains(args[0].toLowerCase())) {
                        if (getDataStorage.playerExist(Bukkit.getOfflinePlayer(args[1]))) {
                            if (!altherneum.fr.menu.island.island.GetHasIsland(Bukkit.getOfflinePlayer(args[1]))) {
                                altherneum.fr.menu.island.island.SetPendingIsland(Bukkit.getOfflinePlayer(args[1]),
                                        (OfflinePlayer) sender);
                            } else {
                                sender.sendMessage(textTranslation
                                        .OtherAlreadyHaveIsland(playerLang.getPlayerLang((Player) sender)));
                            }
                        } else {
                            sender.sendMessage(
                                    textTranslation.noInformationPage(playerLang.getPlayerLang((Player) sender)));
                        }
                        return true;
                    }
                }
            } catch (IOException | IllegalArgumentException | ParseException e) {
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> argsToReturn = new ArrayList<String>();
        if (args.length == 1) {
            return TotalCmds();
        } else if (args.length == 2) {
            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                argsToReturn.add(offlinePlayer.getName());
            }
        }
        return argsToReturn;
    }
}