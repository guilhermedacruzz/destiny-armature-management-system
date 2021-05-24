package info.oo.model;

public class Inventory {
    private int lumen;
    private int legendaryFragments;
    private int ascendentFragments;
    private int enhancementPrism;
    private int improvementCore;
    private int ehancementModule;

    public Inventory(int lumen, int legendaryFragments, int ascendentFragments, int enhancementPrism, int improvementCore, int ehancementModule) {
        this.lumen = lumen;
        this.legendaryFragments = legendaryFragments;
        this.ascendentFragments = ascendentFragments;
        this.enhancementPrism = enhancementPrism;
        this.improvementCore = improvementCore;
        this.ehancementModule = ehancementModule;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "lumen=" + lumen +
                ", legendaryFragments=" + legendaryFragments +
                ", ascendentFragments=" + ascendentFragments +
                ", enhancementPrism=" + enhancementPrism +
                ", improvementCore=" + improvementCore +
                ", ehancementModule=" + ehancementModule +
                '}';
    }
}