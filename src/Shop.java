import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
	
	ArrayList<Item> inventory;
	
	public Shop() {
		
	}
	
	public Shop(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	public static void viewInventory(Scanner sc, ArrayList<Item> inventory, ArrayList<Item> cart) {
		System.out.println("ID  Item      Desc         Qty    Price");
		System.out.println("==  ====      ====         ===    =====");
		for(int i = 0; i < inventory.size(); i++) {
			System.out.println((i+1) + "  " + inventory.get(i));
		}
		System.out.println((inventory.size()+2) + " Quit" );
		
		int selection = getItem(sc, inventory);
		
		if (selection == inventory.size()+1) {
			
		}
		if (selection == inventory.size()+2) {
			
		}
		
		int userQty = getQty(selection , sc, inventory);
		
		String addToCart = Validator.getChoice(sc, "Do you want to add to cart? (y/n): ", "yn");
		if (addToCart.equalsIgnoreCase("n")) {
			viewInventory( sc, inventory, cart);
		}
		
		else {
			Item tempItem = inventory.get(selection);
			tempItem.qty = userQty;
			cart.add(tempItem);
			
			String goToCheckout = Validator.getChoice(sc, "Do you want to select another item? (y/n): ", "yn");
			if (goToCheckout.equalsIgnoreCase("y")) {
				viewInventory( sc, inventory, cart);
			}
			else {
			//	Checkout.checkout(sc, inventory, cart);
			}
			
		}
		
		
		
	}
	
	public static int getItem(Scanner sc, ArrayList<Item> inventory) {
		
		int userInput = Validator.getInt(sc, "Enter which item you'd like to buy: ", 1, inventory.size() + 1);
		
		return userInput;
	}
	
	public static int getQty(int selection, Scanner sc, ArrayList<Item> inventory) {
		
		int userInput = Validator.getInt(sc, "Enter which item you'd like to buy: ", 1, inventory.get(selection -1).getQty());
		return userInput;
	}
	
	
	
}
