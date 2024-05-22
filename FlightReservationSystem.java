import java.io.*;
import java.text.*;
import java.util.*;

//interface display
interface Display {
    void printDetails();
}

//interface for seat class
interface SeatManagment {
    void setBooked(boolean booked);
}

//flight class
class Flight implements Display {
    //private attributes
    private int flightNumber;
    private Airport origin;
    private Airport destination;
    private Date arrivalTime;
    private Date departureTime;
    private List<Passenger> passengers;
    private List<Seat> seats;

    //constructor
    public Flight(int flightNumber, Airport origin, Airport destination, Date arrivalTime, Date departureTime) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.passengers = new ArrayList<>();
        this.seats = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            seats.add(new Seat(i));
        }
    }

    //getters
    public int getFlightNumber() {
        return flightNumber;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    //setters
    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }  

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    //method to add passenger
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    //method to remove passenger
    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
    }

    //getter
    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    //method to print details
    public void printDetails() {
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Origin: " + origin);
        System.out.println("Destination: " + destination);
        System.out.println("Departure Time: " + departureTime);
        System.out.println("Arrival Time: " + arrivalTime);
    }

    //method to convert into string to add to file
    public String toFileString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return flightNumber + "," + origin.toFileString() + "," + destination.toFileString() + ","
                + sdf.format(departureTime) + "," + sdf.format(arrivalTime);
    }

    //overriden method from obj class
    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber=" + flightNumber +
                ", origin=" + origin +
                ", destination=" + destination +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}

//Airport class
class Airport implements Display {
    //private attributes
    private String name;
    private String city;
    private String state;

    //constructor
    public Airport(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;
    }

    //getters
    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    //method to convert to string so that it can be added to the file
    public String toFileString() {
        return name + "," + city + "," + state;
    }

    //overriden method from obj class
    @Override
    public String toString() {
        return name + ", " + city + ", " + state;
    }

    //method to print details
    public void printDetails() {
        System.out.println("airport: " + name + " city: " + city + " state: " + state);
    }
}

//seat class with interface seatmanagement
class Seat implements SeatManagment {
    //private attributes
    private int seatNumber;
    private boolean booked;

    //constructor
    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.booked = false;
    }

    //getters
    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    //setter
    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    //overriden method from object class
    @Override
    public String toString() {
        return "Seat{" + "seatNumber=" + seatNumber + ", booked=" + booked + '}';
    }
}

//ticket class
class Ticket {
    //private attributes
    private int ticketNumber;
    private Passenger passenger;
    private Flight flight;

    //constructor
    public Ticket(int ticketNumber, Passenger passenger, Flight flight) {
        this.ticketNumber = ticketNumber;
        this.passenger = passenger;
        this.flight = flight;
    }

    //getters
    public int getTicketNumber() {
        return ticketNumber;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public Date getArrivalTime() {
        return flight.getArrivalTime();
    }

    public Date getDepartureTime() {
        return flight.getDepartureTime();
    }

    public Airport getOriginAirport() {
        return flight.getOrigin();
    }

    public Airport getDestinationAirport() {
        return flight.getDestination();
    }

    //setter
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    //method to print details
    public void printDetails() {
        System.out.println("ticket no: " + ticketNumber);
        passenger.printDetails();
        flight.printDetails();
    }

}

// airplane class
class Airplane {
    // private attributes
    private String model;
    private int totalSeats;
    private int maxCapacity;

    // constructors
    public Airplane(String model, int totalSeats, int maxCapacity) {
        this.model = model;
        this.totalSeats = totalSeats;
        this.maxCapacity = maxCapacity;
    }

    // getters
    public String getModel() {
        return model;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    // method to print details
    public void printDetails() {
        System.out.println(
                "Airplane Model: " + model + ", Total Seats: " + totalSeats + ", Max Capacity: " + maxCapacity);
    }
}

// abstract class user, that can't be accessed unless inherited.
abstract class User {
    // private attributes
    protected String username;
    protected String password;

    // constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // abstract method without body
    public abstract void performActions();

    // getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

// child class of user
class Admin extends User {
    // private attributes
    private String adminID;
    private List<Flight> flights;

    // constructor
    public Admin(String username, String password, String adminID) {
        super(username, password);
        this.adminID = adminID;
        this.flights = new ArrayList<>();
        loadFlightsFromFile();
    }

    // getters
    public List<Flight> getFlights() {
        return flights;
    }

    public String getAdminID() {
        return adminID;
    }

    // method to load flights from file
    private void loadFlightsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("flights.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] flightData = line.split(",");
                int flightNumber = Integer.parseInt(flightData[0]);
                Airport origin = new Airport(flightData[1], flightData[2], flightData[3]);
                Airport destination = new Airport(flightData[4], flightData[5], flightData[6]);
                Date departureTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(flightData[7]);
                Date arrivalTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(flightData[8]);
                Flight flight = new Flight(flightNumber, origin, destination, arrivalTime, departureTime);
                flights.add(flight);
            }
        } catch (Exception e) {
            System.out.println("Error loading flights from file: " + e.getMessage());
        }
    }

    // method to save flights to file
    private void saveFlightsToFile() {
        try (FileWriter writer = new FileWriter("flights.txt")) {
            for (Flight flight : flights) {
                writer.write(flight.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving flights to file: " + e.getMessage());
        }
    }

    // method to add flight
    public void addFlight(Scanner scanner) {
        try {
            System.out.println("Enter flight number:");
            int flightNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
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

            Airport origin = new Airport(originName, originCity, originState);
            Airport destination = new Airport(destinationName, destinationCity, destinationState);
            Flight newFlight = new Flight(flightNumber, origin, destination, arrivalTime, departureTime);
            flights.add(newFlight);
            saveFlightsToFile();
            System.out.println("Flight added successfully.");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // Consume invalid input
        }
    }

    // method to edit flight
    public void editFlight(Scanner scanner) {
        try {
            System.out.println("Enter flight number to edit:");
            int flightNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            Flight flightToEdit = findFlightByNumber(flightNumber);

            if (flightToEdit != null) {
                System.out.println("Enter new origin airport (name, city, state):");
                String originName = scanner.nextLine();
                String originCity = scanner.nextLine();
                String originState = scanner.nextLine();
                System.out.println("Enter new destination airport (name, city, state):");
                String destinationName = scanner.nextLine();
                String destinationCity = scanner.nextLine();
                String destinationState = scanner.nextLine();
                System.out.println("Enter new departure time (yyyy-MM-dd HH:mm:ss):");
                Date departureTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(scanner.nextLine());
                System.out.println("Enter new arrival time (yyyy-MM-dd HH:mm:ss):");
                Date arrivalTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(scanner.nextLine());

                flightToEdit.setOrigin(new Airport(originName, originCity, originState));
                flightToEdit.setDestination(new Airport(destinationName, destinationCity, destinationState));
                flightToEdit.setDepartureTime(departureTime);
                flightToEdit.setArrivalTime(arrivalTime);
                saveFlightsToFile();
                System.out.println("Flight edited successfully.");
            } else {
                System.out.println("Flight not found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // Consume invalid input
        }
    }

    // method to view flight details
    public void viewFlightDetails(Scanner scanner) {
        try {
            System.out.println("Enter flight number to view:");
            int flightNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            Flight flightToView = findFlightByNumber(flightNumber);

            if (flightToView != null) {
                flightToView.printDetails();
            } else {
                System.out.println("Flight not found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // Consume invalid input
        }
    }

    // method to remove flight
    public void removeFlight(Scanner scanner) {
        try {
            System.out.println("Enter flight number to remove:");
            int flightNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            Flight flightToRemove = findFlightByNumber(flightNumber);

            if (flightToRemove != null) {
                flights.remove(flightToRemove);
                saveFlightsToFile();
                System.out.println("Flight removed successfully.");
            } else {
                System.out.println("Flight not found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // Consume invalid input
        }
    }

    // helper method to find flight by number
    private Flight findFlightByNumber(int flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber) {
                return flight;
            }
        }
        return null;
    }

    // overridden method, from parent abstract class user
    @Override
    public void performActions() {
        System.out.println("Admin actions are being performed.");
    }
}

//passenger child class
class Passenger extends User {
    // private attributes
    private String passengerID;
    private String name;
    private int age;
    private String passportNumber;
    private Seat seat;
    private List<Flight> bookedFlights;

    // constructor
    public Passenger(String username, String password, String name, String passengerID, int age, String passportNumber,
            Seat seat) {
        super(username, password);
        this.name = name;
        this.age = age;
        this.passengerID = passengerID;
        this.passportNumber = passportNumber;
        this.seat = seat;
        this.bookedFlights = new ArrayList<>();
    }

    // getter
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public Seat getSeat() {
        return seat;
    }

    // method to print detail
    public void printDetails() {
        System.out.println("Passenger: " + name + ", Age: " + age + ", Passport Number: " + passportNumber + ", Seat: "
                + seat.getSeatNumber());
    }

    // getter
    public List<Flight> getBookedFlights() {
        return bookedFlights;
    }

    public String getPassengerID() {
        return passengerID;
    }

    // overridden method, from parent abstract class user
    @Override
    public void performActions() {
        System.out.println("Passenger actions are being performed.");
    }

    // method to search flights by origin and destination
    public List<Flight> searchFlights(String originCity, String destinationCity) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : new Admin("admin", "admin123", "01").getFlights()) {
            if (flight.getOrigin().getCity().equalsIgnoreCase(originCity)
                    && flight.getDestination().getCity().equalsIgnoreCase(destinationCity)) {
                result.add(flight);
            }
        }
        return result;
    }

    // method to book flight
    public void bookFlight(Scanner scanner) {
        System.out.println("Enter flight number to book:");
        int flightNumberToBook = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Flight flightToBook = searchFlights("", "").stream().filter(f -> f.getFlightNumber() == flightNumberToBook)
                .findFirst().orElse(null);
        if (flightToBook != null) {
            flightToBook.addPassenger(this);
            bookedFlights.add(flightToBook);
            System.out.println("Flight booked successfully.");
        } else {
            System.out.println("Flight not found.");
        }
    }

    // method to cancel flight
    public void cancelFlight(Scanner scanner) {
        System.out.println("Enter flight number to cancel:");
        int flightNumberToCancel = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Flight flightToCancel = bookedFlights.stream().filter(f -> f.getFlightNumber() == flightNumberToCancel)
                .findFirst().orElse(null);
        if (flightToCancel != null) {
            flightToCancel.removePassenger(this);
            bookedFlights.remove(flightToCancel);
            System.out.println("Flight cancelled successfully.");
        } else {
            System.out.println("Flight not found.");
        }
    }

    // method to display booked flights
    public void listBookedFlights() {
        System.out.println("Booked Flights:");
        for (Flight flight : bookedFlights) {
            System.out.println(flight);
        }
    }

    // method to select seat
    public void selectSeat(Scanner scanner) {
        System.out.println("Enter flight number to select seat:");
        int flightNumberForSeat = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Flight flightForSeat = searchFlights("", "").stream().filter(f -> f.getFlightNumber() == flightNumberForSeat)
                .findFirst().orElse(null);
        if (flightForSeat != null) {
            try {
                System.out.println("Available seats: ");
                List<Seat> availableSeats = flightForSeat.getAvailableSeats();
                for (Seat seat : availableSeats) {
                    System.out.print(seat.getSeatNumber() + " ");
                }
                System.out.println("\nEnter seat number to select:");
                int seatNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                for (Seat seat : availableSeats) {
                    if (seat.getSeatNumber() == seatNumber) {
                        this.seat = seat;
                        System.out.println("Seat selected successfully.");
                        return;
                    }
                }
                System.out.println("Invalid seat number. Please try again.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Consume invalid input
            }
        } else {
            System.out.println("Flight not found.");
        }
    }

    //method to search flights
    public void handleSearchFlights(Scanner scanner) {
        System.out.println("Enter origin city:");
        String originCity = scanner.nextLine();
        System.out.println("Enter destination city:");
        String destinationCity = scanner.nextLine();
        List<Flight> flights = searchFlights(originCity, destinationCity);
        if (flights.isEmpty()) {
            System.out.println("No flights found.");
        } else {
            System.out.println("Found flights:");
            for (Flight flight : flights) {
                System.out.println(flight);
            }
        }
    }
}

// main class
public class AirlineReservationSystem {
    static Scanner scanner = new Scanner(System.in);

    // main method
    public static void main(String[] args) {
        Admin admin = new Admin("admin", "admin123", "01");
        Seat seat = new Seat(1);
        Passenger passenger = new Passenger("passenger", "pass123", "Sam", "01", 15, "02303", seat);

        while (true) {
            try {
                System.out.println("Select User Type:");
                System.out.println("1. Admin");
                System.out.println("2. Passenger");
                System.out.println("3. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                if (choice == 1) {
                    adminMenu(admin);
                } else if (choice == 2) {
                    passengerMenu(passenger);
                } else if (choice == 3) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Incorrect input, try again!");
                scanner.nextLine(); 
            }
        }
        scanner.close();
    }

    //menu for admin
    private static void adminMenu(Admin admin) {
        while (true) {
            try {
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();

                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                    while (true) {
                        System.out.println("Admin Actions:");
                        System.out.println(
                                "1. Add Flights\n2. Edit Flights\n3. View Flight Details\n4. Remove Flights\n5. Log Out");
                        int adminChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (adminChoice) {
                            case 1:
                                admin.addFlight(scanner);
                                break;
                            case 2:
                                admin.editFlight(scanner);
                                break;
                            case 3:
                                admin.viewFlightDetails(scanner);
                                break;
                            case 4:
                                admin.removeFlight(scanner);
                                break;
                            case 5:
                                return;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                } else {
                    System.out.println("Invalid credentials.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    //menu for passenger
    private static void passengerMenu(Passenger passenger) {
        while (true) {
            try {
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();

                if (passenger.getUsername().equals(username) && passenger.getPassword().equals(password)) {
                    while (true) {
                        System.out.println("Passenger Actions:");
                        System.out.println(
                                "1. Search Flights\n2. Book Flight\n3. Cancel Flight\n4. List Booked Flights\n5. Select Seat\n6. Log out");
                        int passengerChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (passengerChoice) {
                            case 1:
                                passenger.handleSearchFlights(scanner);
                                break;
                            case 2:
                                passenger.bookFlight(scanner);
                                break;
                            case 3:
                                passenger.cancelFlight(scanner);
                                break;
                            case 4:
                                passenger.listBookedFlights();
                                break;
                            case 5:
                                passenger.selectSeat(scanner);
                                break;
                            case 6:
                                return;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                } else {
                    System.out.println("Invalid credentials.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

}
