import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
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
	    
    	System.out.println("");
    	
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
			System.out.println("5: Display customers in this bank (name, SIN)");
			System.out.println("6: Find profile by last name, first name");
			System.out.println("7: Find profile by SIN");
			System.out.println("8: Quit");
	
	        try
	        {
	            // Read console input
	            int option = Integer.parseInt(console.readLine());
	            System.out.println();
	           
	            boolean failure = false;
	
	            // Process the console input
	            switch (option)
	            {
	                case 1:
	                    // Create a customer
	                    try
	                    {
	                    	System.out.println("Creating a new customer:");
	                    	System.out.println();
		                    System.out.print("Enter the customer's last name: ");
		                    String lastName = console.readLine();
		                    System.out.print("Enter the customer's first name: ");
		                    String firstName = console.readLine();
		                    System.out.print("Enter the customer's SIN: ");
		                    int sin = Integer.parseInt(console.readLine());
		                    System.out.print("Enter the customer's birth year: ");
		                    int birthYear = Integer.parseInt(console.readLine());
		                    System.out.print("Enter the customer's birth month: ");
		                    int birthMonth = Integer.parseInt(console.readLine());
		                    System.out.print("Enter the customer's birth day? ");
		                    int birthDay = Integer.parseInt(console.readLine());
		                    System.out.println();
		                    // Validate birthday
		                    LocalDate birthDate = LocalDate.of(birthYear,
		                    		birthMonth, birthDay);
		                    LocalDate today = LocalDate.now();
		                    long age = ChronoUnit.YEARS.between(birthDate, today);
	                    	String accountID = Customer.SAVINGS_ID;
		                    if (age >= AGE_OF_MAJORITY)
		                    {
		                    	System.out.println("Select an account to be created: ");
			                    System.out.println("1. Chequing Account ");
			                    System.out.println("2. Savings Account");
			                    System.out.println("3. Credit Card ");
			                   
			                    int selection = Integer.parseInt(console.readLine());
			                    switch (selection)
			                    {
			                    	case 1:
					                    accountID = Customer.CHEQUING_ID;
					                    System.out.println("Creating new chequing account.");
					                    break;
			                    	case 2:
					                    accountID = Customer.SAVINGS_ID;
					                    System.out.println("Creating new savings account.");
					                    break;
			                    	case 3:
					                    accountID = Customer.CREDIT_CARD_ID;
					                    System.out.println("Creating new credit card.");
					                    break;
					                default:
					                	System.out.println("Please enter a valid menu selection.");
					                	failure = true;
			                    }
		                    } // end of if (age >= AGE_OF_MAJORITY)
		                    else
		                    {
		                    	System.out.println("The customer is under the age of "
		                    			+ AGE_OF_MAJORITY + ".");
		                    	System.out.println("A savings account will be created.");
		                    }
		                    if (failure)
		                    {
			                	System.out.println("Customer creation failed.");
			                	break;
		                    }
		                    System.out.println("What is the initial opening balance of this account: ");
		                    int initialBalance = Integer.parseInt(console.readLine());
		                    customerList.addCustomer(firstName, lastName, sin,
		                    		birthYear, birthMonth, birthDay, accountID, initialBalance);
		                    System.out.println("Customer created successfully.");
	                    } // end of try
	                    catch (NumberFormatException | DateTimeException exception)
	                    {
	                        System.out.println("Please enter a valid "
	                        		+ "birthdate and SIN number.");
		                	System.out.println("Please enter a valid account type to be created.");
		                	System.out.println("Customer creation failed.");
	                    } // end of catch (NumberFormatException exception) ...
	                    break;
	                case 2:
	                    // Delete a customer
	                    System.out.print("How would you like to search for the customer to delete: ");
	                    System.out.print("1. By SIN.");
	                    System.out.print("2. By first and last name.");
	                    // TODO
	                    // HANDLE FINDING MULTIPLES
	                    break;
	                  
	                case 3:
	                    // Sort customers by last name, then first name
	                	customerList.sortByName();
	                	System.out.println("Successfully sorted by first name, then last name!");
	                	break;
	                case 4:
	                	// Sort customers by SIN
	                	customerList.sortBySin();
	                	System.out.println("Successfully sorted by SIN!");
	                    break;
	                case 5:
	                    // Display customer summary (name, SIN)
	                	System.out.println(customerList.getCustomers());
	                    break;
	                case 6:
	                    // Find profile by first name, last name
	                	System.out.println("Enter the last name of the customer: ");
	                	String lastName = console.readLine();
	                	System.out.println("Enter the first name of the customer: ");
	                	String firstName = console.readLine();
	                	System.out.println("The following customers matched your search criteria: ");
	                	for (Customer customer : customerList.getList())
	                	{
	                		if (customer.getLastName().equals(lastName) && customer.getFirstName().equals(firstName))
	                		{
	                			System.out.println(customer.getSummary());
	                		}
	                	}
	                	// TODO
	                    break;
	                case 7:
	                	// Find profile by SIN
	                	System.out.println("Enter the SIN of the customer: ");
	                	int sin = Integer.parseInt(console.readLine());
	                	System.out.println("The following customers matched your search criteria: ");
	                	for (Customer customer : customerList.getList())
	                	{
	                		if (customer.getSin() == sin)
	                		{
	                			System.out.println(customer.getSummary());
	                		}
	                	}
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
	            System.out.println("Please enter a valid numerical menu choice selection!");
	            System.out.println();
	        } // end of catch (InputMismatchException exception)
	    } // end of while (true)
	} // end of method main (String[] argument) ...
} // end of class Bank