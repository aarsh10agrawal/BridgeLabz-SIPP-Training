import java.util.*;
public class intOperationDouble{
	public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	System.out.println("Enter the value of a, b, and c: ");
	double a = input.nextDouble();
	double b = input.nextDouble();
	double c = input.nextDouble();
	double operation1 = a + b * c;
	double operation2 = a * b + c;
	double operation3 = a % b + c;
	System.out.println("The value of operation1, operation2, and operation3 is : " + operation1 + ", " + operation2 + ", " + operation3);
	}

}