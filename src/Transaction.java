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
	public static final int WITHDRAW_ID = 2;
	
	/**
	 * The transaction type ID for processing a cheque
	 */
	public static final int PROCESS_CHEQUE_ID = 3;
	
	/**
	 * The transaction type ID for a purchase using credit card
	 */
	public static final int CREDIT_PURCHASE_ID = 4;
	
	/**
	 * The transaction type ID for making payment to a credit card
	 */
	public static final int CREDIT_PAYMENT_ID = 5;
	
	/**
	 * The transaction type ID for making payment to a credit card
	 */
	public static final int TRANSFER_FUNDS_ID = 6;
	
    // instance fields

    private double amount;
    private double finalBalance;
    private int transactionType;
    
    // constructors
    
    /**
     * Constructs a transaction with the specified characteristics
     * 
     * @param transactionType the type of this transaction
     * @param amount the amount involved in this transaction
     * @param finalBalance the resultant balance of the account this transaction belongs to
     */
    public Transaction(int transactionType, double amount, double finalBalance)
    {
    	this.transactionType = transactionType;
    	this.amount = amount;
    	this.finalBalance = finalBalance;
    } // end of constructor Transaction (int transactionType ... 
    
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
     * Returns the string type of this transaction.
     * 
     * @return the string type of this transaction
     */
    public String getTransactionStringType()
    {
    	if (this.getTransactionType() == DEPOSIT_ID)
    	{
    		return "Deposit";
    	} // end of if (this.getTransactionType ...
    	else if (this.getTransactionType() == WITHDRAW_ID)
    	{
    		return "Withdrawal";
    	} // end of else if (this.getTransactionType ...
		else if (this.getTransactionType() == PROCESS_CHEQUE_ID)
		{
			return "Process Cheque";
		} // end of else if (this.getTransactionType ...
		else if (this.getTransactionType() == CREDIT_PURCHASE_ID)
		{
			return "Credit Card Purchase";
		} // end of else if (this.getTransactionType ...
		else if (this.getTransactionType() == CREDIT_PAYMENT_ID)
		{
			return "Credit Card Payment";
		} // end of else if (this.getTransactionType ...
		else if (this.getTransactionType() == TRANSFER_FUNDS_ID)
		{
			return "Transfer";
		} // end of else if (this.getTransactionType ...
		else
		{
			return "Unknown";
		} // end of else
    } // end of getTransactionStringType()
    
    /**
     * Returns the type of this transaction.
     * 
     * @return the type of this transaction
     */
    public int getTransactionType()
    {
    	return transactionType;
    } // end of getTransactionType()
    
    // mutators
    
    // other methods, string methods
    
    /**
     * Returns the summary of this transaction
     * 
     * @return the summary of this transaction
     */
    public String getTransactionSummary()
    {
        return "Transaction type: " + this.getTransactionStringType()
        + ", Amount transferred: " + Utility.MONEY_FORMAT.format(amount)
        + ", Final balance: " + Utility.MONEY_FORMAT.format(finalBalance);
    } // end of getTransactionSummary()
    
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