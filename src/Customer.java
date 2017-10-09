/**
 * A bank client who uses the banking services
 * 
 * @author Eric Li 
 * @version 1.0
 */
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
    
    /**
     * Constructs a default Customer object
     */
    public Customer()
    {
    	firstName = "";
    	lastName = "";
    }
    
    /**
     * Constructs a Customer object with the specified characteristics
     */
    public Customer(String firstName, String lastName, int sin, int birthYear, int birthMonth, int birthDay)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sin = sin;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int x, int y)
    {
        // put your code here
        return x + y;
    }
}