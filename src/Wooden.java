public class Wooden extends ProductForSale {

    public Wooden(String type, String description, double price) {
        super(type, description, price);
    }

    @Override
    void showDetails() {
        System.out.printf("This %s is considered as a work of art\n",getType());
        System.out.printf(" Description: %s\n",getDescription());
        System.out.printf(" The Price of this product : $ %.2f\n",getPrice());
    }
}
