import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//gui frame for admin
class AdminMenuFrame extends JFrame {
    public AdminMenuFrame(Admin admin) {
        setTitle("Admin Menu");
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

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome, Admin!" );
        JLabel newLabel = new JLabel("Select an option");
        newLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        JButton addFlightButton = new JButton("Add Flight");
        addFlightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton editFlightButton = new JButton("Edit Flight");
        editFlightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton displayFlightsButton = new JButton("Display All Flights");
        displayFlightsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to main panel with spacing
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(welcomeLabel);
        mainPanel.add(newLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(addFlightButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(editFlightButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(displayFlightsButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(logoutButton);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Action listeners
        addFlightButton.addActionListener(e -> {
            new AddFlightFrame(admin).setVisible(true);
            dispose();
        });

        editFlightButton.addActionListener(e -> {
            new EditFlightFrame(admin).setVisible(true);
            dispose();
        });

        displayFlightsButton.addActionListener(e -> {
            new AdminViewFlightsFrame(admin).setVisible(true);
            dispose();
        });

        logoutButton.addActionListener(e -> {
            new FlightReservationSystemGUI().setVisible(true);
            dispose();
        });
    }
}

class AddFlightFrame extends JFrame {
    public AddFlightFrame(Admin admin) {
        setTitle("Add Flight");
        setSize(550, 640);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(224, 233, 240));

        JPanel inputPanel = new JPanel(new GridLayout(12, 2, 10, 10));
        inputPanel.setBackground(new Color(224, 233, 240));

        JTextField flightNumberField = new JTextField();
        JTextField airlineField = new JTextField();
        JTextField originNameField = new JTextField();
        JTextField originCityField = new JTextField();
        JTextField originStateField = new JTextField();
        JTextField destinationNameField = new JTextField();
        JTextField destinationCityField = new JTextField();
        JTextField destinationStateField = new JTextField();
        JTextField departureTimeField = new JTextField();
        JTextField arrivalTimeField = new JTextField();
        JTextField priceField = new JTextField();

        inputPanel.add(new JLabel("Flight Number:"));
        inputPanel.add(flightNumberField);
        inputPanel.add(new JLabel("Airline:"));
        inputPanel.add(airlineField);
        inputPanel.add(new JLabel("Origin Airport Name:"));
        inputPanel.add(originNameField);
        inputPanel.add(new JLabel("Origin City:"));
        inputPanel.add(originCityField);
        inputPanel.add(new JLabel("Origin State:"));
        inputPanel.add(originStateField);
        inputPanel.add(new JLabel("Destination Airport Name:"));
        inputPanel.add(destinationNameField);
        inputPanel.add(new JLabel("Destination City:"));
        inputPanel.add(destinationCityField);
        inputPanel.add(new JLabel("Destination State:"));
        inputPanel.add(destinationStateField);
        inputPanel.add(new JLabel("Departure Time (yyyy-MM-dd HH:mm:ss):"));
        inputPanel.add(departureTimeField);
        inputPanel.add(new JLabel("Arrival Time (yyyy-MM-dd HH:mm:ss):"));
        inputPanel.add(arrivalTimeField);
        inputPanel.add(new JLabel(("Price for flight")));
        inputPanel.add(priceField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(224, 233, 240));

        JButton addButton = new JButton("Add Flight");
        JButton exitButton = new JButton("Back");

        buttonPanel.add(addButton);
        buttonPanel.add(exitButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        addButton.addActionListener(e -> {
            try {
                int flightNumber = Integer.parseInt(flightNumberField.getText());
                String airline = airlineField.getText();
                String originName = originNameField.getText();
                String originCity = originCityField.getText();
                String originState = originStateField.getText();
                String destinationName = destinationNameField.getText();
                String destinationCity = destinationCityField.getText();
                String destinationState = destinationStateField.getText();
                int price = Integer.parseInt(priceField.getText());
                Date departureTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(departureTimeField.getText());
                Date arrivalTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(arrivalTimeField.getText());
                Airport origin = new Airport(originName, originCity, originState);
                Airport destination = new Airport(destinationName, destinationCity, destinationState);
                Flight newFlight = new Flight(flightNumber, airline, origin, destination, departureTime, arrivalTime, price);
                admin.addFlight(newFlight);
                JOptionPane.showMessageDialog(null, "Flight added successfully!");

            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date format! Please try again.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid flight number! Please enter a valid integer.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please try again.");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminMenuFrame(admin).setVisible(true);
                dispose();
            }
        });

    }
}

class EditFlightFrame extends JFrame {
    public EditFlightFrame(Admin admin) {
        setTitle("Edit Flight");
        setSize(550, 640);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(224, 233, 240));

        JPanel inputPanel = new JPanel(new GridLayout(11, 2, 10, 10));
        inputPanel.setBackground(new Color(224, 233, 240));

        JTextField flightNumberField = new JTextField();
        JTextField airlineField = new JTextField();
        JTextField originNameField = new JTextField();
        JTextField originCityField = new JTextField();
        JTextField originStateField = new JTextField();
        JTextField destinationNameField = new JTextField();
        JTextField destinationCityField = new JTextField();
        JTextField destinationStateField = new JTextField();
        JTextField departureTimeField = new JTextField();
        JTextField arrivalTimeField = new JTextField();

        inputPanel.add(new JLabel("Flight Number:"));
        inputPanel.add(flightNumberField);
        inputPanel.add(new JLabel("Airline:"));
        inputPanel.add(airlineField);
        inputPanel.add(new JLabel("Origin Airport Name:"));
        inputPanel.add(originNameField);
        inputPanel.add(new JLabel("Origin City:"));
        inputPanel.add(originCityField);
        inputPanel.add(new JLabel("Origin State:"));
        inputPanel.add(originStateField);
        inputPanel.add(new JLabel("Destination Airport Name:"));
        inputPanel.add(destinationNameField);
        inputPanel.add(new JLabel("Destination City:"));
        inputPanel.add(destinationCityField);
        inputPanel.add(new JLabel("Destination State:"));
        inputPanel.add(destinationStateField);
        inputPanel.add(new JLabel("Departure Time (yyyy-MM-dd HH:mm:ss):"));
        inputPanel.add(departureTimeField);
        inputPanel.add(new JLabel("Arrival Time (yyyy-MM-dd HH:mm:ss):"));
        inputPanel.add(arrivalTimeField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(224, 233, 240));

        JButton editButton = new JButton("Edit Flight");
        JButton exitButton = new JButton("Back");

        buttonPanel.add(editButton);
        buttonPanel.add(exitButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        editButton.addActionListener(e -> {
            try {
                int flightNumber = Integer.parseInt(flightNumberField.getText());
                String airline = airlineField.getText();
                String originName = originNameField.getText();
                String originCity = originCityField.getText();
                String originState = originStateField.getText();
                Airport originAirport = new Airport(originName, originCity, originState);

                String destinationName = destinationNameField.getText();
                String destinationCity = destinationCityField.getText();
                String destinationState = destinationStateField.getText();
                Airport destinationAirport = new Airport(destinationName, destinationCity, destinationState);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date departureTime = dateFormat.parse(departureTimeField.getText());
                Date arrivalTime = dateFormat.parse(arrivalTimeField.getText());

                admin.editFlight(flightNumber, airline, originAirport, destinationAirport, departureTime, arrivalTime, 1);
                JOptionPane.showMessageDialog(null, "Flight details updated successfully!");
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date format!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid flight number! Please enter a valid integer.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please try again.");
            }
        });

        exitButton.addActionListener(e -> {
            new AdminMenuFrame(admin).setVisible(true);
            dispose();
        });

    }
}

class AdminViewFlightsFrame extends JFrame {
    private DefaultTableModel flightsTableModel;
    private Admin admin;

    public AdminViewFlightsFrame(Admin admin) {
        this.admin=admin;
        setTitle("View Flights");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(172, 188, 207)); // Blue color
        topPanel.setPreferredSize(new Dimension(600, 27));

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

        add(panel);

        backButton.addActionListener(e -> {
            new AdminMenuFrame(admin).setVisible(true);
            dispose();
        });

        cancelButton.addActionListener(e -> {
            int selectedRow = flightsTable.getSelectedRow();
            if (selectedRow != -1) {
                int flightNumber = (int) flightsTableModel.getValueAt(selectedRow, 0);
                admin.removeFlight(flightNumber);
                JOptionPane.showMessageDialog(null, "You have deleted flight: " + flightNumber);
                flightsTableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a flight to delete.");
            }
        });

        loadFlights();
    }

    private void loadFlights() {
        for (Flight flight : admin.getFlights()) {
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




