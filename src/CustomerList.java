import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A customer list of the bank
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-10
 */

public class CustomerList
{
	// class fields
	
	/**
	 * The delimiter for each customer in the file database 
	 */
	public static final int CUSTOMER_LENGTH = 6;
	
	/**
	 * The delimiter for each account in the file database 
	 */
	public static final int ACCOUNT_LENGTH = 2;
	
	/**
	 * The delimiter for each transaction in the file database 
	 */
	public static final int TRANSACTION_LENGTH = 3;
	
    // instance fields

    private ArrayList<Customer> customerList;
    
    // constructors
    
    /**
     * Constructs a default customer list
     * 
     */
    public CustomerList()
    {
    	customerList = new ArrayList<Customer>();
    } // end of constructor CustomerList()
       
    /*
     * Constructs a customer list from a file database
     * 
     * @throws IOException
     */
    public CustomerList(final String fileName) throws IOException
    {
    	customerList = new ArrayList<Customer>();
        
        // Establish connections to the text files.
        BufferedReader database = new BufferedReader(new FileReader(fileName));
        
        // Read from the first file
        String lineOfText = database.readLine();
        
		Customer customer = null;
		Account account = null;
		Transaction transaction = null;
		
    	while (true)
    	{
    		lineOfText = database.readLine();
    		if (lineOfText == null) break;
    		System.out.println(lineOfText);
    		String[] line = lineOfText.split(" ");
    		if (line.length == CUSTOMER_LENGTH)
     		{
    			String lastName = line[0];
            	String firstName = line[1];
            	int sin = Integer.parseInt(line[2]);
            	int birthYear = Integer.parseInt(line[3]);
            	int birthMonth = Integer.parseInt(line[4]);
            	int birthDay = Integer.parseInt(line[5]);
            	customer = new Customer(firstName, lastName, sin, birthYear, birthMonth, birthDay);
            	customerList.add(customer);
     		}
    		else if (line.length == ACCOUNT_LENGTH && customer != null)
    		{
    			if (Integer.parseInt(line[0]) == Customer.CHEQUING_ID)
    				account = new ChequingAccount(Double.parseDouble(line[1]));
    			else if (Integer.parseInt(line[0]) == Customer.SAVINGS_ID)
					account = new SavingsAccount(Double.parseDouble(line[1]));
    			else if (Integer.parseInt(line[0]) == Customer.CREDIT_CARD_ID)
					account = new CreditCard(Double.parseDouble(line[1]));
    			customer.addAccount(account);
    		}
    		else if (line.length == TRANSACTION_LENGTH && account != null)
    		{
    			transaction = new Transaction(Integer.parseInt(line[0]),Double.parseDouble(line[1]),Double.parseDouble(line[2]));
    			account.addTransaction(transaction);
    		}
    		else 
    		{
    			System.out.println("Your database is corrupt!");
    			System.out.println("Halting program!");
    			System.exit(0);
    		}
    	}
        
        // Wrap up.
        database.close();
    } // end of constructor CustomerList(String fileName)
    
    // accessors
    
    /**
     * Returns the customer in this list with the specified index.
     * 
     * @return the customer in this list with the specified index
     */
    public Customer getCustomer(int index)
    {
    	return customerList.get(index);
    } // end of getCustomer() 
    
    /**
     * Returns the customers from this list that match the name search criteria.
     * 
     * @return the array list of customers matching the name search criteria
     */
    public ArrayList<Customer> getCustomersByName(String firstName, String lastName)
    {
    	ArrayList<Customer> customerSearch = new ArrayList<Customer>(); 
    	for (Customer customer : customerList)
    	{
    		if (customer.getFirstName().equalsIgnoreCase(firstName) && customer.getLastName().equalsIgnoreCase(lastName))
    		{
    			customerSearch.add(customer);
    		}
    	}
    	return customerSearch;
    } // end of ArrayList<Customer> getCustomersByName(String ...
    
    /**
     * Returns the customers from this list that match the sin search criteria.
     * 
     * @return the array list of customers matching the sin search criteria
     */
    public ArrayList<Customer> getCustomersBySin(int sin)
    {
    	ArrayList<Customer> customerSearch = new ArrayList<Customer>(); 
    	for (Customer customer : customerList)
    	{
    		if (customer.getSin() == sin)
    		{
    			customerSearch.add(customer);
    		}
    	}
    	return customerSearch;
    } // end of ArrayList<Customer> getCustomersBySin(int ...
    
    /**
     * Returns this customer list.
     * 
     * @return this customer list
     */
    public ArrayList<Customer> getList()
    {
    	return customerList;
    } // end of getList() 
    
    // mutators
    
    /**
     * Adds a customer to this customer list
     * 
     * @param customer a customer to be added to this customer list
     */
	public void addCustomer(Customer customer)
	{
		customerList.add(customer);
	} // end of addCustomer(Customer customer)
	
    /**
     * Deletes a customer from this customer list
     * 
     * @param index the index in the list of this customer
     */
	public void removeCustomer(int index) {
		customerList.remove(index);		
	} // end of removeCustomer(int index)

    /**
     * Sorts the customers in this customer list by name
     * 
     * @return the customers in this customer list
     */
    public void sortByName() 
    {
    	Collections.sort(customerList, Customer.compareByName);
    } // end of sortByName()
	
    /**
     * Sorts the customers in this customer list by sin
     * 
     * @return the customers in this customer list
     */
    public void sortBySin() 
    {
    	Collections.sort(customerList, Customer.compareBySin);
    } // end of sortBySin()
    
    // file IO
    
    /**
     * Saves this customer list to a file database
     * 
     * @param fileName the name of the file containing the database
     * @throws IOException
     */
    public void exportList(String fileName) throws IOException
    {        
    	// Establish connections to the text files.
        PrintWriter database = new PrintWriter(new FileWriter(fileName));
        
        // Print first line
        database.println("START");
		
        for (Customer customer : customerList)
        {
        	database.println(customer.getLastName() + " " + customer.getFirstName()
        			+ " " + customer.getSin() + " " + customer.getBirthYear()
        			+ " " + customer.getBirthMonth() + " " + customer.getBirthDay());
        	for (Account chequingAccount : customer.getChequingAccounts())
        	{
        		System.out.println(chequingAccount.getBalance());
        		database.println(Customer.CHEQUING_ID + " " + chequingAccount.getBalance());
        		for (Transaction transaction : chequingAccount.getTransactionsReversed())
        		{
        			database.println(transaction.getTransactionType() + " " + transaction.getAmount()
        					+ " " + transaction.getFinalBalance());
        		}
        	}
        	for (Account savingsAccount : customer.getSavingsAccounts())
        	{
        		database.println(Customer.SAVINGS_ID + " " + savingsAccount.getBalance());
        		for (Transaction transaction : savingsAccount.getTransactionsReversed())
        		{
        			database.println(transaction.getTransactionType() + " " + transaction.getAmount()
        					+ " " + transaction.getFinalBalance());
        		}
        	}
        	for (Account creditCard : customer.getCreditCards())
        	{
        		database.println(Customer.CREDIT_CARD_ID + " " + creditCard.getBalance());
        		for (Transaction transaction : creditCard.getTransactionsReversed())
        		{
        			database.println(transaction.getTransactionType() + " " + transaction.getAmount()
        					+ " " + transaction.getFinalBalance());
        		}
        	}
        }
    	// wrap up
        database.close();
    } // end of exportList(String fileName) ...
        
    // other methods, string methods
    
    /**
     * Returns the customers in this customer list.
     * 
     * @return the customers in this customer list
     */
    public String getCustomers()
    {
    	String customers = "";
        for (int i = 0; i < customerList.size(); i++)
            customers = customers + (i + 1) + ": " + customerList.get(i).getSummary() + "\n";
        return customers;
    } // end of getCustomers()  
    
    /**
     * Shows a string representation of this customer list
     * 
     * @return the string representation of this customer list
     */
    public String toString()
    {
        return
        getClass().getName() 
        + " ["
        + "Customers: " + customerList
        + "]";
    } // end of toString()
    
} // end of class CustomerList