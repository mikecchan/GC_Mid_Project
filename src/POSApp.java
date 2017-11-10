import java.util.ArrayList;
import java.util.Scanner;

public class POSApp {

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
