import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * A bank client who uses the banking services
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-12
 */

public class Customer
{
	// class fields

    // instance fields
    
	private ArrayList<Account> accountList;
    private int birthDay;
    private int birthMonth;
    private int birthYear;
    private String firstName;
    private String lastName;
    private int sin;
    
    // constructors
    
    /**
     * Constructs a customer with specified characteristics with no accounts
     * 
     * @param firstName the first name of this customer
     * @param lastName the last name of this customer
     * @param sin the social insurance number of this customer
     * @param birthYear the year this customer was born in
     * @param birthMonth the month this customer was born in
     * @param birthDay the day of the month this customer was born on
     */
    public Customer(String firstName, String lastName, int sin, 
            int birthYear, int birthMonth, int birthDay)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sin = sin;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.accountList = new ArrayList<Account>();
    } // end of constructor Customer(String firstName ...
    
    /**
     * Constructs a customer with specified characteristics
     * and an initial account
     * 
     * @param firstName the first name of this customer
     * @param lastName the last name of this customer
     * @param sin the social insurance number of this customer
     * @param birthYear the year this customer was born in
     * @param birthMonth the month this customer was born in
     * @param birthDay the day of the month this customer was born on
     * @param account the initial account of this customer
     */
    public Customer(String firstName, String lastName, int sin, 
            int birthYear, int birthMonth, int birthDay, Account account)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sin = sin;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.accountList = new ArrayList<Account>();
        this.accountList.add(account);
    } // end of constructor Customer(String firstName ...
    
    // accessors

    /**
     * Returns the accounts of this customer
     * 
     * @return the accounts of this customer
     */
    public ArrayList<Account> getAccounts()
    {
    	return accountList;
    } // end of getAccounts()
    
    /**
     * Returns the age of this customer
     * 
     * @return the age of this customer
     */
    public long getAge()
    {
        LocalDate birthDate = LocalDate.of(birthYear,
        		birthMonth, birthDay);
        LocalDate today = LocalDate.now();
        return ChronoUnit.YEARS.between(birthDate, today);
        
    } // end of getAge()
    
    /**
     * Returns the birth day of this customer.
     * 
     * @return the birth day of this customer 
     */
    public int getBirthDay()
    {
        return birthDay;
    } // end of getBirthDay()
    
    /**
     * Returns the birth month of this customer.
     * 
     * @return the birth month of this customer 
     */
    public int getBirthMonth()
    {
        return birthMonth;
    } // end of getBirthMonth()
    
    /**
     * Returns the birth year of this customer.
     * 
     * @return the birth year of this customer 
     */
    public int getBirthYear()
    {
        return birthYear;
    } // end of getBirthYear()
    
    /**
     * Returns the chequing accounts of this customer.
     * 
     * @return the chequing accounts of this customer
     */
    public ArrayList<Account> getChequingAccounts()
    {
    	ArrayList<Account> chequingAccounts = new ArrayList<Account>();
    	for (Account account : accountList)
    	{
    		if (account.getType() == ChequingAccount.ID)
    		{
    			chequingAccounts.add(account);
    		}
    	}
    	return chequingAccounts;
    } // end of getChequingAccounts()
    
    /**
     * Returns the credit card accounts of this customer.
     * 
     * @return the credit cards accounts of this customer
     */
    public ArrayList<Account> getCreditCards()
    {
    	ArrayList<Account> creditCards = new ArrayList<Account>();
    	for (Account account : accountList)
    	{
    		if (account.getType() == CreditCard.ID)
    		{
    			creditCards.add(account);
    		}
    	}
    	return creditCards;
    } // end of getCreditCards()
    
    /**
     * Returns the first name of this customer.
     * 
     * @return the first name of this customer 
     */
    public String getFirstName()
    {
        return firstName;
    } // end of getFirstName()
        
    /**
     * Returns the last name of this customer.
     * 
     * @return the last name of this customer 
     */
    public String getLastName()
    {
        return lastName;
    } // end of getLastName()
    
    /**
     * Returns the chequing and savings accounts of this customer.
     * 
     * @return the chequing and savings accounts of this customer
     */
    public ArrayList<Account> getPrimaryAccounts()
    {
    	ArrayList<Account> primaryAccounts = new ArrayList<Account>();
    	for (Account account : accountList)
    	{
    		if (account.getType() == ChequingAccount.ID || account.getType() == SavingsAccount.ID)
    		{
    			primaryAccounts.add(account);
    		}
    	}
    	return primaryAccounts;
    } // end of getPrimaryAccounts()
    
    /**
     * Returns the savings accounts of this customer.
     * 
     * @return the savings accounts of this customer
     */
    public ArrayList<Account> getSavingsAccounts()
    {
    	ArrayList<Account> savingsAccounts = new ArrayList<Account>();
    	for (Account account : accountList)
    	{
    		if (account.getType() == SavingsAccount.ID)
    		{
    			savingsAccounts.add(account);
    		}
    	}
    	return savingsAccounts;
    } // end of getSavingsAccounts()
    
    /**
     * Returns the SIN of this customer.
     * 
     * @return the SIN of this customer 
     */
    public int getSin()
    {
        return sin;
    } // end of getSin()
    
    // mutators
    
    /**
     * Adds an account to this customer.
     * 
     * @param account the account to be added to this customer
     */
    public void addAccount(Account account)
    {
        accountList.add(account);
    } // end of addAccount(Account account)

    /**
     * Removes an account from this customer.
     * 
     * @param account the account to be remove from this customer
     */
    public void removeAccount(Account account)
    {
        accountList.remove(account);
    } // end of removeAccount(Account account)
    
    /**
     * Sets the birth day of this customer.
     * 
     * @param birthDay the birth day of this customer 
     */
    public void setBirthDay(int birthDay)
    {
        this.birthDay = birthDay;
    } // end of setBirthDay(int birthDay)
    
    /**
     * Sets the birth month of this customer.
     * 
     * @param birthMonth the birth month of this customer 
     */
    public void setBirthMonth(int birthMonth)
    {
        this.birthMonth = birthMonth;
    } // end of setBirthMonth(int birthMonth)
    
    /**
     * Returns the birth year of this customer.
     * 
     * @param birthYear the birth year of this customer 
     */
    public void setBirthYear(int birthYear)
    {
        this.birthYear = birthYear;
    } // end of setBirthYear(int birthYear)
        
    /**
     * Sets the first name of this customer.
     * 
     * @param firstName the first name of this customer 
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    } // end of setFirstName(String firstName)
        
    /**
     * Sets the last name of this customer.
     * 
     * @param lastName the last name of this customer 
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    } // end of setLastName(String lastName)
    
    /**
     * Sets the SIN of this customer.
     * 
     * @param sin the SIN of this customer 
     */
    public void setSin(int sin)
    {
        this.sin = sin;
    } // end of setSin(int sin)
    
    // other methods, string methods
	    
	/**
	 * Shows the basic summary of this customer.
	 * 
	 * @return the string containing the full name and SIN of this customer
	 */
	public String getSummary() 
	{
		return this.lastName + ", " + this.firstName + " " + this.sin;
	}
    
    /**
     * Shows a string representation of this customer.
     * 
     * @return the string representation of this customer
     */
	public String toString() 
	{
		return 
		getClass().getName() 
        + "["
        + "First Name: " + firstName
        + ", Last Name: " + lastName
        + ", Birthdate: " + birthYear + "-" + birthMonth + "-" + birthDay
        + ", SIN: " + sin
        + ", Accounts" + accountList
        + "]";
	} // end of toString()

	// comparators
	
	/**
	 * Compares two customers by SIN.
	 */
    public static final Comparator<Customer> compareBySin = new Comparator<Customer>()
    {
        @Override
        public int compare(Customer customer1, Customer customer2) {
            return customer1.sin - customer2.sin;
        }
    }; // end of Comparator<Customer> compareBySin ...
    
    /**
     * Compares two customers by last name, then first name.
     */
    public static final Comparator<Customer> compareByName = new Comparator<Customer>()
    {
        @Override
        public int compare(Customer customer1, Customer customer2) {
        	// first sort by last name
        	int result = customer1.getLastName().compareToIgnoreCase(customer2.getLastName()); 
            // then sort by first name if last names are equivalent
            if (result == 0) result = customer1.getFirstName().compareToIgnoreCase(customer2.getFirstName());
            return result;
        }
    }; // end of Comparator<Customer> compareByName ...
} // end of class Customer