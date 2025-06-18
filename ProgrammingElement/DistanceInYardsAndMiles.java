import java.util.*;
public class totalPrice{
public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	System.out.println("Enter the unit price of the item: ");
	double unitPrice = nextDouble();
	System.out.println("Enter the purchased quantity: ");
	int quantity = nextInt();
	double totalPrice = quantity * unitPrice;
	System.out.println("The total purchase price is INR " + totalPrice + " if the quantity " + quantity + " and unit price is INR " + unitPrice);
	}

}