/**
 * A transaction belonging to an account.
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-10
 */
public class Transaction
{
	// class fields
	
	/**
	 * The transaction type ID for a deposit.
	 */
	public static final int DEPOSIT_ID = 1;

	/**
	 * The transaction type ID for a withdrawal.
	 */
	public static final int WITHDRAW_ID = 2;
	
	/**
	 * The transaction type ID for processing a cheque.
	 */
	public static final int PROCESS_CHEQUE_ID = 3;
	
	/**
	 * The transaction type ID for a purchase using credit card.
	 */
	public static final int CREDIT_PURCHASE_ID = 4;
	
	/**
	 * The transaction type ID for making payment to a credit card from a savings account.
	 */
	public static final int CREDIT_PAYMENT_FROM_SAVINGS_ID = 5;
	
	/**
	 * The transaction type ID for making payment to a credit card from a chequing account.
	 */
	public static final int CREDIT_PAYMENT_FROM_CHEQUING_ID = 6;
	
	/**
	 * The transaction type ID for making payment to a credit card from a credit card.
	 */
	public static final int PAYMENT_TO_CREDIT_CARD_ID = 7;
	
	/**
	 * The transaction type ID for a transfer from a chequing account.
	 */
	public static final int TRANSFER_FROM_CHEQUING_ID = 8;
	
	/**
	 * The transaction type ID for a transfer to a chequing account.
	 */
	public static final int TRANSFER_TO_CHEQUING_ID = 9;
	
	/**
	 * The transaction type ID for a transfer from a savings account.
	 */
	public static final int TRANSFER_FROM_SAVINGS_ID = 10;
	
	/**
	 * The transaction type ID for a transfer to a savings account.
	 */
	public static final int TRANSFER_TO_SAVINGS_ID = 11;
	
    // instance fields

    private double amount;
    private double finalBalance;
    private double initialBalance;
    private int transactionType;
    
    // constructors
    
    /**
     * Constructs a transaction with the specified characteristics.
     * 
     * @param transactionType the type of this transaction
     * @param initialBalance the initialBalance before this transaction
     * @param amount the amount involved in this transaction
     * @param finalBalance the resultant balance of the account this transaction belongs to
     */
    public Transaction(int transactionType, double initialBalance, double amount, double finalBalance)
    {
    	this.transactionType = transactionType;
    	this.initialBalance = initialBalance;
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
    } // end of method getAmount()
    
    /**
     * Returns the resultant balance after this transaction.
     * 
     * @return the final balance after this transaction is completed
     */
    public double getFinalBalance()
    {
    	return finalBalance;
    } // end of method getFinalBalance()
    
    /**
     * Returns the initial balance before this transaction.
     * 
     * @return the initial balance before this transaction is completed
     */
    public double getInitialBalance()
    {
    	return initialBalance;
    } // end of method getInitialBalance()
    
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
		else if (this.getTransactionType() == CREDIT_PURCHASE_ID)
		{
			return "Credit Card Payment";
		} // end of else if (this.getTransactionType ...
		else if (this.getTransactionType() == TRANSFER_TO_CHEQUING_ID)
		{
			return "Transfer to Chequing Account";
		} // end of else if (this.getTransactionType ...
		else if (this.getTransactionType() == TRANSFER_FROM_CHEQUING_ID)
		{
			return "Transfer from Chequing Account";
		} // end of else if (this.getTransactionType ...
		else if (this.getTransactionType() == TRANSFER_TO_SAVINGS_ID)
		{
			return "Transfer to Savings Account";
		} // end of else if (this.getTransactionType ...
		else if (this.getTransactionType() == TRANSFER_FROM_SAVINGS_ID)
		{
			return "Transfer from Savings Account";
		} // end of else if (this.getTransactionType ...
		else if (this.getTransactionType() == CREDIT_PAYMENT_FROM_CHEQUING_ID)
		{
			return "Credit Payment from Chequing Account";
		} // end of else if (this.getTransactionType ...
		else if (this.getTransactionType() == CREDIT_PAYMENT_FROM_SAVINGS_ID)
		{
			return "Credit Payment from Savings Account";
		} // end of else if (this.getTransactionType ...
		else if (this.getTransactionType() == PAYMENT_TO_CREDIT_CARD_ID)
		{
			return "Payment to Credit Card";
		} // end of else if (this.getTransactionType ...
		else
		{
			return "Unknown";
		} // end of else
    } // end of method getTransactionStringType()
    
    /**
     * Returns the type of this transaction.
     * 
     * @return the type of this transaction
     */
    public int getTransactionType()
    {
    	return transactionType;
    } // end of method getTransactionType()
    
    // mutators
    
    // other methods, string methods
    
    /**
     * Returns the summary of this transaction.
     * 
     * @return the summary of this transaction
     */
    public String getTransactionSummary()
    {
        return "Transaction type: " + this.getTransactionStringType()
        + ", Initial balance: $" + Utility.MONEY_FORMAT.format(initialBalance)
        + ", Amount: $" + Utility.MONEY_FORMAT.format(amount)
        + ", Final balance: $" + Utility.MONEY_FORMAT.format(finalBalance);
    } // end of method getTransactionSummary()
    
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
        + "Initial balance: " + initialBalance
        + ", Amount transferred: " + amount
        + ", Balance after transaction: " + finalBalance
        + "]";
    } // end of method toString()
} // end of class Transaction