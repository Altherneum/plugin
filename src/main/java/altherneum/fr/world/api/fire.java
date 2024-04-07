package altherneum.fr.world.api;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockSpreadEvent;

import java.io.IOException;

public class fire implements Listener {
    @EventHandler
    public void onBlockBurn(BlockBurnEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) throws IOException {
        if (e.getIgnitingEntity() instanceof Player && BuildRules.GetCanBuild(e.getPlayer())) {
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockSpread(BlockSpreadEvent e) {
        if (e.getNewState().getType().equals(Material.FIRE)) {
            e.setCancelled(true);
        }
    }
}