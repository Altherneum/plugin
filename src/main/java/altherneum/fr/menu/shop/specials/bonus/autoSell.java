package altherneum.fr.menu.shop.specials.bonus;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import altherneum.fr.main.main;
import altherneum.fr.menu.island.island;
import altherneum.fr.menu.shop.classique.shopPrice;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.system.gold;
import altherneum.fr.system.ServerBootFile.serverType;

public class autoSell {
    public static void autoSellStarter() {
        if (ServerBootFile.getServerType().equals(serverType.SkyBlock)) {
            Plugin plugin = JavaPlugin.getPlugin(main.class);
            new BukkitRunnable() {
                @Override
                public void run() {
                    try {
                        for (World world : Bukkit.getWorlds()) {
                            String worldName = world.getName();

                            if (worldName.startsWith("i.")) {
                                String worldNameSplitDot = worldName.split("\\.")[1];
                                String worldNameSplitUnderScore = worldNameSplitDot.split("_")[0];
                                int i = Integer.valueOf(worldNameSplitUnderScore);

                                if (getDataStorage.islandAutoSell(i).exists()) {
                                    FileConfiguration fileConfiguration = YamlConfiguration
                                            .loadConfiguration(getDataStorage.islandAutoSell(i));
                                    int totalChest = fileConfiguration.getInt("totalChest");
                                    int actual = 0;
                                    int total = 0;
                                    int players = island.GetPlayerList(i).size();
                                    List<String> playerList = island.GetPlayerList(i);

                                    while (actual < totalChest) {
                                        Location loc = new Location(
                                                Bukkit.getWorld(fileConfiguration.getString(actual + "." + "world")),
                                                fileConfiguration.getInt(actual + "." + "x"),
                                                fileConfiguration.getInt(actual + "." + "y"),
                                                fileConfiguration.getInt(actual + "." + "z"));

                                        Block block = world.getBlockAt(loc);
                                        if (block.getState() instanceof InventoryHolder) {
                                            InventoryHolder ih = (InventoryHolder) block.getState();
                                            Inventory inventory = ih.getInventory();

                                            if (inventory.getContents().length >= 1) {
                                                for (ItemStack item : inventory.getContents()) {
                                                    if (item != null && !item.getType().equals(Material.AIR)) {
                                                        if (shopPrice.getPrix(item.getType()) != Integer.MAX_VALUE) {
                                                            total += (shopPrice.getPrix(item.getType())
                                                                    * item.getAmount());
                                                            inventory.remove(item);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        actual++;
                                    }

                                    if (total > 0) {
                                        for (String uuid : playerList) {
                                            gold.AddGold(Bukkit.getOfflinePlayer(UUID.fromString(uuid)), (total / players / 10));
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskTimer(plugin, (20 * 60 * 10), (20 * 60 * 30));
        }

    }

    public static void addChest(Block block, int island) throws IOException {
        File file = getDataStorage.islandAutoSell(island);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

        int totalChest = fileConfiguration.getInt("totalChest");

        // Check for all value possible if world != null
        // if yes it mean there is a chest registered
        // cancel it and dont remove item on hand

        fileConfiguration.set(totalChest + "." + "x", block.getX());
        fileConfiguration.set(totalChest + "." + "y", block.getY());
        fileConfiguration.set(totalChest + "." + "z", block.getZ());
        fileConfiguration.set(totalChest + "." + "world", block.getWorld().getName());

        totalChest++;
        fileConfiguration.set("totalChest", totalChest);

        fileConfiguration.save(file);
    }
}
