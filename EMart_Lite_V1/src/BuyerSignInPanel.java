import javax.swing.*;
import java.awt.*;

public class BuyerSignInPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public BuyerSignInPanel(MainFrame mainFrame, UserManager userManager) {
        setLayout(new BorderLayout());

        // Left panel for the image
        ImageIcon leftIcon = new ImageIcon("BuyerLogin.jpg");
        Image image = leftIcon.getImage();
        Image resizedImage = image.getScaledInstance(400, 575, Image.SCALE_SMOOTH);
        ImageIcon resizedLogo = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedLogo); // Specify the image path
        add(imageLabel, BorderLayout.WEST);

        // Right panel for the sign-in components
        JPanel rightPanel = new JPanel(new GridLayout(5, 2));

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
                mainFrame.setUserId(userManager.getUserId(username)); // Set userId
                mainFrame.showUserPanel();
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

        rightPanel.add(usernameLabel);
        rightPanel.add(usernameField);
        rightPanel.add(passwordLabel);
        rightPanel.add(passwordField);
        rightPanel.add(signInButton);
        rightPanel.add(signUpButton);
        rightPanel.add(new JLabel());  // Empty placeholder
        rightPanel.add(backButton);

        add(rightPanel, BorderLayout.CENTER);
    }
}
