package info.oo.model;

public class User {

    private int id;
    private String name;
    private String surname;
    private String username;

    private String guardianClass;
    private Inventory inventory;

    public User(int id, String name, String surname, String username, String secret, Inventory inventory) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.inventory = inventory;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getGuardianClass() {
        return guardianClass;
    }

    public void setGuardianClass(String guardianClass) {
        this.guardianClass = guardianClass;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", Username='" + username + '\'' +
                ", guardianClass='" + guardianClass + '\'' +
                ", inventory=" + inventory.toString() +
                '}';
    }
}
