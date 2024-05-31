import javax.swing.*;
import java.awt.*;

public class AddProductPanel extends JPanel {
    private JTextField nameField, typeField, categoryField, stockField, priceField, warrantyField, descriptionField, imagePathField;
    private ProductManager productManager;
    private MainFrame mainFrame;

    public AddProductPanel(MainFrame mainFrame, ProductManager productManager) {
        this.mainFrame = mainFrame;
        this.productManager = productManager;
        setLayout(new GridLayout(9, 2));

        JLabel nameLabel = new JLabel("Name:");
        JLabel typeLabel = new JLabel("Type:");
        JLabel categoryLabel = new JLabel("Category:");
        JLabel stockLabel = new JLabel("Stock:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel warrantyLabel = new JLabel("Warranty:");
        JLabel descriptionLabel = new JLabel("Description:");
        JLabel imagePathLabel = new JLabel("Image Path:");

        nameField = new JTextField();
        typeField = new JTextField();
        categoryField = new JTextField();
        stockField = new JTextField();
        priceField = new JTextField();
        warrantyField = new JTextField();
        descriptionField = new JTextField();
        imagePathField = new JTextField();

        JButton addButton = new JButton("Add Product");
        JButton backButton = new JButton("Back");

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String type = typeField.getText();
            String category = categoryField.getText();
            int stock = Integer.parseInt(stockField.getText());
            double price = Double.parseDouble(priceField.getText());
            String warranty = warrantyField.getText();
            String description = descriptionField.getText();
            String imagePath = imagePathField.getText();

            Product product = new Product(name, type, category, stock, price, warranty, description, imagePath);
            productManager.addProduct(product);
            JOptionPane.showMessageDialog(this, "Product added successfully!");
            mainFrame.showBuyerPanel(); // Refresh buyer panel to reflect the new product
        });

        backButton.addActionListener(e -> mainFrame.showSellerPanel());

        add(nameLabel);
        add(nameField);
        add(typeLabel);
        add(typeField);
        add(categoryLabel);
        add(categoryField);
        add(stockLabel);
        add(stockField);
        add(priceLabel);
        add(priceField);
        add(warrantyLabel);
        add(warrantyField);
        add(descriptionLabel);
        add(descriptionField);
        add(imagePathLabel);
        add(imagePathField);
        add(addButton);
        add(backButton);
    }
}
