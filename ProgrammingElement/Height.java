import java.util.*;
public class height{
	public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	double height;
	System.out.println("Enter height in cm: ");
	height = input.nextDouble();
	double heightInFeet = height / 30.48;
	double heightInInches = height / 2.54;
	System.out.println("Your height in cm is " + height + " while in feet is " + heightInFeet + " and in inches is " + heightInInches);

	}
}