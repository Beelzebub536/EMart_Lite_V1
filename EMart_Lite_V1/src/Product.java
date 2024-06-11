public class Product {
    private int id;
    private String name;
    private String type;
    private String category;
    private int stock;
    private double price;
    private String warranty;
    private String description;
    private String imagePath;

    public Product(int id, String name, String type, String category, int stock, double price, String warranty, String description, String imagePath) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.warranty = warranty;
        this.description = description;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return description;
    }

    public String getWarranty() {
        return warranty;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + type + "," + category + "," + stock + "," + price + "," + warranty + "," + description + (imagePath != null ? "," + imagePath : "");
    }

    public String displayString() {
        return "Name: " + name + "\nType: " + type + "\nCategory: " + category + "\nStock: " + stock + "\nPrice: $" + price + "\nWarranty: " + warranty + "\nDescription: " + description + (imagePath != null ? "\nImage: " + imagePath : "");
    }
}
