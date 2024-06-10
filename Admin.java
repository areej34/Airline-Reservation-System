import java.text.*;
import java.util.*;
import java.io.*;

// child class of user
public class Admin extends User{
    //globally declared scanner object for input
    static Scanner scanner = new Scanner(System.in);

    // private attributes
    private final String adminID;
    private List<Flight> flights;

    // constructor
    public Admin(String username, String password, String adminID) {
        super(username, password);
        this.adminID = adminID;
        this.flights = new ArrayList<>();
        loadFlightsFromFile("flights.txt");
    }

    // getters
    public List<Flight> getFlights() {
        return flights;
    }

    public String getAdminID() {
        return adminID;
    }

    //setters
    public void setFlights(List<Flight> flights) { this.flights=flights; }

    // method to add flight
    public void addFlight(Flight newFlight) {
        flights.add(newFlight);
        saveFlightsToFile();
        System.out.println("Flight added successfully.");
    }

    // method to edit flight
    public void editFlight(int flightNumber, String airline, Airport originAirport, Airport destinationAirport, Date departureTime ,Date arrivalTime, int price) {
        Flight flightToEdit = findFlightByNumber(flightNumber);
        if (flightToEdit != null) {
            flightToEdit.setOrigin(originAirport);
            flightToEdit.setAirline(airline);
            flightToEdit.setDestination(destinationAirport);
            flightToEdit.setDepartureTime(departureTime);
            flightToEdit.setArrivalTime(arrivalTime);
            flightToEdit.setPrice(price);
            saveFlightsToFile();
            System.out.println("Flight edited successfully.");
        } else {
            System.out.println("Flight not found.");
        }
    }

    // method to remove flight
    public void removeFlight(int flightNumber) {
        Flight flightToRemove = findFlightByNumber(flightNumber);
        if (flightToRemove != null) {
            flights.remove(flightToRemove);
            saveFlightsToFile();
            System.out.println("Flight removed successfully.");
        } else {
            System.out.println("Flight not found.");
        }
    }

    // Method to get flights by origin and destination from file
    public List<Flight> getFlightsByOriginAndDestination(String origin, String destination) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getOrigin().getCity().equalsIgnoreCase(origin) &&
                    flight.getDestination().getCity().equalsIgnoreCase(destination)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    //method to display all flights in list or in file
    public void displayAllFlights() {
        for (Flight flight : flights) {
            flight.printDetails();
        }
    }

    // method to save flights to file
    private void saveFlightsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("flights.txt"))) {
            oos.writeObject(flights);
            System.out.println("Flights saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving flights to file: " + e.getMessage());
        }
    }

    // method to load flights from file
    public void loadFlightsFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            flights = (List<Flight>) ois.readObject();
            System.out.println("Loaded " + flights.size() + " flights from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading flights from file: " + e.getMessage());
        }
    }

    // helper method to find flight by number
    Flight findFlightByNumber(int flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber) {
                return flight;
            }
        }
        return null;
    }

    // overridden method, from parent abstract class user
    @Override
    public void menu() {
        int adminChoice;
        while (true)
        {
            try {
                do {
                    System.out.println("Admin Actions:");
                    System.out.println("1. Add Flights\n2. Edit Flights\n3. Remove Flights\n4. Display all fights\n5. Log Out");
                    adminChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (adminChoice) {
                        case 1:
                            while (true) {
                                try {
                                    System.out.println("Enter flight number:");
                                    int flightNumber = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("Enter Airline: ");
                                    String airline = scanner.nextLine();
                                    System.out.println("Enter origin airport (name, city, state):");
                                    String originName = scanner.nextLine();
                                    String originCity = scanner.nextLine();
                                    String originState = scanner.nextLine();
                                    System.out.println("Enter destination airport (name, city, state):");
                                    String destinationName = scanner.nextLine();
                                    String destinationCity = scanner.nextLine();
                                    String destinationState = scanner.nextLine();
                                    System.out.println("Enter departure time (yyyy-MM-dd HH:mm:ss):");
                                    Date departureTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(scanner.nextLine());
                                    System.out.println("Enter arrival time (yyyy-MM-dd HH:mm:ss):");
                                    Date arrivalTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(scanner.nextLine());
                                    System.out.println("Enter price for this flight");
                                    int price = scanner.nextInt();
                                    Airport origin = new Airport(originName, originCity, originState);
                                    Airport destination = new Airport(destinationName, destinationCity, destinationState);
                                    Flight newFlight = new Flight(flightNumber,airline, origin, destination, arrivalTime, departureTime, price);
                                    addFlight(newFlight);
                                    break;
                                } catch (ParseException e) {
                                    System.out.println("Invalid time input. Please try again.");
                                    scanner.nextLine();
                                } catch (Exception e) {
                                    System.out.println("Invalid input. Please try again.");
                                    scanner.nextLine();
                                }
                            }
                            break;
                        case 2:
                            while(true)
                            {
                                try {
                                    System.out.println("Enter flight number to edit:");
                                    int flightNumber = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("Enter Airline: ");
                                    String airline = scanner.nextLine();
                                    System.out.println("Enter new origin airport (name, city, state):");
                                    String originName = scanner.nextLine();
                                    String originCity = scanner.nextLine();
                                    String originState = scanner.nextLine();
                                    Airport originAirport = new Airport(originName, originCity, originState);
                                    System.out.println("Enter new destination airport (name, city, state):");
                                    String destinationName = scanner.nextLine();
                                    String destinationCity = scanner.nextLine();
                                    String destinationState = scanner.nextLine();
                                    Airport destinationAirport = new Airport(destinationName, destinationCity, destinationState);
                                    System.out.println("Enter new departure time (yyyy-MM-dd HH:mm:ss):");
                                    Date departureTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(scanner.nextLine());
                                    System.out.println("Enter new arrival time (yyyy-MM-dd HH:mm:ss):");
                                    Date arrivalTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(scanner.nextLine());
                                    System.out.println("Enter new price:");
                                    int price = scanner.nextInt();
                                    editFlight(flightNumber,airline, originAirport, destinationAirport, departureTime, arrivalTime, price);
                                    break;
                                } catch (ParseException e) {
                                    System.out.println("Invalid time input. Please try again.");
                                    scanner.nextLine();
                                } catch (Exception e) {
                                    System.out.println("Invalid input. Please try again.");
                                    scanner.nextLine();
                                }
                            }
                            break;
                        case 3:
                            while (true) {
                                try {
                                    System.out.println("Enter flight number to remove:");
                                    int flightNumber = scanner.nextInt();
                                    scanner.nextLine();
                                    removeFlight(flightNumber);
                                    break;// Consume newline
                                } catch (Exception e) {
                                    System.out.println("Invalid input. Please try again.");
                                    scanner.nextLine(); // Consume invalid input
                                }
                            }
                            break;
                        case 4:
                            displayAllFlights();
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }while(adminChoice!=5);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                scanner.nextLine();
            }
        }
    }
}