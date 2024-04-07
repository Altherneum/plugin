package altherneum.fr.commands.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import altherneum.fr.enchantements.instantmine;
import altherneum.fr.main.main;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.world.api.structure;
import altherneum.fr.world.api.worldManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class test implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                Player player = (Player) sender;
                Plugin plugin = JavaPlugin.getPlugin(main.class);
                instantmine instantmine = new instantmine("instantmine");
                player.getItemInHand().addUnsafeEnchantment(instantmine, 0);
                player.sendMessage("OK");
                for (Enchantment enchant : player.getItemInHand().getEnchantments().keySet()) {
                    player.sendMessage("enchant : " + enchant.getName());
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<String>();
        return list;
    }
}