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
     * Processes the given string of numbers into a list of distinct sorted numbers and statistics.
     *
     * @param input the string of numbers separated by spaces.
     * @return a string representing the processed results.
     */
    public String processInput(String input) {
        List<String> numbers = Arrays.asList(input.split(" "));
        Set<Integer> distinctNumbers = getDistinctNumbers(numbers.toArray(new String[0]));
        TreeSet<Integer> sortedDistinctNumbers = sortDistinctNumbers(distinctNumbers);

        // Building the result:
        StringBuilder result = new StringBuilder();
        sortedDistinctNumbers.forEach(num -> result.append(num).append(" "));
        result.append("\ncount: ").append(numbers.size());
        result.append("\ndistinct: ").append(distinctNumbers.size());
        result.append("\nmin: ").append(sortedDistinctNumbers.first());
        result.append("\nmax: ").append(sortedDistinctNumbers.last());

        return result.toString();
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter integers separated by spaces:");
        String input = scanner.nextLine();

        DistinctSortedAscElements distinctSortedAscElements = new DistinctSortedAscElements(scanner);
        String result = distinctSortedAscElements.processInput(input);

        // Printing the result:
        System.out.println(result);
    }
}