package de.jannnnek.duno.rewards;

public enum Title {

    STARTER("§fAngler", ""),
    GEWOEHNLICH("§7Gewöhnlicher Angler", "gewöhnlich"),
    UNGEWOEHNLICH("§bUngewöhnlicher Angler", "ungewöhnlich"),
    SELTEN("§3Seltener Angler", "selten"),
    EPISCH("§dEpischer Angler", "episch"),
    LEGENDAER("§eLegendärer Angler", "legendär");

    private final String titel;
    private final String rarity;


    Title(String titel, String rarity) {
        this.titel = titel;
        this.rarity = rarity;
    }

    public String getTitel(String rarity) {
        for (Title title1 : Title.values()) {
            if (rarity.equals(title1.rarity)) {
                return title1.titel;
            }
        }
        return STARTER.titel;
    }
}
