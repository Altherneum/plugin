package altherneum.fr.discord;

import java.io.IOException;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class discordMain {
    public static DiscordApi api = null;

    public static void startBot() throws IOException {
        DiscordApiBuilder apiBuilder = new DiscordApiBuilder();
        apiBuilder.setToken(token.getTokens());
        apiBuilder.setAllIntents();
        api = apiBuilder.login().join();
        registerEvents();
        messages.botStart();
    }

    public static void registerEvents() {
        eventsDiscord.ChatLinker();
        eventsDiscord.reconnect();
        uptime.StartUptime();
    }
}