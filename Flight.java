import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

//flight class
public class Flight implements Serializable, Display{
    // private attributes
    private static final long serialVersionUID = 1L;
    private final int flightNumber;
    private String airline;
    private Airport origin;
    private Airport destination;
    private Date arrivalTime;
    private Date departureTime;
    private int price;
    private final List<Passenger> passengers;
    private final List<Seat> seats;

    // constructor
    public Flight(int flightNumber,String airline, Airport origin, Airport destination, Date arrivalTime, Date departureTime, int price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.airline=airline;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.passengers = new ArrayList<>();
        this.price=price;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= 24; i++) {
            seats.add(new Seat(i));
        }
    }

    // method to add passenger
    public Ticket addPassenger(Passenger passenger, Seat seat) {
        passengers.add(passenger);
        Ticket ticket = new Ticket(passenger, this, seat);
        return ticket;
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

    public int getPrice() { return price;}

    public String getAirline() { return  airline; }

    public Date getDepartureTime() {
        return departureTime;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
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

    public void setPrice(int price) { this.price=price; }

    public void setAirline(String airline) { this.airline=airline;}

    //method to remove passenger
    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
    }

    //method to print details
    @Override
    public void printDetails() {
        System.out.println("Flight Number: " + flightNumber + "\nAirline: " + airline + "\n Origin: " + origin
                + ", Destination: " + destination + "\n Departure Time: " + departureTime
                + ", Arrival Time: " + arrivalTime + "\n");
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return flightNumber + "," + airline + "," + origin.getName() + "," + origin.getCity() + "," + origin.getState() + "," +
                destination.getName() + "," + destination.getCity() + "," + destination.getState() + "," +
                sdf.format(departureTime) + "," + sdf.format(arrivalTime);
    }

}