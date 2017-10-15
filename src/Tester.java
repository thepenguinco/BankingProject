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
		customer = new Customer("Zoey", "Jack", 210, 2017, 10, 3, acc);
		list.addCustomer(customer);
		customer = new Customer("Jane", "Doe", 201, 2017, 10, 3, acc);
		list.addCustomer(customer);
		System.out.println(list);
		list.sortBySin();
		System.out.println(list);
		
	}
}
