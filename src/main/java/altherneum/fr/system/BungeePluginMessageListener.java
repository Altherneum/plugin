package altherneum.fr.system;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import altherneum.fr.main.main;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeePluginMessageListener implements PluginMessageListener {
    public static JavaPlugin plugin = main.getPlugin(main.class);

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        /*
         * if (subchannel.equals("PlayerCount"))
         * {
         * String server = in.readUTF();
         * serverCount.put(server, in.readInt());
         * }
         */
    }

    public static void teleportPlayer(String serverName, Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    /*
     * public static void getCount(Player player, String server)
     * {
     * ByteArrayDataOutput out = ByteStreams.newDataOutput();
     * out.writeUTF("PlayerCount");
     * out.writeUTF(server);
     * player.sendPluginMessage(main.getPlugin(main.class), "BungeeCord",
     * out.toByteArray());
     * }
     */
}