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
	 * 
	 */
	
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
    
    /**
     * Constructs a customer list from a specified file database
     * 
     * @throws IOException
     */
    public CustomerList(String fileName) throws IOException
    {
    	this.customer = new ArrayList<Customer>();
    } // end of constructor CustomerList()
    
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
			int birthMonth, int birthDay, int accountID, int initialBalance) {
		this.customer.add(new Customer(firstName, lastName, sin,
		                    		birthYear, birthMonth, birthDay, accountID, initialBalance));
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