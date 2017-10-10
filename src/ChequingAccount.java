/**
 * A chequing account belonging to a customer
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-08
 */
public class ChequingAccount
{
	/*
	 * Class fields
	 */
	
	public static final double FEE_EXEMPTION_BALANCE = 1000;
	public static final double PROCESSING_FEE = 0.15;
	
    /*
     * Instance fields
     */
	
    private int balance;
    
    /*
     * Constructors
     */
    
    /**
     * Constructs a chequing account with default characteristics
     */
    public ChequingAccount()
    {
        balance = 0;
    }
    
    /**
     * Constructs a chequing account with a specified balance
     */
    public ChequingAccount(int initialBalance)
    {
        balance = initialBalance;
    }
    
    /*
     * Accessors
     */
    
    /**
     * Returns the balance of this chequing account
     * 
     * @return balance the balance of this chequing account
     */
    public int getBalance()
    {
    	return balance;
    } // end of getBalance()
    
    /*
     * Mutators
     */

    /**
     * Deposits funds into this savings account
     * 
     * @param amount amount to be deposited, may not be negative
     */
    public void depositFunds(int amount)
    {
    	if (amount > 0) balance = balance + amount;
    } // end of depositFunds(int amount)
    
    /**
     * Withdraws funds from this savings account
     * 
     * @param amount amount to be withdrawn, may not be negative
     */
    public void withdrawFunds(int amount)
    {
    	if (amount > 0) balance = balance - amount;
    } // end of withDrawFunds(int amount)
    
    /*
     * Other methods
     */
    
}