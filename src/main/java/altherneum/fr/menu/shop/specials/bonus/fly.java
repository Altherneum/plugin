package altherneum.fr.menu.shop.specials.bonus;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.text.ParseException;

public class fly {
    public static void setfly(Player player, String world) throws IOException, ParseException {
        if (player.getGameMode().equals(GameMode.SURVIVAL)) {
            if (world.startsWith("i.") || world.startsWith("Spawn")) {
                if (!bonus.isObsoletBonus(player, bonus.BonusList.Fly)) {
                    player.setAllowFlight(true);
                    player.setFlying(true);
                } else {
                    player.setAllowFlight(false);
                    player.setFlying(false);
                }
            } else {
                player.setAllowFlight(false);
                player.setFlying(false);
            }
        }
    }
}