import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * A bank interface to interact with the banking application
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-08
 */
public class Bank
{
	/**
	 * The age of majority at this bank
	 */
	public static final int AGE_OF_MAJORITY = 18;
	
	public static void main(String[] argument) throws IOException
	{
	    // Load customer list from file (if exists)
	    // TODO
	
	    // Establish a connection to the console.
        BufferedReader console = 
                new BufferedReader(new InputStreamReader(System.in));
	    

    	System.out.println("Please enter the directory location in which your customers are stored in");
    	
    	CustomerList customerList = new CustomerList();
        
	    // Main loop
	    while (true)
	    {
	        // Prompt for input
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
	        	// menu variables
	        	Account account;
	        	int birthYear;
	        	int birthMonth;
	        	int birthDate;
	        	String firstName;
	        	String lastName;
	        	int selection;
	        	int sin;
	        	
	            // Read console input
	            int option = Integer.parseInt(console.readLine());
	            System.out.println();
	
	            // Process the console input
	            switch (option)
	            {
	                case 1:
	                    // Create a customer
	                    try
	                    {
		                    System.out.print("What is the last name of the customer? ");
		                    lastName = console.readLine();
		                    System.out.print("What is the first name of the customer? ");
		                    firstName = console.readLine();
		                    System.out.print("What is the SIN number of the customer? ");
		                    sin = Integer.parseInt(console.readLine());
		                    System.out.print("What is the birth year of the customer? ");
		                    birthYear = Integer.parseInt(console.readLine());
		                    System.out.print("What is the birth month of the customer? ");
		                    birthMonth = Integer.parseInt(console.readLine());
		                    System.out.print("What is the birth date of the customer? ");
		                    birthDate = Integer.parseInt(console.readLine());
		                    // Validate birthday to ensure the customer is old enough to have certain accounts
		                    // Add selection for what type of account to be created
		                    // TODO
		                    account = new ChequingAccount();
		                    Customer customer = new Customer(firstName, lastName, sin, birthYear, birthMonth, birthDate, account);
		                    System.out.println("Customer created successfully.");
	                    } // end of try
	                    catch (NumberFormatException exception)
	                    {
	                        System.out.println("Please enter valid numerical data for the birthdate and the SIN number.");
	                        break;
	                    } // end of catch (InputMismatchException exception)
	                    break;
	                case 2:
	                    // Delete a customer
	                    System.out.print("How would you like to search for the customer to delete: ");
	                    System.out.print("1. By SIN.");
	                    System.out.print("2. By first and last name.");
	                    // TODO
	                    break;
	                  
	                case 3:
	                    // Sort customers by last name, first name ??? ASK
	                	// TODO
	                	customerList.sortBySin();
	                	System.out.println("Successfully sorted by first name, then last name!");
	                case 4:
	                    // Sort customers by SIN
	                	// TODO
	                    break;
	                case 5:
	                    // Display customer summary (name, SIN)
	                	// TODO
	                    break;
	                case 6:
	                    // Find profile by first name, last name
	                	// TODO
	                    break;
	                case 7:
	                	// Find profile by SIN
	                	// TODO
	                	break;
	                case 8:
	                	// SAVE TO FILE
	                	System.out.println("Saving bank files...");
	                	// TODO
	                    System.exit(0);
	                default:
	                    System.out.println(option + " is  not a valid menu choice!");
	            } // end of switch (option)
	            System.out.println();
	        } // end of try
	        catch (NumberFormatException exception) 
	        {
	            System.out.println("You didn't enter a valid numerical menu choice selection!");
	            System.out.println();
	        } // end of catch (InputMismatchException exception)
	    } // end of while (true)
	} // end of method main (String[] argument) ...
} // end of class Bank