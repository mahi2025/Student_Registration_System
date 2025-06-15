import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;


public class StudentRegistrationSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static JFrame mainFrame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;

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
        mainFrame = new JFrame("Student Registration Portal");
        //i want to add icon/ school logo before the text
        // i want to change the java icon to in logo of the school 
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 400);
        mainFrame.setLocationRelativeTo(null);
        

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create panels
        JPanel entryPanel = createEntryPanel();
        JPanel registrationPanel = createRegistrationPanel();
        JPanel loginPanel = createLoginPanel();
        JPanel adminPanel = createAdminPanel();

        // Add panels to card layout
        mainPanel.add(entryPanel, "ENTRY");
        mainPanel.add(registrationPanel, "REGISTRATION");
        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(adminPanel, "ADMIN");

        mainFrame.add(mainPanel);
        showPanel("ENTRY");
        mainFrame.setVisible(true);
    }

    private static JPanel createEntryPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255)); // Light blue background
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Create a title label
        JLabel titleLabel = new JLabel("Student Registration Portal");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(25, 25, 112)); // Dark blue text
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 30, 0);
        panel.add(titleLabel, gbc);

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 15));
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        // Style for buttons
        Dimension buttonSize = new Dimension(250, 80);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // Register button
        JButton registerBtn = new JButton("Student");
        registerBtn.setPreferredSize(buttonSize);
        registerBtn.setFont(buttonFont);
        registerBtn.setBackground(new Color(70, 130, 180)); // Steel blue

        registerBtn.setForeground(Color.BLUE);
        registerBtn.setFocusPainted(false);
        registerBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        
        // Admin button
        JButton adminBtn = new JButton("Admin");
        adminBtn.setPreferredSize(buttonSize);
        adminBtn.setFont(buttonFont);
        adminBtn.setBackground(new Color(70, 130, 180));
        adminBtn.setForeground(Color.BLUE);
        adminBtn.setFocusPainted(false);
        adminBtn.setBorder(BorderFactory.createRaisedBevelBorder());


        registerBtn.addActionListener(e -> showPanel("REGISTRATION"));
        adminBtn.addActionListener(e -> showPanel("LOGIN"));

        buttonPanel.add(registerBtn);
        buttonPanel.add(adminBtn);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private static JPanel createRegistrationPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField fullNameField = new JTextField(20);
        JTextField idField = new JTextField(20);
        
        // Year ComboBox
        String[] years = {"1", "2", "3", "4"};
        JComboBox<String> yearCombo = new JComboBox<>(years);
        
        // Department ComboBox
        String[] departments = {
            "Computer Science",
            "Accounting",
            "Marketing",
            "Marketing Management"
        };
        JComboBox<String> deptCombo = new JComboBox<>(departments);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        panel.add(fullNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("ID (Format: 3050/24):"), gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Year:"), gbc);
        gbc.gridx = 1;
        panel.add(yearCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        panel.add(deptCombo, gbc);

        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("Back");

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(registerBtn, gbc);

        gbc.gridy = 5;
        panel.add(backBtn, gbc);

        registerBtn.addActionListener(e -> {
            String fullName = fullNameField.getText();
            String id = idField.getText();
            String year = (String) yearCombo.getSelectedItem();
            String department = (String) deptCombo.getSelectedItem();

            if (fullName.isEmpty() || id.isEmpty()) {
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

            students.add(new Student(fullName, id, year, department));
            
            // Create detailed success message
            StringBuilder message = new StringBuilder();
            message.append("Registration Successful!\n\n");
            message.append("Student Information:\n");
            message.append("Full Name: ").append(fullName).append("\n");
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
        // Validate format: 4 digits followed by / and 2 digits
        return id.matches("\\d{4}/\\d{2}");
    }

    private static JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        JButton loginBtn = new JButton("Login");
        JButton backBtn = new JButton("Back");

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(loginBtn, gbc);

        gbc.gridy = 3;
        panel.add(backBtn, gbc);

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("hope") && password.equals("hope")) {
                showPanel("ADMIN");
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Invalid credentials!");
            }
        });

        backBtn.addActionListener(e -> showPanel("ENTRY"));

        return panel;
    }

    private static JPanel createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create table model
        String[] columnNames = {"Full Name", "ID", "Year", "Department"};
        Object[][] data = new Object[students.size()][4];
        
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = student.getFullName();
            data[i][1] = student.getId();
            data[i][2] = student.getYear();
            data[i][3] = student.getDepartment();
        }

        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);
        
        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(150); // Full Name
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // ID
        table.getColumnModel().getColumn(2).setPreferredWidth(50);  // Year
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Department

        // Enable sorting
        table.setAutoCreateRowSorter(true);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        JButton refreshBtn = new JButton("Refresh");
        JButton backBtn = new JButton("Back");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshBtn);
        buttonPanel.add(backBtn);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        refreshBtn.addActionListener(e -> {
            // Update table data
            Object[][] newData = new Object[students.size()][4];
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                newData[i][0] = student.getFullName();
                newData[i][1] = student.getId();
                newData[i][2] = student.getYear();
                newData[i][3] = student.getDepartment();
            }
            
            // Create new table model and update table
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(newData, columnNames);
            table.setModel(model);
            
            // Restore column widths
            table.getColumnModel().getColumn(0).setPreferredWidth(150);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(50);
            table.getColumnModel().getColumn(3).setPreferredWidth(150);
        });

        backBtn.addActionListener(e -> showPanel("ENTRY"));

        return panel;
    }

    private static void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    static class Student {
        private String fullName;
        private String id;
        private String year;
        private String department;

        public Student(String fullName, String id, String year, String department) {
            this.fullName = fullName;
            this.id = id;
            this.year = year;
            this.department = department;
        }

        public String getFullName() { return fullName; }
        public String getId() { return id; }
        public String getYear() { return year; }
        public String getDepartment() { return department; }
    }
} 