import java.util.*;

/**
 * The ConnectedGraphsFinder class is responsible for finding the number of
 * separate connected components in a graph represented by a list of edges.
 * The class uses the Union-Find data structure to efficiently keep track of
 * connected components.
 *
 * @author gottomy2
 */
public class ConnectedGraphsFinder {

    /**
     * The UnionFind class is a data structure that keeps track of a set of elements
     * partitioned into a number of disjoint (non-overlapping) subsets.
     * It supports union and find operations and is commonly used in algorithms
     * that deal with finding connected components in a graph.
     */
    public static class UnionFind {
        private List<Integer> parent;
        private List<Integer> rank;

        /**
         * Initializes a UnionFind data structure for the given size.
         *
         * @param size The total number of elements.
         */
        public UnionFind(int size) {
            parent = new ArrayList<>(Collections.nCopies(size, 0));
            rank = new ArrayList<>(Collections.nCopies(size, 0));

            // Initially, each element is its own parent.
            for (int i = 0; i < size; i++) {
                parent.set(i, i);
                rank.set(i, 1);
            }
        }

        /**
         * Finds the representative element (root) of the set containing the given element.
         * Performs path compression to flatten the tree for faster subsequent finds.
         *
         * @param element The element to find.
         * @return The representative element of the set containing the given element.
         */
        public int find(int element) {
            if (parent.get(element) != element) {
                // Path compression
                parent.set(element, find(parent.get(element)));
            }
            return parent.get(element);
        }

        /**
         * Unions the sets containing the two given elements.
         * The element in the set with higher rank becomes the parent of the element in the set with lower rank.
         * In case of tie, the second element becomes parent and its rank increases.
         *
         * @param element1 The first element.
         * @param element2 The second element.
         */
        public void union(int element1, int element2) {
            int root1 = find(element1);
            int root2 = find(element2);

            // Elements are already in the same set.
            if (root1 == root2) {
                return;
            }

            // Union by rank
            if (rank.get(root1) < rank.get(root2)) {
                parent.set(root1, root2);
            } else if (rank.get(root1) > rank.get(root2)) {
                parent.set(root2, root1);
            } else {
                parent.set(root2, root1);
                rank.set(root1, rank.get(root1) + 1);
            }
        }
    }

    /**
     * The main method reads the edges from the user input and uses the UnionFind
     * class to determine the number of separate connected components in the graph.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of vertices:");
        int n = Integer.parseInt(scanner.nextLine());

        // Using Hashmap to map vertex values to indices in the UnionFind data structure:
        Map<Integer, Integer> vertexToIndex = new HashMap<>();
        int index = 0;

        // Using UnionFind to keep track of the connected components:
        UnionFind uf = new UnionFind(n*2); // at most 2n vertices

        // Reading the edges and union the vertices:
        System.out.println("Please enter the edges (each edge on a separate line) a b:");
        for (int i = 0; i < n; i++) {
            List<String> edge = Arrays.asList(scanner.nextLine().split(" "));

            int a = Integer.parseInt(edge.get(0));
            int b = Integer.parseInt(edge.get(1));

            if(!vertexToIndex.containsKey(a)) {
                vertexToIndex.put(a, index++);
            }
            if(!vertexToIndex.containsKey(b)) {
                vertexToIndex.put(b, index++);
            }

            uf.union(vertexToIndex.get(a), vertexToIndex.get(b));
        }

        // Counting the number of distinct connected components:
        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < index; i++) {
            roots.add(uf.find(i));
        }

        // Output the number of separate connected components:
        System.out.println("The number of separate connected components is: " + roots.size());
    }
}