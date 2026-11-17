package altherneum.fr.world.api;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.block.Barrel;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import altherneum.fr.main.main;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.system.ServerBootFile.serverType;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class structure {
    public static void saveStructure(double x1, double x2, double y1, double y2, double z1, double z2, World world,
            String structureName) throws IOException {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    double xPos1 = Math.min(x1, x2);
                    double xPos2 = Math.max(x1, x2);
                    double yPos1 = Math.min(y1, y2);
                    double yPos2 = Math.max(y1, y2);
                    double zPos1 = Math.min(z1, z2);
                    double zPos2 = Math.max(z1, z2);
                    Vector minimumPoint = new Vector(xPos1, yPos1, zPos1);
                    Vector maximumPoint = new Vector(xPos2, yPos2, zPos2);

                    File file = getDataStorage.structureFile(structureName);
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

                    if (world != null) {
                        int blockCount = 0;
                        for (int x = minimumPoint.getBlockX(); x <= maximumPoint.getBlockX(); x++) {
                            for (int y = minimumPoint.getBlockY(); y <= maximumPoint.getBlockY()
                                    && y <= world.getMaxHeight(); y++) {
                                for (int z = minimumPoint.getBlockZ(); z <= maximumPoint.getBlockZ(); z++) {
                                    if (!world.getBlockAt(x, y, z).getType().equals(Material.AIR)
                                            && !world.getBlockAt(x, y, z).getType().equals(Material.VOID_AIR)
                                            && !world.getBlockAt(x, y, z).getType().equals(Material.CAVE_AIR)) {
                                        blockCount++;
                                        fileConfiguration.set(blockCount + ".type",
                                                world.getBlockAt(x, y, z).getType().toString());
                                        fileConfiguration.set(blockCount + ".location.x",
                                                world.getBlockAt(x, y, z).getLocation().getBlockX());
                                        fileConfiguration.set(blockCount + ".location.y",
                                                world.getBlockAt(x, y, z).getLocation().getBlockY());
                                        fileConfiguration.set(blockCount + ".location.z",
                                                world.getBlockAt(x, y, z).getLocation().getBlockZ());
                                        fileConfiguration.set(blockCount + ".data",
                                                world.getBlockAt(x, y, z).getBlockData().getAsString());
                                    }
                                }
                            }
                        }
                        if (blockCount > 0) {
                            fileConfiguration.set("blockCount", blockCount);
                            fileConfiguration.save(file);
                        } else {
                            file.delete();
                        }
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    public static void setCustomBlock(Block block) {
        if (block.getWorld().getName().startsWith("i.")) {
            if (block.getType().equals(Material.CHEST)) {
                Chest chest = (Chest) block.getState();
                ItemStack itemStack1 = new ItemStack(Material.OAK_SAPLING, 2);
                ItemStack itemStack2 = new ItemStack(Material.STICK, 10);
                ItemStack itemStack3 = new ItemStack(Material.OAK_LOG, 6);
                ItemStack itemStack4 = new ItemStack(Material.OAK_PLANKS, 36);
                ItemStack itemStack5 = new ItemStack(Material.LAVA_BUCKET, 1);
                chest.getInventory().addItem(itemStack1, itemStack2, itemStack3, itemStack4, itemStack5);
            }
            if (block.getType().equals(Material.BARREL)) {
                Barrel barrel = (Barrel) block.getState();
                ItemStack itemStack1 = new ItemStack(Material.WHEAT_SEEDS, 2);
                ItemStack itemStack2 = new ItemStack(Material.BREAD, 3);
                ItemStack itemStack3 = new ItemStack(Material.COOKED_BEEF, 16);
                ItemStack itemStack4 = new ItemStack(Material.WHEAT, 9);
                ItemStack itemStack5 = new ItemStack(Material.RED_BED, 1);
                barrel.getInventory().addItem(itemStack1, itemStack2, itemStack3, itemStack4, itemStack5);
            }
        }
    }

    public static void setStructure(String structureName, String worldName) throws IOException, ParseException {
        setStructure(structureName, worldName, 0, 0, 0, 0, 0, null);
    }

    public static void setStructure(String structureName, String worldName, Player player)
            throws IOException, ParseException {
        setStructure(structureName, worldName, 0, 0, 0, 0, 0, player);
    }

    public static void setStructure(String structureName, String worldName, int waitTime, int delay)
            throws IOException, ParseException {
        setStructure(structureName, worldName, 0, 0, 0, waitTime, delay, null);
    }

    public static void setStructure(String structureName, String worldName, int waitTime)
            throws IOException, ParseException {
        setStructure(structureName, worldName, 0, 0, 0, waitTime, 0, null);
    }

    public static void setStructure(String structureName, String worldName, int x, int y, int z)
            throws IOException, ParseException {
        setStructure(structureName, worldName, x, y, z, 0, 0, null);
    }

    public static void setStructure(String structureName, String worldName, int x, int y, int z, int waitTime)
            throws IOException, ParseException {
        setStructure(structureName, worldName, x, y, z, waitTime, 0, null);
    }

    public static void setStructure(String structureName, String worldName, int x, int y, int z, int waitTime,
            int delay)
            throws IOException, ParseException {
        setStructure(structureName, worldName, x, y, z, waitTime, delay, null);
    }

    public static void setStructure(String structureName, String worldName, int x, int y, int z, int waitTime,
            int delay,
            Player player) throws IOException, ParseException {

        Plugin plugin = JavaPlugin.getPlugin(main.class);

        File file = getDataStorage.structureFile(structureName);
        if (!file.exists()) {
            return;
        }

        World world = getOrCreateWorld(worldName);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        int blockCount = fileConfiguration.getInt("blockCount");
        if (blockCount <= 0) {
            return;
        }

        // Bornes des chunks couverts par la structure et par le point d'observation
        int[] chunks = chunkBounds(fileConfiguration, blockCount, x, z);

        // Un getBlockAt()/setBlockData() sur un chunk non chargé déclenche un
        // chargement synchrone (syncLoad) : le thread principal attend alors la
        // génération complète du chunk (biomes, structures, décorations) et le
        // serveur se bloque plusieurs secondes. Les chunks sont donc préparés
        // hors du thread principal puis maintenus chargés pendant la construction
        // (setChunkForceLoaded déclenche lui aussi un chargement synchrone).
        if (player != null && player.isOnline()) {
            // Le joueur rejoint tout de suite l'île en spectateur pour regarder la
            // construction : téléportation asynchrone, sans bloquer le thread principal
            player.teleportAsync(new Location(world, 0.5, 85, -15.5, 0, 53)).thenRun(
                    () -> Bukkit.getScheduler().runTask(plugin, () -> {
                        try {
                            teleportation.AfterTeleportation(player, worldName);
                        } catch (IOException | ParseException e) {
                            Bukkit.getLogger().warning("Structure " + structureName + " : téléportation impossible pour "
                                    + worldName + " : " + e);
                        }
                        player.setGameMode(GameMode.SPECTATOR);
                        player.setFlying(true);
                    }));
        }

        prepareChunks(world, chunks, () -> Bukkit.getScheduler().runTask(plugin, () -> {
            setChunksForceLoaded(world, chunks, true);
            startStructurePlacement(plugin, structureName, worldName, world, fileConfiguration, blockCount, chunks,
                    x, y, z, waitTime, delay, player);
        }));
    }

    /**
     * Bornes des chunks de la structure (index 0 et 1 : X, index 2 et 3 : Z).
     * Le point d'observation (0.5, 85, -15.5) est inclus d'office.
     */
    private static int[] chunkBounds(FileConfiguration fileConfiguration, int blockCount, int x, int z) {
        int minChunkX = 0;
        int maxChunkX = 0;
        int minChunkZ = -1;
        int maxChunkZ = -1;

        for (int index = 1; index <= blockCount; index++) {
            int blockX = fileConfiguration.getInt(index + ".location.x") + x;
            int blockZ = fileConfiguration.getInt(index + ".location.z") + z;
            minChunkX = Math.min(minChunkX, blockX >> 4);
            maxChunkX = Math.max(maxChunkX, blockX >> 4);
            minChunkZ = Math.min(minChunkZ, blockZ >> 4);
            maxChunkZ = Math.max(maxChunkZ, blockZ >> 4);
        }

        return new int[] { minChunkX, maxChunkX, minChunkZ, maxChunkZ };
    }

    private static void setChunksForceLoaded(World world, int[] chunks, boolean forceLoaded) {
        for (int chunkX = chunks[0]; chunkX <= chunks[1]; chunkX++) {
            for (int chunkZ = chunks[2]; chunkZ <= chunks[3]; chunkZ++) {
                world.setChunkForceLoaded(chunkX, chunkZ, forceLoaded);
            }
        }
    }

    /**
     * Charge les chunks de façon asynchrone puis exécute l'action, qui repasse
     * sur le thread principal.
     */
    private static void prepareChunks(World world, int[] chunks, Runnable action) {
        List<CompletableFuture<Chunk>> futures = new ArrayList<>();
        for (int chunkX = chunks[0]; chunkX <= chunks[1]; chunkX++) {
            for (int chunkZ = chunks[2]; chunkZ <= chunks[3]; chunkZ++) {
                futures.add(world.getChunkAtAsync(chunkX, chunkZ, true));
            }
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRun(action)
                .exceptionally(throwable -> {
                    Bukkit.getLogger().warning("Structure : chargement des chunks impossible pour le monde "
                            + world.getName() + " : " + throwable);
                    return null;
                });
    }

    private static void startStructurePlacement(Plugin plugin, String structureName, String worldName, World world,
            FileConfiguration fileConfiguration, int blockCount, int[] chunks, int x, int y, int z, int waitTime,
            int delay, Player player) {
        new BukkitRunnable() {

            int counter = 1;
            int counter2 = blockCount;

            @Override
            public void run() {
                try {
                    if (counter > counter2) {
                        if (player != null) {
                            if (player.isOnline()) {
                                teleportation.Teleport(player, worldName, false);
                            }
                        }

                        setChunksForceLoaded(world, chunks, false);
                        cancel();
                        return;
                    }

                    placeBlock(world, fileConfiguration, counter, x, y, z);
                    placeBlock(world, fileConfiguration, counter2, x, y, z);

                    counter++;
                    counter2--;
                } catch (Exception e) {
                    // Avant : exception ignorée, le même bloc était réessayé
                    // à chaque tick et la construction ne finissait jamais
                    Bukkit.getLogger().warning("Structure " + structureName + " interrompue sur le monde "
                            + worldName + " : " + e);
                    e.printStackTrace();
                    setChunksForceLoaded(world, chunks, false);
                    cancel();
                }
            }
        }.runTaskTimer(plugin, delay, waitTime);
    }

    private static World getOrCreateWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            world = worldManager.Generate(worldName, false, World.Environment.NORMAL, WorldType.NORMAL, true);
        }
        return world;
    }

    private static void placeBlock(World world, FileConfiguration fileConfiguration, int index, int x, int y, int z) {
        Block block = world.getBlockAt(fileConfiguration.getInt(index + ".location.x") + x,
                fileConfiguration.getInt(index + ".location.y") + y,
                fileConfiguration.getInt(index + ".location.z") + z);
        // setBlockData pose le type et l'état en une seule fois, sans physique
        // (pas de mise à jour des voisins ni de chute des blocs pendant la copie)
        block.setBlockData(Bukkit.createBlockData(fileConfiguration.getString(index + ".data")), false);
        setCustomBlock(block);
    }

    public static List<String> Structures() {
        List<String> argsToReturn = new ArrayList<String>();
        File file = getDataStorage.structureFolder();
        for (File files : file.listFiles()) {
            argsToReturn.add(files.getName().split(".yml")[0]);
        }
        return argsToReturn;
    }

    public static String SpawnName(serverType serverType) {
        switch (serverType) {
            case Hub:
            case Creatif:
            case RPG:
                return "spawn";
            case SkyBlock:
                return "spawn_SB";
            case Survie:
            case Anarchie:
                return "spawn_Survie";
            case OPPrison:
                return "spawn_OPPrison";
            default:
                return "spawn";
        }
    }
}