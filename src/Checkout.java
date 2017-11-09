import java.util.ArrayList;
import java.util.Scanner;

public class Checkout {

	public static ArrayList<Item> checkout(Scanner sc, ArrayList<Item> Inventory, ArrayList<Item> Cart) {
		
		double subtotal = getSubtotal(Cart);
		double salestax = (subtotal * 0.06);
		double grandtotal = subtotal + salestax;
		
		viewOrder(Cart, subtotal, salestax, grandtotal);
		
		String paymentType = paymentMethod(grandtotal, sc);
		
		System.out.println("Thank you, here is your receipt...");
		viewOrder(Cart, subtotal, salestax, grandtotal);
		System.out.println("Payment type: " + paymentType);
		
		Inventory = updateInventory(Inventory, Cart);
		
		return Inventory;
				
	}
	
	
	
	public static String paymentMethod(double grandtotal, Scanner sc) {
		int choice = 0;
		String paymentType = null;
		
		choice = Validator.getInt(sc, "Please select a payment by the following integers... \n 1) Cash \n 2) Credit Card \n 3) Check\n", 1, 3);
		
		switch (choice) {
			case 1: Cash(grandtotal, sc);
					paymentType = "Cash";
					break;
			case 2: CreditCard(grandtotal, sc);
					paymentType = "Credit Card";
					break;
			case 3: Check(grandtotal, sc);
					paymentType = "Check";
					break;
		}
		
		return paymentType;
	}
	
	public static void Cash(double grandtotal, Scanner sc) {
		double amount = Validator.getDouble(sc, "Please enter amount of cash to deposit");
		if (amount < grandtotal) {
			/*
			int nextoption = Validator.getInt(sc,"Insufficient, please enter '1' to try again, or '0' to change payment method", 0, 1);
			if (nextoption == 0) {
				paymentMethod(grandtotal, sc);
			}
			else {
				Cash(grandtotal, sc);
			}
			*/
			System.out.println("Insufficient, please try again");
			Cash(grandtotal, sc);
		}
		else {
			double change = amount - grandtotal;
			System.out.println("Thank you, your change is " + change);
		}
	}
	
	public static void CreditCard(double grandtotal, Scanner sc) {
		Validator.getLong(sc, "Please Enter 9 digits of your Credit Card Number", 0, 999999999);
		Validator.getInt(sc, "Please enter expiration month: ", 1, 12);
		Validator.getInt(sc, "Please enter expiration year: ", 2017, 3000);
		Validator.getInt(sc, "Please enter your 3 digit CVV: ", 0, 999);
		System.out.println("Thank you!");
	}
	
	public static void Check(double grandtotal, Scanner sc) {
		Validator.getLong(sc, "Please Enter 9 digits of your check", 0, 999999999);
		System.out.println("Thank you!");
	}
	
	public static void viewOrder(ArrayList<Item> Cart, double subtotal, double salestax, double grandtotal) {
		System.out.println("ID  Item      Desc         Qty    Price    total");
		System.out.println("==  ====      ====         ===    =====    =====");
		for(int i=0; i < Cart.size(); i++ ) {
			System.out.println((i+1) + "  " + Cart.get(i) + "  " + (Cart.get(i).getPrice() * Cart.get(i).getQty()) +"");
		}
		System.out.println("");
		System.out.println("Subtotal: " + subtotal);
		System.out.println("Sales Tax: " + salestax);
		System.out.println("Grand total: " + grandtotal);
	}
	
	public static double getSubtotal(ArrayList<Item> Cart) {
		double subtotal = 0;
		for(int i=0; i < Cart.size(); i++ ) {
			subtotal = subtotal + Cart.get(i).getPrice() * Cart.get(i).getQty();
		}
		return subtotal;
	}
	
	public static ArrayList<Item> updateInventory(ArrayList<Item> Inventory, ArrayList<Item> Cart){
		
		for (int i = 0; i < Cart.size(); i++) {
			
			for (int j = 0; j < Inventory.size(); j++) {
				
				if (Cart.get(i).getId() == Inventory.get(j).getId()) {
					int QtyUpdate = Inventory.get(j).getQty() - Cart.get(i).getQty();
					Inventory.get(j).setQty(QtyUpdate);
				}
				
			}
		}
		
		return Inventory;
	}
}
