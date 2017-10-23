/**
 * A chequing account belonging to a customer.
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-10
 */
public class ChequingAccount extends Account
{
	// class fields
	
	/**
	 * The ID of a chequing account belonging to a customer.
	 */
	public static final int ID = 1;
	
	/**
	 * The minimum balance required to be exempt from the cheque processing fee.
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
    public ChequingAccount(double initialBalance)
    {
    	super(initialBalance);    	
    } // end of constructor ChequingAccount(double initialBalance)
    
    // accessors
    
    /**
     * Returns the string representing the type of this chequing account.
     * 
     * @return the string representing the type of this chequing account
     */
    public String getStringType()
    {
    	return "chequing account";
    } // end of method getStringType()
    
    /**
     * Returns the numerical type of this chequing account.
     * 
     * @return the numerical type of this chequing account
     */
    public int getType()
    {
    	return ID;
    } // end of method getType()
    
    // mutators
    
    /**
     * Processes a cheque from this chequing account.
     * 
     * @param amount amount to be processed, may not be negative
     */
    public void processCheque(double amount)
    {
    	if (amount > 0) this.withdrawFunds(amount);
    	if (this.getBalance() < FEE_EXEMPTION_BALANCE) 
    	{
    		this.withdrawFunds(PROCESSING_FEE);
    	}
    } // end of method withdrawFunds(int amount)
    
    // other methods
    
} // end of class ChequingAccount