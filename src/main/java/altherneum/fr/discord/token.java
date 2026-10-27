package altherneum.fr.discord;

import altherneum.fr.system.ServerBootFile;

public class token {
    public static String getTokens() {
        String token = System.getenv("DISCORD_BOT_TOKEN");
        return token == null ? "" : token.trim();
    }

    public static String channel() {
        switch (ServerBootFile.getServerType()) {
            case SkyBlock:
                return "1081945139355848835";
            case RPG:
                return "1081946116045676564";
            case Survie:
                return "1081945244888735804";
            case Anarchie:
                return "1081945360764772462";
            case Creatif:
                return "1081946383004733602";
            case Hub:
                return "1081944880114303066";
            case OPPrison:
                return "1099339446949777418";
            default:
                return "";
        }
    }

    public static String rebootChannelID() {
        return "1081945572744892466";
    }

    public static String uptimeChannelID() {
        return "1093591413159112847";
    }
}
