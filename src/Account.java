import java.util.ArrayList;
import java.util.Collections;

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
	 * The transaction history display limit.
	 */
	public static final int TRANSACTION_HISTORY = 5;
	
    // instance fields
	
    private double balance;
    private ArrayList<Transaction> transactionList;
    
    // constructors
    
    /**
     * Constructs an account with a zero balance.
     */
    public Account()
    {
        balance = 0;
        transactionList = new ArrayList<Transaction>();
    } // end of constructor Account()
    
    /**
     * Constructs an account with a specified balance.
     * 
     * @param initialBalance the initial balance of this account
     */
    public Account(double initialBalance)
    {
        balance = initialBalance;
        transactionList = new ArrayList<Transaction>();
    } // end of constructor Account(int initalBalance)
    
    // accessors
    
    /**
     * Returns the numerical type of this account
     * 
     * @return the numerical type of this account
     */
    public abstract int getType();
    
    /**
     * Returns the string type of this account
     * 
     * @return the string type of this account
     */    
    public abstract String getStringType();
    
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
    	transactionList.add(0, transaction);
    } // end of addTransaction(Transaction transaction)
        
    /**
     * Adds funds into this account.
     * 
     * @param amount amount to be deposited, may not be negative
     */
    public void depositFunds(double amount)
    {
    	balance = balance + amount;
    } // end of depositFunds(double amount)
    
    /**
     * Removes funds from this account.
     * 
     * @param amount amount to be withdrawn, may not be negative
     */
    public void withdrawFunds(double amount)
    {
    	balance = balance - amount;
    } // end of withDrawFunds(double amount)
    
    // other methods
    
    /**
     * Returns the last 5 transactions of this account
     * 
     * @return the last 5 transactions of this account
     */
    public String getTransactionHistory()
    {
    	String transactions = "";
        for (int i = 0; i < transactionList.size(); i++)
        {
        	if (i >= TRANSACTION_HISTORY) break;
            transactions = transactions + (i + 1) + ": " + transactionList.get(i).getTransactionSummary() + "\n";
        }
        return transactions;
    } // end of getTransactionHistory()
    
    /**
     * Returns the list of transactions of this account
     * 
     * @return the list of transactions of this account
     */
    public ArrayList<Transaction> getTransactions()
    {
        return transactionList;
    } // end of getTransactions()
    
    /**
     * Returns the list of transactions of this account in reverse order for file IO.
     * 
     * @return the list of transactions of this account in reverse order
     */
    public ArrayList<Transaction> getTransactionsReversed()
    {
    	// dirty workaround
    	ArrayList<Transaction> reversedTransactions = (ArrayList<Transaction>) transactionList.clone();
    	Collections.reverse(reversedTransactions);
        return reversedTransactions;
    } // end of getTransactionsReversed()
    
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
        + ", Transactions: " + transactionList
        + "]";
    } // end of toString()
} // end of class Account