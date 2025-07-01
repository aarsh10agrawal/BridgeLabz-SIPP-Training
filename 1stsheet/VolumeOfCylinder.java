import java.util.Scanner;
public class Main
{
	public static void main(String[] args) {
	    int volume;
	    Scanner sc = new Scanner(System.in);
	    int radius = sc.nextInt();
	    int height = sc.nextInt();
		volume= 22/7 * radius^2 * height;
	    System.out.print(volume);

	}
}
