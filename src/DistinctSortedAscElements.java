import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class represents a simple application that reads integers from the console,
 * removes duplicates, and prints the result in ascending order.
 * The application also prints the count of the numbers, the count of the distinct numbers,
 * the minimum and the maximum number.
 *
 * @author gottomy2
 */
public class DistinctSortedAscElements {

    /**
     * The entry point of the application.
     *
     * @param args command line arguments (not used in this application).
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        // Reading input:
        System.out.println("Please enter integers separated by spaces:");
        String input = scanner.nextLine();

        // Transforming input into an array of integers:
        String[] numbers = input.split(" ");

        // HashSet is used to remove duplicates
        Set<Integer> distinctNumbers = new HashSet<>();

        for (String number : numbers) {
            distinctNumbers.add(Integer.parseInt(number));
        }

        // TreeSet is used to sort the distinct numbers
        TreeSet<Integer> sortedDistinctNumbers = new TreeSet<>(distinctNumbers);
        int minValue = sortedDistinctNumbers.first();
        int maxValue = sortedDistinctNumbers.last();

        //Printing the result:
        sortedDistinctNumbers.forEach(num -> System.out.print(num + " "));
        System.out.println("\ncount: " + numbers.length);
        System.out.println("distinct: " + distinctNumbers.size());
        System.out.println("min: " + minValue);
        System.out.println("max: " + maxValue);
    }
}