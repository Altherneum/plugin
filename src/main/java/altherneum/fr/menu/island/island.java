package altherneum.fr.menu.island;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import altherneum.fr.main.main;
import altherneum.fr.menu.api.persistentData;
import altherneum.fr.menu.island.bonus.bonusPlayerFile;
import altherneum.fr.system.dateAPI;
import altherneum.fr.system.getDataStorage;
import altherneum.fr.text.playerLang;
import altherneum.fr.text.textTranslation;
import altherneum.fr.world.api.structure;
import altherneum.fr.world.api.teleportation;
import altherneum.fr.world.api.worldManager;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class island {
    public static int getMaxPlayerAmount(OfflinePlayer offlinePlayer) throws IOException {
        int lvl = bonusPlayerFile.getLvlIsland(GetIslandNumber(offlinePlayer),
                persistentData.customKey.bonusplayeramount);
        if (lvl >= 0 && lvl < 20) {
            return 2;
        } else if (lvl >= 20 && lvl < 50) {
            return 3;
        } else if (lvl >= 50 && lvl < 100) {
            return 4;
        } else if (lvl >= 100 && lvl < 250) {
            return 5;
        } else if (lvl >= 250 && lvl < 500) {
            return 6;
        } else if (lvl >= 500 && lvl < 1000) {
            return 7;
        } else if (lvl >= 1000) {
            return 8;
        }
        return 0;
    }

    public static void AddNewPlayerToIsland(OfflinePlayer offlinePlayer, OfflinePlayer offlinePlayerOwner)
            throws IOException {
        if (GetIsIslandOwner(offlinePlayerOwner)) {
            if (!GetHasIsland(offlinePlayer)) {
                if (getMaxPlayerAmount(offlinePlayerOwner) > GetIslandPlayer(offlinePlayerOwner).size()) {
                    SetPlayerToIsland(GetIsland(offlinePlayerOwner), offlinePlayer);
                    SetIslandToPlayer(GetIslandNumber(offlinePlayerOwner), offlinePlayer);
                    if (offlinePlayer.isOnline()) {
                        offlinePlayer.getPlayer().sendMessage(textTranslation
                                .JoinIsland(playerLang.getPlayerLang(offlinePlayer), offlinePlayerOwner));
                    }
                    if (offlinePlayerOwner.isOnline()) {
                        offlinePlayerOwner.getPlayer().sendMessage(textTranslation
                                .PlayerJoinedIsland(playerLang.getPlayerLang(offlinePlayer), offlinePlayer));
                    }
                } else {
                    // MSG goes here
                }
            } else {
                if (offlinePlayer.isOnline()) {
                    offlinePlayer.getPlayer()
                            .sendMessage(textTranslation.AlreadyHaveIsland(playerLang.getPlayerLang(offlinePlayer)));
                }
            }
        } else {
            if (offlinePlayerOwner.isOnline()) {
                offlinePlayer.getPlayer().sendMessage(textTranslation
                        .InviteNoLongerValid(playerLang.getPlayerLang(offlinePlayer), offlinePlayerOwner));
            }
        }
    }

    public static void CheckIfDeleteIsland(int islandNumber)
            throws IllegalArgumentException, IOException, IllegalStateException, ParseException {
        File file = GetIsland(islandNumber);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        List<String> playerList = fileConfiguration.getStringList("Players");
        if (playerList.isEmpty()) {
            worldManager.DeleteWorld("i." + islandNumber, true, true, true);
            worldManager.DeleteWorldFile(getDataStorage.islandFolder(islandNumber));
        }
    }

    public static void CreateIsland(Player player) throws IOException, ParseException {
        if (!GetHasIsland(player)) {
            int islandNumber = GetIslandNumberAvailable();
            File file = GetIsland(islandNumber);
            if (!file.exists()) {
                file.createNewFile();
                SetPlayerToIsland(file, player);
                SetIslandToPlayer(islandNumber, player);
                SetOwner(player, false, true);

                Bukkit.getScheduler().runTask(JavaPlugin.getPlugin(main.class), new Runnable() {

                    @Override
                    public void run() {
                        try {
                            player.sendMessage(textTranslation.CreatingIsland(playerLang.getPlayerLang(player)));
                            worldManager.Generate("i." + islandNumber, true, World.Environment.NORMAL, WorldType.NORMAL,
                                    true);
                            Bukkit.getWorld("i." + islandNumber).setSpawnLocation(0, 64, 0);
                            player.sendMessage(textTranslation.IslandCreated(playerLang.getPlayerLang(player)));
                            structure.setStructure("Island", "i." + islandNumber, player);
                        } catch (Exception e) {

                        }
                    }
                });
            }
        }
    }

    public static boolean GetHasIsland(OfflinePlayer offlinePlayer) throws IOException {
        return YamlConfiguration.loadConfiguration(getDataStorage.playerIslandData(offlinePlayer))
                .getBoolean("HasIsland");
    }

    public static boolean GetIsIslandOwner(OfflinePlayer offlinePlayer) throws IOException {
        return GetHasIsland(offlinePlayer)
                && GetIslandOwner(offlinePlayer).equals(offlinePlayer.getUniqueId().toString());
    }

    public static File GetIsland(int islandNumber) {
        return getDataStorage.islandFile(islandNumber);
    }

    public static File GetIsland(OfflinePlayer offlinePlayer) throws IOException {
        return GetIsland(GetIslandNumber(offlinePlayer));
    }

    public static int GetIslandNumber(OfflinePlayer offlinePlayer) throws IOException {
        return YamlConfiguration.loadConfiguration(getDataStorage.playerIslandData(offlinePlayer))
                .getInt("IslandNumber");
    }

    public static int GetIslandNumberAvailable() {
        int i = 1;
        while (GetIsland(i).exists()) {
            i++;
        }
        return i;
    }

    public static String GetIslandOwner(int IslandNumber) throws IllegalArgumentException {
        File file = GetIsland(IslandNumber);
        if (file.exists()) {
            return YamlConfiguration.loadConfiguration(file).getString("Owner");
        }
        return "";
    }

    public static String GetIslandOwner(OfflinePlayer offlinePlayer) throws IOException {
        return GetIslandOwner(GetIslandNumber(offlinePlayer));
    }

    public static List<Integer> GetPendingIsland(OfflinePlayer offlinePlayer) {
        return YamlConfiguration.loadConfiguration(getDataStorage.playerIslandData(offlinePlayer))
                .getIntegerList("PendingIsland.List");
    }

    public static String GetPendingOwnerForIsland(OfflinePlayer offlinePlayer, int Island) {
        return (String) YamlConfiguration.loadConfiguration(getDataStorage.playerIslandData(offlinePlayer))
                .get("PendingIsland." + Island);
    }

    public static List<String> GetPlayerList(int islandNumber) throws IllegalArgumentException {
        File file = GetIsland(islandNumber);
        if (file.exists()) {
            return YamlConfiguration.loadConfiguration(file).getStringList("Players");
        }
        return new ArrayList<String>();
    }

    public static List<OfflinePlayer> GetIslandPlayer(OfflinePlayer offlinePlayer) throws IOException {
        ArrayList<OfflinePlayer> IslandPlayers = new ArrayList<>();
        for (String string : GetPlayerList(GetIslandNumber(offlinePlayer))) {
            IslandPlayers.add(Bukkit.getOfflinePlayer(UUID.fromString(string)));
        }
        return IslandPlayers;
    }

    public static List<OfflinePlayer> GetIslandPlayerWithoutClickingPlayer(OfflinePlayer offlinePlayer)
            throws IOException {
        List<OfflinePlayer> IslandPlayers = GetIslandPlayer(offlinePlayer);
        IslandPlayers.remove(offlinePlayer);
        return IslandPlayers;
    }

    public static List<OfflinePlayer> GetOnlinePlayerWithoutIsland() throws IOException {
        ArrayList<OfflinePlayer> Players = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!GetHasIsland(player)) {
                Players.add(player);
            }
        }
        return Players;
    }

    public static List<OfflinePlayer> GetOnlinePlayerWithoutIslandWithCheckedInvite(OfflinePlayer offlinePlayer)
            throws IOException {
        ArrayList<OfflinePlayer> Players = new ArrayList<>();
        for (OfflinePlayer player : GetOnlinePlayerWithoutIsland()) {
            if (!island.GetPendingIsland(player).contains(island.GetIslandNumber(offlinePlayer))) {
                Players.add(player);
            }
        }
        return Players;
    }

    public static boolean GetIsPlayerAFK(OfflinePlayer offlinePlayer) throws ParseException {
        Date dateLastLogin = dateAPI.ReturnDate(getDataStorage.playerFile(offlinePlayer), "LastLogin");
        Date moins10Jours = dateAPI.ReturnDateWithXDay(dateAPI.now(), -10);
        return moins10Jours.after(dateLastLogin);
    }

    public static boolean IsInHerIsland(Player player) throws IOException {
        if (GetHasIsland(player)) {
            return player.getWorld().getName().equals("i." + GetIslandNumber(player))
                    || player.getWorld().getName().startsWith("i." + GetIslandNumber(player) + "_");
        }
        return false;
    }

    public static Boolean isPlayerIsland(String world, OfflinePlayer offlinePlayer) throws IOException {
        return world.equals("i." + GetIslandNumber(offlinePlayer))
                || world.startsWith("i." + GetIslandNumber(offlinePlayer) + "_");
    }

    public static boolean IsInSameIsland(OfflinePlayer offlinePlayer, OfflinePlayer offlinePlayer2) throws IOException {
        if (GetHasIsland(offlinePlayer) && GetHasIsland(offlinePlayer2)) {
            return GetPlayerList(GetIslandNumber(offlinePlayer)).contains(offlinePlayer2.getUniqueId().toString());
        }
        return false;
    }

    public static void KickFromIsland(OfflinePlayer offlinePlayerKicked, OfflinePlayer offlinePlayerOwner)
            throws IOException, IllegalArgumentException, IllegalStateException, ParseException {
        if (IsInSameIsland(offlinePlayerKicked, offlinePlayerOwner)) {
            if (GetIsIslandOwner(offlinePlayerOwner)) {
                sendKickMSG(offlinePlayerKicked);
                LeaveIsland(offlinePlayerKicked);
                if (offlinePlayerKicked.isOnline()) {
                    offlinePlayerKicked.getPlayer().sendMessage(textTranslation
                            .PlayerKickedBy(playerLang.getPlayerLang(offlinePlayerKicked), offlinePlayerOwner));
                }
            } else {
                if (offlinePlayerOwner.isOnline()) {
                    offlinePlayerOwner.getPlayer()
                            .sendMessage(textTranslation.NotTeamLeader(playerLang.getPlayerLang(offlinePlayerOwner)));
                }
            }
        }
    }

    public static void LeaveIsland(OfflinePlayer offlinePlayer)
            throws IllegalArgumentException, IOException, IllegalStateException, ParseException {
        if (GetHasIsland(offlinePlayer)) {
            File file = GetIsland(offlinePlayer);
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
            File filePlayer = getDataStorage.playerIslandData(offlinePlayer);
            FileConfiguration fileConfigurationPlayer = YamlConfiguration.loadConfiguration(filePlayer);

            List<String> playerList = fileConfiguration.getStringList("Players");
            playerList.remove(offlinePlayer.getUniqueId().toString());
            fileConfiguration.set("Players", playerList);
            fileConfiguration.save(file);

            CheckIfDeleteIsland(GetIslandNumber(offlinePlayer));

            if (offlinePlayer.isOnline() && offlinePlayer.getPlayer().getWorld().getName()
                    .startsWith("i." + fileConfigurationPlayer.getInt("IslandNumber"))) {
                teleportation.Teleport(offlinePlayer.getPlayer(), "Spawn", false);
                offlinePlayer.getPlayer()
                        .sendMessage(textTranslation.TeleportOutsideIsland(playerLang.getPlayerLang(offlinePlayer)));
            }
            filePlayer.delete();

            if (offlinePlayer.isOnline()) {
                offlinePlayer.getPlayer()
                        .sendMessage(textTranslation.NoLongerIsland(playerLang.getPlayerLang(offlinePlayer)));
            }
        }
    }

    public static void sendKickMSG(OfflinePlayer offlinePlayer) throws IOException {
        List<String> players = GetPlayerList(GetIslandNumber(offlinePlayer));
        for (String string : players) {
            OfflinePlayer offlinePlayerIS = Bukkit.getOfflinePlayer(UUID.fromString(string));
            if (offlinePlayerIS.isOnline()) {
                offlinePlayerIS.getPlayer().sendMessage(
                        textTranslation.PlayerKicked(playerLang.getPlayerLang(offlinePlayerIS), offlinePlayer));
            }
        }
    }

    public static void SetIslandLocation(Player player) throws IllegalArgumentException, IOException {
        if (GetIsIslandOwner(player)) {
            if (IsInHerIsland(player)) {
                player.sendMessage(textTranslation.IslandLocationChanged(playerLang.getPlayerLang(player)));
                player.getWorld().setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY(),
                        player.getLocation().getBlockZ());
            } else {
                player.sendMessage(textTranslation.NotLocatedInIsland(playerLang.getPlayerLang(player)));
            }
        } else {
            player.sendMessage(textTranslation.NotTeamLeader(playerLang.getPlayerLang(player)));
        }
    }

    public static void SetIslandToPlayer(int islandNumber, OfflinePlayer offlinePlayer) throws IOException {
        File file = getDataStorage.playerIslandData(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set("HasIsland", true);
        fileConfiguration.set("IslandNumber", islandNumber);
        fileConfiguration.save(file);
    }

    public static void SetOwner(OfflinePlayer offlinePlayer, boolean removeOldOwner, boolean silent)
            throws IOException, IllegalArgumentException {
        File file = GetIsland(offlinePlayer);
        if (file.exists()) {
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
            if (removeOldOwner && fileConfiguration.isString("Owner")) {
                OfflinePlayer offlinePlayerOldOwner = Bukkit
                        .getOfflinePlayer(UUID.fromString(fileConfiguration.getString("Owner")));
                if (offlinePlayerOldOwner.isOnline() && !silent) {
                    offlinePlayerOldOwner.getPlayer().sendMessage(
                            textTranslation.AreNotMoreIslandLeader(playerLang.getPlayerLang(offlinePlayerOldOwner)));
                }
            }
            fileConfiguration.set("Owner", offlinePlayer.getUniqueId().toString());
            fileConfiguration.save(file);
            if (offlinePlayer.isOnline() && !silent) {
                offlinePlayer.getPlayer()
                        .sendMessage(textTranslation.AreIslandLeader(playerLang.getPlayerLang(offlinePlayer)));
            }
        }
    }

    public static void SetPendingIsland(OfflinePlayer offlinePlayer, OfflinePlayer Owner) throws IOException {
        File file = getDataStorage.playerIslandData(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        int islandNumber = GetIslandNumber(Owner);
        List<Integer> list = GetPendingIsland(offlinePlayer);
        if (!list.contains(islandNumber)) {
            list.add(islandNumber);
            fileConfiguration.set("PendingIsland.List", list);
            fileConfiguration.set("PendingIsland." + islandNumber, Owner.getUniqueId().toString());
            fileConfiguration.save(file);
            if (offlinePlayer.isOnline()) {
                offlinePlayer.getPlayer().sendMessage(
                        textTranslation.AreInvitedInIsland(playerLang.getPlayerLang(offlinePlayer), Owner));
            }
            if (Owner.isOnline()) {
                Owner.getPlayer().sendMessage(
                        textTranslation.InvitedInIsland(playerLang.getPlayerLang(offlinePlayer), offlinePlayer));
            }
        } else {
            if (Owner.isOnline()) {
                Owner.getPlayer().sendMessage(
                        textTranslation.AlreadyInvitedInIsland(playerLang.getPlayerLang(offlinePlayer), offlinePlayer));
            }
        }
    }

    public static void SetPendingIslandRemove(OfflinePlayer offlinePlayer, int islandNumber) throws IOException {
        File file = getDataStorage.playerIslandData(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (GetPendingIsland(offlinePlayer).size() > 1) {
            List<Integer> list = GetPendingIsland(offlinePlayer);
            list.remove((Integer) islandNumber);
            fileConfiguration.set("PendingIsland.List", list);
        } else {
            fileConfiguration.set("PendingIsland.List", null);
            fileConfiguration.set("PendingIsland", null);
        }
        fileConfiguration.set("PendingIsland." + islandNumber, null);
        fileConfiguration.save(file);
    }

    public static void SetPlayerToIsland(File file, OfflinePlayer offlinePlayer)
            throws IOException, IllegalArgumentException {
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        List<String> playerList = fileConfiguration.getStringList("Players");
        playerList.add(offlinePlayer.getUniqueId().toString());
        fileConfiguration.set("Players", playerList);
        fileConfiguration.save(file);
    }

    public static void TeleportToIsland(Player player)
            throws IllegalArgumentException, IllegalStateException, IOException, ParseException {
        if (GetHasIsland(player)) {
            teleportation.Teleport(player, "i." + GetIslandNumber(player), false);
            //Get Island World Spawn point
                //Bukkit.getWorld("i." + GetIslandNumber(player))
            //Check if block is not air or liquid
            //check if block under is same
            //else set block of cobblestone
        }
    }
}