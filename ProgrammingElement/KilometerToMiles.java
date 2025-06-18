import java.util.*;
public class kilometerToMiles{
	public static void main(String[] args){
	double km;
	Scanner input = new Scanner(System.in);
	System.out.println("Enter kilometer: ");
	km = input.nextInt();
	double unitMile = 1.6;
	double totalMiles = km * unitMile;
	System.out.println("The total miles is " + totalMiles + " mile for the given " + km + " km");

	}
}