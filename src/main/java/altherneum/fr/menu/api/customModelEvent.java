package altherneum.fr.menu.api;

import java.io.IOException;
import java.text.ParseException;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class customModelEvent implements Listener {
    @EventHandler
    public void PlayerUseCustomModel(PlayerInteractEvent e) throws IllegalArgumentException, IOException, ParseException {
        if ((e.getItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getItem(), persistentData.customKey.weapon) && !e.getItem().getItemMeta().hasUseCooldown()) {
                if(e.getItem().getDurability()>= 1 && e.getAction().isLeftClick()){
                    
                e.getPlayer().sendMessage("dura pre : " + e.getItem().getDurability());

                if(customModel.hasCustomModelString(e.getItem(), "ak47")){
                        e.setCancelled(true);

                        e.getPlayer().setCooldown(e.getItem(), customModel.coolDown("ak47"));
                        Projectile projectile = e.getPlayer().launchProjectile(Arrow.class); // Can use Fireball, Snowball, etc.

                        Vector velocity = e.getPlayer().getLocation().getDirection().multiply(3.0); // 3x speed
                        projectile.setVelocity(velocity);

                        projectile.setShooter(e.getPlayer());

                        e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), org.bukkit.Sound.UI_BUTTON_CLICK, 1.0f, 0.8f);

                        e.getItem().setDurability((short) (e.getItem().getDurability()+1));

                        e.getPlayer().updateInventory();
                    }
                }
                else if(e.getItem().getDurability()== 0 && (e.getAction().isLeftClick() || e.getAction().isRightClick())){
                    if(customModel.hasCustomModelString(e.getItem(), "ak47")){
                        e.getPlayer().setCooldown(e.getItem(), customModel.reloadCoolDown("ak47"));

                        e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), org.bukkit.Sound.ITEM_CROSSBOW_LOADING_START, 1.0f, 0.8f);
                        e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), org.bukkit.Sound.UI_BUTTON_CLICK, 1.0f, 0.8f);

                        e.getItem().setDurability((short) 0);

                        e.getPlayer().updateInventory();
                    }
                }

                e.getPlayer().sendMessage("after : " + e.getItem().getDurability());
            }
        }
    }
}
