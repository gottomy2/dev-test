import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ConnectedGraphsFinderTest {

    @Test
    public void testFindConnectedComponents() {
        ConnectedGraphsFinder cgf = new ConnectedGraphsFinder();

        // Test case: 5 vertices and 4 edges, forming 2 connected components
        int n = 3;
        List<List<Integer>> edges = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5)
        );

        int connectedComponents = cgf.findConnectedComponents(n, edges);
        assertEquals(2, connectedComponents);
    }

    @Test
    public void testLargeDataset() {
        ConnectedGraphsFinder cgf = new ConnectedGraphsFinder();
        Random random = new Random();

        int n = 10000000; // large number of vertices
        List<List<Integer>> edges = new ArrayList<>();

        // Create large number of edges
        for(int i=0; i<n; i++) {
            List<Integer> edge = Arrays.asList(i, random.nextInt(n));
            edges.add(edge);
        }

        // Call the method (just testing for any runtime error, not verifying the result)s
        long timeBefore = System.currentTimeMillis();
        cgf.findConnectedComponents(n, edges);
        long timeAfter = System.currentTimeMillis();

        System.out.println("Time taken: " + (timeAfter - timeBefore) + " ms");
    }

    @Test
    public void testLargeNumbersSet(){
        ConnectedGraphsFinder cgf = new ConnectedGraphsFinder();

        int n = 4;
        List<List<Integer>> edges = Arrays.asList(
                Arrays.asList(1, 305),
                Arrays.asList(305, 2),
                Arrays.asList(4, 802),
                Arrays.asList(802, 12)
        );

        int connectedComponents = cgf.findConnectedComponents(n, edges);
        System.out.println(connectedComponents);
        assertEquals(2, connectedComponents);
    }
}