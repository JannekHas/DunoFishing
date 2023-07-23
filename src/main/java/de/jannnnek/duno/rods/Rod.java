package de.jannnnek.duno.rods;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Rod {

    STARTER("§7Starter Angel", 1, 90, 10, 0, 0, 0, 0),
    VERBESSERTE("§bVerbesserte Angel",2, 85, 10, 5, 0, 0, 0),
    FORTGESCHRITTENE("§aFortgeschrittene Angel", 3, 83, 10, 5, 2, 0, 0),
    PROFI("§4Profi Angel",4, 82, 10, 5, 2, 1, 0),
    ELITE("§5Elite Angel", 5, 78, 11, 6, 3, 2, 0),
    MEISTER("§cMeister Angel", 6, 74, 12, 7, 4, 3, 2),
    LEGENDÄRE("§6Legendäre Angel", 7, 70, 13, 8, 5, 4, 4),
    DUPLIZIERTE("§8Duplizierte Angel", 8, 78, 11, 6, 3, 2, 20);

    private final String name;
    private final int customModelData;
    private final double gewöhnlich;
    private final double ungewöhnlich;
    private final double selten;
    private final double episch;
    private final double legendär;
    private final double doubledrop;


    Rod(String name, int customModelData, double gewöhnlich, double ungewöhnlich, double selten, double episch, double legendär, double doubledrop) {
        this.name = name;
        this.customModelData = customModelData;
        this.gewöhnlich = gewöhnlich;
        this.ungewöhnlich = ungewöhnlich;
        this.selten = selten;
        this.episch = episch;
        this.legendär = legendär;
        this.doubledrop = doubledrop;
    }

    public String getName() {
        return name;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public double getGewöhnlich() {
        return gewöhnlich;
    }

    public double getUngewöhnlich() {
        return ungewöhnlich;
    }

    public double getSelten() {
        return selten;
    }

    public double getEpisch() {
        return episch;
    }

    public double getLegendär() {
        return legendär;
    }

    public double getDoubledrop() {
        return doubledrop;
    }

    public Rod getRod(ItemStack itemStack) {
        for (Rod rod : values()) {
            if (rod.customModelData == itemStack.getItemMeta().getCustomModelData()) {
                return rod;
            }
        }
        return null;
    }

    public ItemStack getRod() {
        ItemStack itemStack = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(getCustomModelData());
        itemMeta.setDisplayName(getName());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public String getRarityAsString() {
        double random = Math.random() * 100; // Zufällige Zahl zwischen 0 und 100 generieren

        if (random <= gewöhnlich) {
            return "gewöhnlich";
        } else if (random <= gewöhnlich + ungewöhnlich) {
            return "ungewöhnlich";
        } else if (random <= gewöhnlich + ungewöhnlich + selten) {
            return "selten";
        } else if (random <= gewöhnlich + ungewöhnlich + selten + episch) {
            return "episch";
        } else if (random <= gewöhnlich + ungewöhnlich + selten + episch + legendär) {
            return "legendär";
        }

        return ""; // Falls die Rarität nicht erkannt wird, wird ein leerer String zurückgegeben
    }
}
