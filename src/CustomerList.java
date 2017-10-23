import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A list of the customers of a bank
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-10
 */
public class CustomerList
{
	// class fields
	
	/**
	 * The line length for each customer in the file database 
	 */
	public static final int CUSTOMER_LENGTH = 6;
	
	/**
	 * The line length for each account in the file database 
	 */
	public static final int ACCOUNT_LENGTH = 2;
	
	/**
	 * The line length for each transaction in the file database 
	 */
	public static final int TRANSACTION_LENGTH = 4;
	
	/**
	 * The delimiter for a space in the file database
	 */
	public static final String MAGIC_SPACE = "\\s";
	
    // instance fields

    private ArrayList<Customer> customerList;
    
    // constructors
    
    /**
     * Constructs a default empty customer list.
     * 
     */
    public CustomerList()
    {
    	customerList = new ArrayList<Customer>();
    } // end of constructor CustomerList()
       
    /**
     * Constructs a customer list from a file database.
     * 
     * @param fileName the file name of the database
     * @throws IOException
     */
    public CustomerList(final String fileName) throws IOException
    {
    	customerList = new ArrayList<Customer>();
        
        // Establish connections to the text files
        BufferedReader database = new BufferedReader(new FileReader(fileName));
        
        // Read from the first file
        String lineOfText = database.readLine();
        
        // variables, scope required
		Customer customer = null;
		Account account = null;
		Transaction transaction = null;
		
    	while (true)
    	{
    		lineOfText = database.readLine();
    		if (lineOfText == null) break;
    		// System.out.println(lineOfText);
    		String[] line = lineOfText.split(" ");
    		if (line.length == CUSTOMER_LENGTH)
     		{
    			String lastName = line[0].replace(MAGIC_SPACE, " ");
    			String firstName = line[1].replace(MAGIC_SPACE, " ");
            	int sin = Integer.parseInt(line[2]);
            	int birthYear = Integer.parseInt(line[3]);
            	int birthMonth = Integer.parseInt(line[4]);
            	int birthDay = Integer.parseInt(line[5]);
            	customer = new Customer(firstName, lastName, sin, birthYear, birthMonth, birthDay);
            	customerList.add(customer);
     		} // end of if (line.length ...
    		else if (line.length == ACCOUNT_LENGTH && customer != null)
    		{
    			if (Integer.parseInt(line[0]) == ChequingAccount.ID)
    				account = new ChequingAccount(Double.parseDouble(line[1]));
    			else if (Integer.parseInt(line[0]) == SavingsAccount.ID)
					account = new SavingsAccount(Double.parseDouble(line[1]));
    			else if (Integer.parseInt(line[0]) == CreditCard.ID)
					account = new CreditCard(Double.parseDouble(line[1]));
    			customer.addAccount(account);
    		} // end of else if (line.length ...
    		else if (line.length == TRANSACTION_LENGTH && account != null)
    		{
    			transaction = new Transaction(Integer.parseInt(line[0]), Double.parseDouble(line[1]),Double.parseDouble(line[2]), Double.parseDouble(line[3]));
    			account.addTransaction(transaction);
    		} // end of else if (line.length ...
    		else 
    		{
    			System.out.println("Your database is corrupt!");
    			System.out.println("Halting program!");
    			System.exit(0);
    		} // end of else
    	} // end of while (true)
        
        // wrap up
        database.close();
    } // end of constructor CustomerList(String fileName)
    
    // accessors
     
    /**
     * Returns the customers from this list that match the name search criteria.
     * 
     * @return the list of customers matching the name search criteria
     */
    public ArrayList<Customer> getCustomersByName(String firstName, String lastName)
    {
    	ArrayList<Customer> customerSearch = new ArrayList<Customer>(); 
    	for (Customer customer : customerList)
    	{
    		if (customer.getFirstName().equalsIgnoreCase(firstName) && customer.getLastName().equalsIgnoreCase(lastName))
    		{
    			customerSearch.add(customer);
    		} // end of if (customer.getFirstName ...
    	} // end of for (Customer customer ...
    	return customerSearch;
    } // end of method getCustomersByName(String firstName ...
    
    /**
     * Returns the customers from this list that match the SIN search criteria.
     * 
     * @return the list of customers matching the SIN search criteria
     */
    public ArrayList<Customer> getCustomersBySin(int sin)
    {
    	ArrayList<Customer> customerSearch = new ArrayList<Customer>(); 
    	for (Customer customer : customerList)
    	{
    		if (customer.getSin() == sin) customerSearch.add(customer);
    	} // end of for (Customer customer : customerList)
    	return customerSearch;
    } // end of method getCustomersBySin(int sin)
    
    /**
     * Returns this customer list.
     * 
     * @return this customer list
     */
    public ArrayList<Customer> getList()
    {
    	return customerList;
    } // end of method getList() 
    
    /**
     * Returns <code>true</code> if this SIN is unique; otherwise <code>false</code>.
     * 
     * @return <code>true</code> if the given sin is unique; otherwise <code>false</code>
     */
    public boolean hasUniqueSin(int sin) 
    {
    	for (Customer customer : customerList)
    	{
    		if (customer.getSin() == sin) return false;
    	} // end of for (Customer customer : customerList)
    	return true;
    } // end of method hasUniqueSin()
    
    // mutators
    
    /**
     * Adds a customer to this customer list.
     * 
     * @param customer a customer to be added to this customer list
     */
	public void addCustomer(Customer customer)
	{
		customerList.add(customer);
	} // end of method addCustomer(Customer customer)
	
    /**
     * Deletes a customer from this customer list.
     * 
     * @param index the index in the list of this customer
     */
	public void removeCustomer(Customer customer) {
		customerList.remove(customer);		
	} // end of method removeCustomer(Customer customer)

    /**
     * Sorts the customers in this customer list by name.
     */
    public void sortByName() 
    {
    	Collections.sort(customerList, Customer.compareByName);
    } // end of method sortByName()
	
    /**
     * Sorts the customers in this customer list by SIN.
     */
    public void sortBySin() 
    {
    	Collections.sort(customerList, Customer.compareBySin);
    } // end of method sortBySin()
    
    // file IO
    
    /**
     * Saves this customer list to a file database.
     * 
     * @param fileName the name of the file containing the database
     * @throws IOException
     */
    public void exportList(final String fileName) throws IOException
    {        
    	// Establish connections to the text files.
        PrintWriter database = new PrintWriter(new FileWriter(fileName));
        
        // Print first line
        database.println("START");
		
        // Populate database
        for (Customer customer : customerList)
        {
        	database.println(customer.getLastName().replace(" ", MAGIC_SPACE)
        			+ " " + customer.getFirstName().replace(" ", MAGIC_SPACE)
        			+ " " + customer.getSin() + " " + customer.getBirthYear()
        			+ " " + customer.getBirthMonth() + " " + customer.getBirthDay());
        	for (Account chequingAccount : customer.getChequingAccounts())
        	{
        		database.println(ChequingAccount.ID + " " + chequingAccount.getBalance());
        		for (Transaction transaction : chequingAccount.getTransactionsReversed())
        		{
        			database.println(transaction.getTransactionType() + " " + transaction.getInitialBalance()
					+ " " + transaction.getAmount() + " " + transaction.getFinalBalance());
        		} // end of for (Transaction transaction ...
        	} // end of for (Account chequingAccount ...
        	for (Account savingsAccount : customer.getSavingsAccounts())
        	{
        		database.println(SavingsAccount.ID + " " + savingsAccount.getBalance());
        		for (Transaction transaction : savingsAccount.getTransactionsReversed())
        		{
        			database.println(transaction.getTransactionType() + " " + transaction.getInitialBalance()
					+ " " + transaction.getAmount() + " " + transaction.getFinalBalance());
        		} // end of for (Transaction transaction ...
        	} // end of for (Account savingsAccount ...
        	for (Account creditCard : customer.getCreditCards())
        	{
        		database.println(CreditCard.ID + " " + creditCard.getBalance());
        		for (Transaction transaction : creditCard.getTransactionsReversed())
        		{
        			database.println(transaction.getTransactionType() + " " + transaction.getInitialBalance()
        					+ " " + transaction.getAmount() + " " + transaction.getFinalBalance());
        		} // end of for (Transaction transaction ...
        	} // end of for (Account creditCard ...
        } // end of for (Customer customer ...
        
    	// wrap up
        database.close();
    } // end of method exportList(String fileName) ...
        
    // other methods, string methods
    
    /**
     * Returns the string of customers in this customer list.
     * 
     * @return the string of customers in this customer list
     */
    public String getCustomers()
    {
    	String customers = "";
        for (int i = 0; i < customerList.size(); i++)
            customers = customers + (i + 1) + ": " + customerList.get(i).getSummary() + "\n";
        return customers;
    } // end of method getCustomers()  
    
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
    } // end of method toString()
} // end of class CustomerList