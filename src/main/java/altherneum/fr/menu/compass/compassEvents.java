package altherneum.fr.menu.compass;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class compassEvents implements Listener {
    public static ArrayList<Integer> listPose() {
        ArrayList<Integer> listPose = new ArrayList<>();
        listPose.add(0);
        listPose.add(4);
        listPose.add(8);
        listPose.add(9);
        listPose.add(17);
        listPose.add(27);
        listPose.add(35);
        return listPose;
    }

    public static int getNextPose(int current) {
        if (current >= listPose().size() - 1) {
            return 0;
        } else {
            int i = current;
            i++;
            return i;
        }
    }

    public static int getActualPose(OfflinePlayer offlinePlayer) {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        return fileConfiguration.getInt("CompassPose");
    }

    public static void setNextPose(OfflinePlayer offlinePlayer) throws IOException { 
        moveCompass(offlinePlayer, getNextPose(getActualPose(offlinePlayer)));
    }

    public static boolean playerHasCompass(Player p) throws IOException {
        for (int i : listPose()) {
            if (p.getInventory().getItem(i) != null) {
                if (persistentData.hasPersistentDataItemStack(p.getInventory().getItem(i),
                        persistentData.customKey.compass)) {
                    lang.languages lang = playerLang.getPlayerLang(p);
                    if (p.getInventory().getItem(i).equals(compassItems.ItemStackCompass(lang))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int getPlayerCompassSlot(OfflinePlayer offlinePlayer) throws IOException {
        return listPose().get(getActualPose(offlinePlayer));
    }

    public static void setPlayerCompassSlot(OfflinePlayer offlinePlayer, int slot) throws IOException {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set("CompassPose", slot);
        fileConfiguration.save(file); 
    }

    public static void setCompassInv(Player p) throws IOException {
        lang.languages lang = playerLang.getPlayerLang(p);
        p.getInventory().setItem(getPlayerCompassSlot(p), compassItems.ItemStackCompass(lang)); 
    }

    public static void moveCompass(OfflinePlayer offlinePlayer, int pose) throws IOException {
        if (offlinePlayer.isOnline()) {
            Player p = offlinePlayer.getPlayer();
            int oldSlot = getPlayerCompassSlot(p);
            int newSlot = listPose().get(pose);
            if (p.getInventory().getItem(newSlot) == null || p.getInventory().getItem(newSlot).getType().equals(Material.AIR)) {
                p.getInventory().setItem(oldSlot, new ItemStack(Material.AIR));
            } else {
                p.getInventory().setItem(oldSlot, p.getInventory().getItem(newSlot));
            }
            setPlayerCompassSlot(offlinePlayer, pose);
            setCompassInv(p);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws IOException {
        setCompassInv(e.getPlayer());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) throws IOException {
        setCompassInv(e.getPlayer());
    }

    @EventHandler
    public void PlayerClickCompass(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.compass)) {
                if (e.getSlot() != getPlayerCompassSlot((OfflinePlayer) e.getWhoClicked())) {
                    e.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
                }

                compassMenu.menuCompass((Player) e.getWhoClicked());
                lang.languages lang = playerLang.getPlayerLang((OfflinePlayer) e.getWhoClicked());
                Player player = (Player) e.getWhoClicked();
                if (!e.getCurrentItem().equals(compassItems.ItemStackCompass(lang))) {
                    player.getInventory().setItem(e.getSlot(), compassItems.ItemStackCompass(lang));
                }

                if (!listPose().contains(e.getSlot())) {
                    player.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
                    setCompassInv(player);
                }
            }
        }
    }

    @EventHandler
    public void PlayerInteractCompass(PlayerInteractEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getItem(), persistentData.customKey.compass)) {
                compassMenu.menuCompass((Player) e.getPlayer());
            }
        }
    }
}