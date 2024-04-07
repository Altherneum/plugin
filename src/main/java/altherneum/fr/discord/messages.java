package altherneum.fr.discord;

import org.bukkit.Bukkit;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.message.MessageBuilder;

import altherneum.fr.system.ServerBootFile;
import altherneum.fr.text.lang;
import altherneum.fr.text.textTranslation;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class messages {
    public static void botStop() throws ExecutionException, InterruptedException {
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.append("**Bot & serveur __" + ServerBootFile.serverTypeActual.toString() + " hors ligne__** ❌");
        ServerTextChannel serverTextChannel = discordMain.api.getServerTextChannelById(token.rebootChannelID())
                .get();
        messageBuilder.send(serverTextChannel).get();
        SendMessageToBukkit("§6§lBot discord §r§4§lhors ligne !");
        discordMain.api.disconnect();
    }

    public static void botStart() {
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.append("**Bot & serveur __" + ServerBootFile.serverTypeActual.toString() + " en ligne__** ✅");
        ServerTextChannel serverTextChannel = discordMain.api.getServerTextChannelById(token.rebootChannelID())
                .get();
        messageBuilder.send(serverTextChannel);
        SendMessageToBukkit("§6§lBot discord §r§2§len ligne !");
    }

    public static void ServerRestart(int sec) {
        String temps = "";
        int time = 0;
        if ((sec / 60) >= 1) {
            time = sec / 60;
            if (time == 1) {
                temps += " minute ";
            } else {
                temps += " minutes ";
            }
        } else {
            temps += " secondes ";
            time = sec;
        }
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.append("**__Redémarrage__ Bot & serveur " + ServerBootFile.serverTypeActual.toString()
                + "** dans ⏳ " + time + temps + " " + "\uD83D\uDEA5");
        ServerTextChannel serverTextChannel = discordMain.api.getServerTextChannelById(token.rebootChannelID())
                .get();
        messageBuilder.send(serverTextChannel);
        // SendMessageToChannel("**__Redémarrage__ Bot & serveur " +
        // ServerBootFile.serverTypeActual.toString() + "** dans ⏳ " + time + temps + "
        // " + "\uD83D\uDEA5");
        SendMessageToBukkit("");
        SendMessageToBukkit(
                textTranslation.System(lang.languages.fr) + "§6Redémarrage Bot & serveur §rdans §l§4" + time + temps);
        SendMessageToBukkit("");
    }

    public static void SendMessageToChannel(String message) {
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.append(message);
        ServerTextChannel serverTextChannel = discordMain.api.getServerTextChannelById(token.channel()).get();
        messageBuilder.send(serverTextChannel);
    }

    public static void SendMessageToChannel(ArrayList<String> message) throws ExecutionException, InterruptedException {
        MessageBuilder messageBuilder = new MessageBuilder();
        for (String string : message) {
            messageBuilder.append(string);
            messageBuilder.appendNewLine();
        }
        ServerTextChannel serverTextChannel = discordMain.api.getServerTextChannelById(token.channel()).get();
        messageBuilder.send(serverTextChannel);
    }

    public static void SendMessageToBukkit(String message) {
        Bukkit.broadcastMessage(message);
    }
}