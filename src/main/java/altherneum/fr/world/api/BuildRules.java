package altherneum.fr.world.api;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;

import altherneum.fr.menu.island.island;
import altherneum.fr.system.ServerBootFile;
import altherneum.fr.system.gold;
import altherneum.fr.system.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildRules implements Listener {
    public static boolean GetCanBuild(Player player) throws IOException {
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            return island.IsInHerIsland(player) || tags.hasTags(player, tags.TagsList.Build);
        }

        else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Survie)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.Anarchie)) {
            return !player.getWorld().getName().equalsIgnoreCase("Spawn") || tags.hasTags(player, tags.TagsList.Build);
        }
        
        else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Hub)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.RPG)) {
            return tags.hasTags(player, tags.TagsList.Build);
        }
        

        else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Creatif)) {
            return tags.hasTags(player, tags.TagsList.Builder) || tags.hasTags(player, tags.TagsList.Build);
        }

        else {
            return tags.hasTags(player, tags.TagsList.Admin);
        }
    }

    public static int GetPrixInteract(Material material) {
        switch (material) {
            case ENDER_CHEST:
            case ENCHANTING_TABLE:
                return 50;
            case CRAFTING_TABLE:
                return 10;
            default:
                return 0;
        }
    }

    @EventHandler
    public void OnEntityDamage(EntityDamageByEntityEvent e) throws IOException {
        if (e.getDamager() instanceof Player && !GetCanBuild((Player) e.getDamager())) {
            e.setCancelled(true);
        } else if (e.getDamager() instanceof Projectile projectile) {
            if (projectile.getShooter() instanceof Player player) {
                if (!GetCanBuild(player)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void OnProjectileKill(ProjectileHitEvent e) throws IOException {
        Projectile projectile = e.getEntity();
        if (projectile.getShooter() instanceof Player player) {
            if (!GetCanBuild(player)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) throws IOException {
        if (!GetCanBuild(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBuild(BlockPlaceEvent e) throws IOException {
        if (!GetCanBuild(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemFrameBreak(HangingBreakEvent e) {
        if (!e.getCause().equals(HangingBreakEvent.RemoveCause.ENTITY)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemFrameBreakByPlayer(HangingBreakByEntityEvent e) throws IOException {
        if (e.getRemover() instanceof Player && !GetCanBuild((Player) e.getRemover())) {
            e.setCancelled(true);
        } else if (e.getRemover() instanceof Projectile projectile) {
            if (projectile.getShooter() instanceof Player player) {
                if (!GetCanBuild(player)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void OnEntityInteractDecoration(PlayerInteractEntityEvent e) throws IOException {
        if (e.getRightClicked().getType().equals(EntityType.ITEM_FRAME)
                || e.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {
            if (!GetCanBuild(e.getPlayer())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void OnVehicleDamage(VehicleDamageEvent e) throws IOException {
        if (e.getAttacker() instanceof Player && !GetCanBuild((Player) e.getAttacker())) {
            e.setCancelled(true);
        } else if (e.getAttacker() instanceof Projectile projectile) {
            if (projectile.getShooter() instanceof Player player) {
                if (!GetCanBuild(player)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void OnVehicleEnter(VehicleEnterEvent e) throws IOException {
        if (e.getEntered() instanceof Player player) {
            if (!GetCanBuild(player)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void OnVehicleEnter(VehicleEntityCollisionEvent e) throws IOException {
        if (e.getEntity() instanceof Player player) {
            if (!GetCanBuild(player)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlaceEntity(PlayerInteractEvent e) throws IOException {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            List<Material> ImpossibleEntity = new ArrayList<Material>();
            ImpossibleEntity.add(Material.ARMOR_STAND);
            ImpossibleEntity.add(Material.PAINTING);
            ImpossibleEntity.add(Material.ITEM_FRAME);
            if (e.getItem() != null) {
                if (ImpossibleEntity.contains(e.getItem().getType())
                        || e.getItem().getType().toString().contains("BOAT")
                        || e.getItem().getType().toString().contains("BUCKET")) {
                    if (!GetCanBuild(e.getPlayer())) {
                        e.setUseItemInHand(Event.Result.DENY);
                    }
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInteract(PlayerInteractEvent e) throws IOException {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            List<Material> PossibleBlock = new ArrayList<Material>();
            PossibleBlock.add(Material.CRAFTING_TABLE);
            PossibleBlock.add(Material.ENDER_CHEST);
            PossibleBlock.add(Material.ENCHANTING_TABLE);

            PossibleBlock.add(Material.SPRUCE_DOOR);
            PossibleBlock.add(Material.OAK_DOOR);
            PossibleBlock.add(Material.BIRCH_DOOR);
            PossibleBlock.add(Material.JUNGLE_DOOR);
            PossibleBlock.add(Material.SPRUCE_DOOR);
            PossibleBlock.add(Material.ACACIA_DOOR);
            PossibleBlock.add(Material.SPRUCE_DOOR);
            PossibleBlock.add(Material.DARK_OAK_DOOR);
            PossibleBlock.add(Material.CRIMSON_DOOR);
            PossibleBlock.add(Material.WARPED_DOOR);
            PossibleBlock.add(Material.BELL);

            if (!GetCanBuild(e.getPlayer())) {
                if (!PossibleBlock.contains(e.getClickedBlock().getType())) {
                    e.setUseInteractedBlock(Event.Result.DENY);
                } else {
                    if (gold.GetHasEnoughGold(e.getPlayer(), GetPrixInteract(e.getClickedBlock().getType()))
                            && !e.getPlayer().isSneaking()) {
                        if (GetPrixInteract(e.getClickedBlock().getType()) != 0) {
                            gold.RemoveGold(e.getPlayer(), GetPrixInteract(e.getClickedBlock().getType()));
                        }
                    } else {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}