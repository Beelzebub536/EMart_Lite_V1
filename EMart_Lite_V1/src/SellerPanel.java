import javax.swing.*;
import java.awt.*;

public class SellerPanel extends JPanel {
    private MainFrame mainFrame;
    private ProductManager productManager;

    public SellerPanel(MainFrame mainFrame, ProductManager productManager) {
        this.mainFrame = mainFrame;
        this.productManager = productManager;
        setLayout(new GridLayout(5, 1));

        JButton addProductButton = new JButton("Add Product");
        JButton removeProductButton = new JButton("Remove Product");
        JButton putOnSaleButton = new JButton("Put on Sale");
        JButton addDiscountButton = new JButton("Add Discount");
        JButton backButton = new JButton("Back");

        addProductButton.addActionListener(e -> mainFrame.showAddProductPanel());
        removeProductButton.addActionListener(e -> mainFrame.showRemoveProductPanel());
        putOnSaleButton.addActionListener(e -> mainFrame.showPutOnSalePanel());
        addDiscountButton.addActionListener(e -> mainFrame.showAddDiscountPanel());

        backButton.addActionListener(e -> mainFrame.goBackToMainPanel());

        add(addProductButton);
        add(removeProductButton);
        add(putOnSaleButton);
        add(addDiscountButton);
        add(backButton);
    }
}
