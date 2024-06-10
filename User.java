// abstract class user, that can't be accessed unless inherited.
public abstract class User {
    // private attributes
    protected String username;
    protected String password;

    // constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // abstract method without body
    public abstract void menu();

    // getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}