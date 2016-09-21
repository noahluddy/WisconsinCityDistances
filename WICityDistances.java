
import java.util.Scanner;
import java.io.File;
/**
 * Get all your information about distances to and from Wisconsin Cities!
 *
 * @author Noah Luddy
 * @version 1.0
 */
public class WICityDistances {
    // instance variables
    String[] names = new String[17];
    int[][] miles = new int[17][17];
	private Scanner myScan;

    /**
     * Constructor for objects of class WiCityDistances
     * Takes zero parameters and fills names and miles with their respective data
     */
    public WICityDistances() throws Exception {
        File mileage = new File("/Users/Schatzy/Desktop/Projects/EclipseProjects/WICityDistances/Mileage.csv");
        myScan = new Scanner(mileage);
        String[] dataArray;
        String data;
        int line = 0;

        while (myScan.hasNext()) {
            data = myScan.nextLine();
            dataArray = data.split(",");
            if (line == 0) {
                names = dataArray;
            } else {
                for (int i = 0; i < dataArray.length; i++) {
                    miles[line-1][i] = Integer.parseInt(dataArray[i]);
                }
            }
            line++;
        }
    }

    //Methods
    /**
     * Returns a one dimensional String array with the names of each city
     * @return names - the String array of city names
     */
    public String[] getNames() {
        return names;
    }

    /**
     * Returns the index of a city in the names array. Returns -1 if not found.
     * @return index - the index of the city in the names array
     */
    public int findCityIndex(String city) { //public because I needed to use it in the runner as well
        int index = -1;

        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(city)) {
                index = i;
            }
        }

        return index;
    }

    /**
     * Prints the table of distances with the city names on top and to the left
     * No parameters and no return value
     */
    public void printTable() { //From class
        System.out.print("\t\t");

        for (int i = 0; i < names.length; i++) {
            System.out.print(names[i]);

            for (int j = 16 - names[i].length(); j > 0; j--) {
                System.out.print(" ");
            }
        }

        System.out.println("");

        for (int k = 0; k < miles.length; k++) {
            System.out.print(names[k] + "\t");

            if (names[k].length() <= 7) {
                System.out.print("\t");
            }

            for (int r = 0; r < miles.length; r++) {
                System.out.print(miles[k][r] + "\t\t");
            }
            System.out.println();
        }
    }

    /**
     * Returns a one dimensional double array with the average distances to and from each city minus the distance to itself(0)
     * The index of the array corresponds to the index of the city in the names array
     * @return avg - the double array with the average values
     */
    public double[] avgDistance() {
        double[] avg = new double[names.length];
        double sum = 0;

        for (int i = 0; i < miles.length; i++) {
            for (int j = 0; j < miles.length; j++) {
                sum += miles[i][j];
            }
            avg[i] = sum/(double)(names.length-1); //The -1 takes care of the 0
            sum = 0;
        }

        return avg;
    }

    /**
     * Returns a one dimensional String array with two values, the cities that are the closest together
     * @return minCities - the String array of two cities
     */
    public String[] minDistance() { //From class
        String[] minCities = new String[2];
        int smallest = Integer.MAX_VALUE;

        for (int i = 0; i < miles.length; i++) {
            for (int j = 0; j < miles[i].length && miles[i][j] != 0; j++) {
                if (miles[i][j] < smallest) {
                    smallest = miles[i][j];
                    minCities[0] = names[i];
                    minCities[1] = names[j];
                }
            }
        }

        return minCities;
    }

    /**
     * Returns a one dimensional String array with two values, the cities that are the farthest apart
     * @return maxCities - the String array of two cities
     */
    public String[] maxDistance() {
        String[] maxCities = new String[2];
        int largest = Integer.MIN_VALUE;

        for (int i = 0; i < miles.length; i++) {
            for (int j = 0; j < miles[i].length && miles[i][j] != 0; j++) {
                if (miles[i][j] > largest) {
                    largest = miles[i][j];
                    maxCities[0] = names[i];
                    maxCities[1] = names[j];
                }
            }
        }

        return maxCities;
    }

    /**
     * Takes two cities as parameters and returns the int value of the distance between them
     * @param city1 the first city
     * @param city2 the second city
     * @return distance - the int value of the distance betw
     */
    public int distance(String city1, String city2) {
        int cityOne = this.findCityIndex(city1);
        int cityTwo = this.findCityIndex(city2);
        int distance = miles[cityOne][cityTwo];

        return distance;
    }
}
