package info.oo.models;

public class User {

    private int id;
    private String name;
    private String surname;
    private String Username;

    public User(int id, String name, String surname, String username) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        Username = username;
    }

    public User(String name, String surname, String username) {
        this(-1, name, surname, username);
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
}
