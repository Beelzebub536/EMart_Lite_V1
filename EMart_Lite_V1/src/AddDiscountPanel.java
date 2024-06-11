import javax.swing.*;
import java.awt.*;

public class AddDiscountPanel extends JPanel {
    private JTextField nameField, discountField;
    private ProductManager productManager;
    private MainFrame mainFrame;

    public AddDiscountPanel(MainFrame mainFrame, ProductManager productManager) {
        this.mainFrame = mainFrame;
        this.productManager = productManager;
        setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Product Name:");
        JLabel discountLabel = new JLabel("Discount Percentage:");

        nameField = new JTextField();
        discountField = new JTextField();

        JButton addDiscountButton = new JButton("Add Discount");
        JButton backButton = new JButton("Back");

        addDiscountButton.addActionListener(e -> {
            String name = nameField.getText();
            try {
                double discount = Double.parseDouble(discountField.getText());
                if (discount > 100) {
                    JOptionPane.showMessageDialog(this, "Discount cannot be more than 100%");
                    return;
                }
                boolean success = productManager.addDiscount(name, discount);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Discount added successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Product not found or out of stock.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid discount value.");
            }
        });

        backButton.addActionListener(e -> mainFrame.showSellerPanel());

        add(nameLabel);
        add(nameField);
        add(discountLabel);
        add(discountField);
        add(addDiscountButton);
        add(backButton);
    }
}
