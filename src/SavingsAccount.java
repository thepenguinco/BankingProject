/**
 * A savings account belonging to a customer
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-10
 */
public class SavingsAccount extends Account
{
	// class fields
	
	/**
	 * The ID of a savings account belonging to a customer
	 */
	public static final int ID = 2;
	
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
    public SavingsAccount(double initialBalance)
    {
        super(initialBalance);
    } // end of constructor SavingsAccount(double initialBalance)
    
    // accessors
    
    /**
     * Returns the string representing the type of this savings account.
     * 
     * @return the string representing the type of this savings account.
     */
    public String getStringType()
    {
    	return "savings account";
    } // end of method getStringType()
    
    /**
     * Returns the numerical type of this savings account.
     * 
     * @return the numerical type of this savings account.
     */
    public int getType()
    {
    	return ID;
    } // end of method getType()
    
    // other methods
        
} // end of class SavingsAccount