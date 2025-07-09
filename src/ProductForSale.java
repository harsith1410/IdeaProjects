public abstract class ProductForSale {

    private String type;
    private String description;
    private double price;

    public ProductForSale(String type, String description, double price) {
        this.type = type;
        this.description = description;
        this.price = price;
    }

    double getSalePrice (int qty){
        return qty*price;
    }

    void printPricedItems (int qty){

        System.out.printf("%-15s Price: $%8.2f  %-35s %n", type,price*qty,description);

    }

    abstract void showDetails();

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
