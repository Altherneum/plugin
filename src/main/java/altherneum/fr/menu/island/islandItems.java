package altherneum.fr.menu.island;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.text.itemsTranslation;
import altherneum.fr.text.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class islandItems {
    public static ItemStack ItemStackCreateIle(lang.languages lang) {
        ItemStack is = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.IleCreateTitle(lang));
        meta.setLore(itemsTranslation.IleCreateLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.ilecreate);
        return is;
    }

    public static ItemStack ItemStackIleBonus(lang.languages lang) {
        ItemStack is = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.IleBonusTitle(lang));
        meta.setLore(itemsTranslation.IleBonusLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.ileboost);
        return is;
    }

    public static ItemStack ItemStackKickIle(lang.languages lang) {
        ItemStack is = new ItemStack(Material.BARRIER);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.IleKickTitle(lang));
        meta.setLore(itemsTranslation.IleKickLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.ilekick);
        return is;
    }

    public static ItemStack ItemStackTPIle(lang.languages lang) {
        ItemStack is = new ItemStack(Material.DRAGON_BREATH);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.IleTPTitle(lang));
        meta.setLore(itemsTranslation.IleTPLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.iletp);
        return is;
    }

    public static ItemStack ItemStackInviteIle(lang.languages lang) {
        ItemStack is = new ItemStack(Material.MAP);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.IleInviteTitle(lang));
        meta.setLore(itemsTranslation.IleInviteLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.ileinvite);
        return is;
    }

    public static ItemStack ItemStackLocateIle(lang.languages lang) {
        ItemStack is = new ItemStack(Material.BEACON);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.IleLocateTitle(lang));
        meta.setLore(itemsTranslation.IleLocateLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.ilelocate);
        return is;
    }

    public static ItemStack ItemStackOwnerIle(lang.languages lang) {
        ItemStack is = new ItemStack(Material.ENCHANTING_TABLE);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.IleOwnerTitle(lang));
        meta.setLore(itemsTranslation.IleOwnerLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.ileowner);
        return is;
    }

    public static ItemStack ItemStackPendingIle(lang.languages lang) {
        ItemStack is = new ItemStack(Material.OAK_SIGN);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.IlePendingTitle(lang));
        meta.setLore(itemsTranslation.IlePendingLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.ilepending);
        return is;
    }

    public static ItemStack ItemStackLeaveIle(lang.languages lang) {
        ItemStack is = new ItemStack(Material.COBWEB);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.IleLeaveTitle(lang));
        meta.setLore(itemsTranslation.IleLeaveLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.ileleave);
        return is;
    }

    public static ItemStack ItemStackLeaveConfirmIle(lang.languages lang) {
        ItemStack is = new ItemStack(Material.COBWEB);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.IleLeaveConfirmTitle(lang));
        meta.setLore(itemsTranslation.IleLeaveConfirmLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.ileleaveconfirm);
        return is;
    }

    public static ItemStack ItemStackPlayerHeadInfo(OfflinePlayer offlinePlayer, lang.languages languages)
            throws IOException {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setOwningPlayer(offlinePlayer);
        itemMeta.setDisplayName("§r§6§l" + offlinePlayer.getName());
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(itemsTranslation.IleHead(languages, offlinePlayer));
        if (offlinePlayer.isOnline()) {
            lore.add(itemsTranslation.Online(languages));
        } else {
            lore.add(itemsTranslation.Offline(languages));
        }
        lore.add("");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        persistentData.setPersistentDataItemStack(itemStack, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(itemStack, persistentData.customKey.ileplayerhead);
        return itemStack;
    }

    public static ItemStack ItemStackPlayerHeadInfoVisite(OfflinePlayer offlinePlayer, lang.languages languages)
            throws IOException {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setOwningPlayer(offlinePlayer);
        itemMeta.setDisplayName("§r§6§l"+offlinePlayer.getName());
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(itemsTranslation.IleHeadVisite(languages, offlinePlayer));
        if (offlinePlayer.isOnline()) {
            lore.add(itemsTranslation.Online(languages));
        } else {
            lore.add(itemsTranslation.Offline(languages));
        }
        lore.add("");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        persistentData.setPersistentDataItemStack(itemStack, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(itemStack, persistentData.customKey.ileprofilviste);
        return itemStack;
    }

    public static ItemStack ItemStackPlayerHeadExclude(OfflinePlayer offlinePlayer, lang.languages languages)
            throws IOException {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setOwningPlayer(offlinePlayer);
        itemMeta.setDisplayName(offlinePlayer.getName());
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(itemsTranslation.ExcludeHead(languages, offlinePlayer));
        if (offlinePlayer.isOnline()) {
            lore.add(itemsTranslation.Online(languages));
        } else {
            lore.add(itemsTranslation.Offline(languages));
        }
        lore.add("");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        persistentData.setPersistentDataItemStack(itemStack, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(itemStack, persistentData.customKey.excludeplayerhead);
        return itemStack;
    }

    public static ItemStack ItemStackPlayerHeadOwner(OfflinePlayer offlinePlayer, lang.languages languages)
            throws IOException {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setOwningPlayer(offlinePlayer);
        itemMeta.setDisplayName(offlinePlayer.getName());
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(itemsTranslation.OwnerHead(languages, offlinePlayer));
        if (offlinePlayer.isOnline()) {
            lore.add(itemsTranslation.Online(languages));
        } else {
            lore.add(itemsTranslation.Offline(languages));
        }
        lore.add("");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        persistentData.setPersistentDataItemStack(itemStack, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(itemStack, persistentData.customKey.ownerplayerhead);
        return itemStack;
    }

    public static ItemStack ItemStackPlayerHeadInvite(OfflinePlayer offlinePlayer, lang.languages languages)
            throws IOException {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setOwningPlayer(offlinePlayer);
        itemMeta.setDisplayName(offlinePlayer.getName());
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(itemsTranslation.InviteHead(languages, offlinePlayer));
        lore.add("");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        persistentData.setPersistentDataItemStack(itemStack, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(itemStack, persistentData.customKey.inviteplayerhead);
        return itemStack;
    }

    public static List<ItemStack> GetPlayerHeadIsland(List<OfflinePlayer> offlinePlayers, lang.languages languages)
            throws IOException {
        ArrayList<ItemStack> PlayerHeads = new ArrayList<>();
        for (OfflinePlayer offlinePlayerIS : offlinePlayers) {
            PlayerHeads.add(ItemStackPlayerHeadInfo(offlinePlayerIS, languages));
        }
        return PlayerHeads;
    }

    public static List<ItemStack> GetPlayerHeadExclude(List<OfflinePlayer> offlinePlayers, lang.languages languages)
            throws IOException {
        ArrayList<ItemStack> PlayerHeads = new ArrayList<>();
        for (OfflinePlayer offlinePlayerIS : offlinePlayers) {
            PlayerHeads.add(ItemStackPlayerHeadExclude(offlinePlayerIS, languages));
        }
        return PlayerHeads;
    }

    public static List<ItemStack> GetPlayerHeadOwner(List<OfflinePlayer> offlinePlayers, lang.languages languages)
            throws IOException {
        ArrayList<ItemStack> PlayerHeads = new ArrayList<>();
        for (OfflinePlayer offlinePlayerIS : offlinePlayers) {
            PlayerHeads.add(ItemStackPlayerHeadOwner(offlinePlayerIS, languages));
        }
        return PlayerHeads;
    }

    public static List<ItemStack> GetPlayerHeadInvite(List<OfflinePlayer> offlinePlayers, lang.languages languages)
            throws IOException {
        ArrayList<ItemStack> PlayerHeads = new ArrayList<>();
        for (OfflinePlayer offlinePlayerIS : offlinePlayers) {
            PlayerHeads.add(ItemStackPlayerHeadInvite(offlinePlayerIS, languages));
        }
        return PlayerHeads;
    }

    public static ItemStack GetPlayerHeadPending(OfflinePlayer offlinePlayer, lang.languages languages) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setOwningPlayer(offlinePlayer);
        itemMeta.setDisplayName(offlinePlayer.getName());
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(itemsTranslation.pendingHead(languages, offlinePlayer));
        lore.add("");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        persistentData.setPersistentDataItemStack(itemStack, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(itemStack, persistentData.customKey.pendingplayerhead);
        return itemStack;
    }
}