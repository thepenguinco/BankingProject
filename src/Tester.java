import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Tester {
	public static void main(String[] args)
	{
		ChequingAccount acc = new ChequingAccount();
		Customer customer = new Customer("John", "Doe", 300, 2017, 10, 3, acc);
		System.out.println(customer.getChequingAccounts());
		System.out.println(customer.getSavingsAccounts());
		System.out.println(customer);
		CustomerList list = new CustomerList();
		list.addCustomer(customer);
		customer = new Customer("John", "Jack", 210, 2017, 10, 3, acc);
		list.addCustomer(customer);
		customer = new Customer("Jane", "Doe", 201, 2017, 10, 3, acc);
		list.addCustomer(customer);
		System.out.println(list);
		list.sortByName();
		System.out.println(list);
		Transaction transaction = new Transaction(1,10,100);
		list.getCustomer(0).getChequingAccount(0).addTransaction(transaction);
		System.out.println(list);
		/*
		 * QUESTIONS:
		 * - sorting by last name, then first name
		 * - accessor methods needed when not necessary??
		 * - export upon closing
		 * - file io in customerList
		 * - try catch for everything (also in methods?? or just use loops and booleans)
		 * - column 80 issue?
		 */
	}
}
