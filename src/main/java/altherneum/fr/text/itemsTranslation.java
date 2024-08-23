package altherneum.fr.text;

import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import altherneum.fr.menu.island.island;
import altherneum.fr.menu.prison.pickaxe;
import altherneum.fr.menu.prison.prisonData;
import altherneum.fr.menu.shop.specials.bonus.bonus;
import altherneum.fr.system.dateAPI;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.system.gold;
import altherneum.fr.system.security;
import altherneum.fr.system.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class itemsTranslation {
    public static String compassTitle() {
        return "§6§lMenu";
    }

    public static ArrayList<String> compassLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6menu";
        String commande1 = "/§6compass§f, §6compas";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fOuvrez §7votre §fmenu");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("§7" + commande1);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fOpen §7your §fmenu");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("§7" + commande1);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String spawnTitle() {
        return "§6§lSpawn";
    }

    public static ArrayList<String> spawnLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6Spawn";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fTéléportez §7vous au §fspawn");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fTeleport §7to the §fspawn");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String mainWorldTitle() {
        return "§6Monde principal";
    }

    public static String mainBedTitle() {
        return "§6Lit";
    }

    public static ArrayList<String> mainWorldLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6World (ToDo)";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fTéléportez §7vous au §fmonde principal");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fTeleport §7to the §fmain world");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static ArrayList<String> mainBedLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6Bed (ToDo)";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fTéléportez §7vous à votre §flit");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fTeleport §7to your §fbed");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String IleTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lÎle";
            case en:
                return "§6§lIsland";
            default:
                return "";
        }
    }

    public static ArrayList<String> IleLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6menu §f[§2ile§f, §2is§f, §2island§f]";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fOuvrez §7votre §fmenu d'île");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fOpen §7your §fisland menu");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String IleBonusTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lBonus d'île";
            case en:
                return "§6§lIsland bonus";
            default:
                return "";
        }
    }

    public static ArrayList<String> IleBonusLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6menu §f[§2bonus§f, §2boost§f, §2islandbonus§f, §2bonusile§f]";
        String commande1 = "/§6islandbonus§f, §6bonusile";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fOuvrez §7votre §fmenu de bonus d'île");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("§7" + commande1);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fOpen §7your §fisland bonus menu");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("§7" + commande1);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String questTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lQuêtes";
            case en:
                return "§6§lQuests";
            default:
                return "";
        }
    }

    public static ArrayList<String> questLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6menu §f[§2quetes§f, §2quete§f, §2quest§f, §2quests§f]";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fOuvrez §7votre §fmenu de quêtes");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fOpen §7your §fquests menu");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String shopTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lBoutique";
            case en:
                return "§6§lShop";
            default:
                return "";
        }
    }

    public static ArrayList<String> shopLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();

        String commande = "/§6menu §f[§2boutique§f, §2shop§f]";
        String commande1 = "/§6shop";
        String commande2 = "/§6shop §f[§2sellall§f, §2toutvendre§f]";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fOuvrez §7votre §fmenu de boutique");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("§7" + commande1);
                Lore.add("§7" + commande2);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fOpen §7your §fshop menu");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("§7" + commande1);
                Lore.add("§7" + commande2);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String shopEnchantTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lBoutique d'enchantements";
            case en:
                return "§6§lEnchantments shop";
            default:
                return "";
        }
    }

    public static ArrayList<String> shopEnchantLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();

        String commande = "/§6menu §f[§2enchants§f, §2enchantements§f]";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fOuvrez §7votre §fmenu de boutique d'enchantements");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fOpen §7your §fenchantments shop menu");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String teleporationTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lTéléportation";
            case en:
                return "§6§lTeleportation";
            default:
                return "";
        }
    }

    public static ArrayList<String> teleporationLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6menu §f[§2voyage§f, §2teleportation§f]";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fOuvrez §7votre §fmenu de téléportation");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fOpen §7your §fteleportation menu");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String IleCreateTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lCréer une Île";
            case en:
                return "§6§lCreate an Island";
            default:
                return "";
        }
    }

    public static ArrayList<String> IleCreateLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6ile§f, §6is§f, §6island";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§7Créer une §fnouvelle île");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§7Create a §fnew island");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String IleKickTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lExclure un joueur";
            case en:
                return "§6§lExclude a player";
            default:
                return "";
        }
    }

    public static ArrayList<String> IleKickLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6ile §f[§2kick§f, §2exclure§f, §2exclude§f]";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fExclure un joueur§7 de votre §fîle");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fExclude a player§7 from your §fisland");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String IleTPTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lSe téléporter à l'île";
            case en:
                return "§6§lTeleport to the island";
            default:
                return "";
        }
    }

    public static ArrayList<String> IleTPLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6ile§f, §6is§f, §6island";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fSe téléporter §7vers l'§fîle");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fTeleport §7to the §fisland");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String IleInviteTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lInviter un joueur";
            case en:
                return "§6§lInvite a player";
            default:
                return "";
        }
    }

    public static ArrayList<String> IleInviteLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6ile §f[§2invite§f, §2add§f, §2inviter§f]";
        String commande2 = "/§6ile §f[§2invite§f, §2add§f, §2inviter§f] ";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fInviter un joueur §7dans l'§fîle");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add(" §6~ §7" + commande2 + "§f<§2NOM§f>");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fInvite a player §7to the §fisland");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add(" §6~ §7" + commande2 + "§f<§2NAME§f>");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String IleLocateTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lChanger le point de réapparition de l'île";
            case en:
                return "§6§lChange the island's spawn point";
            default:
                return "";
        }
    }

    public static ArrayList<String> IleLocateLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6ile §f[§2locate§f, §2spawnpoint§f, §2location§f]";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fChanger le point de réapparition §7de l'île");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fChange the §7island's §fspawn point");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String IleOwnerTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lChef d'île";
            case en:
                return "§6§lLeader of the island";
            default:
                return "";
        }
    }

    public static ArrayList<String> IleOwnerLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6ile §f[§2owner§f, §2chef§f, §2leader§f]";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fDonner le rôle de chef §7d'île");
                Lore.add("§fOu revendiquer §7l'île");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fAssign the role of leader §7of the island");
                Lore.add("§fOr claim §7the island");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String IlePendingTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lListe des îles en attentes";
            case en:
                return "§6§lList of pending islands";
            default:
                return "";
        }
    }

    public static ArrayList<String> IlePendingLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6ile §f[§2join§f, §2rejoindre§f, §2pending§f]";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§7Choisissez une §fîle à rejoindre");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                Lore.add("§fClique {§2Gauche§f} : §6Rejoindre");
                Lore.add("§fClique {§4Droit§f} : §6Supprimer l'invitation");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§7Choose an §fisland to join");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                Lore.add("§f{§2Left§f} Click : §6Join");
                Lore.add("§f{§4Right§f} Click : §6Delete invitation");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String IleLeaveTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lQuitter l'île";
            case en:
                return "§6§lLeave the island";
            default:
                return "";
        }
    }

    public static ArrayList<String> IleLeaveLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6ile §f[§2leave§f, §2quitter§f]";
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fQuitter l'île");
                Lore.add("§fSi il n'y a plus personne§7, elle sera §4détruite");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fLeave the island");
                Lore.add("§fIf there is no one left§7, it will be §4destroyed");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String IleLeaveConfirmTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§4§l/!\\ §r§6Quitter l'île §4§l/!\\";
            case en:
                return "§4§l/!\\ §r§6Leave the island §4§l/!\\";
            default:
                return "";
        }
    }

    public static ArrayList<String> IleLeaveConfirmLore(lang.languages languages) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fQuitter l'île");
                Lore.add("§fSi il n'y a plus personne§7, elle sera §4détruite §4§l/!\\");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fLeave the island");
                Lore.add("§fIf there is no one left§7, it will be §4destroyed §4§l/!\\");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String IleMenuTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lÎle";
            case en:
                return "§6§lIsland";
            default:
                return "";
        }
    }

    public static String IleHead(lang.languages languages, OfflinePlayer player) {
        switch (languages) {
            case fr:
                return "§6Cliquez pour voire les informations";
            case en:
                return "§6Click to view information";
            default:
                return "";
        }
    }

    public static String IleHeadVisite(lang.languages languages, OfflinePlayer player) {
        switch (languages) {
            case fr:
                return "§6Cliquez pour visiter l'île du joueur";
            case en:
                return "§6Click to view the player island";
            default:
                return "";
        }
    }

    public static String ExcludeHead(lang.languages languages, OfflinePlayer player) {
        switch (languages) {
            case fr:
                return "§6Cliquez pour exclure le joueur";
            case en:
                return "§6Click to exclude player";
            default:
                return "";
        }
    }

    public static String InviteHead(lang.languages languages, OfflinePlayer player) {
        switch (languages) {
            case fr:
                return "§6Cliquez pour inviter le joueur";
            case en:
                return "§6Click to invite player";
            default:
                return "";
        }
    }

    public static String pendingHead(lang.languages languages, OfflinePlayer player) {
        switch (languages) {
            case fr:
                return "§6Cliquez pour rejoindre l'île du joueur";
            case en:
                return "§6Click to join the player's island";
            default:
                return "";
        }
    }

    public static String OwnerHead(lang.languages languages, OfflinePlayer player) {
        switch (languages) {
            case fr:
                return "§6Cliquez pour donner le rôle de chef d'île au joueur";
            case en:
                return "§6Click to assign the role of leader of the island to the player";
            default:
                return "";
        }
    }

    public static String Online(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§2En ligne";
            case en:
                return "§2Online";
            default:
                return "";
        }
    }

    public static String Offline(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§4Hors ligne";
            case en:
                return "§4Offline";
            default:
                return "";
        }
    }

    public static String InfoTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lInformations";
            case en:
                return "§6";
            default:
                return "";
        }
    }

    public static List<String> InfoLore(lang.languages languages, OfflinePlayer offlinePlayer) throws IOException {
        ArrayList<String> Lore = new ArrayList<String>();
        FileConfiguration fileConfiguration = getDataStorage.playerFileConfiguration(offlinePlayer);
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§6Joueur depuis §f: " + fileConfiguration.getString("FirstLogin") + "\n");
                Lore.add("§6Dernière visite §f: " + fileConfiguration.getString("LastLogin") + "\n");
                Lore.add("§6Nombres de connexions §f: " + fileConfiguration.getString("AmountLogin") + "\n");
                Lore.add("§2Heures de jeu §f: " + offlinePlayer.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20 / 3600);
                Lore.add("");
                Lore.add("§4Nombres de morts §f: " + offlinePlayer.getStatistic(Statistic.DEATHS));
                Lore.add("§2Heures depuis la dernière mort §f: "
                        + offlinePlayer.getStatistic(Statistic.TIME_SINCE_DEATH) / 20 / 3600);
                Lore.add("");
                Lore.add("§6Nombres de monstres tués §f: " + offlinePlayer.getStatistic(Statistic.MOB_KILLS));
                Lore.add("§6Nombres de joueurs tués §f: " + offlinePlayer.getStatistic(Statistic.PLAYER_KILLS));
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String PlayerDataTitle(lang.languages languages) {
        switch (languages) {
            case fr:
            case en:
                return "§6§lPLAY.altherneum.fr";
            default:
                return "";
        }
    }

    public static List<String> PlayerDataLore(lang.languages languages, OfflinePlayer offlinePlayer)
            throws IOException {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§6§lUUID §f: " + offlinePlayer.getUniqueId().toString());
                Lore.add("");
                if (offlinePlayer.isOnline()) {
                    Lore.add(itemsTranslation.Online(languages));
                } else {
                    Lore.add(itemsTranslation.Offline(languages));
                }
                Lore.add("");
                Lore.add(textTranslation.MenuGoldFormat(languages, offlinePlayer));
                Lore.add("");
                if (island.GetHasIsland(offlinePlayer)) {
                    if (island.GetIsIslandOwner(offlinePlayer)) {
                        Lore.add("§6Île §fN°§6" + island.GetIslandNumber(offlinePlayer) + " §2Chef d'île\n");
                    } else {
                        Lore.add("§6Île §fN°§6" + island.GetIslandNumber(offlinePlayer) + " \n");
                    }
                    Lore.add("");
                }
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§6§lUUID §f: " + offlinePlayer.getUniqueId().toString());
                Lore.add("");
                if (offlinePlayer.isOnline()) {
                    Lore.add(itemsTranslation.Online(languages));
                } else {
                    Lore.add(itemsTranslation.Offline(languages));
                }
                Lore.add("");
                Lore.add(textTranslation.MenuGoldFormat(languages, offlinePlayer));
                Lore.add("");
                if (island.GetHasIsland(offlinePlayer)) {
                    if (island.GetIsIslandOwner(offlinePlayer)) {
                        Lore.add("§6Island §fN°§6" + island.GetIslandNumber(offlinePlayer) + " §2Island leader\n");
                    } else {
                        Lore.add("§6Island §fN°§6" + island.GetIslandNumber(offlinePlayer) + " \n");
                    }
                    Lore.add("");
                }
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String TagsTitle(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6§lTags, Bonus, Sécurités";
            case en:
                return "§6§lTags, Bonus, Security";
            default:
                return "";
        }
    }

    public static List<String> TagsLore(lang.languages languages, OfflinePlayer offlinePlayer) throws IOException {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (languages) {
            case fr:
                Lore.add("");
                if (!tags.GetTag(offlinePlayer).isEmpty()) {
                    Lore.add("§6§lTags :");
                    Lore.add("§f" + tags.GetTag(offlinePlayer));
                    Lore.add("");
                }
                if (!bonus.GetBonus(offlinePlayer).isEmpty()) {
                    Lore.add("§6§lBonus :");
                    Lore.add("§f" + bonus.GetBonus(offlinePlayer));
                    Lore.add("");
                }
                if (!security.GetSecurity(offlinePlayer).isEmpty()) {
                    Lore.add("§6§lSécurités :");
                    Lore.add("§f" + security.GetSecurity(offlinePlayer));
                    Lore.add("");
                }
                return Lore;
            case en:
                Lore.add("");
                if (!tags.GetTag(offlinePlayer).isEmpty()) {
                    Lore.add("§6§lTags :");
                    Lore.add("§f" + tags.GetTag(offlinePlayer));
                    Lore.add("");
                }
                if (!bonus.GetBonus(offlinePlayer).isEmpty()) {
                    Lore.add("§6§lBonus :");
                    Lore.add("§f" + bonus.GetBonus(offlinePlayer));
                    Lore.add("");
                }
                if (!security.GetSecurity(offlinePlayer).isEmpty()) {
                    Lore.add("§6§lSecurity :");
                    Lore.add("§f" + security.GetSecurity(offlinePlayer));
                    Lore.add("");
                }
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> hubLore(lang.languages languages) throws IOException {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (languages) {
            case fr:
                Lore.add("");
                Lore.add("§fTéléporte §7vers le §fhub du serveur");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fTeleport §7to the §fserver's hub");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String hubTitle(lang.languages languages) {
        switch (languages) {
            case fr:
            case en:
                return "§6§lHub";
            default:
                return "";
        }
    }

    public static String CreatifTitle(lang.languages lang) {
        switch (lang) {
            case fr:
                return "§6§lCréatif";
            case en:
                return "§6§lCreative";
            default:
                return "";
        }
    }

    public static String AnarchieTitle(lang.languages lang) {
        switch (lang) {
            case fr:
            case en:
                return "§6§lAnarchie";
            default:
                return "";
        }
    }

    public static String RPGTitle(lang.languages lang) {
        switch (lang) {
            case fr:
            case en:
                return "§6§lRPG";
            default:
                return "";
        }
    }

    public static String SkyBlockTitle(lang.languages lang) {
        switch (lang) {
            case fr:
            case en:
                return "§6§lSkyBlock";
            default:
                return "";
        }
    }

    public static String SurvieTitle(lang.languages lang) {
        switch (lang) {
            case fr:
                return "§6§lSurvie";
            case en:
                return "§6§lSurvival";
            default:
                return "";
        }
    }

    public static String OPPrisonTitle(lang.languages lang) {
        switch (lang) {
            case fr:
            case en:
                return "§6§lOPPrison";
            default:
                return "";
        }
    }

    public static List<String> CreatifLore(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fTéléportez §7vous vers le serveur §2Créatif");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> CreatifLoreWhiteList(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fAccès réservé aux §6Builders");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> CreatifLoreInfo(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§4Serveur sous white liste");
                Lore.add("§7Permet au §fstaff §7de §6construire §7pour les autres serveurs");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> AnarchieLore(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fTéléportez §7vous vers le serveur §2Anarchie");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> AnarchieLoreInfo(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§7Ce serveur est une survie §4sans règles");
                Lore.add("§4Mode HardCore, PvP actif !");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> RPGLore(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fTéléportez §7vous vers le serveur §2RPG");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> RPGLoreInfo(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§4Serveur sous white liste");
                Lore.add("§7Le §2RPG §7est un serveur §fcustom");
                Lore.add("§4En développement / §2§lbuild");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> RPGLoreWhiteList(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fAccès réservé aux §6testeurs RPG");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> SkyBlockLore(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fTéléportez §7vous vers le serveur §2SkyBlock");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> SkyBlockLoreInfo(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§7SkyBlock au style §f\"§2old school§f\"");
                Lore.add(
                        "§7Faites votre île, §2allez dans un village, le nether, l'end...");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> SurvieLore(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fTéléportez §7vous vers le serveur §2Survie");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> SurvieLoreInfo(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§4Serveur sous white liste");
                Lore.add("§7Survie §2semi privée §fstyle HolyCube");
                Lore.add("§2Recrutement sur Discord via ticket");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> SurvieLoreWhiteList(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fAccès réservé aux §6membres de la survie");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> OPPrisonLore(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fTéléportez §7vous vers le serveur §2OPPrison");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> OPPrisonLoreInfo(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§4Serveur sous white liste");
                Lore.add("§7OPPrison §2ouverture bientôt");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> OPPrisonLoreWhiteList(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fAccès réservé aux §6membres de l'OPPrison");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String OnlineCount(lang.languages lang, int onlinePlayers, int maxPlayers) {
        return "§6" + onlinePlayers + "§f/§4" + maxPlayers + " §f" + textTranslation.joueurs(lang);
    }

    public static String lastSeen(lang.languages lang, Date date) {
        String Lore = "";
        switch (lang) {
            case fr:
                return "§4Dernier tick du serveur §r§f: §6" + dateAPI.DateFormated(date);
            case en:
                return "§4Last server tick §r§f: §6" + dateAPI.DateFormated(date);
            default:
                return Lore;
        }
    }

    public static String lastSeenNotFound(lang.languages lang) {
        String Lore = "";
        switch (lang) {
            case fr:
                return "§4Dernier tick du serveur inexistant !";
            case en:
                return "§4Last server tick not found !";
            default:
                return Lore;
        }
    }

    public static String MineNetheriteTitle(lang.languages lang) {
        switch (lang) {
            case fr:
            case en:
                return "§6§lNetherite";
            default:
                return "";
        }
    }

    public static String MineDiamondTitle(lang.languages lang) {
        switch (lang) {
            case fr:
            case en:
                return "§6§lDiamond";
            default:
                return "";
        }
    }

    public static String MineGoldTitle(lang.languages lang) {
        switch (lang) {
            case fr:
            case en:
                return "§6§lGold";
            default:
                return "";
        }
    }

    public static String MineIronTitle(lang.languages lang) {
        switch (lang) {
            case fr:
            case en:
                return "§6§lIron";
            default:
                return "";
        }
    }

    public static String MineStoneTitle(lang.languages lang) {
        switch (lang) {
            case fr:
            case en:
                return "§6§lStone";
            default:
                return "";
        }
    }

    public static int mineStone = 0;

    public static List<String> MineStoneLore(lang.languages lang, Boolean unlocked) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                if (unlocked) {
                    Lore.add("§fTéléportez vous vers la mine : " + MineStoneTitle(lang));
                } else {
                    Lore.add("§6Prix §f: §2" + mineStone);
                }
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static int mineIron = 5_000;

    public static List<String> MineIronLore(lang.languages lang, Boolean unlocked) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                if (unlocked) {
                    Lore.add("§fTéléportez vous vers la mine : " + MineIronTitle(lang));
                } else {
                    Lore.add("§6Prix §f: §2" + mineIron);
                }
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static int mineGold = 25_000;

    public static List<String> MineGoldLore(lang.languages lang, Boolean unlocked) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                if (unlocked) {
                    Lore.add("§fTéléportez vous vers la mine : " + MineGoldTitle(lang));
                } else {
                    Lore.add("§6Prix §f: §2" + mineGold);
                }
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static int mineDiamond = 100_000;

    public static List<String> MineDiamondLore(lang.languages lang, Boolean unlocked) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                if (unlocked) {
                    Lore.add("§fTéléportez vous vers la mine : " + MineDiamondTitle(lang));
                } else {
                    Lore.add("§6Prix §f: §2" + mineDiamond);
                }
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static int mineNetherite = 500_000;

    public static List<String> MineNetheriteLore(lang.languages lang, Boolean unlocked) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                if (unlocked) {
                    Lore.add("§fTéléportez vous vers la mine : " + MineNetheriteTitle(lang));
                } else {
                    Lore.add("§6Prix §f: §2" + mineNetherite);
                }
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> MineSelectorLore(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fTéléportez vous vers une §6mine");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String MineSelectorTitle(lang.languages lang) {
        switch (lang) {
            case fr:
            case en:
                return "§6§lMines";
            default:
                return "";
        }
    }

    public static List<String> YourPickaxeLore(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§6Améliorez §fvotre pioche via le §2PNJ au spawn");
                Lore.add("§fOu via [ §2/§6Pickaxe, §2/§6Pioche §f]");
                Lore.add("§fOu sinon en utilisant le §6clique droit dessus");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§6Upgrade §fyour pickaxes with the §2spawn NPC");
                Lore.add("§fOr with [ §2/§6Pickaxe, §2/§6Pioche §f]");
                Lore.add("§fOr else by §6right clicking it");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static List<String> PickaxeSelectorLore(lang.languages lang) {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§6Améliorez §fvotre pioche");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§6Upgrade §fyour pickaxes");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String PickaxeSelectorTitle(lang.languages lang) {
        switch (lang) {
            case fr:
                return "§6§lPioches";
            case en:
                return "§6§lPickaxes";
            default:
                return "";
        }
    }

    public static List<String> PickaxeUpgradeLore(lang.languages lang, Player player) throws IOException {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§6Améliorez §fvotre pioche");
                Lore.add("§4Prix §f: " + pickaxe.NextpickaxePrice(prisonData.getPickaxe(player)) + " §6gold");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§6Upgrade §fyour pickaxes");
                Lore.add("§4Price §f: " + pickaxe.NextpickaxePrice(prisonData.getPickaxe(player)) + " §6gold");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String PickaxeUpgradeTitle(lang.languages lang) {
        switch (lang) {
            case fr:
                return "§6§lMatériaux de la pioche";
            case en:
                return "§6§lPickaxe's materials";
            default:
                return "";
        }
    }

    public static List<String> PickaxeCantUpgradeLore(lang.languages lang) throws IOException {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§4Vous ne pouvez plus améliorer §fvotre pioche");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§4You can't anymore upgrade §fyour pickaxes");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String PickaxeCantUpgradeTitle(lang.languages lang) {
        switch (lang) {
            case fr:
                return "§6§lMatériaux de la pioche";
            case en:
                return "§6§lPickaxe's materials";
            default:
                return "";
        }
    }

    public static List<String> PickaxeEnchantLore(lang.languages lang, Player player, Enchantment enchantment)
            throws IOException {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§6Enchantez §fvotre pioche");
                Lore.add("§4Prix §f: " + gold.FormatInt(pickaxe.enchantPrice(pickaxe.enchantKey(enchantment),
                        prisonData.getEnchantLvl(player, enchantment))) + " §6gold");
                Lore.add("§6Niveau actuel §f: " + prisonData.getEnchantLvl(player, enchantment));
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§6Enchant §fyour pickaxes");
                Lore.add("§4Price §f: " + gold.FormatInt(pickaxe.enchantPrice(pickaxe.enchantKey(enchantment),
                        prisonData.getEnchantLvl(player, enchantment))) + " §6gold");
                Lore.add("§6Actual level §f: " + prisonData.getEnchantLvl(player, enchantment));
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String PickaxeEnchantTitle(lang.languages lang, Enchantment enchantment) {
        switch (lang) {
            case fr:
                return "§6§lEnchantement de la pioche";
            case en:
                return "§6§lPickaxe's enchantment";
            default:
                return "";
        }
    }

    public static List<String> SettingsLore(lang.languages lang)
            throws IOException {
        ArrayList<String> Lore = new ArrayList<String>();
        String commande = "/§6menu §f[§2....§f, §2....§f]";
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fOuvrez §7votre §fmenu des paramètres");
                Lore.add("§fCommande§7 : " + commande);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fOpen §7your §fsettings menu");
                Lore.add("§fCommand§7 : " + commande);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String SettingsTitle(lang.languages lang) {
        switch (lang) {
            case fr:
                return "§6§lParamètres";
            case en:
                return "§6§lSettings";
            default:
                return "";
        }
    }

    public static List<String> ResourcePackLore(lang.languages lang) throws IOException {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fCliquez pour §6télécharger le ressource pack");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fClick to §6download the resource pack");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String ResourcePackTitle(lang.languages lang) {
        switch (lang) {
            case fr:
                return "§6§lRessource pack";
            case en:
                return "§6§lResource pack";
            default:
                return "";
        }
    }

    public static List<String> LangLore(lang.languages lang) throws IOException {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fChangez la langue vers l'§6Anglais");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fChange the language to §6French");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String LangTitle(lang.languages lang) {
        switch (lang) {
            case fr:
                return "§6§lLangue";
            case en:
                return "§6§lLanguage ";
            default:
                return "";
        }
    }

    public static List<String> CompassPoseLore(lang.languages lang) throws IOException {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fChangez la position du menu principal (§6boussole§f)");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fChange the position of the main menu (§6compass§f)");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String CompassPoseTitle(lang.languages lang) {
        switch (lang) {
            case fr:
                return "§6§lPosition du menu";
            case en:
                return "§6§lMenu position";
            default:
                return "";
        }
    }

    public static List<String> ChatLore(lang.languages lang) throws IOException {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fCachez les messages de joueur dans le chat");
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fHide players message in chat");
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String ChatTitle(lang.languages lang) {
        switch (lang) {
            case fr:
                return "§6§lMessage des joueurs";
            case en:
                return "§6§lPlayers message";
            default:
                return "";
        }
    }

    public static List<String> anarchieLifeLore(lang.languages lang, Date date) throws IOException {
        ArrayList<String> Lore = new ArrayList<String>();
        switch (lang) {
            case fr:
                Lore.add("");
                Lore.add("§fVotre prochaine vie sera disponible le : §2§l" + date);
                Lore.add("");
                return Lore;
            case en:
                Lore.add("");
                Lore.add("§fYour next life will be available on : §2§l" + date);
                Lore.add("");
                return Lore;
            default:
                return Lore;
        }
    }

    public static String anarchieLifeTitle(lang.languages lang, boolean available) {
        switch (lang) {
            case fr:
                if (available) {
                    return "§6§lObtenez une nouvelle vie";
                } else {
                    return "§4§lProchaine vie dans 24h";
                }
            case en:
                if (available) {
                    return "§6§lGet a new life";
                } else {
                    return "§4§lNext life in 24 hours";
                }
            default:
                return "";
        }
    }
}