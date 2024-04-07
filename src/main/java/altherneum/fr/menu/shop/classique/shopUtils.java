package altherneum.fr.menu.shop.classique;

import org.bukkit.Material;

import java.util.List;

public class shopUtils {
    public static int GetInventorySize(List<Material> materials) {
        if (materials.size() <= 9) {
            return 9;
        } else if (materials.size() > 9 && materials.size() <= 18) {
            return 18;
        } else if (materials.size() > 18 && materials.size() <= 27) {
            return 27;
        } else if (materials.size() > 27 && materials.size() <= 36) {
            return 36;
        } else if (materials.size() > 36 && materials.size() <= 45) {
            return 45;
        } else if (materials.size() > 45 && materials.size() <= 54) {
            return 54;
        }
        return 54;
    }
}