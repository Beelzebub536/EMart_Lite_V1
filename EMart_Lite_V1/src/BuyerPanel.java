import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class BuyerPanel extends JPanel {
    private ProductManager productManager;
    private DefaultListModel<Product> cartModel;
    private JPanel productPanel;
    private JScrollPane scrollPane;
    private int currentPage = 1;
    private int pageSize = 10;
    private int userId;

    public BuyerPanel(MainFrame mainFrame, ProductManager productManager, DefaultListModel<Product> cartModel, int userId) {
        this.productManager = productManager;
        this.cartModel = cartModel;
        this.userId = userId;

        setLayout(new BorderLayout());

        // Top panel with sorting buttons
        JPanel topPanel = new JPanel();
        JButton sortByNameButton = new JButton("Sort by Name");
        JButton sortByPriceButton = new JButton("Sort by Price");
        JButton backButton = new JButton("Back");
        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        sortByNameButton.addActionListener(e -> showProducts(productManager.getProductsSortedByName(), currentPage));
        sortByPriceButton.addActionListener(e -> showProducts(productManager.getProductsSortedByPrice(), currentPage));
        backButton.addActionListener(e -> mainFrame.showUserPanel());
        previousButton.addActionListener(e -> showPreviousPage());
        nextButton.addActionListener(e -> showNextPage());

        topPanel.add(sortByNameButton);
        topPanel.add(sortByPriceButton);
        topPanel.add(backButton);
        topPanel.add(previousButton);
        topPanel.add(nextButton);

        add(topPanel, BorderLayout.NORTH);

        // Panel to display products
        productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(0, 4, 10, 10));

        // Scroll pane for the product panel
        scrollPane = new JScrollPane(productPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Show all products initially
        showProducts(productManager.getProducts(), currentPage);
    }

    public void showProducts(java.util.List<Product> products, int page) {
        currentPage = page;
        productPanel.removeAll();

        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, products.size());

        for (int i = startIndex; i < endIndex; i++) {
            Product product = products.get(i);
            productPanel.add(new JLabel(product.getName()));
            productPanel.add(new JLabel("$" + product.getPrice()));

            JButton viewButton = new JButton("View Product Info");
            viewButton.addActionListener(e -> new ProductInfoWindow(product));
            productPanel.add(viewButton);

            JButton addToCartButton = new JButton("Add to Cart");
            addToCartButton.addActionListener(e -> addToCart(product.getId()));
            productPanel.add(addToCartButton);
        }

        // Refresh the layout
        productPanel.revalidate();
        productPanel.repaint();
    }

    private void showPreviousPage() {
        if (currentPage > 1) {
            showProducts(productManager.getProducts(), currentPage - 1);
        }
    }

    private void showNextPage() {
        int totalPages = (int) Math.ceil((double) productManager.getProducts().size() / pageSize);
        if (currentPage < totalPages) {
            showProducts(productManager.getProducts(), currentPage + 1);
        }
    }

    private void addToCart(int productId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("cart.txt"))) {
            String line;
            boolean productExists = false;
            List<String> fileLines = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == userId) {
                    for (String part : parts) {
                        if (Integer.parseInt(part) == productId) {
                            productExists = true;
                            break;
                        }
                    }
                    if (!productExists) {
                        line += "," + productId;
                    }
                }
                fileLines.add(line);
            }

//            if (!productExists) {
//                fileLines.add(userId + "," + productId);
//            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("cart.txt"))) {
                for (String fileLine : fileLines) {
                    writer.write(fileLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}