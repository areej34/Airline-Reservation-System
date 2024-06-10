import java.io.Serializable;

//seat class with interface seat management
public class Seat implements Serializable, SeatManagement {
    private static final long serialVersionUID = 1L;
    //private attributes
    private final int seatNumber;
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
    @Override
    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    //override method from object class
    @Override
    public String toString() {
        return "Seat{" + "seatNumber=" + seatNumber + ", booked=" + booked + '}';
    }
}