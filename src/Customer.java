/**
 * A bank client who uses the banking services
 * 
 * @author Eric Li 
 * @version 1.0
 */

//*****************************************************************************|
public class Customer
{
    // instance variables
    
	private String firstName;
    private String lastName;
    private int sin;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private ChequingAccount chequingAccount;
    private SavingsAccount savingsAccount;
    private CreditCard creditCard;
    
    // constructors
    
    /**
     * Constructs a Customer with default characteristics
     */
    public Customer()
    {
    	// TODO
    }
    
    /**
     * Constructs a customer with specified characteristics
     * and an initial savings account
     * 
     * @param firstName the first name of this customer
     * @param lastName the last name of this customer
     * @param sin the social insurance number of this customer
     * @param birthYear the year this customer was born in
     * @param birthMonth the month this customer was born in
     * @param birthDay the day of the month this customer was born on
     * @param savingsAccount the savings account of this customer
     */
    public Customer(String firstName, String lastName, int sin, 
    		int birthYear, int birthMonth, int birthDay, SavingsAccount savingsAccount)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sin = sin;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.savingsAccount = savingsAccount;
    }
    
    // TODO
    
    // accessors

    /**
     * Returns the first name of the customer
     * 
     * @return the first name of the customer 
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    // TODO
}