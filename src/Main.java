import java.util.ArrayList;
import java.util.List;

public class Main {

    record OrderItem(int qty, ProductForSale product) {}

    public static class Store{

        List<ProductForSale> products = new ArrayList<>();


        public void add_product(ProductForSale product){
            products.add(product);
            System.out.println(product.getType() + " added successfully");
        }

        public void list_products(){
            for(ProductForSale product : products){
                System.out.println("__".repeat(30));
                product.showDetails();
            }
        }

        public void addItemToOrder(ArrayList<OrderItem> order, int orderIndex,int qty) {
            order.add(new OrderItem(qty, products.get(orderIndex-1)));
        }


        public void printOrder(ArrayList<OrderItem> order) {

            double salesTotal = 0;
            System.out.println("Sales Receipt");
            System.out.println("____".repeat(30));
            for (OrderItem item : order) {
                item.product().printPricedItems(item.qty());
                salesTotal += item.product().getSalePrice(item.qty());
                System.out.println("____".repeat(15));
            }
            System.out.println("____".repeat(30));
            System.out.printf("Sales Total = $%6.2f %n", salesTotal);
        }

    }

    public static void main(String[] args) {


        Store store = new Store();
        store.add_product(new Wooden("Table","A table is a flat-topped piece of furniture supported by legs, used for working, eating, or holding objects.",1299.99));
        store.add_product(new Wooden("Chair", "A chair is a piece of furniture with a raised surface supported by legs, used to sit one person.", 699.99));
        store.add_product(new Wooden("Bookshelf", "A bookshelf is a piece of furniture with horizontal shelves, used to store books or other items.", 1499.50));
        store.add_product(new Wooden("Bed", "A bed is a piece of furniture used for sleeping or resting, typically consisting of a frame and a mattress.", 4999.00));
        store.add_product(new Wooden("Wardrobe", "A wardrobe is a tall, standing cupboard used for storing clothes, with hanging space and shelves.", 3599.75));
        store.add_product(new Wooden("Coffee Table", "A coffee table is a low table placed in front of a sofa, used for holding drinks, magazines, or decor items.", 1199.00));

        //store.list_products();

        ArrayList<OrderItem> order1 = new ArrayList<>();
        store.addItemToOrder(order1,2,3);
        store.addItemToOrder(order1,3,4);
        store.printOrder(order1);


    }
}