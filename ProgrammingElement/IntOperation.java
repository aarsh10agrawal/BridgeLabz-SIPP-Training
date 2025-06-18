import java.util.*;
public class intOperation{
	public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	System.out.println("Enter the value of a, b, and c: ");
	int a = input.nextInt();
	int b = input.nextInt();
	int c = input.nextInt(); 
	int operation1 = a + b * c;
	int operation2 = a * b + c;
	int operation3 = a % b + c;
	System.out.println("The value of operation1, operation2, and operation3 is : " + operation1 + ", " + operation2 + ", " + operation3);
	}

}