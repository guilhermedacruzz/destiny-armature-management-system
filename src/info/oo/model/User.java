package info.oo.model;

public class User {

    private int id;
    private String name;
    private String surname;
    private String Username;
    private String secret;

    private String guardianClass;

    private Inventory inventory;

    public User(int id, String name, String surname, String username, String secret, Inventory inventory) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.Username = username;
        this.secret = secret;
        this.inventory = inventory;
    }

    public User(String name, String surname, String username, String secret) {
        this(-1, name, surname, username, secret, null);
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
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
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
                ", Username='" + Username + '\'' +
                ", guardianClass='" + guardianClass + '\'' +
                ", inventory=" + inventory.toString() +
                '}';
    }
}
