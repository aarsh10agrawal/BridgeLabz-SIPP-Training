import java.util.Scanner;
public class Main
{
	public static void main(String[] args) {
	    double Miles;
	    Scanner sc = new Scanner(System.in);
	    double Kilometers = sc.nextDouble();
	    Miles= Kilometers * 0.621371;
	    System.out.print("Kilometers to Miles: "+ Miles);

	}
}
