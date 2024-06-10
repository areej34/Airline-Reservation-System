import java.util.*;

//ticket class
public class Ticket implements Display {
    //attributes
    private static int ticketNumber = 0;
    private final Passenger passenger;
    private Flight flight;
    private final Seat seat;

    //constructor
    public Ticket(Passenger passenger, Flight flight, Seat seat) {
        ticketNumber++;
        this.passenger = passenger;
        this.flight = flight;
        this.seat = seat;
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

    public Seat getSeat() { return seat;}

    //setter
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void printDetails() {
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Passenger: " + passenger.getName());
        System.out.println("Flight Number: " + flight.getFlightNumber());
        System.out.println("Seat Number: " + seat.getSeatNumber());
    }

    @Override
    public String toString() {
        return "ticket no. " + ticketNumber +
                "\nname: " + passenger.getName() +
                "\nDeparture City: " + flight.getDestination().getCity() +
                "\nArrival City: " + flight.getOrigin().getCity() +
                "\nFLight time: " + flight.getDepartureTime().toString();

    }

}