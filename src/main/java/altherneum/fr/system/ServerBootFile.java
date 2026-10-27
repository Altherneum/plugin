package altherneum.fr.system;

import org.bukkit.Bukkit;

import java.util.Locale;

public class ServerBootFile {
    public static serverType serverTypeActual;

    public enum serverType {
        Hub, SkyBlock, RPG, Survie, Anarchie, Creatif, OPPrison
    }

    public static serverType getServerType() {
        return serverTypeActual;
    }

    public static void loadServerType() {
        serverTypeActual = getServerTypeFromEnv();
    }

    public static serverType getServerTypeFromEnv() {
        String name = System.getenv("SERVER_NAME");
        if (name == null || name.isBlank()) {
            Bukkit.getLogger().warning("SERVER_NAME absent : type Hub par défaut.");
            return serverType.Hub;
        }
        switch (name.trim().toLowerCase(Locale.ROOT)) {
            case "hub":
                return serverType.Hub;
            case "skyblock":
                return serverType.SkyBlock;
            case "rpg":
                return serverType.RPG;
            case "survie":
                return serverType.Survie;
            case "anarchie":
                return serverType.Anarchie;
            case "creatif":
                return serverType.Creatif;
            case "opprison":
                return serverType.OPPrison;
            case "test":
                // Le serveur de test utilise les règles de Survie.
                return serverType.Survie;
            default:
                Bukkit.getLogger().warning("SERVER_NAME inconnu (" + name + ") : type Hub par défaut.");
                return serverType.Hub;
        }
    }
}
