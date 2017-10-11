/**
 * A savings account belonging to a customer
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-08
 */

//*****************************************************************************|
public class SavingsAccount
{
    /*
     * Instance fields
     */
	
    private double balance;
    
    /*
     * Constructors
     */
    
    /**
     * Constructs a savings account with a zero balance.
     */
    public SavingsAccount()
    {
        balance = 0;
    } // end of constructor SavingsAccount()
    
    /**
     * Constructs a savings account with a specified balance.
     * 
     * @param initialBalance the initial balance of this savings account, may not be negative
     */
    public SavingsAccount(int initialBalance)
    {
        balance = initialBalance;
        if (balance < 0) balance = 0;
    } // end of constructor SavingsAccount(int initalBalance)
    
    /*
     * Accessors
     */
    
    /**
     * Returns the balance of this savings account.
     * 
     * @return the balance of this savings account
     */
    public double getBalance()
    {
    	return balance;
    } // end of getBalance()
    
    /*
     * Mutators
     */

    /**
     * Deposits funds into the savings account.
     * 
     * @param amount amount to be deposited, may not be negative
     */
    public void depositFunds(int amount)
    {
    	if (amount > 0) balance = balance + amount;
    } // end of depositFunds(int amount)
    
    /**
     * Withdraws funds from the savings account.
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
    
    /**
     * Shows a string representation of this savings account.
     * 
     * @return the string representation of this savings account
     */
    public String toString()
    {
        return
        getClass().getName() 
        + "["
        + "Current Balance: " + balance
        + "]";
    } // end of toString()
    
} // end of class SavingsAccount