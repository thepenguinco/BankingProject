import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A bank interface to interact with the banking application
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-08
 */
public class Bank
{
	public static final int AGE_OF_MAJORITY = 18;
	
	public static void main(String[] args)
	{
		// initialize input/ouput
		Scanner scanner = new Scanner(System.in);
		int optionSelected = 0;
		
		// banking application interface
		do
		{
			System.out.println("Welcome to the VP bank.");
			System.out.println("-----------------------");
			System.out.println("Please choose an action from the following: ");
			System.out.println("1: Add a customer");
			System.out.println("2: Delete a customer");
			System.out.println("3: Sort customers by last name, first name");
			System.out.println("4: Sort customers by SIN");
			System.out.println("5: Display customer summary (name, SIN)");
			System.out.println("6: Find profile by last name, first name");
			System.out.println("7: Find profile by SIN");
			System.out.println("8: Quit");
			
			try 
			{
				int selection = scanner.nextInt();
				//switch ()
			} 
			catch (InputMismatchException e)
			{
				System.out.println("Please enter a valid numerical option. ");
			}
		} while (optionSelected != 8);
		
	}
}
