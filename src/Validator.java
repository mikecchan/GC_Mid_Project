
import java.util.Scanner;

public class Validator {

	public static String getChoice(Scanner sc, String prompt, String limit) {
		String s = "";

		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNext()) {
				s = sc.next(); // read user entry
				sc.nextLine(); // discard any other data entered on the line
				if (limit.indexOf(String.valueOf(s.charAt(0)).toLowerCase()) > -1) {
					isValid = true;
				} else {
					System.out.println("Error! Invalid entry. Try again.");
				}
			}
		}

		return String.valueOf(s.charAt(0));
	}

	public static String getString(Scanner sc, String prompt) {
		System.out.print(prompt);
		String s = sc.next(); // read user entry
		sc.nextLine(); // discard any other data entered on the line
		return s;
	}

	public static int getInt(Scanner sc, String prompt) {
		int i = 0;
		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				isValid = true;
			} else {
				System.out.println("Error! Invalid integer value. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return i;
	}

	public static int getInt(Scanner sc, String prompt, int min, int max) {
		int i = 0;
		boolean isValid = false;
		while (isValid == false) {
			i = getInt(sc, prompt);
			if (i < min)
				System.out.println("Error! Number must be " + min + " or greater.");
			else if (i > max)
				System.out.println("Error! Number must be " + max + " or less.");
			else
				isValid = true;
		}
		return i;
	}

	public static double getDouble(Scanner sc, String prompt) {
		double d = 0;
		boolean isValid = false;
		while (isValid == false) {
			System.out.print(prompt);
			if (sc.hasNextDouble()) {
				d = sc.nextDouble();
				isValid = true;
			} else {
				System.out.println("Error! Invalid decimal value. Try again.");
			}
			sc.nextLine(); // discard any other data entered on the line
		}
		return d;
	}

	public static double getDouble(Scanner sc, String prompt, double min, double max) {
		double d = 0;
		boolean isValid = false;
		while (isValid == false) {
			d = getDouble(sc, prompt);
			if (d < min)
				System.out.println("Error! Number must be " + min + " or greater.");
			else if (d > max)
				System.out.println("Error! Number must be " + max + " or less.");
			else
				isValid = true;
		}
		return d;
	}
}