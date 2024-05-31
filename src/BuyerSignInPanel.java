import javax.swing.*;
import java.awt.*;

public class BuyerSignInPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public BuyerSignInPanel(MainFrame mainFrame, UserManager userManager) {
        setLayout(new GridLayout(5, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton signInButton = new JButton("Sign In");
        JButton signUpButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");

        signInButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (userManager.isValidUser(username, password)) {
                mainFrame.showBuyerPanel();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        });

        signUpButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (userManager.addUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Sign up successful. Please sign in.");
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists.");
            }
        });

        backButton.addActionListener(e -> mainFrame.goBackToMainPanel());

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(signInButton);
        add(signUpButton);
        add(new JLabel());  // Empty placeholder
        add(backButton);
    }
}
