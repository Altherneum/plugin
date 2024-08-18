package altherneum.fr.menu.shop.specials.bonus;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import altherneum.fr.main.main;

import java.io.IOException;
import java.text.ParseException;

public class teleportationAura implements Listener {
    @EventHandler
    public void FireWorkDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Firework fw) {
            if (fw.hasMetadata("nodamage")) {
                e.setCancelled(true);
            }
        }
    }

    public static void FireWorkSpawn(Player player)
            throws IllegalArgumentException, IllegalStateException, IOException, ParseException {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        if (!bonus.isObsoletBonus(player, bonus.BonusList.AuraTP)) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Location location = player.getLocation();
                    Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK_ROCKET);
                    FireworkMeta fireWorkMeta = firework.getFireworkMeta();

                    fireWorkMeta.setPower(0);
                    fireWorkMeta.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());

                    firework.setFireworkMeta(fireWorkMeta);
                    firework.setMetadata("nodamage", new FixedMetadataValue(plugin, true));
                    firework.detonate();
                }
            }.runTaskLater(plugin, 5);
        }
    }
}