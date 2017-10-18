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
	public static final String CUSTOMER_DELIMITER = "#";
	
    // instance fields

    private ArrayList<Customer> customer;
    
    // constructors
    
    /**
     * Constructs a default customer list
     * 
     */
    public CustomerList()
    {
    	this.customer = new ArrayList<Customer>();
    } // end of constructor CustomerList()
       
    /*
     * Constructs a customer list from a file database
     * 
     * @throws IOException
     */
    public CustomerList(final String fileName) throws IOException
    {
    	this.customer = new ArrayList<Customer>();
        
        // Establish connections to the text files.
        BufferedReader database = new BufferedReader(new FileReader(fileName));
        
        // Read from the first file
        String lineOfText = database.readLine();
        
        while (lineOfText != null)
        {
        	String lastName = database.readLine();
        	String firstName = database.readLine();
        	int sin = Integer.parseInt(database.readLine());
        	int birthYear = Integer.parseInt(database.readLine());
        	int birthMonth = Integer.parseInt(database.readLine());
        	int birthDay = Integer.parseInt(database.readLine());
        	customer.addCustomer(lastName, firstName, sin, birthYear, birthMonth, birthDay);
        	do
        	{
        		String line = database.readLine();
        		Account account = null;
        		if (line.equals(Customer.CHEQUING_ID))
        		{
        			account = new ChequingAccount();
        		}
        		else if (line.equals(Customer.SAVINGS_ID))
        		{
        			
        		}
        		else if (line.equals(Customer.CREDIT_CARD_ID))
        		{
        			
        		}
        		else if (line.equals(CUSTOMER_DELIMITER))
        		{
        			break;
        		}
        		else 
        		{
        			int transactionType = Integer.parseInt(database.readLine());
        			int amount = Integer.parseInt(database.readLine());
        			int finalBalance = Integer.parseInt(database.readLine());
        			account.addTransaction(Integer.parseInt(transaction[0]), amount, finalBalance);
        		}
        		// case
        		// new accounts
        		// add some transactions
        	} while (!line.equals(CUSTOMER_DELIMITER));
           lineOfText = database.readLine();
        } // while(lineOfText != null)
        
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
    	return customer.get(index);
    } // end of getCustomer() 
    
    /**
     * Returns this customer list
     * 
     * @return this customer list
     */
    public ArrayList<Customer> getList()
    {
    	return customer;
    } // end of getList() 
    
    // mutators
    
    /**
     * Adds a customer to this customer list
     * 
     * @param customer a customer to be added to this customer list
     */
	public void addCustomer(String firstName, String lastName, int sin, int birthYear, 
			int birthMonth, int birthDay, String accountID, int initialBalance) {
		this.customer.add(new Customer(firstName, lastName, sin,
		                    		birthYear, birthMonth, birthDay, accountID, initialBalance));
	}
	
    /**
     * Adds a customer to this customer list
     * 
     * @param customer a customer to be added to this customer list
     */
	public void addCustomer(String firstName, String lastName, int sin, int birthYear, 
			int birthMonth, int birthDay) {
		this.customer.add(new Customer(firstName, lastName, sin,
		                    		birthYear, birthMonth, birthDay));
	}
	
	
    /**
     * Deletes a customer from this customer list
     * 
     * @param index the index in the list of this customer
     */
	public void removeCustomer(int index) {
		this.customer.remove(index);		
	}

    /**
     * Sorts the customers in this customer list by name
     * 
     * @return the customers in this customer list
     */
    public void sortByName() 
    {
    	Collections.sort(customer, Customer.compareByName);
    }
	
    /**
     * Sorts the customers in this customer list by sin
     * 
     * @return the customers in this customer list
     */
    public void sortBySin() 
    {
    	Collections.sort(customer, Customer.compareBySin);
    }
    
    // file IO
    
    /**
     * Saves this customer list to a file database
     * 
     * @throws IOException
     */
    public void exportList() throws IOException
    {
    	for (Customer customer : this.customer)
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
        for (int i = 0; i < customer.size(); i++)
            customers = customers + (i + 1) + ": " + customer.get(i).getSummary();
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
        + "Customers: " + customer
        + "]";
    } // end of toString()
    
} // end of class CustomerList