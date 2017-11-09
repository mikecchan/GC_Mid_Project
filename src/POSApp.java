import java.util.ArrayList;
import java.util.Scanner;

public class POSApp {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		ArrayList<Item> inventory = TextFile.readFromFile();
		ArrayList<Item> cart = new ArrayList<Item>();
		Shop.viewInventory(scnr, inventory, cart );

	}

}
