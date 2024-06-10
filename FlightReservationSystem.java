import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FlightReservationSystem {
    static Scanner scanner = new Scanner(System.in);
    private static final String PASSENGERS_FILE = "passengers.txt";

    public static void main(String[] args) {
        FlightReservationSystemGUI app = new FlightReservationSystemGUI();
        app.setVisible(true);

        Admin admin = new Admin("admin", "admin123", "01");
        int choice;
        while (true) {
            try {
                do {
                    System.out.println("Select User Type:");
                    System.out.println("1. Admin");
                    System.out.println("2. Passenger");
                    System.out.println("3. Exit");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            adminLogIn(admin);
                            break;
                        case 2:
                            passengerOptions();
                            break;
                        case 3:
                            System.out.println("Exiting...");
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } while (true);
            } catch (Exception e) {
                System.out.println("Incorrect input, try again!");
                scanner.nextLine();
            }
        }
    }

    public static void adminLogIn(Admin admin) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            admin.menu();
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void passengerOptions() {
        int choice;
        while (true) {
            try {
                do {
                    System.out.println("Passenger Actions:");
                    System.out.println("1. Sign Up");
                    System.out.println("2. Log In");
                    System.out.println("3. Go Back");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            signUp();
                            break;
                        case 2:
                            passengerLogIn();
                            break;
                        case 3:
                            return;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } while (true);
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                scanner.nextLine();
            }
        }
    }

    protected static void signUp() {
        while (true) {
            try {
                System.out.println("Enter name:");
                String name = scanner.nextLine();
                System.out.println("Enter phoneNumber:");
                String phoneNumber = scanner.nextLine();
                System.out.println("Enter email:");
                String email = scanner.nextLine();
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();

                if (isUsernameTaken(username)) {
                    System.out.println("Username already taken. Please try again.");
                    return;
                }

                if (isValidPhoneNumber(phoneNumber)) {
                    System.out.println("Incorrect phone number format.");
                    return;
                }

                if (username.length() > 10) {
                    System.out.println("Username cannot be longer than 10 characters.");
                    return;
                } else if (isValidEmail(email)) {
                    System.out.println("Invalid email format.");
                    return;
                } else if (password.length() > 8) {
                    System.out.println("Password cannot be longer than 8 characters.");
                    return;
                }

                registerPassenger(name, phoneNumber, email, username, password);
                break;
            } catch (IOException e) {
                System.out.println("Error while signing up. Please try again later.");
            }
        }
    }

    private static void passengerLogIn() {
        while (true) {
            try {
                System.out.println("Enter username:");
                String username = scanner.nextLine();
                System.out.println("Enter password:");
                String password = scanner.nextLine();

                Passenger p = authenticate(username, password);
                if (p != null) {
                    p.menu();
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                }
                break;
            } catch (IOException e) {
                System.out.println("Error while logging in. Please try again.");
            }
        }
    }

    protected static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pat = Pattern.compile(emailRegex);
        return email == null || !pat.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneNumberPattern = "^(\\+\\d{1,2}\\s?)?1?-?\\.?\\(?\\d{3}\\)?[\\s\\-.]?\\d{3}[\\s\\-.]?\\d{4}$";
        Pattern pattern = Pattern.compile(phoneNumberPattern);
        Matcher matcher = pattern.matcher(phoneNumber);
        return !matcher.matches();
    }

    static boolean isUsernameTaken(String username) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSENGERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Passenger passenger = Passenger.fromString(line);
                if (passenger.getUsername().equals(username)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            // File not found, meaning no passengers registered yet
            return false;
        }
        return false;
    }

    static void registerPassenger(String name, String email, String phoneNumber, String username, String password) throws IOException {
        // Create a new Passenger object
        Passenger newPassenger = new Passenger(name, email, phoneNumber, username, password);

        // Use a try-with-resources block to ensure streams are closed properly
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASSENGERS_FILE, true))) {
            // Write the new passenger to the file
            writer.write(newPassenger.toString());
            writer.newLine();
        }
    }

    static Passenger authenticate(String username, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSENGERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Passenger passenger = Passenger.fromString(line);
                if (passenger.getUsername().equals(username) && passenger.getPassword().equals(password)) {
                    return passenger;
                }
            }
        } catch (IOException e) {
            // File not found or error reading file
            return null;
        }
        return null;
    }
}

// FlightReservationSystemGUI class
class FlightReservationSystemGUI extends JFrame {
    // Constructor
    public FlightReservationSystemGUI() {
        //frame
        setTitle("Flight Reservation System");
        setSize(400, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        //panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.setBackground(Color.WHITE);

        //image
        ImageIcon originalIcon = new ImageIcon("Aircraft aviation icon.jpg");
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(214, 120, Image.SCALE_REPLICATE); // Adjust size as needed
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Add a logo at the top
        JLabel logoLabel = new JLabel(scaledIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add a label underneath the logo
        JLabel selectLabel = new JLabel("Select your option:");
        selectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create buttons
        JButton adminButton = new JButton("Admin");
        adminButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton passengerButton = new JButton("Passenger");
        passengerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add everything to panel
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(logoLabel);
        mainPanel.add(selectLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(adminButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some space between buttons
        mainPanel.add(passengerButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some space between buttons
        mainPanel.add(exitButton);

        //add panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Button actions
        adminButton.addActionListener(e -> {
            new AdminLoginFrame().setVisible(true);
            dispose(); // Close the main frame after opening the admin login frame
        });

        passengerButton.addActionListener(e -> {
            new PassengerOptionFrame().setVisible(true);
            dispose();
        });

        exitButton.addActionListener(e -> System.exit(0));
    }

}

//gui for admin login frame
class AdminLoginFrame extends JFrame{
    //constructor
    public AdminLoginFrame() {
        setVisible(true);
        Admin admin = new Admin("admin", "admin123", "01");

        //frame
        setTitle("Admin Login");
        setSize(400, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        //panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(224, 233, 240));

        //panel 2
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 60, 20));
        buttonPanel.setBackground(new Color(224, 233, 240));

        // Initialize components
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField usernameField = new JTextField();
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setMaximumSize(new Dimension(200, 30));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setMaximumSize(new Dimension(200, 30));

        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel selectLabel = new JLabel("Enter your username and password:");
        selectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the main panel
        mainPanel.add(Box.createRigidArea(new Dimension(0, 55)));
        mainPanel.add(selectLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add top margin
        mainPanel.add(usernameLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));  // Add space between label and field
        mainPanel.add(usernameField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between fields
        mainPanel.add(passwordLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));  // Add space between label and field
        mainPanel.add(passwordField);
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // add panels to frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            Arrays.fill(passwordChars, ' '); // Clear password from memory after use
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(null, "Login successful.");
                new AdminMenuFrame(admin).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login unsuccessful. Try again.");
            }
        });

        backButton.addActionListener(e -> {
            new FlightReservationSystemGUI().setVisible(true);
            dispose();
        });


    }
}

//gui for passengers options
class PassengerOptionFrame extends JFrame {
    // Constructor
    public PassengerOptionFrame() {
        //frame
        setTitle("Passenger Options");
        setSize(400, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //panel 1
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.gray);

        //panel 2
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);

        // Add an image on the left side
        ImageIcon originalImage = new ImageIcon("travel.jpg");
        Image newImage = originalImage.getImage();
        Image scaledImage = newImage.getScaledInstance(190, 330, Image.SCALE_REPLICATE); // Adjust size as needed
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledIcon);

        // Panel 3
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(75, 50, 100, 50));
        optionsPanel.setBackground(new Color(159, 173, 180));

        JLabel optionLabel = new JLabel("Would you like to");
        optionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signInButton = new JButton("Sign In");
        signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the options panel
        imagePanel.add(logoLabel, BorderLayout.CENTER);
        optionsPanel.add(optionLabel);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        optionsPanel.add(signInButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        optionsPanel.add(signUpButton);
        optionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        optionsPanel.add(backButton);

        // Add panels to the main panel
        mainPanel.add(imagePanel, BorderLayout.WEST);
        mainPanel.add(optionsPanel, BorderLayout.EAST);

        //add main panel to frame
        add(mainPanel);

        // Action listeners
        signInButton.addActionListener(e -> {
            new PassengerLoginFrame().setVisible(true);
            dispose();
        });

        signUpButton.addActionListener(e -> {
            new PassengerSignUpFrame().setVisible(true);
            dispose();
        });

        backButton.addActionListener(e -> {
            new FlightReservationSystemGUI().setVisible(true);
            dispose(); // Set current frame invisible instead of disposing
        });
    }
}

//gui for passenger sign up
class PassengerSignUpFrame extends JFrame {
    //constructor
    public PassengerSignUpFrame() {
        //frame
        setTitle("Passenger Sign Up");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //panel 1
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(224, 233, 240));

        //panel 2
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(45, 20, 45, 20));
        formPanel.setBackground(new Color(224, 233, 240));
        formPanel.setOpaque(true);

        // Initialize components
        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel phoneLabel = new JLabel("Phone Number:");
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JButton signUpButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");

        Dimension textFieldSize = new Dimension(150, 25);

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(textFieldSize);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(textFieldSize);

        JTextField phoneField = new JTextField();
        phoneField.setPreferredSize(textFieldSize);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(textFieldSize);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(textFieldSize);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(textFieldSize);

        // Add components to the form panel
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(confirmPasswordLabel);
        formPanel.add(confirmPasswordField);
        formPanel.add(signUpButton);
        formPanel.add(backButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Action listeners
        signUpButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            try {
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match. Try again.");
                    return;
                }

                if (FlightReservationSystem.isValidPhoneNumber(phoneNumber)) {
                    JOptionPane.showMessageDialog(null, "Invalid phone number. Try again.");
                    return;
                }

                if (FlightReservationSystem.isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Invalid email format. Try again.");
                    return;
                }

                if (FlightReservationSystem.isUsernameTaken(username)) {
                    JOptionPane.showMessageDialog(null, "Username already taken. Try again.");
                    return;
                }

                if (username.length() > 10) {
                    JOptionPane.showMessageDialog(null, "Username cannot be longer than 10 characters. Try again.");
                    return;
                }

                FlightReservationSystem.registerPassenger(name, email, phoneNumber, username, password);
                JOptionPane.showMessageDialog(null, "Sign up successful.");
                Passenger p = new Passenger(name, email, phoneNumber, username, password);
                new PassengerMenuFrame(p).setVisible(true);
                dispose(); // Close sign-up frame after successful registration
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred. Please try again later.");
            }
        });

        backButton.addActionListener(e -> {
            new PassengerOptionFrame().setVisible(true);
            dispose();
        });

        add(mainPanel);
    }
}

//gui for passenger log in
class PassengerLoginFrame extends JFrame {
    //constructor
    public PassengerLoginFrame() {
        //frame
        setTitle("Passenger Login");
        setSize(400, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        //panel 1
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(224, 233, 240));

        //panel 2
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 60, 20));
        buttonPanel.setBackground(new Color(224, 233, 240));

        // Initialize components
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField usernameField = new JTextField();
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setMaximumSize(new Dimension(200, 30));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setMaximumSize(new Dimension(200, 30));

        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel selectLabel = new JLabel("Enter your username and password:");
        selectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the main panel
        mainPanel.add(Box.createRigidArea(new Dimension(0, 55)));
        mainPanel.add(selectLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add top margin
        mainPanel.add(usernameLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));  // Add space between label and field
        mainPanel.add(usernameField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between fields
        mainPanel.add(passwordLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));  // Add space between label and field
        mainPanel.add(passwordField);
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Add the panels to the frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        loginButton.addActionListener(e -> {
            try {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Authenticate the user
                Passenger passenger = FlightReservationSystem.authenticate(username, password);
                if (passenger != null) {
                    JOptionPane.showMessageDialog(null, "Login successful.");
                    new PassengerMenuFrame(passenger).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred. Please try again later.");
            }
        });

        backButton.addActionListener(e -> {
            new PassengerOptionFrame().setVisible(true);
            dispose();
        });
    }
}


