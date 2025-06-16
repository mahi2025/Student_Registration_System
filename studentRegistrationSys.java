import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;


public class StudentRegistrationSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static JFrame mainFrame;  //The main application window 
    private static CardLayout cardLayout; //o switch between different panels 
    private static JPanel mainPanel; //Holds all the different view panels

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        mainFrame = new JFrame("HEUC Student Registration Portal");
        
        
        try {
            ImageIcon appIcon = new ImageIcon("logo.png");
            mainFrame.setIconImage(appIcon.getImage());
        } catch (Exception e) {
            System.out.println("Could not load the application icon" + e.getMessage());
        }
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600); 
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel entryPanel = EntryPanel();
        JPanel registrationPanel = RegistrationPanel();
        JPanel loginPanel = LoginPanel();
        JPanel adminPanel = AdminPanel();

        mainPanel.add(entryPanel, "ENTRY");
        mainPanel.add(registrationPanel, "REGISTRATION");
        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(adminPanel, "ADMIN");

        mainFrame.add(mainPanel);
        showPanel("ENTRY");
        mainFrame.setVisible(true);
    }

    //Creates a JPanel with a GridBagLayout
    private static JPanel EntryPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 245));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        try {
            ImageIcon logoIcon = new ImageIcon("logo.png");
            Image scaledImage = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(0, 0, 20, 0);
            panel.add(logoLabel, gbc);
        } catch (Exception e) {
            System.out.println("Could not load logo: " + e.getMessage());
        }
        
        JLabel titleLabel = new JLabel("Welcome to Student Registration Portal");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(51, 51, 51));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        panel.add(titleLabel, gbc);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 15));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        Dimension buttonSize = new Dimension(700, 600);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);

        JButton registerBtn = new JButton("Student");
        registerBtn.setPreferredSize(buttonSize);
        registerBtn.setFont(buttonFont);
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setBackground(new Color(41, 128, 185));
        registerBtn.setBorderPainted(false);
        registerBtn.setFocusPainted(false);
        
        JButton adminBtn = new JButton("Admin");
        adminBtn.setPreferredSize(buttonSize);
        adminBtn.setFont(buttonFont);
        adminBtn.setForeground(Color.WHITE);
        adminBtn.setBackground(new Color(46, 204, 113));
        adminBtn.setBorderPainted(false);
        adminBtn.setFocusPainted(false);

        registerBtn.addActionListener(e -> showPanel("REGISTRATION"));
        adminBtn.addActionListener(e -> showPanel("LOGIN"));

        buttonPanel.add(registerBtn);
        buttonPanel.add(adminBtn);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(buttonPanel, gbc);

        return panel;
    }

    //Creates a JPanel with a GridBagLayout for the student registration form
    private static JPanel RegistrationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titleLabel = new JLabel("Student Registration Form");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(51, 51, 51));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        panel.add(titleLabel, gbc);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
         
         //JTextfield for First Name and Last Name and ID
        JPanel firstNamePanel = new JPanel(new BorderLayout(10, 0));
        firstNamePanel.setBackground(new Color(245, 245, 245));
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(labelFont);
        JTextField firstNameField = new JTextField(15);
        firstNameField.setFont(inputFont);
        firstNameField.setPreferredSize(new Dimension(200, 25));
        firstNamePanel.add(firstNameLabel, BorderLayout.WEST);
        firstNamePanel.add(firstNameField, BorderLayout.CENTER);

        JPanel lastNamePanel = new JPanel(new BorderLayout(10, 0));
        lastNamePanel.setBackground(new Color(245, 245, 245));
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(labelFont);
        JTextField lastNameField = new JTextField(15);
        lastNameField.setFont(inputFont);
        lastNameField.setPreferredSize(new Dimension(200, 25));
        lastNamePanel.add(lastNameLabel, BorderLayout.WEST);
        lastNamePanel.add(lastNameField, BorderLayout.CENTER);
        
        //Jspinner for Date of Birth
        JPanel dobPanel = new JPanel(new BorderLayout(10, 0));
        dobPanel.setBackground(new Color(245, 245, 245));
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(labelFont);
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dobSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dobSpinner, "dd/MM/yyyy");
        dobSpinner.setEditor(dateEditor);
        dobSpinner.setFont(inputFont);
        dobSpinner.setPreferredSize(new Dimension(200, 25));
        dobPanel.add(dobLabel, BorderLayout.WEST);
        dobPanel.add(dobSpinner, BorderLayout.CENTER);

        //JRadioButton for Gender
        JPanel genderPanel = new JPanel(new BorderLayout(10, 0));
        genderPanel.setBackground(new Color(245, 245, 245));
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(labelFont);
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.setBackground(new Color(245, 245, 245));
        ButtonGroup genderGroup = new ButtonGroup();
        JRadioButton femaleRadio = new JRadioButton("Female");
        JRadioButton maleRadio = new JRadioButton("Male");
        femaleRadio.setFont(inputFont);
        maleRadio.setFont(inputFont);
        femaleRadio.setBackground(new Color(245, 245, 245));
        maleRadio.setBackground(new Color(245, 245, 245));
        genderGroup.add(femaleRadio);
        genderGroup.add(maleRadio);
        femaleRadio.setSelected(true);
        radioPanel.add(femaleRadio);
        radioPanel.add(maleRadio);
        genderPanel.add(genderLabel, BorderLayout.WEST);
        genderPanel.add(radioPanel, BorderLayout.CENTER);

        JPanel idPanel = new JPanel(new BorderLayout(10, 0));
        idPanel.setBackground(new Color(245, 245, 245));
        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(labelFont);
        JTextField idField = new JTextField(15);
        idField.setFont(inputFont);
        idField.setPreferredSize(new Dimension(200, 25));
        idField.setToolTipText("Enter ID in format: 3050/24");
        idPanel.add(idLabel, BorderLayout.WEST);
        idPanel.add(idField, BorderLayout.CENTER);

        //JcomboBox for Department and Year
        JPanel deptPanel = new JPanel(new BorderLayout(10, 0));
        deptPanel.setBackground(new Color(245, 245, 245));
        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setFont(labelFont);
        String[] departments = {
            "Computer Science",
            "Accounting",
            "Marketing",
            "Marketing Management"
        };
        JComboBox<String> deptCombo = new JComboBox<>(departments);
        deptCombo.setFont(inputFont);
        deptCombo.setPreferredSize(new Dimension(200, 25));
        deptPanel.add(deptLabel, BorderLayout.WEST);
        deptPanel.add(deptCombo, BorderLayout.CENTER);

        JPanel yearPanel = new JPanel(new BorderLayout(10, 0));
        yearPanel.setBackground(new Color(245, 245, 245));
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(labelFont);
        String[] years = {"1", "2", "3", "4"};
        JComboBox<String> yearCombo = new JComboBox<>(years);
        yearCombo.setFont(inputFont);
        yearCombo.setPreferredSize(new Dimension(200, 25));
        yearPanel.add(yearLabel, BorderLayout.WEST);
        yearPanel.add(yearCombo, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(firstNamePanel, gbc);

        gbc.gridy = 2;
        panel.add(lastNamePanel, gbc);

        gbc.gridy = 3;
        panel.add(dobPanel, gbc);

        gbc.gridy = 4;
        panel.add(genderPanel, gbc);

        gbc.gridy = 5;
        panel.add(idPanel, gbc);

        gbc.gridy = 6;
        panel.add(deptPanel, gbc);

        gbc.gridy = 7;
        panel.add(yearPanel, gbc);

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        JButton registerBtn = new JButton("Register");
        registerBtn.setFont(buttonFont);
        registerBtn.setPreferredSize(new Dimension(120, 35));
        registerBtn.setBackground(new Color(41, 128, 185));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setBorderPainted(false);
        registerBtn.setFocusPainted(false);
        
        JButton backBtn = new JButton("Back");
        backBtn.setFont(buttonFont);
        backBtn.setPreferredSize(new Dimension(120, 35));
        backBtn.setBackground(new Color(46, 204, 113));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        buttonPanel.add(registerBtn);
        buttonPanel.add(backBtn);

        gbc.gridy = 8;
        gbc.insets = new Insets(30, 0, 30, 0);
        panel.add(buttonPanel, gbc);

        registerBtn.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String fullName = firstName + " " + lastName;
            Date dob = (Date) dobSpinner.getValue();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dobStr = sdf.format(dob);
            String gender = femaleRadio.isSelected() ? "Female" : "Male";
            String id = idField.getText();
            String year = (String) yearCombo.getSelectedItem();
            String department = (String) deptCombo.getSelectedItem();

            if (firstName.isEmpty() || lastName.isEmpty() || id.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Please fill all fields!");
                return;
            }

            if (!validateStudentId(id)) {
                JOptionPane.showMessageDialog(mainFrame, 
                    "Invalid ID format! Please use format: 3050/24",
                    "Invalid ID",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            students.add(new Student(fullName, dobStr, gender, id, year, department));
            
            StringBuilder message = new StringBuilder();
            message.append("You are Registred Successfully!\n\n");
            message.append("Full Name: ").append(fullName).append("\n");
            message.append("Date of Birth: ").append(dobStr).append("\n");
            message.append("Gender: ").append(gender).append("\n");
            message.append("ID: ").append(id).append("\n");
            message.append("Year: ").append(year).append("\n");
            message.append("Department: ").append(department);
            
            JOptionPane.showMessageDialog(mainFrame, 
                message.toString(),
                "Registration Success",
                JOptionPane.INFORMATION_MESSAGE);
                
            showPanel("ENTRY");
        });

        backBtn.addActionListener(e -> showPanel("ENTRY"));

        return panel;
    }

    private static boolean validateStudentId(String id) {
        return id.matches("\\d{4}/\\d{2}");
    }

    private static boolean validateDateOfBirth(String dob) {
        return dob.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    //Creates a JPanel with a GridBagLayout for the admin login interface
    private static JPanel LoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel welcomeLabel = new JLabel("Welcome to Admin Login");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(25, 25, 112));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(welcomeLabel, gbc);

        JLabel instructionLabel = new JLabel("Please enter your username and password");
        instructionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(instructionLabel, gbc);

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);

        JTextField usernameField = new JTextField(20);
        usernameField.setFont(inputFont);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(inputFont);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);
        panel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(buttonFont);
        JButton backBtn = new JButton("Back");
        backBtn.setFont(buttonFont);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(loginBtn, gbc);

        gbc.gridy = 5;
        panel.add(backBtn, gbc);

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("hope") && password.equals("hope")) {
                showPanel("ADMIN");
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Invalid Username or Password");
            }
        });

        backBtn.addActionListener(e -> showPanel("ENTRY"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        buttonPanel.add(loginBtn);
        buttonPanel.add(backBtn);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(buttonPanel, gbc);

        return panel;
    }

    //Creates a JPanel with a BorderLayout to display all registered student records
    private static JPanel AdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 245));

        JLabel titleLabel = new JLabel("Student Records");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Full Name", "Date of Birth", "Gender", "ID", "Year", "Department"};
        Object[][] data = new Object[students.size()][6];
        
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = student.getFullName();
            data[i][1] = student.getDateOfBirth();
            data[i][2] = student.getGender();
            data[i][3] = student.getId();
            data[i][4] = student.getYear();
            data[i][5] = student.getDepartment();
        }

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setRowHeight(25);
        table.setFillsViewportHeight(true);
        //Table for student record
        table.getColumnModel().getColumn(0).setPreferredWidth(150); 
        table.getColumnModel().getColumn(1).setPreferredWidth(100); 
        table.getColumnModel().getColumn(2).setPreferredWidth(80);  
        table.getColumnModel().getColumn(3).setPreferredWidth(100); 
        table.getColumnModel().getColumn(4).setPreferredWidth(50);  
        table.getColumnModel().getColumn(5).setPreferredWidth(150); 

        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 300));

        //Updates the table model with the latest data
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setFont(buttonFont);
        refreshBtn.setPreferredSize(new Dimension(120, 35));
        refreshBtn.setBackground(new Color(41, 128, 185));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setBorderPainted(false);
        refreshBtn.setFocusPainted(false);
        
        JButton backBtn = new JButton("Back");
        backBtn.setFont(buttonFont);
        backBtn.setPreferredSize(new Dimension(120, 35));
        backBtn.setBackground(new Color(46, 204, 113));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        buttonPanel.add(refreshBtn);
        buttonPanel.add(backBtn);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        refreshBtn.addActionListener(e -> {
            Object[][] newData = new Object[students.size()][6];
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                newData[i][0] = student.getFullName();
                newData[i][1] = student.getDateOfBirth();
                newData[i][2] = student.getGender();
                newData[i][3] = student.getId();
                newData[i][4] = student.getYear();
                newData[i][5] = student.getDepartment();
            }
            
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(newData, columnNames);
            table.setModel(model);
            
            table.getColumnModel().getColumn(0).setPreferredWidth(150);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(80);
            table.getColumnModel().getColumn(3).setPreferredWidth(100);
            table.getColumnModel().getColumn(4).setPreferredWidth(50);
            table.getColumnModel().getColumn(5).setPreferredWidth(150);
        });

        backBtn.addActionListener(e -> showPanel("ENTRY"));

        return panel;
    }

    //Helper method to control CardLayout
    private static void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }
        //Inner Static class for individual student records
    static class Student {
        private String fullName;
        private String dateOfBirth;
        private String gender;
        private String id;
        private String year;
        private String department;

        //Initilize a new student object with provided data
        public Student(String fullName, String dateOfBirth, String gender, String id, String year, String department) {
            this.fullName = fullName;
            this.dateOfBirth = dateOfBirth;
            this.gender = gender;
            this.id = id;
            this.year = year;
            this.department = department;
        }
            //To access the private data members
        public String getFullName() { return fullName; }
        public String getDateOfBirth() { return dateOfBirth; }
        public String getGender() { return gender; }
        public String getId() { return id; }
        public String getYear() { return year; }
        public String getDepartment() { return department; }
    }
} 