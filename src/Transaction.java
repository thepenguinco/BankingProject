/**
 * A transaction belonging to an account
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-10
 */

public class Transaction
{
	// class fields
	
	/**
	 * The transaction type ID for a deposit
	 */
	public static final int DEPOSIT_ID = 1;

	/**
	 * The transaction type ID for a withdrawal
	 */
	public static final int WITHDRAWAL_ID = 2;
	
	/**
	 * The transaction type ID for processing a cheque
	 */
	public static final int PROCESS_CHEQUE_ID = 3;
	
    // instance fields

    private double amount;
    private double finalBalance;
    private int transactionType;
    
    // constructors
    
    /**
     * Constructs a transaction with the specified characteristics
     * 
     * @param initialBalance the initial balance of this savings account, may not be negative
     */
    public Transaction(int transactionType, double amount, double finalBalance)
    {
    	this.transactionType = transactionType;
    	this.amount = amount;
    	this.finalBalance = finalBalance;
    } // end of constructor Transaction (int transactionType...)
    
    // accessors
    
    /**
     * Returns the amount in this transaction.
     * 
     * @return the amount in this transaction
     */
    public double getAmount()
    {
    	return amount;
    } // end of getAmount()
    
    /**
     * Returns the resultant balance after this transaction.
     * 
     * @return the final balance after this transaction is completed
     */
    public double getFinalBalance()
    {
    	return finalBalance;
    } // end of getFinalBalance()
    
    /**
     * Returns the type of this transaction.
     * 
     * @return the type of this transaction
     */
    public double getTransactionType()
    {
    	return transactionType;
    } // end of getAmount()
    
    // mutators
    
    // other methods, string methods
    
    /**
     * Returns the summary of this transaction
     * 
     * @return the summary of this transaction
     */
    public String getTransactionSummary()
    {
        return "Transaction type: " + transactionType
        + ", Amount transferred: " + amount
        + ", Final balance: " + finalBalance;
    } // end of toString()
    
    /**
     * Shows a string representation of this transaction.
     * 
     * @return the string representation of this transaction
     */
    public String toString()
    {
        return
        getClass().getName() 
        + " ["
        + "Transaction type: " + transactionType
        + ", Amount transferred: " + amount
        + ", Balance after transaction: " + finalBalance
        + "]";
    } // end of toString()
    
} // end of class Transaction