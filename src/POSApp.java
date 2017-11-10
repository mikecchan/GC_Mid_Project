import java.util.ArrayList;
import java.util.Scanner;

public class POSApp {
	
	/*
	 * @author: Michael Chan, Alexander Cyr, Matthew Davis
	 * Midterm Project: Grocery POS Terminal System
	 * 
	 * In this project, I am writing a cash register or self-service terminal for some kind of a 
	 * made up grocery store.
	 */

	/*
	 * This is my main method to iterate through the entire project...
	 * 
	 * 1) Creates an array of items (as strings) read from a text file.
	 * 2) Then our projects runs into a while loop to continuously run.
	 * 3) In the while loop, an empty shopping will be created to hold what users want to buy.
	 * 4) A duplicate inventory array has been created to hold the original inventory quantity as the user
	 * 	  puts items in and out of cart or purchases them.
	 * 5) Then will call the shopping() method from the Shop class to go through several steps and finally return
	 * 	  back what is left in the inventory after user completes purchases.
	 * 6) The program will prompt users if they would like to make another purchase.  If so, then program
	 * 	  will re run again with the latest inventory.
	 */
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		ArrayList<Item> inventory = TextFile.readFromFile();
		
		System.out.println("Welcome to our Pretended Grand Circus Grocery Market! \n");
		
		String choice = "y";
		
		while (choice.equalsIgnoreCase("y")) {
			ArrayList<Item> cart = new ArrayList<Item>();
			ArrayList<Item> OriginaInventory = (fillOriginalInventory(inventory));
			inventory = Shop.shopping(scnr, inventory, cart, OriginaInventory);
			choice = Validator.getChoice(scnr, "\nWould you like to make another order? (y/n): ", "yn");
		}
		System.out.println("Thank you for shopping with us! Have a great day!");
		scnr.close();
	}
	
	/*
	 * In this method, I am creating a duplicate array of inventory.  Because the inventory can frequently
	 * change as the user puts items in and out of cart, this duplicate array will hold the original quantity
	 * of each item in the inventory.
	 */
	public static ArrayList<Item> fillOriginalInventory(ArrayList<Item> inventory){
		ArrayList<Item> OriginalInventory = new ArrayList<Item>();
		for(int i = 0; i < inventory.size(); i++) {
			Item item = new Item(	inventory.get(i).getId(),
									inventory.get(i).getName(),
									inventory.get(i).getCat(),
									inventory.get(i).getDesc(), 
									inventory.get(i).getQty(),
									inventory.get(i).getPrice()
								);
			OriginalInventory.add(item);
		}
		return OriginalInventory;
	}

}
