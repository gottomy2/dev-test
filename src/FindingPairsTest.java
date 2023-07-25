import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindingPairsTest {
    @Test
    public void testFindingPairs() {
        String input = "1 12 3 10 5 8 6 7 11 2 9 4";
        int targetSum = 13;
        FindingPairs app = new FindingPairs(new Scanner(input), targetSum);
        List<FindingPairs.Pair> resultPairs = app.findPairs();
        resultPairs.forEach(pair -> System.out.println(pair.getFirst() + " " + pair.getSecond()));

        assertTrue(resultPairs.contains(new FindingPairs.Pair(1, 12)));
        assertTrue(resultPairs.contains(new FindingPairs.Pair(2, 11)));
        assertTrue(resultPairs.contains(new FindingPairs.Pair(3, 10)));
        assertTrue(resultPairs.contains(new FindingPairs.Pair(4, 9)));
        assertTrue(resultPairs.contains(new FindingPairs.Pair(5, 8)));
        assertTrue(resultPairs.contains(new FindingPairs.Pair(6, 7)));
    }

    @Test
    public void testFindPairsWithDuplicates() {
        String input = "1 12 3 10 5 8 6 7 11 2 9 4 1 12 3 10 5 8 6 7 11 2 9 4";
        int targetSum = 13;
        FindingPairs app = new FindingPairs(new Scanner(input), targetSum);

        List<FindingPairs.Pair> resultPairs = app.findPairs();
        resultPairs.forEach(pair -> System.out.println(pair.getFirst() + " " + pair.getSecond()));

        assertEquals(12, resultPairs.size());
        assertTrue(resultPairs.contains(new FindingPairs.Pair(1, 12)));
        assertTrue(resultPairs.contains(new FindingPairs.Pair(3, 10)));
        assertTrue(resultPairs.contains(new FindingPairs.Pair(5, 8)));
        assertTrue(resultPairs.contains(new FindingPairs.Pair(2, 11)));
        assertTrue(resultPairs.contains(new FindingPairs.Pair(4, 9)));
        assertTrue(resultPairs.contains(new FindingPairs.Pair(6, 7)));
    }
}
