import java.util.*;

/**
 * Author: gottomy2
 * This class represents a simple application that reads integers from the console,
 * finds pairs that sum up to a target sum (by default 13),
 * According to the rule, that first number in the pair should be smaller than the second number.
 * The application also prints the result in ascending order.
 */
public class FindingPairs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Using HashMap to count the occurence of each number
        Map<Integer, Integer> numCounts = new HashMap<>();

        // Using ArrayList to store the pairs
        List<Pair> resultPairs = new ArrayList<>();

        int targetSum = 13;

        // Reading input:
        System.out.println("Please enter integers separated by spaces:");
        String input = scanner.nextLine();
        String[] numbers = input.split(" ");

        //Count the occurence of each number
        for (String number : numbers) {
            int num = Integer.parseInt(number);
            if (numCounts.containsKey(num)) {
                numCounts.put(num, numCounts.get(num) + 1);
            } else {
                numCounts.put(num, 1);
            }
        }

        //Finding pairs that sum up to the targetSum
        for (String number : numbers) {
            int num = Integer.parseInt(number);
            int complement = targetSum - num;

            if (numCounts.containsKey(complement)) {
                int times = Math.min(numCounts.get(num), numCounts.get(complement));
                for (int i = 0; i < times; i++) {
                    //making sure that first number is smaller than the second
                    resultPairs.add(new Pair(Math.min(num, complement), Math.max(num, complement)));
                }
                // remove so that they are not counted again
                numCounts.remove(num);
                numCounts.remove(complement);
            }
        }

        //Sorting pairs in ascending order:
        Collections.sort(resultPairs);

        //Printing the result:
        resultPairs.forEach(pair -> System.out.println(pair.first + " " + pair.second));
    }

    // Simple class to represent a pair of integers
    static class Pair implements Comparable<Pair> {
        int first, second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        // Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
        @Override
        public int compareTo(Pair o) {
            if (first != o.first) {
                return Integer.compare(first, o.first);
            }
            return Integer.compare(second, o.second);
        }
    }
}
