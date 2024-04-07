package altherneum.fr.entity;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import altherneum.fr.main.main;

public class entityPersistentData {
    private static final JavaPlugin plugin = main.getPlugin(main.class);

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static NamespacedKey getKeyAsNameSpaced(entityPersistentData.customKey customKey) {
        return new NamespacedKey(getPlugin(), customKey.toString());
    }

    public static void setPersistentDataEntity(Entity entity, entityPersistentData.customKey customKey,
            PersistentDataType persistentDataType) {
        if (entity == null) {
            return;
        }
        PersistentDataContainer persistentDataContainer = entity.getPersistentDataContainer();
        persistentDataContainer.set(getKeyAsNameSpaced(customKey), persistentDataType, customKey.toString());
    }

    public static void setPersistentDataEntity(Entity entity, entityPersistentData.customKey customKey) {
        setPersistentDataEntity(entity, customKey, PersistentDataType.STRING);
    }

    public static Boolean hasPersistentDataEntity(Entity entity, entityPersistentData.customKey customKey,
            PersistentDataType persistentDataType) {
        if (entity == null) {
            return false;
        }
        PersistentDataContainer persistentDataContainer = entity.getPersistentDataContainer();
        if (persistentDataContainer.has(getKeyAsNameSpaced(customKey), persistentDataType)) {
            return persistentDataContainer.get(getKeyAsNameSpaced(customKey), persistentDataType)
                    .equals(customKey.toString());
        }
        return false;
    }

    public static Boolean hasPersistentDataEntity(Entity entity, customKey customKey) {
        return hasPersistentDataEntity(entity, customKey, PersistentDataType.STRING);
    }

    public static Boolean isCustomEntity(Entity entity) {
        return hasPersistentDataEntity(entity, customKey.custom);
    }

    public enum customKey {
        custom, topgold, commercant, guide, ile, quetes, mainmenu, villager, spawnvillager, tpworld, tpbed, mines,
        pickaxe
    }
}