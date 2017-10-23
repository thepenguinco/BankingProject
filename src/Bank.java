import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.io.IOException;

/**
 * A bank interface to interact with the banking application.
 * 
 * @author Eric Li
 * @version 1.0 2017-10-08
 */
public class Bank
{
	// Global constants
	
	/**
	 * The age of majority at this bank.
	 */
	public static final int AGE_OF_MAJORITY = 18;
	
	/**
	 * The minimum number of accounts required for a funds transfer to take place.
	 */
	public static final int FUNDS_TRANSFER_REQUIREMENT = 2;
	
	/**
	 * The upper boundary of valid SIN numbers.
	 */
	public static final int MAXIMUM_SIN = 999999999;
	
	/**
	 * The lower boundary of valid SIN numbers.
	 */
	public static final int MINIMUM_SIN = 100000000;

	// Global variables
	
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
	
	/*
	 * Global state of the profile menu
	 */
	private static boolean profileMenuActive;
	
	// Menu methods
	
	/*
	 * Display the main bank menu
	 */
	private static void mainMenu() throws IOException
	{
		// Prompt for input
    	System.out.println("Welcome to the VP bank.");
		System.out.println("-----------------------");
		System.out.println("Please choose an action from the following: ");
		System.out.println("	1: Add a customer");
		System.out.println("	2: Delete a customer");
		System.out.println("	3: Sort customers by last name, first name");
		System.out.println("	4: Sort customers by SIN");
		System.out.println("	5: Display customers in this bank (name, SIN)");
		System.out.println("	6: Find profile by last name, first name");
		System.out.println("	7: Find profile by SIN");
		System.out.println("	8: Quit");
    	System.out.print("Enter an option: ");
        try
        {
            // Read console input
            int mainOption = Integer.parseInt(console.readLine());
            System.out.println();
           
            // Required for scope
            ArrayList<Customer> customerSearch;;
            
            // Process the console input
            switch (mainOption)
            {
                case 1:
                    // Create a customer
                    try
                    {
                    	// this is gross
                    	System.out.println("Creating a new customer...");
                    	System.out.println();
	                    System.out.print("Enter the customer's last name: ");
	                    String lastName = console.readLine();
	                    System.out.print("Enter the customer's first name: ");
	                    String firstName = console.readLine();
	                    System.out.print("Enter the customer's SIN: ");
	                    int sin = Integer.parseInt(console.readLine());
	                    // check unique SIN
	                    if(!customerList.hasUniqueSin(sin))
	                    {
	                    	System.out.println("This is not a unique sin.");
	                    	System.out.println("You may be a victim of identity fraud!");
	                    	System.out.println("This account will not be created.");
	                    	break;
	                    } // if (!customerList.isUniqueSin(sin))
	                    else if (sin > MAXIMUM_SIN || sin < MINIMUM_SIN)
	                    {
	                    	System.out.println("Please enter a valid SIN.");
	                    	break;
	                    } // end of else if (sin > MAXIMUM) 
	                    System.out.print("Enter the customer's birth year: ");
	                    int birthYear = Integer.parseInt(console.readLine());
	                    System.out.print("Enter the customer's birth month: ");
	                    int birthMonth = Integer.parseInt(console.readLine());
	                    System.out.print("Enter the customer's birth day: ");
	                    int birthDay = Integer.parseInt(console.readLine());
	                    System.out.println();
	                    Customer customer = new Customer(firstName, lastName, sin, birthYear, birthMonth, birthDay);
	                    // required for scope
	                    Account account = null;
	                    // check age
	                    if (customer.getAge() >= AGE_OF_MAJORITY)
	                    {
	                    	System.out.println("Select an account to be created: ");
		                    System.out.println("	1. Chequing Account ");
		                    System.out.println("	2. Savings Account");
		                    System.out.println("	3. Credit Card ");
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
				                	System.out.println("Invalid account type, automatically generating a savings account.");
				                	account = new SavingsAccount();
		                    } // end of switch (selection)
	                    } // end of if (age >= AGE_OF_MAJORITY)
	                    else
	                    {
	                    	System.out.println("The customer is under the age of "
	                    			+ AGE_OF_MAJORITY + ".");
	                    	System.out.println("A savings account will be created.");
	                    	account = new SavingsAccount();
	                    } // end of else
	                    System.out.print("Enter the initial balance of this account: ");
	                    double initialBalance = Double.parseDouble(console.readLine());
	                    if (initialBalance > 0)
	                    {
	                    	account.depositFunds(initialBalance);
		                    System.out.println("A " + account.getStringType() + " with an initial balance of $"
		                    		+ Utility.MONEY_FORMAT.format(account.getBalance()) + " has been created.");
	                    } // end of if (initialBalance < 0)
	                    else 
	                    {
	                    	System.out.println("The initial account balance cannot be negative.");
	                    } // end of else
	                    customer.addAccount(account);
	                    customerList.addCustomer(customer);
	                    System.out.println("Customer created successfully.");
	                    
                    } // end of try
                    catch (NumberFormatException | DateTimeException exception)
                    {
                        System.out.println("Please enter a valid birthdate and SIN number.");
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
	                	System.out.print("Enter the SIN of the customer: ");
	                	int sin = Integer.parseInt(console.readLine());
	                	System.out.println("The following customers matched your search criteria: ");
	                	customerSearch = customerList.getCustomersBySin(sin);
	                	for (int i = 0; i < customerSearch.size(); i++)
	                	{
	                		System.out.println((i + 1) + ". " + customerSearch.get(i).getSummary());
	                	} // end of for (int i = 0 ...
	                	try
		                {
		                	if (customerSearch.size() > 0)
		                	{
		                		int customerIndex = Integer.parseInt(console.readLine()) - 1;
		                		customerList.removeCustomer(customerSearch.get(customerIndex));
		                		System.out.println("Customer deleted.");  
		                	} // end of if (customerSearch.size() > 0)
		                	else 
		                	{
		                		System.out.println("No customers found.");
		                	} // end of else
		                } // end of try
	                	catch (IndexOutOfBoundsException | NumberFormatException e)
	                	{
	                		System.out.println("Please select a valid customer.");
	                	} // end of catch (IndexOutOfBoundsException | NumberFormatException ...
                	} // end of if (selection == 1)
                	else if (selection == 2)
                	{
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
		                		customerList.removeCustomer(customerSearch.get(customerIndex));
		                		System.out.println("Customer deleted.");
		                	} // end of if (customerSearch.size() > 0)
		                	else 
		                	{
		                		System.out.println("No customers found.");
		                	} // end of else
		                } // end of try
	                	catch (IndexOutOfBoundsException | NumberFormatException e)
	                	{
	                		System.out.println("That is not a valid customer.");
	                	} // end of catch (IndexOutOfBounds Exception | NumberFormatException ...
                	} // end of if (selection == 2)
                	else 
                	{
                		System.out.println("That's not a valid option.");
                	} // end of else
	                break;
                case 3:
                    // Sort customers by last name, then first name
                	customerList.sortByName();
                	System.out.println("Successfully sorted by last name, then first name.");
                	break;
                case 4:
                	// Sort customers by SIN
                	customerList.sortBySin();
                	System.out.println("Successfully sorted by SIN.");
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
	                			profileMenuActive = true;
		                		while (profileMenuActive) profileMenu(customer);
	                		} // end of if (customerIndex < customerSearch.size())
	                		else
	                		{
	                			System.out.println("That is not a valid customer.");
	                		} // end of else
	                	} // end of if (customerSearch.size() > 0)
	                	else
	                	{
	                		System.out.println("No customers with those search parameters were found.");
	                	} // end of else
                	} // end of try
                	catch (IndexOutOfBoundsException | NumberFormatException e)
                	{
                		System.out.println("That is not a valid customer.");
                	} // end of catch (IndexOutOfBoundsException | NumberFormatException ...
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
	                	} // end of for (int i = 0 ...
	                	if (customerSearch.size() > 0)
	                	{
	                		int customerIndex = Integer.parseInt(console.readLine()) - 1;
	                		if (customerIndex < customerSearch.size())
	                		{
	                			Customer customer = customerSearch.get(customerIndex);
	                			profileMenuActive = true;
		                		while (profileMenuActive) profileMenu(customer);
	                		} // end of if (customerIndex < customerSearch.size()
	                		else
	                		{
	                			System.out.println("That is not a valid customer.");
	                		} // end of else
	                	} // end of if (customer.searchSize() > 0)
	                	else
	                	{
	                		System.out.println("No customers with those search parameters were found.");
	                	} // end of else
                	} // end of try
                	catch (IndexOutOfBoundsException | NumberFormatException e)
                	{
                		System.out.println("Please enter a valid SIN number and a valid menu choice.");
                	} // end of catch (IndexOutOfBoundsException | NumberFormatException ...
                	break;
                case 8:
                	// Save to file
                	System.out.println("Saving bank files...");
                	customerList.exportList(fileName);
                    System.exit(0);
                default:
                	// Invalid menu option
                    System.out.println(mainOption + " is not a valid menu choice.");
            } // end of switch (mainOption)
            System.out.println();
        } // end of try
        catch (NumberFormatException exception) 
        {
            System.out.println("Please enter a valid numerical menu choice selection.");
            System.out.println();
        } // end of catch (NumberFormatException exception)
	}
	
	/*
	 * Display the profile menu for a customer
	 */
	private static void profileMenu(final Customer customer) throws IOException
	{
		System.out.println();
		System.out.println("PROFILE MENU - " + customer.getLastName() + ", " + customer.getFirstName());
		System.out.println("-----------------------");
		System.out.println("	1: View account activity");
		System.out.println("	2: Deposit");
		System.out.println("	3: Withdraw");
		System.out.println("	4: Process cheque");
		System.out.println("	5: Process purchase");
		System.out.println("	6: Process payment");
		System.out.println("	7: Transfer funds");
		System.out.println("	8: Open account or issue card");
		System.out.println("	9: Cancel account or card");
		System.out.println("	10: Return to main menu");
    	System.out.print("Enter an option: ");
		try
		{
			int option = Integer.parseInt(console.readLine());
			// main/source account list, required for scope
			ArrayList<Account> accountList;
			// destination account list, required for scope
			ArrayList<Account> accountList2;
			switch (option)
			{
				case 1:
					// View account activity
					accountList = customer.getAccounts();
					System.out.println("Please select an account: ");
			    	for (int i = 0; i < accountList.size(); i++)
			    	{
			    		System.out.println((i + 1) + ". " + accountList.get(i).getStringType() + 
			    				" $" + Utility.MONEY_FORMAT.format(accountList.get(i).getBalance()));
			    	} // end of for (int i = 0 ...
					try
					{
						int accountIndex = Integer.parseInt(console.readLine()) - 1;
						System.out.println(customer.getAccounts().get(accountIndex).getTransactionHistory());
					} // end of try
					catch (IndexOutOfBoundsException | NumberFormatException e)
					{
						System.out.println("Please enter a valid account.");
					} // end of catch (IndexOutOfBoundsException | NumberFormatException e)
					break;
				case 2:
					// Deposit
					accountList = customer.getPrimaryAccounts();
					if (accountList.size() > 0)
					{
						System.out.println("Please select an account: ");
				    	for (int i = 0; i < accountList.size(); i++)
				    	{
				    		System.out.println((i + 1) + ". " + accountList.get(i).getStringType() + 
				    				" $" + Utility.MONEY_FORMAT.format(accountList.get(i).getBalance()));
				    	} // end of for (int i = 0 ...
						try
						{
							int accountIndex = Integer.parseInt(console.readLine()) - 1;
							System.out.print("Enter an amount to deposit into the account: ");
							double amount = Double.parseDouble(console.readLine());
							if (amount < 0)
							{
								System.out.println("You cannot deposit a negative amount.");
							} // end of if (amount < 0)
							else
							{
								Account account = accountList.get(accountIndex);
								double initialBalance = account.getBalance();
								account.depositFunds(amount);
								Transaction transaction = new Transaction(Transaction.DEPOSIT_ID, initialBalance, amount, account.getBalance());
								account.addTransaction(transaction);
								System.out.println("You have deposited $" + Utility.MONEY_FORMAT.format(amount) + ".");
								System.out.println("This " + account.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(account.getBalance()));
							} // end of else
						} // end of try
						catch (IndexOutOfBoundsException | NumberFormatException e)
						{
							System.out.println("Please enter a valid account and amount to deposit.");
						} // end of catch (IndexOutOfBoundsException | NumberFormatException e)
					} // end of if (accountList.size() > 0)
					else 
					{
						System.out.println("You have no primary (chequing or savings) accounts.");
					} // end of else
					break;
				case 3:
					// withdraw
					accountList = customer.getAccounts();
					System.out.println("Please select an account: ");
					try
					{
				    	for (int i = 0; i < accountList.size(); i++)
				    	{
				    		System.out.println((i + 1) + ". " + accountList.get(i).getStringType() + 
				    				" $" + Utility.MONEY_FORMAT.format(accountList.get(i).getBalance()));
				    	} // end of for (int i = 0 ...
						int accountIndex = Integer.parseInt(console.readLine()) - 1;
						System.out.print("Enter an amount to withdraw from the account: ");
						double amount = Double.parseDouble(console.readLine());
						Account account = accountList.get(accountIndex);
						if (amount < 0)
						{
							System.out.println("You cannot withdraw a negative amount.");
						} // end of if (amount < 0)
						else if (account.getType() != CreditCard.ID && account.getBalance() - amount < 0)
						{
							System.out.println("You cannot go into a negative balance with this account.");
						} // end of else if (account.getType() 
						else
						{
							double initialBalance = account.getBalance();
							account.withdrawFunds(amount);
							Transaction transaction = new Transaction(Transaction.WITHDRAW_ID, initialBalance, amount, account.getBalance());
							account.addTransaction(transaction);
							System.out.println("You have withdrawn $" + Utility.MONEY_FORMAT.format(amount) + ".");
							System.out.println("This " + account.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(account.getBalance()));
						} // end of else
					} // end of try
					catch (IndexOutOfBoundsException | NumberFormatException e)
					{
						System.out.println("Please enter a valid account and amount to withdraw.");
					} // end of catch (IndexOutOfBoundsException | NumberFormatException e)
					break;
				case 4:
					// process cheque
					accountList = customer.getChequingAccounts();
					if (customer.getAge() >= AGE_OF_MAJORITY && accountList.size() > 0)
					{
						try
						{
							System.out.println("The chequing accounts of this customer: ");
							for (int i = 0; i < accountList.size(); i++)
							{
								System.out.println((i + 1) + " " + accountList.get(i).getStringType() + " $" 
										+ Utility.MONEY_FORMAT.format(accountList.get(i).getBalance()));
							} // end of for (int i = 0 ...
							int accountIndex = Integer.parseInt(console.readLine()) - 1;
							ChequingAccount account = (ChequingAccount) accountList.get(accountIndex);
							System.out.print("Enter the amount you wish to write a cheque out for: ");
							double amount = Double.parseDouble(console.readLine());
							if (amount < 0)
							{
								System.out.println("You cannot process a cheque with a negative amount.");
							}
							else if ((account.getBalance() < ChequingAccount.FEE_EXEMPTION_BALANCE  
									&& account.getBalance() - amount - ChequingAccount.PROCESSING_FEE < 0)
									|| account.getBalance() - amount < 0)
							{
								System.out.println("Insufficient funds for the transaction.");
							} // end of else if ((account.getBalance() ...
							else
							{
								double initialBalance = account.getBalance();
								account.processCheque(amount);
								Transaction transaction = new Transaction(Transaction.PROCESS_CHEQUE_ID, initialBalance, amount, account.getBalance());
								account.addTransaction(transaction);
								System.out.println("Cheque successfully processed.");
								System.out.println("This " + account.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(account.getBalance()));
							} // end of else
						} // end of try
						catch (IndexOutOfBoundsException | NumberFormatException e)
						{
							System.out.println("Please enter a valid account and a valid amount to process a cheque.");
						} // end of catch (IndexOutOfBoundsException | NumberFormatException e)
					}
					else 
					{
						System.out.println("You have no chequing accounts.");
					} // end of else
					break;
				case 5:
					// credit card purchase
					accountList = customer.getCreditCards();
					if (accountList.size() > 0)
					{
						try
						{
							System.out.println("Please select a credit card: ");
					    	for (int i = 0; i < accountList.size(); i++)
					    	{
					    		System.out.println((i + 1) + ". " + accountList.get(i).getStringType() + 
					    				" $" + Utility.MONEY_FORMAT.format(accountList.get(i).getBalance()));
					    	} // end of for (int i = 0 ...
							int accountIndex = Integer.parseInt(console.readLine()) - 1;
							System.out.print("Enter an amount to purchase using this credit card: ");
							double amount = Double.parseDouble(console.readLine());
							if (amount < 0)
							{
								System.out.println("You cannot purchase a negative amount.");
							} // end of if (amount < 0)
							else
							{
								Account account = accountList.get(accountIndex);
								double initialBalance = account.getBalance();
								account.withdrawFunds(amount);
								Transaction transaction = new Transaction(Transaction.CREDIT_PURCHASE_ID, initialBalance, amount, account.getBalance());
								account.addTransaction(transaction);
								System.out.println("You have purchased $" + Utility.MONEY_FORMAT.format(amount) + " with this credit card.");
								System.out.println("This " + account.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(account.getBalance()));
							} // end of else
						} // end of try
						catch (IndexOutOfBoundsException | NumberFormatException e)
						{
							System.out.println("Please enter a valid account and a valid amount to purchase using your credit card.");
						} // end of catch (IndexOutOfBoundsException | NumberFormatException e)
					} // end of if (accountList.size() > 0)
					else 
					{
						System.out.println("You have no credit cards.");
					} // end of else
					break;
				case 6:
					// credit card payment
					accountList = customer.getPrimaryAccounts();
					accountList2 = customer.getCreditCards();
					if (accountList.size() > 0 && accountList2.size() > 0)
					{
						try
						{
							System.out.println("Please select a primary source account: ");
					    	for (int i = 0; i < accountList.size(); i++)
					    	{
					    		System.out.println((i + 1) + ". " + accountList.get(i).getStringType() + 
					    				" $" + Utility.MONEY_FORMAT.format(accountList.get(i).getBalance()));
					    	} // end of for (int i = 0 ...
					    	int sourceAccountIndex = Integer.parseInt(console.readLine()) - 1;
							System.out.println("Please select a credit card to be paid off: ");
					    	for (int i = 0; i < accountList2.size(); i++)
					    	{
					    		System.out.println((i + 1) + ". " + accountList2.get(i).getStringType() + 
					    				" $" + Utility.MONEY_FORMAT.format(accountList2.get(i).getBalance()));
					    	} // end of for (int i = 0 ...
							int creditCardIndex = Integer.parseInt(console.readLine()) - 1;
							Account sourceAccount = accountList.get(sourceAccountIndex);
							CreditCard destinationAccount = (CreditCard) accountList2.get(creditCardIndex);
							System.out.print("Enter an amount to pay of using this credit card: ");
							double amount = Double.parseDouble(console.readLine());
							if (amount < 0)
							{
								System.out.println("You cannot pay off using a negative amount.");
							} // end of if (amount < 0)
							else if (sourceAccount.getBalance() - amount < 0)
							{
								System.out.println("This primary account cannot go into a negative balance.");
							} // end of else if (sourceAccount.getBalance() ...
							else
							{
								// required for scope
								Transaction sourceTransaction = null;
								Transaction destinationTransaction = null;
								double sourceInitialBalance = sourceAccount.getBalance();
								double destinationInitialBalance = destinationAccount.getBalance();
								// handle transactions
								sourceAccount.withdrawFunds(amount);
								destinationAccount.depositFunds(amount);
								if (sourceAccount.getType() == ChequingAccount.ID) destinationTransaction = new Transaction(Transaction.CREDIT_PAYMENT_FROM_CHEQUING_ID, destinationInitialBalance, amount, destinationAccount.getBalance());
								else destinationTransaction = new Transaction(Transaction.CREDIT_PAYMENT_FROM_SAVINGS_ID, destinationInitialBalance, amount, destinationAccount.getBalance());
								sourceTransaction = new Transaction(Transaction.PAYMENT_TO_CREDIT_CARD_ID, sourceInitialBalance, amount, sourceAccount.getBalance());
								sourceAccount.addTransaction(sourceTransaction);
								destinationAccount.addTransaction(destinationTransaction);
								System.out.println("You have submitted a payment of $" + Utility.MONEY_FORMAT.format(amount) + " to this credit card.");
								System.out.println("This " + sourceAccount.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(sourceAccount.getBalance()));
								System.out.println("This " + destinationAccount.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(destinationAccount.getBalance()));
							} // end of else
						} // end of try
						catch (IndexOutOfBoundsException | NumberFormatException e)
						{
							System.out.println("Please enter valid accounts and a valid amount to pay off your credit card.");
						} // end of catch (IndexOutOfBoundsException | NumberFormatException e)
					} // end of if (accountList.size() ...
					else 
					{
						System.out.println("You have no credit cards or no primary (savings or chequing) accounts to pay off a credit card.");
					} // end of else
					break;
				case 7:
					// transfer funds
					accountList = customer.getPrimaryAccounts();
					if (accountList.size() >= FUNDS_TRANSFER_REQUIREMENT)
					{
						try
						{
							System.out.println("Please select a primary source account: ");
					    	for (int i = 0; i < accountList.size(); i++)
					    	{
					    		System.out.println((i + 1) + ". " + accountList.get(i).getStringType() + 
					    				" $" + Utility.MONEY_FORMAT.format(accountList.get(i).getBalance()));
					    	} // end of for (int i = 0 ...
					    	int sourceAccountIndex = Integer.parseInt(console.readLine()) - 1;
							System.out.println("Please select a primary account to transfer funds to: ");
					    	for (int i = 0; i < accountList.size(); i++)
					    	{
					    		// don't display the source account again
					    		if (i != sourceAccountIndex)
					    			System.out.println((i + 1) + ". " + accountList.get(i).getStringType() + 
					    					" $" + Utility.MONEY_FORMAT.format(accountList.get(i).getBalance()));
					    	} // end of for (int i = 0 ...
							int destinationAccountIndex = Integer.parseInt(console.readLine()) - 1;
							Account sourceAccount = accountList.get(sourceAccountIndex);
							Account destinationAccount = accountList.get(destinationAccountIndex);
							System.out.print("Enter an amount to transfer: ");
							double amount = Double.parseDouble(console.readLine());
							if (sourceAccountIndex == destinationAccountIndex)
							{
								System.out.println("You cannot transfer funds into the same account.");
							} // end if (sourceAccountIndex ...
							else if (amount < 0)
							{
								System.out.println("You cannot transfer a negative amount of funds.");
							} // end of else if (amount < 0)
							else if (sourceAccount.getBalance() - amount < 0)
							{
								System.out.println("The source primary account cannot go into a negative balance.");
							} // end of if (sourceAccount.getBalance() ...
							else
							{ 
								double sourceInitialBalance = sourceAccount.getBalance();
								double destinationInitialBalance = destinationAccount.getBalance();
								sourceAccount.withdrawFunds(amount);
								destinationAccount.depositFunds(amount);
								// required for scope
								Transaction sourceTransaction = null;
								Transaction destinationTransaction = null;
								if (sourceAccount.getType() == ChequingAccount.ID) destinationTransaction = new Transaction(Transaction.TRANSFER_FROM_CHEQUING_ID, destinationInitialBalance, amount, destinationAccount.getBalance());
								else destinationTransaction = new Transaction(Transaction.TRANSFER_FROM_SAVINGS_ID, destinationInitialBalance, amount, destinationAccount.getBalance());
								if (destinationAccount.getType() == ChequingAccount.ID) sourceTransaction = new Transaction(Transaction.TRANSFER_TO_CHEQUING_ID, sourceInitialBalance, amount, sourceAccount.getBalance());
								else sourceTransaction = new Transaction(Transaction.TRANSFER_TO_SAVINGS_ID, sourceInitialBalance, amount, sourceAccount.getBalance());
								sourceAccount.addTransaction(sourceTransaction);
								destinationAccount.addTransaction(destinationTransaction);
								System.out.println("You have transferred $" + Utility.MONEY_FORMAT.format(amount) + ".");
								System.out.println("The source " + sourceAccount.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(sourceAccount.getBalance()));
								System.out.println("The destination " + destinationAccount.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(destinationAccount.getBalance()));
							} // end of else
						} // end of try
						catch (IndexOutOfBoundsException | NumberFormatException e)
						{
							System.out.println("Please enter valid accounts and a valid amount to transfer.");
						} // end of catch (IndexOutOfBoundsException | NumberFormatException e)
					} // end of if (accountList.size() ...
					else 
					{
						System.out.println("You do not have a sufficient amount of primary (savings or chequing) accounts to transfer funds.");
					} // end of else
					break;
				case 8:
					// Open an account
					try
	                {
						// required for scope
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
				                	account = new SavingsAccount();
				                	System.out.println("Invalid account type, automatically generating a savings account.");
		                    } // end of switch (selection)
	                    } // end of if (age >= AGE_OF_MAJORITY)
	                    else
	                    {
	                    	System.out.println("The customer is under the age of "
	                    			+ AGE_OF_MAJORITY + ".");
	                    	System.out.println("A savings account will be created.");
	                    	account = new SavingsAccount();
	                    } // end of else
	                    System.out.print("What is the initial balance of this account: ");
	                    double initialBalance = Double.parseDouble(console.readLine());
	                    if (account.getType() != CreditCard.ID && initialBalance < 0)
	                    {
	                    	System.out.println("This type of account cannot have a negative initial balance.");
	                    } // end of if (account.getType() ...
	                    else
	                    {   
	                    	account.depositFunds(initialBalance);
		                    customer.addAccount(account);
		                    System.out.println("A " + account.getStringType() + " with an initial balance of $"
		                    		+ Utility.MONEY_FORMAT.format(account.getBalance()) + " has been created.");
	                    } // end of else
	                } // end of try
					catch (NumberFormatException e)
					{
						System.out.println("Please enter a valid menu choice and valid numerical data.");
						System.out.println("Account creation failed.");
					} // end of catch (NumberFormatException e)
					break;
				case 9:
					// Delete or cancel an account
					accountList = customer.getAccounts();
					System.out.println("Please select an account: ");
			    	for (int i = 0; i < accountList.size(); i++)
			    	{
			    		System.out.println((i + 1) + ". " + accountList.get(i).getStringType() + 
			    				" $" + Utility.MONEY_FORMAT.format(accountList.get(i).getBalance()));
			    	} // end of for (int i = 0 ...
					try
					{
						int accountIndex = Integer.parseInt(console.readLine()) - 1;
						customer.removeAccount(accountList.get(accountIndex));
						System.out.println("Account sucessfully deleted.");
						if (customer.getAccounts().size() == 0)
						{
							System.out.println("No accounts exist for this customer.");
							customerList.removeCustomer(customer);
							System.out.println("This customer has been deleted.");
							profileMenuActive = false;
						} // end of if (customer.getAccounts() ...
					} // end of try
					catch (IndexOutOfBoundsException | NumberFormatException e)
					{
						System.out.println("Please enter a valid account.");
					} // end of catch (IndexOutOfBoundsException | NumberFormatException e)
					break;
				case 10:
					profileMenuActive = false;
					break;
				default:
					System.out.println("Please enter a valid menu option.");
					System.out.println();
			} // end of switch (option)
		} // end of try
		catch (NumberFormatException e)
		{
			System.out.println("Please enter a valid menu option.");
			System.out.println();
		} // end of catch (NumberFormatException e)
	} // end of method profileMenu
	
	/**
	 * The main bank interface
	 * 
	 * @param argument
	 * @throws IOException
	 */
	public static void main(String[] argument) throws IOException
	{
	    // Establish a connection to the console.
      	console = new BufferedReader(new InputStreamReader(System.in));

	    // Load customer list from file (if exists)
		System.out.print("Enter database file name: ");
		fileName = console.readLine();
		try
		{
			System.out.println("Loading from file...");
			customerList = new CustomerList(fileName);
		} // end of try
		catch (FileNotFoundException exception)
		{
			System.out.println("File not found, creating new database...");
			customerList = new CustomerList();
		} // end of catch (FileNotFoundException exception)
		
		System.out.println();
        
		// main menu
		while (true)
		{
			mainMenu();
		} // end of while (true)
	} // end of method main (String[] argument) ...
} // end of class Bank