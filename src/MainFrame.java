import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private UserManager buyerManager;
    private UserManager sellerManager;
    private ProductManager productManager;

    public MainFrame() {
        buyerManager = new UserManager("buyers.txt");
        sellerManager = new UserManager("sellers.txt");
        productManager = new ProductManager();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        MainPanel mp = new MainPanel(this);
        BuyerSignInPanel bsp = new BuyerSignInPanel(this, buyerManager);
        SellerSignInPanel ssp = new SellerSignInPanel(this, sellerManager);
        BuyerPanel bp = new BuyerPanel(this, productManager);
        SellerPanel sp = new SellerPanel(this, productManager);

        mainPanel.add(mp, "main");
        mainPanel.add(bsp, "buyerSignIn");
        mainPanel.add(ssp, "sellerSignIn");
        mainPanel.add(bp, "buyer");
        mainPanel.add(sp, "seller");

        add(mainPanel);

        setTitle("User Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        showMainPanel();
    }

    public void showMainPanel() {
        cardLayout.show(mainPanel, "main");
    }

    public void showBuyerSignInPanel() {
        cardLayout.show(mainPanel, "buyerSignIn");
    }

    public void showSellerSignInPanel() {
        cardLayout.show(mainPanel, "sellerSignIn");
    }

    public void showBuyerPanel() {
        cardLayout.show(mainPanel, "buyer");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
