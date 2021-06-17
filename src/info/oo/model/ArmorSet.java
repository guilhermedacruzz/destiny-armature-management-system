package info.oo.model;

import java.util.Arrays;

public class ArmorSet {
    private Armor[] armors;

    private boolean powerfulFriends;
    private boolean radiantLight;
    private boolean stasis;

    public ArmorSet(ArmorSet armorSet) {
        this(armorSet.getArmors().clone(), armorSet.isPowerfulFriends(), armorSet.isRadiantLight(), armorSet.isStasis());
    }

    public ArmorSet(Armor[] armors, boolean powerfulFriends, boolean radiantLight, boolean stasis) {
        this.armors = armors;
        this.powerfulFriends = powerfulFriends;
        this.radiantLight = radiantLight;
        this.stasis = stasis;
    }

    public ArmorAttribute generateAttributes() {
        int mobility = 0, resilience = 0, recovery = 0, dicipline = 0, intellect = 0, strenght = 0;

        for(Armor armor: armors) {
            mobility += armor.getArmorAttribute().getMobility();
            resilience += armor.getArmorAttribute().getResilience();
            recovery += armor.getArmorAttribute().getRecovery();
            dicipline += armor.getArmorAttribute().getDicipline();
            intellect += armor.getArmorAttribute().getIntellect();
            strenght += armor.getArmorAttribute().getStrenght();

            if(!armor.isStatusMasterprice()) {
                mobility += 2;
                resilience += 2;
                recovery += 2;
                dicipline += 2;
                intellect += 2;
                strenght += 2;
            }        }

        if(powerfulFriends)
            mobility += 20;

        if(radiantLight)
            strenght += 20;

        if(stasis) {
            resilience += 10;
            recovery += 10;
            intellect += 10;
            strenght += 20;
        }

        return new ArmorAttribute(mobility, resilience, recovery, dicipline, intellect, strenght);
    }

    public Inventory generateSpent() {
        int lumen = 0;
        int legendaryFragments = 0;
        int ascendentFragments = 0;
        int enhancementPrism = 0;
        int improvementCore = 0;
        int enhancementModule = 0;

        System.out.println(Arrays.toString(armors));
        for(Armor currentArmors: armors) {
            if(!currentArmors.getElement().equals("Arco") || !currentArmors.isStatusMasterprice()) {
                if(currentArmors.getRarity().equals("Exótico")) {
                    lumen += 15000;
                    ascendentFragments += 3;
                    enhancementPrism += 6;
                    improvementCore += 6;
                }
                else {
                    lumen += 5000;
                    ascendentFragments += 1;
                    enhancementModule += 3;
                    improvementCore += 3;
                }

                legendaryFragments += 15;
                enhancementModule += 1;
            }
        }

        return new Inventory(lumen, legendaryFragments, ascendentFragments, enhancementPrism, improvementCore, enhancementModule);
    }

    public Armor[] getArmors() {
        return armors;
    }

    public void setArmors(Armor[] armors) {
        this.armors = armors;
    }

    public boolean isPowerfulFriends() {
        return powerfulFriends;
    }

    public void setPowerfulFriends(boolean powerfulFriends) {
        this.powerfulFriends = powerfulFriends;
    }

    public boolean isRadiantLight() {
        return radiantLight;
    }

    public void setRadiantLight(boolean radiantLight) {
        this.radiantLight = radiantLight;
    }

    public boolean isStasis() {
        return stasis;
    }

    public void setStasis(boolean stasis) {
        this.stasis = stasis;
    }
}
