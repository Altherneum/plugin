package altherneum.fr.menu.island.bonus;

import altherneum.fr.menu.api.persistentData;

public class bonusPrice {
    public static int Price(persistentData.customKey key) {
        switch (key) {
            case bonusores:
                return 100;
            case bonusplayeramount:
            case bonusworldborder:
                return 250;
            case bonustickspeed:
                return 25000;
            default:
                return 0;
        }
    }
}