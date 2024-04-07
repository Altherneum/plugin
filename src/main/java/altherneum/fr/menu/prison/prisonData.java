package altherneum.fr.menu.prison;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;

import altherneum.fr.system.getDataStorage;

public class prisonData {
    public static FileConfiguration fileConfiguration(OfflinePlayer player) throws IOException {
        File file = getDataStorage.playerOPPrisonData(player);
        if (!file.exists()) {
            file.createNewFile();
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static Material getPickaxe(OfflinePlayer player) throws IOException {
        FileConfiguration fileConfiguration = fileConfiguration(player);
        if (fileConfiguration.get("material") == null) {
            return Material.WOODEN_PICKAXE;
        }
        return Material.valueOf(fileConfiguration.getString("material"));
    }

    public static void setPickaxe(OfflinePlayer player, Material material) throws IOException {
        FileConfiguration fileConfiguration = fileConfiguration(player);
        fileConfiguration.set("material", material.toString());
        fileConfiguration.save(getDataStorage.playerOPPrisonData(player));
    }

    public static ArrayList<String> getEnchant(OfflinePlayer player) throws IOException {
        FileConfiguration fileConfiguration = fileConfiguration(player);
        return (ArrayList<String>) fileConfiguration.getStringList("enchantements");
    }

    public static int getEnchantLvl(OfflinePlayer player, Enchantment enchantment) throws IOException {
        FileConfiguration fileConfiguration = fileConfiguration(player);
        return fileConfiguration.getInt("enchantement." + enchantment.getKey().toString());
    }

    public static void setEnchant(OfflinePlayer player, Enchantment enchantment, int lvl) throws IOException {
        FileConfiguration fileConfiguration = fileConfiguration(player);

        ArrayList<String> keys = getEnchant(player);
        keys.add(enchantment.getKey().toString());
        fileConfiguration.set("enchantements", keys);

        int newLvl = getEnchantLvl(player, enchantment) + lvl;
        fileConfiguration.set("enchantement." + enchantment.getKey().toString(), newLvl);

        fileConfiguration.save(getDataStorage.playerOPPrisonData(player));
    }

    public static ArrayList<String> getMinesUnlocked(OfflinePlayer player) throws IOException {
        FileConfiguration fileConfiguration = fileConfiguration(player);
        return (ArrayList<String>) fileConfiguration.getStringList("mines");
    }

    public static void setMinesUnlocked(OfflinePlayer player, ArrayList<String> mines) throws IOException {
        FileConfiguration fileConfiguration = fileConfiguration(player);
        fileConfiguration.set("mines", mines);
        fileConfiguration.save(getDataStorage.playerOPPrisonData(player));
    }
}
