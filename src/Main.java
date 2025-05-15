import java.time.LocalDate;
import java.util.List;

public class Main {
    /*
    Esercizio #1
Ottenere una lista di prodotti che appartengono alla categoria «Books» ed hanno un prezzo > 100

Esercizio #2
Ottenere una lista di ordini con prodotti che appartengono alla categoria «Baby»

Esercizio #3
Ottenere una lista di prodotti che appartengono alla categoria «Boys» ed applicare 10% di sconto al loro prezzo

Esercizio #4
Ottenere una lista di prodotti ordinati da clienti di livello (tier) 2 tra l’01-Feb-2021 e l’01-Apr-2021

     */
    public static void main(String[] args) {
        Product b1 = new Product(4523L, "Ciao", "Books", 34.0);
        Product b2 = new Product(4345L, "Don Quijote", "Books", 109.0);
        Product b3 = new Product(4523L, "Ciao", "Books", 30.0);

        Product ba1 = new Product(2342L, "Ciao", "Baby", 30.0);
        Product ba2 = new Product(4453L, "Ciao", "Baby", 50.0);
        Product ba3 = new Product(4523L, "Ciao", "Baby", 35.0);

        Product bo1 = new Product(4578L, "Ciao", "Boys", 20.0);
        Product bo2 = new Product(4563L, "Ciao", "Boys", 35.0);
        Product bo3 = new Product(4567L, "Ciao", "Boys", 38.0);

        List<Product> products = List.of(b1,b2,b3,ba1,ba2,ba3,bo1,bo2,bo3);

        //es 1
        List<Product> libri = List.of(b1, b2, b3);
        List<Product> libriCostosi = products.stream().filter(libro -> libro.getPrice() > 100 && libro.getCategory().contains("Books")).toList(); //to list
        // ritorna una lista immutibile, non ci potrà più fare .add  .remove. .collect(Collectors.toList()) invece
        // ritorna una lista mutabile
        System.out.println(libriCostosi);




        //es2
        List<Product> babyProducts = products.stream().filter(product -> product.getName().contains("Baby")).toList();


        //es 3
        List<Product> boysProducts = products.stream().filter(product -> product.getName().contains("Boys")).peek(product -> product.setPrice((product.getPrice()*0.9))).toList();
        List<Product> boysProducts2 = products.stream().filter(product -> product.getName().contains("Boys")).map(product -> {product.setPrice(product.getPrice()*0.9); return product;}).toList();

        System.out.println(boysProducts2);


        Customer gino = new Customer(234L, "Gino", 2);
        Customer carlo = new Customer(235L, "Carlo", 1);
        Customer mario = new Customer(236L, "Mario", 3);
        Customer giusy = new Customer(237L, "Giusy", 2);

        Order n1 = new Order(2345L, "delivered", LocalDate.of(2025,5,5), LocalDate.of(2025,5,14), List.of(b1,ba1,bo2), gino);
        Order n2 = new Order(2395L, "pending", LocalDate.of(2025,5,10), LocalDate.of(2025,5,18), List.of(b2,ba2,bo1), giusy);
        Order n3 = new Order(2395L, "pending", LocalDate.of(2025,5,10), LocalDate.of(2025,4,18), List.of(b3,ba3,bo3), mario);

        List<Order> ordini = List.of(n1, n2, n3);

        List<Order> ordini2 = ordini.stream().filter(order -> order.getCustomer().getTier() == 2 && order.getOrderDate().isAfter(LocalDate.of(2025,4,1)) && order.getDeliveryDate().isBefore(LocalDate.of(2025, 5, 14))).toList();
        //alternativa es 4
        List<Order> prodottiOrdini3 = ordini.stream().filter(order -> order.getCustomer().getTier() == 2).
                filter(order -> order.getOrderDate().isAfter(LocalDate.of(2025,4,1))).
                filter(order -> order.getDeliveryDate().isBefore(LocalDate.of(2025, 5, 14))).
                flatMap(order -> order.getProducts().stream()).toList();

        System.out.println(prodottiOrdini3);

        //es 2corretto
        List<Order> es2 = ordini.stream().filter(order -> order.getProducts().stream().
                anyMatch(product-> product.getCategory().equals("Baby"))).toList();
        System.out.println(es2);

    }
}
