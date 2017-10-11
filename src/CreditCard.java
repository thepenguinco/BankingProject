/**
 * A credit card belonging to a customer
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-10
 */

//*****************************************************************************|
public class CreditCard
{
    // instance fields
	
    private double balance;
    
    // constructors
    
    /**
     * Constructs a credit card account with a zero balance.
     */
    public CreditCard()
    {
        balance = 0;
    } // end of constructor CreditCard()
    
    // accessors
    
    /**
     * Returns the balance of this credit card.
     * 
     * @return the balance of this credit card
     */
    public double getBalance()
    {
    	return balance;
    } // end of getBalance()
    
    // mutators

    /**
     * Funds the balance on this credit card.
     * 
     * @param amount amount to be funded, may not be negative
     */
    public void fundAccount(int amount)
    {
    	if (amount > 0) balance = balance + amount;
    } // end of fundAccount(int amount)
    
    /**
     * Makes a purchase with this credit card.
     * 
     * @param amount purchase amount, may not be negative
     */
    public void makePurchase(int amount)
    {
    	if (amount > 0) balance = balance - amount;
    } // end of makePurchase(int amount)
    
    // other methods
    
    /**
     * Shows a string representation of this credit card.
     * 
     * @return the string representation of this credit card
     */
    public String toString()
    {
        return
        getClass().getName() 
        + "["
        + "Current Balance: " + balance
        + "]";
    } // end of toString()
    
} // end of class CreditCard