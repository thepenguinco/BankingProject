/**
 * A savings account belonging to a customer
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-08
 */
public class SavingsAccount
{
    /*
     * Instance fields
     */
	
    private int balance;
    
    /*
     * Constructors
     */
    
    /**
     * Constructs a default SavingsAccount object
     */
    public SavingsAccount()
    {
        balance = 0;
    }
    
    /**
     * Constructs a SavingsAccount object with a specificed balance
     */
    public SavingsAccount(int initialBalance)
    {
        balance = initialBalance;
    }
    
    /*
     * Accessors
     */
    
    /**
     * Deposits funds into the savings account
     * 
     */
    public int getBalance()
    {
    	return balance;
    } // end of getBalance()
    
    /*
     * Mutators
     */

    /**
     * Deposits funds into the savings account
     * 
     * @param amount amount to be deposited, may not be negative
     */
    public void depositFunds(int amount)
    {
    	if (amount > 0) balance = balance + amount;
    } // end of depositFunds(int amount)
    
    /**
     * Withdraws funds from the savings account
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