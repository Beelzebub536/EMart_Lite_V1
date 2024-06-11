import javax.swing.*;
import java.awt.*;

public class ProductInfoWindow extends JFrame {
    public ProductInfoWindow(Product product) {
        setTitle("Product Information");
        setSize(400, 300);
        setLayout(new GridLayout(12, 2));

        add(new JLabel("Name:"));
        add(new JLabel(product.getName()));
        add(new JLabel("Type:"));
        add(new JLabel(product.getType()));
        add(new JLabel("Category:"));
        add(new JLabel(product.getCategory()));
        add(new JLabel("Stock:"));
        add(new JLabel(String.valueOf(product.getStock())));
        add(new JLabel("Price:"));
        add(new JLabel("$" + product.getPrice()));
        add(new JLabel("Warranty:"));
        add(new JLabel(product.getWarranty()));
        add(new JLabel("Description:"));
        add(new JLabel(product.getDescription()));
        if (product.getImagePath() != null && !product.getImagePath().isEmpty()) {
            add(new JLabel("Image:"));
            add(new JLabel(product.getImagePath()));
        }

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
