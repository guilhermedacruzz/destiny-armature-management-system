package info.oo.model;

public class ArmorSet {
    private Armor[] armors;

    private boolean powerfulFriends;
    private boolean radiantLight;
    private boolean stasis;

    public ArmorSet(ArmorSet armorSet) {
        this.armors = armorSet.getArmors();
        this.powerfulFriends = armorSet.isPowerfulFriends();
        this.radiantLight = armorSet.isRadiantLight();
        this.stasis = armorSet.isStasis();
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
        }

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
