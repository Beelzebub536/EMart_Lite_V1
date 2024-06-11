import java.io.*;
import java.util.*;

public class ProductManager {
    private List<Product> products;
    private int nextProductId;

    public ProductManager() {
        products = new ArrayList<>();
        loadProducts();
    }

    private void loadProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            int maxProductId = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String type = parts[2];
                    String category = parts[3];
                    int stock = Integer.parseInt(parts[4]);
                    double price = Double.parseDouble(parts[5]);
                    String warranty = parts[6];
                    String description = parts[7];
                    String imagePath = parts.length > 8 ? parts[8] : null;
                    products.add(new Product(id, name, type, category, stock, price, warranty, description, imagePath));
                    if (id > maxProductId) {
                        maxProductId = id;
                    }
                }
            }
            nextProductId = maxProductId + 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addProduct(Product product) {
        try {
            products.add(product);
            saveProducts();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
            if (product.getName().equals(name) && product.getStock() > 0) {
                double newPrice = product.getPrice() * (1 - discount / 100);
                product.setPrice(newPrice);
                saveProducts();
                return true;
            }
        }
        return false;
    }

    public boolean addDiscount(String name, double discount) {
        for (Product product : products) {
            if (product.getName().equals(name) && product.getStock() > 0) {
                double newPrice = product.getPrice() * (1 - discount / 100);
                product.setPrice(newPrice);
                saveProducts();
                return true;
            }
        }
        return false;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void decreaseStock(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setStock(product.getStock() - 1);
                if (product.getStock() <= 0) {
                    products.remove(product);
                }
                saveProducts();
                return;
            }
        }
    }

    public List<Product> getProductsSortedByPrice() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
            String line;
            List<Product> productList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    String name = parts[1];
                    String type = parts[2];
                    String category = parts[3];
                    int stock = Integer.parseInt(parts[4]);
                    double price = Double.parseDouble(parts[5]);
                    String warranty = parts[6];
                    String description = parts[7];
                    String imagePath = parts.length > 8 ? parts[8] : null;
                    productList.add(new Product(Integer.parseInt(parts[0]), name, type, category, stock, price, warranty, description, imagePath));
                }
            }
            reader.close();

            productList.sort(Comparator.comparingDouble(Product::getPrice));
            return productList;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Product> getProductsSortedByName() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
            String line;
            List<Product> productList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    String name = parts[1];
                    String type = parts[2];
                    String category = parts[3];
                    int stock = Integer.parseInt(parts[4]);
                    double price = Double.parseDouble(parts[5]);
                    String warranty = parts[6];
                    String description = parts[7];
                    String imagePath = parts.length > 8 ? parts[8] : null;
                    productList.add(new Product(Integer.parseInt(parts[0]), name, type, category, stock, price, warranty, description, imagePath));
                }
            }
            reader.close();

            productList.sort(Comparator.comparing(Product::getName));
            return productList;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveProducts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"))) {
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNextProductId() {
        return nextProductId;
    }

    public void incrementNextProductId() {
        nextProductId++;
    }
}
