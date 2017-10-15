/**
 * A savings account belonging to a customer
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-10
 */

//*****************************************************************************|
public class SavingsAccount extends Account
{
    // instance fields
	
    // constructors
    
    /**
     * Constructs a savings account with a zero balance.
     */
    public SavingsAccount()
    {
        super();
    } // end of constructor SavingsAccount()
    
    /**
     * Constructs a savings account with a specified balance.
     * 
     * @param initialBalance the initial balance of this savings account, may not be negative
     */
    public SavingsAccount(int initialBalance)
    {
        super(initialBalance);
    } // end of constructor SavingsAccount(int initalBalance)
    
    // accessors
    
    // other methods
        
} // end of class SavingsAccount