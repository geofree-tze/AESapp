import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Arrays;

public class Scramble {

	public static void main(String[] args) {
		
		Console cons;
		char [] secretKey01 = null;
		char [] secretKey02 = null;
		if ((cons = System.console()) != null) {
			secretKey01 = cons.readPassword("Enter your password: ");
		}
		else {
			System.out.println("No Console found...");
		}
		
		if ((cons = System.console()) != null) {
			secretKey02 = cons.readPassword("Enter your password again: ");
		}
		else {
			System.out.println("No Console found...");
		}

		boolean isMatch = Arrays.equals(secretKey01, secretKey02);
		
		if (isMatch) {
			Scanner obj = new Scanner(System.in);
			System.out.print("Enter your message: ");			
			String originalString = obj.nextLine();
			
			//Enc
			final String secretKey = new String(secretKey01);
			String encSite = MyAESApp.encrypt(originalString, secretKey);
			
			//Display all
			System.out.println("Scrambled message: " + encSite);
		}
		else {
			System.out.println("Passwords didn't match.");			
		}
	}
}
