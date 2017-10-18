import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Tester 
{
	public static void main(String[] args)
	{
		ChequingAccount acc = new ChequingAccount();
		Customer customer = new Customer("John", "Doe", 300, 2017, 10, 3, 0, 300);
		System.out.println(customer.getChequingAccounts());
		System.out.println(customer.getSavingsAccounts());
		System.out.println(customer);
		CustomerList list = new CustomerList();
		list.addCustomer(customer);
		customer = new Customer("John", "Jack", 210, 2017, 10, 3, 1, 300);
		list.addCustomer(customer);
		customer = new Customer("Jane", "Doe", 201, 2017, 10, 3, 1, 400);
		list.addCustomer(customer);
		System.out.println(list);
		list.sortBySin();
		System.out.println(list);
		list.getCustomer(0).getChequingAccount(0).addTransaction(1,10,100);
		list.getCustomer(0).getChequingAccount(0).addTransaction(1,20,100);

		list.getCustomer(0).getChequingAccount(0).addTransaction(1,20,400);

		list.getCustomer(0).getChequingAccount(0).addTransaction(1,20,400);

		list.getCustomer(0).getChequingAccount(0).addTransaction(1,20,400);

		list.getCustomer(0).getChequingAccount(0).addTransaction(1,20,400);
		System.out.println(list);

		System.out.println(list.getCustomer(0).getChequingAccount(0).getTransactions());
		/*
		 * QUESTIONS
		 * - instantiate other objects within private class methods or instantiate in main method
		 * - try catch for everything (also in methods?? or just use loops and booleans)
		 */
	}
}
