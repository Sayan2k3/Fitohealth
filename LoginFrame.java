import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetPasswordButton;
    private JButton createAccountButton;
    private JPanel signInPanel;
    private JPanel newUserPanel;

    public LoginFrame() {
        setTitle("FITOHEALTH Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set full black background
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridLayout(2, 1));

        signInPanel = new JPanel(new GridLayout(3, 2));
        signInPanel.setOpaque(false); // Set panel transparent

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        signInPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 20));
        signInPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        signInPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        signInPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (isValidCredentials(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login successful! Welcome, " + username);
                    // Redirect to the main page
                    redirectToMainPage();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password. Please try again.");
                }
            }
        });
        signInPanel.add(loginButton);

        resetPasswordButton = new JButton("Reset Password");
        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Please enter your username to reset the password.");
                } else {
                    // Add code here to handle password reset
                    JOptionPane.showMessageDialog(LoginFrame.this, "A password reset link has been sent to your email.");
                }
            }
        });
        signInPanel.add(resetPasswordButton);

        newUserPanel = new JPanel(new GridLayout(3, 2));
        newUserPanel.setOpaque(false); // Set panel transparent

        JLabel newUsernameLabel = new JLabel("New Username:");
        newUsernameLabel.setForeground(Color.WHITE);
        newUsernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        newUserPanel.add(newUsernameLabel);

        JTextField newUsernameField = new JTextField();
        newUsernameField.setFont(new Font("Arial", Font.PLAIN, 20));
        newUserPanel.add(newUsernameField);

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setForeground(Color.WHITE);
        newPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        newUserPanel.add(newPasswordLabel);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 20));
        newUserPanel.add(newPasswordField);

        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUsername = newUsernameField.getText();
                String newPassword = new String(newPasswordField.getPassword());
                if (newUsername.isEmpty() || newPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Please enter both username and password to create a new account.");
                } else {
                    // Add code here to create a new account
                    JOptionPane.showMessageDialog(LoginFrame.this, "New account created successfully!");
                    // Redirect to the main page after creating account
                    redirectToMainPage();
                }
            }
        });
        newUserPanel.add(createAccountButton);

        panel.add(signInPanel);
        panel.add(newUserPanel);

        add(panel);
        setVisible(true);
    }

    private boolean isValidCredentials(String username, String password) {
        // Dummy authentication logic for demonstration
        return "admin".equals(username) && "admin123".equals(password);
    }

    private void redirectToMainPage() {
        // Open the main page with four sections
        MainPageFrame mainPageFrame = new MainPageFrame();
        mainPageFrame.setVisible(true);
        dispose(); // Close the login frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame();
            }
        });
    }
}
