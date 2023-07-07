import java.util.*;

public class ConnectedGraphsFinder {
    public static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];

            // Initially, each element is its own parent.
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int element) {
            if (parent[element] != element) {
                // Path compression
                parent[element] = find(parent[element]);
            }
            return parent[element];
        }

        public void union(int element1, int element2) {
            int root1 = find(element1);
            int root2 = find(element2);

            // Elements are already in the same set.
            if (root1 == root2) {
                return;
            }

            // Union by rank
            if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else {
                parent[root2] = root1;
                rank[root1]++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of vertices:");
        int n = Integer.parseInt(scanner.nextLine());

        //Using Hashmap to map vertex values to indices in the UnionFind data structure:
        Map<Integer, Integer> vertexToIndex = new HashMap<>();
        int index = 0;

        //Using UnionFind to keep track of the connected components:
        UnionFind uf = new UnionFind(n*2); // at most 2n vertices

        //Reading the edges and union the vertices:
        System.out.println("Please enter the edges (each edge on a separate line) a b:");
        for (int i = 0; i < n; i++) {
            String[] edge = scanner.nextLine().split(" ");

            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);

            if(!vertexToIndex.containsKey(a)) {
                vertexToIndex.put(a, index++);
            }
            if(!vertexToIndex.containsKey(b)) {
                vertexToIndex.put(b, index++);
            }

            uf.union(vertexToIndex.get(a), vertexToIndex.get(b));
        }

        //Counting the number of distinct connectec components:
        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < index; i++) {
            roots.add(uf.find(i));
        }

        //Output the number of separate connected components:
        System.out.println("The number of separate connected components is: " + roots.size());
    }
}
