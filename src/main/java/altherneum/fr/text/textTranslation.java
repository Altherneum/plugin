package altherneum.fr.text;

import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import altherneum.fr.system.gold;

import java.io.IOException;

public class textTranslation {
    public static String System(lang.languages languages) {
        switch (languages) {
            case fr:
                return "[§6§lSystème§r] ";
            case en:
                return "[§6§lSystem§r] ";
            default:
                return "";
        }
    }

    public static String newLine() {
        return "§6* §r";
    }

    public static String commandAdmin(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "§4Commande administrateur";
            case en:
                return System(languages) + "§4Administrator command";
            default:
                return "";
        }
    }

    public static String Teleportation(lang.languages languages, String nom) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "Téléportation vers : §" + WorldColorCode(nom) + "§l" + nom;
            case en:
                return textTranslation.System(languages) + "Teleport to : §" + WorldColorCode(nom) + "§l" + nom;
            default:
                return "";
        }
    }

    public static String MenuGoldFormat(lang.languages lang, OfflinePlayer offlinePlayer) {
        switch (lang) {
            case fr:
            case en:
                return " §f~ " + gold.GetGoldFormat(offlinePlayer) + textTranslation.gold(lang);
            default:
                return "";
        }
    }

    public static char WorldColorCode(String nom) {
        if (nom.startsWith("i.")) {
            return '2';
        } else if (nom.startsWith("Spawn")) {
            return '6';
        } else if (nom.startsWith("RPG")) {
            return '4';
        }
        return 'r';
    }

    public static char DifficultyColorCode(Difficulty difficulty) {
        if (difficulty.equals(Difficulty.EASY)) {
            return '2';
        } else if (difficulty.equals(Difficulty.NORMAL)) {
            return '6';
        } else if (difficulty.equals(Difficulty.HARD)) {
            return '4';
        }
        return 'r';
    }

    public static void GameRulesMessage(World world, Player player) throws IOException {
        lang.languages lang = playerLang.getPlayerLang(player);
        if (world.getName().startsWith("i.")) {
            player.sendMessage(newLine() + "Seed §r" + world.getSeed());
        }

        player.sendMessage(newLine() + world.getPlayers().size() + " " + joueurs(lang) + "§r");
        String Rules = newLine() + "§" + DifficultyColorCode(world.getDifficulty()) + world.getDifficulty().name()
                + "§r, ";
        if (world.getGameRuleValue(GameRule.KEEP_INVENTORY)) {
            Rules += inventory(lang) + " [§2OFF§r], ";
        } else {
            Rules += inventory(lang) + " [§4ON§r], ";
        }

        if (world.getGameRuleValue(GameRule.NATURAL_REGENERATION)) {
            Rules += regeneration(lang) + " [§2ON§r], ";
        } else {
            Rules += regeneration(lang) + " [§4OFF§r], ";
        }

        if (world.getPVP()) {
            Rules += "§4PvP";
        } else {
            Rules += "§2PvE";
        }
        player.sendMessage(Rules);
    }

    public static String unloadedIslands(lang.languages languages) {
        switch (languages) {
            case fr:
                return "Îles déchargés";
            case en:
                return "Unloaded islands";
            default:
                return "";
        }
    }

    public static String IslandUnloaded(lang.languages languages, int i, int i2) {
        return textTranslation.System(languages) + "§4" + textTranslation.unloadedIslands(languages) + " §r: §6" + i
                + "§r/§6" + i2;
    }

    public static String joueurs(lang.languages languages) {
        switch (languages) {
            case fr:
                return "joueurs";
            case en:
                return "players";
            default:
                return "";
        }
    }

    public static String inventory(lang.languages languages) {
        switch (languages) {
            case fr:
                return "Perte d'inventaire";
            case en:
                return "Inventory loss";
            default:
                return "";
        }
    }

    public static String regeneration(lang.languages languages) {
        switch (languages) {
            case fr:
                return "Régénération";
            case en:
                return "Regeneration";
            default:
                return "";
        }
    }

    public static String WorldUnloading(lang.languages languages) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "Déchargement du monde";
            case en:
                return textTranslation.System(languages) + "Unloading world";
            default:
                return "";
        }
    }

    public static String addBonus(lang.languages languages, String bonusName, int day) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "Vous §2avez le bonus §r[ §6" + bonusName
                        + " §r], pendant §2" + day + " jours";
            case en:
                return textTranslation.System(languages) + "You §2have the bonus §r[ §6" + bonusName + " §r], for §2"
                        + day + " days";
            default:
                return "";
        }
    }

    public static String removeBonus(lang.languages languages, String bonusName) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "Vous §4n'avez plus le bonus §r[ §6" + bonusName + " §r]";
            case en:
                return textTranslation.System(languages) + "You §4no longer have the bonus §r[ §6" + bonusName + " §r]";
            default:
                return "";
        }
    }

    public static String addTags(lang.languages languages, String tagName) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "Vous §2avez le tag §r[ §6" + tagName + " §r]";
            case en:
                return textTranslation.System(languages) + "You §2have the tag §r[ §6" + tagName + " §r]";
            default:
                return "";
        }
    }

    public static String removeTags(lang.languages languages, String tagName) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "Vous §4n'avez plus le tag §r[ §6" + tagName + " §r]";
            case en:
                return textTranslation.System(languages) + "You §4no longer have the tag §r[ §6" + tagName + " §r]";
            default:
                return "";
        }
    }

    public static String addSecurity(lang.languages languages, String securityName, int Day) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "Vous §4avez la sécurité §r[ §6" + securityName
                        + " §r], pendant §4" + Day + " jours";
            case en:
                return textTranslation.System(languages) + "You §4have the security §r[ §6" + securityName
                        + " §r], for §4" + Day + " days";
            default:
                return "";
        }
    }

    public static String addBan(lang.languages languages, int Day) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "Vous avez été §4banni pendant " + Day + " jours";
            case en:
                return textTranslation.System(languages) + "You have been §4banned for " + Day + " days";
            default:
                return "";
        }
    }

    public static String addWarn(lang.languages languages) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages)
                        + "Vous §4serez banni pendant §61 jours §rsi vous êtes Warn à nouveau !";
            case en:
                return textTranslation.System(languages)
                        + "You §4will be banned for §61 day §rif you are warned again !";
            default:
                return "";
        }
    }

    public static String removeSecurity(lang.languages languages, String securityName) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "Vous §2n'avez plus la sécurité §r[ §6" + securityName
                        + " §r]";
            case en:
                return textTranslation.System(languages) + "You §2no longer have the security §r[ §6" + securityName
                        + " §r]";
            default:
                return "";
        }
    }

    public static String Welcome(lang.languages languages, String playerName) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "§2§lAccueillez chaleureusement §r§6" + playerName;
            case en:
                return textTranslation.System(languages) + "§2§lPlease give a warm welcome to §r§6" + playerName;
            default:
                return "";
        }
    }

    public static String Beta(lang.languages languages) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages)
                        + "§2Bravo ! §6Vous avez gagné §r5'000 Or §6& le tag [§r Beta testeur §6] §2pour votre participation à la béta §r! §lMerci";
            case en:
                return textTranslation.System(languages)
                        + "§2Congratulations ! §6You won §r5,000 Gold §6& the [§r Beta tester §6] tag §2for your participation in the beta§r ! §lThank you";
            default:
                return "";
        }
    }

    public static String goToPNJ(lang.languages languages) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "§2Va parler au PNJ §6pour découvrir le serveur !";
            case en:
                return textTranslation.System(languages) + "§2Go talk to the NPC §6to discover the server !";
            default:
                return "";
        }
    }

    public static String serveurFull(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§4§lServeur complet §r!\nAchetez le §2bonus VIP§r pour §6rejoindre même au complet §r!";
            case en:
                return "§4§lServer is full §r!\nBuy the §2VIP bonus§r to §6join even full§r!";
            default:
                return "";
        }
    }

    public static String banned(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§4Vous êtes §lbanni §r§4!\n§6Discord §rpour toutes demandes Altherneum.fr/discord";
            case en:
                return "§4You are §lbanned §r§4!\n§6Discord §rfor all requests Altherneum.fr/discord";
            default:
                return "";
        }
    }

    public static String Whitelist(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§4Vous êtes §lpas sous white list §r§4!\n§6Discord §rpour toutes demandes Altherneum.fr/discord";
            case en:
                return "§4You are §lnot whitelisted §r§4!\n§6Discord §rfor all requests Altherneum.fr/discord";
            default:
                return "";
        }
    }

    public static String WelcomeBack(lang.languages languages, String playerName) {
        switch (languages) {
            case fr:
                return "§6§lBon retour §r" + playerName + " §2sur §laltherneum.fr";
            case en:
                return "§6§lWelcome back §r" + playerName + " §2on §laltherneum.fr";
            default:
                return "";
        }
    }

    public static String mute(lang.languages languages) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "§4Vous êtes muet !";
            case en:
                return textTranslation.System(languages) + "§4You are muted";
            default:
                return "";
        }
    }

    public static String death(lang.languages languages) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "§4§lVous êtes mort §r!";
            case en:
                return textTranslation.System(languages) + "§4§lYou are dead !";
            default:
                return "";
        }
    }

    public static String deathAnarchie(lang.languages languages, int count) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "§4§lIl vous reste §6§l" + count + " vies §r!";
            case en:
                return textTranslation.System(languages) + "§4§lYou have §6§l" + count + " lives left §r!";
            default:
                return "";
        }
    }

    public static String gold(lang.languages languages) {
        switch (languages) {
            case fr:
                return " §6or";
            case en:
                return " §6gold";
            default:
                return "";
        }
    }

    public static String addGold(lang.languages languages, String goldFormat) {
        switch (languages) {
            case fr:
            case en:
                return textTranslation.System(languages) + "§2+ " + goldFormat + gold(languages);
            default:
                return "";
        }
    }

    public static String removeGold(lang.languages languages, String goldFormat) {
        switch (languages) {
            case fr:
            case en:
                return textTranslation.System(languages) + "§4- " + goldFormat + gold(languages);
            default:
                return "";
        }
    }

    public static String remainingGold(lang.languages languages, String goldFormat) {
        switch (languages) {
            case fr:
                return textTranslation.newLine() + "Vous avez §6" + goldFormat + gold(languages);
            case en:
                return textTranslation.newLine() + "You have §6" + goldFormat + gold(languages);
            default:
                return "";
        }
    }

    public static String SayGold(lang.languages lang, OfflinePlayer offlinePlayer, String goldFormat) {
        switch (lang) {
            case fr:
            case en:
                return textTranslation.newLine() + "§2" + offlinePlayer.getName() + " §f: §6" + goldFormat + gold(lang);
            default:
                return "";
        }
    }

    public static String noInformationPage(lang.languages languages) {
        switch (languages) {
            case fr:
                return textTranslation.System(languages) + "§4Le joueur n'a pas encore de page d'information";
            case en:
                return textTranslation.System(languages) + "§4The player does not yet have an information page";
            default:
                return "";
        }
    }

    public static String clickPlayerInfo(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§2Cliquez pour §6§lvoire les informations du joueur";
            case en:
                return "§2Click to §6§lview player information";
            default:
                return "";
        }
    }

    public static String limitedCmd(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "§4Impossible d'utiliser cette commande";
            case en:
                return System(languages) + "§4Cannot use this command";
            default:
                return "";
        }
    }

    public static String useHelp(lang.languages languages) {
        switch (languages) {
            case fr:
                return newLine() + "/§2aide §rou /§2commandes §rou /§2menu";
            case en:
                return newLine() + "/§2help §ror /§2commands §ror /§2menu";
            default:
                return "";
        }
    }

    public static String helpMSG(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "Utilise le §6compas dans ton inventaire !" + "\n" + useHelp(languages)
                        + "\n" + Discord();
            case en:
                return System(languages) + "Use the §6compass in your inventory !" + "\n" + useHelp(languages) + "\n"
                        + Discord();
            default:
                return "";
        }
    }

    public static String joinServer(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "§6Rejoint un serveur §rdans le menu principal !";
            case en:
                return System(languages) + "§6Join a server §rin the main menu !";
            default:
                return "";
        }
    }

    public static String UpgradeIsland(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages)
                        + "§6Ajoute des amis à ton île et améliore la §rdans le menu lié\n§2§lCommence par améliorer ton générateur de minerais";
            case en:
                return System(languages)
                        + "§6Add friends to your island and improve it §rin the linked menu\n§2§lStart by improving your ore generator";
            default:
                return "";
        }
    }

    public static String CreateOrJoinIslandToStart(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "Pour commencer ton aventure, rejoins ou crée une île";
            case en:
                return System(languages) + "To start your adventure, join or create an island";
            default:
                return "";
        }
    }

    public static String Menu(lang.languages languages) {
        switch (languages) {
            case fr:
            case en:
                return "§6§lMenu";
            default:
                return "";
        }
    }

    public static String Mines(lang.languages languages) {
        switch (languages) {
            case fr:
            case en:
                return "§6§lMines";
            default:
                return "";
        }
    }

    public static String voyage(lang.languages languages) {
        switch (languages) {
            case fr:
                return "§6Voyage";
            case en:
                return "§6Travel";
            default:
                return "";
        }
    }

    public static String randomMSGNPC(lang.languages languages, int rng) {
        if (languages.equals(lang.languages.fr)) {
            switch (rng) {
                case 0:
                    return "Tu me veut quoi ?";
                case 1:
                    return "Oui ?";
                case 2:
                    return "Moi aussi j'étais un aventurier autrefois, et puis j'ai pris une fléche dans le genou";
                case 3:
                    return "Là tu me dérange à me cliquer dessus";
                case 4:
                    return "N'importe qui peux être couronné Empereur. C'est l'un des risques que nous prenons";
                case 5:
                    return "Je vivrai pour toujours, ou je mourrai en essayant";
                case 6:
                    return "Un raid ? Quoi un raid ?";
                case 7:
                    return "Apparement il y a des morts à la prison";
                case 8:
                    return "Tu peux arrêter de me parler ?!";
                case 9:
                    return "Tu est déjà allé à la mine ?";
                case 10:
                    return "Tu veux te battre ?";
                case 11:
                    return "Défend le village, des raids arrivent tous les jours";
                case 12:
                    return "Mppph, encore un nouveau !";
                case 13:
                    return "BlaBlaBla, c'est important vas-y";
                case 14:
                    return "Me parle pas t'es moche !";
                case 15:
                    return "Appelez les hendek !!!";
                default:
                    return "???";
            }
        } else if (languages.equals(lang.languages.en)) {
            switch (rng) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    return "No translation for now";
                default:
                    return "???";
            }
        }
        return "";
    }

    public static String VillagerName(lang.languages languages) {
        switch (languages) {
            case fr:
                return "[§6§lVillageois§r] ";
            case en:
                return "[§6§lVillager§r] ";
            default:
                return "";
        }
    }

    public static String DiscordNoNewLine() {
        return "§faltherneum.fr/§6discord";
    }

    public static String Discord() {
        return textTranslation.newLine() + DiscordNoNewLine();
    }

    public static String JoinIsland(lang.languages languages, OfflinePlayer offlinePlayer) {
        switch (languages) {
            case fr:
                return System(languages) + "§2Vous avez rejoint l'île de §6§l" + offlinePlayer.getName();
            case en:
                return System(languages) + "§2You have joined the island of §6§l" + offlinePlayer.getName();
            default:
                return "";
        }
    }

    public static String PlayerJoinedIsland(lang.languages languages, OfflinePlayer offlinePlayer) {
        switch (languages) {
            case fr:
                return System(languages) + "§6§l" + offlinePlayer.getName() + " §r§2à rejoins l'île";
            case en:
                return System(languages) + "§6§l" + offlinePlayer.getName() + " §r§2joined the island";
            default:
                return "";
        }
    }

    public static String AlreadyHaveIsland(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "Vous avez §4déjà une île";
            case en:
                return System(languages) + "You §4already have an island";
            default:
                return "";
        }
    }

    public static String InviteNoLongerValid(lang.languages languages, OfflinePlayer offlinePlayer) {
        switch (languages) {
            case fr:
                return System(languages) + "§4L'invitation n'est plus valide§r, §6" + offlinePlayer.getName()
                        + " §rn'est plus chef de l'île !";
            case en:
                return System(languages) + "§4The invitation is no longer valid§r, §6" + offlinePlayer.getName()
                        + " §ris no longer the leader of the island !";
            default:
                return "";
        }
    }

    public static String CreatingIsland(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "§2Création de l'île ! ...";
            case en:
                return System(languages) + "§2Creation of the island ! ...";
            default:
                return "";
        }
    }

    public static String IslandCreated(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "§2Île crée !\n" + newLine() + "§rDébut de la construction de l'île";
            case en:
                return System(languages) + "§2Island created !\n" + newLine()
                        + "§rBeginning of the construction of the island";
            default:
                return "";
        }
    }

    public static String PlayerKickedBy(lang.languages languages, OfflinePlayer offlinePlayer) {
        switch (languages) {
            case fr:
                return System(languages) + "Vous avez été §4exclu de l'île §rpar §6" + offlinePlayer.getName();
            case en:
                return System(languages) + "You have been §4kicked out of the island §rby §6" + offlinePlayer.getName();
            default:
                return "";
        }
    }

    public static String PlayerKicked(lang.languages languages, OfflinePlayer offlinePlayer) {
        switch (languages) {
            case fr:
                return System(languages) + "§6" + offlinePlayer.getName() + " §rà été §2exclu de l'île";
            case en:
                return System(languages) + "§6" + offlinePlayer.getName() + " §rwas §2excluded from the island";
            default:
                return "";
        }
    }

    public static String NotTeamLeader(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "Vous n'êtes §4pas chef de l'île";
            case en:
                return System(languages) + "You are §4not the leader of the island";
            default:
                return "";
        }
    }

    public static String TeleportOutsideIsland(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "§6Téléportation hors de l'île !";
            case en:
                return System(languages) + "§6Teleportation outside the island !";
            default:
                return "";
        }
    }

    public static String NoLongerIsland(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "Vous n'êtes §4plus de l'île !";
            case en:
                return System(languages) + "You are §4no longer on the island !";
            default:
                return "";
        }
    }

    public static String NotLocatedInIsland(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "Vous n'êtes §4pas dans votre île";
            case en:
                return System(languages) + "You are §4not on your island";
            default:
                return "";
        }
    }

    public static String AreNotMoreIslandLeader(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "§4Vous n'êtes plus chef de l'île !";
            case en:
                return System(languages) + "§4You are no longer the leader of the island !";
            default:
                return "";
        }
    }

    public static String IslandLocationChanged(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "§2Le point de réapparition de l'île a été changé !";
            case en:
                return System(languages) + "§2Island respawn point has been changed !";
            default:
                return "";
        }
    }

    public static String AreIslandLeader(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "§2Vous êtes chef de l'île !";
            case en:
                return System(languages) + "§2You are the leader of the island !";
            default:
                return "";
        }
    }

    public static String AreInvitedInIsland(lang.languages languages, OfflinePlayer offlinePlayer) {
        switch (languages) {
            case fr:
                return System(languages) + "§2Vous êtes invité dans l'île de §6" + offlinePlayer.getName() + " §r!";
            case en:
                return System(languages) + "§2You are invited to the island of §6" + offlinePlayer.getName() + " §r!";
            default:
                return "";
        }
    }

    public static String InvitedInIsland(lang.languages languages, OfflinePlayer offlinePlayer) {
        switch (languages) {
            case fr:
                return System(languages) + "Vous avez §2invité §6" + offlinePlayer.getName() + " §r!";
            case en:
                return System(languages) + "You have §2invited §6" + offlinePlayer.getName() + " §r!";
            default:
                return "";
        }
    }

    public static String AlreadyInvitedInIsland(lang.languages languages, OfflinePlayer offlinePlayer) {
        switch (languages) {
            case fr:
                return System(languages) + "Vous avez §4déjà invité §6" + offlinePlayer.getName() + " §r!";
            case en:
                return System(languages) + "You have §4already invited §6" + offlinePlayer.getName() + " §r!";
            default:
                return "";
        }
    }

    public static String OtherAlreadyHaveIsland(lang.languages languages) {
        switch (languages) {
            case fr:
                return System(languages) + "Le joueur est §4déjà sur une île !";
            case en:
                return System(languages) + "The player is §4already on an island !";
            default:
                return "";
        }
    }

    public static String bannedAnarchie(lang.languages lang) {
        switch (lang) {
            case fr:
                return "§4Vous êtes §lmort (24h) §r§4!\n§6Discord §rpour toutes demandes Altherneum.fr/discord";
            case en:
                return "§4You are §ldead (24h) §r§4!\n§6Discord §rfor all requests Altherneum.fr/discord";
            default:
                return "";
        }
    }

    public static String hubfirstLogin(lang.languages lang) {
        switch (lang) {
            case fr:
                return "§4§lMerci de vous connecter sur le Hub\n§r§6Si ce n'est pas une première connection -> §r§2"
                        + Discord();
            case en:
                return " ";
            default:
                return "";
        }
    }

    public static String noBed(lang.languages lang) {
        switch (lang) {
            case fr:
                return System(lang) + "§4Vous n'avez pas de lit";
            case en:
                return System(lang) + "§4You don't have a bed";
            default:
                return "";
        }
    }

    public static String bedObstrued(lang.languages lang) {
        switch (lang) {
            case fr:
                return System(lang) + "§4Votre lit est obstrué";
            case en:
                return System(lang) + "§4Your bed is obstructed";
            default:
                return "";
        }
    }

    public static String chatSetting(lang.languages lang, boolean show) {
        String text = "";
        if (!show && lang.equals(lang.fr)) {
            text += "§2affiché";
        } else if (show && lang.equals(lang.fr)) {
            text += "§4caché";
        } else if (!show && lang.equals(lang.en)) {
            text += "§2show";
        } else if (show && lang.equals(lang.en)) {
            text += "§4hide";
        }

        switch (lang) {
            case fr:
                return System(lang) + "§fMessage des joueurs : " + text;
            case en:
                return System(lang) + "§fPlayers message : " + text;
            default:
                return "";
        }
    }

    public static String RemoveDeathAnarchie(lang.languages lang, int count) {
        switch (lang) {
            case fr:
                return System(lang) + "§2Vous avez récupéré une vie, vous avez §6§l" + count + " vies";
            case en:
                return System(lang) + "§2You have regained a life, you have §6§l" + count + " lives";
            default:
                return "";
        }
    }

    public static String roleRequiered(lang.languages lang, String grade) {
        switch (lang) {
            case fr:
                return System(lang) + "§4Vous n'avez pas le grade requis§f : §6§l" + grade;
            case en:
                return System(lang) + "§4You do not have the grade required§f : §6§l" + grade;
            default:
                return "";
        }
    }
}