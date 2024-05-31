public class Product {
    private String name;
    private String type;
    private String category;
    private int stock;
    private double price;
    private String warranty;
    private String description;
    private String imagePath;

    public Product(String name, String type, String category, int stock, double price, String warranty, String description, String imagePath) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.warranty = warranty;
        this.description = description;
        this.imagePath = imagePath;
    }

    // Getters and setters omitted for brevity


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + "," + type + "," + category + "," + stock + "," + price + "," + warranty + "," + description + (imagePath != null ? "," + imagePath : "");
    }

    public String displayString() {
        return "Name: " + name + "\nType: " + type + "\nCategory: " + category + "\nStock: " + stock + "\nPrice: $" + price + "\nWarranty: " + warranty + "\nDescription: " + description + (imagePath != null ? "\nImage: " + imagePath : "");
    }
}
