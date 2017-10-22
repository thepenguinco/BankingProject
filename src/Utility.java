import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * A utility class for formatting and parsing purposes
 * 
 * @author Eric Li 
 * @version 1.0 2017-10-20
 */
public class Utility 
{
	/**
	 * Format money to two decimal places to reflect banking on Earth.
	 */
	public static final NumberFormat MONEY_FORMAT = new DecimalFormat("#0.00");
} // end of class Utility
