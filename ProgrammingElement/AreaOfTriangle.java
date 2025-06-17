import java.util.*;
public class areaOfTriangle{
	public static void main(String[] args){
	double base, height;
	Scanner input = new Scanner(System.in);
	System.out.println("Enter base: ");
	base = input.nextDouble();
	System.out.println("Enter height: ");
	height = input.nextDouble();
	double areaOfTriangle = 0.5 * base * height;
	System.out.println("The base and the height of the triangle is " + base + ", and " + height + " and its area is " + areaOfTriangle);

	}
}