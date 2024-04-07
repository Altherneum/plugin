package altherneum.fr.player;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import altherneum.fr.system.getDataStorage;

import java.io.File;
import java.io.IOException;

public class xpBonus implements Listener {
    @EventHandler
    public void PlayerLevelChange(PlayerLevelChangeEvent e) throws IOException {
        File file = getDataStorage.playerFile(e.getPlayer());
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set("Level", e.getPlayer().getLevel());
        fileConfiguration.save(file);
        SetHealthFromLVL(e.getPlayer());
        SetDamageFromLVL(e.getPlayer());
        SetArmorFromLVL(e.getPlayer());
    }

    public void SetHealthFromLVL(Player player) {
        AttributeModifier attribute = new AttributeModifier("VieLVL", player.getLevel() / 10,
                AttributeModifier.Operation.ADD_NUMBER);
        for (AttributeModifier am : player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getModifiers()) {
            if (am.getName().equals("VieLVL")) {
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).removeModifier(am);
            }
        }
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(attribute);
    }

    public void SetArmorFromLVL(Player player) {
        AttributeModifier attribute = new AttributeModifier("ArmorLVL", player.getLevel() / 20,
                AttributeModifier.Operation.ADD_NUMBER);
        for (AttributeModifier am : player.getAttribute(Attribute.GENERIC_ARMOR).getModifiers()) {
            if (am.getName().equals("ArmorLVL")) {
                player.getAttribute(Attribute.GENERIC_ARMOR).removeModifier(am);
            }
        }
        player.getAttribute(Attribute.GENERIC_ARMOR).addModifier(attribute);
    }

    public void SetDamageFromLVL(Player player) {
        AttributeModifier attribute = new AttributeModifier("DamageLVL", player.getLevel() / 20,
                AttributeModifier.Operation.ADD_NUMBER);
        for (AttributeModifier am : player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getModifiers()) {
            if (am.getName().equals("DamageLVL")) {
                player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).removeModifier(am);
            }
        }
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).addModifier(attribute);
    }
}