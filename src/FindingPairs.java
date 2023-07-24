import java.util.*;

/**
 * This class finds pairs of integers in the input that sum up to a target value.
 * According to the rule, that first number in the pair should be smaller than the second number.
 * The application also prints the result in ascending order.
 *
 * @author gottomy2
 */
public class FindingPairs {

    private Scanner scanner;
    private int targetSum;

    /**
     * Initializes a new FindingPairs object with the provided scanner and target sum.
     *
     * @param scanner the scanner used for reading input.
     * @param targetSum the sum to which pairs of numbers must add up.
     */
    public FindingPairs(Scanner scanner, int targetSum) {
        this.scanner = scanner;
        this.targetSum = targetSum;
    }

    /**
     * Reads input from the user and splits it into a list of numbers.
     *
     * @return a list of string representation of the numbers entered by the user.
     */
    public List<String> readInput() {
        System.out.println("Please enter integers separated by spaces:");
        String input = scanner.nextLine();
        return Arrays.asList(input.split(" "));
    }

    /**
     * Counts the occurrences of each number in the provided list.
     *
     * @param numCounts a map to store the count of each number.
     * @param numbers the list of numbers to count.
     */
    private void countNumbers(Map<Integer, Integer> numCounts, List<String> numbers) {
        for (String number : numbers) {
            int num = Integer.parseInt(number);
            numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
        }
    }

    /**
     * Finds pairs of numbers that add up to the target sum and adds them to the provided list.
     *
     * @param resultPairs a list to store the resulting pairs.
     * @param numCounts a map storing the count of each number.
     * @param numbers the list of numbers in which to find pairs.
     */
    private void findPairsThatSumUpToTarget(List<Pair> resultPairs, Map<Integer, Integer> numCounts, List<String> numbers) {
        for (String number : numbers) {
            int num = Integer.parseInt(number);
            int complement = targetSum - num;

            if (numCounts.containsKey(complement)) {
                int times = Math.min(numCounts.get(num), numCounts.get(complement));
                for (int i = 0; i < times; i++) {
                    resultPairs.add(new Pair(Math.min(num, complement), Math.max(num, complement)));
                }
                numCounts.remove(num);
                numCounts.remove(complement);
            }
        }
    }

    /**
     * Processes the user's input, finds pairs of numbers that add up to the target sum, and returns a list of these pairs.
     *
     * @return a list of pairs of numbers that add up to the target sum.
     */
    public List<Pair> findPairs() {
        Map<Integer, Integer> numCounts = new HashMap<>();
        List<Pair> resultPairs = new ArrayList<>();

        List<String> numbers = readInput();

        countNumbers(numCounts, numbers);
        findPairsThatSumUpToTarget(resultPairs, numCounts, numbers);

        Collections.sort(resultPairs);

        return resultPairs;
    }

    /**
     * The entry point of the application.
     *
     * @param args command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        FindingPairs findingPairs = new FindingPairs(new Scanner(System.in), 13);
        List<Pair> resultPairs = findingPairs.findPairs();
        resultPairs.forEach(pair -> System.out.println(pair.getFirst() + " " + pair.getSecond()));
    }

    /**
     * The {@code Pair} class represents a pair of integers.
     */
    public static class Pair implements Comparable<Pair> {
        int first, second;

        /**
         * Constructs a new pair of integers.
         *
         * @param first  the first integer in the pair.
         * @param second the second integer in the pair.
         */
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        /**
         * Compares this pair with the specified pair for order.
         * Returns a negative integer, zero, or a positive integer as this object is less than, equal to,
         * or greater than the specified object.
         *
         * @param o the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         */
        @Override
        public int compareTo(Pair o) {
            if (first != o.first) {
                return Integer.compare(first, o.first);
            }
            return Integer.compare(second, o.second);
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }
    }
}