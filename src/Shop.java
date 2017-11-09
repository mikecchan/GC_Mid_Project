import java.util.ArrayList;
import java.util.Scanner;

public class Shop {

	ArrayList<Item> inventory;

	public Shop() {

	}

	public Shop(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	public static ArrayList<Item> viewInventory(Scanner sc, ArrayList<Item> inventory, ArrayList<Item> cart) {

		int inventorySize = inventory.size();

		System.out.println("ID  Item      Desc         Qty    Price");
		System.out.println("==  ====      ====         ===    =====");
		for (int i = 0; i < inventorySize; i++) {
			System.out.println(inventory.get(i));
		}
		System.out.println((inventorySize + 1) + ". View Cart");
		System.out.println((inventorySize + 2) + ". Quit");

		int selection = getItem(sc, inventory);
		System.out.println(inventory.get(selection - 1));

		if (selection == inventorySize + 1) {
			inventory = Checkout.checkout(sc, inventory, cart);
		} 

		else if (selection <= inventorySize) {
			int userQty = getQty(selection, sc, inventory);

			String addToCart = Validator.getChoice(sc, "Do you want to add to cart? (y/n): ", "yn");
			if (addToCart.equalsIgnoreCase("n")) {
				viewInventory(sc, inventory, cart);
			}

			else {
				Item tempItem = inventory.get(selection-1);
				tempItem.qty = userQty;
				cart.add(tempItem);

				String goToCheckout = Validator.getChoice(sc, "Do you want to select another item? (y/n): ", "yn");
				if (goToCheckout.equalsIgnoreCase("y")) {
					viewInventory(sc, inventory, cart);
				} else {
					inventory = Checkout.checkout(sc, inventory, cart);
				}

			}
		}
		else if (selection == inventorySize + 2) {
			System.out.println("Thank you for shopping with us! Have a great day!");
		}
		return inventory;

	}

	public static int getItem(Scanner sc, ArrayList<Item> inventory) {

		int userInput = Validator.getInt(sc, "What would you like: ", 1, inventory.size() + 2);

		return userInput;
	}

	public static int getQty(int selection, Scanner sc, ArrayList<Item> inventory) {

		int userInput = Validator.getInt(sc, "How much do you want: ", 1,
				inventory.get(selection - 1).getQty());
		return userInput;
	}

}
