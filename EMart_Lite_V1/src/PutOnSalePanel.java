import javax.swing.*;
import java.awt.*;

public class PutOnSalePanel extends JPanel {
    private JTextField nameField, discountField, durationField;
    private ProductManager productManager;
    private MainFrame mainFrame;

    public PutOnSalePanel(MainFrame mainFrame, ProductManager productManager) {
        this.mainFrame = mainFrame;
        this.productManager = productManager;
        setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Product Name:");
        JLabel discountLabel = new JLabel("Discount Percentage:");
        JLabel durationLabel = new JLabel("Duration (days):");

        nameField = new JTextField();
        discountField = new JTextField();
        durationField = new JTextField();

        JButton putOnSaleButton = new JButton("Put on Sale");
        JButton backButton = new JButton("Back");

        putOnSaleButton.addActionListener(e -> {
            String name = nameField.getText();
            try {
                double discount = Double.parseDouble(discountField.getText());
                int duration = Integer.parseInt(durationField.getText());
                if (discount > 100) {
                    JOptionPane.showMessageDialog(this, "Discount cannot be more than 100%");
                    return;
                }
                boolean success = productManager.putProductOnSale(name, discount, duration);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Product put on sale successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Product not found or out of stock.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid discount or duration value.");
            }
        });

        backButton.addActionListener(e -> mainFrame.showSellerPanel());

        add(nameLabel);
        add(nameField);
        add(discountLabel);
        add(discountField);
        add(durationLabel);
        add(durationField);
        add(putOnSaleButton);
        add(backButton);
    }
}
