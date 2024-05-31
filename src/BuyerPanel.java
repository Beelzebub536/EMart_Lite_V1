import javax.swing.*;
import java.awt.*;

public class BuyerPanel extends JPanel {
    private ProductManager productManager;
    private JTextArea productArea;

    public BuyerPanel(MainFrame mainFrame, ProductManager productManager) {
        this.productManager = productManager;
        setLayout(new BorderLayout());

        // Display products
        productArea = new JTextArea();
        productArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(productArea);

        JPanel topPanel = new JPanel();
        JButton sortByNameButton = new JButton("Sort by Name");
        JButton sortByPriceButton = new JButton("Sort by Price");
        JButton backButton = new JButton("Back");

        sortByNameButton.addActionListener(e -> showProducts(productManager.getProductsSortedByName()));
        sortByPriceButton.addActionListener(e -> showProducts(productManager.getProductsSortedByPrice()));
        backButton.addActionListener(e -> mainFrame.goBackToMainPanel());

        topPanel.add(sortByNameButton);
        topPanel.add(sortByPriceButton);
        topPanel.add(backButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Initially show all products
        showProducts(productManager.getProducts());
    }

    public void showProducts(java.util.List<Product> products) {
        productArea.setText("");
        for (Product product : products) {
            productArea.append(product.displayString() + "\n\n");
        }
    }
}
