import java.util.*;
public class volumeOfEarth{
	public static void main(String[] args){
	double radiusInKilometer = 6378;
	double radiusInMiles = 6378 * 1.6;
	double volumeInKilometer = (4/3) * 3.14 * Math.pow(radiusInKilometer, 3);
	double volumeInMiles = (4/3) * 3.14 * Math.pow(radiusInMiles, 3);
	System.out.println("The volume of earth in cubic kilometers is " + volumeInKilometer + " and cubic miles is " + volumeInMiles);

	}
}