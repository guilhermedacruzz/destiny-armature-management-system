package info.oo.model;

public class ResultArmor {

    private Armor helmet;
    private Armor arms;
    private Armor chest;
    private Armor boot;
    private Armor classItem;

    public ResultArmor(Armor helmet, Armor arms, Armor chest, Armor boot, Armor classItem) {
        this.helmet = helmet;
        this.arms = arms;
        this.chest = chest;
        this.boot = boot;
        this.classItem = classItem;
    }

    public ArmorAttribute getAttributesSet() {
        int mobility = helmet.getArmorAttribute().getMobility() +
                       arms.getArmorAttribute().getMobility() +
                       chest.getArmorAttribute().getMobility() +
                       boot.getArmorAttribute().getMobility() +
                       classItem.getArmorAttribute().getMobility();

        ArmorAttribute attribute = new ArmorAttribute(mobility, 0, 0, 0,0,0);

        return attribute;
    }

    public Armor getHelmet() {
        return helmet;
    }

    public void setHelmet(Armor helmet) {
        this.helmet = helmet;
    }

    public Armor getArms() {
        return arms;
    }

    public void setArms(Armor arms) {
        this.arms = arms;
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
