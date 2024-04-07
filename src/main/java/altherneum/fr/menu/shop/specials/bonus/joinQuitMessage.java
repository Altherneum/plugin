package altherneum.fr.menu.shop.specials.bonus;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import altherneum.fr.chat.playerTextHover;

import java.io.IOException;
import java.text.ParseException;

public class joinQuitMessage implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws IOException, ParseException {
        e.setJoinMessage("");
        if (!bonus.isObsoletBonus(e.getPlayer(), bonus.BonusList.JoinQuitMessage)) {
            TextComponent message = new TextComponent();
            message.setText("§l[§a+§r§l] §r");
            for (Player receiver : Bukkit.getOnlinePlayers()) {
                TextComponent playerHover = playerTextHover.getHover(e.getPlayer(), receiver);
                receiver.spigot().sendMessage(message, playerHover);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) throws IOException, ParseException {
        e.setQuitMessage("");
        if (!bonus.isObsoletBonus(e.getPlayer(), bonus.BonusList.JoinQuitMessage)) {
            TextComponent message = new TextComponent();
            message.setText("§l[§c-§r§l] §r");
            for (Player receiver : Bukkit.getOnlinePlayers()) {
                TextComponent playerHover = playerTextHover.getHover(e.getPlayer(), receiver);
                receiver.spigot().sendMessage(message, playerHover);
            }
        }
    }
}