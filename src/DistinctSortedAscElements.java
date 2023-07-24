import java.util.*;

/**
 * This class represents a simple application that reads integers from the console,
 * removes duplicates, and prints the result in ascending order.
 * The application also prints the count of the numbers, the count of the distinct numbers,
 * the minimum and the maximum number.
 *
 * @author gottomy2
 */
public class DistinctSortedAscElements {

    private Scanner scanner;

    /**
     * Initializes a new DistinctSortedAscElements object with the provided scanner
     *
     * @param scanner the scanner used for reading input.
     */
    public DistinctSortedAscElements(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads input from the user and splits it into a list of numbers.
     *
     * @return a list of string representation of the numbers entered by the user.
     */
    public List<String> readInput() {
        System.out.println("Please enter integers separated by spaces:");
        return Arrays.asList(scanner.nextLine().split(" "));
    }

    /**
     * Transforms an array of strings into a set of distinct integers.
     *
     * @param numbers the array of string representations of the numbers.
     * @return a set of distinct integers.
     */
    public Set<Integer> getDistinctNumbers(String[] numbers) {
        Set<Integer> distinctNumbers = new HashSet<>();
        for (String number : numbers) {
            distinctNumbers.add(Integer.parseInt(number));
        }
        return distinctNumbers;
    }

    /**
     * Sorts the given set of distinct numbers in ascending order.
     *
     * @param distinctNumbers the set of distinct numbers to be sorted.
     * @return a TreeSet containing the distinct numbers sorted in ascending order.
     */
    public TreeSet<Integer> sortDistinctNumbers(Set<Integer> distinctNumbers) {
        return new TreeSet<>(distinctNumbers);
    }

    /**
     * The entry point of the application.
     *
     * @param args command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        DistinctSortedAscElements distinctSortedAscElements = new DistinctSortedAscElements(new Scanner(System.in));
        List<String> numbers = distinctSortedAscElements.readInput();
        Set<Integer> distinctNumbers = distinctSortedAscElements.getDistinctNumbers(numbers.toArray(new String[0]));
        TreeSet<Integer> sortedDistinctNumbers = distinctSortedAscElements.sortDistinctNumbers(distinctNumbers);

        //Printing the result:
        sortedDistinctNumbers.forEach(num -> System.out.print(num + " "));
        System.out.println("\ncount: " + numbers.size());
        System.out.println("distinct: " + distinctNumbers.size());
        System.out.println("min: " + sortedDistinctNumbers.first());
        System.out.println("max: " + sortedDistinctNumbers.last());
    }
}