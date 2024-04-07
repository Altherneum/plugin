package altherneum.fr.world.api;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

import altherneum.fr.text.lang;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class worldManager implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void KeepSpawnInMemoryFalse(WorldInitEvent e) {
        e.getWorld().setKeepSpawnInMemory(false);
    }

    public static void setWorldRules(World world) {
        world.setKeepSpawnInMemory(false);
        // world.setAutoSave(false);
        GameRulesApply(world);
    }

    public static World Generate(String WorldName, Boolean structure, World.Environment environment,
            WorldType worldType, Boolean SkyBlock) {
        WorldCreator worldCreator = new WorldCreator(WorldName);
        worldCreator.environment(environment);
        worldCreator.type(worldType);
        worldCreator.generateStructures(structure);
        if (SkyBlock && structure) {
            worldCreator.generator(new SkyBlockWorld());
        } else if (SkyBlock && !structure) {
            worldCreator.generator(new voidWorld());
        }
        World world = worldCreator.createWorld();
        setWorldRules(world);
        TimeSync(world);
        Bukkit.getServer().getWorlds().add(world);
        return world;
    }

    public static void TimeSync(World world) {
        World defaultWorld = Bukkit.getServer().getWorlds().get(0);
        world.setTime(defaultWorld.getTime());
        world.setThunderDuration(defaultWorld.getThunderDuration());
        world.setWeatherDuration(defaultWorld.getWeatherDuration());
        world.setThundering(defaultWorld.isThundering());
        world.setStorm(defaultWorld.hasStorm());
    }

    public static void UnloadWorld(String worldName, Boolean save)
            throws IllegalArgumentException, IllegalStateException, IOException, ParseException {
        if (Bukkit.getWorld(worldName) == null) {
            return;
        }
        World world = Bukkit.getWorld(worldName);
        if (!world.getPlayers().isEmpty()) {
            for (Player player : world.getPlayers()) {
                lang.languages lang = playerLang.getPlayerLang(player);
                player.sendMessage(textTranslation.WorldUnloading(lang));
                teleportation.Teleport(player, "Spawn", true);
            }
            UnloadWorld(worldName, save);
        } else {
            Bukkit.unloadWorld(worldName, save);
        }
    }

    public static void DeleteWorld(String worldName)
            throws IllegalArgumentException, IllegalStateException, IOException, ParseException {
        File file = new File(Bukkit.getWorldContainer().getCanonicalPath(), worldName);
        if (file.exists()) {
            UnloadWorld(worldName, false);
            DeleteWorldFile(file);
        }
    }

    public static void DeleteWorld(String worldName, Boolean Normal, Boolean Nether, Boolean TheEnd)
            throws IllegalArgumentException, IllegalStateException, IOException, ParseException {
        if (Normal) {
            DeleteWorld(worldName);
        }
        if (Nether) {
            DeleteWorld(worldName + "_nether");
        }
        if (TheEnd) {
            DeleteWorld(worldName + "_the_end");
        }
    }

    public static void DeleteWorldFile(File path) {
        if (path.exists()) {
            for (File file : path.listFiles()) {
                if (file.isDirectory()) {
                    DeleteWorldFile(file);
                } else {
                    file.delete();
                }
            }
            path.delete();
        }
    }

    public static void GameRulesApply(World world) {
        if (world.getName().startsWith("Spawn")) {
            world.setGameRule(GameRule.KEEP_INVENTORY, true);
            world.setPVP(false);
            world.setGameRule(GameRule.NATURAL_REGENERATION, true);
            world.setDifficulty(Difficulty.PEACEFUL);
        } else if (world.getName().startsWith("i.")) {
            world.setGameRule(GameRule.KEEP_INVENTORY, true);
            world.setPVP(false);
            world.setGameRule(GameRule.NATURAL_REGENERATION, true);
            world.setDifficulty(Difficulty.HARD);
        } else if (world.getName().startsWith("RPG")) {
            if (world.getName().equals("RPG")) {
                world.setGameRule(GameRule.KEEP_INVENTORY, true);
                world.setPVP(false);
                world.setGameRule(GameRule.NATURAL_REGENERATION, true);
                world.setDifficulty(Difficulty.HARD);
            }
        }
    }
}