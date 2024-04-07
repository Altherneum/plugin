package altherneum.fr.entity;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import altherneum.fr.main.main;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.system.gold;
import altherneum.fr.world.api.worldManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class armorStand {
    static ArrayList<OfflinePlayer> listTopGold = new ArrayList<>();

    public static ArmorStand Summon(Location location, String text) {
        if (location != null) {
            if (location.getWorld() != null) {
                if (location.getChunk().isEntitiesLoaded() && location.getChunk().isLoaded()) {
                    ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location,
                            EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setCanPickupItems(false);
                    armorStand.setCollidable(false);
                    armorStand.setInvulnerable(true);
                    armorStand.setCustomName(text);
                    armorStand.setCustomNameVisible(true);
                    return armorStand;
                }
            }
        }
        return null;
    }

    public static ArmorStand summonTopGold(Location location, String text) {
        ArmorStand armorStand = Summon(location, text);
        entityPersistentData.setPersistentDataEntity(armorStand, entityPersistentData.customKey.topgold);
        entityPersistentData.setPersistentDataEntity(armorStand, entityPersistentData.customKey.custom);
        return armorStand;
    }

    public static void start() {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            try {
                // if world != null
                keepSpawnInMemory();
                listTopGold.clear();
                getTopGold();
                DeleteArmorStandOnSpawn();
                PlayerCount();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 20 * 10, 20 * 60);
    }

    public static void keepSpawnInMemory() {
        if (Bukkit.getWorld("Spawn") == null) {
            worldManager.Generate("Spawn", false, World.Environment.NORMAL, WorldType.FLAT, true);
        }
        World world = Bukkit.getWorld("Spawn");
        for (int x = -5; x < 10; x++) {
            for (int z = -5; z < 10; z++) {
                if (!world.isChunkForceLoaded(x, z)) {
                    world.setChunkForceLoaded(x, z, true);
                }
            }
        }
    }

    public static void DeleteArmorStandOnSpawn() {
        World world = Bukkit.getWorld("Spawn");
        List<Entity> list = world.getEntities();

        for (Entity entity : list) {
            if (entity.getType().equals(EntityType.ARMOR_STAND)) {
                if (entityPersistentData.isCustomEntity(entity)) {
                    if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.topgold)) {
                        ArmorStand armorStand = (ArmorStand) entity;
                        armorStand.remove();
                    }
                }
            }
        }
    }

    public static void getTopGold() {
        int topPlayerGold = 0;
        OfflinePlayer topPlayer = null;
        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            if (getDataStorage.playerExist(offlinePlayer) && !listTopGold.contains(offlinePlayer)) {
                if (topPlayerGold < gold.GetGold(offlinePlayer)) {
                    topPlayerGold = gold.GetGold(offlinePlayer);
                    topPlayer = offlinePlayer;
                }
            }
        }
        listTopGold.add(topPlayer);
        if (listTopGold.size() <= 2) {
            getTopGold();
        }
    }

    public static void PlayerCount() throws IOException {
        if (!listTopGold.isEmpty()) {
            Location location = getTopGoldLocation();
            if (location != null) {
                if (location.getWorld() != null) {
                    if (location.getChunk().isEntitiesLoaded() && location.getChunk().isLoaded()) {
                        summonTopGold(location, "§6Joueurs inscrits §r: §2" + Bukkit.getOfflinePlayers().length);
                        location.setY(location.getY() - 0.5);

                        summonTopGold(location, "§f< §6Top Gold§f >");
                        location.setY(location.getY() - 0.25);

                        OfflinePlayer player1 = listTopGold.get(0);
                        OfflinePlayer player2 = listTopGold.get(1);
                        OfflinePlayer player3 = listTopGold.get(2);

                        if (player1 != null) {
                            summonTopGold(location, "§4N°1 §f: §2" + player1.getName());
                            location.setY(location.getY() - 0.25);
                            summonTopGold(location, "§6" + gold.GetGoldFormat(player1) + " §fgold");
                            location.setY(location.getY() - 0.4);
                        }
                        if (player2 != null) {
                            summonTopGold(location, "§6N°2 §f: §2" + player2.getName());
                            location.setY(location.getY() - 0.25);
                            summonTopGold(location, "§6" + gold.GetGoldFormat(player2) + " §fgold");
                            location.setY(location.getY() - 0.4);
                        }
                        if (player3 != null) {
                            summonTopGold(location, "§eN°3 §f: §2" + player3.getName());
                            location.setY(location.getY() - 0.25);
                            summonTopGold(location, "§6" + gold.GetGoldFormat(player3) + " §fgold");
                        }
                    }
                }
            }
        }
    }

    public static Location getTopGoldLocation() {

        double x = 0.0;
        double y = 0.0;
        double z = 0.0;

        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            x = 28.0;
            y = 67;
            z = -75.5;
        } else {
            x = 0.5;
            y = 66;
            z = 19.5;
        }

        return new Location(Bukkit.getWorld("Spawn"), x, y, z);
    }
}