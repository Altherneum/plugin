package altherneum.fr.menu.prison;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World; 
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import altherneum.fr.main.main;

public class mineOresGeneration {
    public static void startGeneration(String worldName) {
        generateOres(worldName);
    }

    public static void generateOres(String worldName) {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    World world = Bukkit.getWorld(worldName);

                    int x1 = 44;
                    int x2 = -44;
                    int y1 = -2;
                    int y2 = -41;
                    int z1 = 44;
                    int z2 = -44;

                    int xPos1 = Math.min(x1, x2);
                    int xPos2 = Math.max(x1, x2);
                    int yPos1 = Math.min(y1, y2);
                    int yPos2 = Math.max(y1, y2);
                    int zPos1 = Math.min(z1, z2);
                    int zPos2 = Math.max(z1, z2);

                    Vector minimumPoint = new Vector(xPos1, yPos1, zPos1);
                    Vector maximumPoint = new Vector(xPos2, yPos2, zPos2);

                    if (world != null) {

                        for (int x = minimumPoint.getBlockX(); x <= maximumPoint.getBlockX(); x++) {
                            for (int y = minimumPoint.getBlockY(); y <= maximumPoint.getBlockY()
                                    && y <= world.getMaxHeight(); y++) {
                                for (int z = minimumPoint.getBlockZ(); z <= maximumPoint.getBlockZ(); z++) {

                                    if (world.getBlockAt(x, y, z).getType().equals(Material.AIR)
                                            || world.getBlockAt(x, y, z).getType().equals(Material.VOID_AIR)
                                            || world.getBlockAt(x, y, z).getType().equals(Material.CAVE_AIR)) {
                                        world.getBlockAt(x, y, z).setType(getRandomBlock(worldName));
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimer(plugin, 100, 20*60*5);
    }

    public static Material getRandomBlock(String worldName) {
        Random random = new Random();
        int value = random.nextInt(100);

        if (worldName.equals("Mine.1")) {
            if (value >= 0 && value <= 80) {
                int value2 = random.nextInt(2) + 1;
                switch (value2) {
                    case 1:
                        return Material.STONE;
                    case 2:
                        return Material.COBBLESTONE;
                }
                return Material.STONE;
            } else if (value >= 81 && value <= 90) {
                return Material.COAL_ORE;
            } else if (value >= 91 && value <= 95) {
                return Material.IRON_ORE;
            } else if (value >= 96 && value <= 100) {
                return Material.REDSTONE_ORE;
            }
        }

        else if (worldName.equals("Mine.2")) {
            if (value >= 0 && value <= 70) {
                int value2 = random.nextInt(3) + 1;
                switch (value2) {
                    case 1:
                        return Material.STONE;
                    case 2:
                        return Material.COBBLESTONE;
                    case 3:
                        return Material.MOSSY_COBBLESTONE;
                }
                return Material.STONE;
            } else if (value >= 71 && value <= 75) {
                return Material.COAL_ORE;
            } else if (value >= 76 && value <= 85) {
                return Material.IRON_ORE;
            } else if (value >= 86 && value <= 95) {
                return Material.LAPIS_ORE;
            } else if (value >= 96 && value <= 100) {
                return Material.GOLD_ORE;
            }
        }

        else if (worldName.equals("Mine.3")) {
            if (value >= 0 && value <= 70) {
                int value2 = random.nextInt(5) + 1;
                switch (value2) {
                    case 1:
                        return Material.STONE;
                    case 2:
                        return Material.COBBLESTONE;
                    case 3:
                        return Material.MOSSY_COBBLESTONE;
                    case 4:
                        return Material.STONE_BRICKS;
                    case 5:
                        return Material.MOSSY_STONE_BRICKS;
                }
                return Material.STONE;
            } else if (value >= 71 && value <= 80) {
                return Material.IRON_ORE;
            } else if (value >= 81 && value <= 90) {
                return Material.LAPIS_ORE;
            } else if (value >= 91 && value <= 100) {
                return Material.GOLD_ORE;
            }
        }

        else if (worldName.equals("Mine.4")) {
            if (value >= 0 && value <= 80) {
                int value2 = random.nextInt(5) + 1;
                switch (value2) {
                    case 1:
                        return Material.COBBLED_DEEPSLATE;
                    case 2:
                        return Material.DEEPSLATE;
                    case 3:
                        return Material.MOSSY_COBBLESTONE;
                    case 4:
                        return Material.STONE_BRICKS;
                    case 5:
                        return Material.MOSSY_STONE_BRICKS;
                }
                return Material.STONE;
            } else if (value >= 81 && value <= 90) {
                return Material.GOLD_ORE;
            } else if (value >= 91 && value <= 95) {
                return Material.DIAMOND_ORE;
            } else if (value >= 96 && value <= 100) {
                return Material.LAPIS_ORE;
            }
        }

        else if (worldName.equals("Mine.5")) {
            if (value >= 0 && value <= 80) {
                int value2 = random.nextInt(5) + 1;
                switch (value2) {
                    case 1:
                        return Material.DEEPSLATE;
                    case 2:
                        return Material.DEEPSLATE_BRICKS;
                    case 3:
                        return Material.COBBLED_DEEPSLATE;
                    case 4:
                        return Material.STONE_BRICKS;
                    case 5:
                        return Material.MOSSY_STONE_BRICKS;
                }
                return Material.STONE;
            } else if (value >= 81 && value <= 90) {
                return Material.DEEPSLATE_DIAMOND_ORE;
            } else if (value >= 91 && value <= 95) {
                return Material.ANCIENT_DEBRIS;
            } else if (value >= 96 && value <= 100) {
                return Material.DEEPSLATE_EMERALD_ORE;
            }
        }

        return Material.AIR;
    }
}
