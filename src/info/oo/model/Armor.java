package info.oo.model;

public class Armor {
    private int id;
    private String name;
    private String guardianClass;
    private String type;
    private String rarity;
    private boolean status;
    private ArmorAttribute armorAttribute;
    private boolean statusMasterprice;
    private String element;

    private int codUser;

    public Armor(int id, String name, String guardianClass, String type, String rarity, boolean status,
                 boolean statusMasterprice, ArmorAttribute armorAttribute, String element, int codUser) {
        this.id = id;
        this.name = name;
        this.guardianClass = guardianClass;
        this.type = type;
        this.rarity = rarity;
        this.status = status;
        this.armorAttribute = armorAttribute;
        this.statusMasterprice = statusMasterprice;
        this.element = element;
        this.codUser = codUser;
    }

    public Armor(String name, String guardianClass, String type, String rarity, boolean status,
                 boolean statusMasterprice, ArmorAttribute armorAttribute, String element, int codUser) {
        this(-1, name, guardianClass, type, rarity, status, statusMasterprice, armorAttribute, element, codUser);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuardianClass() {
        return guardianClass;
    }

    public void setGuardianClass(String guardianClass) {
        this.guardianClass = guardianClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArmorAttribute getArmorAttribute() {
        return armorAttribute;
    }

    public void setArmorAttribute(ArmorAttribute armorAttribute) {
        this.armorAttribute = armorAttribute;
    }

    public boolean isStatusMasterprice() {
        return statusMasterprice;
    }

    public void setStatusMasterprice(boolean statusMasterprice) {
        this.statusMasterprice = statusMasterprice;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getCodUser() {
        return codUser;
    }

    public void setCodUser(int codUser) {
        this.codUser = codUser;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", guardianClass='" + guardianClass + '\'' +
                ", type='" + type + '\'' +
                ", rarity='" + rarity + '\'' +
                ", status=" + status +
                ", armorAttribute=" + armorAttribute.toString() +
                ", statusMasterprice=" + statusMasterprice +
                ", element='" + element + '\'' +
                '}';
    }
}
