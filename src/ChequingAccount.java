/**
 * A chequing account belonging to a customer
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-10
 */

public class ChequingAccount extends Account
{
	// class fields
	
	/**
	 * Minimum balance required to be exempt from the cheque processing fee
	 */
	public static final double FEE_EXEMPTION_BALANCE = 1000;
	
	/**
	 * Cheque processing fee
	 */
	public static final double PROCESSING_FEE = 0.15;
	
    // instance fields
    
    // constructors
    
    /**
     * Constructs a chequing account with a zero balance.
     */
    public ChequingAccount()
    {
        super();
    } // end of constructor ChequingAccount()
    
    /**
     * Constructs a chequing account with a specified balance.
     * 
     * @param initialBalance the initial balance of this chequing account,
     * may not be negative
     */
    public ChequingAccount(int initialBalance)
    {
    	super(initialBalance);    	
    } // end of constructor ChequingAccount(int initialBalance)
    
    // accessors
        
    // mutators
    
    /**
     * Processes a cheque from this chequing account.
     * 
     * @param amount amount to be processed, may not be negative
     */
    public void processCheque(int amount)
    {
    	if (amount > 0) this.withdrawFunds(amount);
    	if (this.getBalance() < FEE_EXEMPTION_BALANCE) 
    	{
    		this.withdrawFunds(PROCESSING_FEE);
    	}
    } // end of withdrawFunds(int amount)
    
    // other methods
    
} // end of class ChequingAccount