import java.util.Scanner;
public class Main
{
	public static void main(String[] args) {
	    int Fahrenheit;
	    Scanner sc = new Scanner(System.in);
	    int celsius = sc.nextInt();
		Fahrenheit= (celsius * 9/5) + 32;
	    System.out.print(Fahrenheit);

	}
}
