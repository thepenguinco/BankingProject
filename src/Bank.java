import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.io.IOException;

/**
 * A bank interface to interact with the banking application
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-08
 */
// HASN'T BEEN CHECKED THOROUGHLY
public class Bank
{
	// Global constants
	
	/**
	 * The age of majority at this bank
	 */
	public static final int AGE_OF_MAJORITY = 18;
	
	/**
	 * The minimum number of accounts required for a funds transfer to take place
	 */
	public static final int FUNDS_TRANSFER_REQUIREMENT = 2;
	
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
	
	// Menu methods
	
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
           
            // Required for scope
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
	                    // check unique SIN
	                    if(!customerList.hasUniqueSin(sin))
	                    {
	                    	System.out.println("This is not a unique sin.");
	                    	System.out.println("You may be a victim of identity fraud!");
	                    	System.out.println("This account will not be created.");
	                    	break;
	                    } // if (!customerList.isUniqueSin(sin))
	                    System.out.print("Enter the customer's birth year: ");
	                    int birthYear = Integer.parseInt(console.readLine());
	                    System.out.print("Enter the customer's birth month: ");
	                    int birthMonth = Integer.parseInt(console.readLine());
	                    System.out.print("Enter the customer's birth day: ");
	                    int birthDay = Integer.parseInt(console.readLine());
	                    System.out.println();
	                    Customer customer = new Customer(firstName, lastName, sin, birthYear, birthMonth, birthDay);
	                    Account account = null;
	                    // check age
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
	                    System.out.print("What is the initial balance of this account: ");
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
	                	} // end of for (int i = 0 ...
	                	try
		                {
		                	if (customerSearch.size() > 0)
		                	{
		                		int customerIndex = Integer.parseInt(console.readLine()) - 1;
		                		if (customerIndex < customerSearch.size())
		                		{
		                			customerList.removeCustomer(customerSearch.get(customerIndex));
		                			System.out.println("Customer deleted.");
		                		} // end of if (customerIndex < customerSearch.size())
		                	} // end of if (customerSearch.size() > 0)
		                	else 
		                	{
		                		System.out.println("No customers found.");
		                	} // end of else
		                } // end of try
	                	catch (IndexOutOfBoundsException | NumberFormatException e)
	                	{
	                		System.out.println("That's not a valid customer.");
	                	} // end of catch (IndexOutOfBoundsException | NumberFormatException ...
                	} // end of if (selection == 1)
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
	                	} // end of for (int i = 0 ...
	                	try
		                {
		                	if (customerSearch.size() > 0)
		                	{
		                		int customerIndex = Integer.parseInt(console.readLine()) - 1;
		                		if (customerIndex < customerSearch.size())
		                		{
		                			customerList.removeCustomer(customerSearch.get(customerIndex));
		                			System.out.println("Customer deleted.");
		                		} // end of if (customerIndex < ...
		                	} // end of if (customerSearch.size() > 0)
		                	else 
		                	{
		                		System.out.println("No customers found.");
		                	} // end of else
		                } // end of try
	                	catch (IndexOutOfBoundsException | NumberFormatException e)
	                	{
	                		System.out.println("That's not a valid customer.");
	                	} // end of catch (ArrayIndexOutOfBounds Exception | NumberFormatException ...
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
		                		profileMenu(customer);
	                		} // end of if (customerIndex < customerSearch.size())
	                		else
	                		{
	                			System.out.println("That is not a valid customer.");
	                		} // end of else
	                	} // end of if (customerSearch.size() > 0)
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
		                		profileMenu(customer);
	                		} // end of if (customerIndex < customerSearch.size()
	                		else
	                		{
	                			System.out.println("That is not a valid customer.");
	                		} // end of else
	                	} // end of if (customer.searchSize() > 0)
                	} // end of try
                	catch (IndexOutOfBoundsException | NumberFormatException e)
                	{
                		System.out.println("Please enter a valid SIN number and a valid menu choice.");
                	} // end of catch (IndexOutOfBoundsException | NumberFormatException ...
                	break;
                case 8:
                	// SAVE TO FILE
                	System.out.println("Saving bank files...");
                	customerList.exportList(fileName);
                    System.exit(0);
                default:
                	// INVALID MENU OPTION
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
	
	/**
	 * Display the profile menu for a customer
	 * 
	 * @throws IOException
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
			    	}
					try
					{
						int accountIndex = Integer.parseInt(console.readLine()) - 1;
						System.out.println(customer.getAccounts().get(accountIndex).getTransactionHistory());
					}
					catch (IndexOutOfBoundsException | NumberFormatException e)
					{
						System.out.println("Please enter a valid account.");
					}
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
				    	}
						try
						{
							int accountIndex = Integer.parseInt(console.readLine()) - 1;
							System.out.println("Enter an amount to deposit into the account: ");
							double amount = Double.parseDouble(console.readLine());
							if (amount < 0)
							{
								System.out.println("You cannot deposit a negative amount.");
							}
							else
							{
								Account account = accountList.get(accountIndex);
								account.depositFunds(amount);
								Transaction transaction = new Transaction(Transaction.DEPOSIT_ID, amount, account.getBalance());
								account.addTransaction(transaction);
								System.out.println("You have deposited $" + Utility.MONEY_FORMAT.format(amount) + ".");
								System.out.println("This " + account.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(account.getBalance()));
							}
						}
						catch (IndexOutOfBoundsException | NumberFormatException e)
						{
							System.out.println("Please enter a valid account and amount to deposit.");
						}
					}
					else 
					{
						System.out.println("You have no primary (chequing or savings) accounts.");
					}
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
				    	}
						int accountIndex = Integer.parseInt(console.readLine()) - 1;
						System.out.println("Enter an amount to withdraw from the account: ");
						double amount = Double.parseDouble(console.readLine());
						if (amount < 0)
						{
							System.out.println("You cannot withdraw a negative amount.");
						}
						else
						{
							Account account = accountList.get(accountIndex);
							account.withdrawFunds(amount);
							Transaction transaction = new Transaction(Transaction.WITHDRAW_ID, amount, account.getBalance());
							account.addTransaction(transaction);
							System.out.println("You have withdrawn $" + Utility.MONEY_FORMAT.format(amount) + ".");
							System.out.println("This " + account.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(account.getBalance()));
						}
					}
					catch (IndexOutOfBoundsException | NumberFormatException e)
					{
						System.out.println("Please enter a valid account and amount to withdraw.");
					}
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
							}
							int accountIndex = Integer.parseInt(console.readLine()) - 1;
							ChequingAccount account = (ChequingAccount) accountList.get(accountIndex);
							System.out.print("Enter the amount you wish to write a cheque out for: ");
							double amount = Double.parseDouble(console.readLine());
							if ((account.getBalance() < ChequingAccount.FEE_EXEMPTION_BALANCE  
									&& account.getBalance() - amount - ChequingAccount.PROCESSING_FEE < 0)
									|| account.getBalance() - amount < 0)
							{
								System.out.println("Insufficient funds for the transaction.");
							}
							else
							{
								account.processCheque(amount);
								Transaction transaction = new Transaction(Transaction.PROCESS_CHEQUE_ID, amount, account.getBalance());
								account.addTransaction(transaction);
								System.out.println("Cheque successfully processed");
								System.out.println("This " + account.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(account.getBalance()));
							}
						}
						catch (IndexOutOfBoundsException | NumberFormatException e)
						{
							System.out.println("Please enter a valid account and a valid amount to process a cheque.");
						}
					}
					else 
					{
						System.out.println("You have no chequing accounts.");
					}
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
					    	}
							int accountIndex = Integer.parseInt(console.readLine()) - 1;
							System.out.println("Enter an amount to purchase using this credit card: ");
							double amount = Double.parseDouble(console.readLine());
							if (amount < 0)
							{
								System.out.println("You cannot purchase a negative amount.");
							}
							else
							{
								Account account = accountList.get(accountIndex);
								account.withdrawFunds(amount);
								Transaction transaction = new Transaction(Transaction.CREDIT_PURCHASE_ID, amount, account.getBalance());
								account.addTransaction(transaction);
								System.out.println("You have purchased $" + Utility.MONEY_FORMAT.format(amount) + " with this credit card.");
								System.out.println("This " + account.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(account.getBalance()));
							}
						}
						catch (IndexOutOfBoundsException | NumberFormatException e)
						{
							System.out.println("Please enter a valid account and a valid amount to purchase using your credit card.");
						}
					}
					else 
					{
						System.out.println("You have no credit cards.");
					}
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
					    	}
					    	int sourceAccountIndex = Integer.parseInt(console.readLine()) - 1;
							System.out.println("Please select a credit card to be paid off: ");
					    	for (int i = 0; i < accountList2.size(); i++)
					    	{
					    		System.out.println((i + 1) + ". " + accountList2.get(i).getStringType() + 
					    				" $" + Utility.MONEY_FORMAT.format(accountList2.get(i).getBalance()));
					    	}
							int creditCardIndex = Integer.parseInt(console.readLine()) - 1;
							Account sourceAccount = accountList.get(sourceAccountIndex);
							CreditCard destinationAccount = (CreditCard) accountList2.get(creditCardIndex);
							System.out.println("Enter an amount to pay of using this credit card: ");
							double amount = Double.parseDouble(console.readLine());
							if (amount < 0)
							{
								System.out.println("You cannot pay off using a negative amount.");
							}
							else if (sourceAccount.getBalance() - amount < 0)
							{
								System.out.println("This primary account cannot go into a negative balance.");
							}
							else
							{
								sourceAccount.withdrawFunds(amount);
								destinationAccount.depositFunds(amount);
								Transaction transaction = new Transaction(Transaction.CREDIT_PAYMENT_ID, amount, sourceAccount.getBalance());
								sourceAccount.addTransaction(transaction);
								transaction = new Transaction(Transaction.CREDIT_PAYMENT_ID, amount, destinationAccount.getBalance());
								destinationAccount.addTransaction(transaction);
								System.out.println("You have submitted a payment of $" + Utility.MONEY_FORMAT.format(amount) + " to this credit card.");
								System.out.println("This " + sourceAccount.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(sourceAccount.getBalance()));
								System.out.println("This " + destinationAccount.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(destinationAccount.getBalance()));
							}
						}
						catch (IndexOutOfBoundsException | NumberFormatException e)
						{
							System.out.println("Please enter valid accounts and a valid amount to pay off your credit card.");
						}
					}
					else 
					{
						System.out.println("You have no credit cards or no primary (savings or chequing) accounts to pay off a credit card.");
					}
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
					    	}
					    	int sourceAccountIndex = Integer.parseInt(console.readLine()) - 1;
					    	accountList.remove(sourceAccountIndex);
							System.out.println("Please select a primary account to transfer funds to: ");
					    	for (int i = 0; i < accountList.size(); i++)
					    	{
					    		System.out.println((i + 1) + ". " + accountList.get(i).getStringType() + 
					    				" $" + Utility.MONEY_FORMAT.format(accountList.get(i).getBalance()));
					    	}
							int destinationAccountIndex = Integer.parseInt(console.readLine()) - 1;
							Account sourceAccount = accountList.get(sourceAccountIndex);
							Account destinationAccount = accountList.get(destinationAccountIndex);
							System.out.println("Enter an amount to transfer of using this credit card: ");
							double amount = Double.parseDouble(console.readLine());
							if (amount < 0)
							{
								System.out.println("You cannot transfer a negative amount of funds.");
							}
							else if (sourceAccount.getBalance() - amount < 0)
							{
								System.out.println("The source primary account cannot go into a negative balance.");
							}
							else
							{
								sourceAccount.withdrawFunds(amount);
								destinationAccount.depositFunds(amount);
								Transaction transaction = new Transaction(Transaction.TRANSFER_FUNDS_ID, amount, sourceAccount.getBalance());
								sourceAccount.addTransaction(transaction);
								transaction = new Transaction(Transaction.CREDIT_PAYMENT_ID, amount, destinationAccount.getBalance());
								destinationAccount.addTransaction(transaction);
								System.out.println("You have submitted a payment of $" + Utility.MONEY_FORMAT.format(amount) + " to this credit card.");
								System.out.println("The source " + sourceAccount.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(sourceAccount.getBalance()));
								System.out.println("The destination " + destinationAccount.getStringType() + " has a new balance of $" + Utility.MONEY_FORMAT.format(destinationAccount.getBalance()));
							}
						}
						catch (IndexOutOfBoundsException | NumberFormatException e)
						{
							System.out.println("Please enter valid accounts and a valid amount to transfer.");
						}
					}
					else 
					{
						System.out.println("You do not have a sufficient amount of primary (savings or chequing) accounts to transfer funds.");
					}
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
	                    System.out.print("What is the initial balance of this account: ");
	                    double initialBalance = Double.parseDouble(console.readLine());
	                    account.depositFunds(initialBalance);
	                    customer.addAccount(account);
	                    System.out.println("A " + account.getStringType() + " account with an initial balance of $"
	                    		+ Utility.MONEY_FORMAT.format(account.getBalance()) + " has been created");
	                }
					catch (NumberFormatException e)
					{
						System.out.println("Please enter a valid menu choice and valid numerical data.");
						System.out.println("Account creation failed.");
					}
					break;
				case 9:
					// Delete or cancel an account
					accountList = customer.getAccounts();
					System.out.println("Please select an account: ");
			    	for (int i = 0; i < accountList.size(); i++)
			    	{
			    		System.out.println((i + 1) + ". " + accountList.get(i).getStringType() + 
			    				" $" + Utility.MONEY_FORMAT.format(accountList.get(i).getBalance()));
			    	}
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
						}
					}
					catch (IndexOutOfBoundsException | NumberFormatException e)
					{
						System.out.println("Please enter a valid account.");
					}
					break;
				case 10:
					break;
				default:
					System.out.println("Please enter a valid menu option.");
			}
		}
		catch (NumberFormatException e)
		{
			System.out.println("Please enter a valid menu option.");
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
		System.out.print("Enter file name: ");
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