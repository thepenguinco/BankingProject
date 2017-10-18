import java.util.ArrayList;

/**
 * An abstract account belonging to a customer
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-10
 */

public abstract class Account
{
	// class fields
	
	/**
	 * Transaction history limit for display
	 */
	public static final int TRANSACTION_HISTORY = 5;
	
    // instance fields
	
    private double balance;
    private ArrayList<Transaction> transaction;
    
    // constructors
    
    /**
     * Constructs an account with a zero balance.
     */
    public Account()
    {
        balance = 0;
        transaction = new ArrayList<Transaction>();
    } // end of constructor Account()
    
    /**
     * Constructs an account with a specified balance.
     * 
     * @param initialBalance the initial balance of this account
     */
    public Account(int initialBalance)
    {
        balance = initialBalance;
        transaction = new ArrayList<Transaction>();
    } // end of constructor Account(int initalBalance)
    
    // accessors
    
    /**
     * Returns the balance of this account.
     * 
     * @return the balance of this account
     */
    public double getBalance()
    {
    	return balance;
    } // end of getBalance()
    
    // mutators

    /**
     * Adds a transaction into this account.
     * 
     * @param transaction transaction to be added to this account
     */
    public void addTransaction(Transaction transaction)
    {
    	this.transaction.add(0, transaction);
    } // end of addTransaction(Transaction transaction)
    
    /**
     * Adds funds into this account.
     * 
     * @param amount amount to be deposited, may not be negative
     */
    public void depositFunds(double amount)
    {
    	if (amount > 0) balance = balance + amount;
    } // end of depositFunds(int amount)
    
    /**
     * Removes funds from this account.
     * 
     * @param amount amount to be withdrawn, may not be negative
     */
    public void withdrawFunds(double amount)
    {
    	if (amount > 0) balance = balance - amount;
    } // end of withDrawFunds(int amount)
    
    // other methods
    
    /**
     * Returns the last 5 transactions of this account
     * 
     * @return the last 5 transactions of this account
     */
    public String getTransactions()
    {
    	String transactions = "";
        for (int i = 0; i < transaction.size(); i++)
        {
        	if (i >= TRANSACTION_HISTORY) break;
            transactions = transactions + i + ": " + transaction.get(i).getTransactionSummary() + "\n";
        }
        return transactions;
    } // end of getTransactions()
    
    /**
     * Shows a string representation of this account.
     * 
     * @return the string representation of this account
     */
    public String toString()
    {
        return
        getClass().getName() 
        + " ["
        + "Current Balance: " + balance
        + ", Transactions: " + transaction
        + "]";
    } // end of toString()
    
} // end of class Account