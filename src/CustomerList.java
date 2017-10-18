import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
	public static final int CUSTOMER_LENGTH = 1;
	
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
        
    	while (lineOfText != null)
    	{
    		String[] line = database.readLine().split(" ");
    		Account account = null;
    		Customer customer = null;
    		if (line.length == CUSTOMER_LENGTH)
     		{
    			String lastName = line[0];
            	String firstName = database.readLine();
            	int sin = Integer.parseInt(database.readLine());
            	int birthYear = Integer.parseInt(database.readLine());
            	int birthMonth = Integer.parseInt(database.readLine());
            	int birthDay = Integer.parseInt(database.readLine());
            	customer = new Customer(lastName, firstName, sin, birthYear, birthMonth, birthDay);
     		}
    		
    		else if (line.length == ACCOUNT_LENGTH && customer != null)
    		{
    			customer.addAccount(account);
    			if (Integer.parseInt(line[0]) == Customer.CHEQUING_ID)
    					account = new ChequingAccount(Integer.parseInt(line[1]));
    			else if (Integer.parseInt(line[0]) == Customer.SAVINGS_ID)
					account = new SavingsAccount(Integer.parseInt(line[1]));
    			else if (Integer.parseInt(line[0]) == Customer.CREDIT_CARD_ID)
					account = new CreditCard(Integer.parseInt(line[1]));
    		}
    		else if (line.length == TRANSACTION_LENGTH && account != null)
    		{
    			Transaction transaction = new Transaction(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]));
    			account.addTransaction(transaction);
    		}
    		else 
    		{
    			System.out.println("Your database is corrupt!");
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
     * Returns the customers from this list that match the search criteria.
     * 
     * @return the customers matching the search criteria
     */
    public ArrayList<Customer> getCustomersByName(String firstName, String lastName)
    {
    	ArrayList<Customer> customerSearch = new ArrayList<Customer>(); 
    	for (Customer customer : customerList)
    	{
    		if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName))
    		{
    			customerSearch.add(customer);
    		}
    	}
    	return customerSearch;
    } // end of ArrayList<Customer getCustomersByName()
    
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
	}
	
    /**
     * Deletes a customer from this customer list
     * 
     * @param index the index in the list of this customer
     */
	public void removeCustomer(int index) {
		customerList.remove(index);		
	}

    /**
     * Sorts the customers in this customer list by name
     * 
     * @return the customers in this customer list
     */
    public void sortByName() 
    {
    	Collections.sort(customerList, Customer.compareByName);
    }
	
    /**
     * Sorts the customers in this customer list by sin
     * 
     * @return the customers in this customer list
     */
    public void sortBySin() 
    {
    	Collections.sort(customerList, Customer.compareBySin);
    }
    
    // file IO
    
    /**
     * Saves this customer list to a file database
     * 
     * @throws IOException
     */
    public void exportList() throws IOException
    {
    	for (Customer customer : customerList)
    	{
    		// TODO
    	}
    }
        
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
            customers = customers + (i + 1) + ": " + customerList.get(i).getSummary();
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