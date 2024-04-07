package altherneum.fr.player;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HexFormat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;

import altherneum.fr.main.main;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.system.ServerBootFile.serverType;

public class ResourcePackHandler implements Listener {
    public static String url = main.url;
    public static String sha1 = main.sha1;
    public static byte[] hashed = main.hashed;
    public static String text = "§4§lUtilisation du resource pack custom §r§f..."
            + "\n§fRôles, monstres, tags, armes & fusils...";
    public static Boolean force = false;

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) throws IOException {
        if (sha1 == null || sha1 == "") {
            sha1 = getSHA1(url);
        }

        serverType serverTypes = ServerBootFile.getServerTypeFromYML();
        if (serverTypes.equals(serverType.Hub)) {
            setResourcePack(e.getPlayer(), url, sha1, text, force);
        }//Fix if player join directly on other server
          //Add on a list when player is resourcePacked
        //Check if on list before re run resource pack apply
    }

    public static void setResourcePack(Player player, String url, String hash, String text, boolean force) {
        if (hashed == null || hashed.length == 0) {
            hashed = HexFormat.of().parseHex(hash);
        }

        player.setResourcePack(url, hashed, text, force);
    }

    ArrayList<String> queu = new ArrayList<>();
    ArrayList<String> downloaded = new ArrayList<>();

    @EventHandler
    public void ResourcePackStatusEvent(PlayerResourcePackStatusEvent e) {
        e.getStatus();
        if (e.getStatus().equals(Status.ACCEPTED)) {
            if (!queu.contains(e.getPlayer().getUniqueId().toString())
                    && !downloaded.contains(e.getPlayer().getUniqueId().toString())) {
                queu.add(e.getPlayer().getUniqueId().toString());
                //setResourcePack(e.getPlayer(), url, sha1, text, true);
            }
        }

        else if (e.getStatus().equals(Status.DECLINED)) {
            if (force) {
                e.getPlayer().kick();
            }
        }

        else if (e.getStatus().equals(Status.FAILED_DOWNLOAD)) {
            setResourcePack(e.getPlayer(), url, sha1, text, true);
        }

        else if (e.getStatus().equals(Status.SUCCESSFULLY_LOADED)) {
            queu.remove(e.getPlayer().getUniqueId().toString());
            downloaded.add(e.getPlayer().getUniqueId().toString());
        }
    }

    public static String getSHA1(String url) throws IOException {
        try {
            final URLConnection urlConnection = new URL(url).openConnection();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            InputStream fis = urlConnection.getInputStream();
            byte[] dataBytes = new byte[1024];

            int nread = 0;

            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            ;

            byte[] mdbytes = md.digest();

            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}