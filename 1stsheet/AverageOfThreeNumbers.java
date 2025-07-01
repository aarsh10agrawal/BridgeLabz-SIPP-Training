import java.util.Scanner;
public class Main
{
	public static void main(String[] args) {
	    int Average;
	    Scanner sc = new Scanner(System.in);
	    int Num1 = sc.nextInt();
	    int Num2 = sc.nextInt();
	    int Num3 = sc.nextInt();
	    Average= (Num1+Num2+Num3)/3;
	    System.out.print("Average of given numbers are: "+ Average);

	}
}
