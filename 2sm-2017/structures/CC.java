/* WARNING: Adapted to introcs */

/******************************************************************************
*  Compilation:  javac CC.java
*  Execution:    java CC filename.txt
*  Dependencies: Graph.java StdOut.java Queue.java
*  Data files:   http://algs4.cs.princeton.edu/41graph/tinyG.txt
*                http://algs4.cs.princeton.edu/41graph/mediumG.txt
*                http://algs4.cs.princeton.edu/41graph/largeG.txt
*
*  Compute connected components using depth first search.
*  Runs in O(E + V) time.
*
*  % java CC tinyG.txt
*  3 components
*  0 1 2 3 4 5 6
*  7 8
*  9 10 11 12
*
*  % java CC mediumG.txt
*  1 components
*  0 1 2 3 4 5 6 7 8 9 10 ...
*
*  % java -Xss50m CC largeG.txt
*  1 components
*  0 1 2 3 4 5 6 7 8 9 10 ...
*
*  Note: This implementation uses a recursive DFS. To avoid needing
*        a potentially very large stack size, replace with a non-recurisve
*        DFS ala NonrecursiveDFS.java.
*
******************************************************************************/

/**
*  The {@code CC} class represents a data type for
*  determining the connected components in an undirected graph.
*  The <em>id</em> operation determines in which connected component
*  a given vertex lies; the <em>connected</em> operation
*  determines whether two vertices are in the same connected component;
*  the <em>count</em> operation determines the number of connected
*  components; and the <em>size</em> operation determines the number
*  of vertices in the connect component containing a given vertex.

*  The <em>component identifier</em> of a connected component is one of the
*  vertices in the connected component: two vertices have the same component
*  identifier if and only if they are in the same connected component.

*  <p>
*  This implementation uses depth-first search.
*  The constructor takes time proportional to <em>V</em> + <em>E</em>
*  (in the worst case),
*  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
*  Afterwards, the <em>id</em>, <em>count</em>, <em>connected</em>,
*  and <em>size</em> operations take constant time.
*  <p>
*  For additional documentation, see <a href="http://algs4.cs.princeton.edu/41graph">Section 4.1</a>
*  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
*
*  @author Robert Sedgewick
*  @author Kevin Wayne
*/
public class CC {
  private static ST<String, Boolean> marked;   // marked[v] = has vertex v been marked?
  private static ST<String, Integer> id;       // id[v] = id of connected component containing v
  private static int[] size;         // size[id] = number of vertices in given component
  private static int count;          // number of connected components

  /**
  * Computes the connected components of the undirected graph {@code G}.
  *
  * @param G the undirected graph
  */
  public CC(Graph G) {
    marked = new ST<String, Boolean>();
    for (String v : G.vertices()) marked.put(v, false);
    id = new ST<String, Integer>();
    size = new int[G.V()];
    for (String v : G.vertices()) {
      if (!marked.get(v)) {
        // dfs(G, v);
        count++;
      }
    }
  }

  // Stack<String> stack;

  // depth-first search for a Graph
  public static void dfs(Graph G, String ini, String next, String concat, int height) {
    // stack = new Stack<String>();
    marked.put(next, true);
    id.put(next, count);
    size[count]++;
    // height++;
    for (String w : G.adjacentTo(next)) {
      if (!marked.get(w)) {
        dfs(G, ini, w, concat, height+1);
      }
      concat += w;
    }
    System.out.println(concat);
  }

  /**
  * Returns the component id of the connected component containing vertex {@code v}.
  *
  * @param  v the vertex
  * @return the component id of the connected component containing vertex {@code v}
  * @throws IllegalArgumentException unless {@code 0 <= v < V}
  */
  public int id(String v) {
    validateVertex(v);
    return id.get(v);
  }

  /**
  * Returns the number of vertices in the connected component containing vertex {@code v}.
  *
  * @param  v the vertex
  * @return the number of vertices in the connected component containing vertex {@code v}
  * @throws IllegalArgumentException unless {@code 0 <= v < V}
  */
  public int size(String v) {
    validateVertex(v);
    return size[id.get(v)];
  }

  /**
  * Returns the number of connected components in the graph {@code G}.
  *
  * @return the number of connected components in the graph {@code G}
  */
  public int count() {
    return count;
  }

  /**
  * Returns true if vertices {@code v} and {@code w} are in the same
  * connected component.
  *
  * @param  v one vertex
  * @param  w the other vertex
  * @return {@code true} if vertices {@code v} and {@code w} are in the same
  *         connected component; {@code false} otherwise
  * @throws IllegalArgumentException unless {@code 0 <= v < V}
  * @throws IllegalArgumentException unless {@code 0 <= w < V}
  */
  public boolean connected(String v, String w) {
    validateVertex(v);
    validateVertex(w);
    return id(v) == id(w);
  }

  // throw an IllegalArgumentException unless {@code 0 <= v < V}
  private void validateVertex(String v) {
    if (!marked.contains(v))
    throw new IllegalArgumentException("vertex not in graph");
  }

  /**
  * Unit tests the {@code CC} data type.
  *
  * @param args the command-line arguments
  */
  public static void main(String[] args) {
    In in = new In(args[0]);
    Graph G = new Graph(in,"/");
    StdOut.println(G.V() + " vertices and " + G.E() + " edges");

    CC cc = new CC(G);

    // number of connected components
    int m = cc.count();
    StdOut.println(m + " components");

    // compute list of vertices in each connected component
    Queue<String>[] components = (Queue<String>[]) new Queue[m];
    for (int i = 0; i < m; i++)
    components[i] = new Queue<String>();

    for (String v : G.vertices())
    components[cc.id(v)].enqueue(v);

    // print results
    for (int i = 0; i < m; i++) {
      for (String v : components[i])
      StdOut.print(v + " ");
      StdOut.println();
    }
  }
}
