/* Made by: tuts at 26/04/18
 *  Project: ep4work
 *  Dependencies:
 */

import edu.princeton.cs.algs4.*;

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

    public Iterable<Point2D> points() {
        if (isEmpty()) {
            return new Queue<Point2D>();
        }
        return keys(min(), max());
    }
    private Iterable<Point2D> keys()
    public Iterable<Point2D> range(RectHV rect){
        return null;
    }

    public Iterable<Point2D> nearest(Point2D p, int k){ 
        return null;
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
    }
}