package info.oo.model;

public class Attribuites {
    private int id;
    private int mobility;
    private int resilience;
    private int recovery;
    private int dicipline;
    private int intellect;
    private int strenght;

    public Attribuites(int id, int mobility, int resilience, int recovery, int dicipline, int intellect, int strenght) {
        this.id = id;
        this.mobility = mobility;
        this.resilience = resilience;
        this.recovery = recovery;
        this.dicipline = dicipline;
        this.intellect = intellect;
        this.strenght = strenght;
    }

    public Attribuites(int mobility, int resilience, int recovery, int dicipline, int intellect, int strenght) {
        this(-1, mobility, resilience, recovery, dicipline, intellect, strenght);
    }
}
