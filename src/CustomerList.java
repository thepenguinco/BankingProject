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

//*****************************************************************************|
public class CustomerList
{
	// class fields
	
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
     * Returns the customers in this customer list.
     * 
     * @return the customers in this customer list
     */
    public String getCustomers()
    {
    	String customers = "";
        for (int i = 0; i < customer.size(); i++)
            customers = customers + i + ": " + customer.get(i);
        return customers;
    } // end of getCustomers()  
    
    // mutators
    
    /**
     * Adds a customer to this customer list
     * 
     * @param customer a customer to be added to this customer list
     */
	public void addCustomer(Customer customer) {
		this.customer.add(customer);		
	}
	
    /**
     * Deletes a customer from this customer list
     * 
     * @param customer a customer to be deleted from to this customer list
     */
	// ???
	public void removeCustomer(Customer customer) {
		this.customer.remove(customer);		
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
     * Reads the bank database from a file
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
    
    /**
     * Reads the bank database from a file
     * 
     * @param directory
     * @throws IOException
     */
    public void populateList(String directory) throws IOException
    {
    	FileReader inputStream = null;
    	// TODO
    }
    
    // other methods
    
    /**
     * Shows a string representation of this transaction.
     * 
     * @return the string representation of this transaction
     */
    public String toString()
    {
        return
        getClass().getName() 
        + "["
        + "Customers: " + customer
        + "]";
    } // end of toString()
    
} // end of class CustomerList