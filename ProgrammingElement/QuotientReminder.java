import java.util.*;
public class quotientReminder{
	public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	System.out.println("Enter number1 and number2: ");
	double number1 = input.nextDouble();
	double number2 = input.nextDouble();
	double quotient = number1 / number2;
	double reminder = number1 % number2;
	System.out.println("The Quotient is " + quotient + " and reminder is " + reminder + " of two number " + number1 + " and " + number2);
	}

}