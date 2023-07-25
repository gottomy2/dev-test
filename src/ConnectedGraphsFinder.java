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
        private Map<Integer, Integer> parent;
        private Map<Integer, Integer> rank;

        /**
         * Initializes a UnionFind data structure for the given size.
         * parent - maps each element to its parent.
         * rank - maps each element to its rank.
         */
        public UnionFind() {
            parent = new HashMap<>();
            rank = new HashMap<>();
        }

        /***
         * Finds the representative element (root) of the set containing the given element.
         * Performs path compression to flatten the tree for faster subsequent finds.
         *
         * @param element The element to find.
         * @return The representative element of the set containing the given element.
         */
        public int find(int element) {
            if (!parent.containsKey(element)) {
                parent.put(element, element);
                rank.put(element, 1);
                return element;
            }

            if (parent.get(element) != element) {
                parent.put(element, find(parent.get(element)));
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

            if (root1 == root2) {
                return;
            }

            if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);
            } else if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);
            } else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
            }
        }
    }

    /**
     * Finds the number of separate connected components in a graph represented by a list of edges.
     * @param n The number of vertices in the graph.
     * @param edges The list of edges in the graph.
     * @return The number of separate connected components in the graph.
     */
    public int findConnectedComponents(int n, List<List<Integer>> edges) {
        UnionFind uf = new UnionFind();

        // Reading the edges and union the vertices:
        for (List<Integer> edge : edges) {
            int a = edge.get(0);
            int b = edge.get(1);

            uf.union(a, b);
        }

        Set<Integer> roots = new HashSet<>();
        for (int i : uf.parent.keySet()) {
            roots.add(uf.find(i));
        }

        return roots.size();
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

        System.out.println("Please enter the edges (each edge on a separate line) a b:");
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> edge = Arrays.asList(scanner.nextLine().split(" "));
            edges.add(Arrays.asList(Integer.parseInt(edge.get(0)), Integer.parseInt(edge.get(1))));
        }

        ConnectedGraphsFinder cgf = new ConnectedGraphsFinder();
        int connectedComponents = cgf.findConnectedComponents(n, edges);
        System.out.println("The number of separate connected components is: " + connectedComponents);
    }
}