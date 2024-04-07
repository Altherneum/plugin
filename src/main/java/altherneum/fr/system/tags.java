package altherneum.fr.system;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class tags {
    public enum TagsList {
        Admin, Build, Builder, Messages, Moderation, Survie, Creatif, Test, Beta, Joueur, YouTube,
        Aventurier, Soldat
    }

    public static File file(OfflinePlayer offlinePlayer) {
        return getDataStorage.tagFile(offlinePlayer);
    }

    public static FileConfiguration fileConfiguration(OfflinePlayer offlinePlayer) throws IOException {
        File file = file(offlinePlayer);
        if (!file.exists()) {
            file.createNewFile();
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void addTags(OfflinePlayer offlinePlayer, TagsList TagString) throws IOException {
        if (!hasTags(offlinePlayer, TagString)) {
            List<String> tagsList = GetTag(offlinePlayer);
            tagsList.add(TagString.name());
            FileConfiguration fileConfigurationPlayer = fileConfiguration(offlinePlayer);
            fileConfigurationPlayer.set("Tags", tagsList);
            fileConfigurationPlayer.save(file(offlinePlayer));

            if (offlinePlayer.isOnline()) {
                lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
                offlinePlayer.getPlayer().sendMessage(textTranslation.addTags(lang, TagString.name()));
            }
        }
    }

    public static List<String> GetTag(OfflinePlayer offlinePlayer) throws IOException {
        return fileConfiguration(offlinePlayer).getStringList("Tags");
    }

    public static boolean hasTags(OfflinePlayer offlinePlayer, TagsList tagsString) throws IOException {
        return GetTag(offlinePlayer).contains(tagsString.name());
    }

    public static void removeTags(OfflinePlayer offlinePlayer, TagsList TagString) throws IOException {
        if (hasTags(offlinePlayer, TagString)) {
            List<String> tagsList = GetTag(offlinePlayer);
            tagsList.remove(TagString.name());
            FileConfiguration fileConfigurationPlayer = fileConfiguration(offlinePlayer);
            if (tagsList.isEmpty()) {
                file(offlinePlayer).delete();
            } else {
                fileConfigurationPlayer.set("Tags", tagsList);
                fileConfigurationPlayer.save(file(offlinePlayer));
            }

            if (offlinePlayer.isOnline()) {
                lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
                offlinePlayer.getPlayer().sendMessage(textTranslation.removeTags(lang, TagString.name()));
            }
        }
    }
}