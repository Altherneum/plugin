package altherneum.fr.player;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.system.dateAPI;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.system.ServerBootFile.serverType;
import altherneum.fr.system.security.SecurityList;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;
import altherneum.fr.text.lang.languages;

public class anarchieLife implements Listener {
    public static ItemStack GetlifeItemStack(lang.languages lang, OfflinePlayer offlinePlayer)
            throws ParseException, IOException {
        ItemStack is = new ItemStack(Material.DIRT);
        if (canGetLife(offlinePlayer)) {
            is.setType(Material.POTION);
        } else {
            is.setType(Material.BARRIER);
        }
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.anarchieLifeTitle(lang, canGetLife(offlinePlayer)));

        Date date = getLastLifeDate(offlinePlayer);
        if (date != null) {
            meta.setLore(itemsTranslation.anarchieLifeLore(lang, getLastLifeDate(offlinePlayer)));
        }
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.anarchielife);
        return is;
    }

    @EventHandler
    public void PlayerClickSettings(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.anarchielife)) {
                if (canGetLife((Player) e.getWhoClicked())) {
                    removeDeath((Player) e.getWhoClicked());
                }
            }
        }
    }

    public static int getDeathCount(OfflinePlayer offlinePlayer) {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        return fileConfiguration.getInt("deathAnarchie");
    }

    public static void addDeath(OfflinePlayer offlinePlayer) throws IOException {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        int death = fileConfiguration.getInt("deathAnarchie");
        if (death <= 2) {
            death++;
            fileConfiguration.set("deathAnarchie", death);
            fileConfiguration.save(file);
        }
    }

    public static void removeDeath(OfflinePlayer offlinePlayer) throws IOException {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        int death = fileConfiguration.getInt("deathAnarchie");
        if (death >= -1) {
            death--;
            fileConfiguration.set("deathAnarchie", death);
            fileConfiguration.save(file);
            setDateLifeDate(offlinePlayer);

            if (offlinePlayer.isOnline()) {
                lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
                int count = getRemainingLifes(offlinePlayer);
                offlinePlayer.getPlayer().sendMessage(textTranslation.RemoveDeathAnarchie(lang, count));
            }
        } else {
            // send message max life possible
        }
    }

    public static int getRemainingLifes(OfflinePlayer offlinePlayer) {
        return 3 - getDeathCount(offlinePlayer);
    }

    public static Date getLastLifeDate(OfflinePlayer offlinePlayer) throws ParseException {
        File file = getDataStorage.playerFile(offlinePlayer);
        return dateAPI.ReturnDate(file, "lifeAnarchie");
    }

    public static void setDateLifeDate(OfflinePlayer offlinePlayer) throws IOException {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set("lifeAnarchie", dateAPI.DateFormated(dateAPI.now()));
        fileConfiguration.save(file);
    }

    public static boolean canGetLife(OfflinePlayer offlinePlayer) throws ParseException {
        Date date = getLastLifeDate(offlinePlayer);
        if (date == null) {
            return true;
        }

        Date now = new Date();
        date.setHours(date.getHours() + 24);
        if (now.after(date)) {
            int death = getDeathCount(offlinePlayer);

            if (death >= -1) {
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void playerDeathAnarchie(PlayerDeathEvent e) throws IOException, ParseException {
        if (ServerBootFile.getServerType().equals(serverType.Anarchie)) {
            anarchieLife.addDeath(e.getPlayer());
            int counter = anarchieLife.getRemainingLifes(e.getPlayer());

            languages lang = playerLang.getPlayerLang(e.getPlayer());
            e.getPlayer().sendMessage(textTranslation.deathAnarchie(lang, counter));

            if (canGetLife(e.getPlayer()) || getLastLifeDate(e.getPlayer()) == null) {
                removeDeath(e.getPlayer());
            }

            if (counter <= 0) {
                altherneum.fr.system.security.addSecurity(e.getPlayer(), SecurityList.BanAnarchie, 1);
            }
        }
    }

    @EventHandler
    public void playerJoinGetLife(PlayerJoinEvent e) throws ParseException, IOException {
        if (ServerBootFile.getServerType().equals(serverType.Anarchie)) {
            if (canGetLife(e.getPlayer()) || getLastLifeDate(e.getPlayer()) == null) {
                removeDeath(e.getPlayer());
            }
        }
    }
}
