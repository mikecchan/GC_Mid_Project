import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Checkout {

	/*
	 * In this class, this is the last part where the user is to checkout.
	 * Will prompt user for payment method and enter in those payment information.
	 * checkout() method will be the main method ran under this class.
	 */
	public static void checkout(Scanner sc, ArrayList<Item> Cart) {
		double subtotal = getSubtotal(Cart);
		double salestax = subtotal * 0.06;
		double grandtotal = subtotal + salestax;
		
		System.out.println("\nYOU ARE READY TO CHECKOUT...\n");
		System.out.println("                     Your Shopping Cart                    ");
		viewOrder(Cart, subtotal, salestax, grandtotal);
		
		String paymentType = paymentMethod(grandtotal, sc);
		
		System.out.println("\nThank you, here is your receipt...\n");
		viewOrder(Cart, subtotal, salestax, grandtotal);
		System.out.println("Payment type: " + paymentType);
				
	}
	
	
	/*
	 * In this method, the user will select a payment to checkout.
	 */
	public static String paymentMethod(double grandtotal, Scanner sc) {
		int choice = 0;
		String paymentType = null;
		
		choice = Validator.getInt(sc, "Please select a payment by the following integers... \n 1) Cash \n 2) Credit Card \n 3) Check\n ", 1, 3);
		
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
	
	/*
	 * Method if user selects cash.
	 */
	public static void Cash(double grandtotal, Scanner sc) {
		double amount = Validator.getDouble(sc, "Please enter amount of cash to deposit: ");
		if (amount < grandtotal) {
			System.out.println("Insufficient, please try again");
			Cash(grandtotal, sc);
		}
		else {
			DecimalFormat df = new DecimalFormat("0.00");
			double change = amount - grandtotal;
			System.out.println("Thank you, your change is $" + df.format(change));
		}
	}
	
	/*
	 * Method if user selects Credit Card.
	 */
	public static void CreditCard(double grandtotal, Scanner sc) {
		Validator.getLong(sc, "Please Enter 9 digits of your Credit Card Number", 0, 999999999);
		Validator.getInt(sc, "Please enter expiration month: ", 1, 12);
		Validator.getInt(sc, "Please enter expiration year: ", 2017, 3000);
		Validator.getInt(sc, "Please enter your 3 digit CVV: ", 0, 999);
		System.out.println("Thank you!");
	}
	
	/*
	 * Method if user selects Check.
	 */
	public static void Check(double grandtotal, Scanner sc) {
		Validator.getLong(sc, "Please Enter 9 digits of your check", 0, 999999999);
		System.out.println("Thank you!");
	}
	
	/*
	 * Method to view what is in cart.
	 */
	public static void viewOrder(ArrayList<Item> Cart, double subtotal, double salestax, double grandtotal) {
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("ID  Item      Category    Description Qty    Price    total");
		System.out.println("==  ====      ========    =========== ===    =====    =====");
		for(int i=0; i < Cart.size(); i++ ) {
			System.out.println((i+1) + "  " + Cart.get(i) + "  $" + df.format(Cart.get(i).getPrice() * Cart.get(i).getQty()) +"");
		}
		System.out.println("");
		System.out.println("Subtotal: $" + df.format(subtotal));
		System.out.println("Sales Tax: $" + df.format(salestax));
		System.out.println("Grand total: $" + df.format(grandtotal));
	}
	
	/*
	 * Method to return the subtotal of all items in cart.
	 */
	public static double getSubtotal(ArrayList<Item> Cart) {
		double subtotal = 0;
		for(int i=0; i < Cart.size(); i++ ) {
			subtotal = subtotal + Cart.get(i).getPrice() * Cart.get(i).getQty();
		}
		return subtotal;
	}
}
