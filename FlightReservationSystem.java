import java.util.*;

// Parent class Airport, that contains common attributes and methods from children classes.
class Airport
{
    //private attributess
    private String name;
    private String city;
    private String state;

    //constructor
    public Airport(String name, String city, String state)
    {
        this.name = name;
        this.city = city;
        this.state = state;
    }

    //getters
    public String getName() { return name; }

    public String getCity() { return city; }

    public String getState() { return state; }

    //setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    //method to print details about the airport
    public void printDetails()
    {
        System.out.println("Airport: " + name + ", City: " + city + ", State: " + state);
    }

    //overridden method, from the object class
    @Override
    public String toString()
    {
        return name + " (" + city + ", " + state + ")";
    }
}

//child class of airport, that specifies which airport the airplane takes flight from.
class OriginAirport extends Airport
{
    //private attribute
    private Date departureTime;

    //constructor
    public OriginAirport(String name, String city, String state, Date departureTime)
    {
        super(name, city, state);
        this.departureTime = departureTime;
    }

    //getter
    public Date getDepartureTime() { return departureTime; }

    //setter
    public void setDepartureTime(Date departureTime)
    {
        this.departureTime = departureTime;
    }

    //overridden method, from parent class airport
    @Override
    public void printDetails()
    {
        super.printDetails();
        System.out.println("Departure Time: " + departureTime);
    }
}

//child class of airport, that specifies the airports the airplane lands on during long flight.
class IntermediateAirport extends Airport
{
    //private attributes
    private Date arrivalTime;
    private Date departureTime;

    //constructor
    public IntermediateAirport(String name, String city, String state, Date arrivalTime, Date departureTime)
    {
        super(name, city, state);
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    //getters
    public Date getArrivalTime() { return arrivalTime; }

    public Date getDepartureTime() { return departureTime; }

    //setters
    public void setArrivalTime(Date arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(Date departureTime)
    {
        this.departureTime = departureTime;
    }

    //overridden method, from parent class airport
    @Override
    public void printDetails()
    {
        super.printDetails();
        System.out.println("Arrival Time: " + arrivalTime + ", Departure Time: " + departureTime);
    }
}

//child class of airport, that specifies the airports the airplane lands on during long flight.
class DestinationAirport extends Airport
{
    //private attributes
    private Date arrivalTime;

    //constructor
    public DestinationAirport(String name, String city, String state, Date arrivalTime)
    {
        super(name, city, state);
        this.arrivalTime = arrivalTime;
    }

    //getter
    public Date getArrivalTime() { return arrivalTime; }

    //setter
    public void setArrivalTime(Date arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    //overridden method, from parent class airport
    @Override
    public void printDetails()
    {
        super.printDetails();
        System.out.println("Arrival Time: " + arrivalTime);
    }
}

//flight class
class Flight
{
    //private attributes
    private int flightNumber;
    private OriginAirport origin;
    private List<IntermediateAirport> intermediateAirports;
    private DestinationAirport destination;
    private Date arrivalTime;
    private Date departureTime;
    private Airline airline;
    private List<Passenger> passengers;

    //constructor
    public Flight(int flightNumber, OriginAirport origin, List<IntermediateAirport> intermediateAirports,
                  DestinationAirport destination, Date arrivalTime, Date departureTime, Airline airline)
    {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.intermediateAirports = intermediateAirports != null ? intermediateAirports : new ArrayList<>();
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.airline = airline;
        this.passengers = new ArrayList<>();
    }

    //getters
    public int getFlightNumber() { return flightNumber; }

    public OriginAirport getOrigin() { return origin; }

    public List<IntermediateAirport> getIntermediateAirports() { return intermediateAirports; }

    public DestinationAirport getDestination() { return destination; }

    public Date getArrivalTime() { return arrivalTime; }

    public Date getDepartureTime() { return departureTime; }

    public Airline getAirline() { return airline; }

    public List<Passenger> getPassengers() { return passengers; }

    //setters
    public void setFlightNumber(int flightNumber)
    {
        this.flightNumber = flightNumber;
    }

    public void setOrigin(OriginAirport origin)
    {
        this.origin = origin;
    }

    public void setIntermediateAirports(List<IntermediateAirport> intermediateAirports)
    {
        this.intermediateAirports = intermediateAirports;
    }

    public void setDestination(DestinationAirport destination)
    {
        this.destination = destination;
    }

    public void setArrivalTime(Date arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(Date departureTime)
    {
        this.departureTime = departureTime;
    }

    public void setAirline(Airline airline)
    {
        this.airline = airline;
    }

    public void setPassengers(List<Passenger> passengers)
    {
        this.passengers = passengers;
    }

    //method to add passengers
    public void addPassenger(Passenger passenger)
    {
        passengers.add(passenger);
    }

    //method to remove passengers
    public void removePassenger(Passenger passenger)
    {
        passengers.remove(passenger);
    }

    //overridden method, from parent class object.
    @Override
    public String toString()
    {
        return "Flight " + flightNumber + " (" + origin.getName() + " to " + destination.getName() + ")";
    }
}

//Airline Class
class Airline
{
    //private attributes
    private String name;
    private List<Flight> flights;
    private List<Airplane> airplanes;

    //constructor
    public Airline(String name, List<Flight> flights, List<Airplane> airplanes)
    {
        this.name = name;
        this.flights = flights != null ? flights : new ArrayList<>();
        this.airplanes = airplanes != null ? airplanes : new ArrayList<>();
    }

    //getters
    public String getName() { return name; }

    public List<Flight> getFlights() { return flights; }

    public List<Airplane> getAirplanes() { return airplanes; }

    //setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setFlights(List<Flight> flights)
    {
        this.flights = flights;
    }

    public void setAirplanes(List<Airplane> airplanes)
    {
        this.airplanes = airplanes;
    }

    //method to add flights
    public void addFlight(Flight flight)
    {
        flights.add(flight);
    }

    //method to remove flight
    public void removeFlight(Flight flight)
    {
        flights.remove(flight);
    }

    //method to print details
    public void printDetails()
    {
        System.out.println("Airline: " + name);
    }
}

//airplane class
class Airplane
{
    //private attributes
    private String model;
    private int totalSeats;
    private int maxCapacity;

    //constructors
    public Airplane(String model, int totalSeats, int maxCapacity)
    {
        this.model = model;
        this.totalSeats = totalSeats;
        this.maxCapacity = maxCapacity;
    }

    //getters
    public String getModel() { return model; }

    public int getTotalSeats() { return totalSeats; }

    public int getMaxCapacity() { return maxCapacity; }

    //method to print details
    public void printDetails()
    {
        System.out.println("Airplane Model: " + model + ", Total Seats: " + totalSeats + ", Max Capacity: " + maxCapacity);
    }
}

//seat class
class Seat
{
    //private attributes
    private int seatNumber;
    private boolean isAvailable;

    //constructor
    public Seat(int seatNumber)
    {
        this.seatNumber = seatNumber;
        this.isAvailable = true;
    }

    //getter
    public int getSeatNumber() { return seatNumber; }

    public boolean isAvailable() { return isAvailable; }

    //method to reserve seat
    public void reserveSeat()
    {
        if (isAvailable)
        {
            isAvailable = false;
        }
        else
        {
            System.out.println("Seat " + seatNumber + " is already reserved.");
        }
    }

    //method to cancel reservation of a seat
    public void freeSeat()
    {
        if (!isAvailable)
        {
            isAvailable = true;
        }
        else
        {
            System.out.println("Seat " + seatNumber + " is already available.");
        }
    }

    //overridden method, from the parent class object.
    @Override
    public String toString()
    {
        return "Seat " + seatNumber + " (Available: " + isAvailable + ")";
    }
}

//Passenger class
class Passenger
{
    //private attributes
    private String name;
    private int age;
    private String passportNumber;
    private Seat seat;

    //constructor
    public Passenger(String name, int age, String passportNumber, Seat seat)
    {
        this.name = name;
        this.age = age;
        this.passportNumber = passportNumber;
        this.seat = seat;
    }

    //getter
    public String getName() { return name; }

    public int getAge() { return age; }

    public String getPassportNumber() { return passportNumber; }

    public Seat getSeat() { return seat; }

    //method to print detail
    public void printDetails()
    {
        System.out.println("Passenger: " + name + ", Age: " + age + ", Passport Number: " + passportNumber + ", Seat: " + seat.getSeatNumber());
    }
}

//abstract class user, that cant be accessed unless inherited.
abstract class User
{
    //private attributes
    protected String username;
    protected String password;

    //constructor
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    //abstract method without body
    public abstract void performActions();

    //getters
    public String getUsername() { return username; }

    public String getPassword() { return password; }
}

//child class of user
class Admin extends User
{
    //private attributes
    private String adminID;
    private List<Flight> flights;
    private List<Airline> airlines;

    //constructor
    public Admin(String username, String password, String adminID, List<Flight> flights, List<Airline> airlines)
    {
        super(username, password);
        this.adminID=adminID;
        this.flights = flights != null ? flights : new ArrayList<>();
        this.airlines = airlines != null ? airlines : new ArrayList<>();
    }

    //getters
    public List<Flight> getFlights() { return flights; }

    public String getAdminID() { return adminID; }

    public List<Airline> getAirlines() { return airlines; }

    //method to add flight
    public void addFlight(Flight flight)
    {
        flights.add(flight);
    }

    //method to remove flight
    public void removeFlight(Flight flight)
    {
        flights.remove(flight);
    }

    //method to add airline
    public void addAirline(Airline airline)
    {
        airlines.add(airline);
    }

    //method to remove airline
    public void removeAirline(Airline airline)
    {
        airlines.remove(airline);
    }

    //overridden method, from parent abstract class user.
    @Override
    public void performActions()
    {
        System.out.println("Admin actions are being performed.");
    }

    //method to print details of the flights.
    public void listFlights()
    {
        System.out.println("Flights:");
        for (Flight flight : flights)
        {
            System.out.println(flight);
        }
    }
}

//child class of user
class PassengerUser extends User
{
    //private attributes
    private String passengerID;
    private Seat seat;
    private List<Flight> bookedFlights;

    //constructor
    public PassengerUser(String username, String password, String passengerID, Seat seat)
    {
        super(username, password);
        this.seat=seat;
        this.passengerID=passengerID;
        this.bookedFlights = new ArrayList<>();
    }

    //getter
    public List<Flight> getBookedFlights() { return bookedFlights; }

    public Seat getSeat() { return seat; }

    public String getPassengerID() { return passengerID; }

    //setters


    //overridden method, from parent abstract class user
    @Override
    public void performActions()
    {
        System.out.println("Passenger actions are being performed.");
    }

    //method to book flight
    public void bookFlight(Flight flight, Passenger passenger)
    {
        flight.addPassenger(passenger);
        bookedFlights.add(flight);
        System.out.println("Flight booked successfully.");
    }

    //method to cancel flight
    public void cancelFlight(Flight flight, Passenger passenger)
    {
        flight.removePassenger(passenger);
        bookedFlights.remove(flight);
        System.out.println("Flight cancelled successfully.");
    }

    //method to display booked flights
    public void listBookedFlights()
    {
        System.out.println("Booked Flights:");
        for (Flight flight : bookedFlights)
        {
            System.out.println(flight);
        }
    }
}

//main class
public class FlightReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Flight> flights = new ArrayList<>();
        List<Airline> airlines = new ArrayList<>();

        // Sample data
        OriginAirport origin = new OriginAirport("JFK", "New York", "NY", new Date());
        DestinationAirport destination = new DestinationAirport("LAX", "Los Angeles", "CA", new Date());
        Flight flight = new Flight(1, origin, null, destination, new Date(), new Date(), null);
        flights.add(flight);

        Admin admin = new Admin("admin", "admin123", "01", flights, airlines);
        Seat seat = new Seat(1);
        PassengerUser passenger = new PassengerUser("passenger", "pass123", "01", seat);
        Passenger p1 = new Passenger("John Doe", 30, "A12345678", seat);

        while (true) {
            System.out.println("Select User Type:");
            System.out.println("1. Admin");
            System.out.println("2. Passenger");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                handleAdminMenu(scanner, admin);
            } else if (choice == 2) {
                handlePassengerMenu(scanner, passenger, flights, p1);
            } else if (choice == 3) {
                break;
            }
        }

        scanner.close();
    }

    private static void handleAdminMenu(Scanner scanner, Admin admin) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            while (true) {
                System.out.println("Admin Actions:");
                System.out.println("1. Add Flight");
                System.out.println("2. Remove Flight");
                System.out.println("3. List Flights");
                System.out.println("4. Log out");
                int adminChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (adminChoice == 1) {
                    addFlight(scanner, admin);
                } else if (adminChoice == 2) {
                    removeFlight(scanner, admin);
                } else if (adminChoice == 3) {
                    admin.listFlights();
                } else if (adminChoice == 4) {
                    break;
                }
            }
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void handlePassengerMenu(Scanner scanner, PassengerUser passenger, List<Flight> flights, Passenger p1) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (passenger.getUsername().equals(username) && passenger.getPassword().equals(password)) {
            while (true) {
                System.out.println("Passenger Actions:");
                System.out.println("1. Book Flight");
                System.out.println("2. Cancel Flight");
                System.out.println("3. List Booked Flights");
                System.out.println("4. Log out");
                int passengerChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (passengerChoice == 1) {
                    bookFlight(scanner, passenger, flights, p1);
                } else if (passengerChoice == 2) {
                    cancelFlight(scanner, passenger, p1);
                } else if (passengerChoice == 3) {
                    passenger.listBookedFlights();
                } else if (passengerChoice == 4) {
                    break;
                }
            }
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void addFlight(Scanner scanner, Admin admin) {
        System.out.println("Enter flight number:");
        int flightNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        OriginAirport origin = new OriginAirport("JFK", "New York", "NY", new Date());
        DestinationAirport destination = new DestinationAirport("LAX", "Los Angeles", "CA", new Date());
        Flight newFlight = new Flight(flightNumber, origin, null, destination, new Date(), new Date(), null);
        admin.addFlight(newFlight);
        System.out.println("Flight added successfully.");
    }

    private static void removeFlight(Scanner scanner, Admin admin) {
        System.out.println("Enter flight number to remove:");
        int flightNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Flight flightToRemove = null;
        for (Flight f : admin.getFlights()) {
            if (f.getFlightNumber() == flightNumber) {
                flightToRemove = f;
                break;
            }
        }
        if (flightToRemove != null) {
            admin.removeFlight(flightToRemove);
            System.out.println("Flight removed successfully.");
        } else {
            System.out.println("Flight not found.");
        }
    }

    private static void bookFlight(Scanner scanner, PassengerUser passenger, List<Flight> flights, Passenger p1) {
        System.out.println("Enter flight number to book:");
        int flightNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Flight flightToBook = null;
        for (Flight f : flights) {
            if (f.getFlightNumber() == flightNumber) {
                flightToBook = f;
                break;
            }
        }
        if (flightToBook != null) {
            passenger.bookFlight(flightToBook, p1);
        } else {
            System.out.println("Flight not found.");
        }
    }

    private static void cancelFlight(Scanner scanner, PassengerUser passenger, Passenger p1) {
        System.out.println("Enter flight number to cancel:");
        int flightNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Flight flightToCancel = null;
        for (Flight f : passenger.getBookedFlights()) {
            if (f.getFlightNumber() == flightNumber) {
                flightToCancel = f;
                break;
            }
        }
        if (flightToCancel != null) {
            passenger.cancelFlight(flightToCancel, p1);
        } else {
            System.out.println("Flight not found.");
        }
    }
}




