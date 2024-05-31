import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel(MainFrame mainFrame) {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        JButton buyerButton = new JButton("Become a Buyer");
        JButton sellerButton = new JButton("Become a Seller");

        buyerButton.addActionListener(e -> mainFrame.showBuyerSignInPanel());
        sellerButton.addActionListener(e -> mainFrame.showSellerSignInPanel());

        buttonPanel.add(buyerButton);
        buttonPanel.add(sellerButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
}
