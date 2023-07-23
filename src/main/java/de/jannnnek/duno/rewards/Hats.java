package de.jannnnek.duno.rewards;

import de.jannnnek.duno.region.Region;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Hats {

    LOBBY("§bGoldene Krone", 1, Region.LOBBY),
    TUEMPEL("§bSeerose", 2, Region.TUEMPEL),
    BACH("§bTiara", 3, Region.BACH),
    EISSCHOLLE("§bEisbär", 4, Region.EISSCHOLLE),
    FLUSS("§bAnglerhut", 5, Region.FLUSS),
    BERGSEE("§bAlpiner Hut", 6, Region.BERGSEE),
    SUMPF("§bFroschkönig-Krone", 7, Region.SUMPF),
    SCHIFFKUTTER("§bKapitänsmütze", 8, Region.SCHIFFKUTTER),
    TROPISCHE_INSEL("§bPalmenblätter", 9, Region.TROPISCHE_INSEL),
    MANGROVEN("§bKrokodilzahn-Halskette", 10, Region.MANGROVEN),
    HOEHLENSEE("§bGrubenarbeiterhelm", 11, Region.HOEHLENSEE);


    private final String name;
    private final int customModelData;
    private final Region region;


    Hats(String name, int customModelData, Region region) {
        this.name = name;
        this.customModelData = customModelData;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public Region getRegion() {
        return region;
    }

    public static Hats getHat(int customModelData) {
        for (Hats hats : Hats.values()) {
            if (customModelData == hats.getCustomModelData()) {
                return hats;
            }
        }
        return null;
    }
}
