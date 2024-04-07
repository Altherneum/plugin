package altherneum.fr.text;

import java.util.ArrayList;

import altherneum.fr.menu.api.persistentData.customKey;
import altherneum.fr.menu.shop.grades.gradesPrices;
import altherneum.fr.system.gold;

public class shopTranslation {
    public static String Escape(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§féchap pour quitter";
            case en:
                return "§fescape to quit";
            default:
                return "";
        }
    }

    public static String OtherClick(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§f(§6Autre clique §f: §2menu du shop§f)";
            case en:
                return "§f(§6Another click §f: §2shop menu§f)";
            default:
                return "";
        }
    }

    public static String LeftClick(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§fAchat : Clique {§4Gauche§f}";
            case en:
                return "§fBuy : Click {§4Left§f}";
            default:
                return "";
        }
    }

    public static String RightClick(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§fVente : Clique {§2Droit§f}";
            case en:
                return "§fSell : Click {§2Right§f}";
            default:
                return "";
        }
    }

    public static String BuyPrice(lang.languages languages, int prix) {
        switch (languages) {
            case fr:
                return "§fUnité : §4 - " + prix + textTranslation.gold(languages);
            case en:
                return "§fUnit : §4 - " + prix + textTranslation.gold(languages);
            default:
                return "";
        }
    }

    public static String BuyPriceBonus(lang.languages languages, int prix) {
        switch (languages) {
            case fr:
                return "§fPrix du bonus : §4 - " + gold.FormatInt(prix) + textTranslation.gold(languages);
            case en:
                return "§fBonus price : §4 - " + gold.FormatInt(prix) + textTranslation.gold(languages);
            default:
                return "";
        }
    }

    public static String SellPrice(lang.languages languages, int prix) {
        switch (languages) {
            case fr:
                return "§fUnité : §2 + " + prix / 10 + textTranslation.gold(languages);
            case en:
                return "§fUnit : §2 + " + prix / 10 + textTranslation.gold(languages);
            default:
                return "";
        }
    }

    public static String TotalSellPrice(lang.languages languages, int prix, int nombre) {
        switch (languages) {
            case fr:
            case en:
                return "§fTotal : §2 + " + gold.FormatInt(prix * nombre / 10) + textTranslation.gold(languages);
            default:
                return "";
        }
    }

    public static String TotalBuyPrice(lang.languages languages, int prix, int nombre) {
        switch (languages) {
            case fr:
            case en:
                return "§fTotal : §4 - " + gold.FormatInt(prix * nombre) + textTranslation.gold(languages);
            default:
                return "";
        }
    }

    public static String AddToCart(lang.languages languages, int nombre) {
        switch (languages) {
            case fr:
                return "§2Ajouter au panier §f+§2" + nombre;
            case en:
                return "§2Add to cart §f+§2" + nombre;
            default:
                return "";
        }
    }

    public static String RemoveFromCart(lang.languages languages, int nombre) {
        switch (languages) {
            case fr:
                return "§4Retirer du panier §f-§4" + nombre;
            case en:
                return "§4Remove to cart §f-§4" + nombre;
            default:
                return "";
        }
    }

    public static String AddToCartPrice(lang.languages languages, int prix, int nombre) {
        switch (languages) {
            case fr:
            case en:
                return "§2+ §6" + gold.FormatInt(prix * nombre) + " " + textTranslation.gold(languages);
            default:
                return "";
        }
    }

    public static String RemoveFromCartPrice(lang.languages languages, int prix, int nombre) {
        switch (languages) {
            case fr:
            case en:
                return "§4- §6" + gold.FormatInt(prix * nombre) + " " + textTranslation.gold(languages);
            default:
                return "";
        }
    }

    public static String Blocks(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§féchangez des §7blocs de construction";
            case en:
                return "§ftrade §7building blocks";
            default:
                return "";
        }
    }

    public static String BlocksTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Blocs";
            case en:
                return "§6Blocks";
            default:
                return "";
        }
    }

    public static String sellAllTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Tout vendre";
            case en:
                return "§6Sell all";
            default:
                return "";
        }
    }

    public static String sellAllLore(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Vendez votre inventaire §fau complet";
            case en:
                return "§6Sell your inventory §fcompletely";
            default:
                return "";
        }
    }

    public static String Graines(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§féchangez des §7graines";
            case en:
                return "§ftrade §7seeds";
            default:
                return "";
        }
    }

    public static String GrainesTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Graines";
            case en:
                return "§6Seeds";
            default:
                return "";
        }
    }

    public static String Minerais(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§féchangez des §7minerais";
            case en:
                return "§ftrade §7ores";
            default:
                return "";
        }
    }

    public static String MineraisTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Minerais";
            case en:
                return "§6Ores";
            default:
                return "";
        }
    }

    public static String Monstres(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§féchangez des §7reliques de monstres";
            case en:
                return "§ftrade §7monster relics";
            default:
                return "";
        }
    }

    public static String MonstresTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Monstres";
            case en:
                return "§6Monsters";
            default:
                return "";
        }
    }

    public static String Nourritures(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§féchangez de la §7nourriture";
            case en:
                return "§ftrade §7food";
            default:
                return "";
        }
    }

    public static String NourrituresTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Nourriture";
            case en:
                return "§6Food";
            default:
                return "";
        }
    }

    public static String Plantes(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§féchangez des §7plantes";
            case en:
                return "§ftrade §7plants";
            default:
                return "";
        }
    }

    public static String PlantesTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Plantes";
            case en:
                return "§6Plants";
            default:
                return "";
        }
    }

    public static String Speciaux(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§féchangez des §7objets spéciaux";
            case en:
                return "§ftrade §7special items";
            default:
                return "";
        }
    }

    public static String SpeciauxTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Spéciaux";
            case en:
                return "§6Specials";
            default:
                return "";
        }
    }

    public static String Oeufs(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§féchangez des §7oeufs";
            case en:
                return "§ftrade §7eggs";
            default:
                return "";
        }
    }

    public static String OeufsTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Oeufs";
            case en:
                return "§6Eggs";
            default:
                return "";
        }
    }

    public static String Aquatiques(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§féchangez des §7objets aquatiques";
            case en:
                return "§ftrade §7aquatic items";
            default:
                return "";
        }
    }

    public static String AquatiquesTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Aquatiques";
            case en:
                return "§6Aquatic";
            default:
                return "";
        }
    }

    public static String Nether(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§féchangez des §7objets du Nether";
            case en:
                return "§ftrade §7nether items";
            default:
                return "";
        }
    }

    public static String NetherTitle(lang.languages languages) {
        switch (languages) {
            case fr:
            case en:
                return "§6Nether";
            default:
                return "";
        }
    }

    public static String Teintures(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§féchangez des §7teintures";
            case en:
                return "§ftrade §7dyes";
            default:
                return "";
        }
    }

    public static String TeinturesTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Teintures";
            case en:
                return "§6Dyes";
            default:
                return "";
        }
    }

    public static String Custom(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§féchangez des §7objets spéciaux++";
            case en:
                return "§ftrade §7special items++";
            default:
                return "";
        }
    }

    public static String CustomTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Spéciaux++";
            case en:
                return "§6Specials++";
            default:
                return "";
        }
    }

    public static String EmplacementVide(lang.languages languages) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "Vous n'avez §4pas d'emplacement vide §r!";
            case en:
                return textTranslation.System(languages) + "You don't have §4empty space §r!";
            default:
                return "";
        }
    }

    public static String PasAssezArgent(lang.languages languages) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "Vous n'avez §4pas assez d'argent §r!";
            case en:
                return textTranslation.System(languages) + "You don't have §4enough money §r!";
            default:
                return "";
        }
    }

    public static String PasAssezObjetVendre(lang.languages languages) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "Vous n'avez §4pas assez d'objet à vendre §r!";
            case en:
                return textTranslation.System(languages) + "You don't have §4enough items to sell §r!";
            default:
                return "";
        }
    }

    public static String days(lang.languages languages) {
        switch (languages) {
            case fr:
                return " §23 jours";
            case en:
                return " §23 days";
            default:
                return "";
        }
    }

    public static String cliqueDroitSpecialsUse(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Utilisez cet objet avec §2clique droit";
            case en:
                return "§6Use this item with §2right click";
            default:
                return "";
        }
    }

    public static String VIPTitle(lang.languages languages) {
        switch (languages) {
            case fr:
            case en:
                return "§6VIP" + days(languages);
            default:
                return "";
        }
    }

    public static ArrayList<String> VIPLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(cliqueDroitSpecialsUse(languages));
        switch (languages) {
            case fr:
                Lore.add("§fPermet de §6rejoindre le serveur même complet !");
                return Lore;
            case en:
                Lore.add("§fAllows to §6join the server even full !");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static String JoinQuitTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Messages de connexion" + days(languages);
            case en:
                return "§6Connection messages" + days(languages);
            default:
                return "";
        }
    }

    public static ArrayList<String> JoinQuitLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(cliqueDroitSpecialsUse(languages));
        switch (languages) {
            case fr:
                Lore.add("§faffiche les §6messages de connexion");
                return Lore;
            case en:
                Lore.add("§fdisplay §6connection messages");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static String AuraTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Aura de téléportation" + days(languages);
            case en:
                return "§6Teleportation aura" + days(languages);
            default:
                return "";
        }
    }

    public static ArrayList<String> AuraLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(cliqueDroitSpecialsUse(languages));
        switch (languages) {
            case fr:
                Lore.add("§6Effet visuel §flors d'une téléportation");
                return Lore;
            case en:
                Lore.add("§6Visual effect §fwhen teleporting");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static String FlyTitle(lang.languages languages) {
        switch (languages) {
            case fr:
            case en:
                return "§6Fly" + days(languages);
            default:
                return "";
        }
    }

    public static ArrayList<String> FlyLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(cliqueDroitSpecialsUse(languages));
        switch (languages) {
            case fr:
                Lore.add("§fPermet de §6voler dans le spawn / îles ...");
                return Lore;
            case en:
                Lore.add("§fAllow to §6fly into the spawn / islands ...");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static String VenteAutoTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Vente automatique";
            case en:
                return "§6Automatic selling";
            default:
                return "";
        }
    }

    public static ArrayList<String> VenteAutoLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<>();
        Lore.add(cliqueDroitSpecialsUse(languages));
        switch (languages) {
            case fr:
                Lore.add("§6coffre §f/ §6tonneau §f/ §6hopper §f...");
                Lore.add("§fPour §2vendre toutes les 30 minutes");
                Lore.add("§f(§4Si l'île est chargée§f)");
                return Lore;
            case en:
                Lore.add("§6chest §f/ §6barrel §f/ §6hopper §f...");
                Lore.add("§fTo §2sell every 30 minutes");
                Lore.add("§f(§4If the island is loaded§f)");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static ArrayList<String> LVLBonusText(lang.languages languages, int lvlplayer, int lvlIsland) {
        ArrayList<String> Lore = new ArrayList<>();
        switch (languages) {
            case fr:
                Lore.add("§6LVL (vous) §f: §2" + lvlplayer);
                Lore.add("§6LVL (vous + île) §f: §2" + lvlIsland);
                return Lore;
            case en:
                Lore.add("§6LVL (you) §f: §2" + lvlplayer);
                Lore.add("§6LVL (you + island) §f: §2" + lvlIsland);
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static String color(int lvltotal, int needed) {
        if (lvltotal >= needed) {
            return "§2[√] ";
        } else {
            return "§4[X] ";
        }
    }

    public static String WorldBoarderTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Bordure du monde";
            case en:
                return "§6World border";
            default:
                return "";
        }
    }

    public static ArrayList<String> WorldBoarderLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<>();
        switch (languages) {
            case fr:
                Lore.add("§faugmente la taille du monde de §625 blocs");
                return Lore;
            case en:
                Lore.add("§fincrease the size of the world by §625 blocks");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static ArrayList<String> WorldBoarderLVL(lang.languages languages, int lvl, int islandLvl) {
        ArrayList<String> Lore = new ArrayList<>();
        Lore.addAll(LVLBonusText(languages, lvl, islandLvl));
        switch (languages) {
            case fr:
                Lore.add("§2" + islandLvl * 25 + " §fblocs");
                return Lore;
            case en:
                Lore.add("§2" + islandLvl * 25 + " §fblocks");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static String OresTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Générateur de minerais";
            case en:
                return "§6Ores generator";
            default:
                return "";
        }
    }

    public static ArrayList<String> OresLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<>();
        switch (languages) {
            case fr:
                Lore.add("§fDébloque de nouveaux minerais via §6les fermes à cobble");
                return Lore;
            case en:
                Lore.add("§fUnlock new ores through §6Cobble Farms");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static ArrayList<String> OresLoreLVL(lang.languages languages, int lvl, int islandLvl) {
        ArrayList<String> Lore = new ArrayList<>();
        Lore.addAll(LVLBonusText(languages, lvl, islandLvl));
        Lore.add(" ");
        switch (languages) {
            case fr:
                Lore.add(color(islandLvl, 0) + "§fLVL 0 : §6Cobble");
                Lore.add(color(islandLvl, 20) + "§fLVL 20 : §6Fer §f& §6charbon");
                Lore.add(color(islandLvl, 50) + "§fLVL 50 : §6Redstone §f& §6Lapis");
                Lore.add(color(islandLvl, 100) + "§fLVL 100 : §6Or §f& §6Cuivre");
                Lore.add(color(islandLvl, 250) + "§fLVL 250 : §6Émeraude");
                Lore.add(color(islandLvl, 500) + "§fLVL 500 : §6Pierres des abîmes");
                Lore.add(color(islandLvl, 500) + "§fLVL 500 : §6Diamant");
                Lore.add(color(islandLvl, 1000) + "§fLVL 1000 : §6Netherite");
                return Lore;
            case en:
                Lore.add(color(islandLvl, 0) + "§fLVL 0 : §6Cobble");
                Lore.add(color(islandLvl, 20) + "§fLVL 20 : §6Iron §f& §6coal");
                Lore.add(color(islandLvl, 50) + "§fLVL 50 : §6Redstone §f& §6Lapis");
                Lore.add(color(islandLvl, 100) + "§fLVL 100 : §6Gold §f& §6Copper");
                Lore.add(color(islandLvl, 250) + "§fLVL 250 : §6Emerald");
                Lore.add(color(islandLvl, 500) + "§fLVL 500 : §6Cobbled Deepslate");
                Lore.add(color(islandLvl, 500) + "§fLVL 500 : §6Diamond");
                Lore.add(color(islandLvl, 1000) + "§fLVL 1000 : §6Netherite");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static String PlayerAmountTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Nombres de joueurs maximum";
            case en:
                return "§6Maximum number of players";
            default:
                return "";
        }
    }

    public static ArrayList<String> PlayerAmountLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<>();
        switch (languages) {
            case fr:
                Lore.add("§fAjoute des emplacements pour de nouveaux coéquipier");
                return Lore;
            case en:
                Lore.add("§fAdd slots for new teammates");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static String joueur(lang.languages languages) {
        switch (languages) {
            case fr:
                return " joueurs";
            case en:
                return " players";
            default:
                return "";
        }
    }

    public static ArrayList<String> PlayerAmountLoreLVL(lang.languages languages, int lvl, int islandLvl) {
        ArrayList<String> Lore = new ArrayList<>();
        Lore.addAll(LVLBonusText(languages, lvl, islandLvl));
        Lore.add(" ");
        switch (languages) {
            case fr:
            case en:
                Lore.add(color(islandLvl, 0) + "§fLVL 0 : §62" + joueur(languages));
                Lore.add(color(islandLvl, 20) + "§fLVL 20 : §63" + joueur(languages));
                Lore.add(color(islandLvl, 50) + "§fLVL 50 : §64" + joueur(languages));
                Lore.add(color(islandLvl, 100) + "§fLVL 100 : §65" + joueur(languages));
                Lore.add(color(islandLvl, 250) + "§fLVL 250 : §66" + joueur(languages));
                Lore.add(color(islandLvl, 500) + "§fLVL 500 : §67" + joueur(languages));
                Lore.add(color(islandLvl, 1000) + "§fLVL 1000 : §68" + joueur(languages));
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static String TickSpeedTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Vitesse de tick aléatoire";
            case en:
                return "§6Random Tick Speed";
            default:
                return "";
        }
    }

    public static ArrayList<String> TickSpeedLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<>();
        switch (languages) {
            case fr:
                Lore.add("§fAccélérez la vitesse des ticks aléatoires pour votre île");
                return Lore;
            case en:
                Lore.add("§fSpeed up the Random tick speed for your island");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static ArrayList<String> TickSpeedLoreLVL(lang.languages languages, int lvl, int islandLvl) {
        ArrayList<String> Lore = new ArrayList<>();
        Lore.addAll(LVLBonusText(languages, lvl, islandLvl));
        Lore.add(" ");
        switch (languages) {
            case fr:
                Lore.add("§2LVL / 10 + 3 §f(Vitesse de base)");
                Lore.add("§2" + (islandLvl / 10 + 3) + " §fvitesse de tick aléatoire");
                return Lore;
            case en:
                Lore.add("§2LVL / 10 + 3 §f(Base speed)");
                Lore.add("§2" + (islandLvl / 10 + 3) + " §frandom tick speed");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static String aventurierTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Aventurier";
            case en:
                return "§6Adventurer";
            default:
                return "";
        }
    }

    public static ArrayList<String> aventurierLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<>();
        switch (languages) {
            case fr:
                Lore.add("§fDébloquez le grade : §6Aventurier");
                Lore.add("§4§lPrix §f: §4§l" + gradesPrices.prices(customKey.aventurier));
                Lore.add("§f- Accéder au §4nether");
                return Lore;
            case en:
                Lore.add("§fUnlock rank: §6Adventurer");
                Lore.add("§4§lPrice §f: §4§l" + gradesPrices.prices(customKey.aventurier));
                Lore.add("§f- Access the §4nether");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static String soldatTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Soldat";
            case en:
                return "§6Soldier";
            default:
                return "";
        }
    }

    public static ArrayList<String> soldatLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<>();
        switch (languages) {
            case fr:
                Lore.add("§fDébloquez le grade : §6Soldat");
                Lore.add("§4§lPrix §f: §4§l" + gradesPrices.prices(customKey.soldat));
                Lore.add("§f- Accéder à l'§5end");
                return Lore;
            case en:
                Lore.add("§fUnlock rank: §6Soldier");
                Lore.add("§4§lPrice §f: §4§l" + gradesPrices.prices(customKey.soldat));
                Lore.add("§f- Access the §5end");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }

    public static String gradeTitle(lang.languages languages) {
        switch (languages) {
            case fr:
            case en:
                return "§6Grades";
            default:
                return "";
        }
    }

    public static ArrayList<String> gradeLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<>();
        switch (languages) {
            case fr:
                Lore.add("§6Débloquez de nouveaux grades §fet bonus");
                return Lore;
            case en:
                Lore.add("§6Unlock new ranks §fand bonuses");
                return Lore;
            default:
                Lore.add("");
        }
        return Lore;
    }
}