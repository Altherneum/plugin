package altherneum.fr.discord;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.server.Server;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class eventsDiscord {
    public static void ChatLinker() {
        discordMain.api.addMessageCreateListener(event -> {
            if (event.getChannel().getIdAsString().equals(token.channel())) {
                if (!event.getMessageAuthor().isBotUser()) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        try {
                            TextComponent message = new TextComponent();
                            message.setText("§r §7§l>>§r " + event.getMessage().getReadableContent());
                            p.getPlayer().spigot().sendMessage(TextPlayerHover(event.getMessageAuthor(), event.getServer().get()), message);
                        } catch (IOException | ParseException e) {
                        }
                    }
                    Bukkit.getServer().getLogger()
                            .info(event.getMessageAuthor().getId() + " : "
                                    + event.getMessageAuthor().getDiscriminatedName() + " : "
                                    + event.getMessage().getReadableContent());
                }
            }
        });
    }

    public static TextComponent TextPlayerHover(MessageAuthor messageAuthor, Server server) throws IOException, ParseException {
        TextComponent PlayerComponent = new TextComponent();
        PlayerComponent.setText("[ §r §6" + messageAuthor.getDisplayName() + " §r]");
        String hover = "";
        hover = hover.concat("\n");
        hover = hover.concat("§6§lUUID§r: " + messageAuthor.getIdAsString());
        hover = hover.concat("\n");
        hover = hover.concat("§6§lPseudo: " + messageAuthor.getName());
        hover = hover.concat("\n");
        hover = hover.concat("§6§lTAG§r: " + messageAuthor.getDiscriminator().get());
        if (messageAuthor.getConnectedVoiceChannel().isPresent()) {
            hover = hover.concat("\n");
            hover = hover.concat("§6§lVocal§r: " + messageAuthor.getConnectedVoiceChannel().get().getName());
        }
        hover = hover.concat("\n");
        hover = hover.concat("§6§lRôle§r: " + server.getHighestRole(messageAuthor.asUser().get()).get().getName());
        hover = hover.concat("\n");
        Timestamp timestamp = new Timestamp(messageAuthor.getCreationTimestamp().toEpochMilli());
        Date date = new Date(timestamp.getTime());
        Timestamp timestamp2 = new Timestamp(server.getJoinedAtTimestamp(messageAuthor.asUser().get()).get().toEpochMilli());
        Date date2 = new Date(timestamp2.getTime());
        hover = hover.concat("§6§lRejoins Discord le§r: " + DateFormated(date));
        hover = hover.concat("\n");
        hover = hover.concat("§6§lRejoins serveur le§r: " + DateFormated(date2));
        hover = hover.concat("\n");
        ComponentBuilder hoverText = new ComponentBuilder(hover);
        PlayerComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText.create()));
        return PlayerComponent;
    }

    public static void reconnect() {
        discordMain.api.addReconnectListener(event -> {
            messages.SendMessageToChannel("**Bot & serveur __reconnecté__** ! ⚡");
            messages.SendMessageToBukkit("§2Bot Discord reconnecté !");
        });
    }

    public static DateFormat dateFormat() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    public static String DateFormated(Date date) {
        return dateFormat().format(date);
    }
}