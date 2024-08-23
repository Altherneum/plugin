package altherneum.fr.world;

import java.io.IOException;
import java.text.ParseException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import altherneum.fr.system.ServerBootFile;
import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;
import altherneum.fr.world.api.worldManager;

public class portalEvent implements Listener {
    @EventHandler
    public void onPlayerTeleport(PlayerPortalEvent e) throws IOException, ParseException {
        Player player = e.getPlayer();
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Survie)|| ServerBootFile.getServerType().equals(ServerBootFile.serverType.Anarchie)) {

            Location to = e.getTo();
            Location from = e.getFrom();

            if (player.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
                if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)) {
                        worldManager.Generate(from.getWorld().getName() + "_nether", true, World.Environment.NETHER, WorldType.NORMAL, false);
                        to.setWorld(Bukkit.getWorld(from.getWorld().getName() + "_nether"));
                    }
                } else if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)) {
                        worldManager.Generate(from.getWorld().getName() + "_the_end", true, World.Environment.THE_END, WorldType.NORMAL, false);
                        to.setWorld(Bukkit.getWorld(from.getWorld().getName() + "_the_end"));
                }
            else if (player.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)) {
                    worldManager.Generate(from.getWorld().getName().replace("_nether", ""), true, World.Environment.NORMAL, WorldType.NORMAL, false);
                    to.setWorld(Bukkit.getWorld(from.getWorld().getName().replace("_nether", "")));
                }
            } else if (player.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)) {
                    worldManager.Generate(from.getWorld().getName().replace("_the_end", ""), true, World.Environment.NORMAL, WorldType.NORMAL, false);
                    to.setWorld(Bukkit.getWorld(from.getWorld().getName().replace("_the_end", "")));
                }
            }

            e.setTo(to);

            lang.languages lang = playerLang.getPlayerLang(e.getPlayer());
            player.sendMessage(textTranslation.Teleportation(lang, e.getTo().getWorld().getName()));
        }
    }
}
