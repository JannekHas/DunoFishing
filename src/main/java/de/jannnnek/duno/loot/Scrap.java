package de.jannnnek.duno.loot;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public enum Scrap {
    TRASH("§bMüll", "", 1, 1, "§61 Münze", " ", "§7Kann beim Händler verkauft werden."),
    DRIFTWOOD("§bTreibholz", "", 2, 2, "§62 Münzen", " ", "§7Kann beim Händler verkauft werden."),
    SHOE("§bSchuh", "einen ", 1, 3, "§61 Münze", " ", "§7Kann beim Händler verkauft werden."),
    GLASSES("§bBrille","eine ", 3, 4, "§63 Münzen", " ", "§7Kann beim Händler verkauft werden."),
    CAN("§bDose","eine ", 3, 5, "§63 Münzen", " ", "§7Kann beim Händler verkauft werden."),
    PLASTIC_BAG("§bPlastiktüte","eine ", 2, 6, "§62 Münzen", " ", "§7Kann beim Händler verkauft werden."),
    OLD_NEWSPAPER("§bAlte Zeitung", "eine ", 2, 7, "§62 Münzen", " ", "§7Kann beim Händler verkauft werden.");

    private final String name;
    private final String akkusativ;
    private final int worth;
    private final int customModelData;
    private final String[] lore;

    Scrap(String name,String akkusativ, int worth, int customModelData, String... lore) {
        this.name = name;
        this.akkusativ = akkusativ;
        this.worth = worth;
        this.customModelData = customModelData;
        this.lore = lore;
    }

    public String getName() {
        return name;
    }

    public String getAkkusativ() {
        return akkusativ;
    }

    public int getWorth() {
        return worth;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public String[] getLore() {
        return lore;
    }

    public ArrayList getRandomScrap() {
        Scrap[] scraps = Scrap.values();
        Random random = new Random();
        int randomIndex = random.nextInt(scraps.length);
        Scrap randomScrap = scraps[randomIndex];
        ItemStack itemStack = new ItemStack(Material.RED_DYE, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(randomScrap.getCustomModelData());
        itemMeta.setDisplayName(randomScrap.getName());
        itemMeta.setLore(List.of(randomScrap.getLore()));
        itemStack.setItemMeta(itemMeta);
        ArrayList ret = new ArrayList<>();
        ret.add(itemStack);
        ret.add(randomScrap);
        return ret;
    }
}
