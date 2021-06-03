package info.oo.model;

public class ArmorSet {
    private Armor[] armors;
    private ArmorAttribute attributes;

    public ArmorSet(Armor[] armors, boolean powerfulFriends, boolean radiantLight, boolean stasis) {
        this.armors = armors;
        attributes = generateAttributes(powerfulFriends, radiantLight, stasis);
    }

    private ArmorAttribute generateAttributes(boolean powerfulFriends, boolean radiantLight, boolean stasis) {
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

    public ArmorAttribute getAttributes() {
        return attributes;
    }
}
