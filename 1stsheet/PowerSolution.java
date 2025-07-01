import java.util.Scanner;
public class Main
{
	public static void main(String[] args) {
	    double PowerSolution;
	    Scanner sc = new Scanner(System.in);
	    double base = sc.nextDouble();
	    double exponent = sc.nextDouble();
	    PowerSolution= Math.pow(base,exponent);
	    System.out.print("Power Solution of given numbers are: "+ PowerSolution);

	}
}
