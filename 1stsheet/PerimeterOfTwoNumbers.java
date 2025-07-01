import java.util.Scanner;
public class Main
{
	public static void main(String[] args) {
	    int perimeter;
	    Scanner sc = new Scanner(System.in);
	    int length = sc.nextInt();
	    int width = sc.nextInt();
		perimeter= 2*(length+width);
	    System.out.print("perimeters of given numbers are: "+ perimeter);

	}
}
