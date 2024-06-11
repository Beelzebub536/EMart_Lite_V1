import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
    private MainFrame mainFrame;
    private ProductManager productManager;

    public UserPanel(MainFrame mainFrame, ProductManager productManager) {
        this.mainFrame = mainFrame;
        this.productManager = productManager;
        setLayout(new GridLayout(3, 1));

        JButton cartButton = new JButton("Cart");
        JButton allProductsButton = new JButton("All Products");
        JButton logoutButton = new JButton("Logout");

        cartButton.addActionListener(e -> mainFrame.showCartPanel());
        allProductsButton.addActionListener(e -> mainFrame.showBuyerPanel());
        logoutButton.addActionListener(e -> mainFrame.showMainPanel());

        add(cartButton);
        add(allProductsButton);
        add(logoutButton);
    }
}
