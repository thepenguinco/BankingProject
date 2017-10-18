import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Tester 
{
	public static void main(String[] args)
	{
		ChequingAccount acc = new ChequingAccount();
		CustomerList list = new CustomerList()
		CustomerList list = new CustomerList();
		System.out.println(list);
		list.sortBySin();
		System.out.println(list);

		System.out.println(list);

		System.out.println(list.getCustomer(0).getChequingAccount(0).getTransactions());
		/*
		 * QUESTIONS
		 * - instantiate other objects within private class methods or instantiate in main method
		 * - try catch for everything (also in methods?? or just use loops and booleans)
		 */
	}
}
