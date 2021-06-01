package info.oo.model;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ResultArmor {

    private Armor helmet;
    private Armor arm;
    private Armor chest;
    private Armor boot;
    private Armor classItem;

    private boolean powerfulFriends;
    private boolean radiantLight;
    private boolean stasis;

    public ResultArmor(Armor helmet, Armor arm, Armor chest, Armor boot, Armor classItem, boolean powerfulFriends, boolean radiantLight, boolean stasis) {
        this.helmet = helmet;
        this.arm = arm;
        this.chest = chest;
        this.boot = boot;
        this.classItem = classItem;
        this.powerfulFriends = powerfulFriends;
        this.radiantLight = radiantLight;
        this.stasis = stasis;
    }

    private void sumAttributes(int[] attributes1, int[] attributes2) {
        for(int i = 0; i < attributes1.length; i++)
            attributes1[i] += attributes2[i];
    }

    private void addMods(int[] attributes) {
        if(powerfulFriends)
            attributes[0] += 20;

        if(radiantLight)
            attributes[5] += 20;

        if(stasis) {
            attributes[1] += 20;
            attributes[2] += 10;
            attributes[4] += 10;
            attributes[5] += 10;
        }
    }

    public ArmorAttribute getAttributesSet() {
        int[] attributesHelmets = helmet.getArmorAttribute().getAttributesVetor();
        int[] attributesArms = arm.getArmorAttribute().getAttributesVetor();
        int[] attributesChets = chest.getArmorAttribute().getAttributesVetor();
        int[] attributesBoots = boot.getArmorAttribute().getAttributesVetor();
        int[] attributesClassItens = classItem.getArmorAttribute().getAttributesVetor();

        int[] attributes = new int[6];
        sumAttributes(attributes, attributesHelmets);
        sumAttributes(attributes, attributesArms);
        sumAttributes(attributes, attributesChets);
        sumAttributes(attributes, attributesBoots);
        sumAttributes(attributes, attributesClassItens);

        addMods(attributes);

        ArmorAttribute attribute = new ArmorAttribute(attributes[0], attributes[1], attributes[2],
                                                      attributes[3], attributes[4], attributes[5]);

        return attribute;
    }

    public Armor getHelmet() {
        return helmet;
    }

    public void setHelmet(Armor helmet) {
        this.helmet = helmet;
    }

    public Armor getArm() {
        return arm;
    }

    public void setArm(Armor arms) {
        this.arm = arms;
    }

    public Armor getChest() {
        return chest;
    }

    public void setChest(Armor chest) {
        this.chest = chest;
    }

    public Armor getBoot() {
        return boot;
    }

    public void setBoot(Armor boot) {
        this.boot = boot;
    }

    public Armor getClassItem() {
        return classItem;
    }

    public void setClassItem(Armor classItem) {
        this.classItem = classItem;
    }
}