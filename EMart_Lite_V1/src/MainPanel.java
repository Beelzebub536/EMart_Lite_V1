import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final Image background = new ImageIcon("Welcome.jpg").getImage();
    public MainPanel(MainFrame mainFrame) {
        setLayout(new BorderLayout());

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (background != null) {
                    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        buttonPanel.setLayout(new GridBagLayout());

        // Create the buttons
        JButton buyerButton = new JButton("Become a Buyer");
        JButton sellerButton = new JButton("Become a Seller");

        // Add action listeners to the buttons
        buyerButton.addActionListener(e -> mainFrame.showBuyerSignInPanel());
        sellerButton.addActionListener(e -> mainFrame.showSellerSignInPanel());

        // Set constraints to center the buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Add some padding
        buttonPanel.add(buyerButton, gbc);

        gbc.gridy = 1;
        buttonPanel.add(sellerButton, gbc);

        // Add the button panel to the center of the main panel
        add(buttonPanel, BorderLayout.CENTER);
    }

}
