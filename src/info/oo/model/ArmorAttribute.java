package info.oo.model;

public class ArmorAttribute {
    private int id;
    private int mobility;
    private int resilience;
    private int recovery;
    private int dicipline;
    private int intellect;
    private int strenght;

    public ArmorAttribute(int id, int mobility, int resilience, int recovery, int dicipline, int intellect, int strenght) {
        this.id = id;
        this.mobility = mobility;
        this.resilience = resilience;
        this.recovery = recovery;
        this.dicipline = dicipline;
        this.intellect = intellect;
        this.strenght = strenght;
    }

    public ArmorAttribute(int mobility, int resilience, int recovery, int dicipline, int intellect, int strenght) {
        this(-1, mobility, resilience, recovery, dicipline, intellect, strenght);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMobility() {
        return mobility;
    }

    public void setMobility(int mobility) {
        this.mobility = mobility;
    }

    public int getResilience() {
        return resilience;
    }

    public void setResilience(int resilience) {
        this.resilience = resilience;
    }

    public int getRecovery() {
        return recovery;
    }

    public void setRecovery(int recovery) {
        this.recovery = recovery;
    }

    public int getDicipline() {
        return dicipline;
    }

    public void setDicipline(int dicipline) {
        this.dicipline = dicipline;
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public int getStrenght() {
        return strenght;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
    }

    @Override
    public String toString() {
        return "Attributes{" +
                "id=" + id +
                ", mobility=" + mobility +
                ", resilience=" + resilience +
                ", recovery=" + recovery +
                ", dicipline=" + dicipline +
                ", intellect=" + intellect +
                ", strenght=" + strenght +
                '}';
    }
}
