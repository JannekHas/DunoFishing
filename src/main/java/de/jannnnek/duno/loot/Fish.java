package de.jannnnek.duno.loot;

import de.jannnnek.duno.player.FishingPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum Fish {

    HERING("§bHering", "gewöhnlich", 20.0, 40.0, 4, 1),
    LACHS("§bLachs", "gewöhnlich", 90.0, 150.0, 5, 2),
    THUNFISCH("§bThunfisch", "gewöhnlich", 55.0, 80.0, 4, 3),
    DORADE("§bDorade", "ungewöhnlich", 35.0, 70.0, 30, 4),
    ZANDER("§bZander", "gewöhnlich", 40.0, 50.0, 4, 5),
    HECHT("§bHecht", "gewöhnlich", 60.0, 100.0, 5, 6),
    BARSCH("§bBarsch", "ungewöhnlich", 20.0, 30.0, 27, 7),
    WELS("§bWels", "selten", 60.0, 150.0, 50, 8),
    MAKRELE("§bMakrele", "ungewöhnlich", 30.0, 50.0, 28, 9),
    FORELLE("§bForelle", "gewöhnlich", 20.0, 60.0, 4, 10),
    SCHOLLE("§bScholle", "ungewöhnlich", 25.0, 100.0, 32, 11),
    GOLDFISCH("§bGoldfisch", "selten", 5.0, 30.0, 45, 12),
    MONDFISCH("§bMondfisch", "episch", 180.0, 330.0, 100, 13),
    ROTBARSCH("§bRotbarsch", "selten", 30.0, 90.0, 51, 14),
    LENGFISCH("§bLengfisch", "episch", 80.0, 200.0, 120, 15),
    SEELACHS("§bSeelachs", "ungewöhnlich", 55.0, 120.0, 34, 16),
    STOER("§bStör", "selten", 100.0, 550.0, 60, 17),
    KOENIGSLACHS("§bKönigslachs", "selten", 90.0, 100.0, 56, 18),
    BLOBFISCH("§bBlobfisch", "legendär", 20.0, 70.0, 250, 19),
    ZITRONENFISCH("§bZitronenfisch", "selten", 15.0, 30.0, 45, 20),
    KABELJAU("§bKabeljau", "gewöhnlich", 60.0, 100.0, 5, 21),
    SCHWERTFISCH("§bSchwertfisch", "episch", 120.0, 190.0, 110, 22),
    BAARS("§bBaars", "gewöhnlich", 25.0, 60.0, 4, 23),
    HAI("§bHai", "selten", 350.0, 700.0, 52, 24),
    BARRAMUNDI("§bBarramundi", "selten", 120.0, 200.0, 52, 25),
    MAHI_MAHI("§bMahi Mahi", "gewöhnlich", 86.0, 140.0, 5, 26),
    SEEZUNGE("§bSeezunge", "ungewöhnlich", 40.0, 60.0, 30, 27),
    ROTZAHN("§bRotzahn", "ungewöhnlich", 20.0, 40.0, 28, 28),
    TARPUN("§bTarpun", "selten", 150.0, 250.0, 58, 29),
    GLITSCHFISCH("§bGlitschfisch", "gewöhnlich", 15.0, 20.0, 4, 30),
    SEETEUFEL("§bSeeteufel", "ungewöhnlich", 80.0, 120.0, 29, 31),
    ROCHEN("§bRochen", "ungewöhnlich", 30.0, 800.0, 34, 32),
    KARPFEN("§bKarpfen", "gewöhnlich", 40.0, 80.0, 5, 33),
    KOIKARPFEN("§bKoikarpfen", "gewöhnlich", 15.0, 40.0, 6, 34),
    ANGLERFISCH("§bAnglerfisch", "ungewöhnlich", 30.0, 45.0, 33, 35),
    TELESKOPAUGE("§bTeleskopauge", "episch", 10.0, 20.0, 95, 36),
    RANCHU("§bRanchu", "selten", 12.0, 15.0, 51, 37),
    KATZENWELS("§bKatzenwels", "gewöhnlich", 40.0, 50.0, 4, 38),
    TILAPIA("§bTilapia", "gewöhnlich", 13.0, 35.0, 4, 39),
    SAIBLING("§bSaibling", "gewöhnlich", 20.0, 35.0, 4, 40),
    GUPPY("§bGuppy", "ungewöhnlich", 2.5, 6.0, 27, 41),
    SKALAR("§bSkalar", "selten", 15.0, 20.0, 50, 42),
    KAMPFFISCH("§bKampffisch", "gewöhnlich", 6.0, 8.0, 4, 43),
    PIRANHA("§bPiranha", "gewöhnlich", 15.0, 40.0, 5, 44),
    SEEPFERDCHEN("§bSeepferdchen", "legendär", 18.0, 28.0, 250, 45),
    ANEMONENFISCH("§bAnemonenfisch", "episch", 10.0, 18.0, 120, 46),
    PALETTENDOKTORFISCH("§bPalettendoktorfisch", "episch", 25.0, 31.0, 120, 47),
    FLATTERFISCH("§bFlatterfisch", "ungewöhnlich", 40.0, 50.0, 30, 48),
    KUGELFISCH("§bKugelfisch", "selten", 2.0, 120.0, 55, 49),
    FLUNDER("§bFlunder", "gewöhnlich", 25.0, 30.0, 5, 50),
    KAISERSCHNAPPER("§bKaiserschnapper", "ungewöhnlich", 50.0, 116.0, 37, 51),
    KALMAR("§bKalmar", "legendär", 2.0, 1400.0, 250, 52),
    SAEGEHAI("§bSägehai", "legendär", 136.0, 153.0, 250, 53),
    HAMMERHAI("§bHammerhai", "legendär", 50.0, 610.0, 250, 54),
    RIEMENFISCH("§bRiemenfisch", "episch", 500.0, 1700.0, 140, 55),
    GLASKOPFFISCH("§bGlaskopffisch", "legendär", 5.0, 15.0, 250, 56),
    STEINFISCH("§bSteinfisch", "legendär", 30.0, 40.0, 250, 57),
    HANDFISCH("§bHandfisch", "episch", 2.0, 15.0, 98, 58),
    ZIGARRENHAI("Zigarrenhai", "episch", 40.0, 50.0, 105, 59),
    RIESENHAI("§bRiesenhai", "legendär", 600.0, 800.0, 250, 60),
    MEGADOLON("§bMegadolon", "legendär", 1500.0, 2000.0, 250, 61),
    FANGZAHNFISCH("§bFangzahnfisch", "episch", 10.0, 15.0, 100, 62),
    SEESTERN("§bSeestern", "episch", 10.0, 40.0, 120, 63),
    SEEDRACHE("§bSeedrache", "episch", 30.0, 46.0, 115, 64);



    private final String name;
    private final String rarity;                  // gewöhnlich, ungewöhnlich, selten, episch, legendär
    private final double minSize;
    private final double maxSize;
    private final int worth;
    private final int customModelData;

    Fish(String name, String rarity, double minSize, double maxSize, int worth, int customModelData) {
        this.name = name;
        this.rarity = rarity;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.worth = worth;
        this.customModelData = customModelData;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public double getMinSize() {
        return minSize;
    }

    public double getMaxSize() {
        return maxSize;
    }

    public double getRandomSize() {
        return Math.round((minSize + (maxSize - minSize) * new Random().nextDouble())*10.0) / 10.0;
    }

    public int getWorth() {
        return worth;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public String[] getLore() {
        ArrayList<String> ret = new ArrayList<>();
        if (worth == 1) {
            ret.add("§6" + worth + " Münze");
        } else {
            ret.add("§6" + worth + " Münzen");
        }
        ret.add(" ");
        ret.add("§7Kann beim Händler verkauft werden.");
        return ret.toArray(new String[0]);
    }

    public String[] getLoreRecord(Player player) {
        ArrayList<String> ret = new ArrayList<>();
        if (worth == 1) {
            ret.add("§6" + worth + " Münze");
        } else {
            ret.add("§6" + worth + " Münzen");
        }
        ret.add(" ");
        ret.add("§7Rekord: §6" + FishingPlayer.players.get(player.getUniqueId()).getRecord(getName()) + "cm");
        return ret.toArray(new String[0]);
    }

    public ItemStack getFish() {
        ItemStack itemStack = new ItemStack(Material.WHITE_DYE, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(getCustomModelData());
        itemMeta.setDisplayName(getName());
        itemMeta.setLore(List.of(getLore()));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getFishRecord(Player player) {
        ItemStack itemStack = new ItemStack(Material.WHITE_DYE, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(getCustomModelData());
        itemMeta.setDisplayName(getName());
        itemMeta.setLore(List.of(getLoreRecord(player)));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
