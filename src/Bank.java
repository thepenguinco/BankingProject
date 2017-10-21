import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
	
	/**
	 * Display the main bank menu
	 * @throws IOException 
	 */
	public static void mainMenu() throws IOException
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
	            int mainOption = Integer.parseInt(console.readLine());
	            System.out.println();
	           
	            // required for scope
	            boolean failure = false;
	            ArrayList<Customer> customerSearch;;
	            
	            // Process the console input
	            switch (mainOption)
	            {
	                case 1:
	                    // Create a customer
	                    try
	                    {
	                    	System.out.println("Creating a new customer...");
	                    	System.out.println();
		                    System.out.print("Enter the customer's last name: ");
		                    String lastName = console.readLine();
		                    System.out.print("Enter the customer's first name: ");
		                    String firstName = console.readLine();
		                    System.out.print("Enter the customer's SIN: ");
		                    int sin = Integer.parseInt(console.readLine());
		                    // check SIN
		                    for (Customer customer : customerList.getList())
		                    {
		                    	if (sin == customer.getSin())
		                    	{
		                    		System.out.println("This is not a unique sin!");
		                    		System.out.println("You may be a victim of identity fraud!");
		                    		failure = true;
		                    		break;
		                    	}
		                    }
	                    	if (failure) break;
	                    	failure = false;
		                    System.out.print("Enter the customer's birth year: ");
		                    int birthYear = Integer.parseInt(console.readLine());
		                    System.out.print("Enter the customer's birth month: ");
		                    int birthMonth = Integer.parseInt(console.readLine());
		                    System.out.print("Enter the customer's birth day: ");
		                    int birthDay = Integer.parseInt(console.readLine());
		                    System.out.println();
		                    // Validate birthday;
		                    Customer customer = new Customer(firstName, lastName, sin, birthYear, birthMonth, birthDay);
		                    Account account = null;
		                    if (customer.getAge() >= AGE_OF_MAJORITY)
		                    {
		                    	System.out.println("Select an account to be created: ");
			                    System.out.println("1. Chequing Account ");
			                    System.out.println("2. Savings Account");
			                    System.out.println("3. Credit Card ");
			                    int selection = Integer.parseInt(console.readLine());
			                    switch (selection)
			                    {
			                    	case 1:
					                    account = new ChequingAccount();
					                    System.out.println("Creating new chequing account.");
					                    break;
			                    	case 2:
					                    account = new SavingsAccount();
					                    System.out.println("Creating new savings account.");
					                    break;
			                    	case 3:
					                    account = new CreditCard();
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
		                    	account = new SavingsAccount();
		                    }
		                    if (failure)
		                    {
			                	System.out.println("Customer creation failed.");
			                	break;
		                    }
		                    System.out.print("What is the initial opening balance of this account: ");
		                    double initialBalance = Double.parseDouble(console.readLine());
		                    account.depositFunds(initialBalance);
		                    customerList.addCustomer(customer);
		                    System.out.println("Customer created successfully.");
	                    } // end of try
	                    catch (NumberFormatException | DateTimeException exception)
	                    {
	                        System.out.println("Please enter a valid "
	                        		+ "birthdate and SIN number.");
		                	System.out.println("Please enter a valid account type to be created.");
		                	System.out.println("Please enter a valid balance for the account to be created.");
		                	System.out.println("Customer creation failed.");
	                    } // end of catch (NumberFormatException exception) ...
	                    break;
	                case 2:
	                	// Delete a customer
	                	System.out.println("How would you like to search for the customer to delete: ");
	                	System.out.println("1. By SIN");
	                	System.out.println("2. By name");
	                	int selection = Integer.parseInt(console.readLine());
	                	if (selection == 1)
	                	{
		                	// Find profile by SIN
		                	System.out.println("Enter the SIN of the customer: ");
		                	int sin = Integer.parseInt(console.readLine());
		                	System.out.println("The following customers matched your search criteria: ");
		                	customerSearch = customerList.getCustomersBySin(sin);
		                	for (int i = 0; i < customerSearch.size(); i++)
		                	{
		                		System.out.println((i + 1) + ". " + customerSearch.get(i).getSummary());
		                	}
		                	try
			                {
			                	if (customerSearch.size() > 0)
			                	{
			                		int customerIndex = Integer.parseInt(console.readLine()) - 1;
			                		if (customerIndex < customerSearch.size())
			                		{
			                			customerList.removeCustomer(customerSearch.get(customerIndex));
			                			System.out.println("Customer deleted!");
			                		}
			                	}
			                	else 
			                	{
			                		System.out.println("No customers found!");
			                	}
			                }
		                	catch (ArrayIndexOutOfBoundsException | NumberFormatException e)
		                	{
		                		System.out.println("That's not a valid customer!");
		                	}
		                	finally
		                	{
		                		System.out.println("Exiting to main menu.");
		                	}
	                	}
	                	else if (selection == 2)
	                	{
		                    // Find profile by first name, last name
		                	System.out.println("Enter the last name of the customer: ");
		                	String lastName = console.readLine();
		                	System.out.println("Enter the first name of the customer: ");
		                	String firstName = console.readLine();
		                	System.out.println("The following customers matched your search criteria: ");
		                	customerSearch = customerList.getCustomersByName(firstName, lastName);
		                	for (int i = 0; i < customerSearch.size(); i++)
		                	{
		                		System.out.println((i + 1) + ". " + customerSearch.get(i).getSummary());
		                	}
		                	try
			                {
			                	if (customerSearch.size() > 0)
			                	{
			                		int customerIndex = Integer.parseInt(console.readLine()) - 1;
			                		if (customerIndex < customerSearch.size())
			                		{
			                			customerList.removeCustomer(customerSearch.get(customerIndex));
			                			System.out.println("Customer deleted!");
			                		}
			                	}
			                	else 
			                	{
			                		System.out.println("No customers found!");
			                	}
			                }
		                	catch (ArrayIndexOutOfBoundsException | NumberFormatException e)
		                	{
		                		System.out.println("That's not a valid customer!");
		                	}
		                	finally
		                	{
		                		System.out.println("Exiting to main menu.");
		                	}
	                	}
	                	else {
	                		System.out.println("That's not a valid option!");
	                	}
		                break;
	                case 3:
	                    // Sort customers by last name, then first name
	                	customerList.sortByName();
	                	System.out.println("Successfully sorted by last name, then first name!");
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
	                	System.out.print("Enter the last name of the customer: ");
	                	String lastName = console.readLine();
	                	System.out.print("Enter the first name of the customer: ");
	                	String firstName = console.readLine();
	                	System.out.println("The following customers matched your search criteria: ");
	                	customerSearch = customerList.getCustomersByName(firstName, lastName);
	                	for (int i = 0; i < customerSearch.size(); i++)
	                	{
	                		System.out.println((i + 1) + ". " + customerSearch.get(i).getSummary());
	                	} // end of for (int i = 0 ...
	                	try
	                	{
		                	if (customerSearch.size() > 0)
		                	{
		                		int customerIndex = Integer.parseInt(console.readLine()) - 1;
		                		if (customerIndex < customerSearch.size())
		                		{
		                			Customer customer = customerSearch.get(customerIndex);
			                		profileMenu(customer);
		                		}
		                		else
		                		{
		                			System.out.println("That is not a valid customer!");
		                		}
		                	}
	                	}
	                	catch (ArrayIndexOutOfBoundsException | NumberFormatException e)
	                	{
	                		System.out.println("That is not a valid customer!");
	                	}
	                	finally
	                	{
	                		System.out.println("Exiting to main menu.");
	                	}
	                    break;
	                case 7:
	                	// Find profile by SIN
	                	try
	                	{
		                	System.out.print("Enter the SIN of the customer: ");
		                	int sin = Integer.parseInt(console.readLine());
		                	System.out.println("The following customers matched your search criteria: ");
		                	customerSearch = customerList.getCustomersBySin(sin);
		                	for (int i = 0; i < customerSearch.size(); i++)
		                	{
		                		System.out.println((i + 1) + ". " + customerSearch.get(i).getSummary());
		                	}
		                	if (customerSearch.size() > 0)
		                	{
		                		int customerIndex = Integer.parseInt(console.readLine()) - 1;
		                		if (customerIndex < customerSearch.size())
		                		{
		                			Customer customer = customerSearch.get(customerIndex);
			                		profileMenu(customer);
		                		}
		                		else
		                		{
		                			System.out.println("That is not a valid customer!");
		                		}
		                	}
	                	}
	                	catch (ArrayIndexOutOfBoundsException | NumberFormatException e)
	                	{
	                		System.out.println("Please enter a valid SIN number and a valid menu choice!");
	                	}
	                	finally
	                	{
	                		System.out.println("Exiting to main menu.");
	                	}
	                	break;
	                case 8:
	                	// SAVE TO FILE
	                	System.out.println("Saving bank files...");
	                	customerList.exportList(fileName);
	                    System.exit(0);
	                default:
	                	// INVALID MENU OPTION
	                    System.out.println(mainOption + " is  not a valid menu choice!");
	            } // end of switch (mainOption)
	            System.out.println();
	        } // end of try
	        catch (NumberFormatException exception) 
	        {
	            System.out.println("Please enter a valid numerical menu choice selection!");
	            System.out.println();
	        } // end of catch (NumberFormatException exception)
	}
	
	/**
	 * Display the profile menu for a customer
	 */
	public static void profileMenu(Customer customer) throws IOException
	{
		System.out.println("PROFILE MENU");
		System.out.println("-----------------------");
		System.out.println("1: View account activity");
		System.out.println("2: Deposit");
		System.out.println("3: Withdraw");
		System.out.println("4: Process cheque");
		System.out.println("5: Process purchase");
		System.out.println("6: Process payment");
		System.out.println("7: Transfer funds");
		System.out.println("8: Open account or issue card");
		System.out.println("9: Cancel account or card");
		System.out.println("10: Return to main menu");
		try
		{
			int option = Integer.parseInt(console.readLine());
			switch (option)
			{
				case 1:
					// View account activity
					System.out.println("Please select an account: ");
					System.out.println(customer.getAccountSummary());
					try
					{
						int accountIndex = Integer.parseInt(console.readLine()) - 1;
						System.out.println(customer.getAccounts().get(accountIndex).getTransactionHistory());
					}
					catch (IndexOutOfBoundsException | NumberFormatException e)
					{
						System.out.println("Please enter a valid account!");
					}
					break;
				case 2:
					// Deposit
					break;
				case 3:
					// Withdraw
					break;
				case 4:
					// Process Cheque
					if (customer.getAge() >= AGE_OF_MAJORITY)
					{
						ArrayList<Account> chequingAccounts = customer.getChequingAccounts();
						System.out.println("The chequing accounts of this customer: ");
						for (int i = 0; i < chequingAccounts.size(); i++)
						{
							System.out.println((i + 1) + " " + chequingAccounts.get(i).getType() + " " + chequingAccounts.get(i).getBalance());
						}
						try
						{
							int accountIndex = Integer.parseInt(console.readLine()) - 1;
							Account account = chequingAccounts.get(accountIndex);
							System.out.println("Enter the amount you wish to write a cheque: ");
							account.processCheque(amount);
							System.out.println("Cheque successfully processed");
						}
						catch (IndexOutOfBoundsException | NumberFormatException e)
						{
							System.out.println("Please enter a valid account and a valid amount to process a cheque!");
						}
					}
					else 
					{
						System.out.println("Minors cannot write cheques.");
					}
					break;
				case 5:
					// 
					break;
				case 6:
					System.out.println("The accounts of this customer: ");
					for (int i = 0; i < customer.getAccounts().size(); i++)
					{
						System.out.println((i + 1) + ". " + customer.getAccounts().get(i).getType() + 
								" " + customer.getAccounts().get(i).getBalance());
					}
					break;
				case 7:
					System.out.println("The accounts of this customer: ");
					for (int i = 0; i < customer.getAccounts().size(); i++)
					{
						System.out.println((i + 1) + ". " + customer.getAccounts().get(i).getType() + 
								" " + customer.getAccounts().get(i).getBalance());
					}
					// Account menu
					break;
				case 8:
					// Open an account
					try
	                {
						Account account = null;
						if (customer.getAge() >= AGE_OF_MAJORITY)
						{
		                    System.out.println("Select an account to be created: ");
		                    System.out.println("1. Chequing Account ");
		                    System.out.println("2. Savings Account");
		                    System.out.println("3. Credit Card ");
		                    int selection = Integer.parseInt(console.readLine());
		                    switch (selection)
		                    {
		                    	case 1:
				                    account = new ChequingAccount();
				                    System.out.println("Creating new chequing account.");
				                    break;
		                    	case 2:
				                    account = new SavingsAccount();
				                    System.out.println("Creating new savings account.");
				                    break;
		                    	case 3:
				                    account = new CreditCard();
				                    System.out.println("Creating new credit card.");
				                    break;
				                default:
				                	System.out.println("Please enter a valid menu selection.");
		                    }
	                    } // end of if (age >= AGE_OF_MAJORITY)
	                    else
	                    {
	                    	System.out.println("The customer is under the age of "
	                    			+ AGE_OF_MAJORITY + ".");
	                    	System.out.println("A savings account will be created.");
	                    	account = new SavingsAccount();
	                    }
	                    System.out.print("What is the initial opening balance of this account: ");
	                    double initialBalance = Double.parseDouble(console.readLine());
	                    account.depositFunds(initialBalance);
	                    customer.addAccount(account);
	                }
					catch (NumberFormatException e)
					{
						System.out.println("Please enter a valid menu choice and valid numerical data.");
					}
					break;
				case 9:
					// Delete or cancel an account
					System.out.println("Please select an account: ");
					System.out.println(customer.getAccountSummary());
					try
					{
						int accountIndex = Integer.parseInt(console.readLine());
						customer.removeAccount(customer.getAccounts().get(accountIndex));
						System.out.println("Account sucessfully deleted.");
						if (customer.getAccounts().size() == 0)
						{
							System.out.println("No accounts exist for this customer.");
							customerList.removeCustomer(customer);
							System.out.println("This customer has been deleted.");
						}
					}
					catch (IndexOutOfBoundsException | NumberFormatException e)
					{
						System.out.println("Please enter a valid account!");
					}
					break;
				case 10:
					break;
				default:
					System.out.println("Please enter a valid menu option!");
					profileMenu(customer);
				}
		}
		catch (NumberFormatException e)
		{
			System.out.println("Please enter a valid menu option!");
			profileMenu(customer);
		}
	}
	
	/*
	 * Global console input
	 */
	private static BufferedReader console;
	
	/*
	 * Global customer list
	 */
	private static CustomerList customerList;
	
	/*
	 * Global file name 
	 */
	private static String fileName;
	
	/**
	 * The main bank interface
	 * 
	 * @param argument
	 * @throws IOException
	 */
	public static void main(String[] argument) throws IOException
	{
	    // Establish a connection to the console.
      	console = 
                new BufferedReader(new InputStreamReader(System.in));

	    // Load customer list from file (if exists)
		System.out.print("Enter file name: ");
		fileName = console.readLine();
		try
		{
			System.out.println("Loading from file...");
			customerList = new CustomerList(fileName);
		}
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found, creating new database...");
			customerList = new CustomerList();
		}
		
		System.out.println();
        
		while(true)
		{
			mainMenu();
		} // end of while(true)
		
	} // end of method main (String[] argument) ...
} // end of class Bank