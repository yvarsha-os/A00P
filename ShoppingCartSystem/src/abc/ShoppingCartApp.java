package abc;


	import java.util.ArrayList;
	import java.util.Comparator;
	import java.util.List;
	import java.util.Scanner;

	// Product class
	class Product {
	    private String name;
	    private double price;

	    public Product(String name, double price) {
	        this.name = name;
	        this.price = price;
	    }

	    public String getName() {
	        return name;
	    }

	    public double getPrice() {
	        return price;
	    }

	    @Override
	    public String toString() {
	        return String.format("%s - $%.2f", name, price);
	    }
	}

	// ShoppingCart class
	class ShoppingCart {
	    private List<Product> products = new ArrayList<>();

	    // Nested class to manage discounts
	    class Discount {
	        private double percentage;

	        public Discount(double percentage) {
	            this.percentage = percentage;
	        }

	        public double applyDiscount(double total) {
	            return total * (1 - percentage / 100);
	        }
	    }

	    // Method to add a product to the cart
	    public void addProduct(Product product) {
	        products.add(product);
	    }

	    // Method to calculate total price
	    public double calculateTotal() {
	        return products.stream().mapToDouble(Product::getPrice).sum();
	    }

	    // Method to apply a discount using a nested class
	    public double calculateTotalWithDiscount(Discount discount) {
	        double total = calculateTotal();
	        return discount.applyDiscount(total);
	    }

	    // Method to display products in the cart
	    public void displayCart() {
	        if (products.isEmpty()) {
	            System.out.println("Your shopping cart is empty.");
	        } else {
	            System.out.println("Products in your cart:");
	            products.forEach(System.out::println);
	        }
	    }

	    // Public method to sort products by price
	    public void sortProductsByPrice() {
	        products.sort(Comparator.comparingDouble(Product::getPrice));
	    }
	}

	// Main application class
	public class ShoppingCartApp {
	    public static void main(String[] args) {
	        ShoppingCart cart = new ShoppingCart();
	        Scanner scanner = new Scanner(System.in);

	        // Adding products to the cart
	        cart.addProduct(new Product("Laptop", 1200.00));
	        cart.addProduct(new Product("Smartphone", 800.00));
	        cart.addProduct(new Product("Headphones", 150.00));

	        // Displaying the cart
	        cart.displayCart();

	        // Calculating total price without discount
	        double total = cart.calculateTotal();
	        System.out.printf("Total: $%.2f%n", total);

	        // Apply a discount using a nested class
	        System.out.print("Enter discount percentage: ");
	        double discountPercentage = scanner.nextDouble();
	        ShoppingCart.Discount discount = cart.new Discount(discountPercentage);

	        // Calculate and display total with discount
	        double totalWithDiscount = cart.calculateTotalWithDiscount(discount);
	        System.out.printf("Total after %.2f%% discount: $%.2f%n", discountPercentage, totalWithDiscount);

	        // Sort products by price
	        cart.sortProductsByPrice();
	        System.out.println("Products sorted by price:");
	        cart.displayCart();

	        scanner.close();
	    }
	}
