import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductManager {
    private List<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
        loadProducts();
    }

    private void loadProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String name = parts[0];
                    String type = parts[1];
                    String category = parts[2];
                    int stock = Integer.parseInt(parts[3]);
                    double price = Double.parseDouble(parts[4]);
                    String warranty = parts[5];
                    String description = parts[6];
                    String imagePath = parts.length > 7 ? parts[7] : null;
                    products.add(new Product(name, type, category, stock, price, warranty, description, imagePath));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        products.add(product);
        saveProducts();
    }

    public boolean removeProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                products.remove(product);
                saveProducts();
                return true;
            }
        }
        return false;
    }

    public boolean putProductOnSale(String name, double discount, int duration) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                // Logic to put product on sale
                saveProducts();
                return true;
            }
        }
        return false;
    }

    public boolean addDiscount(String name, double discount) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                // Logic to add discount to product
                saveProducts();
                return true;
            }
        }
        return false;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public List<Product> getProductsSortedByName() {
        List<Product> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparing(Product::getName));
        return sortedProducts;
    }

    public List<Product> getProductsSortedByPrice() {
        List<Product> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparingDouble(Product::getPrice));
        return sortedProducts;
    }

    private void saveProducts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"))) {
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
