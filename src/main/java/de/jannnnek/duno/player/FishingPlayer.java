package de.jannnnek.duno.player;

import de.jannnnek.duno.loot.Fish;
import de.jannnnek.duno.region.Region;
import de.jannnnek.duno.util.FileBuilder;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FishingPlayer {

    public static Map<UUID, FishingPlayer> players = new HashMap<>();
    private String name;
    private UUID uuid;
    private int region;
    private double coins;
    private int catchedFish;
    private int equipedHat;
    private double hering, lachs, thunfisch, dorade, zander, hecht, barsch, wels, makrele, forelle, scholle, goldfisch,
            mondFisch, rotbarsch, lengfisch, seelachs, stör, königslachs, blobfisch, zitronenfisch, kabeljau, schwertfisch,
            baars, hai, barramundi, mahiMahi, seezunge, rotzahn, tarpun, glitschfisch, seeteufel, rochen, karpfen, koikarpfen,
            anglerfisch, teleskopauge, ranchu, katzenwels, tilapia, saibling, guppy, skalar, kampffisch, piranha, seepferdchen,
            anemonenfisch, palettendoktorfisch, flatterfisch, kugelfisch, flunder, kaiserschnapper, kalmar, sägehai, hammerhai,
            riemenfisch, glaskopffisch, steinfisch, handfisch, zigarrenhai, riesenhai, megadolon, fangzahnfisch, seestern, seedrache;
    private FileBuilder f;

    public FishingPlayer(UUID uuid){
        f = new FileBuilder("plugins//DunoFishing//playerdata//", uuid.toString() + ".yml");
        this.name = Bukkit.getOfflinePlayer(uuid).getName();
        this.region = f.exist() ? f.getInt("Region") : 0;
        this.coins = f.exist() ? f.getDouble("Coins") : 0;
        this.catchedFish = f.exist() ? f.getInt("CatchedFish") : 0;
        this.equipedHat = f.exist() ? f.getInt("EquipedHat") : 0;
        this.hering = f.exist() ? f.getDouble("Hering") : 0.0;
        this.lachs = f.exist() ? f.getDouble("Lachs") : 0.0;
        this.thunfisch = f.exist() ? f.getDouble("Thunfisch") : 0.0;
        this.dorade = f.exist() ? f.getDouble("Dorade") : 0.0;
        this.zander = f.exist() ? f.getDouble("Zander") : 0.0;
        this.hecht = f.exist() ? f.getDouble("Hecht") : 0.0;
        this.barsch = f.exist() ? f.getDouble("Barsch") : 0.0;
        this.wels = f.exist() ? f.getDouble("Wels") : 0.0;
        this.makrele = f.exist() ? f.getDouble("Makrele") : 0.0;
        this.forelle = f.exist() ? f.getDouble("Forelle") : 0.0;
        this.scholle = f.exist() ? f.getDouble("Scholle") : 0.0;
        this.goldfisch = f.exist() ? f.getDouble("Goldfisch") : 0.0;
        this.mondFisch = f.exist() ? f.getDouble("Mondfisch") : 0.0;
        this.rotbarsch = f.exist() ? f.getDouble("Rotbarsch") : 0.0;
        this.lengfisch = f.exist() ? f.getDouble("Lengfisch") : 0.0;
        this.seelachs = f.exist() ? f.getDouble("Seelachs") : 0.0;
        this.stör = f.exist() ? f.getDouble("Stör") : 0.0;
        this.königslachs = f.exist() ? f.getDouble("Königslachs") : 0.0;
        this.blobfisch = f.exist() ? f.getDouble("Blobfisch") : 0.0;
        this.zitronenfisch = f.exist() ? f.getDouble("Zitronenfisch") : 0.0;
        this.kabeljau = f.exist() ? f.getDouble("Kabeljau") : 0.0;
        this.schwertfisch = f.exist() ? f.getDouble("Schwertfisch") : 0.0;
        this.baars = f.exist() ? f.getDouble("Baars") : 0.0;
        this.hai = f.exist() ? f.getDouble("Hai") : 0.0;
        this.barramundi = f.exist() ? f.getDouble("Barramundi") : 0.0;
        this.mahiMahi = f.exist() ? f.getDouble("MahiMahi") : 0.0;
        this.seezunge = f.exist() ? f.getDouble("Seezunge") : 0.0;
        this.rotzahn = f.exist() ? f.getDouble("Rotzahn") : 0.0;
        this.tarpun = f.exist() ? f.getDouble("Tarpun") : 0.0;
        this.glitschfisch = f.exist() ? f.getDouble("Glitschfisch") : 0.0;
        this.seeteufel = f.exist() ? f.getDouble("Seeteufel") : 0.0;
        this.rochen = f.exist() ? f.getDouble("Rochen") : 0.0;
        this.karpfen = f.exist() ? f.getDouble("Karpfen") : 0.0;
        this.koikarpfen = f.exist() ? f.getDouble("Koikarpfen") : 0.0;
        this.anglerfisch = f.exist() ? f.getDouble("Anglerfisch") : 0.0;
        this.teleskopauge = f.exist() ? f.getDouble("Teleskopauge") : 0.0;
        this.ranchu = f.exist() ? f.getDouble("Ranchu") : 0.0;
        this.katzenwels = f.exist() ? f.getDouble("Katzenwels") : 0.0;
        this.tilapia = f.exist() ? f.getDouble("Tilapia") : 0.0;
        this.saibling = f.exist() ? f.getDouble("Saibling") : 0.0;
        this.guppy = f.exist() ? f.getDouble("Guppy") : 0.0;
        this.skalar = f.exist() ? f.getDouble("Skalar") : 0.0;
        this.kampffisch = f.exist() ? f.getDouble("Kampffisch") : 0.0;
        this.piranha = f.exist() ? f.getDouble("Piranha") : 0.0;
        this.seepferdchen = f.exist() ? f.getDouble("Seepferdchen") : 0.0;
        this.anemonenfisch = f.exist() ? f.getDouble("Anemonenfisch") : 0.0;
        this.palettendoktorfisch = f.exist() ? f.getDouble("Palettendoktorfisch") : 0.0;
        this.flatterfisch = f.exist() ? f.getDouble("Flatterfisch") : 0.0;
        this.kugelfisch = f.exist() ? f.getDouble("Kugelfisch") : 0.0;
        this.flunder = f.exist() ? f.getDouble("Flunder") : 0.0;
        this.kaiserschnapper = f.exist() ? f.getDouble("Kaiserschnapper") : 0.0;
        this.kalmar = f.exist() ? f.getDouble("Kalmar") : 0.0;
        this.sägehai = f.exist() ? f.getDouble("Sägehai") : 0.0;
        this.hammerhai = f.exist() ? f.getDouble("Hammerhai") : 0.0;
        this.riemenfisch = f.exist() ? f.getDouble("Riemenfisch") : 0.0;
        this.glaskopffisch = f.exist() ? f.getDouble("Glaskopffisch") : 0.0;
        this.steinfisch = f.exist() ? f.getDouble("Steinfisch") : 0.0;
        this.handfisch = f.exist() ? f.getDouble("Handfisch") : 0.0;
        this.zigarrenhai = f.exist() ? f.getDouble("Zigarrenhai") : 0.0;
        this.riesenhai = f.exist() ? f.getDouble("Riesenhai") : 0.0;
        this.megadolon = f.exist() ? f.getDouble("Megadolon") : 0.0;
        this.fangzahnfisch = f.exist() ? f.getDouble("Fangzahnfisch") : 0.0;
        this.seestern = f.exist() ? f.getDouble("Seestern") : 0.0;
        this.seedrache = f.exist() ? f.getDouble("Seedrache") : 0.0;
        this.uuid = uuid;
        players.put(uuid, this);
    }

    public void save(){
        f.setValue("Region", region);
        f.setValue("Coins", coins);
        f.setValue("CatchedFish", catchedFish);
        f.setValue("EquipedHat", equipedHat);
        f.setValue("Hering", hering);
        f.setValue("Lachs", lachs);
        f.setValue("Thunfisch", thunfisch);
        f.setValue("Dorade", dorade);
        f.setValue("Zander", zander);
        f.setValue("Hecht", hecht);
        f.setValue("Barsch", barsch);
        f.setValue("Wels", wels);
        f.setValue("Makrele", makrele);
        f.setValue("Forelle", forelle);
        f.setValue("Scholle", scholle);
        f.setValue("Goldfisch", goldfisch);
        f.setValue("MondFisch", mondFisch);
        f.setValue("Rotbarsch", rotbarsch);
        f.setValue("Lengfisch", lengfisch);
        f.setValue("Seelachs", seelachs);
        f.setValue("Stör", stör);
        f.setValue("Königslachs", königslachs);
        f.setValue("Blobfisch", blobfisch);
        f.setValue("Zitronenfisch", zitronenfisch);
        f.setValue("Kabeljau", kabeljau);
        f.setValue("Schwertfisch", schwertfisch);
        f.setValue("Baars", baars);
        f.setValue("Hai", hai);
        f.setValue("Barramundi", barramundi);
        f.setValue("MahiMahi", mahiMahi);
        f.setValue("Seezunge", seezunge);
        f.setValue("Rotzahn", rotzahn);
        f.setValue("Tarpun", tarpun);
        f.setValue("Glitschfisch", glitschfisch);
        f.setValue("Seeteufel", seeteufel);
        f.setValue("Rochen", rochen);
        f.setValue("Karpfen", karpfen);
        f.setValue("Koikarpfen", koikarpfen);
        f.setValue("Anglerfisch", anglerfisch);
        f.setValue("Teleskopauge", teleskopauge);
        f.setValue("Ranchu", ranchu);
        f.setValue("Katzenwels", katzenwels);
        f.setValue("Tilapia", tilapia);
        f.setValue("Saibling", saibling);
        f.setValue("Guppy", guppy);
        f.setValue("Skalar", skalar);
        f.setValue("Kampffisch", kampffisch);
        f.setValue("Piranha", piranha);
        f.setValue("Seepferdchen", seepferdchen);
        f.setValue("Anemonenfisch", anemonenfisch);
        f.setValue("Palettendoktorfisch", palettendoktorfisch);
        f.setValue("Flatterfisch", flatterfisch);
        f.setValue("Kugelfisch", kugelfisch);
        f.setValue("Flunder", flunder);
        f.setValue("Kaiserschnapper", kaiserschnapper);
        f.setValue("Kalmar", kalmar);
        f.setValue("Sägehai", sägehai);
        f.setValue("Hammerhai", hammerhai);
        f.setValue("Riemenfisch", riemenfisch);
        f.setValue("Glaskopffisch", glaskopffisch);
        f.setValue("Steinfisch", steinfisch);
        f.setValue("Handfisch", handfisch);
        f.setValue("Zigarrenhai", zigarrenhai);
        f.setValue("Riesenhai", riesenhai);
        f.setValue("Megadolon", megadolon);
        f.setValue("Fangzahnfisch", fangzahnfisch);
        f.setValue("Seestern", seestern);
        f.setValue("Seedrache", seedrache);
        f.save();
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getRegion() {
        return region;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setEquipedHat(int equipedHat) {
        this.equipedHat = equipedHat;
    }

    public int getEquipedHat() {
        return equipedHat;
    }

    public int getCoins() {
        return (int) Math.round(coins);
    }

    public void setCatchedFish(int catchedFish) {
        this.catchedFish = catchedFish;
    }

    public int getCatchedFish() {
        return catchedFish;
    }

    public void addCatchedFish(int amount) {
        this.catchedFish = this.catchedFish + amount;
    }

    public double getRecord(String fish) {
        return switch (fish) {
            case "§bHering" -> hering;
            case "§bLachs" -> lachs;
            case "§bThunfisch" -> thunfisch;
            case "§bDorade" -> dorade;
            case "§bZander" -> zander;
            case "§bHecht" -> hecht;
            case "§bBarsch" -> barsch;
            case "§bWels" -> wels;
            case "§bMakrele" -> makrele;
            case "§bForelle" -> forelle;
            case "§bScholle" -> scholle;
            case "§bGoldfisch" -> goldfisch;
            case "§bMondfisch" -> mondFisch;
            case "§bRotbarsch" -> rotbarsch;
            case "§bLengfisch" -> lengfisch;
            case "§bSeelachs" -> seelachs;
            case "§bStör" -> stör;
            case "§bKönigslachs" -> königslachs;
            case "§bBlobfisch" -> blobfisch;
            case "§bZitronenfisch" -> zitronenfisch;
            case "§bKabeljau" -> kabeljau;
            case "§bSchwertfisch" -> schwertfisch;
            case "§bBaars" -> baars;
            case "§bHai" -> hai;
            case "§bBarramundi" -> barramundi;
            case "§bMahi Mahi" -> mahiMahi;
            case "§bSeezunge" -> seezunge;
            case "§bRotzahn" -> rotzahn;
            case "§bTarpun" -> tarpun;
            case "§bGlitschfisch" -> glitschfisch;
            case "§bSeeteufel" -> seeteufel;
            case "§bRochen" -> rochen;
            case "§bKarpfen" -> karpfen;
            case "§bKoikarpfen" -> koikarpfen;
            case "§bAnglerfisch" -> anglerfisch;
            case "§bTeleskopauge" -> teleskopauge;
            case "§bRanchu" -> ranchu;
            case "§bKatzenwels" -> katzenwels;
            case "§bTilapia" -> tilapia;
            case "§bSaibling" -> saibling;
            case "§bGuppy" -> guppy;
            case "§bSkalar" -> skalar;
            case "§bKampffisch" -> kampffisch;
            case "§bPiranha" -> piranha;
            case "§bSeepferdchen" -> seepferdchen;
            case "§bAnemonenfisch" -> anemonenfisch;
            case "§bPalettendoktorfisch" -> palettendoktorfisch;
            case "§bFlatterfisch" -> flatterfisch;
            case "§bKugelfisch" -> kugelfisch;
            case "§bFlunder" -> flunder;
            case "§bKaiserschnapper" -> kaiserschnapper;
            case "§bKalmar" -> kalmar;
            case "§bSägehai" -> sägehai;
            case "§bHammerhai" -> hammerhai;
            case "§bRiemenfisch" -> riemenfisch;
            case "§bGlaskopffisch" -> glaskopffisch;
            case "§bSteinfisch" -> steinfisch;
            case "§bHandfisch" -> handfisch;
            case "§bZigarrenhai" -> zigarrenhai;
            case "§bRiesenhai" -> riesenhai;
            case "§bMegadolon" -> megadolon;
            case "§bFangzahnfisch" -> fangzahnfisch;
            case "§bSeestern" -> seestern;
            case "§bSeedrache" -> seedrache;
            default -> 0.0;
        };
    }

    public void setRecord(String fish, double size) {
        switch (fish) {
            case "§bHering" -> hering = size;
            case "§bLachs" -> lachs = size;
            case "§bThunfisch" -> thunfisch = size;
            case "§bDorade" -> dorade = size;
            case "§bZander" -> zander = size;
            case "§bHecht" -> hecht = size;
            case "§bBarsch" -> barsch = size;
            case "§bWels" -> wels = size;
            case "§bMakrele" -> makrele = size;
            case "§bForelle" -> forelle = size;
            case "§bScholle" -> scholle = size;
            case "§bGoldfisch" -> goldfisch = size;
            case "§bMondfisch" -> mondFisch = size;
            case "§bRotbarsch" -> rotbarsch = size;
            case "§bLengfisch" -> lengfisch = size;
            case "§bSeelachs" -> seelachs = size;
            case "§bStör" -> stör = size;
            case "§bKönigslachs" -> königslachs = size;
            case "§bBlobfisch" -> blobfisch = size;
            case "§bZitronenfisch" -> zitronenfisch = size;
            case "§bKabeljau" -> kabeljau = size;
            case "§bSchwertfisch" -> schwertfisch = size;
            case "§bBaars" -> baars = size;
            case "§bHai" -> hai = size;
            case "§bBarramundi" -> barramundi = size;
            case "§bMahi Mahi" -> mahiMahi = size;
            case "§bSeezunge" -> seezunge = size;
            case "§bRotzahn" -> rotzahn = size;
            case "§bTarpun" -> tarpun = size;
            case "§bGlitschfisch" -> glitschfisch = size;
            case "§bSeeteufel" -> seeteufel = size;
            case "§bRochen" -> rochen = size;
            case "§bKarpfen" -> karpfen = size;
            case "§bKoikarpfen" -> koikarpfen = size;
            case "§bAnglerfisch" -> anglerfisch = size;
            case "§bTeleskopauge" -> teleskopauge = size;
            case "§bRanchu" -> ranchu = size;
            case "§bKatzenwels" -> katzenwels = size;
            case "§bTilapia" -> tilapia = size;
            case "§bSaibling" -> saibling = size;
            case "§bGuppy" -> guppy = size;
            case "§bSkalar" -> skalar = size;
            case "§bKampffisch" -> kampffisch = size;
            case "§bPiranha" -> piranha = size;
            case "§bSeepferdchen" -> seepferdchen = size;
            case "§bAnemonenfisch" -> anemonenfisch = size;
            case "§bPalettendoktorfisch" -> palettendoktorfisch = size;
            case "§bFlatterfisch" -> flatterfisch = size;
            case "§bKugelfisch" -> kugelfisch = size;
            case "§bFlunder" -> flunder = size;
            case "§bKaiserschnapper" -> kaiserschnapper = size;
            case "§bKalmar" -> kalmar = size;
            case "§bSägehai" -> sägehai = size;
            case "§bHammerhai" -> hammerhai = size;
            case "§bRiemenfisch" -> riemenfisch = size;
            case "§bGlaskopffisch" -> glaskopffisch = size;
            case "§bSteinfisch" -> steinfisch = size;
            case "§bHandfisch" -> handfisch = size;
            case "§bZigarrenhai" -> zigarrenhai = size;
            case "§bRiesenhai" -> riesenhai = size;
            case "§bMegadolon" -> megadolon = size;
            case "§bFangzahnfisch" -> fangzahnfisch = size;
            case "§bSeestern" -> seestern = size;
            case "§bSeedrache" -> seedrache = size;
        }
    }

    public void addCoins(int amount) {
        this.coins = this.coins + amount;
    }

    public boolean collectionIsFull(Region region) {
        for (Fish fish : region.getFish()) {
            if (getRecord(fish.getName()) == 0.0) {
                return false;
            }
        }
        return true;
    }

    public void reset() {
        setRegion(0);
        setCoins(0);
        setCatchedFish(0);
        setEquipedHat(0);
        for (Fish fish : Fish.values()) {
            setRecord(fish.getName(), 0.0);
        }
        save();
    }
}
