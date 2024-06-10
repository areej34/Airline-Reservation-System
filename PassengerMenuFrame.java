import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//gui for passenger menu
public class PassengerMenuFrame extends JFrame {
    //constructor
    public PassengerMenuFrame(Passenger passenger) {
        //frame
        setTitle("Passenger Menu");
        setSize(400, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(224, 233, 240));
        setResizable(false);
        setLayout(new BorderLayout());

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(224, 233, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Welcome labels
        JLabel welcomeLabel = new JLabel("We Take you to your desired destination!");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welLabel = new JLabel("Select from the options: ");
        welLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        JButton searchFlightButton = new JButton("Search Flight");
        searchFlightButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton viewBookedFlightsButton = new JButton("View Booked Flights");
        viewBookedFlightsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton viewTicketButton = new JButton("View Ticket");
        viewTicketButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to main panel with spacing
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(welcomeLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(welLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(searchFlightButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(viewBookedFlightsButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(viewTicketButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(logoutButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        //action listeners
        searchFlightButton.addActionListener(e -> {
            new OriginDestinationFrame(passenger).setVisible(true);
            dispose();
        });

        viewBookedFlightsButton.addActionListener(e -> {
            new PassengerViewFlightsFrame(passenger).setVisible(true);
            dispose();
        });

        viewTicketButton.addActionListener(e -> {
            new TicketFrame(passenger).setVisible(true);
            dispose();
        });

        logoutButton.addActionListener(e -> {
            new FlightReservationSystemGUI().setVisible(true);
            dispose();
        });
    }
}


class TicketFrame extends JFrame {
    public TicketFrame(Passenger passenger) {
        setTitle("Booked Tickets");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(620, 360);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel ticketPanel = new JPanel();
        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
        ticketPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel passengerPanel = new JPanel();
        passengerPanel.setLayout(new BoxLayout(passengerPanel, BoxLayout.Y_AXIS));
        passengerPanel.setBorder(BorderFactory.createEtchedBorder());
        passengerPanel.setBackground(new Color(172, 188, 207));

        JLabel nameLabel = new JLabel("Passenger: " + passenger.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passengerPanel.add(Box.createVerticalStrut(10));
        passengerPanel.add(nameLabel);
        passengerPanel.add(Box.createVerticalStrut(5));

        for (Ticket ticket : passenger.getBookedTickets()) {
            JPanel ticketInfoPanel = new JPanel(new GridLayout(1, 2));
            ticketInfoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            JLabel passengerInfoLabel = new JLabel("<html><b>Passenger Information:</b><br><br>" +
                    "Name: " + passenger.getName() + "<br>" +
                    "Seat Number: " + ticket.getSeat().getSeatNumber() + "<br>" +
                    "Phone Number: " + passenger.getPhoneNumber() + "</html>");
            JLabel flightInfoLabel = new JLabel("<html><b>Flight Information:</b><br><br>" +
                    "Flight Number: " + ticket.getFlight().getFlightNumber() + "<br>" +
                    "Airline: " + ticket.getFlight().getAirline() + "<br>" +
                    "Origin City: " + ticket.getFlight().getOrigin().getCity() + "<br>" +
                    "Destination City: " + ticket.getFlight().getDestination().getCity() + "<br>" +
                    "Departure Time: " + ticket.getFlight().getDepartureTime() + "<br>" +
                    "Price: " + ticket.getFlight().getPrice() + "</html>");

            ticketInfoPanel.add(passengerInfoLabel);
            ticketInfoPanel.add(flightInfoLabel);
            passengerPanel.add(ticketInfoPanel);
        }

        passengerPanel.add(Box.createVerticalStrut(10));
        ticketPanel.add(passengerPanel);

        JButton backButton = new JButton("Back");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(new JScrollPane(ticketPanel), BorderLayout.CENTER);

        add(mainPanel);

        backButton.addActionListener(e -> {
            new PassengerMenuFrame(passenger).setVisible(true);
            dispose();
        });

    }
}

class PassengerViewFlightsFrame extends JFrame {

    private final DefaultTableModel flightsTableModel;
    private final Passenger passenger;

    public PassengerViewFlightsFrame(Passenger passenger) {
        this.passenger = passenger;
        setTitle("View Booked Flights");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(172, 188, 207)); // Blue color
        topPanel.setPreferredSize(new Dimension(600, 27));

        JLabel welcomeLabel = new JLabel("We Take YOU To Your Desired Destination");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        topPanel.add(welcomeLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        flightsTableModel = new DefaultTableModel(new String[]{"Flight Number", "Origin", "Destination", "Departure Time", "Arrival Time"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable flightsTable = new JTable(flightsTableModel);
        flightsTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(flightsTable);

        JButton backButton = new JButton("Back");

        JButton cancelButton = new JButton("Cancel");

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(backButton);
        bottomPanel.add(cancelButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        loadBookedFlights();

        add(panel);

        backButton.addActionListener(e -> {
            new PassengerMenuFrame(passenger).setVisible(true);
            dispose();
        });

        cancelButton.addActionListener(e -> {
            int selectedRow = flightsTable.getSelectedRow();
            if (selectedRow != -1) {
                int flightNumber = (int) flightsTableModel.getValueAt(selectedRow, 0);
                passenger.cancelFlight(flightNumber);
                JOptionPane.showMessageDialog(null, "You are no longer booked on flight: " + flightNumber);
                flightsTableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a flight to cancel.");
            }
        });
    }

    private void loadBookedFlights() {
        if (passenger.getBookedTickets() != null) {
            for (Ticket ticket : passenger.getBookedTickets()) {
                Flight flight = ticket.getFlight();

                Object[] rowData = {
                        flight.getFlightNumber(),
                        flight.getOrigin().getCity(),
                        flight.getDestination().getCity(),
                        flight.getDepartureTime().toString(),
                        flight.getArrivalTime().toString(),
                };
                flightsTableModel.addRow(rowData);
            }
        }
    }
}

//frame to search flights
class OriginDestinationFrame extends JFrame {

    private JComboBox<String> originComboBox;
    private JComboBox<String> destinationComboBox;
    private JTable flightsTable;
    private DefaultTableModel flightsTableModel;
    private Admin admin;

    // Constructor
    public OriginDestinationFrame(Passenger p) {
        // Frame
        admin = new Admin("admin", "admin123", "01");
        setTitle("Flight Booking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel 1
        JPanel panel = new JPanel(new BorderLayout());

        // Panel 2
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 35, 20, 20));
        selectionPanel.setBackground(new Color(172, 188, 207));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        bottomPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ComboBox
        originComboBox = new JComboBox<>(getCityOptions());
        originComboBox.setEditable(false);
        destinationComboBox = new JComboBox<>(getCityOptions());
        destinationComboBox.setEditable(false);

        // Images
        ImageIcon originalIcon = new ImageIcon("black and white logo for safari.jpg");
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Changed to SCALE_SMOOTH for better quality
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Search button
        JButton searchButton = new JButton(scaledIcon);
        searchButton.setPreferredSize(new Dimension(27, 27));
        searchButton.setBackground(new Color(123, 156, 169));
        JButton bookButton = new JButton("Book Selected Flight");
        JButton backButton = new JButton("Back");
        bookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add to selection panel
        selectionPanel.add(new JLabel("Select Origin:"));
        selectionPanel.add(originComboBox);
        selectionPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        selectionPanel.add(new JLabel("Select Destination:"));
        selectionPanel.add(destinationComboBox);
        selectionPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        selectionPanel.add(searchButton);
        bottomPanel.add(bookButton);
        bottomPanel.add(backButton);

        // Table for flights
        flightsTableModel = new DefaultTableModel(new String[]{"Flight Number", "Airline", "Departure Time", "Arrival Time", "Price"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        flightsTable = new JTable(flightsTableModel);
        flightsTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane tableScrollPane = new JScrollPane(flightsTable);

        // Add other panels to main panel
        panel.add(selectionPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Add panel to frame
        add(panel);

        // Action listener for search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String origin = (String) originComboBox.getSelectedItem();
                String destination = (String) destinationComboBox.getSelectedItem();
                List<Flight> flights = admin.getFlightsByOriginAndDestination(origin, destination);
                updateFlightsTable(flights);
            }
        });

        // Action listener for book button
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = flightsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String flightNumberStr = (String) flightsTableModel.getValueAt(selectedRow, 0);
                    try {
                        int flightNumber = Integer.parseInt(flightNumberStr);
                        Flight selectedFlight = admin.findFlightByNumber(flightNumber);
                        new SeatSelectionFrame(selectedFlight, p).setVisible(true);
                        dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Invalid flight number format.");
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Please select a flight to book.");
                }
            }
        });

        // Action listener for back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PassengerMenuFrame(p).setVisible(true);
                dispose();
            }
        });
    }

    private String[] getCityOptions() {
        // Dummy city options. Replace these with real data.
        return new String[]{"Karachi", "Islamabad", "Lahore", "Peshawar", "Faisalabad", "Gujranwala"};
    }

    private void updateFlightsTable(List<Flight> flights) {
        flightsTableModel.setRowCount(0); // Clear existing rows
        for (Flight flight : flights) {
            Object[] rowData = {
                    String.valueOf(flight.getFlightNumber()), // Ensure this is a string
                    flight.getAirline(),
                    flight.getDepartureTime().toString(),
                    flight.getArrivalTime().toString(),
                    flight.getPrice()
            };
            flightsTableModel.addRow(rowData);
        }
    }
}

class SeatSelectionFrame extends JFrame {

    private String selectedSeat;

    public SeatSelectionFrame(Flight flight, Passenger p) {
        setTitle("Seat Selection - " + flight.getFlightNumber());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(25, 20, 10, 20));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(60, 10, 10, 10));
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton bookButton = new JButton("Book");
        JButton backButton = new JButton("Back");

        JLabel flightInfoLabel = new JLabel("<html><b>Flight Information:</b><br><br>" +
                "Flight Number: " + flight.getFlightNumber() + "<br>" +
                "Airline: " + flight.getAirline() + "<br>" +
                "Origin City: " + flight.getOrigin().getCity() + "<br>" +
                "Destination City: " + flight.getDestination().getCity() + "<br>" +
                "Departure Time: " + flight.getDepartureTime() + "<br>" +
                "Price: " + flight.getPrice() + "</html>");
        flightInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(flightInfoLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(bookButton, LEFT_ALIGNMENT);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 7)));
        infoPanel.add(backButton, LEFT_ALIGNMENT);
        infoPanel.setBackground(new Color(185,202,222));

        JPanel seatSelectionPanel = new JPanel();
        seatSelectionPanel.setLayout(new BoxLayout(seatSelectionPanel, BoxLayout.Y_AXIS));
        seatSelectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel selectLabel = new JLabel("<html><b>Select a seat:</b><br></html>");
        selectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel seatGridPanel = new JPanel(new GridLayout(6, 4, 10, 10)); // 6 rows, 4 columns for seats
        addSeatSelectionComponents(seatGridPanel);

        seatSelectionPanel.add(selectLabel);
        seatSelectionPanel.add(seatGridPanel);

        JPanel sidePanel = new JPanel(new GridLayout(1, 2, 20, 0));
        sidePanel.add(infoPanel);
        sidePanel.add(seatSelectionPanel);

        panel.add(sidePanel, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panel.add(actionPanel, BorderLayout.SOUTH);

        add(panel);

        bookButton.addActionListener(e -> {
            if (selectedSeat != null) {
                int seatNumber = Integer.parseInt(selectedSeat);
                Seat seat= new Seat(seatNumber);
                if (seat.isBooked()!=true)
                {
                    JOptionPane.showMessageDialog(this, "Seat " + seatNumber + " selected and booking confirmed!");
                    new PassengerMenuFrame(p).setVisible(true);
                    p.bookFlight(flight.getFlightNumber(), seatNumber);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(this, "Seat " + seatNumber + " is already occupied, select another!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a seat.");
            }
        });

        backButton.addActionListener(e->{
            new OriginDestinationFrame(p).setVisible(true);
            dispose();
        });

    }

    private void addSeatSelectionComponents(JPanel panel) {
        int totalSeats = 24;

        for (int seatNum = 1; seatNum <= totalSeats; seatNum++) {
            String seat = String.valueOf(seatNum);
            JButton seatButton = new JButton(seat);
            seatButton.setPreferredSize(new Dimension(50, 50));
            seatButton.addActionListener(e -> {
                JButton clickedButton = (JButton) e.getSource();
                selectedSeat = clickedButton.getText();
                clickedButton.setBackground(Color.GREEN);
            });
            panel.add(seatButton);
        }
    }
}