
import java.util.Scanner;
/**
 * Runs WiCityDistances.
 *
 * @author Noah Luddy
 * @version 1.0
 */
public class Driver
{
    private static Scanner myScan;

	public static void main(String args[]) throws Exception {
        //System.out.println("\f");

        WICityDistances myMiles = new WICityDistances();
        while (true) //Ethan helped with the while(true) and System.exit(0)
        {
            myScan = new Scanner(System.in);
            System.out.print("\nWelcome! Welcome!\nHere is what you can do:\n1 - Print the Table\n2 - Get the average distance from each city to all the other cities\n3 - Get the city pair with the smallest distance between them\n4 - Get the city pair with the largest distance between them\n5 - Get the distance between any two cities\n6 - Exit the program\nPlease enter the number of what you would like to do: ");
            String choice = myScan.nextLine();
            System.out.println("");
            switch (choice) {
                case "1":
                myMiles.printTable();
                break;

                case "2":
                System.out.println("The average distances(in miles) are:");
                for (int i = 0; i < 17; i++) {
                    System.out.println(myMiles.getNames()[i] + " - " + myMiles.avgDistance()[i]);
                }
                break;

                case "3":
                String[] closest = myMiles.minDistance();
                System.out.println("The closest cities are: " + closest[0] + " and " + closest[1]);
                break;

                case "4":
                String[] farthest = myMiles.maxDistance();
                System.out.println("The farthest cities are: " + farthest[0] + " and " + farthest[1]);
                break;

                case "5":
                System.out.print("Please enter the first city: ");
                String city1 = myScan.nextLine();
                while (myMiles.findCityIndex(city1) == -1) {
                    System.out.print("Invalid city. Please enter the first city: ");
                    city1 = myScan.nextLine();
                }
                System.out.print("Please enter the second city: ");
                String city2 = myScan.nextLine();
                while (myMiles.findCityIndex(city2) == -1) {
                    System.out.print("Invalid city. Please enter the second city: ");
                    city2 = myScan.nextLine();
                }
                System.out.println("\nThe distance between " + city1 + " and " + city2 + " is: " + myMiles.distance(city1, city2));
                break;

                case "6":
                System.exit(0);
                break;

                default:
                System.out.print("You entered an invalid choice. Please enter a number 1-6.\n");
                //break;
            }
        }
    }
}
