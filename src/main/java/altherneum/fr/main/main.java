package altherneum.fr.main;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import altherneum.fr.chat.playerChat;
import altherneum.fr.commands.admin.removePlayerAttribute;
import altherneum.fr.commands.admin.entity.*;
import altherneum.fr.commands.admin.playersStorage.bonus;
import altherneum.fr.commands.admin.playersStorage.security;
import altherneum.fr.commands.admin.playersStorage.tag;
import altherneum.fr.commands.admin.worldsRelated.*;
import altherneum.fr.commands.api.commandLimiter;
import altherneum.fr.commands.api.tabCompleterHidder;
import altherneum.fr.commands.api.test;
import altherneum.fr.commands.player.*;
import altherneum.fr.discord.discordMain;
import altherneum.fr.discord.eventsMinecraft;
import altherneum.fr.discord.messages;
import altherneum.fr.enchantements.instantmine;
import altherneum.fr.entity.clickNPC;
import altherneum.fr.menu.api.customInventoryClick;
import altherneum.fr.menu.api.persistentDataClick;
import altherneum.fr.menu.compass.compassEvents;
import altherneum.fr.menu.island.islandEvents;
import altherneum.fr.menu.island.islandHeadsEvents;
import altherneum.fr.menu.island.bonus.bonusEvents;
import altherneum.fr.menu.prison.EventLimiter;
import altherneum.fr.menu.prison.minesEvents;
import altherneum.fr.menu.prison.pickaxeEvents;
import altherneum.fr.menu.profil.profilEvents;
import altherneum.fr.menu.serverSelector.serverSelectorEvents;
import altherneum.fr.menu.settings.settingsEvents;
import altherneum.fr.menu.shop.classique.shopEvents;
import altherneum.fr.menu.shop.grades.gradesMenuEvent;
import altherneum.fr.menu.shop.specials.specialsMenuEvents;
import altherneum.fr.menu.shop.specials.specialsUseEvents;
import altherneum.fr.menu.shop.specials.bonus.joinQuitMessage;
import altherneum.fr.menu.shop.specials.bonus.teleportationAura;
import altherneum.fr.menu.voyage.voyageEvents;
import altherneum.fr.player.*;
import altherneum.fr.system.BossBarMessage;
import altherneum.fr.system.BungeePluginMessageListener;
import altherneum.fr.system.MOTD;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.world.portalEvent;
import altherneum.fr.world.api.BuildRules;
import altherneum.fr.world.api.entityGriefing;
import altherneum.fr.world.api.fire;
import altherneum.fr.world.api.voidWorld;
import altherneum.fr.world.api.worldManager;
import altherneum.fr.world.spawn.joinAtSpawn;
import altherneum.fr.world.spawn.limiter;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HexFormat;
import java.util.logging.Logger;

public class main extends JavaPlugin implements PluginMessageListener {

    private static final Logger logger = Bukkit.getLogger();

    public static String url = "https://mc.altherneum.fr/storage/zip/resourcePack.zip";
    public static String sha1;
    public static byte[] hashed;

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new voidWorld();
    }

    @Override
    public void onLoad() {
        logger.info("Plugin Loaded");
        try {
            ServerBootFile.loadServerTypeFile();
            logger.info(ServerBootFile.getServerType().toString());
        } catch (Exception e) {
        }
    }

    @Override
    public void onEnable() {
        logger.info("Plugin Enabled");
        try {
            discordMain.startBot();
            generateResourcePack();
        } catch (Exception e) {
        }
        Bukkit.getPluginManager().registerEvents(new eventsMinecraft(), this);
        registerEvents();
        //registerEnchant(); // to debug it crash due to already been registered
        registerCommands();
        lib.hook();
        scheduler.start();
        /*
         * 
         */
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            SkyBlockEventsCommands();
        } else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.RPG)) {
            Bukkit.getPluginManager().registerEvents(new xpBonus(), this);
        }
        /*
         *
         */
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord",
                new BungeePluginMessageListener());
    }

    private void generateResourcePack() throws IOException {
        sha1 = ResourcePackHandler.getSHA1(url);
        hashed = HexFormat.of().parseHex(sha1);
    }

    /* public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if (registered) {
            // It's been registered!
        }
    } */

    @Override
    public void onDisable() {
        logger.info("Plugin Disabled");
        try {
            messages.botStop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
         */
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
        /*
         */
    }

    /*
     * public void disableEnchant() {
     * try {
     * Field byKeyField = Enchantment.class.getDeclaredField("byKey");
     * Field byNameField = Enchantment.class.getDeclaredField("byName");
     * 
     * byKeyField.setAccessible(true);
     * byNameField.setAccessible(true);
     * 
     * HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey,
     * Enchantment>) byKeyField.get(null);
     * HashMap<NamespacedKey, Enchantment> byName = (HashMap<NamespacedKey,
     * Enchantment>) byNameField.get(null);
     * 
     * if (byKey.containsKey(instantmine.getKey())) {
     * byKey.remove(instantmine.getKey());
     * }
     * 
     * if (byName.containsKey(instantmine.getName())) {
     * byName.remove(instantmine.getName());
     * }
     * 
     * } catch (Exception ignored) {
     * 
     * }
     * }
     */

    public void SkyBlockEventsCommands() {
        Bukkit.getPluginCommand("island").setExecutor(new island());
        Bukkit.getPluginManager().registerEvents(new islandEvents(), this);
        Bukkit.getPluginManager().registerEvents(new islandHeadsEvents(), this);
        Bukkit.getPluginManager().registerEvents(new bonusEvents(), this);
        Bukkit.getPluginManager().registerEvents(new altherneum.fr.world.islandEvents(), this);
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new customInventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new serverSelectorEvents(), this);
        Bukkit.getPluginManager().registerEvents(new compassEvents(), this);
        Bukkit.getPluginManager().registerEvents(new persistentDataClick(), this);
        Bukkit.getPluginManager().registerEvents(new playerFirstJoin(), this);
        Bukkit.getPluginManager().registerEvents(new playerPreJoin(), this);
        Bukkit.getPluginManager().registerEvents(new limiter(), this);
        Bukkit.getPluginManager().registerEvents(new joinAtSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new entityGriefing(), this);
        Bukkit.getPluginManager().registerEvents(new MOTD(), this);
        Bukkit.getPluginManager().registerEvents(new teleportationAura(), this);
        Bukkit.getPluginManager().registerEvents(new joinQuitMessage(), this);
        Bukkit.getPluginManager().registerEvents(new playerChat(), this);
        Bukkit.getPluginManager().registerEvents(new death(), this);
        Bukkit.getPluginManager().registerEvents(new worldManager(), this);
        Bukkit.getPluginManager().registerEvents(new fire(), this);
        Bukkit.getPluginManager().registerEvents(new commandLimiter(), this);
        Bukkit.getPluginManager().registerEvents(new tabCompleterHidder(), this);
        Bukkit.getPluginManager().registerEvents(new anvilUnlimited(), this);
        Bukkit.getPluginManager().registerEvents(new clickNPC(), this);
        Bukkit.getPluginManager().registerEvents(new shopEvents(), this);
        Bukkit.getPluginManager().registerEvents(new specialsUseEvents(), this);
        Bukkit.getPluginManager().registerEvents(new specialsMenuEvents(), this);
        Bukkit.getPluginManager().registerEvents(new BuildRules(), this);
        Bukkit.getPluginManager().registerEvents(new voyageEvents(), this);
        Bukkit.getPluginManager().registerEvents(new profilEvents(), this);
        Bukkit.getPluginManager().registerEvents(new minesEvents(), this);
        Bukkit.getPluginManager().registerEvents(new pickaxeEvents(), this);
        Bukkit.getPluginManager().registerEvents(new BossBarMessage(), this);
        Bukkit.getPluginManager().registerEvents(new ResourcePackHandler(), this);
        Bukkit.getPluginManager().registerEvents(new settingsEvents(), this);
        Bukkit.getPluginManager().registerEvents(new EventLimiter(), this);
        Bukkit.getPluginManager().registerEvents(new gradesMenuEvent(), this);
        Bukkit.getPluginManager().registerEvents(new anarchieLife(), this);
        Bukkit.getPluginManager().registerEvents(new portalEvent(), this);
    }

    // public static final Enchantment INSTANTMINE = new instantmine(new
    // NamespacedKey(this, "instantmine"));

    /* public void registerEnchant() {
        instantmine instantmine = new instantmine("instantmine");
        registerEnchantment(instantmine);
        Bukkit.getPluginManager().registerEvents(instantmine, this);
    } */

    public void registerCommands() {
        registerAdminCommands();
        registerUserCommands();
    }

    public void registerAdminCommands() {
        Bukkit.getPluginCommand("test").setExecutor(new test());
        Bukkit.getPluginCommand("createworld").setExecutor(new worldCreate());
        Bukkit.getPluginCommand("unloadworld").setExecutor(new worldUnload());
        Bukkit.getPluginCommand("deleteworld").setExecutor(new worldDelete());
        Bukkit.getPluginCommand("setspawn").setExecutor(new setSpawn());
        Bukkit.getPluginCommand("bonus").setExecutor(new bonus());
        Bukkit.getPluginCommand("tag").setExecutor(new tag());
        Bukkit.getPluginCommand("security").setExecutor(new security());
        Bukkit.getPluginCommand("removeplayerattribute").setExecutor(new removePlayerAttribute());
        Bukkit.getPluginCommand("npc").setExecutor(new npc());
        Bukkit.getPluginCommand("armorstand").setExecutor(new armorStand());
        Bukkit.getPluginCommand("setspawner").setExecutor(new setSpawner());
        Bukkit.getPluginCommand("structuresave").setExecutor(new structureSave());
        Bukkit.getPluginCommand("structureset").setExecutor(new structureSet());
        Bukkit.getPluginCommand("structurelocate").setExecutor(new structureLocate());
        Bukkit.getPluginCommand("settag").setExecutor(new setTag());
        Bukkit.getPluginCommand("summon").setExecutor(new summon());
    }

    public void registerUserCommands() {
        Bukkit.getPluginCommand("spawn").setExecutor(new spawn());
        Bukkit.getPluginCommand("help").setExecutor(new help());
        Bukkit.getPluginCommand("commandes").setExecutor(new commandes());
        Bukkit.getPluginCommand("menu").setExecutor(new menu());
        Bukkit.getPluginCommand("profil").setExecutor(new profil());
        Bukkit.getPluginCommand("pay").setExecutor(new pay());
        Bukkit.getPluginCommand("money").setExecutor(new money());
        Bukkit.getPluginCommand("shopsell").setExecutor(new shopSell());
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        /*
         * if (subchannel.equals("SomeSubChannel"))
         * {
         * // Use the code sample in the 'Response' sections below to read
         * // the data.
         * }
         */
    }
}
