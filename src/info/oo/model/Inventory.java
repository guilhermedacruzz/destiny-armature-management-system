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

    public int getLumen() {
        return lumen;
    }

    public void setLumen(int lumen) {
        this.lumen = lumen;
    }

    public int getLegendaryFragments() {
        return legendaryFragments;
    }

    public void setLegendaryFragments(int legendaryFragments) {
        this.legendaryFragments = legendaryFragments;
    }

    public int getAscendentFragments() {
        return ascendentFragments;
    }

    public void setAscendentFragments(int ascendentFragments) {
        this.ascendentFragments = ascendentFragments;
    }

    public int getEnhancementPrism() {
        return enhancementPrism;
    }

    public void setEnhancementPrism(int enhancementPrism) {
        this.enhancementPrism = enhancementPrism;
    }

    public int getImprovementCore() {
        return improvementCore;
    }

    public void setImprovementCore(int improvementCore) {
        this.improvementCore = improvementCore;
    }

    public int getEhancementModule() {
        return ehancementModule;
    }

    public void setEhancementModule(int ehancementModule) {
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