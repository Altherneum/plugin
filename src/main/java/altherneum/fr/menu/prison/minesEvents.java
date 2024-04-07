package altherneum.fr.menu.prison;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.system.gold;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.shopTranslation;
import altherneum.fr.world.api.teleportation;

public class minesEvents implements Listener {
    @EventHandler
    public void PlayerClickCompass(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.mineselector)) {
                minesMenu.minesMenu((Player) e.getWhoClicked());
            }

            else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.minestone)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.1")) {
                    World world = Bukkit.getWorld("Mine.1");
                    Location spawnLocation = new Location(world, 0, 0, 0, 0, 0);
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.1", false, spawnLocation);
                } else {
                    BuyMine((Player) e.getWhoClicked(), itemsTranslation.mineStone, "Mine.1");
                }
            }

            else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.mineiron)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.2")) {
                    World world = Bukkit.getWorld("Mine.2");
                    Location spawnLocation = new Location(world, 0, 0, 0, 0, 0);
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.2", false, spawnLocation);
                } else {
                    BuyMine((Player) e.getWhoClicked(), itemsTranslation.mineIron, "Mine.2");
                }
            }

            else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.minegold)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.3")) {
                    World world = Bukkit.getWorld("Mine.3");
                    Location spawnLocation = new Location(world, 0, 0, 0, 0, 0);
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.3", false, spawnLocation);
                } else {
                    BuyMine((Player) e.getWhoClicked(), itemsTranslation.mineGold, "Mine.3");
                }
            }

            else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.minediamond)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.4")) {
                    World world = Bukkit.getWorld("Mine.4");
                    Location spawnLocation = new Location(world, 0, 0, 0, 0, 0);
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.4", false, spawnLocation);
                } else {
                    BuyMine((Player) e.getWhoClicked(), itemsTranslation.mineDiamond, "Mine.4");
                }
            }

            else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.minenetherite)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.5")) {
                    World world = Bukkit.getWorld("Mine.5");
                    Location spawnLocation = new Location(world, 0, 0, 0, 0, 0);
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.5", false, spawnLocation);
                } else {
                    BuyMine((Player) e.getWhoClicked(), itemsTranslation.mineNetherite, "Mine.5");
                }
            }
        }
    }

    public void BuyMine(OfflinePlayer offlinePlayer, int prix, String nomMine)
            throws IOException, IllegalArgumentException, ParseException {
        if (gold.GetHasEnoughGold(offlinePlayer, prix)) {
            gold.RemoveGold(offlinePlayer, prix);
            ArrayList<String> mines = prisonData.getMinesUnlocked(offlinePlayer);
            mines.add(nomMine);
            prisonData.setMinesUnlocked(offlinePlayer, mines);
            minesMenu.minesMenu(offlinePlayer.getPlayer());
            // send message buy mine
        } else {
            offlinePlayer.getPlayer().sendMessage(
                    shopTranslation.PasAssezArgent(playerLang.getPlayerLang((Player) offlinePlayer)));
        }
    }
}