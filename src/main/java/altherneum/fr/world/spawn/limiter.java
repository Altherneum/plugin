package altherneum.fr.world.spawn;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.raid.RaidTriggerEvent;

public class limiter implements Listener {
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if (e.getLocation().getWorld().getName().startsWith("Spawn")
                || e.getLocation().getWorld().getName().startsWith("Mine.")) {
            if (!e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onRaid(RaidTriggerEvent e) {
        if (e.getWorld().getName().startsWith("Spawn")
                || e.getWorld().getName().startsWith("Mine.")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e) {
        if (e.getEntity().getWorld().getName().startsWith("Spawn")
                || e.getEntity().getWorld().getName().startsWith("Mine.")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity().getWorld().getName().startsWith("Spawn")
                || e.getEntity().getWorld().getName().startsWith("Mine")) {
            if (e.getEntityType().equals(EntityType.PLAYER)) {
                e.setCancelled(true);
            }
        }
    }
}