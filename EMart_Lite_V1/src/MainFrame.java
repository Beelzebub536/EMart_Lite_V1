import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private UserManager buyerManager;
    private UserManager sellerManager;
    private ProductManager productManager;
    private DefaultListModel<Product> cartModel;
    private int userId;

    public MainFrame() {
        // Initialize managers
        buyerManager = new UserManager("buyers.txt");
        sellerManager = new UserManager("sellers.txt");
        productManager = new ProductManager();
        cartModel = new DefaultListModel<>(); // Initialize the cart model

        // Set up the CardLayout and main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create and add panels
        MainPanel mp = new MainPanel(this);
        BuyerSignInPanel bsp = new BuyerSignInPanel(this, buyerManager);
        SellerSignInPanel ssp = new SellerSignInPanel(this, sellerManager);
        UserPanel up = new UserPanel(this, productManager); // UserPanel to manage cart and product viewing
        SellerPanel sp = new SellerPanel(this, productManager);

        mainPanel.add(mp, "main");
        mainPanel.add(bsp, "buyerSignIn");
        mainPanel.add(ssp, "sellerSignIn");
        mainPanel.add(up, "user");
        mainPanel.add(sp, "seller");

        // Set up the JFrame
        add(mainPanel);
        setTitle("User Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Show the main panel initially
        showMainPanel();
    }

    // Methods to show different panels
    public void showMainPanel() {
        cardLayout.show(mainPanel, "main");
    }

    public void showBuyerSignInPanel() {
        cardLayout.show(mainPanel, "buyerSignIn");
    }

    public void showSellerSignInPanel() {
        cardLayout.show(mainPanel, "sellerSignIn");
    }

    public void showUserPanel() {
        cardLayout.show(mainPanel, "user");
    }

    public void showBuyerPanel() {
        BuyerPanel bp = new BuyerPanel(this, productManager, cartModel, userId); // Pass userId to BuyerPanel
        mainPanel.add(bp, "buyer");
        cardLayout.show(mainPanel, "buyer");
    }

    public void showCartPanel() {
        CartPanel cp = new CartPanel(this, productManager, userId);
        mainPanel.add(cp, "cart");
        cardLayout.show(mainPanel, "cart");
    }

    public void showSellerPanel() {
        cardLayout.show(mainPanel, "seller");
    }

    public void showAddProductPanel() {
        mainPanel.add(new AddProductPanel(this, productManager), "addProduct");
        cardLayout.show(mainPanel, "addProduct");
    }

    public void showRemoveProductPanel() {
        mainPanel.add(new RemoveProductPanel(this, productManager), "removeProduct");
        cardLayout.show(mainPanel, "removeProduct");
    }

    public void showPutOnSalePanel() {
        mainPanel.add(new PutOnSalePanel(this, productManager), "putOnSale");
        cardLayout.show(mainPanel, "putOnSale");
    }

    public void showAddDiscountPanel() {
        mainPanel.add(new AddDiscountPanel(this, productManager), "addDiscount");
        cardLayout.show(mainPanel, "addDiscount");
    }

    public void goBackToMainPanel() {
        showMainPanel();
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
