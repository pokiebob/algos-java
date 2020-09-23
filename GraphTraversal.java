import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class AdjacencyLists {
    int n;
    List<Integer>[] adj;
    AdjacencyLists (int n0) {
        n = n0;
        adj = (List<Integer>[]) new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new Stack<Integer>();
        }
    }

    void addEdge(int i, int j) {
        adj[i].add(j);
    }

    void advAdd(int i, int[] t) {
        for (int j : t) {
            adj[i].add(j);
        }
    }

    void dfs(int start, int end) {
        Stack<List<Integer>> path = new Stack<List<Integer>>();
        boolean[] seen = new boolean[adj.length];
        seen[start] = true;

        boolean found = false;
        int current = start;

        int i = 0;
        while (!found) {
            for (Integer j : adj[current]) {
                if (!seen[j]) {
                    System.out.printf("[%d] visit node %d\n", i, j);
                    i++;
                    path.add(adj[j]);
                    seen[j] = true;
                    current = j;
                    if (j == end) {
                        found = true;
                    }
                }
            }
        }
    }

    List<Integer> bfs(int start, int end) {
        List<Integer> q = new ArrayList<Integer>();
        int[] seen = new int[adj.length];
        q.add(start);
        seen[start] = 1;
        boolean found = false;
        List<Integer> path = new ArrayList<Integer>();
        boolean noneSeen = true;

        int level = 2;
        while (!found && !q.isEmpty()) {
            noneSeen = true;
            int k = q.remove(0);
//            System.out.printf("\nk: %d, level: %d", k, level);
            for (Integer j : adj[k]) {
                if (seen[j] == 0) {
                    q.add(j);
                    seen[j] = level;
                    noneSeen = false;
                    System.out.println("\n new seen");
                    for (Integer lvl : seen) {
                        System.out.println(lvl);
                    }
                    if (j == end) {
                        found = true;
                    }
                }
            }
            if (! noneSeen) {
                level++;
            }
        }
        if (! q.isEmpty()) {
            path.add(end);
            int current = end;
            while (current != start) {
                //System.out.println(current);
                for (int loc = 0; loc < seen.length; loc++) {
                    if (adj[loc].contains(current) && seen[loc] == seen[current] - 1) {
                        current = loc;
                        path.add(0, current);
                    }
                }

            }
        }
    return path;

    }

    void prettyPrint() {
        for (int i = 0; i < adj.length; i++) {
            System.out.printf("%d: %s\n",i,adj[i].toString());
        }
    }

}

public class GraphTraversal {


    public static void main(String[] args) {
        AdjacencyLists al = new AdjacencyLists(6);
//        al.addEdge(0, 1);
//        al.addEdge(0, 4);
        al.advAdd(0, new int[]{1, 4});
        al.advAdd(1, new int[]{2, 3, 4});
        al.addEdge(2,5);
        al.addEdge(3,4);
        al.advAdd(4,new int[]{1,2});
        al.addEdge(5,4);
        al.prettyPrint();
        //al.dfs(0,5);
//        System.out.println(al.bfs(0, 4));
//        System.out.println(al.bfs(0, 5));
//        System.out.println(al.bfs(3, 5));
        System.out.println(al.bfs(5,0));
    }
}
