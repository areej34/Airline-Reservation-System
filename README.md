# Flight-Reservation-System
end semester project for OOP

# Detailed Code Logic and Approaches
This code models a simple flight booking system with key functionalities for managing flights, airlines, airplanes, passengers, and user interactions. Here's a detailed breakdown of the logic and approaches used:

# 1. Class Hierarchy and Relationships
Class Hierarchy:

# Airport (Parent Class)
OriginAirport (Child Class)
IntermediateAirport (Child Class)
DestinationAirport (Child Class)
# User (Abstract Parent Class)
Admin (Child Class)
PassengerUser (Child Class)
Relationships:

A Flight has an origin, destination, possibly intermediate airports, and an associated airline.
An Airline can have multiple flights and airplanes.
A Passenger is associated with a seat on a flight.
User can be an Admin or a PassengerUser, with different capabilities.

# 2. Class Definitions and Responsibilities
Airport Class:
Defines common properties and methods for airports, such as name, city, and state. Subclasses add specific properties like departure and arrival times.

OriginAirport, IntermediateAirport, DestinationAirport:
These subclasses of Airport define specific types of airports involved in a flight's journey, with relevant additional properties.

Flight Class:
Manages flight details including flight number, origin, destination, intermediate airports, airline, seats, and passengers. It includes methods to find available seats and manage passenger lists.

Airline Class:
Represents an airline, containing a list of flights and airplanes. Methods allow adding and removing flights.

Airplane Class:
Defines an airplane with a model, total seats, and maximum capacity. Methods provide details about the airplane.

Seat Class:
Represents a seat on a flight, with methods to reserve and free seats.

Passenger Class:
Represents a passenger with a name, age, passport number, and associated seat. Methods print passenger details.

User Class:
Abstract class for user roles, with subclasses Admin and PassengerUser implementing specific functionalities.

Admin Class:
Allows managing flights and airlines. Includes methods for adding/removing flights and airlines. Implements an performActions method for admin-specific actions.

PassengerUser Class:
Allows booking and canceling flight bookings. Includes methods for booking flights, canceling bookings, and viewing booked flights. Implements an performActions method for passenger-specific actions.

# 3. User Interaction Logic
Admin Actions:
Admins can add and remove flights and airlines through a command-line interface (CLI). They interact with the system to manage flight and airline data.

Passenger Actions:
Passengers can book flights, cancel bookings, and view their booked flights through a CLI. The system checks for available seats and manages passenger reservations.
