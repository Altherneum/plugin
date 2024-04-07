package altherneum.fr.menu.shop.classique;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import altherneum.fr.menu.api.persistentData;
import altherneum.fr.text.lang;
import altherneum.fr.text.shopTranslation;

import java.util.ArrayList;

public class shopItems {

    public static ItemStack itemToSell(Material material, int prix, int nombre, boolean BuyMenu, lang.languages lang) {
        ItemStack is = new ItemStack(material, nombre);
        ItemMeta meta = is.getItemMeta();
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        if (BuyMenu) {

            Lore.add(shopTranslation.LeftClick(lang));
            Lore.add(shopTranslation.BuyPrice(lang, prix));
            Lore.add(shopTranslation.TotalBuyPrice(lang, prix, nombre));
            Lore.add(" ");
            Lore.add(shopTranslation.RightClick(lang));
            Lore.add(shopTranslation.SellPrice(lang, prix));
            Lore.add(shopTranslation.TotalSellPrice(lang, prix, nombre));
        } else {
            Lore.add(shopTranslation.BuyPrice(lang, prix));
            Lore.add(shopTranslation.SellPrice(lang, prix));
        }
        Lore.add(" ");
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(shopTranslation.OtherClick(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);

        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        if (BuyMenu) {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopitemtosell);
        } else {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopitemtoselllist);
        }
        return is;
    }

    public static ItemStack itemSplitter(Boolean Add, int nombre, int prix, lang.languages lang) {
        ItemStack is;
        if (Add) {
            is = new ItemStack(Material.EMERALD_BLOCK, nombre);
            ItemMeta meta = is.getItemMeta();
            meta.setDisplayName(shopTranslation.AddToCart(lang, nombre));
            ArrayList<String> Lore = new ArrayList<String>();
            Lore.add(" ");
            Lore.add(shopTranslation.BuyPrice(lang, prix));
            Lore.add(shopTranslation.AddToCartPrice(lang, prix, nombre));
            Lore.add(" ");
            meta.setLore(Lore);
            is.setItemMeta(meta);
        } else {
            is = new ItemStack(Material.REDSTONE_BLOCK, nombre);
            ItemMeta meta = is.getItemMeta();
            meta.setDisplayName(shopTranslation.RemoveFromCart(lang, nombre));
            ArrayList<String> Lore = new ArrayList<String>();
            Lore.add(" ");
            Lore.add(shopTranslation.BuyPrice(lang, prix));
            Lore.add(shopTranslation.RemoveFromCartPrice(lang, prix, nombre));
            Lore.add(" ");
            meta.setLore(Lore);
            is.setItemMeta(meta);
        }
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopsplitter);
        return is;
    }

    public static ItemStack Blocks(lang.languages lang) {
        ItemStack is = new ItemStack(Material.BRICKS, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.BlocksTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(shopTranslation.Blocks(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopblocks);
        return is;
    }

    public static ItemStack sellAll(lang.languages lang) {
        ItemStack is = new ItemStack(Material.CHEST, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.sellAllTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(shopTranslation.sellAllLore(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopsellall);
        return is;
    }

    public static ItemStack Graine(lang.languages lang) {
        ItemStack is = new ItemStack(Material.WHEAT_SEEDS, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.GrainesTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(shopTranslation.Graines(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopgraines);
        return is;
    }

    public static ItemStack Minerais(lang.languages lang) {
        ItemStack is = new ItemStack(Material.IRON_INGOT, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.MineraisTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(shopTranslation.Minerais(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopminerais);
        return is;
    }

    public static ItemStack Monstres(lang.languages lang) {
        ItemStack is = new ItemStack(Material.BONE, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.MonstresTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(shopTranslation.Monstres(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopmonstres);
        return is;
    }

    public static ItemStack Nourriture(lang.languages lang) {
        ItemStack is = new ItemStack(Material.APPLE, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.NourrituresTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(shopTranslation.Nourritures(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopnourritures);
        return is;
    }

    public static ItemStack Plantes(lang.languages lang) {
        ItemStack is = new ItemStack(Material.OAK_SAPLING, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.PlantesTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(shopTranslation.Plantes(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopplantes);
        return is;
    }

    public static ItemStack Dye(lang.languages lang) {
        ItemStack is = new ItemStack(Material.BLUE_DYE, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.TeinturesTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(shopTranslation.Teintures(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopteintures);
        return is;
    }

    public static ItemStack Speciaux(lang.languages lang) {
        ItemStack is = new ItemStack(Material.DRAGON_HEAD, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.SpeciauxTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(shopTranslation.Speciaux(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopspeciaux);
        return is;
    }

    public static ItemStack Custom(lang.languages lang) {
        ItemStack is = new ItemStack(Material.DIAMOND, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setCustomModelData(4);
        meta.setDisplayName(shopTranslation.CustomTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(shopTranslation.Custom(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopcustom);
        return is;
    }

    public static ItemStack Oeufs(lang.languages lang) {
        ItemStack is = new ItemStack(Material.VILLAGER_SPAWN_EGG, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.OeufsTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(shopTranslation.Oeufs(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopoeufs);
        return is;
    }

    public static ItemStack Water(lang.languages lang) {
        ItemStack is = new ItemStack(Material.TUBE_CORAL, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.AquatiquesTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(shopTranslation.Aquatiques(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopaquatiques);
        return is;
    }

    public static ItemStack Nether(lang.languages lang) {
        ItemStack is = new ItemStack(Material.WEEPING_VINES, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.NetherTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.add(shopTranslation.Nether(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shopnether);
        return is;
    }
}