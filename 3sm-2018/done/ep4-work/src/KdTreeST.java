/* Made by: tuts at 26/04/18
 *  Project: ep4work
 *  Dependencies:
 */

import edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;

public class KdTreeST<Value>{

    private Node root;
    private int n;

    private class Node {

        private Point2D p;
        private RectHV  rect;
        private Value   val;
        private Node    lf;
        private Node    rt;
        private int     level;

        public Node(Point2D p, Value v) {
            // RectHV r = rect;
            // if (r == null)
            //     r = new RectHV(0, 0, 1, 1);
            // this.rect   = r;
            this.p      = p;
            this.val = v;
            this.level = 0;
        }

        public int compare(Point2D point) {
            if (level % 2 == 0) {
                if (point.x() < p.x())
                    return 1;
                else if (point.x() > p.x())
                    return -1;
                else
                    return 0;
            } else {
                if (point.y() < p.y())
                    return 1;
                else if (point.y() > p.y())
                    return -1;
                else
                    return 0;
            }
        }
    }

    // Public methods
    public KdTreeST(){
        n = 0;
        root = null;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(Point2D p) {
        return get(p) != null;
    }

    public Value get(Point2D p) {
        return get(p, root, 0);
    }

    public void put(Point2D p, Value val) {
        if (p == null) throw new IllegalArgumentException("calls put() with a null key");
        if (val == null) throw new IllegalArgumentException("calls put() with null value");
        root = put(p, val, root, 0);
    }

    private Node put(Point2D p, Value val, Node node, int level) {
        if (node == null) return new Node(p, val);
        int comp = node.compare(p);
        if      (comp < 0) node.lf  = put(p, val, node.lf, node.level + 1);
        else if (comp > 0) node.rt  = put(p, val, node.rt, node.level + 1);
        else               node.val = val;
        return node;

    }

    private Value get(Point2D p, Node node, int height) {
        if (node == null) return null;
        int comp = node.compare(p);
        if (comp < 0) return get(p, node.lf, node.level + 1);
        else if (comp > 0) return get(p, node.rt, node.level + 1);
        else return node.val;
    }


    /*
    * Return the keys in the tree
    */
    public Iterable<Point2D> points() {
        if (isEmpty()) {
            //System.out.println("Breakpoint 1");
            return new Queue<Point2D>();
        }
        //System.out.println("Breakpoint 2");
        return keys(min(), max());
    }
    private Iterable<Point2D> keys(Point2D lo, Point2D hi) {
        if (lo == null) throw new IllegalArgumentException();
        if (hi == null) throw new IllegalArgumentException();
        //System.out.println("Breakpoint 3");
        
        //System.out.println("Breakpoint do min: "+ hi);
        //System.out.println("Breakpoint do max: "+ lo);

        Queue<Point2D> queue = new Queue<Point2D>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node node, Queue<Point2D> queue, Point2D lo, Point2D hi) {
        if (node == null) return; 
        //System.out.println("Breakpoint 4");

        int cmplo = node.compare(lo);
        //System.out.println("Breakpoint 5 - compare cmplo: " + cmplo);
 
        int cmphi = node.compare(hi); 
        //System.out.println("Breakpoint 6 - compare cmphi: " + cmphi);
        if (cmplo < 0) keys(node.rt, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) {
            //System.out.println("Breakpoint 7 - enqueue p: " + node.p);
            queue.enqueue(node.p); 
        }
        if (cmphi > 0) keys(node.lf, queue, lo, hi);
    }


    /*
    * Methods for min and max
    */
    private Point2D min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).p;
    } 

    private Node min(Node x) { 
        if (x.lf == null) return x; 
        else                return min(x.lf); 
    } 

    private Point2D max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).p;
    } 

    private Node max(Node x) {
        if (x.rt == null) return x; 
        else              return max(x.rt); 
    } 

    //  Todo
    public Iterable<Point2D> range(RectHV rect){
        if (rect == null) throw new IllegalArgumentException();
        Queue<Point2D> q = new Queue<Point2D>();
        for (Point2D p : points()) {
            if (rect.contains(p))
                q.enqueue(p);
        }
        return q;
    }

    public Iterable<Point2D> nearest(Point2D p, int k){ 
        if (p == null) throw new IllegalArgumentException();
        Point2D nearest = null;
        Stack<Point2D> s = new Stack<Point2D>();
        for (Point2D point : points()) {
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
    private boolean contains(Node node, Point2D p, boolean b) {
        if (node == null) return false;
        if (node.p.equals(p)) return true;

        int compare;
        if (b) {
            compare = Point2D.X_ORDER.compare(node.p,p);
        }
        else {
            compare = Point2D.Y_ORDER.compare(node.p,p);
        }

        if (compare < 0) {
            return contains(node.rt, p, !b);
        }
        else {
            return contains(node.lf, p, !b);
        }
    }

    // Unit testing
    public static void main(String[]args){
        KdTreeST<String> points = new KdTreeST<String>();
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

        System.out.println(" Iterating over the tree! ");
        for (Point2D s : points.points()) {
            System.out.println("O valor da chave: " + s);            
        }
        System.out.println(" Finished iterating! ");

    }
}