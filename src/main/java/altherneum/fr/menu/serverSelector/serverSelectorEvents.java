package altherneum.fr.menu.serverSelector;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.system.BungeePluginMessageListener;

import java.io.IOException;
import java.text.ParseException;

public class serverSelectorEvents implements Listener {
    @EventHandler
    public void PlayerClickServer(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.hub)) {
                BungeePluginMessageListener.teleportPlayer("hub", (Player) e.getWhoClicked());
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.survie)) {
                BungeePluginMessageListener.teleportPlayer("survie", (Player) e.getWhoClicked());
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.creatif)) {
                BungeePluginMessageListener.teleportPlayer("creatif", (Player) e.getWhoClicked());
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.anarchie)) {
                BungeePluginMessageListener.teleportPlayer("anarchie", (Player) e.getWhoClicked());
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.rpg)) {
                BungeePluginMessageListener.teleportPlayer("rpg", (Player) e.getWhoClicked());
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.skyblock)) {
                BungeePluginMessageListener.teleportPlayer("skyblock", (Player) e.getWhoClicked());
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.opprison)) {
                BungeePluginMessageListener.teleportPlayer("opprison", (Player) e.getWhoClicked());
            }
        }
    }
}