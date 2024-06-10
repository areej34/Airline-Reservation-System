# Flight-Reservation-System
end semester project for OOP

# Detailed Code Logic and Approaches
This code models a simple flight booking system with key functionalities for managing flights, airports, passengers, and user interactions. Here's a detailed breakdown of the logic and approaches used:

# 1. Class Hierarchy and Relationships
Class Hierarchy:
User (Abstract Parent Class)
Admin (Child Class)
Passenger (Child Class)
Airport (Class)
Flight (Class)
Seat (Class)
Ticket (Class)

Relationships:

A Flight has an origin and destination airport and a list of seats.
A Passenger can book a Flight and is associated with a Seat on that flight.
User can be either an Admin or a Passenger, with different capabilities.

# 2. Class Definitions and Responsibilities
Airport Class:
Defines properties for airports such as name, city, and state.

Flight Class:
Manages flight details including flight number, origin, destination, seats, and passengers. It includes methods to find available seats and manage passenger lists.

Seat Class:
Represents a seat on a flight, with methods to reserve and free seats.

Ticket Class:
Represents a booked ticket, linking a passenger to a specific flight and seat.

User Class:
Abstract class for user roles, with subclasses Admin and Passenger implementing specific functionalities.

Admin Class:
Allows managing flights. Includes methods for adding, editing, viewing, and deleting flights.

Passenger Class:
Allows booking and canceling flight bookings. Includes methods for booking flights, canceling bookings, and viewing booked flights.

# 3. User Interaction Logic
Admin Actions:
Admins can add, edit, view, and delete flights through a graphical user interface (GUI). They interact with the system to manage flight data.

Passenger Actions:
Passengers can sign up, log in, search for flights, book flights, cancel bookings, and view booked flights and tickets through a GUI. The system checks for available seats and manages passenger reservations.
