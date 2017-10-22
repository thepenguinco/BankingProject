/**
 * A credit card belonging to a customer
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-10
 */
public class CreditCard extends Account
{
	// class fields
	
	/**
	 * The ID of a credit card.
	 */
	public static final int ID = 3;
	
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
     * 
     * @param initialBalance the initial balance of this credit card account
     */
    public CreditCard(double initialBalance)
    {
        super(initialBalance);
    } // end of constructor CreditCard(double initialBalance)
    
    // accessors
    
    /**
     * Returns the string representing the type of this credit card.
     * 
     * @return the string representing the type of this credit card.
     */
    public String getStringType()
    {
    	return "credit card";
    } // end of method getStringType()
    
    /**
     * Returns the numerical type of this credit card.
     * 
     * @return the numerical type of this credit card.
     */
    public int getType()
    {
    	return ID;
    } // end of method getType()
    
    // mutators
  
    // other methods
    
} // end of class CreditCard