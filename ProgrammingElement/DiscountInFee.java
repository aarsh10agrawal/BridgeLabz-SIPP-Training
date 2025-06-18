import java.util.*;
public class discountInFee{
	public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	double fee, discountPercent;
	System.out.println("Enter fee: ");
	fee = input.nextDouble();
	System.out.println("Enter discount percent: ");
	discountPercent = input.nextDouble();
	double discount = fee * (discountPercent/100);
	double discountedFee = fee - discount;
	System.out.println("The discount amount is INR " + discount + " and final discounted fee is INR " + discountedFee);

	}
}