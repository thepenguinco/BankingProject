/**
 * A credit card belonging to a customer
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-10
 */

public class CreditCard extends Account
{
    // instance fields
    
    // constructors
    
    /**
     * Constructs a credit card account with a zero balance.
     */
    public CreditCard()
    {
        super();
    } // end of constructor CreditCard()
    
    /**
     * Constructs a credit card account with the specified balance.
     */
    public CreditCard(double initialBalance)
    {
        super(initialBalance);
    } // end of constructor CreditCard(double initialBalance)
    
    // accessors
    
    public String getType()
    {
    	return "Credit Card";
    } // end of getType()
    
    // mutators
  
    // other methods
    
} // end of class CreditCard