import javax.swing.*;
import java.awt.*;

public class RemoveProductPanel extends JPanel {
    private JTextField nameField;
    private ProductManager productManager;
    private MainFrame mainFrame;

    public RemoveProductPanel(MainFrame mainFrame, ProductManager productManager) {
        this.mainFrame = mainFrame;
        this.productManager = productManager;
        setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Product Name:");

        nameField = new JTextField();

        JButton removeButton = new JButton("Remove Product");
        JButton backButton = new JButton("Back");

        removeButton.addActionListener(e -> {
            String name = nameField.getText();
            boolean removed = productManager.removeProductByName(name);
            if (removed) {
                JOptionPane.showMessageDialog(this, "Product removed successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Product not found.");
            }
        });

        backButton.addActionListener(e -> mainFrame.showSellerPanel());

        add(nameLabel);
        add(nameField);
        add(removeButton);
        add(new JLabel()); // Empty placeholder
        add(backButton);
    }
}
