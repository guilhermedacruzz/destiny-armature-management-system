package info.oo.model;

public class Armor {
    private int id;
    private String guardianClass;
    private String type;
    private String rarity;
    private boolean status;
    private Attribuites attribuites;
    private boolean statusMasterprice;
    private String element;

    public Armor(int id, String guardianClass, String type, String rarity,
                 boolean status, Attribuites attribuites, boolean statusMasterprice, String element) {
        this.id = id;
        this.guardianClass = guardianClass;
        this.type = type;
        this.rarity = rarity;
        this.status = status;
        this.attribuites = attribuites;
        this.statusMasterprice = statusMasterprice;
        this.element = element;
    }

    public Armor(String guardianClass, String type, String rarity, boolean status, Attribuites attribuites, boolean statusMasterprice, String element) {
        this(-1, guardianClass, type, rarity, status, attribuites, statusMasterprice, element);
    }
}
