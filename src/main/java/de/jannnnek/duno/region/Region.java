package de.jannnnek.duno.region;

import de.jannnnek.duno.loot.Fish;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Random;

public enum Region {

    LOBBY("§6Lobby", "§fLobby", "world", 15.5, 60, 17.5, 0.0f, 0.0f, 0, Fish.TELESKOPAUGE, Fish.HERING, Fish.LACHS, Fish.ZANDER, Fish.FORELLE, Fish.KABELJAU, Fish.BAARS, Fish.GLITSCHFISCH, Fish.KARPFEN, Fish.SAIBLING, Fish.FLUNDER, Fish.BLOBFISCH, Fish.GOLDFISCH, Fish.ROTBARSCH, Fish.ZITRONENFISCH, Fish.BARRAMUNDI, Fish.KUGELFISCH, Fish.DORADE, Fish.BARSCH, Fish.MAKRELE, Fish.SCHOLLE),
    TUEMPEL("§6Tümpel", "§fTümpel", "tumpel", 15.5, 60, 17.5, 0.0f, 0.0f, 1, Fish.TELESKOPAUGE, Fish.HANDFISCH, Fish.HERING, Fish.LACHS, Fish.HECHT, Fish.BAARS, Fish.MAHI_MAHI, Fish.KOIKARPFEN, Fish.KATZENWELS, Fish.TILAPIA, Fish.PIRANHA, Fish.FLUNDER, Fish.GLASKOPFFISCH, Fish.STEINFISCH, Fish.STOER, Fish.ZITRONENFISCH, Fish.RANCHU, Fish.SKALAR, Fish.WELS, Fish.BARSCH, Fish.ROTZAHN, Fish.GUPPY),
    BACH("§6Bach", "§fBach", "bach", 15.5, 60, 17.5, 0.0f, 0.0f, 2, Fish.LENGFISCH, Fish.SEEDRACHE, Fish.HERING, Fish.LACHS, Fish.HECHT, Fish.FORELLE, Fish.KABELJAU, Fish.BAARS, Fish.GLITSCHFISCH, Fish.KOIKARPFEN, Fish.SAIBLING, Fish.KAMPFFISCH, Fish.FLUNDER, Fish.STEINFISCH, Fish.GOLDFISCH, Fish.ROTBARSCH, Fish.STOER, Fish.KOENIGSLACHS, Fish.ZITRONENFISCH, Fish.BARRAMUNDI, Fish.TARPUN, Fish.WELS, Fish.DORADE, Fish.BARSCH, Fish.SEEZUNGE, Fish.GUPPY, Fish.FLATTERFISCH),
    EISSCHOLLE("§6Eisscholle", "§fEisscholle", "eis", 15.5, 60, 17.5, 0.0f, 0.0f, 3, Fish.MONDFISCH, Fish.SCHWERTFISCH, Fish.HERING, Fish.LACHS, Fish.THUNFISCH, Fish.FORELLE, Fish.KATZENWELS, Fish.HAMMERHAI, Fish.RIESENHAI, Fish.ROTBARSCH, Fish.STOER, Fish.KOENIGSLACHS, Fish.HAI, Fish.BARSCH, Fish.SCHOLLE, Fish.SEELACHS, Fish.KAISERSCHNAPPER),
    FLUSS("§6Fluss", "§fFluss", "fluss", 15.5, 60, 17.5, 0.0f, 0.0f, 4, Fish.LENGFISCH, Fish.SEEDRACHE, Fish.HERING, Fish.LACHS, Fish.THUNFISCH, Fish.HECHT, Fish.FORELLE, Fish.KABELJAU, Fish.GLITSCHFISCH, Fish.KATZENWELS, Fish.TILAPIA, Fish.SAIBLING, Fish.PIRANHA, Fish.FLUNDER, Fish.SAEGEHAI, Fish.HAMMERHAI, Fish.GOLDFISCH, Fish.STOER, Fish.KOENIGSLACHS, Fish.BARRAMUNDI, Fish.RANCHU, Fish.KUGELFISCH, Fish.WELS, Fish.DORADE, Fish.SEEZUNGE, Fish.GUPPY, Fish.FLATTERFISCH, Fish.KAISERSCHNAPPER),
    BERGSEE("§6Bergsee", "§fBergsee", "bergsee", 15.5, 60, 17.5, 0.0f, 0.0f, 5, Fish.LENGFISCH, Fish.ANEMONENFISCH, Fish.PALETTENDOKTORFISCH, Fish.HERING, Fish.LACHS, Fish.ZANDER, Fish.FORELLE, Fish.BAARS, Fish.KOIKARPFEN, Fish.KATZENWELS, Fish.SAIBLING, Fish.KAMPFFISCH, Fish.GLASKOPFFISCH, Fish.ROTBARSCH, Fish.STOER, Fish.ZITRONENFISCH, Fish.HAI, Fish.TARPUN, Fish.DORADE, Fish.SEEZUNGE, Fish.FLATTERFISCH, Fish.KAISERSCHNAPPER),
    SUMPF("§6Sumpf", "§fSumpf", "sumpf", 15.5, 60, 17.5, 0.0f, 0.0f, 6, Fish.TELESKOPAUGE, Fish.HANDFISCH, Fish.HERING, Fish.HECHT, Fish.KABELJAU, Fish.MAHI_MAHI, Fish.KATZENWELS, Fish.FLUNDER, Fish.GLASKOPFFISCH, Fish.TARPUN, Fish.BARSCH, Fish.GUPPY),
    SCHIFFKUTTER("§6Schiffkutter", "§fSchiffkutter", "schiffkutter", 15.5, 60, 17.5, 0.0f, 0.0f, 7, Fish.MONDFISCH, Fish.SCHWERTFISCH, Fish.RIEMENFISCH, Fish.FANGZAHNFISCH, Fish.HERING, Fish.LACHS, Fish.THUNFISCH, Fish.ZANDER, Fish.FORELLE, Fish.KABELJAU, Fish.BAARS, Fish.GLITSCHFISCH, Fish.TILAPIA, Fish.KALMAR, Fish.SAEGEHAI, Fish.RIESENHAI, Fish.MEGADOLON, Fish.ROTBARSCH, Fish.STOER, Fish.HAI, Fish.BARRAMUNDI, Fish.TARPUN, Fish.RANCHU, Fish.WELS, Fish.BARSCH, Fish.MAKRELE, Fish.SCHOLLE, Fish.SEELACHS, Fish.SEETEUFEL, Fish.ANGLERFISCH, Fish.KAISERSCHNAPPER),
    TROPISCHE_INSEL("§6Tropische Insel", "§fTropische Insel", "insel", 15.5, 60, 17.5, 0.0f, 0.0f, 8, Fish.MONDFISCH, Fish.ANEMONENFISCH, Fish.PALETTENDOKTORFISCH, Fish.SEESTERN, Fish.MAHI_MAHI, Fish.KOIKARPFEN, Fish.TILAPIA, Fish.KAMPFFISCH, Fish.PIRANHA, Fish.SEEPFERDCHEN, Fish.KOENIGSLACHS, Fish.HAI, Fish.RANCHU, Fish.SKALAR, Fish.KUGELFISCH, Fish.ROTZAHN, Fish.ROCHEN),
    MANGROVEN("§6Mangroven", "§fMangroven", "mangroven", 15.5, 60, 17.5, 0.0f, 0.0f, 9, Fish.LENGFISCH, Fish.ZIGARRENHAI, Fish.THUNFISCH, Fish.HECHT, Fish.MAHI_MAHI, Fish.KOIKARPFEN, Fish.KATZENWELS, Fish.KAMPFFISCH, Fish.FLUNDER, Fish.GLASKOPFFISCH, Fish.GOLDFISCH, Fish.STOER, Fish.BARRAMUNDI, Fish.SKALAR, Fish.KUGELFISCH, Fish.BARSCH, Fish.ROTZAHN, Fish.ROCHEN, Fish.GUPPY),
    HOEHLENSEE("§6Höhlensee", "§fHöhlensee", "hoehlensee", 15.5, 60, 17.5, 0.0f, 0.0f, 10, Fish.MONDFISCH, Fish.ZIGARRENHAI, Fish.HERING, Fish.THUNFISCH, Fish.ZANDER, Fish.BAARS, Fish.MAHI_MAHI, Fish.GLITSCHFISCH, Fish.SAIBLING, Fish.BLOBFISCH, Fish.STEINFISCH, Fish.ROTBARSCH, Fish.KOENIGSLACHS, Fish.HAI, Fish.TARPUN, Fish.RANCHU, Fish.WELS, Fish.ROTZAHN, Fish.FLATTERFISCH);

    private final String name;
    private final String collectionInvName;
    private final String world;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;
    private final int index;
    private final Fish[] fish;

    Region(String name, String collectionInvName, String world, double x, double y, double z, float yaw, float pitch, int index, Fish... fish) {
        this.name = name;
        this.collectionInvName = collectionInvName;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.index = index;
        this.fish = fish;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public Fish[] getFish() {
        return fish;
    }

    public String getStringWorld() {
        return world;
    }

    public World getWorld() {
        return Bukkit.getWorld(getStringWorld());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public String getCollectionInvName() {
        return collectionInvName;
    }

    public static Region getRegion(World world) {
        for (Region region : values()) {
            if (region.getWorld().equals(world)) {
                return region;
            }
        }
        return null;
    }

    public Fish getLoot(String rarity) {
        ArrayList<Fish> possible = new ArrayList<>();
        for (Fish fish : fish) {
            if (fish.getRarity().equalsIgnoreCase(rarity)) {
                possible.add(fish);
            }
        }
        if (possible.size() != 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(possible.size());
            return possible.get(randomIndex);
        }
        return null;
    }
}
