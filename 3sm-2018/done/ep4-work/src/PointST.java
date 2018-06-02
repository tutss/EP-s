/* Made by: tuts at 26/04/18
 *  Project: ep4work
 *  Dependencies: Point2D.java
 */


import edu.princeton.cs.algs4.*;

public class PointST<Value>{
    // Instance variables
    private RedBlackBST<Point2D, Value> tree;
    // Public methods
    public PointST(){
        tree = new RedBlackBST<Point2D, Value>();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }
    public void put(Point2D p, Value val) {
        if (p == null || val == null) throw new IllegalArgumentException();
        tree.put(p,val);
    }
    public Value get(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return tree.get(p);
    }
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return tree.contains(p);
    }
    public Iterable<Point2D> points() {
        return tree.keys();
    }
    public Iterable<Point2D> range(RectHV rect){
        if (rect == null) throw new IllegalArgumentException();
        Queue<Point2D> q = new Queue<Point2D>();
        for (Point2D p : tree.keys()) {
            if (rect.contains(p))
                q.enqueue(p);
        }
        return q;
    }
    public Iterable<Point2D> nearest(Point2D p, int k){
        if (p == null) throw new IllegalArgumentException();
        Point2D nearest = null;
        Stack<Point2D> s = new Stack<Point2D>();
        for (Point2D point : tree.keys()) {
            if (nearest == null || point.distanceTo(p) < nearest.distanceTo(p))
                nearest = point;
                s.push(nearest);
        }
        Stack<Point2D> ret = new Stack<Point2D>();
        for (int i = 0; i < k; i++) {
            ret.push(s.pop());
        }
        return ret;
    }
    // Private methods


    // Unit testing
    public static void main(String[]args){
        PointST<String> points = new PointST<String>();
        int n = 10;

        Point2D[] points_array = new Point2D[n];
        String[] letter = {"a","b","c","d","e","f","g","h","i","j"};
        for (int i = 0; i < n; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            points_array[i] = new Point2D(x, y);
        }
        
        for (int i = 0; i < 10; i++) {
            System.out.print(letter[i] + ", ");
            System.out.print(points_array[i] + ", ");

            System.out.println(" ------------- ");
            System.out.println(" Putting in tree! ");

            points.put(points_array[i],letter[i]);       
        }
        
        System.out.println(" Finished putting! ");
        System.out.println(" Getting from the tree ");

        for (int i = 0; i < 10; i++) {
            String letter_at = points.get(points_array[i]);
            System.out.println("Point: " + points_array[i] + " got letter " + letter_at);   
        }
        System.out.println(" Finished getting! ");


    }
}