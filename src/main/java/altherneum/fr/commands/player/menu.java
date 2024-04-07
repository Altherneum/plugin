package altherneum.fr.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import altherneum.fr.menu.compass.compassMenu;
import altherneum.fr.menu.island.islandMenu;
import altherneum.fr.menu.shop.classique.shopMenu;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class menu implements CommandExecutor, TabCompleter {
    public ArrayList<String> TotalCmds() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.addAll(Ile());
        cmds.addAll(Quetes());
        cmds.addAll(Voyage());
        cmds.addAll(Shop());
        cmds.addAll(Enchats());
        return cmds;
    }

    public ArrayList<String> Ile() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("ile");
        cmds.add("is");
        cmds.add("island");
        return cmds;
    }

    public ArrayList<String> Quetes() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("quetes");
        cmds.add("quete");
        cmds.add("quest");
        cmds.add("quests");
        return cmds;
    }

    public ArrayList<String> Voyage() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("voyage");
        cmds.add("teleportation");
        return cmds;
    }

    public ArrayList<String> Shop() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("boutique");
        cmds.add("shop");
        return cmds;
    }

    public ArrayList<String> Enchats() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("enchants");
        cmds.add("enchantements");
        return cmds;
    }

    public ArrayList<String> islandBonus() {
        ArrayList<String> cmds = new ArrayList<>();
        cmds.add("bonus");
        cmds.add("boost");
        cmds.add("islandbonus");
        cmds.add("bonusile");
        return cmds;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                if (args.length == 0) {
                    compassMenu.menuCompass((Player) sender);
                    return true;
                } else if (args.length == 1) {
                    if (Ile().contains(args[0].toLowerCase())) {
                        islandMenu.islandCompass((Player) sender);
                        return true;
                    } else if (Quetes().contains(args[0].toLowerCase())) {

                        return true;
                    } else if (Voyage().contains(args[0].toLowerCase())) {

                        return true;
                    } else if (Shop().contains(args[0].toLowerCase())) {
                        shopMenu.OpenShop((Player) sender);
                        return true;
                    } else if (Enchats().contains(args[0].toLowerCase())) {

                        return true;
                    } else if (islandBonus().contains(args[0].toLowerCase())) {

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
        }
        return argsToReturn;
    }
}