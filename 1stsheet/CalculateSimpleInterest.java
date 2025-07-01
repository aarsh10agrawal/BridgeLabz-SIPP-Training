import java.util.Scanner;
public class Main
{
	public static void main(String[] args) {
	    int SI;
	    Scanner sc = new Scanner(System.in);
	    int princile = sc.nextInt();
	    int rate = sc.nextInt();
	    int time = sc.nextInt();
		SI= (princile*rate*time)/100;
	    System.out.print("Simple Interest of given numbers are: "+ SI);

	}
}
