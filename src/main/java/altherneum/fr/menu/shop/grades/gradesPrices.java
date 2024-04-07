package altherneum.fr.menu.shop.grades;

import altherneum.fr.menu.api.persistentData;

public class gradesPrices {
    public static int prices(persistentData.customKey customKey) {
        switch (customKey) {
            case aventurier:
                return 15_000;
            case soldat:
                return 50_000;
            default:
                return Integer.MAX_VALUE;
        }
    }
}
