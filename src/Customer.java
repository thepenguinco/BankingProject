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
	
	/**
	 * The string ID of a chequing account belonging to a customer
	 */
	public static final String CHEQUING_ID = "Chequing";
	
	/**
	 * The string ID of a savings account belonging to a customer
	 */
	public static final String SAVINGS_ID = "Savings";
	
	/**
	 * The string ID of a chequing account
	 */
	public static final String CREDIT_CARD_ID = "Credit";
	
    // instance fields
    
    private int birthDay;
    private int birthMonth;
    private int birthYear;
    private ArrayList<Account> chequingAccount;
    private ArrayList<Account> creditCard;
    private String firstName;
    private String lastName;
    private ArrayList<Account> savingsAccount;
    private int sin;
    
    // constructors
    
    /**
     * Constructs a customer with specified characteristics
     * and an initial account and initial balance
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
        this.chequingAccount = new ArrayList<Account>();
        this.creditCard = new ArrayList<Account>();
        this.savingsAccount = new ArrayList<Account>();
    } // end of constructor Customer(String firstName ...
    
    /**
     * Constructs a customer with specified characteristics
     * and an initial account and initialBalance
     * 
     * @param firstName the first name of this customer
     * @param lastName the last name of this customer
     * @param sin the social insurance number of this customer
     * @param birthYear the year this customer was born in
     * @param birthMonth the month this customer was born in
     * @param birthDay the day of the month this customer was born on
     * @param accountID the integer representing the initial account type
     * @param initialBalance initialBalance of the account
     */
    public Customer(String firstName, String lastName, int sin, 
            int birthYear, int birthMonth, int birthDay, String accountID, int initialBalance)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sin = sin;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.chequingAccount = new ArrayList<Account>();
        this.creditCard = new ArrayList<Account>();
        this.savingsAccount = new ArrayList<Account>();
        if (accountID.equals(CHEQUING_ID)) chequingAccount.add(new ChequingAccount(initialBalance));
        else if (accountID.equals(SAVINGS_ID)) savingsAccount.add(new SavingsAccount(initialBalance));
        else if (accountID.equals(CREDIT_CARD_ID)) creditCard.add(new CreditCard(initialBalance));
    } // end of constructor Customer(String firstName ...
    
    // TODO
    
    // accessors

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
     * Returns the specified chequing account of this customer.
     * 
     * @return the specified chequing account of this customer
     */
    public Account getChequingAccount(int index)
    {
    	return chequingAccount.get(index);
    } // end of getChequingAccount()
    
    /**
     * Returns the specified credit card of this customer.
     * 
     * @return the specified credit card of this customer
     */
    public Account getCreditCard(int index)
    {
    	return creditCard.get(index);
    } // end of getCreditCard()
        
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
        return firstName;
    } // end of getLastName()
    
    /**
     * Returns the specified savings account of this customer.
     * 
     * @return the specified savings account of this customer
     */
    public Account getSavingsAccount(int index)
    {
    	return savingsAccount.get(index);
    } // end of getSavingsAccount()
    
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
     * Adds a chequing account to this customer.
     * 
     * @param initialBalance the initial balance of the chequing account
     */
    public void addChequingAccount(int initialBalance)
    {
    	chequingAccount.add(new ChequingAccount(initialBalance));
    } // end of addChequingAccount(int initialBalance)
    
    /**
     * Adds a credit card to this customer.
     * 
     */
    public void addCreditCard()
    {
    	creditCard.add(new CreditCard());
    } // end of addCreditCard()
    
    /**
     * Adds a savings account to this customer.
     * 
     * @param initialBalance the initial balance of this savings account
     */
    public void addSavingsAccount(int initialBalance)
    {
    	savingsAccount.add(new SavingsAccount(initialBalance));
    } // end of addSavingsAccount(int initialBalance)
    
    /**
     * Removes a chequing account from this customer with the specified index.
     * 
     * @param index the index of the chequing account
     */
    public void removeChequingAccount(int index)
    {
    	chequingAccount.remove(index);
    } // end of removeChequingAccount()
    
    /**
     * Removes a credit card from this customer with the specified index.
     * 
     * @param index the index of the credit card
     */
    public void removeCreditCard(int index)
    {
    	creditCard.remove(index);
    } // end of removeCreditCard()
    
    /**
     * Adds a savings account to this customer.
     * 
     * @param initialBalance the initial balance of this savings account
     */
    public void removeSavingsAccount(int index)
    {
    	savingsAccount.remove(index);
    } // end of addSavingsAccount(int initialBalance)

    
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
        this.birthYear= birthYear;
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
	public String getSummary() {
		return this.lastName + ", " + this.firstName + " " + this.sin + "\n";
	}
	
    /**
     * Returns the chequing accounts of this customer.
     * 
     * @return the chequing accounts of this customer
     */
    public String getChequingAccounts()
    {
    	String accounts = "";
        for (int i = 0; i < chequingAccount.size(); i++)
            accounts = accounts + i + ": " + chequingAccount.get(i);
        return accounts;
    } // end of getChequingAccounts()
	
    /**
     * Returns the credit card accounts of this customer.
     * 
     * @return the credit cards accounts of this customer
     */
    public String getCreditCards()
    {
    	String accounts = "";
        for (int i = 0; i < creditCard.size(); i++)
            accounts = accounts + i + ": " + creditCard.get(i);
        return accounts;
    } // end of getCreditCards()
	
    /**
     * Returns the savings accounts of this customer.
     * 
     * @return the savings accounts of this customer
     */
    public String getSavingsAccounts()
    {
    	String accounts = "";
        for (int i = 0; i < savingsAccount.size(); i++)
            accounts = accounts + i + ": " + savingsAccount.get(i);
        return accounts;
    } // end of getSavingsAccounts()
    
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
        + ", Chequing Accounts" + chequingAccount
        + ", Credit Cards" + creditCard
        + ", Savings Accounts" + savingsAccount
        + "]";
	} // end of toString()

	// comparators
	
	/**
	 * Compares two customers by SIN
	 */
    public static final Comparator<Customer> compareBySin = new Comparator<Customer>(){
        @Override
        public int compare(Customer customer1, Customer customer2) {
            return customer1.sin - customer2.sin;
        }
    }; // end of Comparator<Customer> compareBySin...
    
    /**
     * Compares two customers by first name, then last name
     */
    public static final Comparator<Customer> compareByName = new Comparator<Customer>(){
        @Override
        public int compare(Customer customer1, Customer customer2) {
        	// first sort by first name
        	int result = customer1.getFirstName().compareToIgnoreCase(customer2.getFirstName());
            // then sort by last name if first names are equivalent
            if (result == 0) result = customer1.getLastName().compareToIgnoreCase(customer2.getLastName());
            return result;
        }
    }; // end of Comparator<Customer> compareByName...
    
} // end of class Customer