package altherneum.fr.discord;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class discordMain {
    public static DiscordApi api = null;

    public static void startBot() throws IOException {
        String tokenValue = token.getTokens();
        if (tokenValue.isEmpty()) {
            Bukkit.getLogger().warning("DISCORD_BOT_TOKEN absent : bot Discord désactivé.");
            return;
        }
        DiscordApiBuilder apiBuilder = new DiscordApiBuilder();
        apiBuilder.setToken(tokenValue);
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