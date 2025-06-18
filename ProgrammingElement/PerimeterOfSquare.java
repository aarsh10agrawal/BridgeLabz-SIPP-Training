import java.util.*;
public class perimeterOfSquare{
public static void main(String[] args){
	Scanner input = new Scanner(System.in);
	System.out.println("Enter the perimeter of the square: ");
	int perimeter = input.nextInt();
	int side = perimeter / 4;
	System.out.println("The length of the side is " + side + " whose perimeter is " + perimeter);
	}

}