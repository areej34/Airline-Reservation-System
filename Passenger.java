import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

//child class of user
public class Passenger extends User {
    //private attributes
    private final String email;
    private final String name;
    private final String phoneNumber;
    private final List<Ticket> bookedTickets;
    private static final Scanner scanner = new Scanner(System.in);

    //no arg constructor

    //constructor
    public Passenger(String name, String email, String phoneNumber, String username, String password) {
        super(username, password);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bookedTickets = new ArrayList<>();
    }

    //getters
    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPhoneNumber() { return phoneNumber; }

    public List<Ticket> getBookedTickets() { return bookedTickets; }

    //method to add ticket
    public void addTicket(Ticket ticket) {
        bookedTickets.add(ticket);
    }

    //method to search flights
    public List<Flight> searchFlights(String originCity, String destinationCity) throws IOException, ClassNotFoundException {
        List<Flight> result = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("flights.txt"))) {
            List<Flight> flights = (List<Flight>) ois.readObject();
            for (Flight flight : flights) {
                if (flight.getOrigin().getCity().equalsIgnoreCase(originCity) && flight.getDestination().getCity().equalsIgnoreCase(destinationCity)) {
                    result.add(flight);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading flights from file: " + e.getMessage());
        }
        return result;
    }

    //method to book flight
    public Flight bookFlight(int flightNumberToBook, int seatNumber) {
        Flight flightToBook = null;
        List<Flight> flights = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("flights.txt"))) {
            flights = (List<Flight>) ois.readObject();
            System.out.println("check1");
        } catch (IOException | ClassNotFoundException e) {
            return null; // Return null to indicate failure
        }

        if (flights != null) {
            for (Flight flight : flights) {
                if (flight.getFlightNumber() == flightNumberToBook) {
                    flightToBook = flight;
                    System.out.println("check2");
                    break;
                }
            }

            if (flightToBook != null) {
                List<Seat> availableSeats = flightToBook.getAvailableSeats();
                boolean seatAvailable = false;

                for (Seat seat : availableSeats) {
                    if (seat.getSeatNumber() == seatNumber) {
                        if (!seat.isBooked()) {
                            seatAvailable = true;
                            seat.setBooked(true);
                            Ticket ticket = flightToBook.addPassenger(this, new Seat(seatNumber));
                            bookedTickets.add(ticket);
                            System.out.println("check3");
                            return flightToBook;
                        } else {
                            return null; // Return null to indicate failure
                        }
                    }
                }
            }
        }
        return null; // Return null to indicate failure
    }

    public void cancelFlight(int flightNumber) {
        Ticket ticketToCancel = null;
        for (Ticket t : bookedTickets) {
            if (t.getFlight().getFlightNumber() == flightNumber) {
                ticketToCancel = t;
                break;
            }
        }

        if (ticketToCancel != null) {
            Flight flight = ticketToCancel.getFlight();
            flight.removePassenger(this);
            bookedTickets.remove(ticketToCancel);
            System.out.println("Flight cancelled successfully.");
        } else {
            System.out.println("Ticket not found.");
        }
    }

    public void listBookedFlights() {
        System.out.println("Booked Flights:");
        for (Ticket ticket : bookedTickets) {
            System.out.println(ticket.getFlight());
        }
    }

    //method to view ticket
    public void viewTicket() {
        System.out.println("Enter flight number to view ticket: ");
        int flightNumber = scanner.nextInt();
        for (Ticket ticket : bookedTickets) {
            if (Objects.equals(ticket.getFlight().getFlightNumber(), flightNumber)) {
                ticket.printDetails();
                return;
            }
        }
        System.out.println("No ticket found for the specified flight.");
    }

    //method to get Ticket
    public Ticket getTicketByFlightNumber(int flightNumber) {
        for (Ticket ticket : bookedTickets) {
            if (ticket.getFlight().getFlightNumber() == flightNumber) {
                return ticket;
            }
        }
        return null;
    }

    //helper method
    public void handleSearchFlights() throws IOException, ClassNotFoundException {
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

    // overridden method from parent abstract class User
    @Override
    public void menu() {
        while (true)
        {
            try {
                System.out.println("Passenger Actions:");
                System.out.println(
                        "1. Search Flights\n2. Book Flight\n3. Cancel Flight\n4. List Booked Flights\n5. view Ticket\n6. Log out");
                int passengerChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (passengerChoice)
                {
                    case 1:
                        handleSearchFlights();
                        break;
                    case 2:
                        while(true)
                        {
                            try{
                                System.out.println("Enter flight number to book:");
                                int flightNumberToBook = scanner.nextInt();
                                System.out.println("Enter seat number to book:");
                                int seatNumber = scanner.nextInt();
                                Flight f = bookFlight(flightNumberToBook, seatNumber);
                                f=null;
                                break;
                            } catch (Exception e){
                                System.out.println("invalid input, try again!\n");
                            }
                        }
                        break;
                    case 3:
                        while(true)
                        {
                            try{
                                System.out.println("Enter ticket number to cancel:");
                                int ticketNumberToCancel = scanner.nextInt();
                                cancelFlight(ticketNumberToCancel);
                                break;
                            } catch (Exception e){
                                System.out.println("invalid input, try again!\n");
                            }
                        }
                        break;
                    case 4:
                        listBookedFlights();
                        break;
                    case 5:
                        viewTicket();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                scanner.nextLine();
            }
        }
    }

    @Override
    public String toString() {
        return name + "," + email + "," + phoneNumber + "," + username + "," + password;
    }

    public static Passenger fromString(String line) {
        String[] parts = line.split(",");
        return new Passenger(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }

}