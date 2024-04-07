package altherneum.fr.system;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ServerBootFile {
    public static serverType serverTypeActual;

    public enum serverType {
        Hub, SkyBlock, RPG, Survie, Anarchie, Creatif, OPPrison
    }

    public static serverType getServerType() {
        return serverTypeActual;
    }

    public static void setServerType() {
        serverTypeActual = getServerTypeFromYML();
    }

    public static serverType getServerTypeFromYML() {
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(getDataStorage.serverTypeFile());
        return ServerBootFile.serverType
                .valueOf(fileConfiguration.getString("serverType." + Bukkit.getServer().getPort()));
    }

    public static void loadServerTypeFile() throws IOException {
        File file = getDataStorage.serverTypeFile();
        if (!file.exists()) {
            file.createNewFile();
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
            fileConfiguration.set("serverType." + Bukkit.getServer().getPort(), serverType.Hub.name());
            fileConfiguration.save(file);
        } else {
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(getDataStorage.serverTypeFile());
            if (fileConfiguration.get("serverType." + Bukkit.getServer().getPort()) == null) {
                fileConfiguration.set("serverType." + Bukkit.getServer().getPort(), serverType.Hub.name());
                fileConfiguration.save(file);
            }
        }
        setServerType();
    }
}