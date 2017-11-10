import java.util.ArrayList;
import java.util.Scanner;

public class POSApp {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		ArrayList<Item> inventory = TextFile.readFromFile();
		ArrayList<Item> cart = new ArrayList<Item>();
		
		System.out.println("Welcome to our Pretended Grand Circus Grocery Market");
		
		String choice = "y";
		while (choice.equalsIgnoreCase("y")) {
			cart.clear();
			inventory = Shop.shopping(scnr, inventory, cart);
			choice = Validator.getChoice(scnr, "Would you like to make another order? (y/n): ", "yn");

		}
		System.out.println("Thank you for shopping with us! Have a great day!");
		scnr.close();
	}

}
