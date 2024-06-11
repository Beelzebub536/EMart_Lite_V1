import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class CartPanel extends JPanel {
    private int userId;
    private ProductManager productManager;
    private MainFrame mainFrame;
    private java.util.List<Product> cartProducts;
    private JButton backButton;

    public CartPanel(MainFrame mainFrame, ProductManager productManager, int userId) {
        this.userId = userId;
        this.productManager = productManager;
        this.mainFrame = mainFrame;
        this.cartProducts = new ArrayList<>();

        setLayout(new BorderLayout());

        loadCartProducts();
        displayCart();

        backButton = new JButton("Back");
        backButton.addActionListener(e -> mainFrame.showUserPanel());
        add(backButton, BorderLayout.SOUTH);
    }

    private void loadCartProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("cart.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == userId) {
                    for (int i = 1; i < parts.length; i++) {
                        try {
                            int productId = Integer.parseInt(parts[i]);
                            Product product = productManager.getProductById(productId);
                            if (product != null) {
                                cartProducts.add(product);
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid product ID: " + parts[i]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayCart() {
        removeAll();

        if (cartProducts.isEmpty()) {
            add(new JLabel("Your cart is empty."), BorderLayout.CENTER);
            backButton = new JButton("Back");
            backButton.addActionListener(e -> mainFrame.showUserPanel());
            add(backButton, BorderLayout.SOUTH);

        } else {
            JPanel cartPanel = new JPanel();
            cartPanel.setLayout(new GridLayout(cartProducts.size() + 2, 1));

            double totalPrice = 0.0;

            for (Product product : cartProducts) {
                JPanel productPanel = new JPanel();
                productPanel.setLayout(new GridLayout(1, 4));
                productPanel.add(new JLabel(product.getName()));
                productPanel.add(new JLabel("$" + product.getPrice()));
                JTextField quantityField = new JTextField("1");
                productPanel.add(quantityField);

                JButton removeButton = new JButton("Remove");
                removeButton.addActionListener(e -> {
                    cartProducts.remove(product);
                    saveCart();
                    displayCart();
                });
                productPanel.add(removeButton);

                cartPanel.add(productPanel);
                totalPrice += product.getPrice();
            }

            cartPanel.add(new JSeparator());

            JPanel totalPanel = new JPanel();
            totalPanel.setLayout(new GridLayout(1, 2));
            totalPanel.add(new JLabel("Total: $" + totalPrice));
            JButton checkoutButton = new JButton("Checkout");
            checkoutButton.addActionListener(e -> checkout());
            totalPanel.add(checkoutButton);

            cartPanel.add(totalPanel);
            add(cartPanel, BorderLayout.CENTER);
        }

        revalidate();
        repaint();
    }

    private void saveCart() {
        Map<Integer, List<Integer>> cartMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("cart.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    int id = Integer.parseInt(parts[0]);
                    List<Integer> productIds = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        productIds.add(Integer.parseInt(parts[i]));
                    }
                    cartMap.put(id, productIds);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        cartMap.put(userId, new ArrayList<>());
        for (Product product : cartProducts) {
            cartMap.get(userId).add(product.getId());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cart.txt"))) {
            for (Map.Entry<Integer, List<Integer>> entry : cartMap.entrySet()) {
                writer.write(entry.getKey().toString());
                for (int productId : entry.getValue()) {
                    writer.write("," + productId);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkout() {
        boolean canCheckout = true;
        for (Product product : cartProducts) {
            if (product.getStock() <= 0) {
                JOptionPane.showMessageDialog(this, "Product " + product.getName() + " is out of stock.");
                canCheckout = false;
                break;
            } else if (product.getStock() < 1) {
                JOptionPane.showMessageDialog(this, "Insufficient stock for product " + product.getName() + ".");
                canCheckout = false;
                break;
            }
        }

        if (canCheckout) {
            for (Product product : cartProducts) {
                productManager.decreaseStock(product.getId());
            }
            cartProducts.clear();
            saveCart();
            productManager.saveProducts();
            JOptionPane.showMessageDialog(this, "Checkout successful!");
            displayCart();
        }
    }
}