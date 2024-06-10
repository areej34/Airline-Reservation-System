import java.io.Serializable;

//Airport class
public class Airport implements Serializable, Display {
    //private attributes
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String city;
    private final String state;

    //constructor
    public Airport(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;
    }

    //getters
    public String getCity() { return city; }

    public String getState() { return state; }

    public String getName() { return name; }

    //override method from obj class
    @Override
    public String toString() {
        return name + ", " + city + ", " + state;
    }

    //method to print details
    @Override
    public void printDetails() {
        System.out.println("airport: " + name + " city: " + city + " state: " + state);
    }
}