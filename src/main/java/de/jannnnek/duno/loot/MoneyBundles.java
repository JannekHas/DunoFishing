package de.jannnnek.duno.loot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum MoneyBundles {

    TINY("§bWinziges Geldbündel", 25, 1, 60,"§625 Münzen", " ", "RECHSTKLICK UNICODE §7um die Münzen zu erhalten."),
    SMALL("§bKleines Geldbündel", 50, 2, 20,"§650 Münzen", " ", "RECHSTKLICK UNICODE §7um die Münzen zu erhalten."),
    MEDIUM("§bMittleres Geldbündel", 100, 3,10, "§6100 Münzen", " ", "RECHSTKLICK UNICODE §7um die Münzen zu erhalten."),
    LARGE("§bGroßes Geldbündel", 200, 4,5, "§6200 Münzen", " ", "RECHSTKLICK UNICODE §7um die Münzen zu erhalten."),
    HUGE("§bRiesiges Geldbündel", 500, 5,3, "§6500 Münzen", " ", "RECHSTKLICK UNICODE §7um die Münzen zu erhalten."),
    GIGANTIC("§bGigantisches Geldbündel", 1000, 6, 1.5,"§61000 Münzen", " ", "RECHSTKLICK UNICODE §7um die Münzen zu erhalten."),
    MASSIVE("§bMassives Geldbündel", 5000, 7, 0.4,"§65000 Münzen", " ", "RECHSTKLICK UNICODE §7um die Münzen zu erhalten."),
    ENORMOUS("§bEnormes Geldbündel", 10000, 8, 0.1,"§610000 Münzen", " ", "RECHSTKLICK UNICODE §7um die Münzen zu erhalten.");

    private final String name;
    private final int worth;
    private final int customModelData;
    private final String[] lore;
    private final double weight;

    MoneyBundles(String name, int worth, int customModelData, double weight, String... lore) {
        this.name = name;
        this.worth = worth;
        this.customModelData = customModelData;
        this.lore = lore;
        this.weight = weight;
    }

    public String getName() {
        return name;
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

    public double getWeight() {
        return weight;
    }

    public ArrayList getRandomWeightedBundle() {
        ArrayList ret = new ArrayList<>();
        Random random = new Random();
        double totalWeight = 0;
        for (MoneyBundles bundle : MoneyBundles.values()) {
            totalWeight += bundle.getWeight();
        }
        double randomWeight = random.nextDouble(totalWeight);
        //TODO debug
        Bukkit.broadcastMessage("MoneyBundle TotalWeight: " + totalWeight + ", randomWeigth: " + randomWeight);
        int cumulativeWeight = 0;
        for (MoneyBundles bundle : MoneyBundles.values()) {
            cumulativeWeight += bundle.getWeight();
            if (randomWeight <= cumulativeWeight) {
                ret.add(bundle.getBundle());
                ret.add(bundle);
                return ret;
            }
        }
        ret.add(TINY.getBundle());
        ret.add(TINY);
        return ret;
    }

    public ItemStack getBundle() {
        ItemStack itemStack = new ItemStack(Material.IRON_NUGGET, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(getCustomModelData());
        itemMeta.setDisplayName(getName());
        itemMeta.setLore(List.of(getLore()));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}

