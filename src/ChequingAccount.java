/**
 * A chequing account belonging to a customer
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-08
 */

//*****************************************************************************|
public class ChequingAccount
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
	
    private double balance;
    
    // constructors
    
    /**
     * Constructs a chequing account with a zero balance.
     */
    public ChequingAccount()
    {
        balance = 0;
    } // end of constructor ChequingAccount()
    
    /**
     * Constructs a chequing account with a specified balance.
     * 
     * @param initialBalance the initial balance of this chequing account, may not be negative
     */
    public ChequingAccount(int initialBalance)
    {
        balance = initialBalance;
        if (balance < 0) balance = 0;
    } // end of constructor ChequingAccount(int initialBalance)
    
    // accessors
    
    /**
     * Returns the balance of this chequing account.
     * 
     * @return the balance of this chequing account
     */
    public double getBalance()
    {
    	return balance;
    } // end of getBalance()
    
    // mutators

    /**
     * Deposits funds into this chequing account.
     * 
     * @param amount amount to be deposited, may not be negative
     */
    public void depositFunds(int amount)
    {
    	if (amount > 0) balance = balance + amount;
    } // end of depositFunds(int amount)
    
    /**
     * Levies the transaction fee on this chequing account.
     */
    public void levyFee()
    {
    	if (balance > FEE_EXEMPTION_BALANCE) balance = balance - PROCESSING_FEE;
    } // end of levyFee()
    
    /**
     * Processes a cheque from this chequing account.
     * 
     * @param amount amount to be processed, may not be negative
     */
    public void processCheque(int amount)
    {
    	if (amount > 0) balance = balance - amount;
    } // end of withdrawFunds(int amount)
    
    /**
     * Withdraws funds from this chequing account.
     * 
     * @param amount amount to be withdrawn, may not be negative
     */
    public void withdrawFunds(int amount)
    {
    	if (amount > 0) balance = balance - amount;
    } // end of withdrawFunds(int amount)
    
    // other methods
    
    /**
     * Shows a string representation of this ChequingAccount.
     * 
     * @return the string representation of this ChequingAccount
     */
    public String toString()
    {
        return
        getClass().getName() 
        + "["
        + "Current Balance: " + balance
        + "]";
    } // end of toString()
    
} // end of class ChequingAccount