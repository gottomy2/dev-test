import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DistinctSortedAscElementsTest {

    @Test
    public void testProcessInput() {
        DistinctSortedAscElements distinctSortedAscElements = new DistinctSortedAscElements(new Scanner(System.in));
        String input = "1 10 20 20 5 8 9 18 2 7 7 5 6 8";
        String expected = "1 2 5 6 7 8 9 10 18 20 \n" +
                          "count: 14\n" +
                          "distinct: 10\n" +
                          "min: 1\n" +
                          "max: 20";
        assertEquals(expected, distinctSortedAscElements.processInput(input));
    }

    @Test
    public void testLargeInput(){
        // Generate 10,000 numbers from 0 to 9,999 in random order
        Random rand = new Random(42);  // Random number generator with a fixed seed for repeatability
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 10_000; i++) {
            largeInput.append(rand.nextInt(10_000)).append(' ');
        }

        DistinctSortedAscElements distinctSortedAscElements = new DistinctSortedAscElements(new Scanner(System.in));
        long startTime = System.currentTimeMillis();
        String result = distinctSortedAscElements.processInput(largeInput.toString());
        long endTime = System.currentTimeMillis();

        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
        System.out.println(result);

        assertTrue(result.contains("count: 10000"));
        assertTrue(!result.contains("distinct: 10000"));
        assertTrue(result.contains("min: 0"));
        assertTrue(result.contains("max: 9999"));
    }
}
