package altherneum.fr.world.api;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import altherneum.fr.system.ServerBootFile;

public class entityGriefing implements Listener {
    @EventHandler
    public void onEntityBreakDoor(EntityBreakDoorEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent e) {
        if (!(e.getEntity() instanceof FallingBlock) && !(e.getEntity() instanceof Villager) && !(e.getEntity() instanceof Player)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (ServerBootFile.serverTypeActual.equals(ServerBootFile.serverType.Survie)) {
            if (e.getEntity().getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                if (e.getEntityType().equals(EntityType.TNT)
                        || e.getEntityType().equals(EntityType.TNT_MINECART)) {
                    return;
                }
            }
        }
        e.blockList().clear();
    }

    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent e) {
        if (!e.getEntityType().equals(EntityType.PLAYER) && !e.getEntityType().equals(EntityType.FOX)
                && !e.getEntityType().equals(EntityType.VILLAGER) && !e.getEntityType().equals(EntityType.PIGLIN)
                && !e.getEntityType().equals(EntityType.PIGLIN_BRUTE) && !e.getEntityType().equals(EntityType.ALLAY)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityRightClickBed(PlayerInteractEvent e) {
        if (!ServerBootFile.serverTypeActual.equals(ServerBootFile.serverType.Survie)
                && !ServerBootFile.serverTypeActual.equals(ServerBootFile.serverType.Anarchie)) {
            if (e.getPlayer().getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (e.getClickedBlock().getType().toString().contains("_BED")) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}